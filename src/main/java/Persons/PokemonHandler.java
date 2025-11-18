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
    public PokemonHandler(String pName, String pRegion, String pID, LocalDate pBirthDate, int pPokeDollars){
        super(pName, pRegion, pID, pBirthDate, pPokeDollars);
        phPokeList = new ArrayList<>();
    }
    public PokemonHandler(ArrayList<Pokemon> phPokeList, String pName, String pRegion, String pID, LocalDate pBirthDate, int pPokeDollars) {
        super(pName, pRegion, pID, pBirthDate, pPokeDollars);
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
        try{
            return this.phPokeList.get(phPokemonPst);
        }catch(ArrayIndexOutOfBoundsException e){
            return null;
        }
    }
    public abstract String getPhPokeListStr();
    
    @Override
    public String toString(){
        return super.toString();
    }
}