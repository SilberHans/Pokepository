package Pokemons.Logic.Items;

import Utility.Constants.PkEffectsEnum;

public class Item {
    private final String itName;
    private final String itDescription;
    private final int itPrice;
    private final PkEffectsEnum itEffect;
    private final int itEffectValue;

    public Item(String itName, String itDescription, int itPrice, PkEffectsEnum itEffect, int itEffectValue){
        this.itName = itName;
        this.itDescription = itDescription;
        this.itPrice = itPrice;
        this.itEffect = itEffect;
        this.itEffectValue = itEffectValue;
    }
    
    public String getItName(){
        return itName;
    }
    public String getItDescription(){
        return this.itDescription;
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
        return "\nName:\t" + this.getItName() + "\nDescription:\t" + this.getItDescription() + "\nPrice:\t" + this.getItPrice() + "\nEffects:\t" + this.getItEffect() + "\nValue:\t" + this.getItEffectValue();
    }
}