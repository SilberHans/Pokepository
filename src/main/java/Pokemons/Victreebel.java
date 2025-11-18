package Pokemons;

import Utility.Constants.PkTypeEnum;
import java.util.concurrent.ThreadLocalRandom;

public class Victreebel extends Pokemon{

    public Victreebel(int pkLevel){
        super(pkLevel, PkTypeEnum.Grass, PkTypeEnum.Poison, 80, 105, 100, 65, 70, 70);
    }
    
    @Override
    public String toString(){
        return "\t-----Victreebel-----" + super.toString();
    }
    
    @Override
    public String pkNoise(){
        switch(ThreadLocalRandom.current().nextInt(4)){
            case 0 -> {return "Screeeee!";}
            case 1 -> {return "Vic-tree!";}
            case 2 -> {return "Bel-bel-bel!";}
            case 3 -> {return "Victreee!";}
            default -> {return "Uhh...";}
        }
    }
}