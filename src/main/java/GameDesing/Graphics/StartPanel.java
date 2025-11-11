package GameDesing.Graphics;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;


public class StartPanel extends JPanel implements Runnable {

    final int originalTileSize=32;
    final int scale=2;
    public final int tileSize=originalTileSize *scale;
    final int maxScreenCol=12;
    final int maxScreenRow=9;
    final int screenWidth=tileSize*maxScreenCol;
    final int screenHeight=tileSize*maxScreenRow;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    int FPS = 60;
    UI ui=new UI(this);

    String[] options = {"Create", "Load Game", "Exit"};
    int selectedOption = 0;

    BufferedImage background;
    Font menuFont;

    public StartPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.addKeyListener(keyH);
        this.setFocusable(true);
        loadResources();
    }

    private void loadResources() {
        try {
            // 🖼️ Fondo
            InputStream bgStream = getClass().getResourceAsStream("/images/fond.png");
            background = ImageIO.read(bgStream);

            // 🔤 Fuentes
            InputStream fontStream = getClass().getResourceAsStream("/fonts/Pokemon Classic.ttf");
            menuFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(20f);

            

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("⚠️ Error cargando recursos.");
            menuFont = new Font("Arial", Font.PLAIN, 36);
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
        if (keyH.upPressed) {
            selectedOption--;
            if (selectedOption < 0) selectedOption = options.length - 1;
            keyH.upPressed = false;
        }

        if (keyH.downPressed) {
            selectedOption++;
            if (selectedOption >= options.length) selectedOption = 0;
            keyH.downPressed = false;
        }

        if (keyH.enterPressed) {
            switch (selectedOption) {
                case 0 -> System.out.println("🚀 Start selected");
                case 1 -> System.out.println("ℹ️ Info selected");
                case 2 -> System.exit(0);
            }
            keyH.enterPressed = false;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Fondo escalado
        if (background != null)
            g2.drawImage(background, 0, 0, screenWidth, screenHeight, null);

        
        ui.drawInfoBox(g2, true);
        // Opciones del menú
        g2.setFont(menuFont);
        for (int i = 0; i < options.length; i++) {
            boolean selected = (i == selectedOption);
            drawMenuOption(g2, options[i], i, screenHeight/2, selected);
        }

        g2.dispose();
    }

    private void drawShadowText(Graphics2D g2, String text, int x, int y, Color main, Color shadow) {
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int drawX = x - textWidth / 2;

        g2.setColor(shadow);
        g2.drawString(text, drawX + 4, y + 4); // sombra
        g2.setColor(main);
        g2.drawString(text, drawX, y);
    }

    private void drawMenuOption(Graphics2D g2, String text, int index, int baseY, boolean selected) {
        int y = baseY + index * 60;
        int textWidth = g2.getFontMetrics().stringWidth(text);
        int x = (screenWidth - textWidth) / 2;

        if (selected) {
            // efecto brillante o resaltado
            g2.setColor(new Color(255, 255, 100));
            g2.drawString("> " + text + " <", x - 30, y);
        } else {
            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);
        }
    }
}
