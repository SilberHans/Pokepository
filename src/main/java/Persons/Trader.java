package Persons;

import Pokemons.Logic.Items.Item;
import Utility.DataBase.GenericDataBase;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Trader extends Person {

    private double mPriceMultiplier;
    private ArrayList<Item> mInventory;

    public Trader() {
        String mRndmTempRegion = GenericDataBase.getRndmPersonRegion();
        super(GenericDataBase.getRndmTraderName(), mRndmTempRegion, GenericDataBase.genRndmPersonID(2, mRndmTempRegion), GenericDataBase.genRndmDateByCrrntYears(50, 80), ThreadLocalRandom.current().nextInt(2500, 50001));
        this.mPriceMultiplier = (ThreadLocalRandom.current().nextInt(5, 21)) / 10.0;
        this.mInventory = new ArrayList<>();
        this.mInventory.add(new Item("Pocima","Pocima potenciadora folla como un toro crack",2,3));
        this.mInventory.add(new Item("Sopa de garvanzos","Sopita bien rica bien sabrosona sabe a pollo",2,3));
    }

    public Trader(double mPriceMultiplier, String pName, String pID, String pRegion, LocalDate pBirthDate, int pPokeDollars) {
        super(pName, pID, pRegion, pBirthDate, pPokeDollars);
        this.mPriceMultiplier = mPriceMultiplier;
        this.mInventory = new ArrayList<>();
    }

    public Trader(double mPriceMultiplier, ArrayList<Item> mInventory, String pName, String pRegion, String pID, LocalDate pBirthDate, int pPokeDollars) {
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

    public void addmItem(Item mItem) {
        this.mInventory.add(mItem);
    }

    public double getmPriceMultiplier() {
        return this.mPriceMultiplier;
    }

    public ArrayList<Item> getmInventory() {
        return this.mInventory;
    }

    public Item getmItem(int mItemPst) {
        return this.mInventory.get(mItemPst);
    }

    public String[] getmInventoryStr() {
        if (this.mInventory.isEmpty()) {
            return new String[]{"No Items in Stock"};
        }
        String[] itemNames = new String[this.mInventory.size()];
        for (int i = 0; i < this.mInventory.size(); i++) {
            itemNames[i] = this.mInventory.get(i).getiName(); 
        }
        return itemNames;
    }
    
    public String[] getmInventoryDescription(){
        if (this.mInventory.isEmpty()) {
            return new String[]{"No Items in Stock"};
        }
        String[] itemDescript = new String[this.mInventory.size()];
        for (int i = 0; i < this.mInventory.size(); i++) {
            itemDescript[i] = this.mInventory.get(i).iDescription(); 
        }
        return itemDescript;
    }
    
    public BufferedImage[] getmInventoryIMG(){
        if (this.mInventory.isEmpty()) {
            return null;
        }
        BufferedImage[] img = new BufferedImage[this.mInventory.size()];
        for (int i = 0; i < this.mInventory.size(); i++) {
            img[i]= this.mInventory.get(i).getImg(); 
        }
        return img;
    }
    
    @Override
    public String toString() {
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

    public String sellItem(Trainer mTrainer, int mItemPst) {
        try {
            Item tempItem = this.getmItem(mItemPst);
            Random rndmDialog = new Random();
            if (tempItem.getiStock() > 0) {
                mTrainer.addtItem(tempItem);
                switch (rndmDialog.nextInt(3)) {
                    case 0 -> {
                        return "Thanks for your purchase!";
                    }
                    case 1 -> {
                        return "Much appreciated!";
                    }
                    case 2 -> {
                        return "Thank you, come again!";
                    }
                    default -> {
                        return "Uhh...";
                    }
                }
            }
            switch (rndmDialog.nextInt(3)) {
                case 0 -> {
                    return "That item’s sold out.";
                }
                case 1 -> {
                    return "Out of stock...";
                }
                case 2 -> {
                    return "All sold out for now.";
                }
                default -> {
                    return "Uhh...";
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Uhh... Sorry, I don’t carry that item";
        }
    }

    public void genInventory() {

    }
    
    
}
