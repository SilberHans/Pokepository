package GameDesing.Graphics;

import java.awt.*;

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
        drawT2UI(g2);
        drawT1UI(g2);
        drawBattleBox(g2);
    }

    private void drawT2UI(Graphics2D g2) {
        // Cuadro superior derecho (enemigo)
        g2.setColor(new Color(245, 245, 245));
        g2.fillRoundRect(480, 40, 240, 70, 15, 15);
        g2.setColor(Color.BLACK);
        g2.setFont(mainFont);
        g2.drawString("Pikachu", 495, 65);

        // HP Bar
        g2.setColor(Color.BLACK);
        g2.drawRect(495, 75, 200, 10);
        g2.setColor(new Color(0, 200, 0));
        g2.fillRect(495, 75, 120, 10); // HP actual
    }

    private void drawT1UI(Graphics2D g2) {
        // Cuadro inferior izquierdo (jugador)
        g2.setColor(new Color(245, 245, 245));
        g2.fillRoundRect(80, 330, 260, 100, 15, 15);
        g2.setColor(Color.BLACK);
        g2.setFont(mainFont);
        g2.drawString("Charles ♂ Lv9", 95, 355);

        // HP Bar
        g2.setColor(Color.BLACK);
        g2.drawRect(95, 365, 200, 10);
        g2.setColor(new Color(0, 200, 0));
        g2.fillRect(95, 365, 180, 10);

        // Texto HP
        g2.setFont(smallFont);
        g2.setColor(Color.BLACK);
        g2.drawString("HP: 25 / 27", 95, 390);
    }

    private void drawBattleBox(Graphics2D g2) {
        // Cuadro de texto principal
        g2.setColor(new Color(255, 255, 255, 230));
        g2.fillRoundRect(40, 420, 680, 130, 20, 20);
        g2.setColor(Color.RED);
        g2.drawRoundRect(40, 420, 680, 130, 20, 20);

        // Texto principal
        g2.setFont(mainFont);
        g2.drawString("HOLA QUE HACE?", 60, 450);

        // Cuadro interno de opciones
        g2.setColor(new Color(240, 240, 240));
        g2.fillRoundRect(400, 460, 300, 80, 20, 20);
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.BOLD, 20));

        g2.drawString("Scratch", 420, 490);
        g2.drawString("Growl", 420, 520);
        g2.drawString("Ember", 550, 490);
        g2.drawString("-", 550, 520);

        // Datos de PP
        g2.setFont(new Font("Consolas", Font.BOLD, 16));
        g2.drawString("PP 25/25", 600, 545);
        g2.drawString("Type/Fire", 600, 565);
    }
}
