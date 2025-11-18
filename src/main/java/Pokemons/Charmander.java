package Pokemons;

import Utility.Constants.PkTypeEnum;
import java.util.concurrent.ThreadLocalRandom;

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
}