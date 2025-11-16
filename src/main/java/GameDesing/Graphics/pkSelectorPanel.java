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
import java.io.InputStream;
import java.util.ArrayList;

public class pkSelectorPanel extends JPanel implements Runnable {

    final int originalTileSize = 32;
    final int scale = 2;
    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 12;
    final int maxScreenRow = 9;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    
    private Font msgFont;
    private Trainer trainer1;
    private Trainer trainer2;
    private Game game;
    private KeyHandler keyH;
    private Random random = new Random();
    
    // SYSTEM
    public pkSelectorPanel(Game game) {
        this.game=game;
        try {
            background = selectBackground();
        } catch (IOException e) {
            e.printStackTrace();
        }
        trainer1=game.getgTrainer1();
        trainer2=game.getgTrainer2();
        this.keyH = new KeyHandler();
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.loadResources();
        
        int for1=random.nextInt(4)+1;
        
        this.trainer1.initGraphics(this, keyH, Integer.toString(for1), true);
        this.trainer2.initGraphics(this, keyH, generateRandom(for1), false);
        
    }    
    
    Thread gameThread;
    int FPS = 60;
    
    
    //Generate random sprite != trainer1
    public String generateRandom(int for1){
        int for2=random.nextInt(4)+1;
        while(for1==for2){
            for2=random.nextInt(4)+1;
        }
        String str=Integer.toString(for2);
        return str;
    }
    
    // GAME STATE
    public final int t1State = 1;
    public final int t2State = 2;
    public int gameState = t1State; // empieza Trainer 1

    // ARRAYS DE SELECCIÓN
    String[] trainer1Pokemons = new String[6];
    String[] trainer2Pokemons = new String[6];
    int t1Index = 0;
    int t2Index = 0;

    //TRANCISION
    private boolean battleReady = false;
    private long battleStartTime = 0;
    private final long DELAY = 3000;
    
    // SPRITES
    final int iconSize = 70;
    final int spacing = 15;
    final int rows = 6;
    final int cols = 2;
    
