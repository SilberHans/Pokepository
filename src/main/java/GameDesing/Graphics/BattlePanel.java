package GameDesing.Graphics;

import GameDesing.BattleSystem.Actions.MoveAction;
import GameDesing.BattleSystem.Actions.SwitchAction;
import GameDesing.BattleSystem.Actions.TurnAction;
import GameDesing.BattleSystem.TurnResult;
import GameDesing.Game;
import Persons.Trainer;
// Asegúrate de importar las clases correctas de la lógica
import Pokemons.Logic.Items.Item; 
import Pokemons.Logic.Movements.Move;
import Pokemons.Pokemon;
import java.util.Random;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList; // Importa LinkedList
import java.util.Queue; // Importa Queue
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * BattlePanel actúa como el "Director" (Frontend).
 * Maneja la GUI, las animaciones y la entrada del usuario.
 * NO contiene lógica de batalla. Pide a 'Game.java' (el "Cerebro")
 * que calcule los resultados del turno.
 */
public class BattlePanel extends JPanel implements Runnable {

    // SCREEN CONFIG
    final int originalTileSize = 32;
    final int scale = 2;
    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 12;
    final int maxScreenRow = 9;
    private final int screenWidth = tileSize * maxScreenCol;
    private final int screenHeight = tileSize * maxScreenRow;

    // --- SISTEMA DE BATALLA (LÓGICA) ---
    private Queue<TurnResult> pendingResultsQueue = new LinkedList<>();
    private boolean isProcessingAnimation = false; // Flag para pausar la lógica durante animaciones
    private boolean battleStarted = false; // Flag para saber cuándo empezar a dibujar Pokémon/HP

    // SYSTEM
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Random random = new Random();

    // --- CONEXIÓN ---
    private Game game;
    public Trainer trainer1;
    public Trainer trainer2;
    public Pokemon trainer1ActivePokemon;
    public Pokemon trainer2ActivePokemon;

    // --- GRÁFICOS ---
    UI ui = new UI(this);
    int FPS = 60;
    BufferedImage background;
    BufferedImage pokeballSprite;
    String[] battleOptions = {"FIGHT", "POKEMON", "ITEM", "RUN"}; // Opciones del menú principal

    // --- BATTLE STATE MACHINE (Máquina de Estados Gráfica) ---
    public int battleState;
    public final int STATE_START = 0; // Inicio, mensaje "VS"
    public final int STATE_P1_SEND_ANIM = 3; // Animación de T1 lanzando
    public final int STATE_P1_SEND_ANIM_WAIT = 4; // ¡CONSTANTE AÑADIDA!
    public final int STATE_P2_SEND_ANIM = 5; // Animación de T2 lanzando
    public final int STATE_P2_SEND_ANIM_WAIT = 6; // ¡CONSTANTE AÑADIDA!
    public final int STATE_P1_MENU = 7; // Menú principal (Fight, Pkmn, Item, Run)
    public final int STATE_P1_MOVE = 8; // Menú de selección de movimiento
    public final int STATE_P1_SELECT_POKEMON = 9; // Menú de selección de Pk (T1)
    public final int STATE_P1_ITEM = 10; // Menú de selección de item
    public final int STATE_END = 14; // Fin de la batalla
    public final int STATE_PROCESS_RESULTS = 16; // ¡NUEVO ESTADO PRINCIPAL!
    // Los estados antiguos (1, 2, 11, 12, 13, 15, etc.) ya no se usan.

    // --- LÓGICA DE MENÚS ---
    public String currentMessage = "";
    public int menuCursorPos = 0;
    public int currentMenuSize = 0;
    private String[] currentOptions; // Opciones de menú dinámicas

