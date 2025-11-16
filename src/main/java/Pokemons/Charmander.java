package Pokemons;

import Pokemons.Logic.PkTypeEnum;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;

public class Charmander extends Pokemon{

    public Charmander(int pkLevel){
        super(pkLevel, PkTypeEnum.Fire, null, 39, 52, 60, 43, 50, 65);
    }
    
    @Override
    public String toString(){
        return "\t-----Charmander-----" + super.toString();
    }
    
    @Override
    public String pkNoise(){
        switch(ThreadLocalRandom.current().nextInt(4)){
            case 0 -> {return "Char!";}
            case 1 -> {return "Mander!";}
            case 2 -> {return "Char-char!";}
            case 3 -> {return "Char-mander!";}
            default -> {return "Uhh...";}
        }
    }
    @Override
    public void loadSprites() {
        try {
            this.aback=ImageIO.read(getClass().getResourceAsStream("/battle/Charmander_espalda.png"));
            this.afront=ImageIO.read(getClass().getResourceAsStream("/battle/Charmander_frente.png"));
        } catch (IOException ex) {
            this.aback=null;
            this.afront=null;
            System.out.println("ERROR CARGANDO IMAGENES");
        }
    }
}