package GameDesing.Graphics;

import GameDesing.BattleSystem.Actions.MoveAction;
import GameDesing.BattleSystem.Actions.TurnAction;
import GameDesing.BattleSystem.TurnResult;
import GameDesing.Game;
import Persons.Trainer;
import Pokemons.Logic.Movements.Move;
import Pokemons.Pokemon;
import java.util.Random;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BattlePanel extends JPanel implements Runnable {

    // SCREEN CONFIG
    final int originalTileSize = 32;
    final int scale = 2;
    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 12;
    final int maxScreenRow = 9;
    private final int screenWidth = tileSize * maxScreenCol;
    private final int screenHeight = tileSize * maxScreenRow;
    private java.util.Queue<TurnResult> pendingResultsQueue = new java.util.LinkedList<>();
    private boolean isProcessingAnimation = false;
    
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

    // --- BATTLE STATE MACHINE ---
    public int battleState;
    public final int STATE_START=0;
    public final int STATE_P1_SELECT_POKEMON=1;
    public final int STATE_P2_SELECT_POKEMON=2;
    public final int STATE_P1_SEND_ANIM=3;
    public final int STATE_P2_SEND_ANIM=4;
    public final int STATE_P1_TURN=5;
    public final int STATE_P1_MENU=6;
    public final int STATE_P1_MOVE=7;
    public final int STATE_P2_TURN=8;
    public final int STATE_P2_MENU=9;
    public final int STATE_P2_MOVE=10;
    public final int STATE_PERFORM_MOVE=11;
    public final int STATE_ANNOUNCE=12;
    public final int STATE_FAINTED=13;
    public final int STATE_END=14;
    public final int STATE_ANIMATION=15;
    public final int STATE_PROCESS_RESULTS = 16;

    // --- LÓGICA DE MENÚS ---
    public String currentMessage = "";
    public int menuCursorPos = 0;
    public int currentMenuSize = 0;

    public BattlePanel(Game game) {
        this.game = game;
        
        // 1. Obtener los trainers
        this.trainer1 = game.getgTrainer1();
        this.trainer2 = game.getgTrainer2();
        System.out.println("OBTENIDOS");
        // 2. Configurar el Panel
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        System.out.println("CONFIGURADO PANEL");

        // 3. Cargar Recursos
        this.background = selectBattleBackground();
        this.loadPokeballSprite();
        this.trainer1ActivePokemon = null; 
        this.trainer2ActivePokemon = null;
        this.battleState = STATE_START;
        this.currentMessage = "¡The battle must start, FIGHT!";
        this.menuCursorPos = 0;
        this.currentMenuSize = 0;
        System.out.println("RECURSOS CARGADOS");

        //inicializa trainer
        initTrainers();
    }

    private void initTrainers() {
        // inicializar graficos con sprites
        this.trainer1.initGraphics(this, keyH, trainer1.num, true); 
        this.trainer2.initGraphics(this, keyH, trainer2.num, false); 

        // posiciones iniciales
        trainer1.setDefaultValues(true);
        trainer2.setDefaultValues(false);
        
        // cargar sprites
        trainer1.loadMoveSpritesSafe(trainer1.num, true);
        trainer2.loadMoveSpritesSafe(trainer2.num, false);
    }

    private void loadPokeballSprite() {
        try {
            pokeballSprite = ImageIO.read(getClass().getResourceAsStream("/util/pokeball.png"));
        } catch (IOException e) {
            System.out.println("Error cargando sprite de pokeball");
            // por si las moscas
            pokeballSprite = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        }
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
        this.game.startBattle(); // ¡Llama al nuevo método!
        this.trainer1ActivePokemon = game.getgTrainer1().getActivePokemon(); // Asigna los Pokémon iniciales
        this.trainer2ActivePokemon = game.getgTrainer2().getActivePokemon();
        // ...
        // Asegúrate de que los Pokémon tengan sus posiciones gráficas
        this.trainer1ActivePokemon.setDefaultBattlePosition(90, 158);
        this.trainer2ActivePokemon.setDefaultBattlePosition(475, 28);

        this.battleState = STATE_START; // Tu estado inicial
        this.currentMessage = "¡La batalla va a comenzar!";
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

    public void update() {
        trainer1.update();
        trainer2.update();
        if (trainer1ActivePokemon != null) trainer1ActivePokemon.updateAnimation();
        if (trainer2ActivePokemon != null) trainer2ActivePokemon.updateAnimation();

        // ¡IMPORTANTE! Si una animación se está ejecutando, no hagas nada más.
        // Debes modificar tu `Pokemon.java -> updateAnimation()` para que 
        // ponga `isAnimating = false;` cuando la animación termine.
        if (trainer1ActivePokemon.isAnimating() || trainer2ActivePokemon.isAnimating() || trainer1.isThrowComplete() == false || trainer2.isThrowComplete() == false) {
             // (Aquí necesitas una lógica más robusta, pero esto es la idea)
             // `isProcessingAnimation` es tu propio flag.
             if(isProcessingAnimation && !trainer1ActivePokemon.isAnimating() && !trainer2ActivePokemon.isAnimating()){
                isProcessingAnimation = false; // La animación terminó, podemos procesar el siguiente resultado
             }
             return; // No proceses lógica de estados si hay animaciones
        }

        // Si no hay animaciones, procesa el siguiente resultado de la cola
        if (battleState == STATE_PROCESS_RESULTS) {
            if (pendingResultsQueue.isEmpty()) {
                // No hay más resultados, vuelve al turno del jugador

                // Revisa si la batalla terminó
                if (!trainer1.hastAlivePokemon() || !trainer2.hastAlivePokemon()) {
                    battleState = STATE_END;
                    currentMessage = trainer1.hastAlivePokemon() ? "¡Ganaste!" : "¡Perdiste!";
                } else {
                    battleState = STATE_P1_TURN; // O STATE_P2_TURN si es su turno
                    currentMessage = "¿Qué hará " + trainer1ActivePokemon.getPkNickName() + "?";
                }
            } else {
                // Procesa el siguiente resultado en la cola
                TurnResult result = pendingResultsQueue.poll();
                processSingleResult(result); // ¡El nuevo método mágico!
            }
            return; // Termina este frame de update
        }

        // Tu máquina de estados (switch) va aquí
        switch (battleState) {
            // ... (Tus estados STATE_START, STATE_P1_SELECT_POKEMON, etc. siguen igual)

            // ¡ESTE ES EL GRAN CAMBIO!
            case STATE_P1_MOVE:
                handleMenuNavigation();
                if (keyH.enterPressed) {
                    keyH.enterPressed = false;

                    // 1. Crear la acción del jugador
                    Move selectedMove = trainer1ActivePokemon.getPkMove(menuCursorPos);
                    TurnAction playerAction = new MoveAction(trainer1, trainer1ActivePokemon, trainer2ActivePokemon, selectedMove);

                    // 2. Ejecutar TODA la lógica del turno en el backend
                    ArrayList<TurnResult> turnResults = game.executeTurn(playerAction);

                    // 3. Añadir todos los resultados a la cola para procesarlos visualmente
                    pendingResultsQueue.addAll(turnResults);

                    // 4. Cambiar al estado que procesa la cola
                    battleState = STATE_PROCESS_RESULTS;
                }
                break;

             // ... (El resto de tus estados: STATE_P2_TURN, STATE_P2_MENU, STATE_P2_MOVE, etc.)
             // (STATE_P2_MOVE haría lo mismo, pero creando la acción para el Trainer 2)
        }
    }
   
    // En BattlePanel.java
    private void processSingleResult(TurnResult result) {
        // (Esta función crecerá mucho)
        String key = result.getMessageKey();
        this.currentMessage = key; // Mensaje por defecto

        // Identifica quién ataca y quién defiende (¡necesitarás mejorar TurnResult!)
        // Por ahora, asumimos que sabemos quiénes son:
        Pokemon attacker = trainer1ActivePokemon; // (Esto es una suposición, ¡debes mejorarlo!)
        Pokemon defender = trainer2ActivePokemon; // (Esto es una suposición, ¡debes mejorarlo!)

        if (key.equals("MOVE_HIT_DAMAGE")) {
            // ¡CONEXIÓN!
            this.currentMessage = attacker.getPkNickName() + " usó " + result.getMoveUsed().getMvName() + "!";
            attacker.startAnimation("attack"); // Inicia animación de ataque
            defender.startAnimation("damage"); // Inicia animación de daño
            isProcessingAnimation = true; // ¡IMPORTANTE!

        } else if (key.equals("MOVE_MISSED")) {
            this.currentMessage = "¡" + attacker.getPkNickName() + " falló!";

        } else if (key.equals("POKEMON_FAINTED")) {
            this.currentMessage = "¡El Pokémon se debilitó!";
            // (Aquí iniciarías la animación de desmayo)
            isProcessingAnimation = true; 

        } else if (key.equals("POKEMON_IS_ASLEEP")) {
            this.currentMessage = attacker.getPkNickName() + " está dormido.";

        } else if (key.equals("TARGET_STAT_FELL")) {
            this.currentMessage = "¡La estadística de " + defender.getPkNickName() + " bajó!";

        } else if (key.equals("ATTACKER_STAT_ROSE")) {
            this.currentMessage = "¡La estadística de " + attacker.getPkNickName() + " subió!";

        } else if (key.equals("POKEMON_HURT_BY_POISON")) {
             this.currentMessage = attacker.getPkNickName() + " fue herido por veneno.";
             attacker.startAnimation("damage");
             isProcessingAnimation = true;
        }

        // ... (añade 'else if' para todas las claves de TurnResult: "TARGET_WAS_BURNED", "POKEMON_IS_FROZEN", etc.)

        // Al final, actualiza las barras de HP
        // (Tu método ui.draw(g2) debería leer el HP de trainer1ActivePokemon.getPkHp())
        // ¡Ya no necesitas simular el daño, solo repintar!
        repaint();
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

    /**
     * Verifica si algún Pokémon se debilitó
     */
    private boolean checkFaintedPokemon() {
        if (trainer1ActivePokemon != null && trainer1ActivePokemon.getPkHp()<= 0) {
            battleState = STATE_FAINTED;
            currentMessage = "¡" + trainer1ActivePokemon.getPkNickName() + " se debilitó!";
            return true;
        }
        if (trainer2ActivePokemon != null && trainer2ActivePokemon.getPkHp() <= 0) {
            battleState = STATE_FAINTED;
            currentMessage = "¡" + trainer2ActivePokemon.getPkNickName() + " se debilitó!";
            return true;
        }
        return false;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // --- Preparar datos dinámicos ---
        String message = this.currentMessage;
        String[] options = null;
        int cursor = this.menuCursorPos;

        // Determinar turno actual
        boolean isPlayer1Turn = (battleState == STATE_P1_SELECT_POKEMON || 
                                battleState == STATE_P1_MENU || 
                                battleState == STATE_P1_MOVE);
        
        boolean isPlayer2Turn = (battleState == STATE_P2_SELECT_POKEMON || 
                                battleState == STATE_P2_MENU || 
                                battleState == STATE_P2_MOVE);

        // Configurar opciones del menú según estado
        switch (battleState) {
            case STATE_P1_SELECT_POKEMON:
                options = trainer1.getPokemonNames();
                break;
            case STATE_P2_SELECT_POKEMON:
                options = trainer2.getPokemonNames();
                break;
            case STATE_P1_MENU:
            case STATE_P2_MENU:
                options = new String[]{"FIGHT", "POKEMON", "ITEM", "RUN"};
                break;
            case STATE_P1_MOVE:
            case STATE_P2_MOVE:
                options = new String[]{"Ataque 1", "Ataque 2", "Ataque 3", "Ataque 4"};
                break;
        }

        // --- DIBUJAR TODO ---
        ui.drawBattleLayout(g2, screenWidth, screenHeight, background);
        ui.drawMessage(g2, message);
        ui.drawBattleOptions(g2, options, cursor,isPlayer1Turn);


        /// Dibujar trainers SOLO si no han salido completamente
        if (!trainer1.isFullyExited(true)) {
            trainer1.draw(g2); 
        }
        if (!trainer2.isFullyExited(false)) {
            trainer2.draw(g2);
        }

        // Dibujar pokeballs si están en animación
        if (battleState == STATE_P1_SEND_ANIM || battleState == STATE_P2_SEND_ANIM) {
            drawThrowAnimation(g2);
        }

        // Dibujar Pokémon activos
        // --- MODIFICADO: Llamar a drawPokemon ---
        if (battleState >= STATE_P1_TURN) {
            drawPokemon(g2);
        }
        
        // Dibujar barras de HP
        try {
            ui.draw(g2); 
        } catch (IOException ex) {
            Logger.getLogger(BattlePanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        g2.dispose();
    }
    
    private void drawThrowAnimation(Graphics2D g2) {
        // Dibujar pokeball del trainer 1 si está lanzando
        if (battleState == STATE_P1_SEND_ANIM && !trainer1.isFullyExited(true)) {
            Point ballPos = trainer1.getBallPosition(true);
            int ballSize = trainer1.getBallSize();

            // Efecto de brillo en la pokeball
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
            g2.drawImage(pokeballSprite, ballPos.x, ballPos.y, ballSize, ballSize, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        }

        // Pokeball del trainer 2  
        if (battleState == STATE_P2_SEND_ANIM && !trainer2.isFullyExited(false)) {
            Point ballPos = trainer2.getBallPosition(false);
            int ballSize = trainer2.getBallSize();

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
            g2.drawImage(pokeballSprite, ballPos.x, ballPos.y, ballSize, ballSize, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        }
    }
    
    private void drawPokemon(Graphics2D g2) {
        // Ahora llamamos a los métodos de dibujo de los propios Pokémon
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
            return null;
        }
    }

    public void stopBattle() { //TEMPORALLLLLL MODIFICAR 
        if (gameThread != null) {
            gameThread.interrupt();
            gameThread = null;
        }
        this.removeKeyListener(keyH);
        System.out.println("BattlePanel detenido, cambiando a NurseJoyPanel...");

        // Cambiar al panel de la Enfermera Joy
        GraphicPart.cambiarPanel(GraphicPart.STATE_NURSE_JOY);
    }
    
    //METODOS ANIMACIONES POKEMONESSS
    
    private Pokemon attacker, defender;
}