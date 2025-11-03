package Persons;

import OtherClasses.Item;
import java.time.LocalDate;
import java.util.ArrayList;

public class Trader extends Person{
    private double mPriceMultiplier;
    private ArrayList<Item> mInventory;
    
    public Trader(){
        super();
        this.mPriceMultiplier = 0;
        this.mInventory = new ArrayList<>();
    }
    public Trader(double mPriceMultiplier, ArrayList<Item> mInventory, String pName, String pID, String pRegion, LocalDate pBirthDate, int pPokeDollars){
        super(pName, pID, pRegion, pBirthDate, pPokeDollars);
        this.mPriceMultiplier = mPriceMultiplier;
        this.mInventory = mInventory;
    }
    
    
    @Override
    public void genericDialogue() {
    
    }
}