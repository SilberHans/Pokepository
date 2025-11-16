package Pokemons;

import Pokemons.Logic.PkTypeEnum;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;

public class Hitmonlee extends Pokemon{

    public Hitmonlee(int pkLevel){
        super(pkLevel, PkTypeEnum.Fighting, null, 50, 120, 35, 53, 110, 87);
    }
    
    @Override
    public String toString(){
        return "\t-----Machop-----" + super.toString();
    }
    
    @Override
    public String pkNoise(){
        switch(ThreadLocalRandom.current().nextInt(4)){
            case 0 -> {return "Hiii-ya!";}
            case 1 -> {return "Kiai!";}
            case 2 -> {return "Hit-mon-lee!";}
            case 3 -> {return "Hiii-kick!";}
            default -> {return "Uhh...";}
        }
    }
    @Override
    public void loadSprites() {
        try {
            this.aback=ImageIO.read(getClass().getResourceAsStream("/battle/Hitmonlee_espalda.png"));
            this.afront=ImageIO.read(getClass().getResourceAsStream("/battle/Hitmonlee_frente.png"));
        } catch (IOException ex) {
            this.aback=null;
            this.afront=null;
            System.out.println("ERROR CARGANDO IMAGENES");
        }
    }
}