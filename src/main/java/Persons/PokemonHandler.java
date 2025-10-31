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
    
    public void setphPokeList(ArrayList phPokeList){
        this.phPokeList = phPokeList;
    }
    public void addphPokemon(Pokemon phPokemon){
        this.phPokeList.add(phPokemon);
    }
    
    public ArrayList getphPokeList(){
        return this.phPokeList;
    }
    public Pokemon getPokemon(int phPokemonPst){
        return phPokeList.get(phPokemonPst);
    }
    public abstract String getpHPokeListStr();
    
    @Override
    public String toString(){
        return super.toString();
    }
}