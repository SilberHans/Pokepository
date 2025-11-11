package Persons;

import GameDesing.GenericDataBase;
import Items.Item;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Trader extends Person{
    private double mPriceMultiplier;
    private ArrayList<Item> mInventory;
    
    public Trader(){
        String mRndmTempRegion = GenericDataBase.getRndmPersonRegion();
        super(GenericDataBase.getRndmTraderName(),mRndmTempRegion, GenericDataBase.genRndmPersonID(2, mRndmTempRegion), GenericDataBase.genRndmDateByCrrntYears(50, 80), ThreadLocalRandom.current().nextInt(2500, 50001));
        this.mPriceMultiplier = (ThreadLocalRandom.current().nextInt(5, 21)) / 10.0;
        this.mInventory = new ArrayList<>();
    }
    public Trader(double mPriceMultiplier, String pName, String pID, String pRegion, LocalDate pBirthDate, int pPokeDollars) {
        super(pName, pID, pRegion, pBirthDate, pPokeDollars);
        this.mPriceMultiplier = mPriceMultiplier;
        this.mInventory = new ArrayList<>();
    }
    public Trader(double mPriceMultiplier, ArrayList<Item> mInventory, String pName, String pRegion, String pID, LocalDate pBirthDate, int pPokeDollars){
        super(pName, pRegion, pID, pBirthDate, pPokeDollars);
        this.mPriceMultiplier = mPriceMultiplier;
        this.mInventory = mInventory;
    }

    public void setmPriceMultiplier(double mPriceMultiplier) {
        this.mPriceMultiplier = mPriceMultiplier;
    }
    public void setmInventory(ArrayList<Item> mInventory) {
        this.mInventory = mInventory;
    }
    public void addmItem(Item mItem){
        this.mInventory.add(mItem);
    }

    public double getmPriceMultiplier() {
        return this.mPriceMultiplier;
    }
    public ArrayList<Item> getmInventory() {
        return this.mInventory;
    }
    public Item getmItem(int mItemPst){
        return this.mInventory.get(mItemPst);
    }
    public String getmInventoryStr(){
        String str = "";
        for(Item tryItem: this.mInventory){
            str += tryItem.toString();
        } 
        if(!str.equals("")){
           return str; 
        }
        return "No Items in Stock Yet...";
    }
    
    @Override
    public String toString(){
        return "\t-----Mercader Information-----" + super.toString() + "\nPrice Multiplier:\t" + this.getmPriceMultiplier() + "\n\t-Inventory-\n" + this.getmInventoryStr();
    } 
    
    @Override
    public void genericDialogue() {
    
    }
    
    public String sellItem(Trainer mTrainer, int mItemPst){
        try{
            Item tempItem = this.getmItem(mItemPst);
            Random rndmDialog = new Random();
            if(tempItem.getiStock() >0){
                mTrainer.addtItem(tempItem);
                switch(rndmDialog.nextInt(3)){
                    case 0 -> {return "Thanks for your purchase!";}
                    case 1 -> {return "Much appreciated!";}
                    case 2 -> {return "Thank you, come again!";}
                    default -> {return "Uhh...";}
                }
            }
            switch(rndmDialog.nextInt(3)){
                case 0 -> {return "That item’s sold out.";}
                case 1 -> {return "Out of stock...";}
                case 2 -> {return "All sold out for now.";}
                default -> {return "Uhh...";}
            }
        }catch(ArrayIndexOutOfBoundsException e){
            return "Uhh... Sorry, I don’t carry that item";
        }
    }
    
    public void genInventory(){
        
    }
}