package Persons;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import GameDesing.Graphics.*;
import java.awt.geom.AffineTransform;


import Items.Item;
import Pokemons.Pokemon;
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
            loadMoveSpritesSafe(spriteNum); 
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

    private void loadMoveSpritesSafe(String num) {
        try {
            a1 = ImageIO.read(getClass().getResourceAsStream("/trainer_move/" + num + "_1.png"));
            a2 = ImageIO.read(getClass().getResourceAsStream("/trainer_move/" + num + "_2.png"));
            a3 = ImageIO.read(getClass().getResourceAsStream("/trainer_move/" + num + "_3.png"));
            a4 = ImageIO.read(getClass().getResourceAsStream("/trainer_move/" + num + "_4.png"));
            a5 = ImageIO.read(getClass().getResourceAsStream("/trainer_move/" + num + "_5.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    // setDefault según turno (Batalla)
    public void setDefaultValues(boolean turn) {
        if (turn) {
            x = 80; y = 358; direction = "left";
        } else {
            x = 500; y = 120; direction = "right";
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

    // animación normal 
    public void epicThrow() {
        // Chequeo de seguridad
        if (keyH == null) return; 
        spriteCounter++;
            if (spriteCounter > 8) {
                spriteNum++;
                if (spriteNum > 5) spriteNum = 1;
                spriteCounter = 0;
            }
    }

    // dibujado para pk
    public void static_draw(Graphics2D g2, int drawSize) {
        BufferedImage image = (spriteNum == 2) ? a2 : a1;
        
        if (image == null) return; // No dibujar si no se cargó
        

        g2.drawImage(image, x, y, drawSize, drawSize, null);
    }

    // dibujado en batalla
    public void draw(Graphics2D g2) {
        BufferedImage image = switch (spriteNum) {
            case 1 -> a1;
            case 2 -> a2;
            case 3 -> a3;
            case 4 -> a4;
            case 5 -> a5;
            default -> a1;
        };
        if (image == null) return;

        int size = (bp != null) ? bp.tileSize : 64;
        g2.drawImage(image, x, y, size, size, null);
    }

    public void setPosition(int x, int y) { this.x = x; this.y = y; }
}