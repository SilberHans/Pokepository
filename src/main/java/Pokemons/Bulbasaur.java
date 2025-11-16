package Pokemons;

import Pokemons.Logic.PkTypeEnum;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;

public class Bulbasaur extends Pokemon{

    public Bulbasaur(int pkLevel){
        super(pkLevel, PkTypeEnum.Grass, PkTypeEnum.Poison, 45, 49, 65, 49, 65, 45);
    }
    
    @Override
    public String toString(){
        return "\t-----Bulbasaur-----" + super.toString();
    }

    @Override
    public String pkNoise(){
        switch(ThreadLocalRandom.current().nextInt(4)){
            case 0 -> {return "Bulba!";}
            case 1 -> {return "Saur!";}
            case 2 -> {return "Bulba-saur!";}
            case 3 -> {return "Bulba-bulba!";}
            default -> {return "Uhh...";}
        }
    }
    @Override
    public void loadSprites() {
        try {
            this.aback=ImageIO.read(getClass().getResourceAsStream("/battle/Bulbasaur_espalda.png"));
            this.afront=ImageIO.read(getClass().getResourceAsStream("/battle/Bulbasaur_frente.png"));
        } catch (IOException ex) {
            this.aback=null;
            this.afront=null;
            System.out.println("ERROR CARGANDO IMAGENES");
        }
    }
}