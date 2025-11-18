package Pokemons.Logic.Items;

import Utility.Constants.PkEffectsEnum;
import java.awt.image.BufferedImage;

public class Item {
    private String itName;
    private int itPrice;
    private PkEffectsEnum itEffect;
    private int itEffectValue;

    public Item(String itName, int itPrice, PkEffectsEnum itEffect, int itEffectValue){
        this.itName = itName;
        this.itPrice = itPrice;
        this.itEffect = itEffect;
        this.itEffectValue = itEffectValue;
    }
    
    public String getItName(){
        return itName;
    }
    public int getItPrice(){
        return itPrice;
    }
    public PkEffectsEnum getItEffect(){
        return itEffect;
    }
    public int getItEffectValue(){
        return itEffectValue;
    }
    
    @Override
    public String toString(){
        return "\nName:\t" + this.getItName() + "\nPrice:\t" + this.getItPrice() + "\nEffects:\t" + this.getItEffect() + "\nValue:\t" + this.getItEffectValue();
    }
    

    private String iName;
    private int iPrice;
    private int iStock;
    private BufferedImage img;
    private String iDescription;
    
    public Item(){
        this.iName = "";
        this.iPrice = 0;
        this.iStock = 0;
        this.img=null;
    }
    public Item(String iName, String iDescription,int iPrice, int iStock ) {
        this.iName = iName;
        this.iDescription=iDescription;
        this.iPrice = iPrice;
        this.iStock = iStock;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }
    public void setiPrice(int iPrice) {
        this.iPrice = iPrice;
    }
    public void setiStock(int iStock) {
        this.iStock = iStock;
    }
    public void setiIMG(BufferedImage img){
        this.img=img;
    }

    public BufferedImage getImg() {
        return img;
    }
    
    public String getiName() {
        return this.iName;
    }
    public int getiPrice() {
        return this.iPrice;
    }
    public int getiStock() {
        return this.iStock;
    }

    
    public String iDescription(){
        return this.iDescription;
    }
    
}