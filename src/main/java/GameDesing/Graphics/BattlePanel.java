package GameDesing.Graphics;

import GameDesing.BattleSystem.Actions.MoveAction;
import GameDesing.BattleSystem.Actions.SwitchAction;
import GameDesing.BattleSystem.Actions.TurnAction;
import GameDesing.BattleSystem.TurnResult;
import GameDesing.Game;
import Persons.Trainer;
import Pokemons.Logic.Items.Item; // Asegúrate de importar la clase Item correcta
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
    public boolean battleStarted = false; // ¡IMPORTANTE! Controla si se dibujan los Pokémon y HP

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
    // ¡ESTADOS REORGANIZADOS PARA EL FLUJO CORRECTO!
    public int battleState;
    public final int STATE_P1_SELECT_POKEMON = 1; // T1 elige Pokémon
    public final int STATE_P2_SELECT_POKEMON = 2; // T2 elige Pokémon
    public final int STATE_P1_SEND_ANIM = 3;      // T1 anima lanzamiento
    public final int STATE_P1_SEND_ANIM_WAIT = 4; // T1 espera que termine la anim
    public final int STATE_P2_SEND_ANIM = 5;      // T2 anima lanzamiento
    public final int STATE_P2_SEND_ANIM_WAIT = 6; // T2 espera que termine la anim
    public final int STATE_PROCESS_RESULTS = 7;   // Procesador de cola de lógica
    public final int STATE_P1_MENU = 8;           // Menú principal (Fight, Pkmn...)
    public final int STATE_P1_MOVE = 9;           // Menú de selección de movimiento
    public final int STATE_P1_ITEM = 10;          // Menú de items
    public final int STATE_P1_SWITCH_POKEMON = 11; // Menú de cambio (en batalla)
    public final int STATE_END = 14;              // Fin de la batalla

    // --- LÓGICA DE MENÚS ---
    public String currentMessage = "";
    public int menuCursorPos = 0;
    public int currentMenuSize = 0;
    private String[] currentOptions; // Opciones de menú dinámicas

    public BattlePanel(Game game) {
        this.game = game;
        this.trainer1 = game.getgTrainer1();
        this.trainer2 = game.getgTrainer2();

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        this.background = selectBattleBackground();
        this.loadPokeballSprite();

        // NO asignamos Pokémon activos todavía
        this.trainer1ActivePokemon = null; 
        this.trainer2ActivePokemon = null;

        // --- ESTADO INICIAL ---
        // Empezamos pidiendo al T1 que elija su Pokémon
        this.battleState = STATE_P1_SELECT_POKEMON; 
        this.currentMessage = "¡" + trainer1.getpName() + ", elige tu Pokémon!";
        this.currentOptions = trainer1.getPokemonNames(); // Muestra la lista de Pokémon de T1
        this.currentMenuSize = currentOptions.length;
        this.menuCursorPos = 0;
        
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
        } catch (IOException | NullPointerException e) {
            System.out.println("Error cargando sprite de pokeball");
            pokeballSprite = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        }
    }

    public void startGameThread() {
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
        // 1. Actualiza animaciones SÓLO si es necesario
        
        // Los trainers solo se actualizan (animan) si estamos en una fase de animación
        // o en el bucle principal de la batalla (donde están en reposo).
        if (battleState != STATE_P1_SELECT_POKEMON && 
            battleState != STATE_P2_SELECT_POKEMON) 
        {
            // ¡Llamamos al update() del Trainer que SÍ actualiza la animación!
            trainer1.update(); 
            trainer2.update();
        }

        // Los Pokémon solo se actualizan si están en pantalla
        if (battleStarted) {
            if (trainer1ActivePokemon != null) trainer1ActivePokemon.updateAnimation();
            if (trainer2ActivePokemon != null) trainer2ActivePokemon.updateAnimation();
        }

        // 2. Control de Animación
        if (isProcessingAnimation) {
            boolean t1_pk_anim = (trainer1ActivePokemon != null) && trainer1ActivePokemon.isAnimating();
            boolean t2_pk_anim = (trainer2ActivePokemon != null) && trainer2ActivePokemon.isAnimating();
            // isThrowComplete() viene de Trainer.java y se pone en true (2) cuando la anim. de lanzar termina
            boolean t1_throw_anim = (trainer1.trainerState == 1); // 1 = STATE_THROWING
            boolean t2_throw_anim = (trainer2.trainerState == 1); // 1 = STATE_THROWING

            // Si ninguna animación está en curso, desbloquea la lógica
            if (!t1_pk_anim && !t2_pk_anim && !t1_throw_anim && !t2_throw_anim) {
                isProcessingAnimation = false; 
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
            
            case STATE_P1_SELECT_POKEMON:
                handleMenuNavigation();
                if (keyH.enterPressed) {
                    keyH.enterPressed = false;
                    this.trainer1ActivePokemon = trainer1.getPhPokemon(menuCursorPos);
                    this.trainer1ActivePokemon.setDefaultBattlePosition(90, 158); 
                    
                    battleState = STATE_P2_SELECT_POKEMON;
                    currentMessage = "¡" + trainer2.getpName() + ", elige tu Pokémon!";
                    currentOptions = trainer2.getPokemonNames();
                    currentMenuSize = currentOptions.length;
                    menuCursorPos = 0;
                }
                break;

            case STATE_P2_SELECT_POKEMON:
                handleMenuNavigation();
                if (keyH.enterPressed) {
                    keyH.enterPressed = false;
                    this.trainer2ActivePokemon = trainer2.getPhPokemon(menuCursorPos);
                    this.trainer2ActivePokemon.setDefaultBattlePosition(475, 28);
                    
                    battleState = STATE_P1_SEND_ANIM;
                    currentMessage = "¡" + trainer1.getpName() + " saca a " + trainer1ActivePokemon.getPkNickName() + "!";
                    currentOptions = new String[0]; // Limpia el menú
                }
                break;
                
            case STATE_P1_SEND_ANIM:
                trainer1.startThrowAnimation(true); 
                battleState = STATE_P1_SEND_ANIM_WAIT; 
                isProcessingAnimation = true; // Bloquea el update
                break;
                
            case STATE_P1_SEND_ANIM_WAIT: 
                // isProcessingAnimation se volverá false cuando la anim termine (ver sección 2)
                if (!isProcessingAnimation) { 
                    trainer1.resetState(); 
                    battleState = STATE_P2_SEND_ANIM; 
                    currentMessage = "¡" + trainer2.getpName() + " saca a " + trainer2ActivePokemon.getPkNickName() + "!";
                }
                break;

            case STATE_P2_SEND_ANIM:
                trainer2.startThrowAnimation(false); 
                battleState = STATE_P2_SEND_ANIM_WAIT; 
                isProcessingAnimation = true; // Bloquea el update
                break;

            case STATE_P2_SEND_ANIM_WAIT: 
                if (!isProcessingAnimation) { // Cuando la anim acabe...
                    trainer2.resetState(); 
                    
                    battleStarted = true; // ¡AHORA SÍ se dibujan Pokémon y HP!
                    
                    this.game.startBattle(); 
                    this.pendingResultsQueue.addAll(game.getgTurnResults()); 
                    
                    battleState = STATE_PROCESS_RESULTS; // Ve a procesar la cola
                }
                break;
            
            // ESTADO: Procesando la cola de resultados lógicos
            case STATE_PROCESS_RESULTS:
                if (pendingResultsQueue.isEmpty()) {
                    // No hay más resultados, vuelve al turno del jugador
                    trainer1ActivePokemon = game.getgTrainer1().getActivePokemon();
                    trainer2ActivePokemon = game.getgTrainer2().getActivePokemon();

                    if (trainer1ActivePokemon == null || trainer2ActivePokemon == null) {
                        battleState = STATE_END; 
                    } else {
                        battleState = STATE_P1_MENU;
                        currentMessage = "¿Qué hará " + trainer1ActivePokemon.getPkNickName() + "?";
                        currentOptions = battleOptions; 
                        currentMenuSize = currentOptions.length;
                        menuCursorPos = 0;
                    }
                } else {
                    TurnResult result = pendingResultsQueue.poll();
                    processSingleResult(result);
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
                            currentOptions = trainer1.getPokemonMoveNames(); 
                            currentMenuSize = currentOptions.length;
                            menuCursorPos = 0;
                            break;
                        case 1: // POKEMON
                            battleState = STATE_P1_SWITCH_POKEMON;
                            currentMessage = "Elige un Pokémon:";
                            currentOptions = trainer1.getPokemonNames(); 
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
                    
                    Move selectedMove = trainer1ActivePokemon.getPkMove(menuCursorPos);
                    TurnAction playerAction = new MoveAction(trainer1, trainer1ActivePokemon, trainer2ActivePokemon, selectedMove);
                    
                    ArrayList<TurnResult> turnResults = game.executeTurn(playerAction);
                    pendingResultsQueue.addAll(turnResults);
                    
                    currentOptions = new String[0]; 
                    battleState = STATE_PROCESS_RESULTS;
                }
                break;

            // ESTADO: Jugador eligió cambiar de Pokémon
            case STATE_P1_SWITCH_POKEMON:
                handleMenuNavigation();
                if (keyH.enterPressed) {
                    keyH.enterPressed = false;
                    Pokemon selectedPokemon = trainer1.getPhPokemon(menuCursorPos);
                    
                    if (selectedPokemon == trainer1ActivePokemon) {
                        currentMessage = "¡Ese Pokémon ya está en batalla!";
                        battleState = STATE_P1_MENU; 
                        currentOptions = battleOptions;
                        currentMenuSize = currentOptions.length;
                    } else if (selectedPokemon.getPkHp() <= 0) {
                        currentMessage = "¡Ese Pokémon no puede luchar!";
                    } else {
                        TurnAction playerAction = new SwitchAction(trainer1, selectedPokemon);
                        ArrayList<TurnResult> turnResults = game.executeTurn(playerAction);
                        pendingResultsQueue.addAll(turnResults);
                        
                        currentOptions = new String[0];
                        battleState = STATE_PROCESS_RESULTS;
                    }
                }
                break;
                
            case STATE_END:
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
        this.currentMessage = key; 

        Pokemon attacker = result.getAttacker();
        Pokemon defender = result.getDefender(); 

        if (result.getMessage() != null && !result.getMessage().isEmpty()) {
            this.currentMessage = result.getMessage();
        } else {
             this.currentMessage = key; // Fallback
        }

        switch (key) {
            case "BATTLE_START":
                break;
                
            case "MOVE_HIT_DAMAGE":
            case "MOVE_USED":
                if (attacker != null) attacker.startAnimation("attack");
                if (defender != null) defender.startAnimation("damage");
                isProcessingAnimation = true; 
                break;

            case "POKEMON_HURT_BY_POISON":
            case "POKEMON_HURT_BY_BURN":
            case "POKEMON_HURT_BY_SANDSTORM":
            case "POKEMON_HURT_ITSELF_IN_CONFUSION":
                if (defender != null) defender.startAnimation("damage"); 
                isProcessingAnimation = true;
                break;
                
            case "POKEMON_FAINTED":
                if (defender != null) {
                    // defender.startAnimation("faint"); 
                }
                isProcessingAnimation = true; 
                break;

            case "TRAINER_SWITCHED_POKEMON":
                Pokemon newPokemon = result.getDefender(); // getDefender() tiene el Pokémon que entra
                
                if (newPokemon == null) return;

                if (newPokemon.getTrainer() == trainer1) {
                    this.trainer1ActivePokemon = newPokemon;
                    this.trainer1ActivePokemon.setDefaultBattlePosition(90, 158); 
                    trainer1.startThrowAnimation(true);
                } else {
                    this.trainer2ActivePokemon = newPokemon;
                    this.trainer2ActivePokemon.setDefaultBattlePosition(475, 28); 
                    trainer2.startThrowAnimation(false);
                }
                isProcessingAnimation = true; 
                break;

            case "BATTLE_WON_PLAYER_1":
            case "BATTLE_WON_PLAYER_2":
                battleState = STATE_END; 
                break;

            default:
                isProcessingAnimation = false; 
                break;
        }
        repaint(); 
    }

    private void handleMenuNavigation() {
        if (keyH.upPressed) {
            menuCursorPos--;
            if (menuCursorPos < 0) menuCursorPos = currentMenuSize - 1;
            keyH.upPressed = false;
        }
        if (keyH.downPressed) {
            menuCursorPos++;
            if (menuCursorPos >= currentMenuSize) menuCursorPos = 0;
            keyH.downPressed = false;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        String message = this.currentMessage;
        int cursor = this.menuCursorPos;

        boolean isPlayer1Turn = (battleState == STATE_P1_MENU ||
                                 battleState == STATE_P1_MOVE ||
                                 battleState == STATE_P1_SWITCH_POKEMON ||
                                 battleState == STATE_P1_ITEM);

        // --- DIBUJAR TODO ---
        ui.drawBattleLayout(g2, screenWidth, screenHeight, background);
        ui.drawMessage(g2, message);
        
        // --- ¡LÓGICA DE DIBUJO DE MENÚ CORREGIDA! ---
        if (battleState == STATE_P1_MENU || 
            battleState == STATE_P1_MOVE || 
            battleState == STATE_P1_SWITCH_POKEMON || 
            battleState == STATE_P1_ITEM || 
            battleState == STATE_P1_SELECT_POKEMON || // ¡AÑADIDO!
            battleState == STATE_P2_SELECT_POKEMON) // ¡AÑADIDO!
        {
            ui.drawBattleOptions(g2, currentOptions, cursor, isPlayer1Turn); 
        }

        // --- ¡LÓGICA DE DIBUJO DE TRAINER CORREGIDA! ---
        if (battleState == STATE_P1_SELECT_POKEMON || 
            battleState == STATE_P2_SELECT_POKEMON || 
            (battleStarted && trainer1.trainerState != 1)) // 1 = STATE_THROWING
        {
            // Dibuja estático si estamos seleccionando O si la batalla ha empezado y NO estamos lanzando
            trainer1.static_draw(g2, 200); // 200 es battleSpriteSize
        } else if (trainer1.trainerState == 1) { // 1 = STATE_THROWING
            // Dibuja la animación de lanzamiento SOLO si está lanzando
            if (!trainer1.isFullyExited(true)) trainer1.draw(g2);
        }
        
        if (battleState == STATE_P1_SELECT_POKEMON || 
            battleState == STATE_P2_SELECT_POKEMON || 
            (battleStarted && trainer2.trainerState != 1))
        {
            trainer2.static_draw(g2, 200);
        } else if (trainer2.trainerState == 1) { // 1 = STATE_THROWING
             if (!trainer2.isFullyExited(false)) trainer2.draw(g2);
        }
        // --- FIN DE LÓGICA DE DIBUJO DE TRAINER ---


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
                ui.draw(g2); // Llama a los métodos drawEnemyUI y drawPlayerUI
            } catch (IOException ex) {
                Logger.getLogger(BattlePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        g2.dispose();
    }
    
    private void drawThrowAnimation(Graphics2D g2) {
        if (trainer1.trainerState == 1) { // 1 = STATE_THROWING
            Point ballPos = trainer1.getBallPosition(true);
            int ballSize = trainer1.getBallSize();
            g2.drawImage(pokeballSprite, ballPos.x, ballPos.y, ballSize, ballSize, null);
        }

        if (trainer2.trainerState == 1) { // 1 = STATE_THROWING
            Point ballPos = trainer2.getBallPosition(false);
            int ballSize = trainer2.getBallSize();
            g2.drawImage(pokeballSprite, ballPos.x, ballPos.y, ballSize, ballSize, null);
        }
    }
    
    private void drawPokemon(Graphics2D g2) {
        if (trainer1ActivePokemon != null) {
            this.trainer1ActivePokemon.setDefaultBattlePosition(90, 158); 
            trainer1ActivePokemon.draw(g2, true); 
        }
        if (trainer2ActivePokemon != null) {
            this.trainer2ActivePokemon.setDefaultBattlePosition(475, 28); 
            trainer2ActivePokemon.draw(g2, false); 
        }
    }
    

    private BufferedImage selectBattleBackground() {
        int x = random.nextInt(9) + 1;
        String num = Integer.toString(x);
        try {
            return ImageIO.read(getClass().getResourceAsStream("/btScreen/" + num + ".png"));
        } catch (IOException | IllegalArgumentException | NullPointerException e) {
            System.out.println("Error cargando fondo de batalla: " + e.getMessage());
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
        GraphicPart.cambiarPanel(GraphicPart.STATE_NURSE_JOY);
    }
}