    String[] spriteNames = {
            "Alakazam", "Electrode", "Arcanine", "Bulbasaur",
            "Gyarados", "Charmander", "Dodrio", "Dugtrio",
            "Hitmonlee", "Victreebel", "Squirtle", "Pikachu"
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
    BufferedImage chat;


    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    
    private BufferedImage[] normalPokemonSprites = new BufferedImage[spriteNames.length];
    private BufferedImage[] selectedPokemonSprites = new BufferedImage[spriteNames.length];
    private boolean[] pokemonSelected = new boolean[spriteNames.length];

    
    private void loadResources() {
        try {
            InputStream bgStream = getClass().getResourceAsStream("/util/chat.png");
            chat = ImageIO.read(bgStream);

            InputStream fontStream = getClass().getResourceAsStream("/fonts/Pokemon Classic.ttf");
            msgFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(24f);

            // Cargar sprites normales y seleccionados
            loadPokemonSprites();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error cargando recursos. Usando fuente por defecto.");
        }
    }

    private void loadPokemonSprites() throws IOException {
        for (int i = 0; i < spriteNames.length; i++) {
            // Cargar versión normal
            normalPokemonSprites[i] = ImageIO.read(getClass().getResourceAsStream("/pokemones/" + spriteNames[i] + ".png"));

            // Cargar versión gris(seleccionada)
            String gritName = spriteNames[i] + "_gris.png";
            selectedPokemonSprites[i] = ImageIO.read(getClass().getResourceAsStream("/selected/" + gritName));
        }
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

    private void drawMsg(Graphics2D g2, String text, int boxX, int boxY, int boxWidth, int boxHeight,float fontSize) {
        g2.setFont(msgFont);
        Font originalFont = g2.getFont();
        g2.setFont(originalFont.deriveFont(fontSize));

        // Dividir el texto en líneas usando \n como separador
        String[] lines = text.split("\n");
        FontMetrics fm = g2.getFontMetrics();
        int lineHeight = fm.getHeight();

        // Calcular posición Y inicial para centrar verticalmente
        int totalTextHeight = lines.length * lineHeight;
        int startY = boxY + (boxHeight - totalTextHeight) / 2 + fm.getAscent();

        // Dibujar cada línea
        Color c = new Color(75,65,22);
        g2.setColor(c);
        for (int i = 0; i < lines.length; i++) {
            int textWidth = fm.stringWidth(lines[i]);
            int x = boxX + (boxWidth - textWidth) / 2;
            int y = startY + (i * lineHeight);
            g2.drawString(lines[i], x, y);
        }
       }   
    
    
    public void draw(Graphics2D g2) throws IOException {
        trainer1.static_draw(g2, 300);
        trainer2.static_draw(g2, 300);

        int index = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = gridX + col * (iconSize + spacing);
                int y = gridY + row * (iconSize + spacing);

                // Usar sprite normal o gris según si está seleccionado
                BufferedImage img = pokemonSelected[index] ? 
                    selectedPokemonSprites[index] : normalPokemonSprites[index];

                g2.drawImage(img, x, y, iconSize, iconSize, null);

                // Cambiar color del borde según estado
                if (pokemonSelected[index]) {
                    g2.setColor(new Color(150, 150, 150)); // Gris para seleccionados
                } else {
                    g2.setColor(Color.WHITE); // Blanco para disponibles
                }
                g2.drawRoundRect(x, y, iconSize, iconSize, 10, 10);

                index++;
            }
        }

        // DIBUJAR CURSOR (color depende del turno)
        int highlightX = gridX + selectedCol * (iconSize + spacing);
        int highlightY = gridY + selectedRow * (iconSize + spacing);

        if (gameState == t1State){
            g2.setColor(new Color(255, 0, 0)); // rojo Trainer 1
            g2.drawImage(chat, 100,60, 60, 60,null);
        }else{
            g2.setColor(new Color(0, 77, 255)); // azul Trainer 2
            g2.drawImage(chat, 570,60,60, 60,null);
        }    
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(highlightX - 3, highlightY - 3, iconSize + 6, iconSize + 6, 10, 10);

        
        //DIBUJAR CAJITA 1
        Color c=new Color(255, 255, 200, 200);
        g2.setColor(c);
        g2.fillRoundRect(20,428, 260, 100, 20, 20);
        c=new Color(255,255,180);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(20,428, 260, 100, 20, 20);
        
        //DIBUJAR CAJITA 2
        c=new Color(255, 255, 200, 200);
        g2.setColor(c);
        g2.fillRoundRect(490,428, 260, 100, 20, 20);
        c=new Color(255,255,180);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(490,428, 260, 100, 20, 20);
        
        drawSelectionInfo(g2);
    }
    
    
    private String gameMessage = "";
    private String gameMessage2 = "";
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
            Pokemon chosenPokemonn= this.game.getgPokemonList().get(index);

            // Verificar seleccionado
            if (pokemonSelected[index]) {
                gameMessage = "¡Pokémon ya\nseleccionado!";
                if (gameState == t1State) {
                    gameMessage2 = ""; // Limpiar mensaje del otro trainer
                } else {
                    gameMessage2 = "¡Pokémon ya\nseleccionado!";
                    gameMessage = ""; // Limpiar mensaje del otro trainer
                }
            } else {
                // Pokémon disponible
                if (gameState == t1State) {
                    if (t1Index < trainer1Pokemons.length) {
                        trainer1Pokemons[t1Index++] = chosenPokemon;
                        pokemonSelected[index] = true; // Marcar como seleccionado

                        gameMessage = "Trainer 1 eligió:\n" + chosenPokemon;
                        trainer1.addPhPokemon(chosenPokemonn);
                        gameMessage2 = ""; // Limpiar mensaje anterior
                        System.out.println(gameMessage);
                        gameState = t2State;
                    }
                } else if (gameState == t2State) {
                    if (t2Index < trainer2Pokemons.length) {
                        trainer2Pokemons[t2Index++] = chosenPokemon;
                        pokemonSelected[index] = true; // Marcar como seleccionado

                        gameMessage2 = "Trainer 2 eligió:\n" + chosenPokemon;
                        trainer2.addPhPokemon(chosenPokemonn);
                        gameMessage = ""; // Limpiar mensaje anterior
                        System.out.println(gameMessage2);
                        gameState = t1State;
                    }
                }
            }

