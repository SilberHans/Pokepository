package Pokemons;

import Pokemons.Logic.PkTypeEnum;
import java.util.concurrent.ThreadLocalRandom;

public class Arcanine extends Pokemon{

    public Arcanine(int pkLevel){
        super(pkLevel, PkTypeEnum.Fire, null, 90, 110, 100, 80, 80, 95);
    }
    
    @Override
    public String toString(){
        return "\t-----Eevee-----" + super.toString();
    }
    
    @Override
    public String pkNoise(){
        switch(ThreadLocalRandom.current().nextInt(4)){
            case 0 -> {return "Roooar!";}
            case 1 -> {return "Arca!";}
            case 2 -> {return "Grrrr-nine!";}
            case 3 -> {return "Aaaa-rooo!";}
            default -> {return "Uhh...";}
        }
    }
}