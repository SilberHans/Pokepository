package Pokemons.Movements;

public enum PkEffectsEnum {
    None,
    Paralyze,
    Burn,
    Poison,
    BadlyPoisoned,
    Sleep,
    Freeze,
    Confuse,
    Flinch, 
    
    //Buffs
    AttackUp1, AttackUp2,
    DefenseUp1, DefenseUp2,
    SpeedUp1, SpeedUp2,
    SpecialAttackUp1, SpecialAttackUp2, 
    SpecialDefenseUp1, SpecialDefenseUp2,
    CalmMind,
    DragonDance,
    
    //Debuffs
    AttackDown1, AttackDown2,
    DefenseDown1, DefenseDown2,
    SpeedDown1, SpeedDown2,
    SpecialAttackDown1, SpecialAttackDown2,
    SpecialDefenseDown1, SpecialDefenseDown2,
    
    //Lógica Especial
    DamageEqualsLevel,
    DamageRandom,
    Recoil33,
    RechargeTurn,
    HealHalfHp,
    DrainHalfDamage,
    LeechSeed
}