            keyH.enterPressed = false;
        }
        
        if (!battleReady && t1Index == 6 && t2Index == 6) {
            battleReady = true;
            battleStartTime = System.currentTimeMillis();
            System.out.println("¡Ambos equipos completos! Iniciando batalla en 3 segundos...");
        }

        
        if (battleReady && System.currentTimeMillis() - battleStartTime >= DELAY) {
            startTraderTransition();
        }
        trainer1.static_update();
        trainer2.static_update();
    }
    
    private void startTraderTransition() {
        if (battleReady) {
        battleReady = false;
        
        //DETENER
        if (gameThread != null) {
            gameThread.interrupt();
            gameThread = null;
        }
        
        //LIMPIAR
        this.removeKeyListener(keyH);
        background = null;
        chat = null;
        normalPokemonSprites = null;
        selectedPokemonSprites = null;
        System.out.println("Panel de selección limpiado");
        GraphicPart.traderPanel=new TraderPanel(game);
        
        System.out.println("Cambiando a panel de compra...");
        GraphicPart.cambiarPanel(GraphicPart.STATE_TRADER);
        }
    }
    
    
    private void drawBattleReadyMessage(Graphics2D g2) {
        g2.setColor(new Color(0, 0, 0, 180));
        g2.fillRect(0, 0, screenWidth, screenHeight);

        g2.setFont(msgFont.deriveFont(32f));
        g2.setColor(Color.YELLOW);
        String mainText = "¡EQUIPOS COMPLETOS!";
        int mainWidth = g2.getFontMetrics().stringWidth(mainText);
        g2.drawString(mainText, (screenWidth - mainWidth) / 2, screenHeight / 2 - 20);

        // Contador regresivo
        long elapsed = System.currentTimeMillis() - battleStartTime;
        long remaining = (DELAY - elapsed) / 1000 + 1;

        g2.setFont(msgFont.deriveFont(24f));
        g2.setColor(Color.WHITE);
        String countdown = "Pasando a comprar items en " + remaining + "...";
        int countWidth = g2.getFontMetrics().stringWidth(countdown);
        g2.drawString(countdown, (screenWidth - countWidth) / 2, screenHeight / 2 + 30);
    }
    

    private void drawSelectionInfo(Graphics2D g2) {
        
        g2.setFont(msgFont.deriveFont(14f));
        g2.setColor(new Color(75, 65, 22));

        // Contador 1
        String counter1 = "Pokémon: " + t1Index + "/6";
        int width1 = g2.getFontMetrics().stringWidth(counter1);
        g2.drawString(counter1, 20 + (260 - width1) / 2, 523);

        // Contador 2
        String counter2 = "Pokémon: " + t2Index + "/6";
        int width2 = g2.getFontMetrics().stringWidth(counter2);
        g2.drawString(counter2, 490 + (260 - width2) / 2, 523);

 
        if (battleReady) {
            drawBattleReadyMessage(g2);
        }

        // Restaurar tamaño de fuente original
        g2.setFont(msgFont);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (background != null)
            g2.drawImage(background, 0, 0, screenWidth, screenHeight, null);

        g2.setFont(msgFont);
        
        try {
            draw(g2);
            // Dibuja el mensaje actual si existe
            if (!gameMessage.isEmpty()) {
                drawMsg(g2, gameMessage, 20, 428, 260, 100, 15);
            }
            if (!gameMessage2.isEmpty()) {
                drawMsg(g2, gameMessage2, 490, 428, 260, 100, 15);
            }
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
