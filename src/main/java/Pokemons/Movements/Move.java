package Pokemons.Movements;

import Pokemons.Logic.PkTypeEnum;

public class Move{
    private String mName;
    private PkMovementTypeEnum mType;
    private PkTypeEnum mPkType;
    private int mPower;
    private PkEffectsEnum mPkEffect;
    private int mPkEffectChance;

    public Move(){
        this.mName = "";
        this.mType = null;
        this.mPkType = null;
        this.mPower = 0;
        this.mPkEffect = null;
        this.mPkEffectChance = 0;
    }
    public Move(String mName, PkMovementTypeEnum mType, PkTypeEnum mPkType, int mPower, PkEffectsEnum mPkEffect, int mPkEffectChance) {
        this.mName = mName;
        this.mType = mType;
        this.mPkType = mPkType;
        this.mPower = mPower;
        this.mPkEffect = mPkEffect;
        this.mPkEffectChance = mPkEffectChance;
    } 

    public void setmName(String mName){
        this.mName = mName;
    }
    public void setmType(PkMovementTypeEnum mType){
        this.mType = mType;
    }
    public void setmPkType(PkTypeEnum mPkType){
        this.mPkType = mPkType;
    }
    public void setmPower(int mPower){
        this.mPower = mPower;
    }
    public void setmPkEffect(PkEffectsEnum mPkEffect){
        this.mPkEffect = mPkEffect;
    }
    public void setmPkEffectChance(int mPkEffectChance){
        this.mPkEffectChance = mPkEffectChance;
    }

    public String getmName() {
        return this.mName;
    }
    public PkMovementTypeEnum getmType() {
        return this.mType;
    }
    public PkTypeEnum getmPkType() {
        return this.mPkType;
    }
    public int getmPower() {
        return this.mPower;
    }
    public PkEffectsEnum getmPkEffect(){
        return mPkEffect;
    }
    public int getmPkEffectChance(){
        return mPkEffectChance;
    }

    @Override
    public String toString() {
        return "Name:\t\t" + this.getmName() + "\nType:\t\t" + this.getmType() + "\nPokemon Type:\t" + this.getmPkType() + "\nPower:\t\t" + this.getmPower() + "\nEffect:\t\t" + this.getmPkEffect() + "\nEffect Chance:\t" + this.getmPkEffectChance();
    }
}