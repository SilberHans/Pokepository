package Pokemons;

import Pokemons.Logic.PkTypeEnum;
import java.util.concurrent.ThreadLocalRandom;

public class Electrode extends Pokemon{

    public Electrode(int pkLevel){
        super(pkLevel, PkTypeEnum.Electric, null, 60, 50, 80, 70, 80, 150);
    }
    
    @Override
    public String toString(){
        return "\t-----Meowth-----" + super.toString();
    }
    
    @Override
    public String pkNoise(){
        switch(ThreadLocalRandom.current().nextInt(4)){
            case 0 -> {return "E-lec-trode!";}
            case 1 -> {return "Bzzzt!";}
            case 2 -> {return "Troooode!";}
            case 3 -> {return "Bzz-BOOM!";}
            default -> {return "Uhh...";}
        }
    }
}