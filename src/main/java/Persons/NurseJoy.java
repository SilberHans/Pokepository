package Persons;

import Pokemons.Pokemon;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
    public NurseJoy(LocalDate njAdmissionDate, int njExpYears, boolean njAvailability, ArrayList<Pokemon> phPokeList, String pName, String pID, String pRegion, LocalDate pBirthDate, int pPokeDollars) {
        super(phPokeList, pName, pID, pRegion, pBirthDate, pPokeDollars);
        this.njAdmissionDate = njAdmissionDate;
        this.njExpYears = njExpYears;
        this.njAvailability = njAvailability;
    }

    public void setNjAdmissionDate(LocalDate njAdmissionDate) {
        this.njAdmissionDate = njAdmissionDate;
    }
    public void setNjExpYears(LocalDate njAdmissionDate) {
        this.njExpYears = Period.between(njAdmissionDate, LocalDate.now()).getYears();
    }
    public void setNjAvailability(boolean njAvailability) {
        this.njAvailability = njAvailability;
    } 

    public LocalDate getNjAdmissionDate() {
        return this.njAdmissionDate;
    }
    public String getNjAdmissionDateStr(){
        return this.njAdmissionDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
    public int getNjExpYears() {
        return this.njExpYears;
    }
    public boolean isNjAvailable() {
        return this.njAvailability;
    }
    
    @Override
    public String toString(){
        return "\n\n-----Nurse Information-----\n" + super.toString() + "\nYear of Admission:\t" + this.getNjAdmissionDateStr() + "\nYears of Experience:\t" + this.getNjExpYears() + "\nAvailable:\t\t" + this.isNjAvailable() + "\n\t-Pokemons to Attend-\n" + this.getPhPokeListStr();
    }
    
    @Override
    public String getPhPokeListStr(){
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