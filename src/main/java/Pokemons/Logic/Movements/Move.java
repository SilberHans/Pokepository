package Pokemons.Logic.Movements;

import Utility.Constants.PkMovementTypeEnum;
import Utility.Constants.PkLogicEffectsEnum;
import Utility.Constants.PkEffectsEnum;
import Utility.Constants.PkTypeEnum;

public class Move{
    private final String mvName;
    private final PkMovementTypeEnum mvType;
    private final PkTypeEnum mvPkType;
    private final int mvPower;
    private final PkEffectsEnum mvPkEffect;
    private final int mvPkEffectChance;
    private final PkLogicEffectsEnum mvPkLogicEffect;
    private final int mvAccuracy;

    public Move(String mvName, PkMovementTypeEnum mvType, PkTypeEnum mvPkType, int mvPower, PkEffectsEnum mvPkEffect, int mvPkEffectChance, PkLogicEffectsEnum mvPkLogicEffect, int mvAccuracy) {
        this.mvName = mvName;
        this.mvType = mvType;
        this.mvPkType = mvPkType;
        this.mvPower = mvPower;
        this.mvPkEffect = mvPkEffect;
        this.mvPkEffectChance = mvPkEffectChance;
        this.mvPkLogicEffect = mvPkLogicEffect;
        this.mvAccuracy = mvAccuracy;
    } 

    public String getMvName() {
        return this.mvName;
    }
    public PkMovementTypeEnum getMvType() {
        return this.mvType;
    }
    public PkTypeEnum getMvPkType() {
        return this.mvPkType;
    }
    public int getMvPower() {
        return this.mvPower;
    }
    public PkEffectsEnum getMvPkEffect(){
        return this.mvPkEffect;
    }
    public int getMvPkEffectChance(){
        return this.mvPkEffectChance;
    }
    public PkLogicEffectsEnum getMvPkLogicEffect(){
        return this.mvPkLogicEffect;
    }
    public int getMvAccuracy(){
        return this.mvAccuracy;
    }

    @Override
    public String toString() {
        return "Name:\t\t" + this.getMvName() + "\nType:\t\t" + this.getMvType() + "\nPokemon Type:\t" + this.getMvPkType() + "\nPower:\t\t" + this.getMvPower() + "\nEffect:\t\t" + this.getMvPkEffect() + "\nEffect Chance:\t" + this.getMvPkEffectChance() + "\nLogic Effect:\t" + this.getMvPkLogicEffect() + "\nAccuracy:\t" + this.getMvAccuracy();
    }
}