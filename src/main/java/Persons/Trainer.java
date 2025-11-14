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
    
    // Graphics
    
    BattlePanel bp;
    KeyHandler keyH;
    String num;
    
    public Trainer(BattlePanel bp,KeyHandler keyH,String num,boolean turn){
        this.bp=bp;
        this.keyH=keyH;
        this.num=num;
        setDefaultValues(turn);
        getTrainerMoveImage(num);
    }
    public Trainer(pkSelectorPanel pSp,KeyHandler keyH,String num){
        this.bp=bp;
        this.keyH=keyH;
        this.num=num;
        setDefaultValues();
        
    }
    
    public void getTrainerStaticImage(Trainer trainer){
        int x=Integer.parseInt(trainer.num);
        switch (x){
            case 1->{
                try{
                    a1=ImageIO.read(getClass().getResourceAsStream("/trainer_static/1_1.png"));
                    a2=ImageIO.read(getClass().getResourceAsStream("/trainer_static/2_1.png"));           
                }catch(IOException e){
                    e.printStackTrace();
                } break;
            }    
            case 2 -> {
                try{
                    a1=ImageIO.read(getClass().getResourceAsStream("/trainer_static/2_1.png"));
                    a2=ImageIO.read(getClass().getResourceAsStream("/trainer_static/2_2.png"));           
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            case 3 -> {
                try{
                    a1=ImageIO.read(getClass().getResourceAsStream("/trainer_static/3.png"));
                    a2=ImageIO.read(getClass().getResourceAsStream("/trainer_static/3.png"));           
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            case 4 -> {
                try{
                    a1=ImageIO.read(getClass().getResourceAsStream("/trainer_static/4_1.png"));
                    a2=ImageIO.read(getClass().getResourceAsStream("/trainer_static/4_2.png"));           
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
            
        
        
    
    public void getTrainerMoveImage(String num) {         
        try{
                    a1=ImageIO.read(getClass().getResourceAsStream("/trainer_move/"+num+"_1.png"));
                    a2=ImageIO.read(getClass().getResourceAsStream("/trainer_move/"+num+"_2.png"));
                    a3=ImageIO.read(getClass().getResourceAsStream("/trainer_move/"+num+"_3.png"));
                    a4=ImageIO.read(getClass().getResourceAsStream("/trainer_move/"+num+"_4.png"));
                    a5=ImageIO.read(getClass().getResourceAsStream("/trainer_move/"+num+"_5.png"));
                }catch(IOException e){
                    e.printStackTrace();
                }
    }
    
     public void setDefaultValues(){
        x=220;
        y=280;
        speed=0;
        spriteCounter=0;
        spriteNum=1;
        direction="left";    
    }
    
    public void setDefaultValues(boolean turn){
        if (turn){
            x=120;
            y=260;
            speed=0;
            spriteCounter=0;
            spriteNum=1;
            direction="left";
        }
        else{
            x=480;
            y=120;
            speed=0;
            spriteCounter=0;
            spriteNum=1;
            direction="right";
        }
    }
    
    public void update(){
        if (keyH.spcPressed){
            spriteCounter++;
            if (spriteCounter>8){
                spriteNum++;
                if(spriteNum>5){
                    spriteNum=1;
                }
                spriteCounter=0;
            }
        }          
    }
    public void draw(Graphics2D g2){
        
        BufferedImage image=null;
        switch(spriteNum){
            case 1:
                image=a1;
                break;
            case 2:
                image=a2;
                break;
            case 3:
                image=a3;
                break;
            case 4:
                image=a4;
                break;
            case 5:
                image=a5;
                break;
        }
        g2.drawImage(image, x, y,bp.tileSize,bp.tileSize,null );
    }
}