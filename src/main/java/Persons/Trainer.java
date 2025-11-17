package Persons;

import Pokemons.Logic.Items.PkItem;
import Utility.MedalsEnum;
import Pokemons.Pokemon;
import Utility.PersonValidations;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Trainer extends PokemonHandler{
    private ArrayList<MedalsEnum> tMedalsList;
    private ArrayList<PkItem> tItemsList;
    
    public Trainer(){
        super();
        this.tMedalsList = PersonValidations.gentMedalsList();
        this.tItemsList = new ArrayList<>();
    }
    public Trainer(String pName, String pRegion, String pID, LocalDate pBirthDate, int pPokeDollars) {
        super(pName, pRegion, pID, pBirthDate, pPokeDollars);
        this.tMedalsList = new ArrayList<>();
        this.tItemsList = new ArrayList<>();
    }    
    public Trainer(ArrayList<MedalsEnum> tMedalsList, ArrayList<PkItem> tItemsList, ArrayList<Pokemon> phPokeList, String pName, String pRegion, String pID, LocalDate pBirthDate, int pPokeDollars) {
        super(phPokeList, pName, pRegion, pID, pBirthDate, pPokeDollars);
        this.tMedalsList = tMedalsList;
        this.tItemsList = tItemsList;
    }

    public void settMedalsList(ArrayList<MedalsEnum> tMedalsList){
        this.tMedalsList = tMedalsList;
    }
    public void addtMedal(MedalsEnum tMedal){
        this.tMedalsList.add(tMedal);
    }
    public void settItemList(ArrayList<PkItem> tItemList){
        this.tItemsList = tItemList;
    }
    public void addtItem(PkItem tItem){
        this.tItemsList.add(tItem);
    }

    public ArrayList<MedalsEnum> gettMedalsList(){
        return this.tMedalsList;
    }
    public MedalsEnum gettMedal(int tMedalPst){
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
        for(MedalsEnum tryMedal: this.tMedalsList){
            str += tryMedal + ", ";
        }
        return str;
    }
    public ArrayList<PkItem> gettItemList(){
        return tItemsList;
    }
    public PkItem gettItem(int tItemPst){
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
        for(PkItem tryItem: this.tItemsList){
            str += tryItem.toString() + "\n";
        }
        return str;
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
            str = pokemonTry.toString();
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
}