    public BattlePanel(Game game) {
        this.game = game;

        // 1. Obtener los trainers
        this.trainer1 = game.getgTrainer1();
        this.trainer2 = game.getgTrainer2();

        // 2. Configurar el Panel
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        // 3. Cargar Recursos
        this.background = selectBattleBackground();
        this.loadPokeballSprite();

        // 4. Inicializar estado de batalla
        // Asumimos que los Pokémon ya fueron seleccionados en pkSelectorPanel
        // y están en la lista de cada Trainer.
        this.trainer1ActivePokemon = game.getgTrainer1().getActivePokemon();
        this.trainer2ActivePokemon = game.getgTrainer2().getActivePokemon();

        // Asigna las posiciones gráficas INICIALES
        if (this.trainer1ActivePokemon != null)
            this.trainer1ActivePokemon.setDefaultBattlePosition(90, 158); // POKEMON1
        if (this.trainer2ActivePokemon != null)
            this.trainer2ActivePokemon.setDefaultBattlePosition(475, 28); // POKEMON2

        this.battleState = STATE_START; // Empezamos en la pantalla de "START"
        this.currentMessage = "¡Una batalla va a comenzar! Presiona [ENTER]";
        this.menuCursorPos = 0;
        this.currentMenuSize = 0;
        this.currentOptions = new String[0]; // Inicializa vacío
        
        // 5. Inicializar gráficos de trainers
        initTrainers();
    }

    private void initTrainers() {
        this.trainer1.initGraphics(this, keyH, trainer1.num, true);
        this.trainer2.initGraphics(this, keyH, trainer2.num, false);
        trainer1.setDefaultValues(true);
        trainer2.setDefaultValues(false);
        trainer1.loadMoveSpritesSafe(trainer1.num, true);
        trainer2.loadMoveSpritesSafe(trainer2.num, false);
    }

    private void loadPokeballSprite() {
        try {
            pokeballSprite = ImageIO.read(getClass().getResourceAsStream("/util/pokeball.png"));
        } catch (IOException e) {
            System.out.println("Error cargando sprite de pokeball");
            pokeballSprite = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        }
    }

