package GameDesing.Graphics;

import GameDesing.Game;
import Pokemons.Logic.Items.Item; // ¡Importación corregida!
import Persons.Trader;
import Persons.Trainer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList; // ¡Importación añadida!
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
    private final long DELAY = 3000; // 3 segundos

    // --- LÓGICA DE DRAFT DE ITEMS (MODIFICADA) ---
    private ArrayList<Item> availableItems; // ¡NUEVO! Lista de objetos Item
    String[] itemNames; 
    String[] itemDescriptions; 
    BufferedImage[] itemIMG;
    
    // Estos contadores están perfectos
    String[] trainer1Items = new String[4];
    String[] trainer2Items = new String[4];
    int t1ItemIndex = 0;
    int t2ItemIndex = 0;
    String traderDialog = "¡Bienvenido! Elige 4 items, " + "Player 1."; // Diálogo inicial

    // GAME STATES
    public int gameState;
    public final int t1State = 1;
    public final int t2State = 2;
    public final int infoState = 3; // Estado para mostrar mensaje final antes de la transición

    public TraderPanel(Game game) {
        this.game = game;
        this.trainer1 = game.getgTrainer1();
        this.trainer2 = game.getgTrainer2();
        this.trader = game.getgTrader();
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.loadResources();

        // --- LÓGICA DE CARGA DE ITEMS (MODIFICADA) ---
        
        // 1. Obtenemos la lista de OBJETOS Item desde el Trader
        this.availableItems = trader.getAvailableItems(); 
        
        // 2. Llenamos los arrays de strings e imágenes basados en esta lista
        this.itemNames = new String[availableItems.size()];
        this.itemDescriptions = new String[availableItems.size()];
        this.itemIMG = new BufferedImage[availableItems.size()];

        for (int i = 0; i < availableItems.size(); i++) {
            Item item = availableItems.get(i);
            if (item != null) {
                this.itemNames[i] = item.getItName();
                this.itemDescriptions[i] = item.getItDescription();
                this.itemIMG[i] = item.getImg();
            }
        }
        // --- FIN DE LA LÓGICA MODIFICADA ---
        
        // Actualiza el diálogo inicial con el nombre real del Trainer 1
        this.traderDialog = "¡Bienvenido! Elige 4 items, " + trainer1.getpName() + ".";
        gameState = t1State;
    }    
    
    String t1Index,t2Index;

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void loadResources() {
        try {
            t1Index = trainer1.num;
            t2Index = trainer2.num;
            
            // Carga de imágenes de entrenadores (asegúrate que .num esté asignado)
            if (t1Index != null)
                t1 = ImageIO.read(getClass().getResourceAsStream("/util/"+t1Index+".png")); 
            if (t2Index != null)
                t2 = ImageIO.read(getClass().getResourceAsStream("/util/"+t2Index+".png")); 
            
            background = ImageIO.read(getClass().getResourceAsStream("/images/traderBG.png")); 

            InputStream fontStream = getClass().getResourceAsStream("/fonts/Pokemon Classic.ttf");
            menuFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(20f);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error cargando recursos.");
            menuFont = new Font("Arial", Font.PLAIN, 36);
        }
    }

    // --- MÉTODO UPDATE (LÓGICA DE DRAFT CORREGIDA) ---
    public void update() {
        // Si estamos en el estado final, solo esperamos la transición
        if (gameState == infoState) {
            if (battleReady && System.currentTimeMillis() - battleStartTime >= DELAY) {
                startBattleTransition();
            }
            return; // No procesar más teclas
        }

        // Navegación
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
        
        // Salir
        if (keyH.escPressed) {
            // Esto se saltará la batalla, pero mantenemos tu lógica
            startBattleTransition(); 
            keyH.escPressed = false; 
        }

        // --- LÓGICA DE ENTER (SELECCIÓN DE DRAFT) ---
        if (keyH.enterPressed) {
            
            // 1. Obtener el OBJETO Item seleccionado (no solo el nombre)
            Item selectedItem = availableItems.get(selectedOption);
            
            // 2. Lógica de draft para Trainer 1
            if (gameState == t1State) {
                if (t1ItemIndex < trainer1Items.length) {
                    
                    trainer1Items[t1ItemIndex] = selectedItem.getItName();
                    trainer1.addtItem(selectedItem); // Añade el item al inventario real del trainer
                    t1ItemIndex++;
                    traderDialog = "¡"+trainer1.getpName()+ " eligió " + selectedItem.getItName() + "!";
                    
                    // Si T1 terminó, pasa a T2
                    if (t1ItemIndex == trainer1Items.length) {
                        gameState = t2State;
                        traderDialog = "¡Turno de " + trainer2.getpName()+ "! Elige tus 4 items.";
                        selectedOption = 0; // Resetea el cursor para T2
                    }
                }
            } 
            // 3. Lógica de draft para Trainer 2
            else if (gameState == t2State) {
                if (t2ItemIndex < trainer2Items.length) {
                    
                    trainer2Items[t2ItemIndex] = selectedItem.getItName();
                    trainer2.addtItem(selectedItem); // Añade el item al inventario real del trainer
                    t2ItemIndex++;
                    traderDialog ="¡"+trainer2.getpName()+ " eligió " + selectedItem.getItName() + "!";
                    
                    // Si T2 terminó, la compra finaliza
                    if (t2ItemIndex == trainer2Items.length) {
                        gameState = infoState; // Cambia a estado "finalizado"
                        traderDialog = "¡Selección completa! ¡Listos para la batalla!";
                        
                        // Prepara la transición automática a la batalla
                        battleReady = true;
                        battleStartTime = System.currentTimeMillis();
                        System.out.println("¡Items seleccionados! Iniciando batalla en 3 segundos...");
                    }
                }
            }
            
            keyH.enterPressed = false;
        }
    }
    
    private void startBattleTransition() {
        if (gameThread != null) {
            gameThread.interrupt();
            gameThread = null;
        }
        
        this.removeKeyListener(keyH);
        background = null;
        itemNames = null;
        itemDescriptions = null;
        itemIMG = null;
        availableItems = null; // Limpia la lista de items
        trainer1Items = null;
        trainer2Items = null;
        System.out.println("Panel de compra limpiado");
        
        // Crea el BattlePanel y cambia
        GraphicPart.battlePanel = new BattlePanel(game);
        System.out.println("Cambiando a panel de Batalla...");
        GraphicPart.cambiarPanel(GraphicPart.STATE_BATTLE);
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (background != null)
            g2.drawImage(background, 0, 0, screenWidth, screenHeight, null);
        
        ui.drawTraderDialogBox(g2, traderDialog);

        ui.drawTrainerInfoBoxes(g2,gameState,t1, t2,trainer1.getpName(),trainer2.getpName());
        
        ui.drawTraderBox(g2);

        g2.setFont(menuFont.deriveFont(18f));

        int baseX = (this.screenWidth / 2 - 40) + 30; 
        int baseY = (this.tileSize / 2) + 60; 
        
        // Dibuja el menú
        for (int i = 0; i < itemNames.length; i++) {
            boolean selected = (i == selectedOption);
            drawMenuOption(g2, itemNames[i], i, baseX, baseY, selected);
        }
        
        // Dibuja el contador de items
        g2.setColor(ui.windowTextColor);
        g2.setFont(menuFont.deriveFont(12f));
        String t1Count = t1ItemIndex + "/" + trainer1Items.length;
        String t2Count = t2ItemIndex + "/" + trainer2Items.length;
        g2.drawString(t1Count, this.screenWidth - 66, this.screenHeight - 87);
        g2.drawString(t2Count, this.screenWidth - 66, this.screenHeight - 30);


        // Dibuja la info del item si se presiona ESPACIO
        if (keyH.spcPressed && gameState != infoState) {
            String info = itemNames[selectedOption];
            String desc = itemDescriptions[selectedOption];
            BufferedImage img = itemIMG[selectedOption];
            ui.drawItemInfoBox(g2, info, desc, img); // Pasamos la imagen real
        }

        g2.dispose();
    }

    private void drawMenuOption(Graphics2D g2, String text, int index, int baseX, int baseY, boolean selected) {
        int y = baseY + index * 45; 
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