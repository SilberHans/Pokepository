package Pokemons.Logic.Movements;

import Pokemons.Logic.PkEffectsEnum;
import Pokemons.Logic.PkTypeEnum;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class PkMovementsDataBase {
    private PkMovementsDataBase(){}
    
    private static final List<PkMove> pkMovementsList = List.of();
    
    public static ArrayList<PkMove> pkMovemntsSelection(PkTypeEnum pkType1, PkTypeEnum pkType2){
        ArrayList<PkMove> pkMovements = new ArrayList<>();
        ArrayList<PkMove> pkSelectableMovementsByPkType = new ArrayList<>();
        ArrayList<PkMove> pkSelectablePhysicalMovements = new ArrayList<>();
        ArrayList<PkMove> pkSelectableSpecialMovements = new ArrayList<>();
        ArrayList<PkMove> pkSelectableStatusMovements = new ArrayList<>();
        for(PkMove tryMove: pkMovementsList){
            if(tryMove.getMvPkType() == pkType1 || tryMove.getMvPkType() == pkType2 || tryMove.getMvPkType() == PkTypeEnum.Normal){
                pkSelectableMovementsByPkType.add(tryMove);
            }
        }
        for(PkMove tryMove: pkSelectableMovementsByPkType){
            if(tryMove.getMvType() == PkMovementTypeEnum.Physical){
                pkSelectablePhysicalMovements.add(tryMove);
            }
            if(tryMove.getMvType() == PkMovementTypeEnum.Special){
                pkSelectableSpecialMovements.add(tryMove);
            }
            if(tryMove.getMvType() == PkMovementTypeEnum.Status){
                pkSelectableStatusMovements.add(tryMove);
            }
        }
        if(ThreadLocalRandom.current().nextInt(2) == 0){
            do{
                pkMovements.add(pkSelectablePhysicalMovements.get(ThreadLocalRandom.current().nextInt(pkSelectablePhysicalMovements.size())));
                pkMovements.add(pkSelectablePhysicalMovements.get(ThreadLocalRandom.current().nextInt(pkSelectablePhysicalMovements.size())));
                if(pkMovements.get(0) == pkMovements.get(1)){
                    pkMovements.set(0, null);
                    pkMovements.set(1, null);
                }
            }while(pkMovements.get(0) == pkMovements.get(1));
            pkMovements.add(pkSelectableSpecialMovements.get(ThreadLocalRandom.current().nextInt(pkSelectableSpecialMovements.size())));
            pkMovements.add(pkSelectableStatusMovements.get(ThreadLocalRandom.current().nextInt(pkSelectableStatusMovements.size())));
            return pkMovements;
        }
        do{
            pkMovements.add(pkSelectableSpecialMovements.get(ThreadLocalRandom.current().nextInt(pkSelectableSpecialMovements.size())));
            pkMovements.add(pkSelectableSpecialMovements.get(ThreadLocalRandom.current().nextInt(pkSelectableSpecialMovements.size())));
            if(pkMovements.get(0) == pkMovements.get(1)){
                    pkMovements.set(0, null);
                    pkMovements.set(1, null);
                }
        }while(pkMovements.get(0) == pkMovements.get(1));
        pkMovements.add(pkSelectablePhysicalMovements.get(ThreadLocalRandom.current().nextInt(pkSelectablePhysicalMovements.size())));
        pkMovements.add(pkSelectableStatusMovements.get(ThreadLocalRandom.current().nextInt(pkSelectableStatusMovements.size())));
        return pkMovements;
    }
    
    static {
       // --- NORMAL ---
        pkMovementsList.add(new PkMove("Pound", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 40, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Scratch", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 40, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Tackle", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 40, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Quick Attack", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 40, PkEffectsEnum.None, 0, PkLogicEffectsEnum.PrioritySimple));
        pkMovementsList.add(new PkMove("Hyper Beam", PkMovementTypeEnum.Special, PkTypeEnum.Normal, 150, PkEffectsEnum.RechargeTurn, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Growl", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.AttackDown1, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Rage", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 20, PkEffectsEnum.AttackUp1, 100, PkLogicEffectsEnum.Standard)); // Lógica especial: Se activa al ser golpeado
        pkMovementsList.add(new PkMove("Soft-Boiled", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.HealHalfHp, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Protect", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.Protect, 100, PkLogicEffectsEnum.Protect));
        pkMovementsList.add(new PkMove("Substitute", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard)); // Lógica especial: Sustituto (cuesta 25% HP)
        pkMovementsList.add(new PkMove("Sleep Talk", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard)); // Lógica especial: Usar dormido
        pkMovementsList.add(new PkMove("Double Team", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.EvasionUp1, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Body Slam", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 85, PkEffectsEnum.Paralyze, 30, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Strength", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 80, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Facade", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 70, PkEffectsEnum.None, 0, PkLogicEffectsEnum.DoublePowerIfStatus));
        pkMovementsList.add(new PkMove("Swords Dance", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.AttackUp2, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Attract", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.Infatuate, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Swagger", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.Confuse, 100, PkLogicEffectsEnum.Standard)); // Lógica especial: También AttackUp2 al *oponente*
        pkMovementsList.add(new PkMove("Endure", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Protect)); // Lógica especial: Sobrevivir a 1 HP
        pkMovementsList.add(new PkMove("Extreme Speed", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 80, PkEffectsEnum.None, 0, PkLogicEffectsEnum.PrioritySimple)); // Lógica especial: Prioridad +2
        pkMovementsList.add(new PkMove("Double-Edge", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 120, PkEffectsEnum.Recoil33, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Tri Attack", PkMovementTypeEnum.Special, PkTypeEnum.Normal, 80, PkEffectsEnum.Paralyze, 20, PkLogicEffectsEnum.Standard)); // Lógica especial: 20% total de Par/Burn/Freeze
        pkMovementsList.add(new PkMove("Scary Face", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.SpeedDown2, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Explosion", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 250, PkEffectsEnum.Suicide, 100, PkLogicEffectsEnum.Suicide));
        pkMovementsList.add(new PkMove("Metronome", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard)); // Lógica especial: Movimiento aleatorio
        pkMovementsList.add(new PkMove("Super Fang", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.FixedDamageEqualsLevel)); // Lógica especial: Daño = 50% HP actual oponente
        pkMovementsList.add(new PkMove("Self-Destruct", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 200, PkEffectsEnum.Suicide, 100, PkLogicEffectsEnum.Suicide));
        pkMovementsList.add(new PkMove("Swift", PkMovementTypeEnum.Special, PkTypeEnum.Normal, 60, PkEffectsEnum.None, 0, PkLogicEffectsEnum.NeverMiss));
        // --- FIRE ---
        pkMovementsList.add(new PkMove("Ember", PkMovementTypeEnum.Special, PkTypeEnum.Fire, 40, PkEffectsEnum.Burn, 10, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Flamethrower", PkMovementTypeEnum.Special, PkTypeEnum.Fire, 90, PkEffectsEnum.Burn, 10, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Fire Fang", PkMovementTypeEnum.Physical, PkTypeEnum.Fire, 65, PkEffectsEnum.Burn, 10, PkLogicEffectsEnum.Standard)); // Lógica especial: También 10% Flinch
        pkMovementsList.add(new PkMove("Sunny Day", PkMovementTypeEnum.Status, PkTypeEnum.Fire, 0, PkEffectsEnum.SetWeatherSun, 100, PkLogicEffectsEnum.SetWeather));
        pkMovementsList.add(new PkMove("Will-O-Wisp", PkMovementTypeEnum.Status, PkTypeEnum.Fire, 0, PkEffectsEnum.Burn, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Fire Blast", PkMovementTypeEnum.Special, PkTypeEnum.Fire, 110, PkEffectsEnum.Burn, 10, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Flare Blitz", PkMovementTypeEnum.Physical, PkTypeEnum.Fire, 120, PkEffectsEnum.Recoil33, 100, PkLogicEffectsEnum.Standard)); // Lógica especial: También 10% Burn
        pkMovementsList.add(new PkMove("Heat Wave", PkMovementTypeEnum.Special, PkTypeEnum.Fire, 95, PkEffectsEnum.Burn, 10, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Lava Plume", PkMovementTypeEnum.Special, PkTypeEnum.Fire, 80, PkEffectsEnum.Burn, 30, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Fire Spin", PkMovementTypeEnum.Special, PkTypeEnum.Fire, 35, PkEffectsEnum.Trap, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Fire Punch", PkMovementTypeEnum.Physical, PkTypeEnum.Fire, 75, PkEffectsEnum.Burn, 10, PkLogicEffectsEnum.Standard));
        // --- WATER ---
        pkMovementsList.add(new PkMove("Water Gun", PkMovementTypeEnum.Special, PkTypeEnum.Water, 40, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Surf", PkMovementTypeEnum.Special, PkTypeEnum.Water, 90, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Waterfall", PkMovementTypeEnum.Physical, PkTypeEnum.Water, 80, PkEffectsEnum.Flinch, 20, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Rain Dance", PkMovementTypeEnum.Status, PkTypeEnum.Water, 0, PkEffectsEnum.SetWeatherRain, 100, PkLogicEffectsEnum.SetWeather));
        pkMovementsList.add(new PkMove("Hydro Pump", PkMovementTypeEnum.Special, PkTypeEnum.Water, 110, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Aqua Jet", PkMovementTypeEnum.Physical, PkTypeEnum.Water, 40, PkEffectsEnum.None, 0, PkLogicEffectsEnum.PrioritySimple));
        pkMovementsList.add(new PkMove("Scald", PkMovementTypeEnum.Special, PkTypeEnum.Water, 80, PkEffectsEnum.Burn, 30, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Aqua Tail", PkMovementTypeEnum.Physical, PkTypeEnum.Water, 90, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Withdraw", PkMovementTypeEnum.Status, PkTypeEnum.Water, 0, PkEffectsEnum.DefenseUp1, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Shell Smash", PkMovementTypeEnum.Status, PkTypeEnum.Water, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.MultiStatChange)); // Lógica especial: Buffs y Debuffs propios
        pkMovementsList.add(new PkMove("Bubble Beam", PkMovementTypeEnum.Special, PkTypeEnum.Water, 65, PkEffectsEnum.SpeedDown1, 10, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Crabhammer", PkMovementTypeEnum.Physical, PkTypeEnum.Water, 100, PkEffectsEnum.None, 0, PkLogicEffectsEnum.HighCritRate));
        // --- GRASS ---
        pkMovementsList.add(new PkMove("Vine Whip", PkMovementTypeEnum.Physical, PkTypeEnum.Grass, 45, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Razor Leaf", PkMovementTypeEnum.Physical, PkTypeEnum.Grass, 55, PkEffectsEnum.None, 0, PkLogicEffectsEnum.HighCritRate));
        pkMovementsList.add(new PkMove("Mega Drain", PkMovementTypeEnum.Special, PkTypeEnum.Grass, 40, PkEffectsEnum.DrainHalfDamage, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Giga Drain", PkMovementTypeEnum.Special, PkTypeEnum.Grass, 75, PkEffectsEnum.DrainHalfDamage, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Solar Beam", PkMovementTypeEnum.Special, PkTypeEnum.Grass, 120, PkEffectsEnum.None, 0, PkLogicEffectsEnum.TwoTurn)); // Lógica especial: 1 turno con sol
        pkMovementsList.add(new PkMove("Sleep Powder", PkMovementTypeEnum.Status, PkTypeEnum.Grass, 0, PkEffectsEnum.Sleep, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Spore", PkMovementTypeEnum.Status, PkTypeEnum.Grass, 0, PkEffectsEnum.Sleep, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Leech Seed", PkMovementTypeEnum.Status, PkTypeEnum.Grass, 0, PkEffectsEnum.LeechSeed, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Petal Dance", PkMovementTypeEnum.Special, PkTypeEnum.Grass, 120, PkEffectsEnum.None, 0, PkLogicEffectsEnum.AttackWithSelfDebuff)); // Lógica especial: Confunde al usuario
        pkMovementsList.add(new PkMove("Leaf Blade", PkMovementTypeEnum.Physical, PkTypeEnum.Grass, 90, PkEffectsEnum.None, 0, PkLogicEffectsEnum.HighCritRate));
        pkMovementsList.add(new PkMove("Energy Ball", PkMovementTypeEnum.Special, PkTypeEnum.Grass, 90, PkEffectsEnum.SpecialDefenseDown1, 10, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Stun Spore", PkMovementTypeEnum.Status, PkTypeEnum.Grass, 0, PkEffectsEnum.Paralyze, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Synthesis", PkMovementTypeEnum.Status, PkTypeEnum.Grass, 0, PkEffectsEnum.HealHalfHp, 100, PkLogicEffectsEnum.Standard)); // Lógica especial: (Cura más con sol)
        pkMovementsList.add(new PkMove("Magical Leaf", PkMovementTypeEnum.Special, PkTypeEnum.Grass, 60, PkEffectsEnum.None, 0, PkLogicEffectsEnum.NeverMiss));
        // --- ELECTRIC ---
        pkMovementsList.add(new PkMove("Thunder Shock", PkMovementTypeEnum.Special, PkTypeEnum.Electric, 40, PkEffectsEnum.Paralyze, 10, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Thunderbolt", PkMovementTypeEnum.Special, PkTypeEnum.Electric, 90, PkEffectsEnum.Paralyze, 10, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Wild Charge", PkMovementTypeEnum.Physical, PkTypeEnum.Electric, 90, PkEffectsEnum.Recoil33, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Thunder Wave", PkMovementTypeEnum.Status, PkTypeEnum.Electric, 0, PkEffectsEnum.Paralyze, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Thunder", PkMovementTypeEnum.Special, PkTypeEnum.Electric, 110, PkEffectsEnum.Paralyze, 30, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Volt Tackle", PkMovementTypeEnum.Physical, PkTypeEnum.Electric, 120, PkEffectsEnum.Recoil33, 100, PkLogicEffectsEnum.Standard)); // Lógica especial: También 10% Paralyze
        pkMovementsList.add(new PkMove("Volt Switch", PkMovementTypeEnum.Special, PkTypeEnum.Electric, 70, PkEffectsEnum.SwitchUser, 100, PkLogicEffectsEnum.AttackAndSwitch));
        pkMovementsList.add(new PkMove("Discharge", PkMovementTypeEnum.Special, PkTypeEnum.Electric, 80, PkEffectsEnum.Paralyze, 30, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Thunder Punch", PkMovementTypeEnum.Physical, PkTypeEnum.Electric, 75, PkEffectsEnum.Paralyze, 10, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Nuzzle", PkMovementTypeEnum.Physical, PkTypeEnum.Electric, 20, PkEffectsEnum.Paralyze, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Spark", PkMovementTypeEnum.Physical, PkTypeEnum.Electric, 65, PkEffectsEnum.Paralyze, 30, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Electro Ball", PkMovementTypeEnum.Special, PkTypeEnum.Electric, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.VariablePower)); // Lógica especial: Poder basado en Velocidad
        // --- ROCK ---
        pkMovementsList.add(new PkMove("Rock Throw", PkMovementTypeEnum.Physical, PkTypeEnum.Rock, 50, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Rock Slide", PkMovementTypeEnum.Physical, PkTypeEnum.Rock, 75, PkEffectsEnum.Flinch, 30, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Power Gem", PkMovementTypeEnum.Special, PkTypeEnum.Rock, 80, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Stealth Rock", PkMovementTypeEnum.Status, PkTypeEnum.Rock, 0, PkEffectsEnum.SetHazardStealthRock, 100, PkLogicEffectsEnum.SetHazard));
        pkMovementsList.add(new PkMove("Stone Edge", PkMovementTypeEnum.Physical, PkTypeEnum.Rock, 100, PkEffectsEnum.None, 0, PkLogicEffectsEnum.HighCritRate));
        pkMovementsList.add(new PkMove("Sandstorm", PkMovementTypeEnum.Status, PkTypeEnum.Rock, 0, PkEffectsEnum.SetWeatherSand, 100, PkLogicEffectsEnum.SetWeather));
        pkMovementsList.add(new PkMove("Rock Polish", PkMovementTypeEnum.Status, PkTypeEnum.Rock, 0, PkEffectsEnum.SpeedUp2, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Head Smash", PkMovementTypeEnum.Physical, PkTypeEnum.Rock, 150, PkEffectsEnum.Recoil50, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Rock Blast", PkMovementTypeEnum.Physical, PkTypeEnum.Rock, 25, PkEffectsEnum.None, 0, PkLogicEffectsEnum.MultiHit));
        pkMovementsList.add(new PkMove("Ancient Power", PkMovementTypeEnum.Special, PkTypeEnum.Rock, 60, PkEffectsEnum.AllStatsUp1, 10, PkLogicEffectsEnum.Standard));
        // --- GROUND ---
        pkMovementsList.add(new PkMove("Mud-Slap", PkMovementTypeEnum.Special, PkTypeEnum.Ground, 20, PkEffectsEnum.AccuracyDown1, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Mud Shot", PkMovementTypeEnum.Special, PkTypeEnum.Ground, 55, PkEffectsEnum.SpeedDown1, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Earthquake", PkMovementTypeEnum.Physical, PkTypeEnum.Ground, 100, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Bulldoze", PkMovementTypeEnum.Physical, PkTypeEnum.Ground, 60, PkEffectsEnum.SpeedDown1, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Earth Power", PkMovementTypeEnum.Special, PkTypeEnum.Ground, 90, PkEffectsEnum.SpecialDefenseDown1, 10, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Magnitude", PkMovementTypeEnum.Physical, PkTypeEnum.Ground, 0, PkEffectsEnum.DamageRandom, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Dig", PkMovementTypeEnum.Physical, PkTypeEnum.Ground, 80, PkEffectsEnum.None, 0, PkLogicEffectsEnum.TwoTurn));
        pkMovementsList.add(new PkMove("Spikes", PkMovementTypeEnum.Status, PkTypeEnum.Ground, 0, PkEffectsEnum.SetHazardSpikes, 100, PkLogicEffectsEnum.SetHazard));
        pkMovementsList.add(new PkMove("Bonemerang", PkMovementTypeEnum.Physical, PkTypeEnum.Ground, 50, PkEffectsEnum.None, 0, PkLogicEffectsEnum.MultiHit));
        pkMovementsList.add(new PkMove("Mud Bomb", PkMovementTypeEnum.Special, PkTypeEnum.Ground, 65, PkEffectsEnum.AccuracyDown1, 30, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Sand Tomb", PkMovementTypeEnum.Physical, PkTypeEnum.Ground, 35, PkEffectsEnum.Trap, 100, PkLogicEffectsEnum.Standard));
        // --- FIGHTING ---
        pkMovementsList.add(new PkMove("Rock Smash", PkMovementTypeEnum.Physical, PkTypeEnum.Fighting, 40, PkEffectsEnum.DefenseDown1, 50, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Brick Break", PkMovementTypeEnum.Physical, PkTypeEnum.Fighting, 75, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard)); // Lógica especial: Rompe barreras
        pkMovementsList.add(new PkMove("Close Combat", PkMovementTypeEnum.Physical, PkTypeEnum.Fighting, 120, PkEffectsEnum.None, 0, PkLogicEffectsEnum.AttackWithSelfDebuff));
        pkMovementsList.add(new PkMove("Vacuum Wave", PkMovementTypeEnum.Special, PkTypeEnum.Fighting, 40, PkEffectsEnum.None, 0, PkLogicEffectsEnum.PrioritySimple));
        pkMovementsList.add(new PkMove("Vital Throw", PkMovementTypeEnum.Physical, PkTypeEnum.Fighting, 70, PkEffectsEnum.None, 0, PkLogicEffectsEnum.NeverMiss)); // Lógica especial: Prioridad negativa
        pkMovementsList.add(new PkMove("Seismic Toss", PkMovementTypeEnum.Physical, PkTypeEnum.Fighting, 0, PkEffectsEnum.DamageEqualsLevel, 100, PkLogicEffectsEnum.FixedDamageEqualsLevel));
        pkMovementsList.add(new PkMove("Aura Sphere", PkMovementTypeEnum.Special, PkTypeEnum.Fighting, 80, PkEffectsEnum.None, 0, PkLogicEffectsEnum.NeverMiss));
        pkMovementsList.add(new PkMove("Drain Punch", PkMovementTypeEnum.Physical, PkTypeEnum.Fighting, 75, PkEffectsEnum.DrainHalfDamage, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Mach Punch", PkMovementTypeEnum.Physical, PkTypeEnum.Fighting, 40, PkEffectsEnum.None, 0, PkLogicEffectsEnum.PrioritySimple));
        pkMovementsList.add(new PkMove("Bulk Up", PkMovementTypeEnum.Status, PkTypeEnum.Fighting, 0, PkEffectsEnum.AttackDefenseUp1, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Double Kick", PkMovementTypeEnum.Physical, PkTypeEnum.Fighting, 30, PkEffectsEnum.None, 0, PkLogicEffectsEnum.MultiHit));
        pkMovementsList.add(new PkMove("Counter", PkMovementTypeEnum.Physical, PkTypeEnum.Fighting, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Counter));
        // --- PSYCHIC ---
        pkMovementsList.add(new PkMove("Confusion", PkMovementTypeEnum.Special, PkTypeEnum.Psychic, 50, PkEffectsEnum.Confuse, 10, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Psybeam", PkMovementTypeEnum.Special, PkTypeEnum.Psychic, 65, PkEffectsEnum.Confuse, 10, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Psychic", PkMovementTypeEnum.Special, PkTypeEnum.Psychic, 90, PkEffectsEnum.SpecialDefenseDown1, 10, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Psycho Cut", PkMovementTypeEnum.Physical, PkTypeEnum.Psychic, 70, PkEffectsEnum.None, 0, PkLogicEffectsEnum.HighCritRate));
        pkMovementsList.add(new PkMove("Calm Mind", PkMovementTypeEnum.Status, PkTypeEnum.Psychic, 0, PkEffectsEnum.CalmMind, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Agility", PkMovementTypeEnum.Status, PkTypeEnum.Psychic, 0, PkEffectsEnum.SpeedUp2, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Light Screen", PkMovementTypeEnum.Status, PkTypeEnum.Psychic, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.SetWeather)); // Lógica especial: Barrera (Equipo)
        pkMovementsList.add(new PkMove("Psyshock", PkMovementTypeEnum.Special, PkTypeEnum.Psychic, 80, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard)); // Lógica especial: Daño Físico
        pkMovementsList.add(new PkMove("Zen Headbutt", PkMovementTypeEnum.Physical, PkTypeEnum.Psychic, 80, PkEffectsEnum.Flinch, 20, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Trick Room", PkMovementTypeEnum.Status, PkTypeEnum.Psychic, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.SetWeather)); // Lógica especial: Campo
        pkMovementsList.add(new PkMove("Reflect", PkMovementTypeEnum.Status, PkTypeEnum.Psychic, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.SetWeather)); // Lógica especial: Barrera (Equipo)
        pkMovementsList.add(new PkMove("Rest", PkMovementTypeEnum.Status, PkTypeEnum.Psychic, 0, PkEffectsEnum.HealHalfHp, 100, PkLogicEffectsEnum.Standard)); // Lógica especial: También duerme al usuario
        pkMovementsList.add(new PkMove("Amnesia", PkMovementTypeEnum.Status, PkTypeEnum.Psychic, 0, PkEffectsEnum.SpecialDefenseUp2, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Mirror Coat", PkMovementTypeEnum.Special, PkTypeEnum.Psychic, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Counter));
        // --- GHOST ---
        pkMovementsList.add(new PkMove("Lick", PkMovementTypeEnum.Physical, PkTypeEnum.Ghost, 30, PkEffectsEnum.Paralyze, 30, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Shadow Ball", PkMovementTypeEnum.Special, PkTypeEnum.Ghost, 80, PkEffectsEnum.SpecialDefenseDown1, 20, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Shadow Claw", PkMovementTypeEnum.Physical, PkTypeEnum.Ghost, 70, PkEffectsEnum.None, 0, PkLogicEffectsEnum.HighCritRate));
        pkMovementsList.add(new PkMove("Confuse Ray", PkMovementTypeEnum.Status, PkTypeEnum.Ghost, 0, PkEffectsEnum.Confuse, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Nightmare", PkMovementTypeEnum.Status, PkTypeEnum.Ghost, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard)); // Lógica especial: Si está dormido
        pkMovementsList.add(new PkMove("Hex", PkMovementTypeEnum.Special, PkTypeEnum.Ghost, 65, PkEffectsEnum.None, 0, PkLogicEffectsEnum.DoublePowerIfStatus));
        pkMovementsList.add(new PkMove("Shadow Sneak", PkMovementTypeEnum.Physical, PkTypeEnum.Ghost, 40, PkEffectsEnum.None, 0, PkLogicEffectsEnum.PrioritySimple));
        pkMovementsList.add(new PkMove("Destiny Bond", PkMovementTypeEnum.Status, PkTypeEnum.Ghost, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard)); // Lógica especial
        pkMovementsList.add(new PkMove("Curse", PkMovementTypeEnum.Status, PkTypeEnum.Ghost, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard)); // Lógica especial
        pkMovementsList.add(new PkMove("Ominous Wind", PkMovementTypeEnum.Special, PkTypeEnum.Ghost, 60, PkEffectsEnum.AllStatsUp1, 10, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Night Shade", PkMovementTypeEnum.Special, PkTypeEnum.Ghost, 0, PkEffectsEnum.DamageEqualsLevel, 100, PkLogicEffectsEnum.FixedDamageEqualsLevel));
        // --- POISON ---
        pkMovementsList.add(new PkMove("Poison Sting", PkMovementTypeEnum.Physical, PkTypeEnum.Poison, 15, PkEffectsEnum.Poison, 30, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Acid", PkMovementTypeEnum.Special, PkTypeEnum.Poison, 40, PkEffectsEnum.SpecialDefenseDown1, 10, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Sludge Bomb", PkMovementTypeEnum.Special, PkTypeEnum.Poison, 90, PkEffectsEnum.Poison, 30, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Poison Jab", PkMovementTypeEnum.Physical, PkTypeEnum.Poison, 80, PkEffectsEnum.Poison, 30, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Poison Powder", PkMovementTypeEnum.Status, PkTypeEnum.Poison, 0, PkEffectsEnum.Poison, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Acid Armor", PkMovementTypeEnum.Status, PkTypeEnum.Poison, 0, PkEffectsEnum.DefenseUp2, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Venoshock", PkMovementTypeEnum.Special, PkTypeEnum.Poison, 65, PkEffectsEnum.None, 0, PkLogicEffectsEnum.DoublePowerIfStatus));
        pkMovementsList.add(new PkMove("Gunk Shot", PkMovementTypeEnum.Physical, PkTypeEnum.Poison, 120, PkEffectsEnum.Poison, 30, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Sludge Wave", PkMovementTypeEnum.Special, PkTypeEnum.Poison, 95, PkEffectsEnum.Poison, 10, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Toxic Spikes", PkMovementTypeEnum.Status, PkTypeEnum.Poison, 0, PkEffectsEnum.SetHazardToxicSpikes, 100, PkLogicEffectsEnum.SetHazard));
        pkMovementsList.add(new PkMove("Coil", PkMovementTypeEnum.Status, PkTypeEnum.Poison, 0, PkEffectsEnum.AttackDefenseAccuracyUp1, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Toxic", PkMovementTypeEnum.Status, PkTypeEnum.Poison, 0, PkEffectsEnum.BadlyPoisoned, 100, PkLogicEffectsEnum.Standard));
        // --- FLYING ---
        pkMovementsList.add(new PkMove("Gust", PkMovementTypeEnum.Special, PkTypeEnum.Flying, 40, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Peck", PkMovementTypeEnum.Physical, PkTypeEnum.Flying, 35, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Wing Attack", PkMovementTypeEnum.Physical, PkTypeEnum.Flying, 60, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Air Slash", PkMovementTypeEnum.Special, PkTypeEnum.Flying, 75, PkEffectsEnum.Flinch, 30, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Feather Dance", PkMovementTypeEnum.Status, PkTypeEnum.Flying, 0, PkEffectsEnum.AttackDown2, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Drill Peck", PkMovementTypeEnum.Physical, PkTypeEnum.Flying, 80, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Roost", PkMovementTypeEnum.Status, PkTypeEnum.Flying, 0, PkEffectsEnum.HealHalfHp, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Brave Bird", PkMovementTypeEnum.Physical, PkTypeEnum.Flying, 120, PkEffectsEnum.Recoil33, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Hurricane", PkMovementTypeEnum.Special, PkTypeEnum.Flying, 110, PkEffectsEnum.Confuse, 30, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Acrobatics", PkMovementTypeEnum.Physical, PkTypeEnum.Flying, 55, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard)); // Lógica especial: Doble poder sin ítem
        pkMovementsList.add(new PkMove("Tailwind", PkMovementTypeEnum.Status, PkTypeEnum.Flying, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.SetWeather)); // Lógica especial: Equipo (Sube Vel)
        pkMovementsList.add(new PkMove("Sky Attack", PkMovementTypeEnum.Physical, PkTypeEnum.Flying, 140, PkEffectsEnum.Flinch, 30, PkLogicEffectsEnum.TwoTurn));
        // --- DARK ---
        pkMovementsList.add(new PkMove("Bite", PkMovementTypeEnum.Physical, PkTypeEnum.Dark, 60, PkEffectsEnum.Flinch, 30, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Crunch", PkMovementTypeEnum.Physical, PkTypeEnum.Dark, 80, PkEffectsEnum.DefenseDown1, 20, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Dark Pulse", PkMovementTypeEnum.Special, PkTypeEnum.Dark, 80, PkEffectsEnum.Flinch, 20, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Taunt", PkMovementTypeEnum.Status, PkTypeEnum.Dark, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard)); // Lógica especial: Volátil
        pkMovementsList.add(new PkMove("Foul Play", PkMovementTypeEnum.Physical, PkTypeEnum.Dark, 95, PkEffectsEnum.None, 0, PkLogicEffectsEnum.VariablePower)); // Lógica especial: Usa Atk oponente
        pkMovementsList.add(new PkMove("Nasty Plot", PkMovementTypeEnum.Status, PkTypeEnum.Dark, 0, PkEffectsEnum.SpecialAttackUp2, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Sucker Punch", PkMovementTypeEnum.Physical, PkTypeEnum.Dark, 70, PkEffectsEnum.None, 0, PkLogicEffectsEnum.PrioritySimple)); // Lógica especial: Prioridad condicional
        pkMovementsList.add(new PkMove("Knock Off", PkMovementTypeEnum.Physical, PkTypeEnum.Dark, 65, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard)); // Lógica especial: Quita ítem
        pkMovementsList.add(new PkMove("Snarl", PkMovementTypeEnum.Special, PkTypeEnum.Dark, 55, PkEffectsEnum.SpecialAttackDown1, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Hone Claws", PkMovementTypeEnum.Status, PkTypeEnum.Dark, 0, PkEffectsEnum.AttackAccuracyUp1, 100, PkLogicEffectsEnum.Standard));
        // --- ICE ---
        pkMovementsList.add(new PkMove("Icy Wind", PkMovementTypeEnum.Special, PkTypeEnum.Ice, 55, PkEffectsEnum.SpeedDown1, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Ice Beam", PkMovementTypeEnum.Special, PkTypeEnum.Ice, 90, PkEffectsEnum.Freeze, 10, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Ice Shard", PkMovementTypeEnum.Physical, PkTypeEnum.Ice, 40, PkEffectsEnum.None, 0, PkLogicEffectsEnum.PrioritySimple));
        pkMovementsList.add(new PkMove("Mist", PkMovementTypeEnum.Status, PkTypeEnum.Ice, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Protect)); // Lógica especial: Barrera (previene stat downs)
        pkMovementsList.add(new PkMove("Blizzard", PkMovementTypeEnum.Special, PkTypeEnum.Ice, 110, PkEffectsEnum.Freeze, 10, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Hail", PkMovementTypeEnum.Status, PkTypeEnum.Ice, 0, PkEffectsEnum.SetWeatherSnow, 100, PkLogicEffectsEnum.SetWeather));
        pkMovementsList.add(new PkMove("Ice Punch", PkMovementTypeEnum.Physical, PkTypeEnum.Ice, 75, PkEffectsEnum.Freeze, 10, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Aurora Veil", PkMovementTypeEnum.Status, PkTypeEnum.Ice, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.SetWeather)); // Lógica especial: Barrera (solo en Hail/Snow)
        pkMovementsList.add(new PkMove("Freeze-Dry", PkMovementTypeEnum.Special, PkTypeEnum.Ice, 70, PkEffectsEnum.Freeze, 10, PkLogicEffectsEnum.Standard)); // Lógica especial: Súper efectivo vs Agua
        pkMovementsList.add(new PkMove("Icicle Crash", PkMovementTypeEnum.Physical, PkTypeEnum.Ice, 85, PkEffectsEnum.Flinch, 30, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Ice Fang", PkMovementTypeEnum.Physical, PkTypeEnum.Ice, 65, PkEffectsEnum.Freeze, 10, PkLogicEffectsEnum.Standard)); // Lógica especial: También 10% Flinch
        pkMovementsList.add(new PkMove("Aurora Beam", PkMovementTypeEnum.Special, PkTypeEnum.Ice, 65, PkEffectsEnum.AttackDown1, 10, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Haze", PkMovementTypeEnum.Status, PkTypeEnum.Ice, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard)); // Lógica especial: Resetea todos los stats
        // --- STEEL ---
        pkMovementsList.add(new PkMove("Metal Claw", PkMovementTypeEnum.Physical, PkTypeEnum.Steel, 50, PkEffectsEnum.AttackUp1, 10, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Flash Cannon", PkMovementTypeEnum.Special, PkTypeEnum.Steel, 80, PkEffectsEnum.SpecialDefenseDown1, 10, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Iron Head", PkMovementTypeEnum.Physical, PkTypeEnum.Steel, 80, PkEffectsEnum.Flinch, 30, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Iron Defense", PkMovementTypeEnum.Status, PkTypeEnum.Steel, 0, PkEffectsEnum.DefenseUp2, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Meteor Mash", PkMovementTypeEnum.Physical, PkTypeEnum.Steel, 90, PkEffectsEnum.AttackUp1, 20, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Gyro Ball", PkMovementTypeEnum.Physical, PkTypeEnum.Steel, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.VariablePower)); // Lógica especial: Poder basado en Velocidad
        pkMovementsList.add(new PkMove("Bullet Punch", PkMovementTypeEnum.Physical, PkTypeEnum.Steel, 40, PkEffectsEnum.None, 0, PkLogicEffectsEnum.PrioritySimple));
        pkMovementsList.add(new PkMove("Autotomize", PkMovementTypeEnum.Status, PkTypeEnum.Steel, 0, PkEffectsEnum.SpeedUp2, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Steel Beam", PkMovementTypeEnum.Special, PkTypeEnum.Steel, 140, PkEffectsEnum.Recoil50, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Smart Strike", PkMovementTypeEnum.Physical, PkTypeEnum.Steel, 70, PkEffectsEnum.None, 0, PkLogicEffectsEnum.NeverMiss));
        // --- BUG ---
        pkMovementsList.add(new PkMove("Bug Bite", PkMovementTypeEnum.Physical, PkTypeEnum.Bug, 60, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Bug Buzz", PkMovementTypeEnum.Special, PkTypeEnum.Bug, 90, PkEffectsEnum.SpecialDefenseDown1, 10, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("X-Scissor", PkMovementTypeEnum.Physical, PkTypeEnum.Bug, 80, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("String Shot", PkMovementTypeEnum.Status, PkTypeEnum.Bug, 0, PkEffectsEnum.SpeedDown2, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Leech Life", PkMovementTypeEnum.Physical, PkTypeEnum.Bug, 80, PkEffectsEnum.DrainHalfDamage, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Quiver Dance", PkMovementTypeEnum.Status, PkTypeEnum.Bug, 0, PkEffectsEnum.QuiverDance, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("U-turn", PkMovementTypeEnum.Physical, PkTypeEnum.Bug, 70, PkEffectsEnum.SwitchUser, 100, PkLogicEffectsEnum.AttackAndSwitch));
        pkMovementsList.add(new PkMove("Megahorn", PkMovementTypeEnum.Physical, PkTypeEnum.Bug, 120, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Sticky Web", PkMovementTypeEnum.Status, PkTypeEnum.Bug, 0, PkEffectsEnum.SetHazardStickyWeb, 100, PkLogicEffectsEnum.SetHazard));
        pkMovementsList.add(new PkMove("Tail Glow", PkMovementTypeEnum.Status, PkTypeEnum.Bug, 0, PkEffectsEnum.SpecialAttackUp2, 100, PkLogicEffectsEnum.Standard)); // Lógica especial: Técnicamente +3
        pkMovementsList.add(new PkMove("Twineedle", PkMovementTypeEnum.Physical, PkTypeEnum.Bug, 25, PkEffectsEnum.Poison, 20, PkLogicEffectsEnum.MultiHit));
        pkMovementsList.add(new PkMove("Pin Missile", PkMovementTypeEnum.Physical, PkTypeEnum.Bug, 25, PkEffectsEnum.None, 0, PkLogicEffectsEnum.MultiHit));
        pkMovementsList.add(new PkMove("Signal Beam", PkMovementTypeEnum.Special, PkTypeEnum.Bug, 75, PkEffectsEnum.Confuse, 10, PkLogicEffectsEnum.Standard));
        // --- DRAGON ---
        pkMovementsList.add(new PkMove("Dragon Claw", PkMovementTypeEnum.Physical, PkTypeEnum.Dragon, 80, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Dragon Pulse", PkMovementTypeEnum.Special, PkTypeEnum.Dragon, 85, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Dragon Rush", PkMovementTypeEnum.Physical, PkTypeEnum.Dragon, 100, PkEffectsEnum.Flinch, 20, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Dragon Dance", PkMovementTypeEnum.Status, PkTypeEnum.Dragon, 0, PkEffectsEnum.DragonDance, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Outrage", PkMovementTypeEnum.Physical, PkTypeEnum.Dragon, 120, PkEffectsEnum.None, 0, PkLogicEffectsEnum.AttackWithSelfDebuff)); // Lógica especial: Confunde al usuario
        pkMovementsList.add(new PkMove("Draco Meteor", PkMovementTypeEnum.Special, PkTypeEnum.Dragon, 130, PkEffectsEnum.SpecialAttackDown2, 100, PkLogicEffectsEnum.AttackWithSelfDebuff));
        pkMovementsList.add(new PkMove("Dragon Tail", PkMovementTypeEnum.Physical, PkTypeEnum.Dragon, 60, PkEffectsEnum.ForceSwitchTarget, 100, PkLogicEffectsEnum.AttackAndForceSwitch));
        pkMovementsList.add(new PkMove("Dual Chop", PkMovementTypeEnum.Physical, PkTypeEnum.Dragon, 40, PkEffectsEnum.None, 0, PkLogicEffectsEnum.MultiHit));
        pkMovementsList.add(new PkMove("Dragon Breath", PkMovementTypeEnum.Special, PkTypeEnum.Dragon, 60, PkEffectsEnum.Paralyze, 30, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Breaking Swipe", PkMovementTypeEnum.Physical, PkTypeEnum.Dragon, 60, PkEffectsEnum.AttackDown1, 100, PkLogicEffectsEnum.Standard));
        // --- FAIRY ---
        pkMovementsList.add(new PkMove("Fairy Wind", PkMovementTypeEnum.Special, PkTypeEnum.Fairy, 40, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Dazzling Gleam", PkMovementTypeEnum.Special, PkTypeEnum.Fairy, 80, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Play Rough", PkMovementTypeEnum.Physical, PkTypeEnum.Fairy, 90, PkEffectsEnum.AttackDown1, 10, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Draining Kiss", PkMovementTypeEnum.Special, PkTypeEnum.Fairy, 50, PkEffectsEnum.Drain75Damage, 100, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Disarming Voice", PkMovementTypeEnum.Special, PkTypeEnum.Fairy, 40, PkEffectsEnum.None, 0, PkLogicEffectsEnum.NeverMiss));
        pkMovementsList.add(new PkMove("Moonblast", PkMovementTypeEnum.Special, PkTypeEnum.Fairy, 95, PkEffectsEnum.SpecialAttackDown1, 30, PkLogicEffectsEnum.Standard));
        pkMovementsList.add(new PkMove("Wish", PkMovementTypeEnum.Status, PkTypeEnum.Fairy, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard)); // Lógica especial: Cura retrasada
        pkMovementsList.add(new PkMove("Aromatherapy", PkMovementTypeEnum.Status, PkTypeEnum.Fairy, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard)); // Lógica especial: Cura equipo
        pkMovementsList.add(new PkMove("Misty Terrain", PkMovementTypeEnum.Status, PkTypeEnum.Fairy, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.SetWeather)); // Lógica especial: Campo
        pkMovementsList.add(new PkMove("Spirit Break", PkMovementTypeEnum.Physical, PkTypeEnum.Fairy, 75, PkEffectsEnum.SpecialAttackDown1, 100, PkLogicEffectsEnum.Standard));
    }
}