    public void startGameThread() {
        // Inicia el hilo del juego.
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null && !Thread.currentThread().isInterrupted()) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    /**
     * El bucle principal de actualización del panel de batalla.
     */
    public void update() {
        // 1. Siempre actualiza las animaciones (Pokémon, Trainers)
        trainer1.update();
        trainer2.update();
        if (trainer1ActivePokemon != null) trainer1ActivePokemon.updateAnimation();
        if (trainer2ActivePokemon != null) trainer2ActivePokemon.updateAnimation();

        // 2. Control de Animación: Si una animación se está procesando, no hagas nada más.
        if (isProcessingAnimation) {
            boolean t1_anim = (trainer1ActivePokemon != null) && trainer1ActivePokemon.isAnimating();
            boolean t2_anim = (trainer2ActivePokemon != null) && trainer2ActivePokemon.isAnimating();
            // isThrowComplete() debe volver a false cuando la animación de "throw" termina
            boolean trainer_anim = !trainer1.isThrowComplete() || !trainer2.isThrowComplete();

            if (!t1_anim && !t2_anim && !trainer_anim) {
                isProcessingAnimation = false; // ¡Animación terminada!
            }
            return; // No continúes al switch de estados
        }

        // 3. Salir (temporal)
        if (keyH.escPressed) {
            stopBattle();
            keyH.escPressed = false;
            return;
        }

        // 4. Lógica de Estados (El Director)
        switch (battleState) {
            
            case STATE_START:
                // Espera a que el jugador presione Enter para empezar
                if (keyH.enterPressed) {
                    keyH.enterPressed = false;
                    // Llama a la lógica de la batalla para inicializarse
                    this.game.startBattle(); 
                    // Añade los mensajes iniciales (ej. "BATTLE_START") a la cola
                    this.pendingResultsQueue.addAll(game.getgTurnResults()); // ¡USA EL GETTER!
                    
                    // Ve al estado de animación de lanzamiento
                    battleState = STATE_P1_SEND_ANIM; 
                }
                break;

            // ESTADOS DE ANIMACIÓN DE ENTRADA
            case STATE_P1_SEND_ANIM:
                trainer1.startThrowAnimation(true); // Inicia la animación
                battleState = STATE_P1_SEND_ANIM_WAIT; // Pasa a un estado de espera
                isProcessingAnimation = true;
                break;
                
            case STATE_P1_SEND_ANIM_WAIT: // Estado de espera
                if (trainer1.isThrowComplete()) {
                    isProcessingAnimation = false;
                    battleState = STATE_P2_SEND_ANIM; // Pasa al T2
                }
                break;

            case STATE_P2_SEND_ANIM:
                trainer2.startThrowAnimation(false); // Inicia la animación
                battleState = STATE_P2_SEND_ANIM_WAIT; // Pasa a un estado de espera
                isProcessingAnimation = true;
                break;

            case STATE_P2_SEND_ANIM_WAIT: // Estado de espera
                if (trainer2.isThrowComplete()) {
                    isProcessingAnimation = false;
                    battleStarted = true; // ¡Ahora se pueden dibujar los Pokémon y las barras!
                    battleState = STATE_PROCESS_RESULTS; // Ve a procesar la cola (ej. "¡La batalla comienza!")
                }
                break;
                
            // ESTADO PRINCIPAL: Turno del Jugador
            case STATE_P1_MENU:
                handleMenuNavigation();
                if (keyH.enterPressed) {
                    keyH.enterPressed = false;
                    switch (menuCursorPos) {
                        case 0: // FIGHT
                            battleState = STATE_P1_MOVE;
                            currentMessage = "Elige un ataque:";
                            currentOptions = trainer1.getPokemonMoveNames(); // Pide los nombres de los movimientos
                            currentMenuSize = currentOptions.length;
                            menuCursorPos = 0;
                            break;
                        case 1: // POKEMON
                            battleState = STATE_P1_SELECT_POKEMON;
                            currentMessage = "Elige un Pokémon:";
                            currentOptions = trainer1.getPokemonNames(); // Pide los nombres del equipo
                            currentMenuSize = currentOptions.length;
                            menuCursorPos = 0;
                            break;
                        // ... Casos para ITEM y RUN ...
                    }
                }
                break;

            // ESTADO: Jugador eligió un ataque
            case STATE_P1_MOVE:
                handleMenuNavigation();
                if (keyH.enterPressed) {
                    keyH.enterPressed = false;
                    
                    // 1. Crear la Acción del Jugador
                    Move selectedMove = trainer1ActivePokemon.getPkMove(menuCursorPos);
                    TurnAction playerAction = new MoveAction(trainer1, trainer1ActivePokemon, trainer2ActivePokemon, selectedMove);
                    
                    // 2. Ejecutar la Lógica del Turno (¡El Cerebro!)
                    ArrayList<TurnResult> turnResults = game.executeTurn(playerAction);
                    
                    // 3. Añadir resultados a la cola
                    pendingResultsQueue.addAll(turnResults);
                    
                    // 4. Cambiar al estado que procesa la cola
                    currentOptions = new String[0]; // Limpia las opciones del menú
                    battleState = STATE_PROCESS_RESULTS;
                }
                break;

            // ESTADO: Jugador eligió cambiar de Pokémon
            case STATE_P1_SELECT_POKEMON:
                handleMenuNavigation();
                if (keyH.enterPressed) {
                    keyH.enterPressed = false;
                    Pokemon selectedPokemon = trainer1.getPhPokemon(menuCursorPos);
                    
                    // Validaciones
                    if (selectedPokemon == trainer1ActivePokemon) {
                        currentMessage = "¡Ese Pokémon ya está en batalla!";
                        battleState = STATE_P1_MENU; // Vuelve al menú
                        currentOptions = battleOptions;
                        currentMenuSize = currentOptions.length;
                    } else if (selectedPokemon.getPkHp() <= 0) {
                        currentMessage = "¡Ese Pokémon no puede luchar!";
                    } else {
                        // 1. Crear la Acción de Cambio
                        TurnAction playerAction = new SwitchAction(trainer1, selectedPokemon);
                        
                        // 2. Ejecutar la Lógica del Turno
                        ArrayList<TurnResult> turnResults = game.executeTurn(playerAction);
                        
                        // 3. Añadir resultados a la cola
                        pendingResultsQueue.addAll(turnResults);
                        
                        // 4. Cambiar al estado que procesa la cola
                        currentOptions = new String[0]; // Limpia las opciones del menú
                        battleState = STATE_PROCESS_RESULTS;
                    }
                }
                break;
                
            // ESTADO: Procesando la cola de resultados
            case STATE_PROCESS_RESULTS:
                if (pendingResultsQueue.isEmpty()) {
                    // No hay más resultados, vuelve al turno del jugador
                    
                    // Actualiza las referencias en caso de que hayan cambiado (ej. por un faint)
                    trainer1ActivePokemon = game.getgTrainer1().getActivePokemon();
                    trainer2ActivePokemon = game.getgTrainer2().getActivePokemon();

                    if (trainer1ActivePokemon == null || trainer2ActivePokemon == null) {
                         // Alguien se quedó sin Pokémon
                        battleState = STATE_END; 
                    } else {
                        // La batalla continúa
                        battleState = STATE_P1_MENU;
                        currentMessage = "¿Qué hará " + trainer1ActivePokemon.getPkNickName() + "?";
                        currentOptions = battleOptions; // Opciones del menú principal
                        currentMenuSize = currentOptions.length;
                        menuCursorPos = 0;
                    }
                } else {
                    // Procesa el siguiente resultado en la cola
                    TurnResult result = pendingResultsQueue.poll();
                    processSingleResult(result);
                }
                break;
                
            case STATE_END:
                // La batalla terminó. Espera un Enter para salir.
                if (keyH.enterPressed) {
                    stopBattle();
                }
                break;
        }
    }
    
