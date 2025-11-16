package GameDesing.Graphics;

import Persons.Trainer; // Importar Trainer
import Pokemons.Pokemon; // Importar Pokemon
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;


public class UI{
    
    TraderPanel tp;
    BattlePanel bp;
    StartPanel sp;
    NurseJoyPanel njp; // Añadir referencia a NurseJoyPanel
    Font mainFont, smallFont;
    
    
    public UI(StartPanel sp){
        this.sp = sp;
        this.mainFont = new Font("Arial", Font.BOLD, 22);
        this.smallFont = new Font("Consolas", Font.PLAIN, 16);
    }
    
    public UI(TraderPanel tp){
        this.tp = tp;
        try {
            // Fuentes
            InputStream fontStream = getClass().getResourceAsStream("/fonts/Pokemon Classic.ttf");
            this.mainFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(20f);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error cargando recursos.");
            mainFont = new Font("Arial", Font.PLAIN, 36);
        }
    }
    
    public UI(BattlePanel bp) {
        this.bp = bp;
        try {
            // Fuentes
            InputStream fontStream = getClass().getResourceAsStream("/fonts/Pokemon Classic.ttf");
            this.mainFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(18f);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error cargando recursos.");
            mainFont = new Font("Arial", Font.PLAIN, 36);
        }
    }
    
    // NUEVO CONSTRUCTOR PARA NURSEJOYPANEL
    public UI(NurseJoyPanel njp) {
        this.njp = njp;
        try {
            // Fuentes
            InputStream fontStream = getClass().getResourceAsStream("/fonts/Pokemon Classic.ttf");
            this.mainFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(16f); // Tamaño base 16
            this.smallFont = mainFont.deriveFont(12f); // Tamaño pequeño 12

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error cargando recursos.");
            mainFont = new Font("Arial", Font.PLAIN, 16);
            smallFont = new Font("Arial", Font.PLAIN, 12);
        }
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
    
    
    //TRADER UI COMPLETE
    Color windowBgColor = new Color(255, 255, 200, 220); // Amarillo pálido casi opaco
    Color windowBorderColor = new Color(255, 255, 180); // Borde amarillo más brillante
    Color windowTextColor = new Color(75, 65, 22);
    
    public void drawTraderBox(Graphics2D g2){
        int x=tp.screenWidth/2-40;
        int y=tp.tileSize/2;
        int width=x+65;
        int height=tp.tileSize*6;
        
        
        //FONDO VENTANA
        Color c=new Color(255, 255, 200, 205);
        g2.setColor(c);
        g2.fillRoundRect(x,y, width, height, 20, 20);
        
        //BORDE
        c=new Color(255,255,180);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x,y, width, height, 20, 20);
    }
    
     public void drawItemInfoBox(Graphics2D g2, String itemInfo, String itemDescription, BufferedImage itemImage) {
        // Posición y tamaño centrados
        int width = tp.screenWidth / 2;
        int height = tp.screenHeight / 3;
        int x = (tp.screenWidth - width) / 2;
        int y = (tp.screenHeight - height) / 2;

        //CAJITA
        Color c = new Color(50, 50, 50, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 20, 20);
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x, y, width, height, 20, 20);
        g2.setFont(mainFont.deriveFont(18f));
        g2.setColor(Color.WHITE);
        
        int contentX = x + 20;
        int contentY = y + 40;

        //IMAGENNN ITEM
        int imgSize = 64;
        if (itemImage != null) {
            g2.drawImage(itemImage, contentX, contentY, imgSize, imgSize, null);
        } else {
            // Placeholder si la imagen es null
            g2.setColor(Color.GRAY);
            g2.fillRect(contentX, contentY, imgSize, imgSize);
            g2.setColor(Color.WHITE);
            g2.drawString("IMG", contentX + 15, contentY + 40);
        }

