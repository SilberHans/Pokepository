package Pokemons;

import Pokemons.Logic.PkTypeEnum;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Alakazam extends Pokemon{
    
    public Alakazam(int pkLevel){
        super(pkLevel, PkTypeEnum.Psychic, null, 55, 50, 135, 45, 95, 120);
        super.setPkNickName("Alakazam");
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

    @Override
    public void loadSprites() {
        try {
            this.aback=ImageIO.read(getClass().getResourceAsStream("/battle/Alakazam_espalda.png"));
            this.afront=ImageIO.read(getClass().getResourceAsStream("/battle/Alakazam_frente.png"));
        } catch (IOException ex) {
            this.aback=null;
            this.afront=null;
            System.out.println("ERROR CARGANDO IMAGENES");
        }
    }
}