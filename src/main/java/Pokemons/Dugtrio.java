package Pokemons;

import Pokemons.Logic.PkTypeEnum;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;

public class Dugtrio extends Pokemon{

    public Dugtrio(int pkLevel){
        super(pkLevel, PkTypeEnum.Ground, null, 35, 100, 50, 50, 70, 120);
        super.setPkNickName("Dugtrio");
    }
    
    @Override
    public String toString(){
        return "\t-----Vulpix-----" + super.toString();
    }
    
    @Override
    public String pkNoise(){
        switch(ThreadLocalRandom.current().nextInt(4)){
            case 0 -> {return "Dug!";}
            case 1 -> {return "Trio-trio!";}
            case 2 -> {return "Dug-dug-dug!";}
            case 3 -> {return "Trio!";}
            default -> {return "Uhh...";}
        }
    }
    @Override
    public void loadSprites() {
        try {
            this.aback=ImageIO.read(getClass().getResourceAsStream("/battle/Dugtrio_espalda.png"));
            this.afront=ImageIO.read(getClass().getResourceAsStream("/battle/Dugtrio_frente.png"));
        } catch (IOException ex) {
            this.aback=null;
            this.afront=null;
            System.out.println("ERROR CARGANDO IMAGENES");
        }
    }
}