package Pokemons;

import GameDesing.GenericDataBase;
import Pokemons.Logic.PkStatsEnum;
import Pokemons.Logic.PkStatus;
import Pokemons.Logic.PkStatusEnum;
import Pokemons.Logic.PkTypeEnum;
import Pokemons.Movements.Move;
import Pokemons.Movements.PkEffectsEnum;
import Utility.PokemonValidations;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Pokemon {
    protected String pkNickName;
    protected int pkLevel;
    protected final int pkIV;
    protected final int pkEV;
    protected int pkHp;
    protected final int pkMaxHp;
    protected final int pkAttack;
    protected final int pkSpecialAttack;
    protected final int pkDefense;
    protected final int pkSpecialDefense;
    protected final int pkSpeed;
    protected final PkTypeEnum pkType1;
    protected final PkTypeEnum pkType2;
    protected ArrayList<Move> pkMoveSet;
    protected PkStatus pkStatus;
    protected Map<PkEffectsEnum, Integer> pkEffects;
    protected Map<PkStatsEnum, Integer> pkStatsStages;
    protected BufferedImage afront;
    protected BufferedImage aback;
    public String[] actions={"ATTACK 1","ATTACK 2","DEFEND","ÄTTACK 3"};
    
    public abstract void loadSprites();
    
    public Pokemon(int pkLevel, PkTypeEnum pkType1, PkTypeEnum pkType2, int pkBaseHp, int pkBaseAttack, int pkBaseSpecialAttack, int pkBaseDefense, int pkBaseSpecialDefense, int pkBaseSpeed){
        this.pkNickName = GenericDataBase.getRndmPokemonNickName();
        this.pkType1 = pkType1;
        this.pkType2 = pkType2;
        this.pkLevel = pkLevel;
        this.pkIV = ThreadLocalRandom.current().nextInt(32);
        this.pkEV = ThreadLocalRandom.current().nextInt(511);
        this.pkMaxHp = (int) (((2*pkBaseHp + this.pkIV + (this.pkEV/24))*this.pkLevel)/100 + this.pkLevel + 10);
        this.pkHp = this.pkMaxHp;
        this.pkAttack = (int) (((2*pkBaseAttack + this.pkIV + (this.pkEV/24))*this.pkLevel)/100 + 5);
        this.pkSpecialAttack = (int) (((2*pkBaseSpecialAttack + this.pkIV + (this.pkEV/24))*this.pkLevel)/100 + 5);
        this.pkDefense = (int) (((2*pkBaseDefense + this.pkIV + (this.pkEV/24))*this.pkLevel)/100 + 5);
        this.pkSpecialDefense = (int) (((2*pkBaseSpecialDefense + this.pkIV + (this.pkEV/24))*this.pkLevel)/100 + 5);
        this.pkSpeed = (int) (((2*pkBaseSpeed + this.pkIV + (this.pkEV/24))*this.pkLevel)/100 + 5);
        this.pkMoveSet = new ArrayList<>(4);
        this.pkStatus = null;
        this.pkEffects = new HashMap<>();
        this.pkStatsStages = new HashMap<>();
        for(PkStatsEnum tryStat: PkStatsEnum.values()){
            this.pkStatsStages.put(tryStat, 0);
        }
    }

    public void setPkNickName(String pkNickName){
        this.pkNickName = PokemonValidations.valPkNickNames(pkNickName);
    }
    public void setPkLevel(int pkLevel) {
        this.pkLevel = pkLevel;
    }
    public void setPkHp(int pkHp) {
        this.pkHp = pkHp;
    }
    public void setPkMoveSet(ArrayList<Move> pkMoveSet) {
        this.pkMoveSet = pkMoveSet;
    }
    public void addPkMove(Move pkMove){
        this.pkMoveSet.add(pkMove);
    }
    public void setPkStatus(PkStatusEnum pkStatus, int pkStatusTurnsLeft){
        if(this.pkStatus == null && pkStatus != null){
            if(pkStatus == PkStatusEnum.Burned && this.hasPkType(PkTypeEnum.Fire)){return;}
            if((pkStatus == PkStatusEnum.Poisoned || pkStatus == PkStatusEnum.BadlyPoisoned) && (this.hasPkType(PkTypeEnum.Poison) || this.hasPkType(PkTypeEnum.Steel))){return;}
            if(pkStatus == PkStatusEnum.Paralyzed && this.hasPkType(PkTypeEnum.Electric)){return;}
            if(pkStatus == PkStatusEnum.Frozen && this.hasPkType(PkTypeEnum.Ice)){return;}
            this.pkStatus = new PkStatus(pkStatus, pkStatusTurnsLeft);
        }
    }
    public void setPkEffect(PkEffectsEnum pkEffect, int pkEffectTurnsLeft){
        this.pkEffects.put(pkEffect, pkEffectTurnsLeft);
    }
    public void changePkStatStage(PkStatsEnum pkStat, int pkStageAmount){
        int newStage = Math.max(-6, Math.min(6, this.pkStatsStages.get(pkStat) + pkStageAmount));
        this.pkStatsStages.replace(pkStat, newStage);
    }
    
    public String getPkNickName(){
        return this.pkNickName;
    }
    public int getPkLevel() {
        return this.pkLevel;
    }
    public int getPkIV(){
        return this.pkIV;
    }
    public String getpkIVStr(){
        if(this.pkIV == 31){
            return "Perfect";
        }else if(this.pkIV >= 26){
            return "Exceptional";
        }else if(this.pkIV >= 21){
            return "Superior";
        }else if(this.pkIV >= 11){
            return "Solid";
        }else if(this.pkIV > 0){
            return "Common";
        }else {
            return "Zero";
        }
    }
    public int getPkEV(){
        return this.pkEV;
    }
    public String getPkEVStr(){
        if (this.pkEV == 510) {
            return "Mastered";
        }else if (this.pkEV >= 401){
            return "Elite";
        }else if (this.pkEV >= 301){
            return "Veteran";
        }else if (this.pkEV >= 201){
            return "Hardened";
        }else if (this.pkEV >= 101){
            return "Skilled";
        }else if (this.pkEV > 0){
            return "Rookie";
        }else{
            return "Untrained";
        }
    }
    public int getPkHp(){
        return this.pkHp;
    }
    public int getPkMaxHp(){
        return this.pkMaxHp;
    }
    public int getPkAttack(){
        return this.pkAttack;
    }
    public int getPkSpecialAttack(){
        return this.pkSpecialAttack;
    }
    public int getPkDefense(){
        return this.pkDefense;
    }
    public int getPkSpecialDefense(){
        return this.pkSpecialDefense;
    }
    public int getPkSpeed(){
        return this.pkSpeed;
    }
    public PkTypeEnum getPkType1(){
        return this.pkType1;
    }
    public PkTypeEnum getPkType2(){
        return this.pkType2;
    }
    public boolean hasPkType(PkTypeEnum pkType){
        return (this.pkType1 == pkType || this.pkType2 == pkType);
    }
    public String getPkTypes(){
        if(this.pkType2 == null){
            return this.pkType1.toString();
        }
        return this.pkType1 + " and " + this.pkType2;
    }
    public ArrayList getPkMoveSet(){
        return this.pkMoveSet;
    }
    public Move getPkMove(int pkMovePst){
        try{
            return this.pkMoveSet.get(pkMovePst);
        }catch(ArrayIndexOutOfBoundsException e){
            return null;
        }
    }
    public String getPkMoveSetStr(){
        if(this.pkMoveSet.isEmpty()){
            return this.getPkNickName() + " hasn’t learned any moves";
        }
        String str = "";
        for(Move tryMove: this.pkMoveSet){
            str += tryMove.toString();
        }
        return str;
    }
    public PkStatus getPkStatus(){
        if (this.pkStatus == null){
            return null;
        }
        return this.pkStatus;
    }
    public Map<PkEffectsEnum, Integer> getPkEffectsAndTurns(){
        return this.pkEffects;
    }
    public Set<PkEffectsEnum> getPkEffects(){
        return this.pkEffects.keySet();
    }
    public int getPkEffectTurnsLeft(PkEffectsEnum pkEffectKey){
        return this.pkEffects.getOrDefault(pkEffectKey, 0);
    }
    public Map<PkStatsEnum, Integer> getPkStatsAndStages(){
        return this.pkStatsStages;
    }
    public int getPkStatStage(PkStatsEnum pkStat){
        return this.pkStatsStages.getOrDefault(pkStat, 0);
    }
    public double getPkStatStageMultiplier(PkStatsEnum pkStat){
        switch(this.pkStatsStages.get(pkStat)){
            case -6 -> {return 0.25;} 
            case -5 -> {return 0.28;}
            case -4 -> {return 0.33;}
            case -3 -> {return 0.4;}
            case -2 -> {return 0.5;}
            case -1 -> {return 0.66;}
            case 0 -> {return 1.0;}
            case 1 -> {return 1.5;}
            case 2 -> {return 2.0;}
            case 3 -> {return 2.5;}
            case 4 -> {return 3.0;}
            case 5 -> {return 3.5;}
            case 6 -> {return 4.0;}
            default -> {return 1.0;}
        }
    }
            
    @Override
    public String toString(){
        return "\nNick Name:\t\t" + this.getPkNickName() + "\nType:\t\t" + this.getPkTypes() + "\nLevel:\t\t\t" + this.getPkLevel() + "\nPotential:\t\t" + this.getpkIVStr() + "\nTraining:\t\t" + this.getPkEVStr() + "\nHealth Points\t\t" + this.getPkHp() + "\nAttack:\t\t\t" + this.getPkAttack() + "\nSpecial Attack:\t\t" + this.getPkSpecialAttack() + "\nDefense:\t\t" + this.getPkDefense() + "\nSpecial Defense:\t" + this.getPkSpecialDefense() + "\nSpeed:\t\t\t" + this.getPkSpeed() + "\t-Movement Set-\n" + this.getPkMoveSetStr() + "\n";
    }
    
    public void pkTakeDamage(int pkDamage){
        this.setPkHp(Math.max(0, this.pkHp - pkDamage));
    }
    
    public void pkHeal(int pkHeal){
        this.setPkHp(Math.min(this.pkMaxHp, this.pkHp + pkHeal));
    }
    
    public void pkResetStatStages(){
        this.pkStatsStages.clear();
        for(PkStatsEnum tryStat: PkStatsEnum.values()){
            this.pkStatsStages.put(tryStat, 0);
        }
    }
    
    public void pkUpdateStatus(){
        if(this.pkStatus == null){return;}
        this.pkStatus.decreasePkStatusTurnsLeft();
        if(this.pkStatus.getPkStatusTurnsLeft() == 0){
            this.pkStatus = null;
        }
    }
    
    public void pkCureStatus(){
        this.pkStatus = null;
    }
    
    public void pkUpdateEffects(){
        Iterator<Map.Entry<PkEffectsEnum, Integer>> pkEffectsIterator = this.pkEffects.entrySet().iterator();
        while(pkEffectsIterator.hasNext()){
            Map.Entry<PkEffectsEnum, Integer> pkEffect = pkEffectsIterator.next();
            int pkEffectTurnsLeft = pkEffect.getValue();
            if(pkEffectTurnsLeft == -1){continue;}
            pkEffectTurnsLeft--;
            if(pkEffectTurnsLeft <= 0){
                pkEffectsIterator.remove();
            }else{
                pkEffect.setValue(pkEffectTurnsLeft);
            }
        }
    }
    
    public void pkClearEffects(){
        this.pkEffects.clear();
    }
    
    public abstract String pkNoise();
}