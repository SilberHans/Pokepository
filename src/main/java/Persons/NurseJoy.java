package Persons;

import GameDesing.GenericDataBase;
import Pokemons.Chansey;
import Pokemons.Pokemon;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public class NurseJoy extends PokemonHandler{
    private Pokemon njPokeAssistant;
    private LocalDate njAdmissionDate;
    private int njExpYears;
    private boolean njAvailability;
    
    public NurseJoy(){
        String njRndmTempRegion = GenericDataBase.getRndmPersonRegion();
        super("Joy", njRndmTempRegion, GenericDataBase.genRndmPersonID(1, njRndmTempRegion), GenericDataBase.genRndmDateByCrrntYears(20, 30), ThreadLocalRandom.current().nextInt(1000, 12501));
        this.njPokeAssistant = new Chansey(ThreadLocalRandom.current().nextInt(1, 101));
        LocalDate njRndmTempAdmissionDate = GenericDataBase.genRndmDateByCrrntYears(0, 8);
        this.njAdmissionDate = njRndmTempAdmissionDate;
        this.njExpYears = Period.between(njRndmTempAdmissionDate, LocalDate.now()).getYears();
        this.njAvailability = true;
    }
    public NurseJoy(LocalDate njAdmissionDate, boolean njAvailability, String pRegion, String pID, LocalDate pBirthDate, int pPokeDollars){
        super("Joy", pID, pRegion, pBirthDate, pPokeDollars);
        this.njPokeAssistant = new Chansey(100);
        this.njAdmissionDate = njAdmissionDate;
        this.njExpYears = Period.between(njAdmissionDate, LocalDate.now()).getYears();
        this.njAvailability = njAvailability;
    }
    public NurseJoy(Pokemon njPokeAssistant, LocalDate njAdmissionDate, boolean njAvailability, String pRegion, String pID, LocalDate pBirthDate, int pPokeDollars){
        super("Joy", pID, pRegion, pBirthDate, pPokeDollars);
        this.njPokeAssistant = njPokeAssistant;
        this.njAdmissionDate = njAdmissionDate;
        this.njExpYears = Period.between(njAdmissionDate, LocalDate.now()).getYears();
        this.njAvailability = njAvailability;
    }

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
    public void genericDialogue(){
    
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