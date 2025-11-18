package Persons;

import Pokemons.Logic.Items.Item;
import Utility.Constants.TMedalsEnum;
import Pokemons.Pokemon;
import Utility.Validations.PersonValidations;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Trainer extends PokemonHandler{
    private ArrayList<TMedalsEnum> tMedalsList;
    private ArrayList<Item> tItemsList;
    private Pokemon tActivePokemon;
    
    public Trainer(){
        super();
        this.tMedalsList = PersonValidations.gentMedalsList();
        this.tItemsList = new ArrayList<>();
        this.tActivePokemon = null;
    }
    public Trainer(String pName, String pRegion, String pID, LocalDate pBirthDate, int pPokeDollars) {
        super(pName, pRegion, pID, pBirthDate, pPokeDollars);
        this.tMedalsList = new ArrayList<>();
        this.tItemsList = new ArrayList<>();
        this.tActivePokemon = null;
    }    
    public Trainer(ArrayList<TMedalsEnum> tMedalsList, ArrayList<Item> tItemsList, ArrayList<Pokemon> phPokeList, String pName, String pRegion, String pID, LocalDate pBirthDate, int pPokeDollars) {
        super(phPokeList, pName, pRegion, pID, pBirthDate, pPokeDollars);
        this.tMedalsList = tMedalsList;
        this.tItemsList = tItemsList;
        this.tActivePokemon = null;
    }

    public void settMedalsList(ArrayList<TMedalsEnum> tMedalsList){
        this.tMedalsList = tMedalsList;
    }
    public void addtMedal(TMedalsEnum tMedal){
        this.tMedalsList.add(tMedal);
    }
    public void settItemList(ArrayList<Item> tItemList){
        this.tItemsList = tItemList;
    }
    public void addtItem(Item tItem){
        this.tItemsList.add(tItem);
    }
    public void settActivePokemon(Pokemon tActivePokemon){
        if(tActivePokemon == null){
            this.tActivePokemon = null;
            return;
        }
        if(super.phPokeList.contains(tActivePokemon) && tActivePokemon.getPkHp() > 0){
            this.tActivePokemon = tActivePokemon;
        }
    }

    public ArrayList<TMedalsEnum> gettMedalsList(){
        return this.tMedalsList;
    }
    public TMedalsEnum gettMedal(int tMedalPst){
        try{
            return this.tMedalsList.get(tMedalPst);
        }catch(ArrayIndexOutOfBoundsException e){
            return null;
        }
    }
    public String gettMedalsListStr(){
        if(this.tMedalsList.isEmpty()){
            return "No medals yet...";
        }
        String str = "";
        for(TMedalsEnum tryMedal: this.tMedalsList){
            str += tryMedal + ", ";
        }
        return str;
    }
    public ArrayList<Item> gettItemList(){
        return tItemsList;
    }
    public Item gettItem(int tItemPst){
        try{
            return this.tItemsList.get(tItemPst);
        }catch(ArrayIndexOutOfBoundsException e){
            return null;
        }
    } 
    public String gettItemListStr(){
        if(this.tItemsList.isEmpty()){
            return "No items to use!";
        }
        String str = "";
        for(Item tryItem: this.tItemsList){
            str += tryItem.toString() + "\n";
        }
        return str;
    }
    public Pokemon getActivePokemon(){
        if(this.tActivePokemon == null || this.tActivePokemon.getPkHp() <= 0){
            for(Pokemon tryPokemon: super.phPokeList){
                if(tryPokemon.getPkHp() > 0){
                    this.tActivePokemon = tryPokemon;
                    return this.tActivePokemon;
                }
            }
            return null;
        }
        return this.tActivePokemon;
    }

    @Override
    public String toString(){
        return "-----Trainer Information-----\n" + super.toString() + "\nMedals:\t" + this.gettMedalsListStr() + "\n\t-Pokemons-\n" + this.getPhPokeListStr() + "\n\t-Items-\n" +this.gettItemListStr();
    }
    
    @Override
    public String getPhPokeListStr(){
        if(super.phPokeList.isEmpty()){
            return "No Pokémon have been assigned to this Trainer.";
        }
        String str = "";
        for(Pokemon pokemonTry: super.phPokeList){
            str += pokemonTry.toString();
        }
        return str;
    }

    @Override
    public String genericDialogue() {
        switch(ThreadLocalRandom.current().nextInt(4)){
            case 0 -> {return "Our eyes met! We have to battle!";}
            case 1 -> {return "Do you think you can beat me and my team?";}
            case 2 -> {return "I was waiting for a good fight! Let's go!";}
            case 3 -> {return "Let's see what you're made of!";}
            default -> {return "Get ready...";}
        }
    }
    
    public boolean hastAlivePokemon(){
        for(Pokemon tryPokemon: super.phPokeList){
            if(tryPokemon.getPkHp() > 0){
                return true;
            }
        }
        return false;
    }
    
    public void removetItem(Item tItem){
        this.tItemsList.remove(tItem);
    }
}