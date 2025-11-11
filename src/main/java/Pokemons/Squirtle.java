package Pokemons;

import Pokemons.Logic.PkTypeEnum;
import java.util.concurrent.ThreadLocalRandom;

public class Squirtle extends Pokemon{
    
    public Squirtle(int pkLevel){
        super(pkLevel, PkTypeEnum.Water, null, 44, 48, 50, 65, 64, 43);
    }
    
    @Override
    public String toString(){
        return "\t-----Squirtle-----" + super.toString();
    }
    
    @Override
    public String pkNoise(){
        switch(ThreadLocalRandom.current().nextInt(4)){
            case 0 -> {return "Squirtle!";}
            case 1 -> {return "Squirt-squirt!";}
            case 2 -> {return "Tle-tle!";}
            case 3 -> {return "Squirtle-squirt!";}
            default -> {return "Uhh...";}
        }   
    }
}