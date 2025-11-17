package Pokemons;

import Utility.Constants.PkTypeEnum;
import java.util.concurrent.ThreadLocalRandom;

public class Pikachu extends Pokemon{

    public Pikachu(int pkLevel){
        super(pkLevel, PkTypeEnum.Electric, null, 35, 55, 50, 40, 50, 90);
    }
    
    @Override
    public String toString(){
        return "\t-----Pikachu-----" + super.toString();
    }
    
    @Override
    public String pkNoise(){
        switch(ThreadLocalRandom.current().nextInt(5)){
            case 0 -> {return "Pika!";}
            case 1 -> {return "Pika-Pika!";}
            case 2 -> {return "Chuuuuu!";}
            case 3 -> {return "Pikachu!";}
            case 4 -> {return "Pi-ka-chuuuuu!";}
            default -> {return "Uhh...";}
        }
    }
}