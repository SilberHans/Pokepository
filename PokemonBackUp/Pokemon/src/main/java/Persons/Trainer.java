package Persons;

import OtherClasses.Item;
import Pokemons.Pokemon;
import java.time.LocalDate;
import java.util.ArrayList;

public class Trainer extends PokemonHandler{
    private int tMedals;
    private ArrayList<Item> tItemsList;
    
    public Trainer(){
        super();
        this.tMedals = 0;
        this.tItemsList = new ArrayList<>();
    }
    public Trainer(int tMedals, ArrayList<Item> tConsuList, String pName, String pID, String pRegion, LocalDate pBirthDate, int pPokeDollars){
        super(pName, pID, pRegion, pBirthDate, pPokeDollars);
        this.tMedals = tMedals;
        this.tItemsList = tConsuList;
    }

    public void settMedals(int tMedals){
        this.tMedals = tMedals;
    }
    public void settItemList(ArrayList<Item> tItemList){
        this.tItemsList = tItemList;
    }
    public void addtItemList(Item tItem){
        this.tItemsList.add(tItem);
    }

    public int gettMedals(){
        return tMedals;
    }
    public ArrayList<Item> gettItemList(){
        return tItemsList;
    }
    public Item gettItem(int tItemPst){
        return this.tItemsList.get(tItemPst);
    } 
    public String gettItemListStr(){
        if(this.tItemsList.isEmpty()){
            return "No items to use!";
        }
        String str = "";
        for(Item tryItem: this.tItemsList){
            str += tryItem.toString();
        }
        return str;
    }

    @Override
    public String toString(){
        return "-----Trainer Information-----\n" + super.toString() + "\nMedals:\t" + this.gettMedals() + "\n\t-Pokemons-\n" + this.getpHPokeListStr() + "\n\t-Items-\n" +this.gettItemListStr();
    }
    
    @Override
    public String getpHPokeListStr(){
        if(super.phPokeList.isEmpty()){
            return "No Pokémon have been assigned to this Trainer.";
        }
        String str = "";
        for(Pokemon pokemonTry: super.phPokeList){
            str = pokemonTry.toString();
        }
        return str;
    }

    @Override
    public void genericDialogue() {
    
    }
    
    public void newTrainer(){
        
    }
}