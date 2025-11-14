package GameDesing.Graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI{
    
    TraderPanel tp;
    BattlePanel bp;
    StartPanel sp;
    Font mainFont, smallFont;
    
    
    public UI(StartPanel sp){
        this.sp=sp;
        this.mainFont = new Font("Arial", Font.BOLD, 22);
        this.smallFont = new Font("Consolas", Font.PLAIN, 16);
    }
    
    public UI(TraderPanel tp){
        this.tp=tp;
        this.mainFont = new Font("Arial", Font.BOLD, 22);
        this.smallFont = new Font("Consolas", Font.PLAIN, 16);
    }
    
    public UI(BattlePanel bp) {
        this.bp = bp;
        this.mainFont = new Font("Arial", Font.BOLD, 22);
        this.smallFont = new Font("Consolas", Font.PLAIN, 16);
    }
    
    public void drawInfoBox(Graphics2D g2){
        int x=tp.tileSize*2;
        int y=tp.tileSize/2;
        int width=tp.screenWidth-(tp.tileSize*4);
        int height=tp.tileSize*4;
        
        Color c=new Color(105,105,105,215);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
    }
    
    public void drawInfoBox(Graphics2D g2,boolean hola){
        int width=300;
        int height=250;
        int x=(sp.screenWidth-width)/2;
        int y=(sp.screenHeight-height)/2+50;

        Color c=new Color(105,105,105,215);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
    }
    
    public void drawTraderBox(Graphics2D g2){
        int x=tp.screenWidth/2;
        int y=tp.tileSize/2;
        int width=x-36;
        int height=tp.tileSize*6;
        
        
        //FONDO VENTANA
        Color c=new Color(255, 255, 200, 200);
        g2.setColor(c);
        g2.fillRoundRect(x,y, width, height, 20, 20);
        
        //BORDE
        c=new Color(255,255,180);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x,y, width, height, 20, 20);
        
        

        // Texto principal
        //g2.setFont(smallFont);
        //g2.drawString("Sales",350,90);
    }
    
    public void draw(Graphics2D g2) {
    drawEnemyUI(g2);
    drawPlayerUI(g2);
    }

    
    private void drawEnemyUI(Graphics2D g2) {

        int boxX = 430;
        int boxY = 40;

        // Caja con borde oscuro (HG/SS style)
        g2.setColor(new Color(255, 255, 255));
        g2.fillRoundRect(boxX, boxY, 250, 80, 15, 15);

        g2.setColor(Color.BLACK);
        g2.drawRoundRect(boxX, boxY, 250, 80, 15, 15);

        g2.setFont(mainFont);
        g2.drawString("Pikachu", boxX + 15, boxY + 30);

        // Barra HP
        g2.drawRect(boxX + 15, boxY + 40, 200, 10);
        g2.setColor(new Color(0, 200, 0));
        g2.fillRect(boxX + 15, boxY + 40, 150, 10);
    }

   
    private void drawPlayerUI(Graphics2D g2) {

        int boxX = 80;
        int boxY = 300;

        g2.setColor(new Color(255, 255, 255));
        g2.fillRoundRect(boxX, boxY, 260, 100, 15, 15);

        g2.setColor(Color.BLACK);
        g2.drawRoundRect(boxX, boxY, 260, 100, 15, 15);

        g2.setFont(mainFont);
        g2.drawString("Charles ♂ Lv9", boxX + 15, boxY + 25);

        // HP bar
        g2.drawRect(boxX + 15, boxY + 35, 200, 10);
        g2.setColor(new Color(0, 200, 0));
        g2.fillRect(boxX + 15, boxY + 35, 180, 10);

        g2.setFont(smallFont);
        g2.setColor(Color.BLACK);
        g2.drawString("HP: 25 / 27", boxX + 15, boxY + 60);
    }

    public void drawBattleLayout(Graphics2D g2, int panelWidth, int panelHeight, BufferedImage battleBg) {

       // =========================
       // 1. DIBUJAR FONDO ESCALADO
       // =========================

       int bgOriginalW = battleBg.getWidth();
       int bgOriginalH = battleBg.getHeight();

       double ratio = (double) bgOriginalW / bgOriginalH;
       int bgWidth = panelWidth;                 // 768
       int bgHeight = (int) (bgWidth / ratio);   // 358 (exacto)

       g2.drawImage(battleBg, 0, 0, bgWidth, bgHeight, null);


       // =========================
       // 2. CUADRO GRANDE INFERIOR
       // =========================

       int bigBoxY = bgHeight;     // 358
       int bigBoxHeight = panelHeight - bgHeight;  // 218

       g2.setColor(new Color(255, 255, 255));
       g2.fillRect(0, bigBoxY, panelWidth, bigBoxHeight);

       g2.setColor(Color.BLACK);
       g2.drawRect(0, bigBoxY, panelWidth, bigBoxHeight);


       // =========================
       // 3. CUADRO INTERNO
       // =========================

       int innerX = 20;
       int innerY = bigBoxY + 20;
       int innerW = panelWidth - 40;
       int innerH = bigBoxHeight - 40;

       g2.setColor(new Color(230, 230, 230));
       g2.fillRoundRect(innerX, innerY, innerW, innerH, 20, 20);

       g2.setColor(Color.GRAY);
       g2.drawRoundRect(innerX, innerY, innerW, innerH, 20, 20);


       // =========================
       // 4. SUBDIVISIÓN: TEXTO Y OPCIONES
       // =========================

       int boxMargin = 10;
       int half = innerW / 2;

       // --------------------------
       // TEXTO (izquierda)
       // --------------------------
       int textX = innerX + boxMargin;
       int textY = innerY + boxMargin;
       int textW = half - (boxMargin * 2);
       int textH = innerH - (boxMargin * 2);

       g2.setColor(new Color(250, 250, 250));
       g2.fillRoundRect(textX, textY, textW, textH, 15, 15);

       g2.setColor(Color.DARK_GRAY);
       g2.drawRoundRect(textX, textY, textW, textH, 15, 15);

       g2.setColor(Color.GRAY);
       g2.setFont(new Font("Arial", Font.PLAIN, 28));
       g2.drawString("Texto", textX + 20, textY + 40);


       // --------------------------
       // OPCIONES (derecha)
       // --------------------------
       int optX = innerX + half + boxMargin;
       int optY = innerY + boxMargin;
       int optW = half - (boxMargin * 2);
       int optH = innerH - (boxMargin * 2);

       g2.setColor(new Color(250, 250, 250));
       g2.fillRoundRect(optX, optY, optW, optH, 15, 15);

       g2.setColor(Color.DARK_GRAY);
       g2.drawRoundRect(optX, optY, optW, optH, 15, 15);

       g2.setColor(Color.GRAY);
       g2.setFont(new Font("Arial", Font.PLAIN, 28));
       g2.drawString("Opciones", optX + 20, optY + 40);
   }
   
}
