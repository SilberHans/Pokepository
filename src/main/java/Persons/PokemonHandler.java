package Persons;

import Pokemons.Pokemon;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

public abstract class PokemonHandler extends Person{
    ArrayList<Pokemon> phPokeList;
    
    public int x,y;
    public int speed;
    public BufferedImage a1,a2,a3,a4,a5;
    public String direction;
    public int spriteCounter;
    public int spriteNum;
    

    
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