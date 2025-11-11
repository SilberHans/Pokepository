package Pokemons;

import Pokemons.Logic.PkTypeEnum;
import java.util.concurrent.ThreadLocalRandom;

public class Chansey extends Pokemon{

    public Chansey(int pkLevel){
        super(pkLevel, PkTypeEnum.Normal, null, 250, 5, 35, 5, 105, 50);
    }
    
    @Override
    public String toString(){
        return "\t-----Chansey-----" + super.toString();
    }
    
    @Override
    public String pkNoise(){
        switch(ThreadLocalRandom.current().nextInt(4)){
            case 0 -> {return "Chansey!";}
            case 1 -> {return "Chan-sey!";}
            case 2 -> {return "Sey-sey!";}
            case 3 -> {return "Chaaansey!";}
            default -> {return "Uhh...";}
        }
    }
}