    /**
     * Procesa un único TurnResult de la cola.
     * Muestra mensajes y dispara animaciones.
     */
    private void processSingleResult(TurnResult result) {
        String key = result.getMessageKey();
        this.currentMessage = key; // Mensaje por defecto

        // Asigna los Pokémon relevantes desde el TurnResult
        Pokemon attacker = result.getAttacker();
        Pokemon defender = result.getDefender();

        switch (key) {
            case "BATTLE_START":
                this.currentMessage = result.getMessage(); // "¡La batalla comienza!"
                break;
                
            case "MOVE_HIT_DAMAGE":
            case "MOVE_USED":
                this.currentMessage = result.getMessage();
                if (attacker != null) attacker.startAnimation("attack");
                if (defender != null) defender.startAnimation("damage");
                isProcessingAnimation = true; 
                break;

            case "MOVE_MISSED":
                this.currentMessage = result.getMessage(); // "¡... falló!"
                break;
                
            case "POKEMON_HURT_BY_POISON":
            case "POKEMON_HURT_BY_BURN":
            case "POKEMON_HURT_BY_SANDSTORM":
            case "POKEMON_HURT_ITSELF_IN_CONFUSION":
                this.currentMessage = result.getMessage();
                if (defender != null) defender.startAnimation("damage"); // 'defender' es el "target" del TurnResult
                isProcessingAnimation = true;
                break;
            
            case "POKEMON_IS_ASLEEP":
            case "POKEMON_IS_PARALYZED":
            case "POKEMON_IS_FROZEN":
            case "POKEMON_FLINCHED":
                this.currentMessage = result.getMessage();
                break;
                
            case "POKEMON_FAINTED":
                this.currentMessage = result.getMessage(); // "¡... se debilitó!"
                if (defender != null) {
                    // defender.startAnimation("faint"); // <-- Necesitarás crear esta animación
                }
                isProcessingAnimation = true; // Pausa para leer el mensaje
                break;

            case "TRAINER_SWITCHED_POKEMON":
                this.currentMessage = result.getMessage(); // "¡... saca a ...!"
                
                Pokemon newPokemon =result.getDefender(); // El Pokémon que entra
                Trainer trainer = newPokemon.getTrainer();
                
                // Actualiza las referencias ACTIVAS
                if (trainer == trainer1) {
                    this.trainer1ActivePokemon = newPokemon;
                    trainer1.startThrowAnimation(true);
                } else {
                    this.trainer2ActivePokemon = newPokemon;
                    trainer2.startThrowAnimation(false);
                }
                isProcessingAnimation = true; // Espera a que termine la animación de lanzamiento
                break;

            case "TARGET_STAT_FELL":
            case "ATTACKER_STAT_ROSE":
            case "WEATHER_BECAME_RAINY": // etc.
                this.currentMessage = result.getMessage();
                break;

            case "BATTLE_WON_PLAYER_1":
            case "BATTLE_WON_PLAYER_2":
                this.currentMessage = result.getMessage();
                battleState = STATE_END; // Cambia al estado final
                break;

            default:
                // Mensaje genérico para cualquier clave no reconocida
                this.currentMessage = key; 
                break;
        }
        repaint(); // Vuelve a dibujar la pantalla
    }

