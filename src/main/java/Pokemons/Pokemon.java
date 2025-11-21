package Pokemons;

import Persons.Trainer;
import Utility.Constants.PkStatsEnum;
import Utility.Constants.PkTypeEnum;
import Utility.Constants.PkStatusEnum;
import Pokemons.Logic.Movements.Move;
import Utility.Constants.PkEffectsEnum;
import Utility.DataBase.GenericDataBase;
import Pokemons.Logic.*;
import Utility.Validations.PokemonValidations;
import java.awt.Graphics2D;
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
    protected Status pkStatus;
    protected Map<PkEffectsEnum, Integer> pkEffects;
    protected Map<PkStatsEnum, Integer> pkStatsStages;   
    protected BufferedImage afront;
    protected BufferedImage aback;
    public String[] actions={"ATTACK 1","ATTACK 2","DEFEND","ÄTTACK 3"};
    protected transient Trainer pkTrainer; 

    // Añade este método
    public Trainer getTrainer() {
        return this.pkTrainer;
    }
    
    // Añade este método
    public void setTrainer(Trainer trainer) {
        this.pkTrainer = trainer;
    }
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
        this.pkMoveSet = PokemonValidations.pkMovemntsSelection(pkType1, pkType2);
        this.pkStatus = null;
        this.pkEffects = new HashMap<>();
        this.pkStatsStages = new HashMap<>();
        for(PkStatsEnum tryStat: PkStatsEnum.values()){
            this.pkStatsStages.put(tryStat, 0);
        }
        this.loadSprites();
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
            this.pkStatus = new Status(pkStatus, pkStatusTurnsLeft);
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
    public Status getPkStatus(){
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
    public int getEffectivePkSpeed() {
        int pkEffectiveSpeed = (int) (this.getPkSpeed() * getPkStatStageMultiplier(PkStatsEnum.Speed));
        if (this.pkStatus != null && this.pkStatus.getStatus() == PkStatusEnum.Paralyzed) {
            pkEffectiveSpeed *= 0.5;
    }
    return pkEffectiveSpeed;
    }
    public int getEffectivePkAttack() {
        int pkEffectiveAttack = (int) (this.getPkAttack() * getPkStatStageMultiplier(PkStatsEnum.Attack));
        return pkEffectiveAttack;
    }
    public int getEffectivePkDefense() {
        int pkEffectiveDefense = (int) (this.getPkDefense() * getPkStatStageMultiplier(PkStatsEnum.Defense));
        return pkEffectiveDefense;
    }
    public int getEffectivePkSpecialAttack() {
        int pkEffectiveSpAttack = (int) (this.getPkSpecialAttack() * getPkStatStageMultiplier(PkStatsEnum.SpecialAttack));
        return pkEffectiveSpAttack;
    }
    public int getEffectivePkSpecialDefense() {
        int pkEffectiveSpDefense = (int) (this.getPkSpecialDefense() * getPkStatStageMultiplier(PkStatsEnum.SpecialDefense));
        return pkEffectiveSpDefense;
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
        this.pkStatus.updateStatusCounter();
        if (this.pkStatus.getStatus() == PkStatusEnum.Asleep || this.pkStatus.getStatus() == PkStatusEnum.Frozen){
            if(this.pkStatus.getStatusCounter() == 0){
                this.pkStatus = null;
            }
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

    
    ////GRAFICOS DEL POKEMONNN

    public int x, y; // posicion 
    public int defaultX, defaultY; // posicion default
    public boolean isVisible = true; // parpadeo
    public final int BATTLE_SPRITE_SIZE = 200; // tamaño
    
    private boolean isAnimating = false;
    private String animationType = "none";
    private int animationCounter = 0;
    private int animationState = 0; // Para animaciones multiples
    private int currentDrawSize = BATTLE_SPRITE_SIZE; // NUEVO: Tamaño actual para escalar
    private final int MAX_GROWTH=40;
    
    public void setDefaultBattlePosition(int x, int y) {
        this.x = x;
        this.y = y;
        this.defaultX = x;
        this.defaultY = y;
    }
    
    public boolean isAnimating() {
        return this.isAnimating;
    }
    
    //INICIA ANIMACION YA SEA ATAQUE O DAÑO
    public void startAnimation(String type) {
        if (isAnimating) return; // No empezar si ya hay
        this.animationType = type;
        this.isAnimating = true;
        this.animationCounter = 0;
        this.animationState = 0;
        this.isVisible = true;
        
        
        this.x = this.defaultX;
        this.y = this.defaultY;
        this.currentDrawSize = BATTLE_SPRITE_SIZE;
    }

    //DIBUJA SEGUN SI ES EL 1 O ES EL 2
    public void draw(Graphics2D g2, boolean isPlayerOne) {
        if (!isVisible) return; // No dibujar si esta parpadeando
        
        BufferedImage image = isPlayerOne ? aback : afront;
        
        if (image != null) {
            g2.drawImage(image, x, y, BATTLE_SPRITE_SIZE, BATTLE_SPRITE_SIZE, null);
        }
    }
    
    //actualiza animacion 60fps
    public void updateAnimation() {
        if (!isAnimating) return;
        
        animationCounter++;
        
        switch (animationType) {
            case "damage":
                if (animationCounter % 10 < 5) {
                    isVisible = false;
                } else {
                    isVisible = true;
                }
                
                // Duración total de 60 frames (1 segundo)
                if (animationCounter > 60) {
                    isVisible = true;
                    isAnimating = false;
                    animationType = "none";
                }
                break;
                
            case "attack":
                // Animación de movimiento (va y vuelve, dos veces)
                int speed = 5; // Píxeles por frame
                int distance = 30; // Distancia a moverse
                
                // Determina la dirección del ataque (T1 ataca a la derecha, T2 a la izquierda)
                // Asumimos que T2 (oponente) siempre tendrá un defaultX > 400
                int direction = (defaultX > 400) ? -1 : 1; 

                switch (animationState) {
                    case 0: // "Va" 1
                        x += speed * direction;
                        if (Math.abs(x - defaultX) >= distance) {
                            x = defaultX + (distance * direction);
                            animationState = 1;
                        }
                        break;
                    case 1: // "Vuelve" 1
                        x -= speed * direction;
                        if ((direction == 1 && x <= defaultX) || (direction == -1 && x >= defaultX)) {
                            x = defaultX;
                            animationState = 2;
                        }
                        break;
                    case 2: // "Va" 2
                        x += speed * direction;
                        if (Math.abs(x - defaultX) >= distance) {
                            x = defaultX + (distance * direction);
                            animationState = 3;
                        }
                        break;
                    case 3: // "Vuelve" 2
                        x -= speed * direction;
                        if ((direction == 1 && x <= defaultX) || (direction == -1 && x >= defaultX)) {
                            x = defaultX;
                            animationState = 4; // Terminado
                        }
                        break;
                    case 4: // Fin
                        isAnimating = false;
                        animationType = "none";
                        break;
                }
                break;
            case "item":
                int animSpeed = 30; // 30 frames total (15 crecer, 15 encoger)
                int growthAmount;

                if (animationCounter <= 15) { // Creciendo (frames 0-15)
                    // Progresión de 0.0 a 1.0
                    double progress = (double) animationCounter / 15.0; 
                    growthAmount = (int) (MAX_GROWTH * progress);
                } else { // Encogiendo (frames 16-30)
                    // Progresión de 1.0 a 0.0
                    double progress = (double) (animationCounter - 15) / 15.0; 
                    growthAmount = (int) (MAX_GROWTH * (1.0 - progress));
                }

                // Aplicar tamaño y posición
                currentDrawSize = BATTLE_SPRITE_SIZE + growthAmount;
                x = defaultX - (growthAmount / 2); // Ajustar X para centrar
                y = defaultY - growthAmount;       // Ajustar Y para mantener el suelo
                
                // Fin de la animación
                if (animationCounter > animSpeed) {
                    isAnimating = false;
                    animationType = "none";
                    x = defaultX;
                    y = defaultY;
                    currentDrawSize = BATTLE_SPRITE_SIZE;
                }
                break;
            // --- FIN DE NUEVO CÓDIGO ---
        }
    }  
}



