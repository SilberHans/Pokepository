package Persons;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import GameDesing.Graphics.*;
import Pokemons.Logic.Movements.Move;
import java.awt.geom.AffineTransform;
import Pokemons.Pokemon;
import Utility.Constants.TMedalsEnum;
import Utility.Validations.PersonValidations;
import java.awt.Point;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JPanel;

public class Trainer extends PokemonHandler {

    private ArrayList<TMedalsEnum> tMedalsList;
    private ArrayList<Pokemons.Logic.Items.Item> tItemsList;
    private Pokemon tActivePokemon;
    
    public Trainer(String name){
        super();
        super.setpName(name);
        this.tMedalsList = PersonValidations.gentMedalsList();
        this.tItemsList = new ArrayList<>();
        this.tActivePokemon = null;
        this.keyH = null;
        this.bp = null;
        this.pSp = null;
        this.a1 = this.a2 = this.a3 = this.a4 = this.a5 = null;
    }
    public Trainer(String pName, String pRegion, String pID, LocalDate pBirthDate, int pPokeDollars) {
        super(pName, pRegion, pID, pBirthDate, pPokeDollars);
        this.tMedalsList = new ArrayList<>();
        this.tItemsList = new ArrayList<>();
        this.tActivePokemon = null;
    }    
    public Trainer(ArrayList<TMedalsEnum> tMedalsList, ArrayList<Pokemons.Logic.Items.Item> tItemsList, ArrayList<Pokemon> phPokeList, String pName, String pRegion, String pID, LocalDate pBirthDate, int pPokeDollars) {
        super(phPokeList, pName, pRegion, pID, pBirthDate, pPokeDollars);
        this.tMedalsList = tMedalsList;
        this.tItemsList = tItemsList;
        this.tActivePokemon = null;
    }

    public void settMedalsList(ArrayList<TMedalsEnum> tMedalsList){
        this.tMedalsList = tMedalsList;
    }
    public void addtMedal(TMedalsEnum tMedal){
        this.tMedalsList.add(tMedal);
    }
    public void settItemList(ArrayList<Pokemons.Logic.Items.Item> tItemList){
        this.tItemsList = tItemList;
    }
    public void addtItem(Pokemons.Logic.Items.Item tItem){
        this.tItemsList.add(tItem);
    }
    public void settActivePokemon(Pokemon tActivePokemon){
        if(tActivePokemon == null){
            this.tActivePokemon = null;
            return;
        }
        if(super.phPokeList.contains(tActivePokemon) && tActivePokemon.getPkHp() > 0){
            this.tActivePokemon = tActivePokemon;
        }
    }

    public ArrayList<TMedalsEnum> gettMedalsList(){
        return this.tMedalsList;
    }
    public TMedalsEnum gettMedal(int tMedalPst){
        try{
            return this.tMedalsList.get(tMedalPst);
        }catch(ArrayIndexOutOfBoundsException e){
            return null;
        }
    }
    public String gettMedalsListStr(){
        if(this.tMedalsList.isEmpty()){
            return "No medals yet...";
        }
        String str = "";
        for(TMedalsEnum tryMedal: this.tMedalsList){
            str += tryMedal + ", ";
        }
        return str;
    }
    public ArrayList<Pokemons.Logic.Items.Item> gettItemList(){
        return tItemsList;
    }
    public Pokemons.Logic.Items.Item gettItem(int tItemPst){
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
        for(Pokemons.Logic.Items.Item tryItem: this.tItemsList){
            str += tryItem.toString() + "\n";
        }
        return str;
    }
    public Pokemon getActivePokemon(){
        if(this.tActivePokemon == null || this.tActivePokemon.getPkHp() <= 0){
            for(Pokemon tryPokemon: super.phPokeList){
                if(tryPokemon.getPkHp() > 0){
                    this.tActivePokemon = tryPokemon;
                    return this.tActivePokemon;
                }
            }
            return null;
        }
        return this.tActivePokemon;
    }

    @Override
    public String toString(){
        return "-----Trainer Information-----\n" + super.toString() + "\nMedals:\t" + this.gettMedalsListStr() + "\n\t-Pokemons-\n" + this.getPhPokeListStr() + "\n\t-Items-\n" +this.gettItemListStr();
    }
    