    /**
     * Maneja el movimiento del cursor en los menús
     */
    private void handleMenuNavigation() {
        if (keyH.upPressed) {
            menuCursorPos--;
            if (menuCursorPos < 0) {
                menuCursorPos = currentMenuSize - 1;
            }
            keyH.upPressed = false;
        }
        if (keyH.downPressed) {
            menuCursorPos++;
            if (menuCursorPos >= currentMenuSize) {
                menuCursorPos = 0;
            }
            keyH.downPressed = false;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // --- Preparar datos dinámicos ---
        String message = this.currentMessage;
        int cursor = this.menuCursorPos;

        // Determinar turno actual (solo para el color del cursor)
        boolean isPlayer1Turn = (battleState == STATE_P1_MENU ||
                                 battleState == STATE_P1_MOVE ||
                                 battleState == STATE_P1_SELECT_POKEMON ||
                                 battleState == STATE_P1_ITEM);

        // --- DIBUJAR TODO ---
        ui.drawBattleLayout(g2, screenWidth, screenHeight, background);
        ui.drawMessage(g2, message);
        
        // Solo dibuja las opciones si estamos en un estado de menú
        if (battleState == STATE_P1_MENU || battleState == STATE_P1_MOVE || battleState == STATE_P1_SELECT_POKEMON) {
            ui.drawBattleOptions(g2, currentOptions, cursor, isPlayer1Turn); 
        }

        /// Dibujar trainers
        if (!trainer1.isFullyExited(true)) {
            trainer1.draw(g2);
        }
        if (!trainer2.isFullyExited(false)) {
            trainer2.draw(g2);
        }

        // Dibujar pokeballs si están en animación
        if (isProcessingAnimation) {
            drawThrowAnimation(g2);
        }

        // Dibujar Pokémon activos (solo después de que hayan sido enviados)
        if (battleStarted) { 
            drawPokemon(g2);
        }

        // Dibujar barras de HP (¡Ahora leen el HP real!)
        if (battleStarted) {
            try {
                ui.draw(g2); // Asegúrate que ui.draw() lea de trainer1ActivePokemon y trainer2ActivePokemon
            } catch (IOException ex) {
                Logger.getLogger(BattlePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        g2.dispose();
    }
    
    private void drawThrowAnimation(Graphics2D g2) {
        // Dibuja la animación de lanzamiento si el trainer está en ese estado
        if (!trainer1.isThrowComplete()) {
            Point ballPos = trainer1.getBallPosition(true);
            int ballSize = trainer1.getBallSize();
            g2.drawImage(pokeballSprite, ballPos.x, ballPos.y, ballSize, ballSize, null);
        }

        if (!trainer2.isThrowComplete()) {
            Point ballPos = trainer2.getBallPosition(false);
            int ballSize = trainer2.getBallSize();
            g2.drawImage(pokeballSprite, ballPos.x, ballPos.y, ballSize, ballSize, null);
        }
    }
    
    private void drawPokemon(Graphics2D g2) {
        // Dibuja los Pokémon si existen
        if (trainer1ActivePokemon != null) {
             trainer1ActivePokemon.draw(g2, true); // true = es T1 (usa aback)
        }
        if (trainer2ActivePokemon != null) {
            trainer2ActivePokemon.draw(g2, false); // false = es T2 (usa afront)
        }
    }
    

    private BufferedImage selectBattleBackground() {
        int x = random.nextInt(9) + 1;
        String num = Integer.toString(x);
        try {
            return ImageIO.read(getClass().getResourceAsStream("/btScreen/" + num + ".png"));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Error cargando fondo de batalla");
            // Devuelve un fondo de emergencia
            BufferedImage errorBg = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
            Graphics g = errorBg.getGraphics();
            g.setColor(Color.BLUE);
            g.fillRect(0, 0, screenWidth, screenHeight);
            return errorBg;
        }
    }

    public void stopBattle() { 
        if (gameThread != null) {
            gameThread.interrupt();
            gameThread = null;
        }
        this.removeKeyListener(keyH);
        System.out.println("BattlePanel detenido, cambiando a NurseJoyPanel...");

        // Cambiar al panel de la Enfermera Joy
        GraphicPart.cambiarPanel(GraphicPart.STATE_NURSE_JOY);
    }
}