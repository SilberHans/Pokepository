package GameDesing.BattleSystem;

import Pokemons.Logic.Items.Item;
import Pokemons.Logic.Movements.Move;
import Pokemons.Pokemon;
import Persons.Trainer;
import Pokemons.Logic.TypeChart;
import Utility.Constants.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public final class BattleSystem {
    private BattleSystem() {}

    // --- MÉTODOS DE EJECUCIÓN DE ACCIONES ---

    public static ArrayList<TurnResult> executeItem(Trainer trainer, Pokemon target, Item item) {
        ArrayList<TurnResult> results = new ArrayList<>();
        trainer.removetItem(item); // Asume que esto descuenta 1 item

        // Constructor para Items: new TurnResult(String messageKey, Item item, boolean targetFainted)
        // (Nota: Tu TurnResult actual es el viejo. 
        // ¡DEBES ACTUALIZAR TurnResult.java PRIMERO al que te sugerí en el chat anterior!)
        // Asumiendo que SÍ lo actualizaste al que tiene (String key, String message, ...):

        String itemName = item.getItName();

        switch (item.getItEffect()) {
            case HealFixedAmount:
            case HealPercentage:
                if (target.getPkHp() == target.getPkMaxHp()) {
                    results.add(new TurnResult("ITEM_USE_FAILED", "No tuvo efecto..."));
                    return results;
                }
                if (item.getItEffect() == PkEffectsEnum.HealFixedAmount) {
                    target.pkHeal(item.getItEffectValue());
                } else {
                    int healAmount = (int) (target.getPkMaxHp() * (item.getItEffectValue() / 100.0));
                    target.pkHeal(healAmount);
                }
                results.add(new TurnResult("ITEM_HEALED_HP", trainer.getpName() + " usó " + itemName + "."));
                break;
            case CurePoison:
                if (target.getPkStatus() != null && (target.getPkStatus().getStatus() == PkStatusEnum.Poisoned || target.getPkStatus().getStatus() == PkStatusEnum.BadlyPoisoned)) {
                    target.pkCureStatus();
                    results.add(new TurnResult("ITEM_CURED_STATUS", trainer.getpName() + " usó " + itemName + "."));
                } else {
                    results.add(new TurnResult("ITEM_USE_FAILED", "No tuvo efecto..."));
                }
                break;
            // ... (Casos similares para CureParalysis, CureBurn, CureSleep, CureFreeze) ...
            case CureAllStatus:
                if (target.getPkStatus() != null) {
                    target.pkCureStatus();
                    results.add(new TurnResult("ITEM_CURED_STATUS", trainer.getpName() + " usó " + itemName + "."));
                } else {
                    results.add(new TurnResult("ITEM_USE_FAILED", "No tuvo efecto..."));
                }
                break;
            // ... (Casos para X Attack, X Defense, etc.) ...
            case AttackUp2:
                target.changePkStatStage(PkStatsEnum.Attack, 2);
                results.add(new TurnResult("ITEM_STAT_ROSE", "¡El Ataque de " + target.getPkNickName() + " subió!"));
                break;
            default:
                results.add(new TurnResult("ITEM_USE_FAILED", "No tuvo efecto..."));
                break;
        }
        return results;
    }

    public static ArrayList<TurnResult> executeMove(Trainer trainer, Pokemon attacker, Pokemon defender, Move move,
            Weather weather, Terrain terrain, ArrayList<PkEffectsEnum> defenderHazards) {
        
        ArrayList<TurnResult> results = new ArrayList<>();
        int damageDealt = 0;
        boolean targetFainted = false;
        boolean attackerFainted = false;
        boolean moveMissed = false;

        // --- A. Pre-Move Checks (Status, Confusion, etc.) ---
        TurnResult preTurnCheck = checkPokemonCanMove(attacker, weather, terrain);
        if (preTurnCheck != null) {
            results.add(preTurnCheck);
            if (preTurnCheck.didAttackerFaint()) { // Asumiendo que TurnResult tiene didAttackerFaint()
                return results; // Si el atacante se debilita (ej. confusión), no hay más acción
            }
            if (!preTurnCheck.getMessageKey().equals("POKEMON_CAN_MOVE")) {
                return results; // Si está dormido, paralizado, etc.
            }
        }

        // --- B. Accuracy Check ---
        if (!checkAccuracy(attacker, defender, move, weather, terrain)) {
            moveMissed = true;
            results.add(new TurnResult("MOVE_MISSED", attacker.getPkNickName() + " falló.", attacker, defender, move, 0));
            return results;
        }

        // --- C. Move Logic ("God Switch" PkLogicEffectsEnum) ---
        String messageKey = "MOVE_USED"; // Clave por defecto
        String message = attacker.getPkNickName() + " usó " + move.getMvName() + "!";

        switch (move.getMvPkLogicEffect()) {
            case Standard:
            case PrioritySimple:
            case HighCritRate:
            case DoublePowerIfStatus:
            case AttackWithSelfDebuff:
            case VariablePower:
            case FixedDamageEqualsLevel:
            case NeverMiss:
                damageDealt = calculateDamage(attacker, defender, move, weather, terrain);
                defender.pkTakeDamage(damageDealt);
                targetFainted = (defender.getPkHp() <= 0);
                if (damageDealt > 0) messageKey = "MOVE_HIT_DAMAGE";
                
                // Aplicar efectos secundarios (quemar, paralizar, etc.)
                TurnResult effectResult = applyMoveEffects(attacker, defender, move, damageDealt, weather, terrain, defenderHazards);
                if (effectResult != null) {
                    results.add(effectResult);
                    if (effectResult.didAttackerFaint()) attackerFainted = true;
                }
                break;
            
            case MultiHit:
                messageKey = "MOVE_HIT_DAMAGE"; // Asumimos que al menos golpea una vez
                int hitCount = ThreadLocalRandom.current().nextInt(2, 6);
                for (int i = 0; i < hitCount; i++) {
                    if (targetFainted) break; // Si se debilita, deja de golpear
                    if (!checkAccuracy(attacker, defender, move, weather, terrain)) {
                        results.add(new TurnResult("MOVE_MISSED", "El ataque falló.", attacker, defender, move, 0));
                        continue;
                    }
                    damageDealt = calculateDamage(attacker, defender, move, weather, terrain);
                    defender.pkTakeDamage(damageDealt);
                    targetFainted = (defender.getPkHp() <= 0);
                    // Añadimos un resultado por cada golpe
                    results.add(new TurnResult("MOVE_HIT_DAMAGE", "¡Golpe " + (i+1) + "!", attacker, defender, move, damageDealt));
                }
                // (El mensaje principal se añade al final)
                break;
                
            case Suicide:
                damageDealt = calculateDamage(attacker, defender, move, weather, terrain);
                defender.pkTakeDamage(damageDealt);
                targetFainted = (defender.getPkHp() <= 0);
                attacker.pkTakeDamage(attacker.getPkMaxHp());
                attackerFainted = true;
                messageKey = "MOVE_HIT_DAMAGE";
                break;

            case AttackAndSwitch:
                damageDealt = calculateDamage(attacker, defender, move, weather, terrain);
                defender.pkTakeDamage(damageDealt);
                targetFainted = (defender.getPkHp() <= 0);
                results.add(new TurnResult("ATTACK_AND_SWITCH_REQUIRED", attacker.getPkNickName() + " quiere huir.", attacker));
                messageKey = "MOVE_HIT_DAMAGE";
                break;
                
            // ... (Otros casos como AttackAndForceSwitch) ...

            case MultiStatChange:
            case SetWeather:
            case SetHazard:
            case Protect:
            case Counter:
                // Estos son movimientos de estado, el efecto es el resultado principal
                TurnResult statusMoveResult = applyMoveEffects(attacker, defender, move, 0, weather, terrain, defenderHazards);
                if (statusMoveResult != null) {
                    results.add(statusMoveResult);
                }
                break;
                
            case TwoTurn:
                attacker.setPkEffect(PkEffectsEnum.None, 2); // Esto necesita una clave de efecto real
                results.add(new TurnResult("MOVE_IS_CHARGING", attacker.getPkNickName() + " está cargando.", attacker));
                break;
                
            default:
                results.add(new TurnResult("MOVE_NOT_IMPLEMENTED", "Movimiento no implementado."));
                break;
        }

        // --- D. Package Final Result ---
        // Añade el resultado principal (ej. "Pikachu usó Tackle!") al inicio de la lista.
        TurnResult mainResult = new TurnResult(
            messageKey, 
            message,
            attacker, 
            defender, 
            move, 
            damageDealt
        );
        
        // Pasamos la información de Faint y Miss al resultado principal
        mainResult.setTargetFainted(targetFainted);
        mainResult.setAttackerFainted(attackerFainted);
        mainResult.setMoveMissed(moveMissed);
        
        results.add(0, mainResult);
        
        // Si el objetivo se debilitó, añade un resultado de "FAINTED"
        if (targetFainted) {
            results.add(new TurnResult("POKEMON_FAINTED", "¡" + defender.getPkNickName() + " se debilitó!", defender));
        }
        if (attackerFainted) {
            results.add(new TurnResult("POKEMON_FAINTED", "¡" + attacker.getPkNickName() + " se debilitó!", attacker));
        }
        
        return results;
    }

    // --- MÉTODOS DE CÁLCULO Y CHEQUEO ---

    public static int calculateDamage(Pokemon attacker, Pokemon defender, Move move, Weather weather, Terrain terrain) {
        // ... (Tu lógica de calculateDamage aquí no cambia) ...
        // ... (Solo asegúrate de que funciona) ...
        // (El código original de calculateDamage está bien)
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

    public static TurnResult checkPokemonCanMove(Pokemon attacker, Weather weather, Terrain terrain) {
        // Constructor para efectos: new TurnResult(String key, String message, Pokemon target)
        
        // Recharge Turn Logic
        if (attacker.getPkEffects().contains(PkEffectsEnum.RechargeTurn)) {
            attacker.getPkEffectsAndTurns().remove(PkEffectsEnum.RechargeTurn);
            return new TurnResult("POKEMON_IS_RECHARGING", attacker.getPkNickName() + " está recargando.", attacker);
        }
        if (attacker.getPkStatus() != null) {
            PkStatusEnum status = attacker.getPkStatus().getStatus();
            switch (status) {
                case Asleep:
                    if (attacker.getPkStatus().getStatusCounter() > 0) {
                        return new TurnResult("POKEMON_IS_ASLEEP", attacker.getPkNickName() + " está dormido.", attacker);
                    } else {
                        attacker.pkCureStatus();
                        // (Podríamos añadir un TurnResult de "despertó")
                    }
                    break;
                case Frozen:
                    if (ThreadLocalRandom.current().nextInt(100) < 20) {
                        attacker.pkCureStatus();
                        // (Podríamos añadir un TurnResult de "descongeló")
                    } else {
                        return new TurnResult("POKEMON_IS_FROZEN", attacker.getPkNickName() + " está congelado.", attacker);
                    }
                    break;
                case Paralyzed:
                    if (ThreadLocalRandom.current().nextInt(100) < 25) {
                        return new TurnResult("POKEMON_IS_PARALYZED", attacker.getPkNickName() + " está paralizado.", attacker);
                    }
                    break;
            }
        }
        if (attacker.getPkEffects().contains(PkEffectsEnum.Flinch)) {
            attacker.getPkEffectsAndTurns().remove(PkEffectsEnum.Flinch);
            return new TurnResult("POKEMON_FLINCHED", attacker.getPkNickName() + " retrocedió.", attacker);
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
                    
                    // ¡Línea de error 2 corregida!
                    TurnResult confusionResult = new TurnResult(
                        "POKEMON_HURT_ITSELF_IN_CONFUSION", 
                        attacker.getPkNickName() + " se hirió a sí mismo.", 
                        attacker
                    );
                    confusionResult.setAttackerFainted(fainted); // Pasamos la info de Faint
                    if (fainted) {
                        // Si se debilita, necesitamos un resultado adicional
                        // return new TurnResult("POKEMON_FAINTED", ...);
                    }
                    return confusionResult;
                }
            }
        }
        // Si todo está bien, devuelve un resultado nulo o especial
        return new TurnResult("POKEMON_CAN_MOVE", "", attacker); // Un resultado "invisible" que significa "OK"
    }

    public static boolean checkAccuracy(Pokemon attacker, Pokemon defender, Move move, Weather weather, Terrain terrain) {
        // ... (Tu lógica de checkAccuracy aquí no cambia) ...
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
        
        // ... (Lógica de self-debuff como Close Combat, etc.) ...
        if(move.getMvPkLogicEffect() == PkLogicEffectsEnum.AttackWithSelfDebuff) {
            // (Esta lógica está bien, pero también debería devolver un TurnResult)
            // ej. results.add(new TurnResult("ATTACKER_STAT_FELL", "¡La defensa de " + attacker.getPkNickName() + " bajó!", attacker));
        }

        if (effect == PkEffectsEnum.None || ThreadLocalRandom.current().nextInt(100) >= chance) {
            return null; // No hay efecto
        }

        String messageKey = null;
        String message = "";
        Pokemon target = defender; // Por defecto el objetivo es el defensor
        boolean attackerFainted = false;

        switch (effect) {
            // --- A. Persistent Status Effects (Target: Defender) ---
            case Burn:
                if (defender.getPkStatus() == null) {
                    defender.setPkStatus(PkStatusEnum.Burned, -1);
                    messageKey = "TARGET_WAS_BURNED";
                    message = "¡" + defender.getPkNickName() + " fue quemado!";
                }
                break;
            // ... (Casos para Freeze, Paralyze, Poison, Sleep) ...
            
            // --- B. Volatile Status Effects (Target: Defender) ---
            case Confuse:
                if (!defender.getPkEffects().contains(PkEffectsEnum.Confuse)) {
                    defender.setPkEffect(PkEffectsEnum.Confuse, ThreadLocalRandom.current().nextInt(2, 6));
                    messageKey = "TARGET_WAS_CONFUSED";
                    message = "¡" + defender.getPkNickName() + " está confuso!";
                }
                break;
            case Flinch:
                defender.setPkEffect(PkEffectsEnum.Flinch, 1);
                // (El mensaje de Flinch se maneja en checkPokemonCanMove)
                break;
                
            // --- C. Attacker Buffs (Target: Attacker) ---
            case AttackUp1: 
                attacker.changePkStatStage(PkStatsEnum.Attack, 1); 
                messageKey = "ATTACKER_STAT_ROSE"; 
                message = "¡El Ataque de " + attacker.getPkNickName() + " subió!";
                target = attacker;
                break;
            // ... (Casos para AttackUp2, DefenseUp1, SpeedUp2, etc.) ...
            case DragonDance:
                attacker.changePkStatStage(PkStatsEnum.Attack, 1);
                attacker.changePkStatStage(PkStatsEnum.Speed, 1);
                messageKey = "ATTACKER_STAT_ROSE";
                message = "¡El Ataque y Velocidad de " + attacker.getPkNickName() + " subieron!";
                target = attacker;
                break;

            // --- D. Defender Debuffs (Target: Defender) ---
            case AttackDown1: 
                defender.changePkStatStage(PkStatsEnum.Attack, -1); 
                messageKey = "TARGET_STAT_FELL"; 
                message = "¡El Ataque de " + defender.getPkNickName() + " bajó!";
                break;
            // ... (Casos para DefenseDown1, SpeedDown2, etc.) ...

            // --- E. Special Damage/Healing (Target: Attacker) ---
            case Recoil33:
                int recoilDamage = Math.max(1, damageDealt / 3);
                attacker.pkTakeDamage(recoilDamage);
                messageKey = "ATTACKER_TOOK_RECOIL";
                message = attacker.getPkNickName() + " recibió daño de retroceso.";
                target = attacker;
                if (attacker.getPkHp() <= 0) attackerFainted = true;
                break;
            case DrainHalfDamage:
                int drainAmount = Math.max(1, damageDealt / 2);
                attacker.pkHeal(drainAmount);
                messageKey = "ATTACKER_DRAINED_HP";
                message = attacker.getPkNickName() + " absorbió vida.";
                target = attacker;
                break;
            case HealHalfHp:
                attacker.pkHeal(attacker.getPkMaxHp() / 2);
                messageKey = "ATTACKER_HEALED_HP";
                message = attacker.getPkNickName() + " recuperó vida.";
                target = attacker;
                break;
                
            // ... (Resto de casos) ...
        }

        if (messageKey != null) {
            // ¡Línea de error 1 corregida!
            TurnResult effectResult = new TurnResult(messageKey, message, target);
            effectResult.setAttackerFainted(attackerFainted);
            return effectResult;
        }
        return null; // No hubo efecto
    }

    public static ArrayList<TurnResult> applyEndOfTurnEffects(Pokemon pokemon, Pokemon opponent, Weather weather, Terrain terrain) {
        ArrayList<TurnResult> results = new ArrayList<>();
        boolean fainted = false;

        // 1. Status Damage (Poison, Burn)
        if (pokemon.getPkStatus() != null) {
            PkStatusEnum status = pokemon.getPkStatus().getStatus();
            if (status == PkStatusEnum.Poisoned) {
                pokemon.pkTakeDamage(pokemon.getPkMaxHp() / 8);
                fainted = pokemon.getPkHp() <= 0;
                results.add(new TurnResult("POKEMON_HURT_BY_POISON", pokemon.getPkNickName() + " sufre por el veneno.", pokemon));
            } 
            else if (status == PkStatusEnum.BadlyPoisoned) {
                // ... (lógica de daño) ...
                fainted = pokemon.getPkHp() <= 0;
                results.add(new TurnResult("POKEMON_HURT_BY_POISON", pokemon.getPkNickName() + " sufre por el veneno.", pokemon));
            } 
            else if (status == PkStatusEnum.Burned) {
                pokemon.pkTakeDamage(pokemon.getPkMaxHp() / 16);
                fainted = pokemon.getPkHp() <= 0;
                results.add(new TurnResult("POKEMON_HURT_BY_BURN", pokemon.getPkNickName() + " sufre por la quemadura.", pokemon));
            }
        }
        if (fainted) {
            results.add(new TurnResult("POKEMON_FAINTED", pokemon.getPkNickName() + " se debilitó.", pokemon));
            return results;
        }

        // 2. Leech Seed
        if (pokemon.getPkEffects().contains(PkEffectsEnum.LeechSeed)) {
            // ... (lógica de daño y cura) ...
            fainted = pokemon.getPkHp() <= 0;
            results.add(new TurnResult("POKEMON_HURT_BY_LEECH_SEED", pokemon.getPkNickName() + " fue drenado.", pokemon));
        }
        if (fainted) {
            results.add(new TurnResult("POKEMON_FAINTED", pokemon.getPkNickName() + " se debilitó.", pokemon));
            return results;
        }
        
        // 3. Weather Damage
        if (weather.getBsWeather() != null) {
            if (weather.getBsWeather() == BsWeatherEnum.Sandstorm &&
                    !pokemon.hasPkType(PkTypeEnum.Rock) && !pokemon.hasPkType(PkTypeEnum.Ground)
                    && !pokemon.hasPkType(PkTypeEnum.Steel)) {
                pokemon.pkTakeDamage(pokemon.getPkMaxHp() / 16);
                fainted = pokemon.getPkHp() <= 0;
                results.add(new TurnResult("POKEMON_HURT_BY_SANDSTORM", pokemon.getPkNickName() + " es dañado por la tormenta.", pokemon));
            }
            // ... (Lógica para Snow) ...
        }
        if (fainted) {
            results.add(new TurnResult("POKEMON_FAINTED", pokemon.getPkNickName() + " se debilitó.", pokemon));
            return results;
        }

        // ... (Lógica para Grassy Terrain) ...

        // 4. Update status and effect counters
        pokemon.pkUpdateStatus();
        pokemon.pkUpdateEffects();
        return results;
    }
    
    
    // ---
    // Nota: ¡Necesitas actualizar TurnResult.java para que tenga los constructores
    // que estoy usando aquí! (El que te pasé en el chat anterior)
    // Específicamente:
    // 1. public TurnResult(String key, String message)
    // 2. public TurnResult(String key, String message, Pokemon target)
    // 3. public TurnResult(String key, String message, Pokemon attacker, Pokemon defender, Move move, int damage)
    // Y añadirle los setters: setTargetFainted(b), setAttackerFainted(b), setMoveMissed(b)
    // ---
}