package Persons;

import Utility.DataBase.GenericDataBase;
import Pokemons.Chansey;
import Pokemons.Pokemon;
import java.awt.image.BufferedImage; // Importar
import java.io.IOException; // Importar
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO; // Importar

public class NurseJoy extends PokemonHandler{
    private Pokemon njPokeAssistant;
    private LocalDate njAdmissionDate;
    private int njExpYears;
    private boolean njAvailability;
    private BufferedImage sprite; // Sprite de la Enfermera Joy

    public NurseJoy(){
        String njRndmTempRegion = GenericDataBase.getRndmPersonRegion();
        super("Joy", njRndmTempRegion, GenericDataBase.genRndmPersonID(1, njRndmTempRegion), GenericDataBase.genRndmDateByCrrntYears(20, 30), ThreadLocalRandom.current().nextInt(1000, 12501));
        this.njPokeAssistant = new Chansey(ThreadLocalRandom.current().nextInt(1, 101));
        LocalDate njRndmTempAdmissionDate = GenericDataBase.genRndmDateByCrrntYears(0, 8);
        this.njAdmissionDate = njRndmTempAdmissionDate;
        this.njExpYears = Period.between(njRndmTempAdmissionDate, LocalDate.now()).getYears();
        this.njAvailability = true;
        loadSprite(); // Cargar el sprite al crear
    }
    
    public NurseJoy(LocalDate njAdmissionDate, boolean njAvailability, String pRegion, String pID, LocalDate pBirthDate, int pPokeDollars){
        super("Joy", pID, pRegion, pBirthDate, pPokeDollars);
        this.njPokeAssistant = new Chansey(100);
        this.njAdmissionDate = njAdmissionDate;
        this.njExpYears = Period.between(njAdmissionDate, LocalDate.now()).getYears();
        this.njAvailability = njAvailability;
        loadSprite(); // Cargar el sprite al crear
    }
    
    public NurseJoy(Pokemon njPokeAssistant, LocalDate njAdmissionDate, boolean njAvailability, String pRegion, String pID, LocalDate pBirthDate, int pPokeDollars){
        super("Joy", pID, pRegion, pBirthDate, pPokeDollars);
        this.njPokeAssistant = njPokeAssistant;
        this.njAdmissionDate = njAdmissionDate;
        this.njExpYears = Period.between(njAdmissionDate, LocalDate.now()).getYears();
        this.njAvailability = njAvailability;
        loadSprite(); // Cargar el sprite al crear
    }
    
    // --- NUEVOS MÉTODOS PARA SPRITE ---
    
    /**
     * Carga el sprite de la Enfermera Joy desde los recursos.
     */
    public void loadSprite() {
        try {
            // Asegúrate de tener una imagen en esta ruta: src/main/resources/persons/nurse_joy_sprite.png
            this.sprite = ImageIO.read(getClass().getResourceAsStream("/images/joy_sprite.png"));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Error cargando sprite de NurseJoy: " + e.getMessage());
            this.sprite = null; // Dejar el sprite como null si no se encuentra
        }
    }

    /**
     * Devuelve el sprite cargado de la Enfermera Joy.
     * @return El BufferedImage del sprite.
     */
    public BufferedImage getSprite() {
        return this.sprite;
    }

    // --- FIN DE MÉTODOS DE SPRITE ---

    public void setNjPokeAssistant(Pokemon njPokeAssistant){
        this.njPokeAssistant = njPokeAssistant;
    }
    public void setNjAdmissionDate(LocalDate njAdmissionDate){
        this.njAdmissionDate = njAdmissionDate;
    }
    public void setNjExpYears(LocalDate njAdmissionDate){
        this.njExpYears = Period.between(njAdmissionDate, LocalDate.now()).getYears();
    }
    public void setNjAvailability(boolean njAvailability){
        this.njAvailability = njAvailability;
    } 

    public Pokemon getNjPokeAssistant(){
        return this.njPokeAssistant;
    }
    public String getNjPokeAssistantStr(){
        return this.njPokeAssistant.toString();
    }
    public LocalDate getNjAdmissionDate(){
        return this.njAdmissionDate;
    }
    public String getNjAdmissionDateStr(){
        return this.njAdmissionDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
    public int getNjExpYears(){
        return this.njExpYears;
    }
    public boolean getNjAvailability(){
        return this.njAvailability;
    }
    public String getNjAvailabilityStr(){
        switch(this.njAvailability){
            case true ->{ return "Available";}
            case false ->{ return "Uavailable";}
        }
    }
    
    @Override
    public String toString(){
        return "\t-----Nurse Joy Information-----\n" + super.toString() + "\nYear of Admission:\t" + this.getNjAdmissionDateStr() + "\nYears of Experience:\t" + this.getNjExpYears() + "\n\n" + this.getNjPokeAssistantStr() + "\nAvailability:\t\t" + this.getNjAvailability() + "\n\t-Pokemons to Attend-\n" + this.getPhPokeListStr();
    }
    
    @Override
    public String getPhPokeListStr(){
        if(super.phPokeList.isEmpty()){
            return "No Pokemon have been assigned to this Nurse Joy.";
        }
        String str = "";
        for(Pokemon pokemonTry: super.phPokeList){
            str = pokemonTry.toString();
        }
        return str;
    }
    @Override
    public String genericDialogue(){
        switch(ThreadLocalRandom.current().nextInt(5)){
        case 0 -> {return "Welcome to the Pokémon Center. Would you like me to heal your Pokémon?";}
            case 1 -> {return "Don't worry, your Pokémon will be good as new in just a moment.";}
            case 2 -> {return "Okay, I'll take your Pokémon. We'll begin the treatment.";}
            case 3 -> {return "Oh, you poor things, they look exhausted. We'll take good care of them.";}
            case 4 -> {return "We hope to see you again soon!";}
            default -> {return "Uhh...";}
        }
    }  
    
    public void njHealPokemons(){
        for(Pokemon tryPokemon: this.phPokeList){
            tryPokemon.pkHeal(tryPokemon.getPkMaxHp());
            tryPokemon.pkResetStatStages();
            tryPokemon.pkCureStatus();
            tryPokemon.pkClearEffects();
        }
    }
}