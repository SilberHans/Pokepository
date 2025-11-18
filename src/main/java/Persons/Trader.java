package Persons;

import Utility.DataBase.GenericDataBase;
import Pokemons.Logic.Items.Item;
import Utility.Validations.PersonValidations;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Trader extends Person{
    private double mPriceMultiplier;
    private HashMap<Item, Integer> mInventory;
    
    public Trader(){
        String mRndmTempRegion = GenericDataBase.getRndmPersonRegion();
        super(GenericDataBase.getRndmTraderName(),mRndmTempRegion, GenericDataBase.genRndmPersonID(2, mRndmTempRegion), GenericDataBase.genRndmDateByCrrntYears(50, 80), ThreadLocalRandom.current().nextInt(2500, 50001));
        System.out.println("DEBUG: [Trader] 1. Iniciando constructor de Trader.");
        this.mPriceMultiplier = (ThreadLocalRandom.current().nextInt(5, 21)) / 10.0;
        
        System.out.println("DEBUG: [Trader] 2. Llamando a mInventoryGenerator()...");
        this.mInventory = PersonValidations.mInventoryGenerator(); // <- SOSPECHOSO #2
        System.out.println("DEBUG: [Trader] 3. mInventoryGenerator() terminado.");
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
        
    public String[] getmInventoryStr() {
        if (this.mInventory.isEmpty()) {
            return new String[]{"No Items in Stock"};
        }
        String[] itemNames = new String[this.mInventory.size()];
        int i = 0;
        
        // Itera sobre el 'keySet()' que contiene todos los 'Item'
        for (Item item : this.mInventory.keySet()) { 
            itemNames[i] = item.getItName(); // Llama al getter del Item
            i++;
        }
        return itemNames;
    }
    
    // AÑADE este nuevo método:
    public String[] getmInventoryDescription(){
        if (this.mInventory.isEmpty()) {
            return new String[]{"No Items in Stock"};
        }
        String[] itemDescript = new String[this.mInventory.size()];
        int i = 0;
        
        // Itera sobre las claves (Item)
        for (Item item : this.mInventory.keySet()) {
            itemDescript[i] = item.getItDescription(); // Llama al nuevo getter
            i++;
        }
        return itemDescript;
    }
    
    // AÑADE este nuevo método:
    public BufferedImage[] getmInventoryIMG(){
        if (this.mInventory.isEmpty()) {
            return null;
        }
        BufferedImage[] img = new BufferedImage[this.mInventory.size()];
        int i = 0;
        
        // Itera sobre las claves (Item)
        for (Item item : this.mInventory.keySet()) {
            img[i]= item.getImg(); // Llama al nuevo getter
            i++;
        }
        return img;
    }
}
