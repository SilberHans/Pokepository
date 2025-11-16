package GameDesing.Graphics;

import GameDesing.Game;
import Items.Item;
import Persons.Trader;
import Persons.Trainer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class TraderPanel extends JPanel implements Runnable {

    // SCREEN SIZE
    final int originalTileSize = 32;
    final int scale = 2;
    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 12;
    final int maxScreenRow = 9;
    final int screenWidth = tileSize * maxScreenCol;// 768
    final int screenHeight = tileSize * maxScreenRow;// 576
    private Game game;
    private Trainer trainer1;
    private Trainer trainer2;
    private Trader trader;
    
    // SYSTEM
    public TraderPanel(Game game) {
        this.game=game;
        this.trainer1=game.getgTrainer1();
        this.trainer2=game.getgTrainer2();
        this.trader=game.getgTrader();
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.loadResources();
        this.itemNames= trader.getmInventoryStr();
        this.itemDescriptions= trader.getmInventoryDescription();
        this.itemIMG= trader.getmInventoryIMG();
        gameState = t1State;
    }    
    
    String t1Index,t2Index;
    UI ui = new UI(this); 
    BufferedImage background,t1,t2;
    Font menuFont;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    int FPS = 60;

    // MANEJO OPCIONES
    int selectedOption = 0;
    
    //TRANCISION
    private boolean battleReady = false;
    private long battleStartTime = 0;
    private final long DELAY = 3000;

    //TURNOS, ITEMS
    String[] itemNames; 
    String[] itemDescriptions; 
    BufferedImage[] itemIMG;
    String[] trainer1Items = new String[4];
    String[] trainer2Items = new String[4];
    int t1ItemIndex = 0;
    int t2ItemIndex = 0;
    String traderDialog = "¡Bienvenido! ¿Qué necesitas hoy?"; // Diálogo inicial

    // GAME STATES
    public int gameState;
    public final int t1State = 1;
    public final int t2State = 2;
    public final int infoState = 3; 

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void loadResources() {
        try {
            
            t1Index=trainer1.num;
            t2Index=trainer2.num;
            
            InputStream t12 = getClass().getResourceAsStream("/util/"+t1Index+".png"); 
            t1 = ImageIO.read(t12);
            
            InputStream t21 = getClass().getResourceAsStream("/util/"+t2Index+".png"); 
            t2 = ImageIO.read(t21);
            
            InputStream bgStream = getClass().getResourceAsStream("/images/traderBG.png"); 
            background = ImageIO.read(bgStream);

            InputStream fontStream = getClass().getResourceAsStream("/fonts/Pokemon Classic.ttf");
            menuFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(20f);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error cargando recursos.");
            menuFont = new Font("Arial", Font.PLAIN, 36);
        }
    }

    public void update() {
        if (keyH.upPressed) {
            selectedOption--;
            if (selectedOption < 0)
                selectedOption = itemNames.length - 1;
            keyH.upPressed = false;
        }

        if (keyH.downPressed) {
            selectedOption++;
            if (selectedOption >= itemNames.length)
                selectedOption = 0;
            keyH.downPressed = false;
        }
        
        //LOGICA ESCAPE (arreglar)
        if (keyH.escPressed) {
            GraphicPart.cambiarPanel(GraphicPart.STATE_BATTLE);
            System.out.println("Transición a Batalla (Implementar)");
            keyH.escPressed = false; 
            // Por ahora solo log, pero la idea es salir de la tienda
        }

        // LOGICA DE ENTER
        if (keyH.enterPressed) {
            String selectedItemName = itemNames[selectedOption];
            Item selectedItemNames = trader.getmInventory().get(selectedOption);
            
            if (gameState == t1State) {
                if (t1ItemIndex < trainer1Items.length) {
                    trainer1Items[t1ItemIndex] = selectedItemName;
                    trainer1.addtItem(selectedItemNames);
                    t1ItemIndex++;
                    traderDialog = "¡Trainer 1 compró " + selectedItemName + "!";
                    
                    // Si T1 terminó, pasa a T2
                    if (t1ItemIndex == trainer1Items.length) {
                        gameState = t2State;
                        traderDialog = "¡Turno del Trainer 2! ¿Tú qué quieres?";
                    }
                }
            } 
            else if (gameState == t2State) {
                if (t2ItemIndex < trainer2Items.length) {
                    trainer2Items[t2ItemIndex] = selectedItemName;
                    trainer2.addtItem(selectedItemNames);
                    t2ItemIndex++;
                    traderDialog = "¡Trainer 2 compró " + selectedItemName + "!";
                    
                    // Si T2 terminó, la compra finaliza
                    if (t2ItemIndex == trainer2Items.length) {
                        gameState = infoState; // Un estado "finalizado"
                        traderDialog = "¡Gracias por su compra! ¡Listos para la batalla!";
                        battleReady = true;
                        battleStartTime = System.currentTimeMillis();
                        System.out.println("¡Ambos equipos completos! Iniciando batalla en 3 segundos...");
                        startBattleTransition();
                    }
                }
            }
            
            keyH.enterPressed = false;
        }
    }
    
    private void startBattleTransition() {
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
        itemNames=null;
        itemDescriptions=null;
        itemIMG=null;
        trainer1Items= null;
        trainer2Items = null;
        System.out.println("Panel de compra limpiado");
        GraphicPart.battlePanel=new BattlePanel(game);
        
        System.out.println("Cambiando a panel de Batalla...");
        GraphicPart.cambiarPanel(GraphicPart.STATE_BATTLE);
        }
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (background != null)
            g2.drawImage(background, 0, 0, screenWidth, screenHeight, null);
        
        ui.drawTraderDialogBox(g2, traderDialog);

        ui.drawTrainerInfoBoxes(g2, gameState, t1, t2);
        
        ui.drawTraderBox(g2);

        g2.setFont(menuFont.deriveFont(18f));

        int baseX = (this.screenWidth / 2 - 40) + 30; 
        int baseY = (this.tileSize / 2) + 60; 
        
        for (int i = 0; i < itemNames.length; i++) {
            boolean selected = (i == selectedOption);
            drawMenuOption(g2, itemNames[i], i, baseX, baseY, selected);
        }
        
        // Contador de items (simple)
        g2.setColor(ui.windowTextColor);
        g2.setFont(menuFont.deriveFont(12f));
        String t1Count =t1ItemIndex + "/" + trainer1Items.length;
        String t2Count =t2ItemIndex + "/" + trainer2Items.length;
        g2.drawString(t1Count, this.screenWidth - 66, this.screenHeight - 87);
        g2.drawString(t2Count, this.screenWidth - 66, this.screenHeight - 30);


        if (keyH.spcPressed) {
            String info = itemNames[selectedOption];
            String desc = itemDescriptions[selectedOption];
            BufferedImage img= itemIMG[selectedOption];
            // null= imagen del item
            ui.drawItemInfoBox(g2, info, desc, null);
        }

        g2.dispose();
    }

    private void drawMenuOption(Graphics2D g2, String text, int index, int baseX, int baseY, boolean selected) {
        int y = baseY + index * 45; // Menos espaciado
        int x = baseX;

        if (selected) {
            g2.setColor(new Color(255, 79, 79)); 
            g2.drawString("> " + text, x, y);
        } else {
            g2.setColor(ui.windowTextColor); 
            g2.drawString(text, x, y);
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

}