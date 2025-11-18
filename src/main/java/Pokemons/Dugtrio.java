package Pokemons;

import Utility.Constants.PkTypeEnum;
import java.util.concurrent.ThreadLocalRandom;

public class Dugtrio extends Pokemon{

    public Dugtrio(int pkLevel){
        super(pkLevel, PkTypeEnum.Ground, null, 35, 100, 50, 50, 70, 120);
    }
    
    @Override
    public String toString(){
        return "\t-----Dugtrio-----" + super.toString();
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
}