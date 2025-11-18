package Utility.DataBase;

import Pokemons.Logic.Movements.Move;
import Utility.Constants.PkMovementTypeEnum;
import Utility.Constants.PkLogicEffectsEnum;
import Utility.Constants.PkEffectsEnum;
import Utility.Constants.PkTypeEnum;
import java.util.ArrayList;
import java.util.List;

public final class MovementsDataBase {
    private MovementsDataBase(){}
    
    public static final List<Move> pkMovementsList = new ArrayList<>();
    
    static {
       // --- NORMAL --- (Precisión añadida como último argumento)
        pkMovementsList.add(new Move("Pound", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 40, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Scratch", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 40, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Tackle", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 40, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Quick Attack", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 40, PkEffectsEnum.None, 0, PkLogicEffectsEnum.PrioritySimple, 100));
        pkMovementsList.add(new Move("Hyper Beam", PkMovementTypeEnum.Special, PkTypeEnum.Normal, 150, PkEffectsEnum.RechargeTurn, 100, PkLogicEffectsEnum.Standard, 90));
        pkMovementsList.add(new Move("Growl", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.AttackDown1, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Rage", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 20, PkEffectsEnum.AttackUp1, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Soft-Boiled", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.HealHalfHp, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Protect", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.Protect, 100, PkLogicEffectsEnum.Protect, 100));
        pkMovementsList.add(new Move("Substitute", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Sleep Talk", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Double Team", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.EvasionUp1, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Body Slam", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 85, PkEffectsEnum.Paralyze, 30, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Strength", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 80, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Facade", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 70, PkEffectsEnum.None, 0, PkLogicEffectsEnum.DoublePowerIfStatus, 100));
        pkMovementsList.add(new Move("Swords Dance", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.AttackUp2, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Attract", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.Infatuate, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Swagger", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.Confuse, 100, PkLogicEffectsEnum.Standard, 85));
        pkMovementsList.add(new Move("Endure", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Protect, 100));
        pkMovementsList.add(new Move("Extreme Speed", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 80, PkEffectsEnum.None, 0, PkLogicEffectsEnum.PrioritySimple, 100));
        pkMovementsList.add(new Move("Double-Edge", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 120, PkEffectsEnum.Recoil33, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Tri Attack", PkMovementTypeEnum.Special, PkTypeEnum.Normal, 80, PkEffectsEnum.Paralyze, 20, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Scary Face", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.SpeedDown2, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Explosion", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 250, PkEffectsEnum.Suicide, 100, PkLogicEffectsEnum.Suicide, 100));
        pkMovementsList.add(new Move("Metronome", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Super Fang", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.FixedDamageEqualsLevel, 90));
        pkMovementsList.add(new Move("Self-Destruct", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 200, PkEffectsEnum.Suicide, 100, PkLogicEffectsEnum.Suicide, 100));
        pkMovementsList.add(new Move("Swift", PkMovementTypeEnum.Special, PkTypeEnum.Normal, 60, PkEffectsEnum.None, 0, PkLogicEffectsEnum.NeverMiss, 100));
        // --- FIRE ---
        pkMovementsList.add(new Move("Ember", PkMovementTypeEnum.Special, PkTypeEnum.Fire, 40, PkEffectsEnum.Burn, 10, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Flamethrower", PkMovementTypeEnum.Special, PkTypeEnum.Fire, 90, PkEffectsEnum.Burn, 10, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Fire Fang", PkMovementTypeEnum.Physical, PkTypeEnum.Fire, 65, PkEffectsEnum.Burn, 10, PkLogicEffectsEnum.Standard, 95));
        pkMovementsList.add(new Move("Sunny Day", PkMovementTypeEnum.Status, PkTypeEnum.Fire, 0, PkEffectsEnum.SetWeatherSun, 100, PkLogicEffectsEnum.SetWeather, 100));
        pkMovementsList.add(new Move("Will-O-Wisp", PkMovementTypeEnum.Status, PkTypeEnum.Fire, 0, PkEffectsEnum.Burn, 100, PkLogicEffectsEnum.Standard, 85));
        pkMovementsList.add(new Move("Fire Blast", PkMovementTypeEnum.Special, PkTypeEnum.Fire, 110, PkEffectsEnum.Burn, 10, PkLogicEffectsEnum.Standard, 85));
        pkMovementsList.add(new Move("Flare Blitz", PkMovementTypeEnum.Physical, PkTypeEnum.Fire, 120, PkEffectsEnum.Recoil33, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Heat Wave", PkMovementTypeEnum.Special, PkTypeEnum.Fire, 95, PkEffectsEnum.Burn, 10, PkLogicEffectsEnum.Standard, 90));
        pkMovementsList.add(new Move("Lava Plume", PkMovementTypeEnum.Special, PkTypeEnum.Fire, 80, PkEffectsEnum.Burn, 30, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Fire Spin", PkMovementTypeEnum.Special, PkTypeEnum.Fire, 35, PkEffectsEnum.Trap, 100, PkLogicEffectsEnum.Standard, 85));
        pkMovementsList.add(new Move("Fire Punch", PkMovementTypeEnum.Physical, PkTypeEnum.Fire, 75, PkEffectsEnum.Burn, 10, PkLogicEffectsEnum.Standard, 100));
        // --- WATER ---
        pkMovementsList.add(new Move("Water Gun", PkMovementTypeEnum.Special, PkTypeEnum.Water, 40, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Surf", PkMovementTypeEnum.Special, PkTypeEnum.Water, 90, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Waterfall", PkMovementTypeEnum.Physical, PkTypeEnum.Water, 80, PkEffectsEnum.Flinch, 20, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Rain Dance", PkMovementTypeEnum.Status, PkTypeEnum.Water, 0, PkEffectsEnum.SetWeatherRain, 100, PkLogicEffectsEnum.SetWeather, 100));
        pkMovementsList.add(new Move("Hydro Pump", PkMovementTypeEnum.Special, PkTypeEnum.Water, 110, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 80));
        pkMovementsList.add(new Move("Aqua Jet", PkMovementTypeEnum.Physical, PkTypeEnum.Water, 40, PkEffectsEnum.None, 0, PkLogicEffectsEnum.PrioritySimple, 100));
        pkMovementsList.add(new Move("Scald", PkMovementTypeEnum.Special, PkTypeEnum.Water, 80, PkEffectsEnum.Burn, 30, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Aqua Tail", PkMovementTypeEnum.Physical, PkTypeEnum.Water, 90, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 90));
        pkMovementsList.add(new Move("Withdraw", PkMovementTypeEnum.Status, PkTypeEnum.Water, 0, PkEffectsEnum.DefenseUp1, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Shell Smash", PkMovementTypeEnum.Status, PkTypeEnum.Water, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.MultiStatChange, 100));
        pkMovementsList.add(new Move("Bubble Beam", PkMovementTypeEnum.Special, PkTypeEnum.Water, 65, PkEffectsEnum.SpeedDown1, 10, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Crabhammer", PkMovementTypeEnum.Physical, PkTypeEnum.Water, 100, PkEffectsEnum.None, 0, PkLogicEffectsEnum.HighCritRate, 90));
        // --- GRASS ---
        pkMovementsList.add(new Move("Vine Whip", PkMovementTypeEnum.Physical, PkTypeEnum.Grass, 45, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Razor Leaf", PkMovementTypeEnum.Physical, PkTypeEnum.Grass, 55, PkEffectsEnum.None, 0, PkLogicEffectsEnum.HighCritRate, 95));
        pkMovementsList.add(new Move("Mega Drain", PkMovementTypeEnum.Special, PkTypeEnum.Grass, 40, PkEffectsEnum.DrainHalfDamage, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Giga Drain", PkMovementTypeEnum.Special, PkTypeEnum.Grass, 75, PkEffectsEnum.DrainHalfDamage, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Solar Beam", PkMovementTypeEnum.Special, PkTypeEnum.Grass, 120, PkEffectsEnum.None, 0, PkLogicEffectsEnum.TwoTurn, 100));
        pkMovementsList.add(new Move("Sleep Powder", PkMovementTypeEnum.Status, PkTypeEnum.Grass, 0, PkEffectsEnum.Sleep, 100, PkLogicEffectsEnum.Standard, 75));
        pkMovementsList.add(new Move("Spore", PkMovementTypeEnum.Status, PkTypeEnum.Grass, 0, PkEffectsEnum.Sleep, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Leech Seed", PkMovementTypeEnum.Status, PkTypeEnum.Grass, 0, PkEffectsEnum.LeechSeed, 100, PkLogicEffectsEnum.Standard, 90));
        pkMovementsList.add(new Move("Petal Dance", PkMovementTypeEnum.Special, PkTypeEnum.Grass, 120, PkEffectsEnum.None, 0, PkLogicEffectsEnum.AttackWithSelfDebuff, 100));
        pkMovementsList.add(new Move("Leaf Blade", PkMovementTypeEnum.Physical, PkTypeEnum.Grass, 90, PkEffectsEnum.None, 0, PkLogicEffectsEnum.HighCritRate, 100));
        pkMovementsList.add(new Move("Energy Ball", PkMovementTypeEnum.Special, PkTypeEnum.Grass, 90, PkEffectsEnum.SpecialDefenseDown1, 10, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Stun Spore", PkMovementTypeEnum.Status, PkTypeEnum.Grass, 0, PkEffectsEnum.Paralyze, 100, PkLogicEffectsEnum.Standard, 75));
        pkMovementsList.add(new Move("Synthesis", PkMovementTypeEnum.Status, PkTypeEnum.Grass, 0, PkEffectsEnum.HealHalfHp, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Magical Leaf", PkMovementTypeEnum.Special, PkTypeEnum.Grass, 60, PkEffectsEnum.None, 0, PkLogicEffectsEnum.NeverMiss, 100));
        // --- ELECTRIC ---
        pkMovementsList.add(new Move("Thunder Shock", PkMovementTypeEnum.Special, PkTypeEnum.Electric, 40, PkEffectsEnum.Paralyze, 10, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Thunderbolt", PkMovementTypeEnum.Special, PkTypeEnum.Electric, 90, PkEffectsEnum.Paralyze, 10, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Wild Charge", PkMovementTypeEnum.Physical, PkTypeEnum.Electric, 90, PkEffectsEnum.Recoil33, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Thunder Wave", PkMovementTypeEnum.Status, PkTypeEnum.Electric, 0, PkEffectsEnum.Paralyze, 100, PkLogicEffectsEnum.Standard, 90));
        pkMovementsList.add(new Move("Thunder", PkMovementTypeEnum.Special, PkTypeEnum.Electric, 110, PkEffectsEnum.Paralyze, 30, PkLogicEffectsEnum.Standard, 70));
        pkMovementsList.add(new Move("Volt Tackle", PkMovementTypeEnum.Physical, PkTypeEnum.Electric, 120, PkEffectsEnum.Recoil33, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Volt Switch", PkMovementTypeEnum.Special, PkTypeEnum.Electric, 70, PkEffectsEnum.SwitchUser, 100, PkLogicEffectsEnum.AttackAndSwitch, 100));
        pkMovementsList.add(new Move("Discharge", PkMovementTypeEnum.Special, PkTypeEnum.Electric, 80, PkEffectsEnum.Paralyze, 30, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Thunder Punch", PkMovementTypeEnum.Physical, PkTypeEnum.Electric, 75, PkEffectsEnum.Paralyze, 10, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Nuzzle", PkMovementTypeEnum.Physical, PkTypeEnum.Electric, 20, PkEffectsEnum.Paralyze, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Spark", PkMovementTypeEnum.Physical, PkTypeEnum.Electric, 65, PkEffectsEnum.Paralyze, 30, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Electro Ball", PkMovementTypeEnum.Special, PkTypeEnum.Electric, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.VariablePower, 100));
        // --- ROCK ---
        pkMovementsList.add(new Move("Rock Throw", PkMovementTypeEnum.Physical, PkTypeEnum.Rock, 50, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 90));
        pkMovementsList.add(new Move("Rock Slide", PkMovementTypeEnum.Physical, PkTypeEnum.Rock, 75, PkEffectsEnum.Flinch, 30, PkLogicEffectsEnum.Standard, 90));
        pkMovementsList.add(new Move("Power Gem", PkMovementTypeEnum.Special, PkTypeEnum.Rock, 80, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Stealth Rock", PkMovementTypeEnum.Status, PkTypeEnum.Rock, 0, PkEffectsEnum.SetHazardStealthRock, 100, PkLogicEffectsEnum.SetHazard, 100));
        pkMovementsList.add(new Move("Stone Edge", PkMovementTypeEnum.Physical, PkTypeEnum.Rock, 100, PkEffectsEnum.None, 0, PkLogicEffectsEnum.HighCritRate, 80));
        pkMovementsList.add(new Move("Sandstorm", PkMovementTypeEnum.Status, PkTypeEnum.Rock, 0, PkEffectsEnum.SetWeatherSand, 100, PkLogicEffectsEnum.SetWeather, 100));
        pkMovementsList.add(new Move("Rock Polish", PkMovementTypeEnum.Status, PkTypeEnum.Rock, 0, PkEffectsEnum.SpeedUp2, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Head Smash", PkMovementTypeEnum.Physical, PkTypeEnum.Rock, 150, PkEffectsEnum.Recoil50, 100, PkLogicEffectsEnum.Standard, 80));
        pkMovementsList.add(new Move("Rock Blast", PkMovementTypeEnum.Physical, PkTypeEnum.Rock, 25, PkEffectsEnum.None, 0, PkLogicEffectsEnum.MultiHit, 90));
        pkMovementsList.add(new Move("Ancient Power", PkMovementTypeEnum.Special, PkTypeEnum.Rock, 60, PkEffectsEnum.AllStatsUp1, 10, PkLogicEffectsEnum.Standard, 100));
        // --- GROUND ---
        pkMovementsList.add(new Move("Mud-Slap", PkMovementTypeEnum.Special, PkTypeEnum.Ground, 20, PkEffectsEnum.AccuracyDown1, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Mud Shot", PkMovementTypeEnum.Special, PkTypeEnum.Ground, 55, PkEffectsEnum.SpeedDown1, 100, PkLogicEffectsEnum.Standard, 95));
        pkMovementsList.add(new Move("Earthquake", PkMovementTypeEnum.Physical, PkTypeEnum.Ground, 100, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Bulldoze", PkMovementTypeEnum.Physical, PkTypeEnum.Ground, 60, PkEffectsEnum.SpeedDown1, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Earth Power", PkMovementTypeEnum.Special, PkTypeEnum.Ground, 90, PkEffectsEnum.SpecialDefenseDown1, 10, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Magnitude", PkMovementTypeEnum.Physical, PkTypeEnum.Ground, 0, PkEffectsEnum.DamageRandom, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Dig", PkMovementTypeEnum.Physical, PkTypeEnum.Ground, 80, PkEffectsEnum.None, 0, PkLogicEffectsEnum.TwoTurn, 100));
        pkMovementsList.add(new Move("Spikes", PkMovementTypeEnum.Status, PkTypeEnum.Ground, 0, PkEffectsEnum.SetHazardSpikes, 100, PkLogicEffectsEnum.SetHazard, 100));
        pkMovementsList.add(new Move("Bonemerang", PkMovementTypeEnum.Physical, PkTypeEnum.Ground, 50, PkEffectsEnum.None, 0, PkLogicEffectsEnum.MultiHit, 90));
        pkMovementsList.add(new Move("Mud Bomb", PkMovementTypeEnum.Special, PkTypeEnum.Ground, 65, PkEffectsEnum.AccuracyDown1, 30, PkLogicEffectsEnum.Standard, 85));
        pkMovementsList.add(new Move("Sand Tomb", PkMovementTypeEnum.Physical, PkTypeEnum.Ground, 35, PkEffectsEnum.Trap, 100, PkLogicEffectsEnum.Standard, 85));
        // --- FIGHTING ---
        pkMovementsList.add(new Move("Rock Smash", PkMovementTypeEnum.Physical, PkTypeEnum.Fighting, 40, PkEffectsEnum.DefenseDown1, 50, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Brick Break", PkMovementTypeEnum.Physical, PkTypeEnum.Fighting, 75, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Close Combat", PkMovementTypeEnum.Physical, PkTypeEnum.Fighting, 120, PkEffectsEnum.None, 0, PkLogicEffectsEnum.AttackWithSelfDebuff, 100));
        pkMovementsList.add(new Move("Vacuum Wave", PkMovementTypeEnum.Special, PkTypeEnum.Fighting, 40, PkEffectsEnum.None, 0, PkLogicEffectsEnum.PrioritySimple, 100));
        pkMovementsList.add(new Move("Vital Throw", PkMovementTypeEnum.Physical, PkTypeEnum.Fighting, 70, PkEffectsEnum.None, 0, PkLogicEffectsEnum.NeverMiss, 100));
        pkMovementsList.add(new Move("Seismic Toss", PkMovementTypeEnum.Physical, PkTypeEnum.Fighting, 0, PkEffectsEnum.DamageEqualsLevel, 100, PkLogicEffectsEnum.FixedDamageEqualsLevel, 100));
        pkMovementsList.add(new Move("Aura Sphere", PkMovementTypeEnum.Special, PkTypeEnum.Fighting, 80, PkEffectsEnum.None, 0, PkLogicEffectsEnum.NeverMiss, 100));
        pkMovementsList.add(new Move("Drain Punch", PkMovementTypeEnum.Physical, PkTypeEnum.Fighting, 75, PkEffectsEnum.DrainHalfDamage, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Mach Punch", PkMovementTypeEnum.Physical, PkTypeEnum.Fighting, 40, PkEffectsEnum.None, 0, PkLogicEffectsEnum.PrioritySimple, 100));
        pkMovementsList.add(new Move("Bulk Up", PkMovementTypeEnum.Status, PkTypeEnum.Fighting, 0, PkEffectsEnum.AttackDefenseUp1, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Double Kick", PkMovementTypeEnum.Physical, PkTypeEnum.Fighting, 30, PkEffectsEnum.None, 0, PkLogicEffectsEnum.MultiHit, 100));
        pkMovementsList.add(new Move("Counter", PkMovementTypeEnum.Physical, PkTypeEnum.Fighting, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Counter, 100));
        // --- PSYCHIC ---
        pkMovementsList.add(new Move("Confusion", PkMovementTypeEnum.Special, PkTypeEnum.Psychic, 50, PkEffectsEnum.Confuse, 10, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Psybeam", PkMovementTypeEnum.Special, PkTypeEnum.Psychic, 65, PkEffectsEnum.Confuse, 10, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Psychic", PkMovementTypeEnum.Special, PkTypeEnum.Psychic, 90, PkEffectsEnum.SpecialDefenseDown1, 10, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Psycho Cut", PkMovementTypeEnum.Physical, PkTypeEnum.Psychic, 70, PkEffectsEnum.None, 0, PkLogicEffectsEnum.HighCritRate, 100));
        pkMovementsList.add(new Move("Calm Mind", PkMovementTypeEnum.Status, PkTypeEnum.Psychic, 0, PkEffectsEnum.CalmMind, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Agility", PkMovementTypeEnum.Status, PkTypeEnum.Psychic, 0, PkEffectsEnum.SpeedUp2, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Light Screen", PkMovementTypeEnum.Status, PkTypeEnum.Psychic, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.SetWeather, 100));
        pkMovementsList.add(new Move("Psyshock", PkMovementTypeEnum.Special, PkTypeEnum.Psychic, 80, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Zen Headbutt", PkMovementTypeEnum.Physical, PkTypeEnum.Psychic, 80, PkEffectsEnum.Flinch, 20, PkLogicEffectsEnum.Standard, 90));
        pkMovementsList.add(new Move("Trick Room", PkMovementTypeEnum.Status, PkTypeEnum.Psychic, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.SetWeather, 100));
        pkMovementsList.add(new Move("Reflect", PkMovementTypeEnum.Status, PkTypeEnum.Psychic, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.SetWeather, 100));
        pkMovementsList.add(new Move("Rest", PkMovementTypeEnum.Status, PkTypeEnum.Psychic, 0, PkEffectsEnum.HealHalfHp, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Amnesia", PkMovementTypeEnum.Status, PkTypeEnum.Psychic, 0, PkEffectsEnum.SpecialDefenseUp2, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Mirror Coat", PkMovementTypeEnum.Special, PkTypeEnum.Psychic, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Counter, 100));
        // --- GHOST ---
        pkMovementsList.add(new Move("Lick", PkMovementTypeEnum.Physical, PkTypeEnum.Ghost, 30, PkEffectsEnum.Paralyze, 30, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Shadow Ball", PkMovementTypeEnum.Special, PkTypeEnum.Ghost, 80, PkEffectsEnum.SpecialDefenseDown1, 20, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Shadow Claw", PkMovementTypeEnum.Physical, PkTypeEnum.Ghost, 70, PkEffectsEnum.None, 0, PkLogicEffectsEnum.HighCritRate, 100));
        pkMovementsList.add(new Move("Confuse Ray", PkMovementTypeEnum.Status, PkTypeEnum.Ghost, 0, PkEffectsEnum.Confuse, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Nightmare", PkMovementTypeEnum.Status, PkTypeEnum.Ghost, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Hex", PkMovementTypeEnum.Special, PkTypeEnum.Ghost, 65, PkEffectsEnum.None, 0, PkLogicEffectsEnum.DoublePowerIfStatus, 100));
        pkMovementsList.add(new Move("Shadow Sneak", PkMovementTypeEnum.Physical, PkTypeEnum.Ghost, 40, PkEffectsEnum.None, 0, PkLogicEffectsEnum.PrioritySimple, 100));
        pkMovementsList.add(new Move("Destiny Bond", PkMovementTypeEnum.Status, PkTypeEnum.Ghost, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Curse", PkMovementTypeEnum.Status, PkTypeEnum.Ghost, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Ominous Wind", PkMovementTypeEnum.Special, PkTypeEnum.Ghost, 60, PkEffectsEnum.AllStatsUp1, 10, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Night Shade", PkMovementTypeEnum.Special, PkTypeEnum.Ghost, 0, PkEffectsEnum.DamageEqualsLevel, 100, PkLogicEffectsEnum.FixedDamageEqualsLevel, 100));
        // --- POISON ---
        pkMovementsList.add(new Move("Poison Sting", PkMovementTypeEnum.Physical, PkTypeEnum.Poison, 15, PkEffectsEnum.Poison, 30, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Acid", PkMovementTypeEnum.Special, PkTypeEnum.Poison, 40, PkEffectsEnum.SpecialDefenseDown1, 10, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Sludge Bomb", PkMovementTypeEnum.Special, PkTypeEnum.Poison, 90, PkEffectsEnum.Poison, 30, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Poison Jab", PkMovementTypeEnum.Physical, PkTypeEnum.Poison, 80, PkEffectsEnum.Poison, 30, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Poison Powder", PkMovementTypeEnum.Status, PkTypeEnum.Poison, 0, PkEffectsEnum.Poison, 100, PkLogicEffectsEnum.Standard, 75));
        pkMovementsList.add(new Move("Acid Armor", PkMovementTypeEnum.Status, PkTypeEnum.Poison, 0, PkEffectsEnum.DefenseUp2, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Venoshock", PkMovementTypeEnum.Special, PkTypeEnum.Poison, 65, PkEffectsEnum.None, 0, PkLogicEffectsEnum.DoublePowerIfStatus, 100));
        pkMovementsList.add(new Move("Gunk Shot", PkMovementTypeEnum.Physical, PkTypeEnum.Poison, 120, PkEffectsEnum.Poison, 30, PkLogicEffectsEnum.Standard, 80));
        pkMovementsList.add(new Move("Sludge Wave", PkMovementTypeEnum.Special, PkTypeEnum.Poison, 95, PkEffectsEnum.Poison, 10, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Toxic Spikes", PkMovementTypeEnum.Status, PkTypeEnum.Poison, 0, PkEffectsEnum.SetHazardToxicSpikes, 100, PkLogicEffectsEnum.SetHazard, 100));
        pkMovementsList.add(new Move("Coil", PkMovementTypeEnum.Status, PkTypeEnum.Poison, 0, PkEffectsEnum.AttackDefenseAccuracyUp1, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Toxic", PkMovementTypeEnum.Status, PkTypeEnum.Poison, 0, PkEffectsEnum.BadlyPoisoned, 100, PkLogicEffectsEnum.Standard, 90));
        // --- FLYING ---
        pkMovementsList.add(new Move("Gust", PkMovementTypeEnum.Special, PkTypeEnum.Flying, 40, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Peck", PkMovementTypeEnum.Physical, PkTypeEnum.Flying, 35, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Wing Attack", PkMovementTypeEnum.Physical, PkTypeEnum.Flying, 60, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Air Slash", PkMovementTypeEnum.Special, PkTypeEnum.Flying, 75, PkEffectsEnum.Flinch, 30, PkLogicEffectsEnum.Standard, 95));
        pkMovementsList.add(new Move("Feather Dance", PkMovementTypeEnum.Status, PkTypeEnum.Flying, 0, PkEffectsEnum.AttackDown2, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Drill Peck", PkMovementTypeEnum.Physical, PkTypeEnum.Flying, 80, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Roost", PkMovementTypeEnum.Status, PkTypeEnum.Flying, 0, PkEffectsEnum.HealHalfHp, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Brave Bird", PkMovementTypeEnum.Physical, PkTypeEnum.Flying, 120, PkEffectsEnum.Recoil33, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Hurricane", PkMovementTypeEnum.Special, PkTypeEnum.Flying, 110, PkEffectsEnum.Confuse, 30, PkLogicEffectsEnum.Standard, 70));
        pkMovementsList.add(new Move("Acrobatics", PkMovementTypeEnum.Physical, PkTypeEnum.Flying, 55, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Tailwind", PkMovementTypeEnum.Status, PkTypeEnum.Flying, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.SetWeather, 100));
        pkMovementsList.add(new Move("Sky Attack", PkMovementTypeEnum.Physical, PkTypeEnum.Flying, 140, PkEffectsEnum.Flinch, 30, PkLogicEffectsEnum.TwoTurn, 90));
        // --- DARK ---
        pkMovementsList.add(new Move("Bite", PkMovementTypeEnum.Physical, PkTypeEnum.Dark, 60, PkEffectsEnum.Flinch, 30, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Crunch", PkMovementTypeEnum.Physical, PkTypeEnum.Dark, 80, PkEffectsEnum.DefenseDown1, 20, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Dark Pulse", PkMovementTypeEnum.Special, PkTypeEnum.Dark, 80, PkEffectsEnum.Flinch, 20, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Taunt", PkMovementTypeEnum.Status, PkTypeEnum.Dark, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Foul Play", PkMovementTypeEnum.Physical, PkTypeEnum.Dark, 95, PkEffectsEnum.None, 0, PkLogicEffectsEnum.VariablePower, 100));
        pkMovementsList.add(new Move("Nasty Plot", PkMovementTypeEnum.Status, PkTypeEnum.Dark, 0, PkEffectsEnum.SpecialAttackUp2, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Sucker Punch", PkMovementTypeEnum.Physical, PkTypeEnum.Dark, 70, PkEffectsEnum.None, 0, PkLogicEffectsEnum.PrioritySimple, 100));
        pkMovementsList.add(new Move("Knock Off", PkMovementTypeEnum.Physical, PkTypeEnum.Dark, 65, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Snarl", PkMovementTypeEnum.Special, PkTypeEnum.Dark, 55, PkEffectsEnum.SpecialAttackDown1, 100, PkLogicEffectsEnum.Standard, 95));
        pkMovementsList.add(new Move("Hone Claws", PkMovementTypeEnum.Status, PkTypeEnum.Dark, 0, PkEffectsEnum.AttackAccuracyUp1, 100, PkLogicEffectsEnum.Standard, 100));
        // --- ICE ---
        pkMovementsList.add(new Move("Icy Wind", PkMovementTypeEnum.Special, PkTypeEnum.Ice, 55, PkEffectsEnum.SpeedDown1, 100, PkLogicEffectsEnum.Standard, 95));
        pkMovementsList.add(new Move("Ice Beam", PkMovementTypeEnum.Special, PkTypeEnum.Ice, 90, PkEffectsEnum.Freeze, 10, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Ice Shard", PkMovementTypeEnum.Physical, PkTypeEnum.Ice, 40, PkEffectsEnum.None, 0, PkLogicEffectsEnum.PrioritySimple, 100));
        pkMovementsList.add(new Move("Mist", PkMovementTypeEnum.Status, PkTypeEnum.Ice, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Protect, 100));
        pkMovementsList.add(new Move("Blizzard", PkMovementTypeEnum.Special, PkTypeEnum.Ice, 110, PkEffectsEnum.Freeze, 10, PkLogicEffectsEnum.Standard, 70));
        pkMovementsList.add(new Move("Hail", PkMovementTypeEnum.Status, PkTypeEnum.Ice, 0, PkEffectsEnum.SetWeatherSnow, 100, PkLogicEffectsEnum.SetWeather, 100));
        pkMovementsList.add(new Move("Ice Punch", PkMovementTypeEnum.Physical, PkTypeEnum.Ice, 75, PkEffectsEnum.Freeze, 10, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Aurora Veil", PkMovementTypeEnum.Status, PkTypeEnum.Ice, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.SetWeather, 100));
        pkMovementsList.add(new Move("Freeze-Dry", PkMovementTypeEnum.Special, PkTypeEnum.Ice, 70, PkEffectsEnum.Freeze, 10, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Icicle Crash", PkMovementTypeEnum.Physical, PkTypeEnum.Ice, 85, PkEffectsEnum.Flinch, 30, PkLogicEffectsEnum.Standard, 90));
        pkMovementsList.add(new Move("Ice Fang", PkMovementTypeEnum.Physical, PkTypeEnum.Ice, 65, PkEffectsEnum.Freeze, 10, PkLogicEffectsEnum.Standard, 95));
        pkMovementsList.add(new Move("Aurora Beam", PkMovementTypeEnum.Special, PkTypeEnum.Ice, 65, PkEffectsEnum.AttackDown1, 10, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Haze", PkMovementTypeEnum.Status, PkTypeEnum.Ice, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        // --- STEEL ---
        pkMovementsList.add(new Move("Metal Claw", PkMovementTypeEnum.Physical, PkTypeEnum.Steel, 50, PkEffectsEnum.AttackUp1, 10, PkLogicEffectsEnum.Standard, 95));
        pkMovementsList.add(new Move("Flash Cannon", PkMovementTypeEnum.Special, PkTypeEnum.Steel, 80, PkEffectsEnum.SpecialDefenseDown1, 10, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Iron Head", PkMovementTypeEnum.Physical, PkTypeEnum.Steel, 80, PkEffectsEnum.Flinch, 30, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Iron Defense", PkMovementTypeEnum.Status, PkTypeEnum.Steel, 0, PkEffectsEnum.DefenseUp2, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Meteor Mash", PkMovementTypeEnum.Physical, PkTypeEnum.Steel, 90, PkEffectsEnum.AttackUp1, 20, PkLogicEffectsEnum.Standard, 90));
        pkMovementsList.add(new Move("Gyro Ball", PkMovementTypeEnum.Physical, PkTypeEnum.Steel, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.VariablePower, 100));
        pkMovementsList.add(new Move("Bullet Punch", PkMovementTypeEnum.Physical, PkTypeEnum.Steel, 40, PkEffectsEnum.None, 0, PkLogicEffectsEnum.PrioritySimple, 100));
        pkMovementsList.add(new Move("Autotomize", PkMovementTypeEnum.Status, PkTypeEnum.Steel, 0, PkEffectsEnum.SpeedUp2, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Steel Beam", PkMovementTypeEnum.Special, PkTypeEnum.Steel, 140, PkEffectsEnum.Recoil50, 100, PkLogicEffectsEnum.Standard, 95));
        pkMovementsList.add(new Move("Smart Strike", PkMovementTypeEnum.Physical, PkTypeEnum.Steel, 70, PkEffectsEnum.None, 0, PkLogicEffectsEnum.NeverMiss, 100));
        // --- BUG ---
        pkMovementsList.add(new Move("Bug Bite", PkMovementTypeEnum.Physical, PkTypeEnum.Bug, 60, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Bug Buzz", PkMovementTypeEnum.Special, PkTypeEnum.Bug, 90, PkEffectsEnum.SpecialDefenseDown1, 10, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("X-Scissor", PkMovementTypeEnum.Physical, PkTypeEnum.Bug, 80, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("String Shot", PkMovementTypeEnum.Status, PkTypeEnum.Bug, 0, PkEffectsEnum.SpeedDown2, 100, PkLogicEffectsEnum.Standard, 95));
        pkMovementsList.add(new Move("Leech Life", PkMovementTypeEnum.Physical, PkTypeEnum.Bug, 80, PkEffectsEnum.DrainHalfDamage, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Quiver Dance", PkMovementTypeEnum.Status, PkTypeEnum.Bug, 0, PkEffectsEnum.QuiverDance, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("U-turn", PkMovementTypeEnum.Physical, PkTypeEnum.Bug, 70, PkEffectsEnum.SwitchUser, 100, PkLogicEffectsEnum.AttackAndSwitch, 100));
        pkMovementsList.add(new Move("Megahorn", PkMovementTypeEnum.Physical, PkTypeEnum.Bug, 120, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 85));
        pkMovementsList.add(new Move("Sticky Web", PkMovementTypeEnum.Status, PkTypeEnum.Bug, 0, PkEffectsEnum.SetHazardStickyWeb, 100, PkLogicEffectsEnum.SetHazard, 100));
        pkMovementsList.add(new Move("Tail Glow", PkMovementTypeEnum.Status, PkTypeEnum.Bug, 0, PkEffectsEnum.SpecialAttackUp2, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Twineedle", PkMovementTypeEnum.Physical, PkTypeEnum.Bug, 25, PkEffectsEnum.Poison, 20, PkLogicEffectsEnum.MultiHit, 100));
        pkMovementsList.add(new Move("Pin Missile", PkMovementTypeEnum.Physical, PkTypeEnum.Bug, 25, PkEffectsEnum.None, 0, PkLogicEffectsEnum.MultiHit, 95));
        pkMovementsList.add(new Move("Signal Beam", PkMovementTypeEnum.Special, PkTypeEnum.Bug, 75, PkEffectsEnum.Confuse, 10, PkLogicEffectsEnum.Standard, 100));
        // --- DRAGON ---
        pkMovementsList.add(new Move("Dragon Claw", PkMovementTypeEnum.Physical, PkTypeEnum.Dragon, 80, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Dragon Pulse", PkMovementTypeEnum.Special, PkTypeEnum.Dragon, 85, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Dragon Rush", PkMovementTypeEnum.Physical, PkTypeEnum.Dragon, 100, PkEffectsEnum.Flinch, 20, PkLogicEffectsEnum.Standard, 75));
        pkMovementsList.add(new Move("Dragon Dance", PkMovementTypeEnum.Status, PkTypeEnum.Dragon, 0, PkEffectsEnum.DragonDance, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Outrage", PkMovementTypeEnum.Physical, PkTypeEnum.Dragon, 120, PkEffectsEnum.None, 0, PkLogicEffectsEnum.AttackWithSelfDebuff, 100));
        pkMovementsList.add(new Move("Draco Meteor", PkMovementTypeEnum.Special, PkTypeEnum.Dragon, 130, PkEffectsEnum.SpecialAttackDown2, 100, PkLogicEffectsEnum.AttackWithSelfDebuff, 90));
        pkMovementsList.add(new Move("Dragon Tail", PkMovementTypeEnum.Physical, PkTypeEnum.Dragon, 60, PkEffectsEnum.ForceSwitchTarget, 100, PkLogicEffectsEnum.AttackAndForceSwitch, 90));
        pkMovementsList.add(new Move("Dual Chop", PkMovementTypeEnum.Physical, PkTypeEnum.Dragon, 40, PkEffectsEnum.None, 0, PkLogicEffectsEnum.MultiHit, 90));
        pkMovementsList.add(new Move("Dragon Breath", PkMovementTypeEnum.Special, PkTypeEnum.Dragon, 60, PkEffectsEnum.Paralyze, 30, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Breaking Swipe", PkMovementTypeEnum.Physical, PkTypeEnum.Dragon, 60, PkEffectsEnum.AttackDown1, 100, PkLogicEffectsEnum.Standard, 100));
        // --- FAIRY ---
        pkMovementsList.add(new Move("Fairy Wind", PkMovementTypeEnum.Special, PkTypeEnum.Fairy, 40, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Dazzling Gleam", PkMovementTypeEnum.Special, PkTypeEnum.Fairy, 80, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Play Rough", PkMovementTypeEnum.Physical, PkTypeEnum.Fairy, 90, PkEffectsEnum.AttackDown1, 10, PkLogicEffectsEnum.Standard, 90));
        pkMovementsList.add(new Move("Draining Kiss", PkMovementTypeEnum.Special, PkTypeEnum.Fairy, 50, PkEffectsEnum.Drain75Damage, 100, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Disarming Voice", PkMovementTypeEnum.Special, PkTypeEnum.Fairy, 40, PkEffectsEnum.None, 0, PkLogicEffectsEnum.NeverMiss, 100));
        pkMovementsList.add(new Move("Moonblast", PkMovementTypeEnum.Special, PkTypeEnum.Fairy, 95, PkEffectsEnum.SpecialAttackDown1, 30, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Wish", PkMovementTypeEnum.Status, PkTypeEnum.Fairy, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Aromatherapy", PkMovementTypeEnum.Status, PkTypeEnum.Fairy, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.Standard, 100));
        pkMovementsList.add(new Move("Misty Terrain", PkMovementTypeEnum.Status, PkTypeEnum.Fairy, 0, PkEffectsEnum.None, 0, PkLogicEffectsEnum.SetWeather, 100));
        pkMovementsList.add(new Move("Spirit Break", PkMovementTypeEnum.Physical, PkTypeEnum.Fairy, 75, PkEffectsEnum.SpecialAttackDown1, 100, PkLogicEffectsEnum.Standard, 100));
    }
}