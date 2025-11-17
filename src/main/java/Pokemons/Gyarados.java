package Pokemons;

import Utility.Constants.PkTypeEnum;
import java.util.concurrent.ThreadLocalRandom;

public class Gyarados extends Pokemon{

    public Gyarados(int pkLevel){
        super(pkLevel, PkTypeEnum.Water, PkTypeEnum.Flying, 95, 125, 60, 79, 100, 81);
    }
    
    @Override
    public String toString(){
        return "\t-----Gyarados-----" + super.toString();
    }
    
    @Override
    public String pkNoise(){
        switch(ThreadLocalRandom.current().nextInt(4)){
            case 0 -> {return "GYAAAARGH!";}
            case 1 -> {return "Rooooar!";}
            case 2 -> {return "Gya-ra-dos!";}
            case 3 -> {return "Skreee-onk!";}
            default -> {return "Uhh...";}
        }   
    }
}