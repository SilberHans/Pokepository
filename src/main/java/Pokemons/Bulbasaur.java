package Pokemons;

import Pokemons.Logic.PkTypeEnum;
import java.util.concurrent.ThreadLocalRandom;

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
}