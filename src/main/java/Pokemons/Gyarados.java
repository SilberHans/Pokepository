package Pokemons;

import Pokemons.Logic.PkTypeEnum;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;

public class Gyarados extends Pokemon{

    public Gyarados(int pkLevel){
        super(pkLevel, PkTypeEnum.Water, PkTypeEnum.Flying, 95, 125, 60, 79, 100, 81);
        super.setPkNickName("Gyarados");
    }
    
    @Override
    public String toString(){
        return "\t-----Geodude-----" + super.toString();
    }
    
    @Override
    public String pkNoise(){
        switch(ThreadLocalRandom.current().nextInt(4)){
            case 0 -> {return "GYAAAARGH!";}
            case 1 -> {return "Rooooar!";}
            case 2 -> {return "Gya-ra-dos!";}
            case 3 -> {return "Skreee-onk!";}
            default -> {return "Uhh...";}
        }   
    }
    @Override
    public void loadSprites() {
        try {
            this.aback=ImageIO.read(getClass().getResourceAsStream("/battle/Gyarados_espalda.png"));
            this.afront=ImageIO.read(getClass().getResourceAsStream("/battle/Gyarados_frente.png"));
        } catch (IOException ex) {
            this.aback=null;
            this.afront=null;
            System.out.println("ERROR CARGANDO IMAGENES");
        }
    }
}