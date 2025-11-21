package Pokemons;

import Utility.Constants.PkTypeEnum;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;

public class Electrode extends Pokemon{

    public Electrode(int pkLevel){
        super(pkLevel, PkTypeEnum.Electric, null, 60, 50, 80, 70, 80, 150);
        super.setPkNickName("Electrode");
    }
    
    @Override
    public String toString(){
        return "\t-----Meowth-----" + super.toString();
    }
    
    @Override
    public String pkNoise(){
        switch(ThreadLocalRandom.current().nextInt(4)){
            case 0 -> {return "E-lec-trode!";}
            case 1 -> {return "Bzzzt!";}
            case 2 -> {return "Troooode!";}
            case 3 -> {return "Bzz-BOOM!";}
            default -> {return "Uhh...";}
        }
    }
    @Override
    public void loadSprites() {
        try {
            this.aback=ImageIO.read(getClass().getResourceAsStream("/battle/Electrode_espalda.png"));
            this.afront=ImageIO.read(getClass().getResourceAsStream("/battle/Electrode_frente.png"));
        } catch (IOException ex) {
            this.aback=null;
            this.afront=null;
            System.out.println("ERROR CARGANDO IMAGENES");
        }
    }
}