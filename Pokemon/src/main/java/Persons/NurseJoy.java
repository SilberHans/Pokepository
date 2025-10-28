package Persons;

import Pokemons.Pokemon;
import java.time.LocalDate;
import java.time.Period;

public class NurseJoy extends PokemonHandler{
    private LocalDate njAdmissionDate;
    private int njExpYears;
    private boolean njAvailability;
    
    public NurseJoy(){
        super();
        this.njAdmissionDate = null;
        this.njExpYears = 0;
        this.njAvailability = false;
    }
    public NurseJoy(LocalDate njAdmissionDate, boolean njAvailability, String pName, String pID, String pRegion, LocalDate pBirthDate, int pPokeDollars){
        super(pName, pID, pRegion, pBirthDate, pPokeDollars);
        this.njAdmissionDate = njAdmissionDate;
        this.njExpYears = Period.between(njAdmissionDate, LocalDate.now()).getYears();
        this.njAvailability = njAvailability;
    }

    public void setnjAdmissionDate(LocalDate njAdmissionDate) {
        this.njAdmissionDate = njAdmissionDate;
    }
    public void setnjExpYears(LocalDate njAdmissionDate) {
        this.njExpYears = Period.between(njAdmissionDate, LocalDate.now()).getYears();;
    }
    public void setNjAvailability(boolean njAvailability) {
        this.njAvailability = njAvailability;
    } 

    public LocalDate getNjAdmissionDate() {
        return njAdmissionDate;
    }
    public int getNjExpYears() {
        return njExpYears;
    }
    public boolean isNjAvailability() {
        return njAvailability;
    }
    
    @Override
    public String toString(){
        return "-----Nurse Joy Information-----\n" + super.toString() + "\nYear of Admission:\t" + 
    }
    
    
    @Override
    public String getpHPokeListStr(){
        if(super.phPokeList.isEmpty()){
            return "No Pokémon have been assigned to this Nurse Joy.";
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
    
}