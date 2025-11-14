package GameDesing.Graphics;

import GameDesing.Game;
import Persons.Trainer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.util.Random;
import Pokemons.*;

public class pkSelectorPanel extends JPanel implements Runnable {

    final int originalTileSize = 32;
    final int scale = 2;
    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 12;
    final int maxScreenRow = 9;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    // SYSTEM
    Game ga=new Game();
    
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Random random = new Random();
    int FPS = 60;
    Trainer trainer1=new Trainer(this,keyH,"3",false);
    Trainer trainer2=new Trainer(this,keyH,"1",true);
    
    // GAME STATE
    public final int t1State = 1;
    public final int t2State = 2;
    public int gameState = t1State; // empieza Trainer 1

    // ARRAYS DE SELECCIÓN
    String[] trainer1Pokemons = new String[6];
    String[] trainer2Pokemons = new String[6];
    int t1Index = 0;
    int t2Index = 0;

    
    // SPRITES
    final int iconSize = 70;
    final int spacing = 15;
    final int rows = 6;
    final int cols = 2;
    
    String[] spriteNames = {
            "Alakazam", "Electrode", "arcanine", "bulbasaur",
            "gyarados", "charmander", "dodrio", "dugtrio",
            "hitmonlee", "victreebel", "squirtle", "pikachu"
    };
    
    
    
    Pokemon[] pokimones={
    };
    
 
    // COORDENADAS DEL GRID
    int gridWidth = cols * (iconSize + spacing) - spacing;
    int gridHeight = rows * (iconSize + spacing) - spacing;
    int gridX = (screenWidth - gridWidth) /2;
    int gridY = (screenHeight - gridHeight) /2;

    // CURSOR
    int selectedRow = 0;
    int selectedCol = 0;

    BufferedImage background;

    public pkSelectorPanel() {
        try {
            background = selectBackground();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public BufferedImage selectBackground() throws IOException {
        BufferedImage img = null;
        int num = random.nextInt(5);
        switch (num) {
            case 0 -> img = ImageIO.read(getClass().getResourceAsStream("/selScreen/select1.png"));
            case 1 -> img = ImageIO.read(getClass().getResourceAsStream("/selScreen/select2.png"));
            case 2 -> img = ImageIO.read(getClass().getResourceAsStream("/selScreen/select3.png"));
            case 3 -> img = ImageIO.read(getClass().getResourceAsStream("/selScreen/select4.png"));
            case 4 -> img = ImageIO.read(getClass().getResourceAsStream("/selScreen/select5.png"));
        }
        return img;
    }

    public void draw(Graphics2D g2) throws IOException {
        trainer1.static_draw(g2, 300);
        trainer2.static_draw(g2, 300);
      
        int index = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = gridX + col * (iconSize + spacing);
                int y = gridY + row * (iconSize + spacing);

                BufferedImage img = ImageIO.read(
                        getClass().getResourceAsStream("/pokemones/" + spriteNames[index++] + ".png"));
                g2.drawImage(img, x, y, iconSize, iconSize, null);

                g2.setColor(Color.WHITE);
                g2.drawRoundRect(x, y, iconSize, iconSize, 10, 10);
            }
        }

        // DIBUJAR CURSOR (color depende del turno)
        int highlightX = gridX + selectedCol * (iconSize + spacing);
        int highlightY = gridY + selectedRow * (iconSize + spacing);

        if (gameState == t1State)
            g2.setColor(new Color(255, 60, 60, 180)); // rojo Trainer 1
        else
            g2.setColor(new Color(60, 60, 255, 180)); // azul Trainer 2

        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(highlightX - 3, highlightY - 3, iconSize + 6, iconSize + 6, 10, 10);

        // Mostrar texto de turno
        g2.setFont(new Font("Arial", Font.BOLD, 24));
        g2.setColor(Color.WHITE);
        String turno = (gameState == t1State) ? "Turno: Trainer 1" : "Turno: Trainer 2";
        g2.drawString(turno, screenWidth / 2 - 100, 40);
    }

    public void update() {
        if (keyH.upPressed) {
            selectedRow = (selectedRow - 1 + rows) % rows;
            keyH.upPressed = false;
        }
        if (keyH.downPressed) {
            selectedRow = (selectedRow + 1) % rows;
            keyH.downPressed = false;
        }
        if (keyH.leftPressed) {
            selectedCol = (selectedCol - 1 + cols) % cols;
            keyH.leftPressed = false;
        }
        if (keyH.rightPressed) {
            selectedCol = (selectedCol + 1) % cols;
            keyH.rightPressed = false;
        }

        // Seleccionar Pokémon con ENTER
        if (keyH.enterPressed) {
            int index = selectedRow * cols + selectedCol;
            String chosenPokemon = spriteNames[index];

            if (gameState == t1State) {
                if (t1Index < trainer1Pokemons.length) {
                    trainer1Pokemons[t1Index++] = chosenPokemon;
                    System.out.println("Trainer 1 eligió: " + chosenPokemon);
                    gameState = t2State; // Cambia turno
                }
            } else if (gameState == t2State) {
                if (t2Index < trainer2Pokemons.length) {
                    trainer2Pokemons[t2Index++] = chosenPokemon;
                    System.out.println("Trainer 2 eligió: " + chosenPokemon);
                    gameState = t1State; // vuelve turno a Trainer 1
                }
            }

            keyH.enterPressed = false;
        }
        trainer1.static_update();
        trainer2.static_update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (background != null)
            g2.drawImage(background, 0, 0, screenWidth, screenHeight, null);

        try {
            draw(g2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2.dispose();
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
}
