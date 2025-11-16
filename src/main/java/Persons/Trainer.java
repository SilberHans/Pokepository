package Persons;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import GameDesing.Graphics.*;
import java.awt.geom.AffineTransform;


import Items.Item;
import Pokemons.Pokemon;
import java.awt.Point;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Trainer extends PokemonHandler {

    private int tMedals;
    private ArrayList<Item> tItemsList;


    public Trainer() {
        super();
        this.tMedals = 0;
        this.tItemsList = new ArrayList<>();
        this.keyH = null;
        this.bp = null;
        this.pSp = null;
        this.a1 = this.a2 = this.a3 = this.a4 = this.a5 = null;
    }

    public Trainer(int tMedals, ArrayList<Item> tConsuList, String pName, String pRegion, String pID, LocalDate pBirthDate, int pPokeDollars) {
        super(pName, pRegion, pID, pBirthDate, pPokeDollars);
        this.tMedals = tMedals;
        this.tItemsList = tConsuList;
    }

    
    public void settMedals(int tMedals){
        this.tMedals = tMedals;
    }
    public void settItemList(ArrayList<Item> tItemList){
        this.tItemsList = tItemList;
    }
    public void addtItem(Item tItem){
        this.tItemsList.add(tItem);
    }

    public int gettMedals(){
        return tMedals;
    }
    public ArrayList<Item> gettItemList(){
        return tItemsList;
    }
    public Item gettItem(int tItemPst){
        try{
            return this.tItemsList.get(tItemPst);
        }catch(ArrayIndexOutOfBoundsException e){
            return null;
        }
    } 
    public String gettItemListStr(){
        if(this.tItemsList.isEmpty()){
            return "No items to use!";
        }
        String str = "";
        for(Item tryItem: this.tItemsList){
            str += tryItem.toString();
        }
        return str;
    }

    @Override
    public String toString(){
        return "-----Trainer Information-----\n" + super.toString() + "\nMedals:\t" + this.gettMedals() + "\n\t-Pokemons-\n" + this.getPhPokeListStr() + "\n\t-Items-\n" +this.gettItemListStr();
    }
    
    @Override
    public String getPhPokeListStr() {
        if (super.phPokeList.isEmpty()) {
            return "No Pokémon have been assigned to this Trainer.";
        }
        String str = "";
        for (Pokemon pokemonTry : super.phPokeList) {
            str += pokemonTry.toString() + "\n"; 
        }
        return str;
    }
    
    public String[] getPokemonNames() {
        if (super.phPokeList.isEmpty()) {
            return new String[]{"No Pokémon"};
        }
        
      
        String[] pokemonNames = new String[super.phPokeList.size()];
 
        for (int i = 0; i < super.phPokeList.size(); i++) {
            pokemonNames[i] = super.phPokeList.get(i).getPkNickName();
        }
        
        return pokemonNames;
    }
    
    
    @Override
    public void genericDialogue() {
    }
    
    public void newTrainer() {
    }


    //GRAPHICSS
    
    private BattlePanel bp;
    private pkSelectorPanel pSp;
    private KeyHandler keyH;
    public String num; // "1","2","3","4"

    // Sprites
    private BufferedImage a1, a2, a3, a4, a5;

    // Posición y animación
    public int x, y;
    private int speed;
    private int spriteCounter;
    private int spriteNum;
    public String direction;


    public void initGraphics(JPanel panel, KeyHandler keyH, String spriteNum, boolean isTurn) {
       
        this.keyH = keyH;
        this.num = spriteNum;
        
        if (panel instanceof pkSelectorPanel) {
            loadStaticSpritesSafe(spriteNum);
            this.pSp = (pkSelectorPanel) panel;
            this.bp = null; // 
            setDefaultValuesPK(isTurn); 
            
        } else if (panel instanceof BattlePanel) {
            this.bp = (BattlePanel) panel;
            this.pSp = null;
            setDefaultValues(isTurn); 
        }
        
        System.out.println("Gráficos del Trainer " + num + " inicializados para " + panel.getClass().getSimpleName());
    }


    private void loadStaticSpritesSafe(String num) {
        try {
            int id = safeParse(num, 1);
            switch (id) {
                case 1 -> {
                    a1 = ImageIO.read(getClass().getResourceAsStream("/trainer_static/1_1.png"));
                    a2 = ImageIO.read(getClass().getResourceAsStream("/trainer_static/1_2.png"));
                }
                case 2 -> {
                    a1 = ImageIO.read(getClass().getResourceAsStream("/trainer_static/2_1.png"));
                    a2 = ImageIO.read(getClass().getResourceAsStream("/trainer_static/2_2.png"));
                }
                case 3 -> {
                    a1 = ImageIO.read(getClass().getResourceAsStream("/trainer_static/3.png"));
                    a2 = ImageIO.read(getClass().getResourceAsStream("/trainer_static/3.png"));
                }
                case 4 -> {
                    a1 = ImageIO.read(getClass().getResourceAsStream("/trainer_static/4_1.png"));
                    a2 = ImageIO.read(getClass().getResourceAsStream("/trainer_static/4_2.png"));
                }
                default -> {
                    a1 = ImageIO.read(getClass().getResourceAsStream("/trainer_static/" + num + ".png"));
                    a2 = a1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            a1 = a2 = null;
        }
    }

    public static BufferedImage flipImageHorizontally(BufferedImage original) {
        if (original == null) return null; // Chequeo de seguridad
        int width = original.getWidth();
        int height = original.getHeight();
        BufferedImage flipped = new BufferedImage(width, height, original.getType());
        Graphics2D g = flipped.createGraphics();
        g.transform(AffineTransform.getTranslateInstance(width, 0));
        g.transform(AffineTransform.getScaleInstance(-1, 1));
        g.drawImage(original, 0, 0, null);
        g.dispose();
        return flipped;
    }

    private int safeParse(String s, int fallback) {
        try { return Integer.parseInt(s); } catch (Exception e) { return fallback; }
    }

    // valores por defecto selectPK
    public void setDefaultValuesPK(boolean turn) {
        if (turn) {
            x = 10; y = 120; direction = "left";
        } else {
            x = 480; y = 120; direction = "right"; 
        }
        speed = 0;
        spriteCounter = 0;
        spriteNum = 1;
    }
 

    // animaciion quietos
    public void static_update() {
        spriteCounter++;
        if (spriteCounter > 20) {
            spriteNum++;
            if (spriteNum > 2) spriteNum = 1;
            spriteCounter = 0;
        }
    }
    
    
    // dibujado para pk
    public void static_draw(Graphics2D g2, int drawSize) {
        BufferedImage image = (spriteNum == 2) ? a2 : a1;
        
        if (image == null) return; // No dibujar si no se cargó
        

        g2.drawImage(image, x, y, drawSize, drawSize, null);
    }
    
    
    
    
    
    
    
    
    
    
    
    //BATTLE CONFIG
    
    
    
    
    
    
    public final int STATE_IDLE = 0;
    public final int STATE_THROWING = 1;
    public final int STATE_THROW_COMPLETE = 2;
    
    private int trainerState = STATE_IDLE;
    private int throwProgress = 0; // 0 a 100
    private BufferedImage[] sprites = new BufferedImage[5]; // a1, a2, a3, a4, a5
    private boolean spritesLoaded = false;
    private int battleSpriteSize;
    private int targetX; 
    private int targetY; 
    private int initialX;
    private int initialY;
    private boolean isExiting = false;
    private float exitProgress = 0f; 
    
    public void startThrowAnimation(boolean isPlayerOne) {
        trainerState = STATE_THROWING;
        spriteNum = 1;
        spriteCounter = 0;
        throwProgress = 0;
        exitProgress = 0f;
        
        // Guardar posición inicial
        initialX = this.x;
        initialY = this.y;
        
        if (isPlayerOne) {
            targetX = -200;
            targetY = this.y - 100;
        } else {
            targetX = bp.getWidth() + 200;
            targetY = this.y - 100;
        }
    }
    
    private void updateThrowAnimation() {
        if (trainerState != STATE_THROWING) return;
        
        // Animación de lanzamiento
        spriteCounter++;
        if (spriteCounter > 6) {
            spriteNum++;
            if (spriteNum > 5) {
                spriteNum = 5;
                isExiting = true;
            }
            spriteCounter = 0;
        }
        
        throwProgress += 4;
        if (throwProgress > 100) throwProgress = 100;
        
        // Movimiento suave de salida
        if (isExiting) {
            exitProgress += 0.05f; // Velocidad de salida
            if (exitProgress > 1.0f) exitProgress = 1.0f;
            
            // Interpolación suave entre posición inicial y final
            this.x = (int) (initialX + (targetX - initialX) * exitProgress);
            this.y = (int) (initialY + (targetY - initialY) * exitProgress);
            
            if (exitProgress >= 1.0f) {
                trainerState = STATE_THROW_COMPLETE;
            }
        }
    }

    public boolean isFullyExited(boolean isPlayerOne) {
        if (isPlayerOne) {
            return this.x <= -100; // Completamente fuera por izquierda
        } else {
            return this.x >= (bp.getWidth() + 10); // Completamente fuera por derecha
        }
    }
    
    /**
     * Actualiza el estado del trainer (animaciones, etc.)
     */
    public void update() {
        if (trainerState == STATE_THROWING) {
            updateThrowAnimation();
        }
        // ... resto de tu lógica de update existente ...
    }
    
    /**
     * Verifica si la animación de lanzamiento ha terminado
     */
    public boolean isThrowComplete() {
        return trainerState == STATE_THROW_COMPLETE;
    }

    /**
     * Reinicia el estado del trainer a idle
     */
    public void resetState() {
        trainerState = STATE_IDLE;
        throwProgress = 0;
        spriteNum = 1;
        spriteCounter = 0;
    }

    /**
     * Obtiene la posición de la pokeball según el progreso del lanzamiento
     */
    public Point getBallPosition(boolean isPlayerOne) {
        int ballX, ballY;
        
        if (isPlayerOne) {
            // Para trainer 1 (izquierda): la pokeball se aleja hacia la derecha
            ballX = x + 30 + (throwProgress * 3); // Se mueve hacia la derecha
            ballY = y + 15 - (throwProgress / 3); // Sube ligeramente
        } else {
            // Para trainer 2 (derecha): la pokeball se aleja hacia la izquierda  
            ballX = x - 30 - (throwProgress * 3); // Se mueve hacia la izquierda
            ballY = y + 15 - (throwProgress / 3); // Sube ligeramente
        }
        
        return new Point(ballX, ballY);
    }

    /**
     * Obtiene el tamaño de la pokeball (se hace más pequeña al alejarse)
     */
    public int getBallSize() {
        return 42 - (throwProgress / 4); // De 32 a ~7 pixels
    }

    // ========== MÉTODOS DE CARGA DE SPRITES ==========

    /**
     * Carga los sprites de movimiento del trainer
     * @param num Número del trainer (para la ruta del archivo)
     * @param isPlayerOne Si es true, carga sprites normales; si es false, los voltea
     */
    public void loadMoveSpritesSafe(String num, boolean isPlayerOne) {
        try {
            for (int i = 0; i < 5; i++) {
                BufferedImage original = ImageIO.read(getClass().getResourceAsStream(
                    "/trainer_move/" + num + "_" + (i + 1) + ".png"));
                
                if (original != null) {
                    // Si es trainer 2, voltear la imagen
                    if (!isPlayerOne) {
                        sprites[i] = flipImageHorizontally(original);
                    } else {
                        sprites[i] = original;
                    }
                }
            }
            spritesLoaded = true;
            System.out.println("Sprites cargados para trainer " + num + " (volteado: " + !isPlayerOne + ")");
        } catch (IOException e) {
            e.printStackTrace();
            spritesLoaded = false;
            System.out.println("Error cargando sprites para trainer " + num);
        }
    }
    // ========== MÉTODO DRAW ACTUALIZADO ==========

    /**
     * Dibuja el trainer con el sprite actual
     */
    public void draw(Graphics2D g2) {
        if (!spritesLoaded || spriteNum < 1 || spriteNum > 5) return;
        
        BufferedImage image = sprites[spriteNum - 1];
        if (image == null) return;
        g2.drawImage(image, x, y, battleSpriteSize , battleSpriteSize, null);
    }

    
    // ========== MÉTODOS AUXILIARES ==========

    /**
     * Obtiene el estado actual del trainer (para debug)
     */
    public String getStateString() {
        switch (trainerState) {
            case STATE_IDLE: return "IDLE";
            case STATE_THROWING: return "THROWING";
            case STATE_THROW_COMPLETE: return "THROW_COMPLETE";
            default: return "UNKNOWN";
        }
    }

    /**
     * Obtiene el progreso del lanzamiento (para debug)
     */
    public int getThrowProgress() {
        return throwProgress;
    }
    
    
    


    
    // setDefault según turno (Batalla)
    public void setDefaultValues(boolean turn) {
        if (turn) {
            x = 90; y = 159 ; direction = "left";
        } else {
            x = 500; y = 60; direction = "right";
        }
        battleSpriteSize = 200; 
        speed = 0;
        spriteCounter = 0;
        spriteNum = 1;
        
    }
}

