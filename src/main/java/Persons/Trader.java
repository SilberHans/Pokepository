package Persons;

import Items.Item;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Trader extends Person{
    private double mPriceMultiplier;
    private ArrayList<Item> mInventory;
    
    public Trader(){
        super();
        this.mPriceMultiplier = 0;
        this.mInventory = new ArrayList<>();
    }
    public Trader(double mPriceMultiplier, String pName, String pID, String pRegion, LocalDate pBirthDate, int pPokeDollars) {
        super(pName, pID, pRegion, pBirthDate, pPokeDollars);
        this.mPriceMultiplier = mPriceMultiplier;
        this.mInventory = new ArrayList<>();
    }
    public Trader(double mPriceMultiplier, ArrayList<Item> mInventory, String pName, String pID, String pRegion, LocalDate pBirthDate, int pPokeDollars){
        super(pName, pID, pRegion, pBirthDate, pPokeDollars);
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
        return "\n\n-----Mercader Information-----" + super.toString() + "\nPrice Multiplier:\t" + this.getmPriceMultiplier() + "\n\t-Inventory-\n" + this.getmInventoryStr();
    } 
    
    @Override
    public void genericDialogue() {
    
    }
    
    public String sellItem(Trainer mTrainer, int mItemPst){
        try{
            Item tempItem = this.getmItem(mItemPst);
            Random randomDialog = new Random();
            if(tempItem.getiStock() >0){
                mTrainer.addtItem(tempItem);
                switch(randomDialog.nextInt(3)){
                    case 0 -> {return "Thanks for your purchase!";}
                    case 1 -> {return "Much appreciated!";}
                    case 2 -> {return "Thank you, come again!";}
                    default -> {return "Uhh...";}
                }
            }
            switch(randomDialog.nextInt(3)){
                case 0 -> {return "That item’s sold out.";}
                case 1 -> {return "Out of stock...";}
                case 2 -> {return "All sold out for now.";}
                default -> {return "Uhh...";}
            }
        }catch(ArrayIndexOutOfBoundsException e){
            return "Uhh... Sorry, I don’t carry that item";
        }
    }
}