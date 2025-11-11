package Pokemons.Movements;

import Pokemons.Logic.PkTypeEnum;
import java.util.List;

public final class Movements {
    private static final List<Move> gdbPokemonMovementList = List.of();
    
    static {
       // --- NORMAL ---
        gdbPokemonMovementList.add(new Move("Tackle", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 40, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Quick Attack", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 40, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Hyper Beam", PkMovementTypeEnum.Special, PkTypeEnum.Normal, 150, PkEffectsEnum.RechargeTurn, 100));
        gdbPokemonMovementList.add(new Move("Growl", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.AttackDown1, 100));
        gdbPokemonMovementList.add(new Move("Rage", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 20, PkEffectsEnum.AttackUp1, 100));
        gdbPokemonMovementList.add(new Move("Soft-Boiled", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.HealHalfHp, 100));
        gdbPokemonMovementList.add(new Move("Protect", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Substitute", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Sleep Talk", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Double Team", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.None, 0)); // (Necesita 'EvasionUp1')
        gdbPokemonMovementList.add(new Move("Body Slam", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 85, PkEffectsEnum.Paralyze, 30));
        gdbPokemonMovementList.add(new Move("Strength", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 80, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Facade", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 70, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Swords Dance", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.AttackUp2, 100));
        gdbPokemonMovementList.add(new Move("Attract", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.None, 0)); // (Necesita 'Infatuate')
        gdbPokemonMovementList.add(new Move("Swagger", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.Confuse, 100)); // (También AttackUp2 al oponente)
        gdbPokemonMovementList.add(new Move("Endure", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Extreme Speed", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 80, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Double-Edge", PkMovementTypeEnum.Physical, PkTypeEnum.Normal, 120, PkEffectsEnum.Recoil33, 100));
        gdbPokemonMovementList.add(new Move("Tri Attack", PkMovementTypeEnum.Special, PkTypeEnum.Normal, 80, PkEffectsEnum.Paralyze, 20));
        gdbPokemonMovementList.add(new Move("Scary Face", PkMovementTypeEnum.Status, PkTypeEnum.Normal, 0, PkEffectsEnum.SpeedDown2, 100));
        // --- FIRE ---
        gdbPokemonMovementList.add(new Move("Ember", PkMovementTypeEnum.Special, PkTypeEnum.Fire, 40, PkEffectsEnum.Burn, 10));
        gdbPokemonMovementList.add(new Move("Flamethrower", PkMovementTypeEnum.Special, PkTypeEnum.Fire, 90, PkEffectsEnum.Burn, 10));
        gdbPokemonMovementList.add(new Move("Fire Fang", PkMovementTypeEnum.Physical, PkTypeEnum.Fire, 65, PkEffectsEnum.Burn, 10));
        gdbPokemonMovementList.add(new Move("Sunny Day", PkMovementTypeEnum.Status, PkTypeEnum.Fire, 0, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Will-O-Wisp", PkMovementTypeEnum.Status, PkTypeEnum.Fire, 0, PkEffectsEnum.Burn, 100));
        gdbPokemonMovementList.add(new Move("Fire Blast", PkMovementTypeEnum.Special, PkTypeEnum.Fire, 110, PkEffectsEnum.Burn, 10));
        gdbPokemonMovementList.add(new Move("Flare Blitz", PkMovementTypeEnum.Physical, PkTypeEnum.Fire, 120, PkEffectsEnum.Recoil33, 100));
        gdbPokemonMovementList.add(new Move("Heat Wave", PkMovementTypeEnum.Special, PkTypeEnum.Fire, 95, PkEffectsEnum.Burn, 10));
        gdbPokemonMovementList.add(new Move("Lava Plume", PkMovementTypeEnum.Special, PkTypeEnum.Fire, 80, PkEffectsEnum.Burn, 30));
        gdbPokemonMovementList.add(new Move("Fire Spin", PkMovementTypeEnum.Special, PkTypeEnum.Fire, 35, PkEffectsEnum.None, 0));
        // --- WATER ---
        gdbPokemonMovementList.add(new Move("Water Gun", PkMovementTypeEnum.Special, PkTypeEnum.Water, 40, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Surf", PkMovementTypeEnum.Special, PkTypeEnum.Water, 90, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Waterfall", PkMovementTypeEnum.Physical, PkTypeEnum.Water, 80, PkEffectsEnum.Flinch, 20));
        gdbPokemonMovementList.add(new Move("Rain Dance", PkMovementTypeEnum.Status, PkTypeEnum.Water, 0, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Hydro Pump", PkMovementTypeEnum.Special, PkTypeEnum.Water, 110, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Aqua Jet", PkMovementTypeEnum.Physical, PkTypeEnum.Water, 40, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Scald", PkMovementTypeEnum.Special, PkTypeEnum.Water, 80, PkEffectsEnum.Burn, 30));
        gdbPokemonMovementList.add(new Move("Aqua Tail", PkMovementTypeEnum.Physical, PkTypeEnum.Water, 90, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Withdraw", PkMovementTypeEnum.Status, PkTypeEnum.Water, 0, PkEffectsEnum.DefenseUp1, 100));
        gdbPokemonMovementList.add(new Move("Shell Smash", PkMovementTypeEnum.Status, PkTypeEnum.Water, 0, PkEffectsEnum.None, 0));
        // --- GRASS ---
        gdbPokemonMovementList.add(new Move("Razor Leaf", PkMovementTypeEnum.Physical, PkTypeEnum.Grass, 55, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Giga Drain", PkMovementTypeEnum.Special, PkTypeEnum.Grass, 75, PkEffectsEnum.DrainHalfDamage, 100));
        gdbPokemonMovementList.add(new Move("Solar Beam", PkMovementTypeEnum.Special, PkTypeEnum.Grass, 120, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Sleep Powder", PkMovementTypeEnum.Status, PkTypeEnum.Grass, 0, PkEffectsEnum.Sleep, 100));
        gdbPokemonMovementList.add(new Move("Leech Seed", PkMovementTypeEnum.Status, PkTypeEnum.Grass, 0, PkEffectsEnum.LeechSeed, 100));
        gdbPokemonMovementList.add(new Move("Petal Dance", PkMovementTypeEnum.Special, PkTypeEnum.Grass, 120, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Leaf Blade", PkMovementTypeEnum.Physical, PkTypeEnum.Grass, 90, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Energy Ball", PkMovementTypeEnum.Special, PkTypeEnum.Grass, 90, PkEffectsEnum.SpecialDefenseDown1, 10));
        gdbPokemonMovementList.add(new Move("Stun Spore", PkMovementTypeEnum.Status, PkTypeEnum.Grass, 0, PkEffectsEnum.Paralyze, 100));
        gdbPokemonMovementList.add(new Move("Synthesis", PkMovementTypeEnum.Status, PkTypeEnum.Grass, 0, PkEffectsEnum.HealHalfHp, 100));
        // --- ELECTRIC ---
        gdbPokemonMovementList.add(new Move("Thunder Shock", PkMovementTypeEnum.Special, PkTypeEnum.Electric, 40, PkEffectsEnum.Paralyze, 10));
        gdbPokemonMovementList.add(new Move("Thunderbolt", PkMovementTypeEnum.Special, PkTypeEnum.Electric, 90, PkEffectsEnum.Paralyze, 10));
        gdbPokemonMovementList.add(new Move("Wild Charge", PkMovementTypeEnum.Physical, PkTypeEnum.Electric, 90, PkEffectsEnum.Recoil33, 100));
        gdbPokemonMovementList.add(new Move("Thunder Wave", PkMovementTypeEnum.Status, PkTypeEnum.Electric, 0, PkEffectsEnum.Paralyze, 100));
        gdbPokemonMovementList.add(new Move("Thunder", PkMovementTypeEnum.Special, PkTypeEnum.Electric, 110, PkEffectsEnum.Paralyze, 30));
        gdbPokemonMovementList.add(new Move("Volt Tackle", PkMovementTypeEnum.Physical, PkTypeEnum.Electric, 120, PkEffectsEnum.Recoil33, 100));
        gdbPokemonMovementList.add(new Move("Volt Switch", PkMovementTypeEnum.Special, PkTypeEnum.Electric, 70, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Discharge", PkMovementTypeEnum.Special, PkTypeEnum.Electric, 80, PkEffectsEnum.Paralyze, 30));
        gdbPokemonMovementList.add(new Move("Thunder Punch", PkMovementTypeEnum.Physical, PkTypeEnum.Electric, 75, PkEffectsEnum.Paralyze, 10));
        gdbPokemonMovementList.add(new Move("Nuzzle", PkMovementTypeEnum.Physical, PkTypeEnum.Electric, 20, PkEffectsEnum.Paralyze, 100));
        // --- ROCK ---
        gdbPokemonMovementList.add(new Move("Rock Throw", PkMovementTypeEnum.Physical, PkTypeEnum.Rock, 50, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Rock Slide", PkMovementTypeEnum.Physical, PkTypeEnum.Rock, 75, PkEffectsEnum.Flinch, 30));
        gdbPokemonMovementList.add(new Move("Power Gem", PkMovementTypeEnum.Special, PkTypeEnum.Rock, 80, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Stealth Rock", PkMovementTypeEnum.Status, PkTypeEnum.Rock, 0, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Stone Edge", PkMovementTypeEnum.Physical, PkTypeEnum.Rock, 100, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Sandstorm", PkMovementTypeEnum.Status, PkTypeEnum.Rock, 0, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Rock Polish", PkMovementTypeEnum.Status, PkTypeEnum.Rock, 0, PkEffectsEnum.SpeedUp2, 100));
        gdbPokemonMovementList.add(new Move("Head Smash", PkMovementTypeEnum.Physical, PkTypeEnum.Rock, 150, PkEffectsEnum.Recoil33, 100));
        gdbPokemonMovementList.add(new Move("Rock Blast", PkMovementTypeEnum.Physical, PkTypeEnum.Rock, 25, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Ancient Power", PkMovementTypeEnum.Special, PkTypeEnum.Rock, 60, PkEffectsEnum.None, 0));
        // --- GROUND ---
        gdbPokemonMovementList.add(new Move("Mud Shot", PkMovementTypeEnum.Special, PkTypeEnum.Ground, 55, PkEffectsEnum.SpeedDown1, 100));
        gdbPokemonMovementList.add(new Move("Earthquake", PkMovementTypeEnum.Physical, PkTypeEnum.Ground, 100, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Bulldoze", PkMovementTypeEnum.Physical, PkTypeEnum.Ground, 60, PkEffectsEnum.SpeedDown1, 100));
        gdbPokemonMovementList.add(new Move("Earth Power", PkMovementTypeEnum.Special, PkTypeEnum.Ground, 90, PkEffectsEnum.SpecialDefenseDown1, 10));
        gdbPokemonMovementList.add(new Move("Magnitude", PkMovementTypeEnum.Physical, PkTypeEnum.Ground, 0, PkEffectsEnum.DamageRandom, 100));
        gdbPokemonMovementList.add(new Move("Dig", PkMovementTypeEnum.Physical, PkTypeEnum.Ground, 80, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Spikes", PkMovementTypeEnum.Status, PkTypeEnum.Ground, 0, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Bonemerang", PkMovementTypeEnum.Physical, PkTypeEnum.Ground, 50, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Mud Bomb", PkMovementTypeEnum.Special, PkTypeEnum.Ground, 65, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Sand Tomb", PkMovementTypeEnum.Physical, PkTypeEnum.Ground, 35, PkEffectsEnum.None, 0));
        // --- FIGHTING ---
        gdbPokemonMovementList.add(new Move("Rock Smash", PkMovementTypeEnum.Physical, PkTypeEnum.Fighting, 40, PkEffectsEnum.DefenseDown1, 50));
        gdbPokemonMovementList.add(new Move("Brick Break", PkMovementTypeEnum.Physical, PkTypeEnum.Fighting, 75, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Close Combat", PkMovementTypeEnum.Physical, PkTypeEnum.Fighting, 120, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Vacuum Wave", PkMovementTypeEnum.Special, PkTypeEnum.Fighting, 40, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Vital Throw", PkMovementTypeEnum.Physical, PkTypeEnum.Fighting, 70, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Seismic Toss", PkMovementTypeEnum.Physical, PkTypeEnum.Fighting, 0, PkEffectsEnum.DamageEqualsLevel, 100));
        gdbPokemonMovementList.add(new Move("Aura Sphere", PkMovementTypeEnum.Special, PkTypeEnum.Fighting, 80, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Drain Punch", PkMovementTypeEnum.Physical, PkTypeEnum.Fighting, 75, PkEffectsEnum.DrainHalfDamage, 100));
        gdbPokemonMovementList.add(new Move("Mach Punch", PkMovementTypeEnum.Physical, PkTypeEnum.Fighting, 40, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Bulk Up", PkMovementTypeEnum.Status, PkTypeEnum.Fighting, 0, PkEffectsEnum.None, 0));
        // --- PSYCHIC ---
        gdbPokemonMovementList.add(new Move("Confusion", PkMovementTypeEnum.Special, PkTypeEnum.Psychic, 50, PkEffectsEnum.Confuse, 10));
        gdbPokemonMovementList.add(new Move("Psychic", PkMovementTypeEnum.Special, PkTypeEnum.Psychic, 90, PkEffectsEnum.SpecialDefenseDown1, 10));
        gdbPokemonMovementList.add(new Move("Psycho Cut", PkMovementTypeEnum.Physical, PkTypeEnum.Psychic, 70, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Calm Mind", PkMovementTypeEnum.Status, PkTypeEnum.Psychic, 0, PkEffectsEnum.CalmMind, 100));
        gdbPokemonMovementList.add(new Move("Agility", PkMovementTypeEnum.Status, PkTypeEnum.Psychic, 0, PkEffectsEnum.SpeedUp2, 100));
        gdbPokemonMovementList.add(new Move("Light Screen", PkMovementTypeEnum.Status, PkTypeEnum.Psychic, 0, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Psyshock", PkMovementTypeEnum.Special, PkTypeEnum.Psychic, 80, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Zen Headbutt", PkMovementTypeEnum.Physical, PkTypeEnum.Psychic, 80, PkEffectsEnum.Flinch, 20));
        gdbPokemonMovementList.add(new Move("Trick Room", PkMovementTypeEnum.Status, PkTypeEnum.Psychic, 0, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Reflect", PkMovementTypeEnum.Status, PkTypeEnum.Psychic, 0, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Rest", PkMovementTypeEnum.Status, PkTypeEnum.Psychic, 0, PkEffectsEnum.HealHalfHp, 100));
        // --- GHOST ---
        gdbPokemonMovementList.add(new Move("Lick", PkMovementTypeEnum.Physical, PkTypeEnum.Ghost, 30, PkEffectsEnum.Paralyze, 30));
        gdbPokemonMovementList.add(new Move("Shadow Ball", PkMovementTypeEnum.Special, PkTypeEnum.Ghost, 80, PkEffectsEnum.SpecialDefenseDown1, 20));
        gdbPokemonMovementList.add(new Move("Shadow Claw", PkMovementTypeEnum.Physical, PkTypeEnum.Ghost, 70, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Confuse Ray", PkMovementTypeEnum.Status, PkTypeEnum.Ghost, 0, PkEffectsEnum.Confuse, 100));
        gdbPokemonMovementList.add(new Move("Nightmare", PkMovementTypeEnum.Status, PkTypeEnum.Ghost, 0, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Hex", PkMovementTypeEnum.Special, PkTypeEnum.Ghost, 65, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Shadow Sneak", PkMovementTypeEnum.Physical, PkTypeEnum.Ghost, 40, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Destiny Bond", PkMovementTypeEnum.Status, PkTypeEnum.Ghost, 0, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Curse", PkMovementTypeEnum.Status, PkTypeEnum.Ghost, 0, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Ominous Wind", PkMovementTypeEnum.Special, PkTypeEnum.Ghost, 60, PkEffectsEnum.None, 0));
        // --- POISON ---
        gdbPokemonMovementList.add(new Move("Poison Sting", PkMovementTypeEnum.Physical, PkTypeEnum.Poison, 15, PkEffectsEnum.Poison, 30));
        gdbPokemonMovementList.add(new Move("Sludge Bomb", PkMovementTypeEnum.Special, PkTypeEnum.Poison, 90, PkEffectsEnum.Poison, 30));
        gdbPokemonMovementList.add(new Move("Poison Jab", PkMovementTypeEnum.Physical, PkTypeEnum.Poison, 80, PkEffectsEnum.Poison, 30));
        gdbPokemonMovementList.add(new Move("Poison Powder", PkMovementTypeEnum.Status, PkTypeEnum.Poison, 0, PkEffectsEnum.Poison, 100));
        gdbPokemonMovementList.add(new Move("Acid Armor", PkMovementTypeEnum.Status, PkTypeEnum.Poison, 0, PkEffectsEnum.DefenseUp2, 100));
        gdbPokemonMovementList.add(new Move("Venoshock", PkMovementTypeEnum.Special, PkTypeEnum.Poison, 65, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Gunk Shot", PkMovementTypeEnum.Physical, PkTypeEnum.Poison, 120, PkEffectsEnum.Poison, 30));
        gdbPokemonMovementList.add(new Move("Sludge Wave", PkMovementTypeEnum.Special, PkTypeEnum.Poison, 95, PkEffectsEnum.Poison, 10));
        gdbPokemonMovementList.add(new Move("Toxic Spikes", PkMovementTypeEnum.Status, PkTypeEnum.Poison, 0, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Coil", PkMovementTypeEnum.Status, PkTypeEnum.Poison, 0, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Toxic", PkMovementTypeEnum.Status, PkTypeEnum.Poison, 0, PkEffectsEnum.BadlyPoisoned, 100));
        // --- FLYING ---
        gdbPokemonMovementList.add(new Move("Peck", PkMovementTypeEnum.Physical, PkTypeEnum.Flying, 35, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Wing Attack", PkMovementTypeEnum.Physical, PkTypeEnum.Flying, 60, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Air Slash", PkMovementTypeEnum.Special, PkTypeEnum.Flying, 75, PkEffectsEnum.Flinch, 30));
        gdbPokemonMovementList.add(new Move("Feather Dance", PkMovementTypeEnum.Status, PkTypeEnum.Flying, 0, PkEffectsEnum.AttackDown2, 100));
        gdbPokemonMovementList.add(new Move("Drill Peck", PkMovementTypeEnum.Physical, PkTypeEnum.Flying, 80, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Roost", PkMovementTypeEnum.Status, PkTypeEnum.Flying, 0, PkEffectsEnum.HealHalfHp, 100));
        gdbPokemonMovementList.add(new Move("Brave Bird", PkMovementTypeEnum.Physical, PkTypeEnum.Flying, 120, PkEffectsEnum.Recoil33, 100));
        gdbPokemonMovementList.add(new Move("Hurricane", PkMovementTypeEnum.Special, PkTypeEnum.Flying, 110, PkEffectsEnum.Confuse, 30));
        gdbPokemonMovementList.add(new Move("Acrobatics", PkMovementTypeEnum.Physical, PkTypeEnum.Flying, 55, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Tailwind", PkMovementTypeEnum.Status, PkTypeEnum.Flying, 0, PkEffectsEnum.None, 0));
        // --- DARK ---
        gdbPokemonMovementList.add(new Move("Bite", PkMovementTypeEnum.Physical, PkTypeEnum.Dark, 60, PkEffectsEnum.Flinch, 30));
        gdbPokemonMovementList.add(new Move("Crunch", PkMovementTypeEnum.Physical, PkTypeEnum.Dark, 80, PkEffectsEnum.DefenseDown1, 20));
        gdbPokemonMovementList.add(new Move("Dark Pulse", PkMovementTypeEnum.Special, PkTypeEnum.Dark, 80, PkEffectsEnum.Flinch, 20));
        gdbPokemonMovementList.add(new Move("Taunt", PkMovementTypeEnum.Status, PkTypeEnum.Dark, 0, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Foul Play", PkMovementTypeEnum.Physical, PkTypeEnum.Dark, 95, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Nasty Plot", PkMovementTypeEnum.Status, PkTypeEnum.Dark, 0, PkEffectsEnum.SpecialAttackUp2, 100));
        gdbPokemonMovementList.add(new Move("Sucker Punch", PkMovementTypeEnum.Physical, PkTypeEnum.Dark, 70, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Knock Off", PkMovementTypeEnum.Physical, PkTypeEnum.Dark, 65, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Snarl", PkMovementTypeEnum.Special, PkTypeEnum.Dark, 55, PkEffectsEnum.SpecialAttackDown1, 100));
        gdbPokemonMovementList.add(new Move("Hone Claws", PkMovementTypeEnum.Status, PkTypeEnum.Dark, 0, PkEffectsEnum.AttackUp1, 100));
        // --- ICE ---
        gdbPokemonMovementList.add(new Move("Icy Wind", PkMovementTypeEnum.Special, PkTypeEnum.Ice, 55, PkEffectsEnum.SpeedDown1, 100));
        gdbPokemonMovementList.add(new Move("Ice Beam", PkMovementTypeEnum.Special, PkTypeEnum.Ice, 90, PkEffectsEnum.Freeze, 10));
        gdbPokemonMovementList.add(new Move("Ice Shard", PkMovementTypeEnum.Physical, PkTypeEnum.Ice, 40, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Mist", PkMovementTypeEnum.Status, PkTypeEnum.Ice, 0, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Blizzard", PkMovementTypeEnum.Special, PkTypeEnum.Ice, 110, PkEffectsEnum.Freeze, 10));
        gdbPokemonMovementList.add(new Move("Hail", PkMovementTypeEnum.Status, PkTypeEnum.Ice, 0, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Ice Punch", PkMovementTypeEnum.Physical, PkTypeEnum.Ice, 75, PkEffectsEnum.Freeze, 10));
        gdbPokemonMovementList.add(new Move("Aurora Veil", PkMovementTypeEnum.Status, PkTypeEnum.Ice, 0, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Freeze-Dry", PkMovementTypeEnum.Special, PkTypeEnum.Ice, 70, PkEffectsEnum.Freeze, 10));
        gdbPokemonMovementList.add(new Move("Icicle Crash", PkMovementTypeEnum.Physical, PkTypeEnum.Ice, 85, PkEffectsEnum.Flinch, 30));
        // --- STEEL ---
        gdbPokemonMovementList.add(new Move("Metal Claw", PkMovementTypeEnum.Physical, PkTypeEnum.Steel, 50, PkEffectsEnum.AttackUp1, 10));
        gdbPokemonMovementList.add(new Move("Flash Cannon", PkMovementTypeEnum.Special, PkTypeEnum.Steel, 80, PkEffectsEnum.SpecialDefenseDown1, 10));
        gdbPokemonMovementList.add(new Move("Iron Head", PkMovementTypeEnum.Physical, PkTypeEnum.Steel, 80, PkEffectsEnum.Flinch, 30));
        gdbPokemonMovementList.add(new Move("Iron Defense", PkMovementTypeEnum.Status, PkTypeEnum.Steel, 0, PkEffectsEnum.DefenseUp2, 100));
        gdbPokemonMovementList.add(new Move("Meteor Mash", PkMovementTypeEnum.Physical, PkTypeEnum.Steel, 90, PkEffectsEnum.AttackUp1, 20));
        gdbPokemonMovementList.add(new Move("Gyro Ball", PkMovementTypeEnum.Physical, PkTypeEnum.Steel, 0, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Bullet Punch", PkMovementTypeEnum.Physical, PkTypeEnum.Steel, 40, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Autotomize", PkMovementTypeEnum.Status, PkTypeEnum.Steel, 0, PkEffectsEnum.SpeedUp2, 100));
        gdbPokemonMovementList.add(new Move("Steel Beam", PkMovementTypeEnum.Special, PkTypeEnum.Steel, 140, PkEffectsEnum.Recoil33, 100));
        gdbPokemonMovementList.add(new Move("Smart Strike", PkMovementTypeEnum.Physical, PkTypeEnum.Steel, 70, PkEffectsEnum.None, 0));
        // --- BUG ---
        gdbPokemonMovementList.add(new Move("Bug Bite", PkMovementTypeEnum.Physical, PkTypeEnum.Bug, 60, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Bug Buzz", PkMovementTypeEnum.Special, PkTypeEnum.Bug, 90, PkEffectsEnum.SpecialDefenseDown1, 10));
        gdbPokemonMovementList.add(new Move("X-Scissor", PkMovementTypeEnum.Physical, PkTypeEnum.Bug, 80, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("String Shot", PkMovementTypeEnum.Status, PkTypeEnum.Bug, 0, PkEffectsEnum.SpeedDown2, 100));
        gdbPokemonMovementList.add(new Move("Leech Life", PkMovementTypeEnum.Physical, PkTypeEnum.Bug, 80, PkEffectsEnum.DrainHalfDamage, 100));
        gdbPokemonMovementList.add(new Move("Quiver Dance", PkMovementTypeEnum.Status, PkTypeEnum.Bug, 0, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("U-turn", PkMovementTypeEnum.Physical, PkTypeEnum.Bug, 70, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Megahorn", PkMovementTypeEnum.Physical, PkTypeEnum.Bug, 120, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Sticky Web", PkMovementTypeEnum.Status, PkTypeEnum.Bug, 0, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Tail Glow", PkMovementTypeEnum.Status, PkTypeEnum.Bug, 0, PkEffectsEnum.SpecialAttackUp2, 100));
        // --- DRAGON ---
        gdbPokemonMovementList.add(new Move("Dragon Claw", PkMovementTypeEnum.Physical, PkTypeEnum.Dragon, 80, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Dragon Pulse", PkMovementTypeEnum.Special, PkTypeEnum.Dragon, 85, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Dragon Rush", PkMovementTypeEnum.Physical, PkTypeEnum.Dragon, 100, PkEffectsEnum.Flinch, 20));
        gdbPokemonMovementList.add(new Move("Dragon Dance", PkMovementTypeEnum.Status, PkTypeEnum.Dragon, 0, PkEffectsEnum.DragonDance, 100));
        gdbPokemonMovementList.add(new Move("Outrage", PkMovementTypeEnum.Physical, PkTypeEnum.Dragon, 120, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Draco Meteor", PkMovementTypeEnum.Special, PkTypeEnum.Dragon, 130, PkEffectsEnum.SpecialAttackDown2, 100));
        gdbPokemonMovementList.add(new Move("Dragon Tail", PkMovementTypeEnum.Physical, PkTypeEnum.Dragon, 60, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Dual Chop", PkMovementTypeEnum.Physical, PkTypeEnum.Dragon, 40, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Dragon Breath", PkMovementTypeEnum.Special, PkTypeEnum.Dragon, 60, PkEffectsEnum.Paralyze, 30));
        gdbPokemonMovementList.add(new Move("Breaking Swipe", PkMovementTypeEnum.Physical, PkTypeEnum.Dragon, 60, PkEffectsEnum.AttackDown1, 100));
        // --- FAIRY ---
        gdbPokemonMovementList.add(new Move("Fairy Wind", PkMovementTypeEnum.Special, PkTypeEnum.Fairy, 40, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Dazzling Gleam", PkMovementTypeEnum.Special, PkTypeEnum.Fairy, 80, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Play Rough", PkMovementTypeEnum.Physical, PkTypeEnum.Fairy, 90, PkEffectsEnum.AttackDown1, 10));
        gdbPokemonMovementList.add(new Move("Draining Kiss", PkMovementTypeEnum.Special, PkTypeEnum.Fairy, 50, PkEffectsEnum.DrainHalfDamage, 100));
        gdbPokemonMovementList.add(new Move("Disarming Voice", PkMovementTypeEnum.Special, PkTypeEnum.Fairy, 40, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Moonblast", PkMovementTypeEnum.Special, PkTypeEnum.Fairy, 95, PkEffectsEnum.SpecialAttackDown1, 30));
        gdbPokemonMovementList.add(new Move("Wish", PkMovementTypeEnum.Status, PkTypeEnum.Fairy, 0, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Aromatherapy", PkMovementTypeEnum.Status, PkTypeEnum.Fairy, 0, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Misty Terrain", PkMovementTypeEnum.Status, PkTypeEnum.Fairy, 0, PkEffectsEnum.None, 0));
        gdbPokemonMovementList.add(new Move("Spirit Break", PkMovementTypeEnum.Physical, PkTypeEnum.Fairy, 75, PkEffectsEnum.SpecialAttackDown1, 100));
    }
}