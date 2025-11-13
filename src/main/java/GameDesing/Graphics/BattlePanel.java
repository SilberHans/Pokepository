
package GameDesing.Graphics;

import Persons.Trainer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BattlePanel extends JPanel implements Runnable{
    
    //SCREEN CONFIG
    final int originalTileSize=32;
    final int scale=2;
    public final int tileSize=originalTileSize *scale;
    final int maxScreenCol=12;
    final int maxScreenRow=9;
    final int screenWidth=tileSize*maxScreenCol;
    final int screenHeight=tileSize*maxScreenRow;
    
    //SYSTEM
    KeyHandler keyH=new KeyHandler();
    Thread gameThread;
    //Trainer trainer=new Trainer(this,keyH);
    UI ui=new UI(this);
    int FPS=60;
    
    //GAME STATE
    public int gameState;
    public final int t1State=1;
    public final int t2State=2;
    public final int dialogueState=3;
    
    
    public BattlePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread=new Thread(this);
        gameThread.start();
    }
    
    
    @Override
    public void run() {
        
        double drawInterval=1000000000/FPS;
        double delta=0;
        long lastTime=System.nanoTime();
        long currentTime;
        long timer=0;
        int drawCount=0;
        
        while(gameThread != null){
            
            currentTime=System.nanoTime();
            delta+= (currentTime-lastTime)/drawInterval;
            lastTime=currentTime;
            timer+=(currentTime-lastTime);
            
            if(delta >=1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            
            if(timer >= 1000000000){
                System.out.println("FPS:"+drawCount);
                drawCount=0;
                timer=0;
            }
        }
    } 
    
    public void update(){
        //trainer.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        
        drawBattleBackground(g2);
        ui.draw(g2);
        //trainer.draw(g2);
        
        g2.dispose();
    }
    
    public BufferedImage scene;
    private void drawBattleBackground(Graphics2D g2) {
        try {
            scene=ImageIO.read(getClass().getResourceAsStream("/images/battle1.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
         int panelWidth = getWidth();
        int panelHeight = getHeight();

    // Proporción de la imagen
        double imageRatio = (double) scene.getWidth() / scene.getHeight();

    // Calculamos el nuevo tamaño
        int newWidth = panelWidth; // Queremos cubrir todo el ancho
        int newHeight = (int) (newWidth / imageRatio);

    // Centramos verticalmente si sobra espacio
        int y = (panelHeight - newHeight) / 2;
        
        
        g2.drawImage(scene, 0,0,newWidth,newHeight,null);
       
    }
}
 