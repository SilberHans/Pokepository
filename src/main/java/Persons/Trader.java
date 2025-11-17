package Persons;

import Utility.DataBase.GenericDataBase;
import Pokemons.Logic.Items.Item;
import Utility.Validations.PersonValidations;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Trader extends Person{
    private double mPriceMultiplier;
    private HashMap<Item, Integer> mInventory;
    
    public Trader(){
        String mRndmTempRegion = GenericDataBase.getRndmPersonRegion();
        super(GenericDataBase.getRndmTraderName(),mRndmTempRegion, GenericDataBase.genRndmPersonID(2, mRndmTempRegion), GenericDataBase.genRndmDateByCrrntYears(50, 80), ThreadLocalRandom.current().nextInt(2500, 50001));
        this.mPriceMultiplier = (ThreadLocalRandom.current().nextInt(5, 21)) / 10.0;
        this.mInventory = PersonValidations.mInventoryGenerator();
    }
    public Trader(double mPriceMultiplier, String pName, String pID, String pRegion, LocalDate pBirthDate, int pPokeDollars) {
        super(pName, pID, pRegion, pBirthDate, pPokeDollars);
        this.mPriceMultiplier = mPriceMultiplier;
        this.mInventory = PersonValidations.mInventoryGenerator();
    }
    public Trader(double mPriceMultiplier, HashMap<Item, Integer> mInventory, String pName, String pRegion, String pID, LocalDate pBirthDate, int pPokeDollars){
        super(pName, pRegion, pID, pBirthDate, pPokeDollars);
        this.mPriceMultiplier = mPriceMultiplier;
        this.mInventory = mInventory;
    }

    public void setmPriceMultiplier(double mPriceMultiplier) {
        this.mPriceMultiplier = mPriceMultiplier;
    }
    public void setmInventory(HashMap<Item, Integer> mInventory) {
        this.mInventory = mInventory;
    }
    public void addmItem(Item mItem, int mStock){
        this.mInventory.put(mItem, mStock);
    }

    public double getmPriceMultiplier() {
        return this.mPriceMultiplier;
    }
    public HashMap<Item, Integer> getmInventory() {
        return this.mInventory;
    }
    public int getmItem(Item mItemKey){
        return this.mInventory.get(mItemKey);
    }
    public String getmInventoryStr(){
        if(this.mInventory.isEmpty()){
            return "No Items in Stock Yet...";
        }
        StringBuilder strBld = new StringBuilder();
        for(HashMap.Entry<Item, Integer> entryItem: this.mInventory.entrySet()){
            strBld.append("\n" + entryItem.getKey().toString() + "\nStock:\t" + entryItem.getValue());
        } 
        return strBld.toString();
    }
    
    @Override
    public String toString(){
        return "\t-----Mercader Information-----" + super.toString() + "\nPrice Multiplier:\t" + this.getmPriceMultiplier() + "\n\t-Inventory-\n" + this.getmInventoryStr();
    } 
    
    @Override
    public String genericDialogue() {
        switch(ThreadLocalRandom.current().nextInt(4)){
            case 0 -> {return "Welcome! How can I help you today?";}
            case 1 -> {return "I've got just what you need! Take a look.";}
            case 2 -> {return "If you have the money, I have the goods.";}
            case 3 -> {return "Potions! Antidotes! You name it!";}
            default -> {return "Uhh...";}
            
        }
    }
    
    public String sellItem(Trainer mTrainer, String itemName){
        Item itemToSell = null;
        for (Item itemInStock : this.mInventory.keySet()){
            if (itemInStock.getItName().equals(itemName)){
                itemToSell = itemInStock;
                break;
            }
        }
        if (itemToSell == null) {
            return "Uhh... Sorry, I don’t carry that item";
        }
        int currentStock = this.mInventory.get(itemToSell);
        if (currentStock > 0){
            int price = (int)(itemToSell.getItPrice() * this.mPriceMultiplier);
            try {
                mTrainer.loseMoney(price);
                this.earnMoney(price);
                mTrainer.addtItem(itemToSell);
                this.mInventory.put(itemToSell, currentStock - 1);

                switch(ThreadLocalRandom.current().nextInt(3)){
                    case 0: return "Thanks for your purchase!";
                    case 1: return "Much appreciated!";
                    case 2: return "Thank you, come again!";
                    default: return "Uhh...";
                }
            }catch(IllegalArgumentException e){
                return e.getMessage();
            }
        }else{
            switch(ThreadLocalRandom.current().nextInt(3)){
                case 0: return "That item’s sold out.";
                case 1: return "Out of stock...";
                case 2: return "All sold out for now.";
                default: return "Uhh...";
            }
        }
    }
}