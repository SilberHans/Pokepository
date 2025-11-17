package GameDesing.Graphics;

import GameDesing.Game;
import Persons.Trainer;
import Pokemons.Pokemon;
import java.util.Random;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
        // actualizar trainers en todos los estados (para animaciones)
        trainer1.update();
        trainer2.update();
        
        // ACTUALIZA POKEMONESSs
        if (trainer1ActivePokemon != null) {
            trainer1ActivePokemon.updateAnimation();
        }
        if (trainer2ActivePokemon != null) {
            trainer2ActivePokemon.updateAnimation();
        }
        
        
        
        if (keyH.escPressed) {
            stopBattle(); // Llama al método para detener y cambiar de panel//TEMPORALLLL
            keyH.escPressed = false; 
            return; 
        }
        
        // Lógica de la batalla
        switch (battleState) {
            case STATE_START:
                if (keyH.enterPressed) {
                    battleState = STATE_P1_SELECT_POKEMON;
                    currentMessage = "Trainer 1, ¡elige tu Pokémon!";
                    menuCursorPos = 0;
                    currentMenuSize = trainer1.getPhPokeList().size();
                    keyH.enterPressed = false;
                }
                break;
                
            case STATE_P1_SELECT_POKEMON:
                handleMenuNavigation();
                if (keyH.enterPressed) {
                    trainer1ActivePokemon = trainer1.getPhPokemon(menuCursorPos);
                    
                    battleState = STATE_P2_SELECT_POKEMON;
                    currentMessage = "Trainer 2, ¡elige tu Pokémon!";
                    menuCursorPos = 0;
                    currentMenuSize = trainer2.getPhPokeList().size();
                    keyH.enterPressed = false;
                }
                break;

            case STATE_P2_SELECT_POKEMON:
                handleMenuNavigation();
                if (keyH.enterPressed) {
                    trainer2ActivePokemon = trainer2.getPhPokemon(menuCursorPos);
                    
                    battleState = STATE_P1_SEND_ANIM;
                    currentMessage = "¡Trainer 1 lanza su Pokémon!";
                    trainer1.startThrowAnimation(true);
                    keyH.enterPressed = false;
                }
                break;
            
            // ANIMACIONES DE LANZAMIENTO
            case STATE_P1_SEND_ANIM:
                if (trainer1.isThrowComplete()) {
                    trainer1ActivePokemon.setDefaultBattlePosition(90, 158);//POKEMON1
                    battleState = STATE_P2_SEND_ANIM;
                    currentMessage = "¡Trainer 2 lanza su Pokémon!";
                    trainer2.startThrowAnimation(false);
                }
                break;
                
            case STATE_P2_SEND_ANIM:
                if (trainer2.isThrowComplete()) {
                    trainer2ActivePokemon.setDefaultBattlePosition(475, 28);//POKEMON2
                    battleState = STATE_P1_TURN;
                    currentMessage = "¿Qué hará " + trainer1ActivePokemon.getPkNickName() + "?";
                    menuCursorPos = 0;
                    currentMenuSize = 4;
                    
                    // Reiniciar trainers para estado idle
                    trainer1.resetState();
                    trainer2.resetState();
                }
                break;

            // TURNOS DE LOS JUGADORES
            case STATE_P1_TURN:
                battleState = STATE_P1_MENU;
                currentMessage = "¿Qué hará " + trainer1ActivePokemon.getPkNickName() + "?";
                menuCursorPos = 0;
                currentMenuSize = 4;
                break;
                
            case STATE_P1_MENU:
                handleMenuNavigation();
                if (keyH.enterPressed) {
                    keyH.enterPressed = false;
                    switch (menuCursorPos) {
                        case 0: // FIGHT
                            battleState = STATE_P1_MOVE;
                            currentMessage = "Elige un ataque:";
                            menuCursorPos = 0;
                            currentMenuSize = 4; 
                            break;
                        case 1: // POKEMON
                            battleState = STATE_P1_SELECT_POKEMON;
                            currentMessage = "Trainer 1, ¡elige otro Pokémon!";
                            menuCursorPos = 0;
                            currentMenuSize = trainer1.getPhPokeList().size();
                            break;
                        case 2: // ITEM
                            battleState = STATE_ANNOUNCE;
                            currentMessage = "¡No tienes items!";
                            break;
                        case 3: // RUN
                            battleState = STATE_ANNOUNCE;
                            currentMessage = "¡No puedes huir!";
                            break;
                    }
                }
                break;
                
            case STATE_P1_MOVE:
                handleMenuNavigation();
                if (keyH.enterPressed) {
                    String moveName = "ATAQUE " + (menuCursorPos + 1);
                    
                    attacker = trainer1ActivePokemon;
                    defender = trainer2ActivePokemon;
                    battleState = STATE_PERFORM_MOVE;
                    currentMessage = "¡" + attacker.getPkNickName() + " usó " + moveName + "!";
                    attacker.startAnimation("attack");
                    
                    keyH.enterPressed = false;
                }
                break;
                
            case STATE_PERFORM_MOVE:
                // Espera a que la animación de ATAQUE termine
                if (attacker != null && !attacker.isAnimating()) {
                    // Lógica de daño iría aquí (ej. defender.pkTakeDamage(10);)
                    
                    defender.startAnimation("damage"); // Iniciar animación de daño
                    battleState = STATE_ANIMATION; // Ir al nuevo estado de espera
                }
                break;
                
            case STATE_ANIMATION:
                // Espera a que la animación de DAÑO termine
                if (defender != null && !defender.isAnimating()) {
                    if (checkFaintedPokemon()) {
                        // Manejar Pokémon debilitado (ya lo hace checkFaintedPokemon)
                    } else {
                        // Cambiar al turno del siguiente jugador
                        if (attacker == trainer1ActivePokemon) {
                            battleState = STATE_P2_TURN;
                            currentMessage = "¿Qué hará " + trainer2ActivePokemon.getPkNickName() + "?";
                        } else {
                            battleState = STATE_P1_TURN;
                            currentMessage = "¿Qué hará " + trainer1ActivePokemon.getPkNickName() + "?";
                        }
                    }
                }
                break;
                
            case STATE_P2_TURN:
                battleState = STATE_P2_MENU;
                currentMessage = "¿Qué hará " + trainer2ActivePokemon.getPkNickName() + "?";
                menuCursorPos = 0;
                currentMenuSize = 4;
                break;
                
            case STATE_P2_MENU:
                handleMenuNavigation();
                if (keyH.enterPressed) {
                    keyH.enterPressed = false;
                    switch (menuCursorPos) {
                        case 0: // FIGHT
                            battleState = STATE_P2_MOVE;
                            currentMessage = "Elige un ataque:";
                            menuCursorPos = 0;
                            currentMenuSize = 4; 
                            break;
                        case 1: // POKEMON
                            battleState = STATE_P2_SELECT_POKEMON;
                            currentMessage = "Trainer 2, ¡elige otro Pokémon!";
                            menuCursorPos = 0;
                            currentMenuSize = trainer2.getPhPokeList().size();
                            break;
                        case 2: // ITEM
                            battleState = STATE_ANNOUNCE;
                            currentMessage = "¡No tienes items!";
                            break;
                        case 3: // RUN
                            battleState = STATE_ANNOUNCE;
                            currentMessage = "¡No puedes huir!";
                            break;
                    }
                }
                break;
                
            case STATE_P2_MOVE:
                handleMenuNavigation();
                if (keyH.enterPressed) {
                    String moveName = "ATAQUE " + (menuCursorPos + 1);
                    
                    attacker = trainer2ActivePokemon;
                    defender = trainer1ActivePokemon;
                    battleState = STATE_PERFORM_MOVE;
                    currentMessage = "¡" + attacker.getPkNickName() + " usó " + moveName + "!";
                    attacker.startAnimation("attack");
                    
                    keyH.enterPressed = false;
                }
                break;
                
            case STATE_ANNOUNCE:
                if (keyH.enterPressed) {
                    // Volver al turno correspondiente (lógica simple, puede fallar si T2 usa item)
                    // (Corregido para comprobar quién es el atacante)
                    if (attacker == trainer1ActivePokemon) {
                        battleState = STATE_P1_TURN;
                    } else {
                        battleState = STATE_P2_TURN;
                    }
                    keyH.enterPressed = false;
                }
                break;
                
            case STATE_FAINTED:
                if (keyH.enterPressed) {
                    // Lógica para Pokémon debilitado
                    battleState = STATE_P1_TURN; // Volver al jugador 1 por ahora
                    currentMessage = "¡Elige otro Pokémon!";
                    keyH.enterPressed = false;
                }
                break;
        }
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