        // 2. Nombre del Item
        int textX = contentX + imgSize + 20;
        g2.setFont(mainFont.deriveFont(24f));
        g2.setColor(Color.YELLOW);
        g2.drawString(itemInfo, textX, contentY + 35);

        // 3. Descripción
        g2.setFont(mainFont.deriveFont(16f));
        g2.setColor(Color.WHITE);
        // Usamos una función simple para saltos de línea (ver abajo)
        drawWrappedString(g2, itemDescription, textX, contentY + 65, width - (imgSize + 50));
        
        // Reset stroke
        g2.setStroke(new BasicStroke(1)); 
    }

    
    public void drawTraderDialogBox(Graphics2D g2, String dialog) {
 
        int boxHeight = 115;
        int y = tp.screenHeight - boxHeight;
        int x = 10; 
        int width = tp.screenWidth / 2 + 80; 
        int height = boxHeight - 5;

        // BOX
        g2.setColor(windowBgColor);
        g2.fillRoundRect(x, y, width, height, 30, 30);
        g2.setColor(windowBorderColor); // BORDE
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(x, y, width, height, 30, 30);

        // TEXT
        g2.setColor(windowTextColor);
        g2.setFont(mainFont.deriveFont(18f));
        drawWrappedString(g2, dialog, x + 25, y + 30, width - 40);
        g2.setStroke(new BasicStroke(1)); 
    }

    //TRAINERS BOXES
    public void drawTrainerInfoBoxes(Graphics2D g2, int gameState, BufferedImage img1, BufferedImage img2,String trainer1,String trainer2) {
        int boxWidth = 250;
        int boxHeight = 50;
        int spacing = 8;
        int startX = tp.screenWidth - boxWidth - 15; // Margen derecho
        
        // Posición Y de la primera caja (T1)
        int y1 = tp.screenHeight - boxHeight * 2 - spacing - 10; // Arriba
        // Posición Y de la segunda caja (T2)
        int y2 = tp.screenHeight - boxHeight - 10; // Abajo
        
        // Dibujar ambas cajas
        drawSingleTrainerBox(g2, startX, y1, trainer1, img1, gameState == tp.t1State);
        drawSingleTrainerBox(g2, startX, y2, trainer2, img2, gameState == tp.t2State);
    }

    //TRAINER-INDIVIDUAL-BOX
    private void drawSingleTrainerBox(Graphics2D g2, int x, int y, String name, BufferedImage img, boolean isSelected) {
        // Fondo
        g2.setColor(windowBgColor);
        g2.fillRoundRect(x, y, 250, 50, 20, 20);

        // BORDE-TURNO
        if (isSelected) {
            g2.setColor(Color.YELLOW); 
            g2.setStroke(new BasicStroke(4));
        } else {
            g2.setColor(windowBorderColor);
            g2.setStroke(new BasicStroke(3));
        }
        g2.drawRoundRect(x, y, 250, 50, 20, 20);

        // Imagen (Placeholder)
        int imgSize = 40;
        int imgX = x + 5;
        int imgY = y + 5;
        if (img != null) {
            g2.drawImage(img, imgX, imgY, imgSize, imgSize, null);
        } else {
            g2.setColor(Color.GRAY);
            g2.fillRoundRect(imgX, imgY, imgSize, imgSize, 10, 10);
            g2.setColor(windowTextColor);
            g2.setFont(mainFont.deriveFont(10f));
            g2.drawString("IMG", imgX + 10, imgY + 25);
        }

        // Nombre
        g2.setColor(windowTextColor);
        g2.setFont(mainFont.deriveFont(16f));
        g2.drawString(name, x + imgSize + 15, y + 32);
        g2.setStroke(new BasicStroke(1)); 
    }
    
    // Método 'drawWrappedString' reutilizado (ya existe)
    private void drawWrappedString(Graphics2D g2, String s, int x, int y, int wrapWidth) {
        FontMetrics fm = g2.getFontMetrics();
        String[] words = s.split(" ");
        String line = "";
        int lineHeight = fm.getHeight();

        for (String word : words) {
            if (fm.stringWidth(line + word + " ") < wrapWidth) {
                line += word + " ";
            } else {
                g2.drawString(line, x, y);
                y += lineHeight;
                line = word + " ";
            }
        }
        g2.drawString(line, x, y); // Dibujar la última línea
    }
    
    
    
    
    
    
    
    
    //BATTLE UI INCOMPLETE
    public void draw(Graphics2D g2) throws IOException {
    drawEnemyUI(g2);
    drawPlayerUI(g2);
    }

    int textX, textY, textW, textH;
    int optX, optY, optW, optH;
    Color windowTextColor2 = new Color(61, 58, 75);
    Color windowBorderColor2 = new Color(124, 122, 133);
    Color windowFillColor2 = new Color(250, 250, 250);
    
    private void drawEnemyUI(Graphics2D g2) throws IOException {

        int boxX = 0;
        int boxY = 40;
        
        BufferedImage box=ImageIO.read(getClass().getResourceAsStream("/util/bar2.png"));
        g2.drawImage(box, boxX,boxY,122*3,34*3,null);
        g2.setFont(mainFont);
        g2.setColor(new Color(66, 66, 66));
        g2.drawString("Pikachu ♂ Lv12", boxX+15, boxY + 40);

        // Barra HP
        g2.drawRect(boxX + 15, boxY + 50, 200, 10);
        g2.setColor(new Color(0, 200, 0));
        g2.fillRect(boxX + 15, boxY + 50, 150, 10);
        
        g2.setFont(smallFont);
        g2.setColor(Color.BLACK);
        g2.drawString("HP: 25 / 27", boxX + 15, boxY + 80);
    }

   
    private void drawPlayerUI(Graphics2D g2) throws IOException {

        int boxX = bp.getWidth()-122*3;
        int boxY = bp.getHeight()/2-50;

        BufferedImage box=ImageIO.read(getClass().getResourceAsStream("/util/bar1.png"));
        g2.drawImage(box, boxX,boxY,122*3,34*3,null);
        g2.setFont(mainFont);
        g2.setColor(new Color(66, 66, 66));
        g2.drawString("Charles ♂ Lv9", boxX + 55, boxY + 40);

        // HP bar
        g2.drawRect(boxX + 55, boxY + 50, 200, 10);
        g2.setColor(new Color(0, 200, 0));
        g2.fillRect(boxX + 55, boxY + 50, 180, 10);

        g2.setFont(smallFont);
        g2.setColor(Color.BLACK);
        g2.drawString("HP: 25 / 27", boxX + 55, boxY + 80);
    }

    public void drawBattleLayout(Graphics2D g2, int panelWidth, int panelHeight, BufferedImage battleBg) {

        // 1. DIBUJAR FONDO
        int bgOriginalW = battleBg.getWidth();
        int bgOriginalH = battleBg.getHeight();
        double ratio = (double) bgOriginalW / bgOriginalH;
        int bgWidth = panelWidth;
        int bgHeight = (int) (bgWidth / ratio);
        g2.drawImage(battleBg, 0, 0, bgWidth, bgHeight, null);

        // 2. CUADRO GRANDE INFERIOR
        int bigBoxY = bgHeight;
        int bigBoxHeight = panelHeight - bgHeight;
        g2.setColor(new Color(31, 29, 42));
        g2.fillRect(0, bigBoxY, panelWidth, bigBoxHeight);
        g2.setColor(Color.BLACK);
        g2.drawRect(0, bigBoxY, panelWidth, bigBoxHeight);

        // 3. CUADRO INTERNO
        int innerX = 10;
        int innerY = bigBoxY + 10;
        int innerW = panelWidth - 20;
        int innerH = bigBoxHeight - 20;
        g2.setColor(new Color(61, 58, 75));
        g2.fillRoundRect(innerX, innerY, innerW, innerH, 20, 20);
        g2.setColor(windowBgColor);
        g2.drawRoundRect(innerX, innerY, innerW + 5, innerH + 5, 20, 20);

        // 4. SUBDIVISIÓN
        int boxMargin = 10;
        int half = innerW / 2;

        // --------------------------
        // TEXTO (izquierda) - Dibuja la caja vacía
        // --------------------------
        textX = innerX + boxMargin;
        textY = innerY + boxMargin;
        textW = (half - (boxMargin * 2)) + 50;
        textH = innerH - (boxMargin * 2);
        
        g2.setColor(windowFillColor2);
        g2.fillRoundRect(textX, textY, textW, textH, 15, 15);
        g2.setColor(windowBorderColor2);
        g2.drawRoundRect(textX, textY, textW, textH, 15, 15);

        // --------------------------
        // OPCIONES (derecha) - Dibuja la caja vacía
        // --------------------------
        optX = innerX + half + boxMargin + 40;
        optY = innerY + boxMargin;
        optW = (half - (boxMargin * 2)) - 40;
        optH = innerH - (boxMargin * 2);

        g2.setColor(windowFillColor2);
        g2.fillRoundRect(optX, optY, optW, optH, 15, 15);
        g2.setColor(windowBorderColor2);
        g2.drawRoundRect(optX, optY, optW, optH, 15, 15);
    }

  
    public void drawMessage(Graphics2D g2, String text) {
        if (text == null) text = "";
        
        g2.setColor(windowTextColor2);
        g2.setFont(mainFont);
        
        // Dibuja el texto con saltos de línea (wrapper)
        FontMetrics fm = g2.getFontMetrics();
        String[] words = text.split(" ");
        String line = "";
        int x = textX + 20; // Coordenada X (ya calculada)
        int y = textY + 40; // Coordenada Y (ya calculada)
        int wrapWidth = textW - 40; // Ancho de la caja (ya calculado)
        int lineHeight = fm.getHeight();

        for (String word : words) {
            if (fm.stringWidth(line + word + " ") < wrapWidth) {
                line += word + " ";
            } else {
                g2.drawString(line, x, y);
                y += lineHeight;
                line = word + " ";
            }
        }
        g2.drawString(line, x, y);
    }

   
    public void drawBattleOptions(Graphics2D g2, String[] options, int selectedOption) {
        if (options == null) return; // No dibujar si no hay opciones

        int x = optX + 20; // Coordenada X (ya calculada)
        int y = optY + 40; // Coordenada Y (ya calculada)
        int itemHeight = 20; // Espacio entre ítems

        g2.setFont(mainFont.deriveFont(17f));

        for (int i = 0; i < options.length; i++) {
            if (options[i] == null) continue;
            
            int currentY = y + (i * itemHeight);
            
            if (i == selectedOption) {
                // Dibujar cursor (flecha roja)
                g2.setColor(Color.RED);
                int[] xPoints = {x - 25, x - 10, x - 25};
                int[] yPoints = {currentY - 18, currentY - 8, currentY + 2};
                g2.fillPolygon(xPoints, yPoints, 3);
                
                g2.setColor(new Color(194, 54, 22)); // Color de texto seleccionado
                g2.drawString(options[i], x, currentY);
            } else {
                g2.setColor(windowTextColor2); // Color de texto normal
                g2.drawString(options[i], x, currentY);
            }
        }
    }
    
    // --- MÉTODOS DE DIBUJO PARA NURSEJOYPANEL ---
    
    /**
     * Dibuja el cuadro de diálogo principal de la Enfermera Joy.
     */
    public void drawNurseDialog(Graphics2D g2, String message) {
        int boxX = 10;
        int boxHeight = 100;
        int boxY = njp.screenHeight - boxHeight - 10;
        int boxWidth = njp.screenWidth - 20;

        // Sombra
        g2.setColor(new Color(0, 0, 0, 100));
        g2.fillRoundRect(boxX + 5, boxY + 5, boxWidth, boxHeight, 25, 25);
        
        // Caja
        g2.setColor(new Color(255, 220, 220)); // Rosa pálido
        g2.fillRoundRect(boxX, boxY, boxWidth, boxHeight, 25, 25);
        
        // Borde
        g2.setColor(new Color(220, 100, 100)); // Rosa más oscuro
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(boxX, boxY, boxWidth, boxHeight, 25, 25);
        
        // Texto
        g2.setStroke(new BasicStroke(1));
        g2.setColor(new Color(60, 60, 60)); // Texto oscuro
        g2.setFont(mainFont.deriveFont(18f));
        drawWrappedString(g2, message, boxX + 20, boxY + 30, boxWidth - 40);
    }
    
    /**
     * Dibuja el panel de un entrenador individual para la curación.
     */
    public void drawTrainerHealBox(Graphics2D g2, Trainer trainer, int x, int y, int width, int height, boolean isActive, int selectedPokemon) {
        
        // Color de borde activo/inactivo
        if (isActive) {
            g2.setColor(Color.YELLOW);
            g2.setStroke(new BasicStroke(4));
        } else {
            g2.setColor(new Color(190, 160, 90)); // Dorado oscuro
            g2.setStroke(new BasicStroke(2));
        }

        // Sombra y Caja
        g2.setColor(new Color(0, 0, 0, 70));
        g2.fillRoundRect(x + 3, y + 3, width, height, 20, 20);
        g2.setColor(windowBgColor); // Reusar color amarillo pálido de Trader
        g2.fillRoundRect(x, y, width, height, 20, 20);
        
        // Borde (dibujado después del relleno)
        if (isActive) g2.setColor(Color.YELLOW); else g2.setColor(new Color(190, 160, 90));
        g2.drawRoundRect(x, y, width, height, 20, 20);

        // --- Contenido de la Caja ---
        g2.setStroke(new BasicStroke(1));
        int padding = 15;
        int currentY = y + padding;

        // 1. Nombre del Entrenador
        g2.setFont(mainFont.deriveFont(18f));
        g2.setColor(windowTextColor);
        g2.drawString(trainer.getpName(), x + padding, currentY + 18);
        currentY += 30;

        // 2. Dinero del Entrenador
        g2.setFont(smallFont.deriveFont(14f));
        g2.setColor(new Color(10, 100, 30)); // Verde oscuro
        g2.drawString(trainer.getpPokeDollars() + "$", x + padding, currentY + 18);
        currentY += 35;
        
        // 3. Lista de Pokémon
        g2.setFont(mainFont.deriveFont(16f));
        int lineHeight = 22; // Espacio entre líneas de Pokémon

        for (int i = 0; i < 6; i++) {
            Pokemon pk = trainer.getPhPokemon(i);
            if (pk == null) continue; // Si no hay Pokémon en este slot

            String name = pk.getPkNickName();
            String hp = pk.getPkHp() + " / " + pk.getPkMaxHp();
            int lineY = currentY + (i * lineHeight) + 16;
            
            // Cursor
            if (isActive && i == selectedPokemon) {
                g2.setColor(Color.RED);
                g2.drawString(">", x + padding - 10, lineY);
            }

            // Color de HP (verde, amarillo, rojo)
            double hpPercent = (double) pk.getPkHp() / pk.getPkMaxHp();
            if (hpPercent > 0.5) {
                g2.setColor(windowTextColor); // Normal
            } else if (hpPercent > 0.2) {
                g2.setColor(new Color(200, 150, 0)); // Amarillo
            } else {
                g2.setColor(Color.RED); // Rojo
            }
            
            g2.drawString(name, x + padding, lineY);
            g2.drawString(hp, x + width - padding - g2.getFontMetrics().stringWidth(hp), lineY);
        }
    }
}
