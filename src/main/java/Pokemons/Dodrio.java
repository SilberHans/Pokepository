package Pokemons;

import Pokemons.Logic.PkTypeEnum;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;

public class Dodrio extends Pokemon{

    public Dodrio(int pkLevel){
        super(pkLevel, PkTypeEnum.Normal, PkTypeEnum.Flying, 60, 110, 60, 70, 60, 110);
    }
    
    @Override
    public String toString(){
        return "\t-----Jigglypuff-----" + super.toString();
    }
    
    @Override
    public String pkNoise(){
        switch(ThreadLocalRandom.current().nextInt(4)){
            case 0 -> {return "Drio-drio!";}
            case 1 -> {return "Kreee!";}
            case 2 -> {return "Do-drio!";}
            case 3 -> {return "Do-dri-oooo!";}
            default -> {return "Uhh...";}
        }
    }
    @Override
    public void loadSprites() {
        try {
            this.aback=ImageIO.read(getClass().getResourceAsStream("/battle/Dodrio_espalda.png"));
            this.afront=ImageIO.read(getClass().getResourceAsStream("/battle/Dodrio_frente.png"));
        } catch (IOException ex) {
            this.aback=null;
            this.afront=null;
            System.out.println("ERROR CARGANDO IMAGENES");
        }
    }
}
