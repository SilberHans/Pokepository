package Pokemons;

import Utility.Constants.PkTypeEnum;
import java.util.concurrent.ThreadLocalRandom;

public class Dodrio extends Pokemon{

    public Dodrio(int pkLevel){
        super(pkLevel, PkTypeEnum.Normal, PkTypeEnum.Flying, 60, 110, 60, 70, 60, 110);
    }
    
    @Override
    public String toString(){
        return "\t-----Dodrio-----" + super.toString();
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
}