    @Override
    public String getPhPokeListStr(){
        if(super.phPokeList.isEmpty()){
            return "No Pokémon have been assigned to this Trainer.";
        }
        String str = "";
        for(Pokemon pokemonTry: super.phPokeList){
            str += pokemonTry.toString();
        }
        return str;
    }

    @Override
    public String genericDialogue() {
        switch(ThreadLocalRandom.current().nextInt(4)){
            case 0 -> {return "Our eyes met! We have to battle!";}
            case 1 -> {return "Do you think you can beat me and my team?";}
            case 2 -> {return "I was waiting for a good fight! Let's go!";}
            case 3 -> {return "Let's see what you're made of!";}
            default -> {return "Get ready...";}
        }
    }
    
    public boolean hastAlivePokemon(){
        for(Pokemon tryPokemon: super.phPokeList){
            if(tryPokemon.getPkHp() > 0){
                return true;
            }
        }
        return false;
    }
    
    public void removetItem(Pokemons.Logic.Items.Item tItem){
        this.tItemsList.remove(tItem);
    }
    

    public String[] getPokemonNames() {
        // 'phPokeList' es el ArrayList heredado de PokemonHandler
        if (phPokeList.isEmpty()) { 
            return new String[]{"No Pokémon"};
        }
        
        // Creamos un array de Strings del tamaño de la lista
        String[] pokemonNames = new String[phPokeList.size()];
 
        // Recorremos la lista y obtenemos el nombre de cada Pokémon
        for (int i = 0; i < phPokeList.size(); i++) {
            pokemonNames[i] = phPokeList.get(i).getPkNickName(); 
        }
        
        return pokemonNames;
    }

    // Añade este método a Trainer.java para que el menú "FIGHT" funcione
    public String[] getPokemonMoveNames() {
        Pokemon activePk = getActivePokemon();
        if (activePk == null) {
            return new String[]{"-"};
        }
        
        // Asume que pkMoveSet es un ArrayList<Move> de 4 posiciones
        ArrayList<Move> moveSet = activePk.getPkMoveSet();
        String[] moveNames = new String[4]; // Siempre 4
        
        for (int i = 0; i < 4; i++) {
            if (i < moveSet.size() && moveSet.get(i) != null) {
                moveNames[i] = moveSet.get(i).getMvName();
            } else {
                moveNames[i] = "-";
            }
        }
        return moveNames;
    }
    
    
    @Override
    public void addPhPokemon(Pokemon phPokemon){
        if (phPokemon != null) {
            phPokemon.setTrainer(this); // ¡LÍNEA IMPORTANTE!
            this.phPokeList.add(phPokemon);
        }
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
    private int initialX;
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
        
        if (isPlayerOne) {
            targetX = -200;
            //targetY = this.y - 100;
        } else {
            targetX = bp.getWidth() + 200;
            //targetY = this.y - 100;
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
            //this.y = (int) (initialY + (targetY - initialY) * exitProgress);
            
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

    //animacion pokeball
    public int getBallSize() {
        return 42 - (throwProgress / 4); // De 32 a ~7 pixels
    }


    
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

    
    public void draw(Graphics2D g2) {
        if (!spritesLoaded || spriteNum < 1 || spriteNum > 5) return;
        
        BufferedImage image = sprites[spriteNum - 1];
        if (image == null) return;
        g2.drawImage(image, x, y, battleSpriteSize , battleSpriteSize, null);
    }

    
    //DEBUG
    public String getStateString() {
        switch (trainerState) {
            case STATE_IDLE: return "IDLE";
            case STATE_THROWING: return "THROWING";
            case STATE_THROW_COMPLETE: return "THROW_COMPLETE";
            default: return "UNKNOWN";
        }
    }

    public int getThrowProgress() {
        return throwProgress;
    }
    
    
    


    
    // setDefault según turno (Batalla)
    public void setDefaultValues(boolean turn) {
        if (turn) {
            x = 90; y = 158 ; direction = "left";
        } else {
            x = 500; y = 60; direction = "right";
        }
        battleSpriteSize = 200; 
        speed = 0;
        spriteCounter = 0;
        spriteNum = 1;
        
    }
}

