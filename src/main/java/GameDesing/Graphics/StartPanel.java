package GameDesing.Graphics;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class StartPanel extends JPanel implements Runnable {

    // --- SCREEN CONFIG ---
    private final int originalTileSize = 32;
    private final int scale = 2;
    private final int tileSize = originalTileSize * scale;
    private final int maxScreenCol = 12;
    private final int maxScreenRow = 9;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // --- SYSTEM ---
    private KeyHandler keyH = new KeyHandler();
    private Thread gameThread;
    private final int FPS = 60;
    private UI ui = new UI(this);

    // --- OPTIONS ---
    private String[] options = {"Create", "Load Game", "Exit"};
    private int selectedOption = 0;

    // --- UTILITES ---
    private BufferedImage background;
    private Font menuFont;

    public StartPanel() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setFocusable(true);
        addKeyListener(keyH);
        loadResources();
    }

    private void loadResources() {
        try {
        
            InputStream bgStream = getClass().getResourceAsStream("/images/fond.png");
            background = ImageIO.read(bgStream);

            
            InputStream fontStream = getClass().getResourceAsStream("/fonts/Pokemon Classic.ttf");
            menuFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(24f);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("⚠️ Error cargando recursos. Usando fuente por defecto.");
            menuFont = new Font("Arial", Font.BOLD, 24);
        }
    }

    // --- MAIN THREAD ---
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();

        while (gameThread != null) {
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    // --- logicOptions ---
    private void update() {
        if (keyH.upPressed) {
            selectedOption = (selectedOption - 1 + options.length) % options.length;
            keyH.upPressed = false;
        }

        if (keyH.downPressed) {
            selectedOption = (selectedOption + 1) % options.length;
            keyH.downPressed = false;
        }

        if (keyH.enterPressed) {
            runOption();
            keyH.enterPressed = false;
        }
    }

    private void runOption() {
        switch (selectedOption) {
            case 0 -> { //Create
                System.out.println("Iniciando nueva partida...");
                GraphicPart.cambiarPanel(GraphicPart.STATE_SELECTOR);
            }
            case 1 -> System.out.println("Cargar partida (aún no implementado)");
            case 2 -> System.exit(0);
        }
    }

    // --- Drawing ---
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (background != null)
            g2.drawImage(background, 0, 0, screenWidth, screenHeight, null);

        ui.drawInfoBox(g2, true);

        g2.setFont(menuFont);

        for (int i = 0; i < options.length; i++) {
            drawMenuOption(g2, options[i], i, screenHeight / 2, i == selectedOption);
        }

        g2.dispose();
    }

    private void drawMenuOption(Graphics2D g2, String text, int index, int baseY, boolean selected) {
        int y = baseY + index * 60;
        int textWidth = g2.getFontMetrics().stringWidth(text);
        int x = (screenWidth - textWidth) / 2;

        if (selected) {
            g2.setColor(new Color(255, 255, 100));
            g2.drawString("> " + text + " <", x - 30, y);
        } else {
            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);
        }
    }
}
