package Pokemons.Logic.Items;

import Pokemons.Logic.PkEffectsEnum;

public class PkItem {
    private final String itName;
    private final int itPrice;
    private final PkEffectsEnum itEffect;
    private final int itEffectValue;

    public PkItem(String itName, int itPrice, PkEffectsEnum itEffect, int itEffectValue){
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
}