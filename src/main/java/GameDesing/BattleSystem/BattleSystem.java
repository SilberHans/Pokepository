package GameDesing.BattleSystem;

import Pokemons.Logic.Items.Item; 
import Pokemons.Logic.Movements.Move; 
import Pokemons.Pokemon; 
import Persons.Trainer; //
import Pokemons.Logic.TypeChart;
import Utility.Constants.*; 
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public final class BattleSystem {
    private BattleSystem(){}
    
    public static ArrayList<TurnResult> executeItem(Trainer trainer, Pokemon target, Item item){
        ArrayList<TurnResult> results = new ArrayList<>();
        trainer.removetItem(item);
        switch (item.getItEffect()) {
            case HealFixedAmount:
                if (target.getPkHp() == target.getPkMaxHp()) {
                    results.add(new TurnResult("ITEM_USE_FAILED"));
                    return results;
                }
                target.pkHeal(item.getItEffectValue());
                results.add(new TurnResult("ITEM_HEALED_HP"));
                break;
            case HealPercentage:
                 if (target.getPkHp() == target.getPkMaxHp()) {
                    results.add(new TurnResult("ITEM_USE_FAILED"));
                    return results;
                }
                int healAmount = (int) (target.getPkMaxHp() * (item.getItEffectValue() / 100.0));
                target.pkHeal(healAmount);
                results.add(new TurnResult("ITEM_HEALED_HP"));
                break;
            case CurePoison:
                if (target.getPkStatus() != null && (target.getPkStatus().getStatus() == PkStatusEnum.Poisoned
                        || target.getPkStatus().getStatus() == PkStatusEnum.BadlyPoisoned)) {
                    target.pkCureStatus();
                    results.add(new TurnResult("ITEM_CURED_STATUS"));
                } else {
                    results.add(new TurnResult("ITEM_USE_FAILED"));
                }
                break;
            case CureParalysis:
                if (target.getPkStatus() != null && target.getPkStatus().getStatus() == PkStatusEnum.Paralyzed) {
                    target.pkCureStatus();
                    results.add(new TurnResult("ITEM_CURED_STATUS"));
                } else {
                    results.add(new TurnResult("ITEM_USE_FAILED"));
                }
                break;
            case CureBurn:
                if (target.getPkStatus() != null && target.getPkStatus().getStatus() == PkStatusEnum.Burned) {
                    target.pkCureStatus();
                    results.add(new TurnResult("ITEM_CURED_STATUS"));
                } else {
                    results.add(new TurnResult("ITEM_USE_FAILED"));
                }
                break;
            case CureSleep:
                if (target.getPkStatus() != null && target.getPkStatus().getStatus() == PkStatusEnum.Asleep) {
                    target.pkCureStatus();
                    results.add(new TurnResult("ITEM_CURED_STATUS"));
                } else {
                    results.add(new TurnResult("ITEM_USE_FAILED"));
                }
                break;
            case CureFreeze:
                if (target.getPkStatus() != null && target.getPkStatus().getStatus() == PkStatusEnum.Frozen) {
                    target.pkCureStatus();
                    results.add(new TurnResult("ITEM_CURED_STATUS"));
                } else {
                    results.add(new TurnResult("ITEM_USE_FAILED"));
                }
                break;
            case CureAllStatus:
                if (target.getPkStatus() != null) {
                    target.pkCureStatus();
                    results.add(new TurnResult("ITEM_CURED_STATUS"));
                } else {
                    results.add(new TurnResult("ITEM_USE_FAILED"));
                }
                break;
            case AttackUp2:
                target.changePkStatStage(PkStatsEnum.Attack, 2);
                results.add(new TurnResult("ITEM_STAT_ROSE"));
                break;
            case DefenseUp2:
                target.changePkStatStage(PkStatsEnum.Defense, 2);
                results.add(new TurnResult("ITEM_STAT_ROSE"));
                break;
            case SpeedUp2:
                target.changePkStatStage(PkStatsEnum.Speed, 2);
                results.add(new TurnResult("ITEM_STAT_ROSE"));
                break;
            case SpecialAttackUp2:
                target.changePkStatStage(PkStatsEnum.SpecialAttack, 2);
                results.add(new TurnResult("ITEM_STAT_ROSE"));
                break;
            case SpecialDefenseUp2:
                target.changePkStatStage(PkStatsEnum.SpecialDefense, 2);
                results.add(new TurnResult("ITEM_STAT_ROSE"));
                break;
            case AccuracyUp1:
                target.changePkStatStage(PkStatsEnum.Accuracy, 1);
                results.add(new TurnResult("ITEM_STAT_ROSE"));
                break;
            default:
                results.add(new TurnResult("ITEM_USE_FAILED"));
                break;
        }
        return results;
    }

    public static ArrayList<TurnResult> executeMove(Trainer trainer, Pokemon attacker, Pokemon defender, Move move, 
            Weather weather, Terrain terrain, ArrayList<PkEffectsEnum> defenderHazards){
        ArrayList<TurnResult> results = new ArrayList<>();
        boolean moveMissed = false;
        int damageDealt = 0;
        boolean targetFainted = false;
        boolean attackerFainted = false;
        PkStatusEnum statusApplied = null;
        // --- A. Pre-Move Checks (Status, Confusion, etc.) ---
        TurnResult preTurnCheck = checkPokemonCanMove(attacker, weather, terrain);
        if (preTurnCheck != null) {
            results.add(preTurnCheck);
            if(preTurnCheck.didAttackerFaint()) attackerFainted = true;
            return results;
        }
        // --- B. Accuracy Check ---
        if (!checkAccuracy(attacker, defender, move, weather, terrain)) {
            moveMissed = true;
            results.add(new TurnResult("MOVE_MISSED", move, 0, false, false, null, true));
            return results;
        }
        // --- C. Move Logic ("God Switch" PkLogicEffectsEnum) ---
        switch (move.getMvPkLogicEffect()) {
            case Standard:
            case PrioritySimple:
            case HighCritRate:
            case DoublePowerIfStatus:
            case AttackWithSelfDebuff:
            case VariablePower:
            case FixedDamageEqualsLevel:
                damageDealt = calculateDamage(attacker, defender, move, weather, terrain);
                defender.pkTakeDamage(damageDealt);
                targetFainted = (defender.getPkHp() <= 0);
                TurnResult effectResult = applyMoveEffects(attacker, defender, move, damageDealt, weather, terrain, defenderHazards);
                if (effectResult != null) {
                    results.add(effectResult);
                    statusApplied = effectResult.getStatusApplied();
                    if (effectResult.didAttackerFaint()) {
                        attackerFainted = true;
                    }
                }
                break;
            case NeverMiss:
                damageDealt = calculateDamage(attacker, defender, move, weather, terrain);
                defender.pkTakeDamage(damageDealt);
                targetFainted = (defender.getPkHp() <= 0);
                TurnResult neverMissEffect = applyMoveEffects(attacker, defender, move, damageDealt, weather, terrain, defenderHazards);
                if(neverMissEffect != null) results.add(neverMissEffect);
                break;
            case MultiHit:
                int hitCount = ThreadLocalRandom.current().nextInt(2, 6);
                for (int i = 0; i < hitCount; i++) {
                    if(!checkAccuracy(attacker, defender, move, weather, terrain)) {
                         results.add(new TurnResult("MOVE_MISSED", move, 0, false, false, null, true));
                         continue;
                    }
                    damageDealt = calculateDamage(attacker, defender, move, weather, terrain);
                    defender.pkTakeDamage(damageDealt);
                    targetFainted = (defender.getPkHp() <= 0);
                    results.add(new TurnResult("MOVE_HIT_DAMAGE", move, damageDealt, targetFainted, false, null, false));
                    if (targetFainted) break;
                }
                TurnResult multiHitEffect = applyMoveEffects(attacker, defender, move, 0, weather, terrain, defenderHazards);
                if(multiHitEffect != null) results.add(multiHitEffect);
                break;
            case Suicide:
                damageDealt = calculateDamage(attacker, defender, move, weather, terrain);
                defender.pkTakeDamage(damageDealt);
                targetFainted = (defender.getPkHp() <= 0);
                attacker.pkTakeDamage(attacker.getPkMaxHp());
                attackerFainted = true;
                break;
            case AttackAndSwitch:
                damageDealt = calculateDamage(attacker, defender, move, weather, terrain);
                defender.pkTakeDamage(damageDealt);
                targetFainted = (defender.getPkHp() <= 0);
                results.add(new TurnResult("ATTACK_AND_SWITCH_REQUIRED"));
                break;
            case AttackAndForceSwitch:
                damageDealt = calculateDamage(attacker, defender, move, weather, terrain);
                defender.pkTakeDamage(damageDealt);
                targetFainted = (defender.getPkHp() <= 0);
                if (!targetFainted) {
                    results.add(new TurnResult("FORCE_TARGET_SWITCH"));
                }
                break;
            case MultiStatChange:
            case SetWeather:
            case SetHazard:
            case Protect:
            case Counter:
                TurnResult statusMoveResult = applyMoveEffects(attacker, defender, move, 0, weather, terrain, defenderHazards);
                if (statusMoveResult != null) {
                    results.add(statusMoveResult);
                }
                break;
            case TwoTurn:
                attacker.setPkEffect(PkEffectsEnum.None, 2);
                results.add(new TurnResult("MOVE_IS_CHARGING"));
                break;
            default:
                results.add(new TurnResult("MOVE_NOT_IMPLEMENTED"));
                break;
        }
        // --- D. Package Final Result ---
        String finalMessageKey = (damageDealt > 0) ? "MOVE_HIT_DAMAGE" : "MOVE_USED";
        results.add(0, new TurnResult(finalMessageKey, move, damageDealt, targetFainted, attackerFainted, statusApplied,
                moveMissed));
        return results;
    }

    public static int calculateDamage(Pokemon attacker, Pokemon defender, Move move, Weather weather, Terrain terrain){
        int movePower = move.getMvPower();
        // 0. Fixed Damage Moves
        if (move.getMvPkLogicEffect() == PkLogicEffectsEnum.FixedDamageEqualsLevel
                || move.getMvPkEffect() == PkEffectsEnum.DamageEqualsLevel) {
            return attacker.getPkLevel();
        }
        if (move.getMvName().equals("Super Fang")) {
             return Math.max(1, defender.getPkHp() / 2);
        }
        int attackStat;
        boolean isPhysical = move.getMvType() == PkMovementTypeEnum.Physical;
        // Variable Power Move Logic
        if (movePower <= 0) {
            if(move.getMvPkLogicEffect() == PkLogicEffectsEnum.VariablePower) {
                if(move.getMvName().equals("Electro Ball")) {
                    int ratio = attacker.getEffectivePkSpeed() / Math.max(1, defender.getEffectivePkSpeed());
                    if (ratio >= 4) movePower = 150;
                    else if (ratio >= 3) movePower = 120;
                    else if (ratio >= 2) movePower = 80;
                    else if (ratio >= 1) movePower = 60;
                    else movePower = 40;
                }
                // Gyro Ball Logic
                if(move.getMvName().equals("Gyro Ball")) {
                    movePower = (25 * defender.getEffectivePkSpeed() / Math.max(1, attacker.getEffectivePkSpeed())) + 1;
                    movePower = Math.min(150, movePower);
                }
            }
            if(movePower <= 0) return 0;
        }
        // 1. Get Attack and Defense Stats
        // Foul Play Logic
        if(move.getMvName().equals("Foul Play")) {
            attackStat = defender.getEffectivePkAttack();
        } else {
            attackStat = isPhysical ? attacker.getEffectivePkAttack() : attacker.getEffectivePkSpecialAttack();
        }
        // Psyshock Logic
        int defenseStat;
        if(move.getMvName().equals("Psyshock")) {
            defenseStat = defender.getEffectivePkDefense();
        } else {
            defenseStat = isPhysical ? defender.getEffectivePkDefense() : defender.getEffectivePkSpecialDefense();
        }
        // Sandstorm Sp. Def Boost Logic
        if (!isPhysical && weather.getBsWeather() == BsWeatherEnum.Sandstorm && defender.hasPkType(PkTypeEnum.Rock)) {
            defenseStat = (int)(defenseStat * 1.5);
        }
        // 2. Base Damage Formula
        int damage = (int) ((((2.0 * attacker.getPkLevel() / 5.0) + 2.0) * movePower * (attackStat / (double) defenseStat)
                / 50.0) + 2.0);
        // 3. Weather Modifier
        if (weather.getBsWeather() != null) {
            switch (weather.getBsWeather()) {
                case Sun:
                    if (move.getMvPkType() == PkTypeEnum.Fire) damage *= 1.5;
                    if (move.getMvPkType() == PkTypeEnum.Water) damage *= 0.5;
                    break;
                case Rain:
                    if (move.getMvPkType() == PkTypeEnum.Water) damage *= 1.5;
                    if (move.getMvPkType() == PkTypeEnum.Fire) damage *= 0.5;
                    break;
            }
        }
        // Terrain Boost Logic
        if (terrain.getBsTerrain() != null) {
            if(terrain.getBsTerrain() == BsTerrainEnum.Electric && move.getMvPkType() == PkTypeEnum.Electric) damage *= 1.3;
            if(terrain.getBsTerrain() == BsTerrainEnum.Grassy && move.getMvPkType() == PkTypeEnum.Grass) damage *= 1.3;
            if(terrain.getBsTerrain() == BsTerrainEnum.Psychic && move.getMvPkType() == PkTypeEnum.Psychic) damage *= 1.3;
        }
        // 4. Critical Hit Modifier
        double critChance = 6.25;
        if (move.getMvPkLogicEffect() == PkLogicEffectsEnum.HighCritRate) {
            critChance = 12.5; 
        }
        if (ThreadLocalRandom.current().nextDouble(100) < critChance) { 
            damage *= 1.5;
        }
        // 5. Random Variation (0.85-1.00)
        double randomFactor = ThreadLocalRandom.current().nextDouble(0.85, 1.001);
        damage = (int) (damage * randomFactor);
        // 6. STAB (Same Type Attack Bonus) Modifier
        if (attacker.hasPkType(move.getMvPkType())) {
            damage *= 1.5;
        }
        // 7. Type Effectiveness Modifier
        double typeEffectiveness = TypeChart.getPkEffectiveness(
                move.getMvPkType(),
                defender.getPkType1(),
                defender.getPkType2());
        if(move.getMvName().equals("Freeze-Dry") && defender.hasPkType(PkTypeEnum.Water)) {
            typeEffectiveness = 2.0;
        }
        damage = (int) (damage * typeEffectiveness);
        // 8. Burn Modifier
        if (isPhysical && attacker.getPkStatus() != null
                && attacker.getPkStatus().getStatus() == PkStatusEnum.Burned) {
            damage *= 0.5;
        }
        // 9. Facade / Hex / Venoshock Modifier
        if(move.getMvPkLogicEffect() == PkLogicEffectsEnum.DoublePowerIfStatus) {
            if(move.getMvName().equals("Facade")) {
                if(attacker.getPkStatus() != null) damage *= 2;
            } else {
                if(defender.getPkStatus() != null) damage *= 2;
            }
        }
        return Math.max(1, damage);
    }

    public static TurnResult checkPokemonCanMove(Pokemon attacker, Weather weather, Terrain terrain){
        // Recharge Turn Logic
        if (attacker.getPkEffects().contains(PkEffectsEnum.RechargeTurn)) {
            attacker.getPkEffectsAndTurns().remove(PkEffectsEnum.RechargeTurn);
            return new TurnResult("POKEMON_IS_RECHARGING");
        }
        if (attacker.getPkStatus() != null) {
            PkStatusEnum status = attacker.getPkStatus().getStatus();
            switch (status) {
                case Asleep:
                    if (attacker.getPkStatus().getStatusCounter() > 0) {
                        return new TurnResult("POKEMON_IS_ASLEEP");
                    } else {
                        attacker.pkCureStatus();
                    }
                    break;
                case Frozen:
                    if (ThreadLocalRandom.current().nextInt(100) < 20) {
                        attacker.pkCureStatus();
                    } else {
                        return new TurnResult("POKEMON_IS_FROZEN");
                    }
                    break;
                case Paralyzed:
                    if (ThreadLocalRandom.current().nextInt(100) < 25) {
                        return new TurnResult("POKEMON_IS_PARALYZED");
                    }
                    break;
            }
        }
        if (attacker.getPkEffects().contains(PkEffectsEnum.Flinch)) {
            attacker.getPkEffectsAndTurns().remove(PkEffectsEnum.Flinch);
            return new TurnResult("POKEMON_FLINCHED");
        }
        // Confusion Logic
        if (attacker.getPkEffects().contains(PkEffectsEnum.Confuse)) {
            if (attacker.getPkEffectTurnsLeft(PkEffectsEnum.Confuse) == 0) {
                attacker.getPkEffectsAndTurns().remove(PkEffectsEnum.Confuse);
            } else {
                if (ThreadLocalRandom.current().nextInt(100) < 50) {
                    int selfDamage = (int) ((((2.0 * attacker.getPkLevel() / 5.0) + 2.0) * 40 * (attacker.getEffectivePkAttack() / (double) attacker.getEffectivePkDefense()) / 50.0) + 2.0);
                    attacker.pkTakeDamage(selfDamage);
                    boolean fainted = attacker.getPkHp() <= 0;
                    return new TurnResult("POKEMON_HURT_ITSELF_IN_CONFUSION", null, selfDamage, false, fainted, null, false);
                }
            }
        }
        return null;
    }

    public static boolean checkAccuracy(Pokemon attacker, Pokemon defender, Move move, Weather weather, Terrain terrain){
        if (move.getMvPkLogicEffect() == PkLogicEffectsEnum.NeverMiss) {
            return true;
        }
        int moveAccuracy = move.getMvAccuracy();
        // Weather and Accuracy Logic
        if (weather.getBsWeather() != null) {
            if (weather.getBsWeather() == BsWeatherEnum.Rain && (move.getMvName().equals("Thunder") || move.getMvName().equals("Hurricane"))) return true;
            if (weather.getBsWeather() == BsWeatherEnum.Sun && (move.getMvName().equals("Thunder") || move.getMvName().equals("Hurricane"))) moveAccuracy = 50;
            if (weather.getBsWeather() == BsWeatherEnum.Snow && move.getMvName().equals("Blizzard")) return true;
        }
        double attackerAccuracy = attacker.getPkStatStageMultiplier(PkStatsEnum.Accuracy);
        double defenderEvasion = defender.getPkStatStageMultiplier(PkStatsEnum.Evasion);
        int finalAccuracy = (int) (moveAccuracy * (attackerAccuracy / defenderEvasion));
        return ThreadLocalRandom.current().nextInt(100) < finalAccuracy;
    }

    public static TurnResult applyMoveEffects(Pokemon attacker, Pokemon defender, Move move, int damageDealt, 
            Weather weather, Terrain terrain, ArrayList<PkEffectsEnum> defenderHazards) {
        PkEffectsEnum effect = move.getMvPkEffect();
        int chance = move.getMvPkEffectChance();
        // Handle Self-Debuff Logic (Close Combat, etc.)
        if(move.getMvPkLogicEffect() == PkLogicEffectsEnum.AttackWithSelfDebuff) {
            if(move.getMvName().equals("Close Combat")) {
                attacker.changePkStatStage(PkStatsEnum.Defense, -1);
                attacker.changePkStatStage(PkStatsEnum.SpecialDefense, -1);
            } else if (move.getMvName().equals("Draco Meteor")) {
                 attacker.changePkStatStage(PkStatsEnum.SpecialAttack, -2);
            }
            // Outrage/Petal Dance Confusion Logic
            else if (move.getMvName().equals("Outrage") || move.getMvName().equals("Petal Dance")) {
                attacker.setPkEffect(PkEffectsEnum.Confuse, ThreadLocalRandom.current().nextInt(2, 4));
            }
        }
        if (effect == PkEffectsEnum.None || ThreadLocalRandom.current().nextInt(100) >= chance) {
            return null;
        }
        String messageKey = null;
        PkStatusEnum statusApplied = null;
        boolean attackerFainted = false;
        switch (effect) {
            // --- A. Persistent Status Effects ---
            case Burn:
                if (defender.getPkStatus() == null) {
                    defender.setPkStatus(PkStatusEnum.Burned, -1);
                    statusApplied = PkStatusEnum.Burned;
                    messageKey = "TARGET_WAS_BURNED";
                }
                break;
            case Freeze:
                if (defender.getPkStatus() == null) {
                    defender.setPkStatus(PkStatusEnum.Frozen, ThreadLocalRandom.current().nextInt(2, 6));
                    statusApplied = PkStatusEnum.Frozen;
                    messageKey = "TARGET_WAS_FROZEN";
                }
                break;
            case Paralyze:
                if (defender.getPkStatus() == null) {
                    defender.setPkStatus(PkStatusEnum.Paralyzed, -1);
                    statusApplied = PkStatusEnum.Paralyzed;
                    messageKey = "TARGET_WAS_PARALYZED";
                }
                break;
            case Poison:
                if (defender.getPkStatus() == null) {
                    defender.setPkStatus(PkStatusEnum.Poisoned, -1);
                    statusApplied = PkStatusEnum.Poisoned;
                    messageKey = "TARGET_WAS_POISONED";
                }
                break;
            case BadlyPoisoned:
                if (defender.getPkStatus() == null) {
                    defender.setPkStatus(PkStatusEnum.BadlyPoisoned, 1);
                    statusApplied = PkStatusEnum.BadlyPoisoned;
                    messageKey = "TARGET_WAS_BADLY_POISONED";
                }
                break;
            case Sleep:
                if (defender.getPkStatus() == null) {
                    defender.setPkStatus(PkStatusEnum.Asleep, ThreadLocalRandom.current().nextInt(2, 6));
                    statusApplied = PkStatusEnum.Asleep;
                    messageKey = "TARGET_FELL_ASLEEP";
                }
                break;
            // --- B. Volatile Status Effects ---
            case Confuse:
                if (!defender.getPkEffects().contains(PkEffectsEnum.Confuse)) {
                    defender.setPkEffect(PkEffectsEnum.Confuse, ThreadLocalRandom.current().nextInt(2, 6));
                    messageKey = "TARGET_WAS_CONFUSED";
                }
                break;
            case Flinch:
                defender.setPkEffect(PkEffectsEnum.Flinch, 1);
                messageKey = "TARGET_FLINCHED";
                break;
            case LeechSeed:
                if (!defender.getPkEffects().contains(PkEffectsEnum.LeechSeed) && !defender.hasPkType(PkTypeEnum.Grass)) {
                    defender.setPkEffect(PkEffectsEnum.LeechSeed, -1);
                    messageKey = "TARGET_WAS_SEEDED";
                }
                break;
            case Protect:
                attacker.setPkEffect(PkEffectsEnum.Protect, 1);
                messageKey = "ATTACKER_IS_PROTECTED";
                break;
            // --- C. Attacker Buffs ---
            case AttackUp1: attacker.changePkStatStage(PkStatsEnum.Attack, 1); messageKey = "ATTACKER_STAT_ROSE"; break;
            case AttackUp2: attacker.changePkStatStage(PkStatsEnum.Attack, 2); messageKey = "ATTACKER_STAT_ROSE"; break;
            case DefenseUp1: attacker.changePkStatStage(PkStatsEnum.Defense, 1); messageKey = "ATTACKER_STAT_ROSE"; break;
            case DefenseUp2: attacker.changePkStatStage(PkStatsEnum.Defense, 2); messageKey = "ATTACKER_STAT_ROSE"; break;
            case SpeedUp1: attacker.changePkStatStage(PkStatsEnum.Speed, 1); messageKey = "ATTACKER_STAT_ROSE"; break;
            case SpeedUp2: attacker.changePkStatStage(PkStatsEnum.Speed, 2); messageKey = "ATTACKER_STAT_ROSE"; break;
            case SpecialAttackUp1: attacker.changePkStatStage(PkStatsEnum.SpecialAttack, 1); messageKey = "ATTACKER_STAT_ROSE"; break;
            case SpecialAttackUp2: attacker.changePkStatStage(PkStatsEnum.SpecialAttack, 2); messageKey = "ATTACKER_STAT_ROSE"; break;
            case SpecialDefenseUp1: attacker.changePkStatStage(PkStatsEnum.SpecialDefense, 1); messageKey = "ATTACKER_STAT_ROSE"; break;
            case SpecialDefenseUp2: attacker.changePkStatStage(PkStatsEnum.SpecialDefense, 2); messageKey = "ATTACKER_STAT_ROSE"; break;
            case EvasionUp1: attacker.changePkStatStage(PkStatsEnum.Evasion, 1); messageKey = "ATTACKER_STAT_ROSE"; break;
            case AllStatsUp1:
                attacker.changePkStatStage(PkStatsEnum.Attack, 1);
                attacker.changePkStatStage(PkStatsEnum.Defense, 1);
                attacker.changePkStatStage(PkStatsEnum.Speed, 1);
                attacker.changePkStatStage(PkStatsEnum.SpecialAttack, 1);
                attacker.changePkStatStage(PkStatsEnum.SpecialDefense, 1);
                messageKey = "ATTACKER_ALL_STATS_ROSE";
                break;
            case DragonDance:
                attacker.changePkStatStage(PkStatsEnum.Attack, 1);
                attacker.changePkStatStage(PkStatsEnum.Speed, 1);
                messageKey = "ATTACKER_STAT_ROSE";
                break;
            case CalmMind:
                attacker.changePkStatStage(PkStatsEnum.SpecialAttack, 1);
                attacker.changePkStatStage(PkStatsEnum.SpecialDefense, 1);
                messageKey = "ATTACKER_STAT_ROSE";
                break;
            case QuiverDance:
                attacker.changePkStatStage(PkStatsEnum.SpecialAttack, 1);
                attacker.changePkStatStage(PkStatsEnum.SpecialDefense, 1);
                attacker.changePkStatStage(PkStatsEnum.Speed, 1);
                messageKey = "ATTACKER_STAT_ROSE";
                break;
            case AttackDefenseUp1:
                attacker.changePkStatStage(PkStatsEnum.Attack, 1);
                attacker.changePkStatStage(PkStatsEnum.Defense, 1);
                messageKey = "ATTACKER_STAT_ROSE";
                break;
            case AttackAccuracyUp1:
                attacker.changePkStatStage(PkStatsEnum.Attack, 1);
                attacker.changePkStatStage(PkStatsEnum.Accuracy, 1);
                messageKey = "ATTACKER_STAT_ROSE";
                break;
            case AttackDefenseAccuracyUp1:
                attacker.changePkStatStage(PkStatsEnum.Attack, 1);
                attacker.changePkStatStage(PkStatsEnum.Defense, 1);
                attacker.changePkStatStage(PkStatsEnum.Accuracy, 1);
                messageKey = "ATTACKER_STAT_ROSE";
                break;
            // --- D. Defender Debuffs ---
            case AttackDown1: defender.changePkStatStage(PkStatsEnum.Attack, -1); messageKey = "TARGET_STAT_FELL"; break;
            case AttackDown2: defender.changePkStatStage(PkStatsEnum.Attack, -2); messageKey = "TARGET_STAT_FELL"; break;
            case DefenseDown1: defender.changePkStatStage(PkStatsEnum.Defense, -1); messageKey = "TARGET_STAT_FELL"; break;
            case DefenseDown2: defender.changePkStatStage(PkStatsEnum.Defense, -2); messageKey = "TARGET_STAT_FELL"; break;
            case SpeedDown1: defender.changePkStatStage(PkStatsEnum.Speed, -1); messageKey = "TARGET_STAT_FELL"; break;
            case SpeedDown2: defender.changePkStatStage(PkStatsEnum.Speed, -2); messageKey = "TARGET_STAT_FELL"; break;
            case SpecialAttackDown1: defender.changePkStatStage(PkStatsEnum.SpecialAttack, -1); messageKey = "TARGET_STAT_FELL"; break;
            case SpecialAttackDown2: defender.changePkStatStage(PkStatsEnum.SpecialAttack, -2); messageKey = "TARGET_STAT_FELL"; break;
            case SpecialDefenseDown1: defender.changePkStatStage(PkStatsEnum.SpecialDefense, -1); messageKey = "TARGET_STAT_FELL"; break;
            case SpecialDefenseDown2: defender.changePkStatStage(PkStatsEnum.SpecialDefense, -2); messageKey = "TARGET_STAT_FELL"; break;
            case AccuracyDown1: defender.changePkStatStage(PkStatsEnum.Accuracy, -1); messageKey = "TARGET_STAT_FELL"; break;
            case EvasionDown1: defender.changePkStatStage(PkStatsEnum.Evasion, -1); messageKey = "TARGET_STAT_FELL"; break;
            // --- E. Special Damage/Healing (Recoil, Drain) ---
            case Recoil33:
                int recoilDamage = Math.max(1, damageDealt / 3);
                attacker.pkTakeDamage(recoilDamage);
                messageKey = "ATTACKER_TOOK_RECOIL";
                if (attacker.getPkHp() <= 0) attackerFainted = true;
                break;
            case Recoil50:
                recoilDamage = Math.max(1, damageDealt / 2);
                attacker.pkTakeDamage(recoilDamage);
                messageKey = "ATTACKER_TOOK_RECOIL";
                if (attacker.getPkHp() <= 0) attackerFainted = true;
                break;
            case DrainHalfDamage:
                int drainAmount = Math.max(1, damageDealt / 2);
                attacker.pkHeal(drainAmount);
                messageKey = "ATTACKER_DRAINED_HP";
                break;
            case Drain75Damage:
                drainAmount = Math.max(1, (int) (damageDealt * 0.75));
                attacker.pkHeal(drainAmount);
                messageKey = "ATTACKER_DRAINED_HP";
                break;
            case HealHalfHp:
                attacker.pkHeal(attacker.getPkMaxHp() / 2);
                messageKey = "ATTACKER_HEALED_HP";
                break;
            case RechargeTurn:
                attacker.setPkEffect(PkEffectsEnum.RechargeTurn, 2);
                messageKey = "ATTACKER_MUST_RECHARGE";
                break;
            // --- F. Weather and Terrain ---
            case SetWeatherSun:
                weather.setBsWeather(BsWeatherEnum.Sun);
                weather.setBsWeatherTurnsLeft(5);
                messageKey = "WEATHER_BECAME_SUNNY";
                break;
            case SetWeatherRain:
                weather.setBsWeather(BsWeatherEnum.Rain);
                weather.setBsWeatherTurnsLeft(5);
                messageKey = "WEATHER_BECAME_RAINY";
                break;
            case SetWeatherSand:
                 weather.setBsWeather(BsWeatherEnum.Sandstorm);
                weather.setBsWeatherTurnsLeft(5);
                messageKey = "WEATHER_BECAME_SANDSTORM";
                break;
            case SetWeatherSnow:
                 weather.setBsWeather(BsWeatherEnum.Snow);
                weather.setBsWeatherTurnsLeft(5);
                messageKey = "WEATHER_BECAME_SNOW";
                break;
            // --- G. Hazards ---
            case SetHazardStealthRock:
                if(!defenderHazards.contains(effect)) {
                    defenderHazards.add(effect);
                    messageKey = "HAZARD_SET_STEALTH_ROCK";
                }
                break;
            case SetHazardSpikes:
                 if(!defenderHazards.contains(effect)) { 
                    defenderHazards.add(effect);
                    messageKey = "HAZARD_SET_SPIKES";
                 }
                break;
            case SetHazardToxicSpikes:
                 if(!defenderHazards.contains(effect)) {
                    defenderHazards.add(effect);
                    messageKey = "HAZARD_SET_TOXIC_SPIKES";
                 }
                break;
            case SetHazardStickyWeb:
                 if(!defenderHazards.contains(effect)) {
                    defenderHazards.add(effect);
                    messageKey = "HAZARD_SET_STICKY_WEB";
                 }
                break;
        }
        if (messageKey != null) {
            return new TurnResult(messageKey, move, 0, false, attackerFainted, statusApplied, false);
        }
        return null;
    }

    public static ArrayList<TurnResult> applyEndOfTurnEffects(Pokemon pokemon, Pokemon opponent, Weather weather, Terrain terrain){
        ArrayList<TurnResult> results = new ArrayList<>();
        boolean fainted = false;
        // 1. Status Damage (Poison, Burn)
        if (pokemon.getPkStatus() != null) {
            PkStatusEnum status = pokemon.getPkStatus().getStatus();
            if (status == PkStatusEnum.Poisoned) {
                pokemon.pkTakeDamage(pokemon.getPkMaxHp() / 8);
                results.add(new TurnResult("POKEMON_HURT_BY_POISON"));
                fainted = pokemon.getPkHp() <= 0;
            } 
            // Badly Poisoned (Toxic) escalating damage
            else if (status == PkStatusEnum.BadlyPoisoned) {
                int counter = pokemon.getPkStatus().getStatusCounter();
                int poisonDamage = (int)(pokemon.getPkMaxHp() * (counter / 16.0));
                pokemon.pkTakeDamage(Math.max(1, poisonDamage));
                results.add(new TurnResult("POKEMON_HURT_BY_POISON"));
                fainted = pokemon.getPkHp() <= 0;
            } 
            else if (status == PkStatusEnum.Burned) {
                pokemon.pkTakeDamage(pokemon.getPkMaxHp() / 16);
                results.add(new TurnResult("POKEMON_HURT_BY_BURN"));
                fainted = pokemon.getPkHp() <= 0;
            }
        }
        if (fainted) {
            results.add(new TurnResult("POKEMON_FAINTED"));
            return results;
        }
        // 2. Leech Seed
        if (pokemon.getPkEffects().contains(PkEffectsEnum.LeechSeed)) {
            int drainDamage = pokemon.getPkMaxHp() / 8;
            pokemon.pkTakeDamage(drainDamage);
            opponent.pkHeal(drainDamage);
            results.add(new TurnResult("POKEMON_HURT_BY_LEECH_SEED"));
            fainted = pokemon.getPkHp() <= 0;
        }
        if (fainted) {
            results.add(new TurnResult("POKEMON_FAINTED"));
            return results;
        }
        // 3. Weather Damage
        if (weather.getBsWeather() != null) {
            if (weather.getBsWeather() == BsWeatherEnum.Sandstorm &&
                    !pokemon.hasPkType(PkTypeEnum.Rock) && !pokemon.hasPkType(PkTypeEnum.Ground)
                    && !pokemon.hasPkType(PkTypeEnum.Steel)) {
                pokemon.pkTakeDamage(pokemon.getPkMaxHp() / 16);
                results.add(new TurnResult("POKEMON_HURT_BY_SANDSTORM"));
                fainted = pokemon.getPkHp() <= 0;
            }
            if (weather.getBsWeather() == BsWeatherEnum.Snow && !pokemon.hasPkType(PkTypeEnum.Ice)) {
                pokemon.pkTakeDamage(pokemon.getPkMaxHp() / 16);
                results.add(new TurnResult("POKEMON_HURT_BY_SNOW"));
                fainted = pokemon.getPkHp() <= 0;
            }
        }
        if (fainted) {
            results.add(new TurnResult("POKEMON_FAINTED"));
            return results;
        }
        // Grassy Terrain Healing
        if (terrain.getBsTerrain() != null && terrain.getBsTerrain() == BsTerrainEnum.Grassy) {
            pokemon.pkHeal(pokemon.getPkMaxHp() / 16);
            results.add(new TurnResult("POKEMON_HEALED_BY_TERRAIN"));
        }
        // 4. Update status and effect counters
        pokemon.pkUpdateStatus();
        pokemon.pkUpdateEffects();
        return results;
    }
}