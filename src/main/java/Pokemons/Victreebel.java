package Pokemons;

import Utility.Constants.PkTypeEnum;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;

public class Victreebel extends Pokemon{

    public Victreebel(int pkLevel){
        super(pkLevel, PkTypeEnum.Grass, PkTypeEnum.Poison, 80, 105, 100, 65, 70, 70);
        super.setPkNickName("Victreebel");
    }
    
    @Override
    public String toString(){
        return "\t-----Gastly-----" + super.toString();
    }
    
    @Override
    public String pkNoise(){
        switch(ThreadLocalRandom.current().nextInt(4)){
            case 0 -> {return "Screeeee!";}
            case 1 -> {return "Vic-tree!";}
            case 2 -> {return "Bel-bel-bel!";}
            case 3 -> {return "Victreee!";}
            default -> {return "Uhh...";}
        }
    }
    @Override
    public void loadSprites() {
        try {
            this.aback=ImageIO.read(getClass().getResourceAsStream("/battle/Victreebel_espalda.png"));
            this.afront=ImageIO.read(getClass().getResourceAsStream("/battle/Victreebel_frente.png"));
        } catch (IOException ex) {
            this.aback=null;
            this.afront=null;
            System.out.println("ERROR CARGANDO IMAGENES");
        }
    }
}