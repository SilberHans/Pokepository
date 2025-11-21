package Pokemons;

import Utility.Constants.PkTypeEnum;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;

public class Chansey extends Pokemon{

    public Chansey(int pkLevel){
        super(pkLevel, PkTypeEnum.Normal, null, 250, 5, 35, 5, 105, 50);
        super.setPkNickName("Chansey");
    }
    
    @Override
    public String toString(){
        return "\t-----Chansey-----" + super.toString();
    }
    
    @Override
    public String pkNoise(){
        switch(ThreadLocalRandom.current().nextInt(4)){
            case 0 -> {return "Chansey!";}
            case 1 -> {return "Chan-sey!";}
            case 2 -> {return "Sey-sey!";}
            case 3 -> {return "Chaaansey!";}
            default -> {return "Uhh...";}
        }
    }
    @Override
    public void loadSprites() {
        try {
            this.aback=ImageIO.read(getClass().getResourceAsStream("/battle/Chansey_espalda.png"));
            this.afront=ImageIO.read(getClass().getResourceAsStream("/battle/Chansey_frente.png"));
        } catch (IOException ex) {
            this.aback=null;
            this.afront=null;
            System.out.println("ERROR CARGANDO IMAGENES");
        }
    }
}