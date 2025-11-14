
package GameDesing.Graphics;

import Persons.Trainer;
import java.util.Random;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BattlePanel extends JPanel implements Runnable{

    // SCREEN CONFIG
    final int originalTileSize = 32;
    final int scale = 2;
    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 12;
    final int maxScreenRow = 9;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    // SYSTEM
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Random random = new Random();

    // Trainers ahora se crean después del constructor
    Trainer trainer1;  // jugador
    Trainer trainer2;  // rival

    UI ui = new UI(this);
    int FPS = 60;

    BufferedImage background;

    public BattlePanel() {
        background = selectBattleBackground();
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        // ⚡ Ahora los trainers se crean después de setear el panel
        initTrainers();
    }

    private void initTrainers() {

        trainer1 = new Trainer(this, keyH, "3", true);
        trainer2 = new Trainer(this, keyH, "2", false);

        // Posiciones personalizadas
        trainer1.x = 90;
        trainer1.y = 260;

        trainer2.x = 360;
        trainer2.y = 200;
        trainer2.direction = "right";  
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
        trainer1.update();
        trainer2.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Fondo adaptado
        int newWidth = getWidth();
        double imageRatio = (double) background.getWidth() / background.getHeight();
        int newHeight = (int) (newWidth / imageRatio);
        g2.drawImage(background, 0, 0, newWidth, newHeight, null);

        // UI
        ui.draw(g2);
        ui.drawBattleLayout(g2, screenWidth, screenHeight, background);
        // Trainers
        trainer1.draw(g2);
        trainer2.draw(g2);

        g2.dispose();
    }

    private BufferedImage selectBattleBackground() {
        int x = random.nextInt(10);
        String num = Integer.toString(x);

        try {
            return ImageIO.read(getClass().getResourceAsStream("/btScreen/" + num + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}