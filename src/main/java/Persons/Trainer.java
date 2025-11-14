package Persons;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import GameDesing.Graphics.*;


import Items.Item;
import Pokemons.Pokemon;
import java.time.LocalDate;
import java.util.ArrayList;

public class Trainer extends PokemonHandler{
    private int tMedals;
    private ArrayList<Item> tItemsList;
 
    public Trainer(){
        super();
        this.tMedals = 0;
        this.tItemsList = new ArrayList<>();
    }
    public Trainer(int tMedals, ArrayList<Item> tConsuList, String pName, String pRegion, String pID, LocalDate pBirthDate, int pPokeDollars){
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
    public String getPhPokeListStr(){
        if(super.phPokeList.isEmpty()){
            return "No Pokémon have been assigned to this Trainer.";
        }
        String str = "";
        for(Pokemon pokemonTry: super.phPokeList){
            str = pokemonTry.toString();
        }
        return str;
    }

    @Override
    public void genericDialogue() {
    
    }
    
    public void newTrainer(){
        
    }
    
    // ----- Gráficos -----
    private BattlePanel bp = null;
    private pkSelectorPanel pSp = null;
    private KeyHandler keyH;
    public String num;          // "1","2","3","4" etc.

    // Sprites (a1..a5 para movimiento; a1,a2 usados también para la animación estática)
    private BufferedImage a1, a2, a3, a4, a5;

    // posición y animación
    public int x, y;
    private int speed;
    private int spriteCounter;
    private int spriteNum;
    public String direction;

    // constructor para BattlePanel (uso en batalla)
    public Trainer(BattlePanel bp, KeyHandler keyH, String num, boolean turn) {
        this.bp = bp;
        this.keyH = keyH;
        this.num = num;
        setDefaultValues(turn);
        loadMoveSpritesSafe(num);
        loadStaticSpritesSafe(num);
    }

    // constructor para pkSelectorPanel (uso en selección)
    public Trainer(pkSelectorPanel pSp, KeyHandler keyH, String num,boolean turn) {
        this.pSp = pSp;
        this.keyH = keyH;
        this.num = num;
        setDefaultValuesPK(turn); // no turn específico
        loadStaticSpritesSafe(num);
        // no cargamos movimiento pesado aquí (opcional)
    }

    // carga segura de sprites estáticos (a1 y a2)
    private void loadStaticSpritesSafe(String num) {
        try {
            // rutas que tienes: /trainer_static/1_1.png, /trainer_static/2_1.png etc.
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
                    // fallback: intentar cargar num directamente si tus nombres difieren
                    a1 = ImageIO.read(getClass().getResourceAsStream("/trainer_static/" + num + ".png"));
                    a2 = a1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            a1 = a2 = null;
        }
    }

    // carga segura de sprites de movimiento (a1..a5)
    private void loadMoveSpritesSafe(String num) {
        try {
            a1 = ImageIO.read(getClass().getResourceAsStream("/trainer_move/" + num + "_1.png"));
            a2 = ImageIO.read(getClass().getResourceAsStream("/trainer_move/" + num + "_2.png"));
            a3 = ImageIO.read(getClass().getResourceAsStream("/trainer_move/" + num + "_3.png"));
            a4 = ImageIO.read(getClass().getResourceAsStream("/trainer_move/" + num + "_4.png"));
            a5 = ImageIO.read(getClass().getResourceAsStream("/trainer_move/" + num + "_5.png"));
        } catch (IOException e) {
            e.printStackTrace();
            // no fatal: dejamos lo que haya
        }
    }

    // método público para cambiar skin en tiempo de ejecución
    public void changeSkin(int newNum) {
        this.num = Integer.toString(newNum);
        loadStaticSpritesSafe(this.num);
        loadMoveSpritesSafe(this.num);
    }

    // util: parse seguro
    private int safeParse(String s, int fallback) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return fallback;
        }
    }

    // valores por defecto
    public void setDefaultValuesPK(boolean turn) {
        if (turn) {
            x = 120; y = 260; direction = "left";
        } else {
            x = 480; y = 120; direction = "right";
        }
        speed = 0;
        spriteCounter = 0;
        spriteNum = 1;
    }

    // setDefault según turno (usa posiciones recomendadas)
    public void setDefaultValues(boolean turn) {
        if (turn) {
            x = 120; y = 260; direction = "left";
        } else {
            x = 480; y = 120; direction = "right";
        }
        speed = 0;
        spriteCounter = 0;
        spriteNum = 1;
    }

    // animación estática de 2 frames (se usa en selección)
    public void static_update() {
        spriteCounter++;
        if (spriteCounter > 8) {
            spriteNum++;
            if (spriteNum > 2) spriteNum = 1;
            spriteCounter = 0;
        }
    }

    // animación normal (movimiento) si la necesitas
    public void update() {
        if (keyH != null && keyH.spcPressed) {
            spriteCounter++;
            if (spriteCounter > 8) {
                spriteNum++;
                if (spriteNum > 5) spriteNum = 1;
                spriteCounter = 0;
            }
        }
    }

    // dibujado estático con tamaño personalizado (para pkSelectorPanel)
    public void static_draw(Graphics2D g2, int drawSize) {
        BufferedImage image = (spriteNum == 2) ? a2 : a1;
        if (image == null) return;

        // si bp no es null usa x,y; si solo pSp existe usamos x,y también
        g2.drawImage(image, x, y, drawSize, drawSize, null);
    }

    // dibujado en batalla (usa bp.tileSize si está disponible)
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

    // Exponer setters de posición por si quieres colocarlos desde el panel
    public void setPosition(int x, int y) { this.x = x; this.y = y; }
    }