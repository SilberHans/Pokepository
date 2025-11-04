package Persons;

import Pokemons.Pokemon;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class PokemonHandler extends Person{
    ArrayList<Pokemon> phPokeList;
    
    public PokemonHandler(){
        super();
        phPokeList = new ArrayList<>();
    }
    public PokemonHandler(String pName, String pID, String pRegion, LocalDate pBirthDate, int pPokeDollars){
        super(pName, pID, pRegion, pBirthDate, pPokeDollars);
        phPokeList = new ArrayList<>();
    }
    public PokemonHandler(ArrayList<Pokemon> phPokeList, String pName, String pID, String pRegion, LocalDate pBirthDate, int pPokeDollars) {
        super(pName, pID, pRegion, pBirthDate, pPokeDollars);
        this.phPokeList = phPokeList;
    }
    
    public void setPhPokeList(ArrayList phPokeList){
        this.phPokeList = phPokeList;
    }
    public void addPhPokemon(Pokemon phPokemon){
        this.phPokeList.add(phPokemon);
    }
    
    public ArrayList getPhPokeList(){
        return this.phPokeList;
    }
    public Pokemon getPhPokemon(int phPokemonPst){
        return this.phPokeList.get(phPokemonPst);
    }
    public abstract String getPhPokeListStr();
    
    @Override
    public String toString(){
        return super.toString();
    }
}