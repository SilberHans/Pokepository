package Pokemons;

import Pokemons.Logic.PkTypeEnum;
import java.util.concurrent.ThreadLocalRandom;

public class Alakazam extends Pokemon{

    public Alakazam(int pkLevel){
        super(pkLevel, PkTypeEnum.Psychic, null, 55, 50, 135, 45, 95, 120);
    }

    @Override
    public String toString(){
        return "\t-----Abra-----" + super.toString();
    }
    
    @Override
    public String pkNoise(){
        switch(ThreadLocalRandom.current().nextInt(4)){
            case 0 -> {return "Kazam!";}
            case 1 -> {return "Ala-kaa-zam!";}
            case 2 -> {return "Zaam!";}
            case 3 -> {return "Kaa-zam!";}
            default -> {return "Uhh...";}
        }
    }
}