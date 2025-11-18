package Pokemons;

import Utility.Constants.PkTypeEnum;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;

public class Arcanine extends Pokemon{

    public Arcanine(int pkLevel){
        super(pkLevel, PkTypeEnum.Fire, null, 90, 110, 100, 80, 80, 95);
        super.setPkNickName("Arcanine");
    }
    
    @Override
    public String toString(){
        return "\t-----Eevee-----" + super.toString();
    }
    
    @Override
    public String pkNoise(){
        switch(ThreadLocalRandom.current().nextInt(4)){
            case 0 -> {return "Roooar!";}
            case 1 -> {return "Arca!";}
            case 2 -> {return "Grrrr-nine!";}
            case 3 -> {return "Aaaa-rooo!";}
            default -> {return "Uhh...";}
        }
    }
    
    @Override
    public void loadSprites() {
        try {
            this.aback=ImageIO.read(getClass().getResourceAsStream("/battle/Arcanine_espalda.png"));
            this.afront=ImageIO.read(getClass().getResourceAsStream("/battle/Arcanine_frente.png"));
        } catch (IOException ex) {
            this.aback=null;
            this.afront=null;
            System.out.println("ERROR CARGANDO IMAGENES");
        }
    }
}