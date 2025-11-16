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
import java.util.ArrayList; // Import para la lista de nombres
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
    UI ui = new UI(this); // La UI ahora está conectada
    int FPS = 60;
    BufferedImage background;
    // TODO: Necesitarás cargar los sprites de los Pokémon
    // BufferedImage p1BackSprite, p2FrontSprite;

    // --- BATTLE STATE MACHINE ---
    public int battleState;
    public final int STATE_START = 0;           // Inicio, mensaje "Te desafía"
    public final int STATE_P1_SELECT_POKEMON = 1; // P1 elige Pokémon
    public final int STATE_P2_SELECT_POKEMON = 2; // P2 elige Pokémon
    public final int STATE_SEND_OUT_ANIM = 3;   // Animación de lanzar
    public final int STATE_PLAYER_TURN = 4;     // Turno de P1 (Transición)
    public final int STATE_PLAYER_MENU = 5;     // Menú [FIGHT, PKMN, ITEM]
    public final int STATE_PLAYER_MOVE = 6;     // Menú de ataques
    public final int STATE_PERFORM_MOVE = 7;    // Se ejecuta el ataque
    public final int STATE_ENEMY_TURN = 8;      // Turno de P2
    public final int STATE_ANNOUNCE = 9;        // Mostrando mensaje ("Pikachu usó...")
    public final int STATE_FAINTED = 10;
    public final int STATE_END = 11;
    
    // --- LÓGICA DE MENÚS (Usada por UI.java) ---
    public String currentMessage = "";
    public int menuCursorPos = 0;
    public int currentMenuSize = 0; // Para saber el límite del cursor
   
    
    
    public BattlePanel(Game game) {
        this.game = game;
        
        // 1. Obtener los trainers (que ya tienen sus Pokémon)
        this.trainer1 = game.getgTrainer1();
        this.trainer2 = game.getgTrainer2();

        // 2. Configurar el Panel
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        // 3. Cargar Recursos e Iniciar Batalla
        this.background = selectBattleBackground();
        this.trainer1ActivePokemon = null; 
        this.trainer2ActivePokemon = null;
        this.battleState = STATE_START;
        this.currentMessage = "¡ The battle must start, FIGHT!";
        this.menuCursorPos = 0;
        this.currentMenuSize = 0;
        initTrainers();
        
    }

    private void initTrainers() {
        this.trainer1.initGraphics(this, keyH, trainer1.num, true); 
        this.trainer2.initGraphics(this, keyH, trainer2.num, false); 

        trainer1.setDefaultValues(true);
        trainer2.setDefaultValues(false);
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
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    
    
    
    
    
public void update() {
        
        // Lógica de la batalla basada en el estado
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
                handleMenuNavigation(); // El Jugador 2 también elige
                if (keyH.enterPressed) {
                    trainer2ActivePokemon = trainer2.getPhPokemon(menuCursorPos);
                    
                    battleState = STATE_SEND_OUT_ANIM;
                    // Asumo que Pokemon tiene .getpName()
                    currentMessage = "¡" + trainer1.getpName() + " sacó a " + trainer1ActivePokemon.getPkNickName() + "!";
                    keyH.enterPressed = false;
                }
                break;
                
            case STATE_SEND_OUT_ANIM:
                // Animación simple: Espera a ENTER
                if (keyH.enterPressed) {
                    battleState = STATE_PLAYER_TURN;
                    currentMessage = "¿Qué hará " + trainer1ActivePokemon.getPkNickName() + "?";
                    keyH.enterPressed = false;
                }
                break;

            case STATE_PLAYER_TURN:
                // Estado de transición
                battleState = STATE_PLAYER_MENU;
                currentMessage = "¿Qué hará " + trainer1ActivePokemon.getPkNickName() + "?";
                menuCursorPos = 0;
                currentMenuSize = 4; // FIGHT, PKMN, ITEM, RUN
                break;
                
            case STATE_PLAYER_MENU:
                handleMenuNavigation();
                if (keyH.enterPressed) {
                    keyH.enterPressed = false;
                    switch (menuCursorPos) {
                        case 0: // FIGHT
                            battleState = STATE_PLAYER_MOVE;
                            currentMessage = "Elige un ataque:";
                            menuCursorPos = 0;
                            // Asumo 4 ataques por ahora
                            currentMenuSize = 4; 
                            break;
                        case 1: // POKEMON
                            // Lógica de testeo: Vuelve a la selección de P1
                            battleState = STATE_P1_SELECT_POKEMON;
                            currentMessage = "Trainer 1, ¡elige otro Pokémon!";
                            menuCursorPos = 0;
                            currentMenuSize = trainer1.getPhPokeList().size();
                            break;
                        case 2: // ITEM
                            // Lógica de testeo: Muestra mensaje y vuelve
                            battleState = STATE_ANNOUNCE; // Usamos ANNOUNCE para mostrar el msg
                            currentMessage = "¡No tienes items!";
                            // Al pulsar ENTER en ANNOUNCE, volverá al turno del rival
                            break;
                        case 3: // RUN
                            // Lógica de testeo: Muestra mensaje y pasa turno
                            battleState = STATE_ANNOUNCE; // Usamos ANNOUNCE
                            currentMessage = "¡No puedes huir!";
                            break;
                    }
                }
                break;
                
            case STATE_PLAYER_MOVE:
                handleMenuNavigation();
                if (keyH.enterPressed) {
                    // Lógica de testeo: No ataca, solo anuncia
                    // String moveName = trainer1ActivePokemon.getAttackNames()[menuCursorPos];
                    String moveName = "ATAQUE " + (menuCursorPos + 1);
                    
                    battleState = STATE_ANNOUNCE;
                    currentMessage = "¡" + trainer1ActivePokemon.getPkNickName() + " usó " + moveName + "!";
                    keyH.enterPressed = false;
                }
                break;

            case STATE_ANNOUNCE:
                // Muestra el mensaje (de ataque, item, huir, etc.)
                // Espera a ENTER para pasar al turno del rival
                if (keyH.enterPressed) {
                    battleState = STATE_ENEMY_TURN;
                    currentMessage = "¡Turno del rival! " + trainer2ActivePokemon.getPkNickName() + " está pensando...";
                    keyH.enterPressed = false;
                }
                break;
            
            case STATE_ENEMY_TURN:
                // Lógica de testeo: El rival "ataca" y volvemos al turno del jugador
                // (Presiona ENTER para simular el ataque rival)
                if (keyH.enterPressed) {
                    battleState = STATE_PLAYER_TURN;
                    currentMessage = "¡" + trainer2ActivePokemon.getPkNickName() + " usó ATAQUE RIVAL!\n¿Qué hará " + trainer1ActivePokemon.getPkNickName() + "?";
                    keyH.enterPressed = false;
                }
                break;
                
            // ... (otros estados: FAINTED, END) ...
        }
    }

    /**
     * Maneja el movimiento del cursor en los menús (usado por la UI)
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
     * MÉTODO PAINTCOMPONENT MODIFICADO
     * (Sigue la lógica de tu UI.java)
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Limpia la pantalla
        Graphics2D g2 = (Graphics2D) g;

        // --- 1. Preparar datos dinámicos ---
        
        String message = this.currentMessage;
        String[] options = null; // Por defecto, no hay menú
        int cursor = this.menuCursorPos;

        // Decidir qué menú mostrar basado en el estado
        switch (battleState) {
            case STATE_P1_SELECT_POKEMON:
                options = trainer1.getPokemonNames();
                break;
            case STATE_P2_SELECT_POKEMON:
                options = trainer2.getPokemonNames();
                break;
            case STATE_PLAYER_MENU:
                options = new String[]{"FIGHT", "POKEMON", "ITEM", "RUN"};
                break;
            case STATE_PLAYER_MOVE:
                // (Usando placeholder por ahora)
                // TODO: Reemplazar con: options = trainer1ActivePokemon.getAttackNames();
                options = new String[]{"Ataque 1", "Ataque 2", "Ataque 3", "Ataque 4"};
                break;
            // En otros estados (START, ANNOUNCE, etc.), 'options' sigue siendo 'null'
        }

        // --- 2. DIBUJAR TODO ---

        // Dibuja el fondo y las CAJAS VACÍAS
        ui.drawBattleLayout(g2, screenWidth, screenHeight, background);

        // Dibuja el MENSAJE DENTRO de la caja de texto
        ui.drawMessage(g2, message);

        // Dibuja las OPCIONES DENTRO de la caja de opciones
        ui.drawBattleOptions(g2, options, cursor);

        // Dibuja los Pokémon (si ya salieron)
        if (battleState >= STATE_SEND_OUT_ANIM) {
            // TODO: Dibujar sprites de espalda (P1) y frente (P2)
            // g2.drawImage(p1BackSprite, 90, 250, 192, 192, null);
            // g2.drawImage(p2FrontSprite, 580, 150, 128, 128, null);
        }

        // Dibuja los Entrenadores (solo si NO han lanzado)
        if (battleState < STATE_SEND_OUT_ANIM) {
            trainer1.draw(g2); 
            trainer2.draw(g2);
        }
        
        // Dibuja las barras de HP (EN-CIMA de todo)
        try {
            // ui.draw(g2) llama a drawEnemyUI y drawPlayerUI
            ui.draw(g2); 
        } catch (IOException ex) {
            Logger.getLogger(BattlePanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        g2.dispose();
    }


    private BufferedImage selectBattleBackground() {
        int x = random.nextInt(9)+1;
        String num = Integer.toString(x);
        try {
            return ImageIO.read(getClass().getResourceAsStream("/btScreen/" + num + ".png"));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Error cargando fondo");
            return null;
        }
    }
}