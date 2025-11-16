package GameDesing.Graphics;

import GameDesing.Game;
import Persons.NurseJoy;
import Persons.Trainer;
import Pokemons.Pokemon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class NurseJoyPanel extends JPanel implements Runnable {

    // --- CONFIGURACIÓN DE PANTALLA ---
    final int originalTileSize = 32;
    final int scale = 2;
    public final int tileSize = originalTileSize * scale; // 64
    final int maxScreenCol = 12; // 768 pixels
    final int maxScreenRow = 9;  // 576 pixels
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // --- SISTEMA ---
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    UI ui = new UI(this);
    int FPS = 60;
    
    // --- DATOS DEL JUEGO ---
    Game game;
    Trainer trainer1;
    Trainer trainer2;
    NurseJoy nurseJoy;
    
    // --- ESTADO DEL PANEL ---
    public final int t1Turn = 1;
    public final int t2Turn = 2;
    public int panelState = t1Turn; // Inicia el Trainer 1
    
    public int t1CursorPos = 0; // Cursor de Pokémon para T1 (0-5)
    public int t2CursorPos = 0; // Cursor de Pokémon para T2 (0-5)
    
    final int HEAL_COST = 1000;
    String dialogMessage = "¡Bienvenido al Centro Pokémon! ¿Deseas sanar a tus Pokémon?";
    
    // --- RECURSOS GRÁFICOS ---
    BufferedImage background;
    BufferedImage nurseJoySprite;

    public NurseJoyPanel(Game game) {
        this.game = game;
        this.trainer1 = game.getgTrainer1();
        this.trainer2 = game.getgTrainer2();
        this.nurseJoy = game.getgNurseJoy();
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        
        loadResources();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void loadResources() {
        try {
            // Cargar el fondo que generamos
            InputStream bgStream = getClass().getResourceAsStream("/images/poke_center_bg.png");
            background = ImageIO.read(bgStream);
            
            // Obtener el sprite de la Enfermera Joy (ya cargado en su constructor)
            nurseJoySprite = nurseJoy.getSprite();
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error cargando recursos en NurseJoyPanel.");
            background = null;
            nurseJoySprite = null;
        }
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
        // --- Navegación Vertical (Moverse por la lista de Pokémon) ---
        if (keyH.upPressed) {
            if (panelState == t1Turn) {
                t1CursorPos--;
                if (t1CursorPos < 0) t1CursorPos = 5;
            } else {
                t2CursorPos--;
                if (t2CursorPos < 0) t2CursorPos = 5;
            }
            keyH.upPressed = false;
        }
        
        if (keyH.downPressed) {
            if (panelState == t1Turn) {
                t1CursorPos++;
                if (t1CursorPos > 5) t1CursorPos = 0;
            } else {
                t2CursorPos++;
                if (t2CursorPos > 5) t2CursorPos = 0;
            }
            keyH.downPressed = false;
        }
        
        // --- Navegación Horizontal (Cambiar entre T1 y T2) ---
        if (keyH.leftPressed || keyH.rightPressed) {
            panelState = (panelState == t1Turn) ? t2Turn : t1Turn; // Cambiar de turno
            keyH.leftPressed = false;
            keyH.rightPressed = false;
        }
        
        // --- Acción (Sanar) ---
        if (keyH.enterPressed) {
            healSelectedPokemon();
            keyH.enterPressed = false;
        }
        
        // --- Salir ---
        if (keyH.escPressed) {
            // Volver al menú principal (o a donde sea necesario)
            System.out.println("Saliendo del Centro Pokémon...");
            // Detener este hilo antes de cambiar de panel
            if (gameThread != null) {
                gameThread.interrupt();
                gameThread = null;
            }
            this.removeKeyListener(keyH);
            GraphicPart.cambiarPanel(GraphicPart.STATE_START); // Volver al inicio por ahora
            keyH.escPressed = false;
        }
    }
    
    private void healSelectedPokemon() {
        Trainer activeTrainer = (panelState == t1Turn) ? trainer1 : trainer2;
        int activeCursor = (panelState == t1Turn) ? t1CursorPos : t2CursorPos;
        
        Pokemon selectedPokemon = activeTrainer.getPhPokemon(activeCursor);
        
        if (selectedPokemon == null) {
            dialogMessage = "No hay ningún Pokémon en esa posición.";
            return;
        }
        
        // 1. Revisar si ya está sanado
        if (selectedPokemon.getPkHp() == selectedPokemon.getPkMaxHp()) {
            dialogMessage = "¡" + selectedPokemon.getPkNickName() + " ya está completamente sano!";
            return;
        }
        
        // 2. Revisar dinero
        if (activeTrainer.getpPokeDollars() >= HEAL_COST) {
            // 3. Sanar y cobrar
            activeTrainer.loseMoney(HEAL_COST);
            selectedPokemon.pkHeal(selectedPokemon.getPkMaxHp());
            // También curamos estado y reseteamos stats (comportamiento estándar de Centro Pokémon)
            selectedPokemon.pkCureStatus();
            selectedPokemon.pkResetStatStages();
            
            dialogMessage = "¡" + selectedPokemon.getPkNickName() + " ha sido sanado por " + HEAL_COST + "$!";
        } else {
            // 4. No hay dinero
            dialogMessage = "¡Lo siento! No tienes los " + HEAL_COST + "$ necesarios.";
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // 1. Dibujar Fondo
        if (background != null) {
            g2.drawImage(background, 0, 0, screenWidth, screenHeight, null);
        } else {
            g2.setColor(Color.PINK);
            g2.fillRect(0, 0, screenWidth, screenHeight);
        }

        // 2. Dibujar Enfermera Joy (Mitad izquierda)
        if (nurseJoySprite != null) {
            // Ajustar tamaño y posición
            int spriteWidth = nurseJoySprite.getWidth() * 2;
            int spriteHeight = nurseJoySprite.getHeight() * 2;
            int x = (screenWidth / 2 - spriteWidth) / 2;
            int y = (screenHeight - spriteHeight) / 2 - 50; // Un poco más arriba
            g2.drawImage(nurseJoySprite, x, y, spriteWidth, spriteHeight, null);
        } else {
            g2.setColor(Color.WHITE);
            g2.drawString("Nurse Joy Sprite", 150, screenHeight / 2);
        }
        
        // 3. Dibujar Paneles de Entrenador (Mitad derecha)
        int boxWidth = 350;
        int boxHeight = (screenHeight / 2) - 30;
        int boxX = screenWidth / 2 + 10;
        int boxY1 = 20;
        int boxY2 = boxY1 + boxHeight + 20;
        
        ui.drawTrainerHealBox(g2, trainer1, boxX, boxY1, boxWidth, boxHeight, (panelState == t1Turn), t1CursorPos);
        ui.drawTrainerHealBox(g2, trainer2, boxX, boxY2, boxWidth, boxHeight, (panelState == t2Turn), t2CursorPos);

        // 4. Dibujar Cuadro de Diálogo
        ui.drawNurseDialog(g2, dialogMessage);

        g2.dispose();
    }
}
