package GameDesing.Graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class TraderPanel extends JPanel implements Runnable{

    //SCREEN SIZE
    final int originalTileSize=32;
    final int scale=2;
    public final int tileSize=originalTileSize *scale;
    final int maxScreenCol=12;
    final int maxScreenRow=9;
    final int screenWidth=tileSize*maxScreenCol;//768
    final int screenHeight=tileSize*maxScreenRow;//576
    
    //SYSTEM 
    UI ui=new UI(this);
    BufferedImage background;
    Font menuFont;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    int FPS = 60;

    //MANEJO OPCIONES
    String[] items = {"Objeto 1...\t Objeto para curar","Objeto 2...\t Objeto para luchar","Objeto 3...\t Objeto para volar"};
    int selectedOption=0;
    
    //GAME STATES
    public int gameState;
    public final int t1State=1;
    public final int t2State=2;
    public final int infoState=3;
   
    
    
    public TraderPanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.addKeyListener(keyH);
        this.setFocusable(true);
        loadResources();
    }
    
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    private void loadResources() {
        try {
            // Fondo
            InputStream bgStream = getClass().getResourceAsStream("/images/traderBG.jpg");
            background = ImageIO.read(bgStream);

            // Fuentes
            InputStream fontStream = getClass().getResourceAsStream("/fonts/Pokemon Classic.ttf");
            menuFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(20f);

            

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error cargando recursos.");
            menuFont = new Font("Arial", Font.PLAIN, 36);
        }
    }
    
    public void update(){
        if (keyH.upPressed) {
            selectedOption--;
            if (selectedOption < 0) selectedOption = items.length - 1;
            keyH.upPressed = false;
        }

        if (keyH.downPressed) {
            selectedOption++;
            if (selectedOption >= items.length) selectedOption = 0;
            keyH.downPressed = false;
        }
        
        if(keyH.escPressed){
            System.exit(0);
        }

        if (keyH.enterPressed) {
            switch (selectedOption) {
                case 0 -> System.out.println("Start selected");
                case 1 -> System.out.println(" Info selected");
                case 3 -> System.out.println(" Info selected");
            }
            keyH.enterPressed = false;
        }
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        
        //️ Fondo escalado
        if (background != null)
            g2.drawImage(background, 0, 0, screenWidth, screenHeight, null);

        ui.drawTraderBox(g2);
        //ui.drawItemInfoBox(g2);
        
        // Opciones del menú
        g2.setFont(menuFont.deriveFont(12f));
        int baseY = 100;
        for (int i = 0; i < items.length; i++) {
            boolean selected = (i == selectedOption);
            drawMenuOption(g2, items[i], i, baseY, selected);
        }
        
        
        
        g2.dispose();
    }
    
    private void drawMenuOption(Graphics2D g2, String text, int index, int baseY, boolean selected) {
        int y = baseY + index * 60;
        int x = screenWidth/ 2+ 20;

        if (selected) {
            // efecto brillante o resaltado
            g2.setColor(new Color(255, 79, 79));
            g2.drawString("> " + text, x + 7, y);
        } else {
            g2.setColor(new Color(0,0,0));
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
