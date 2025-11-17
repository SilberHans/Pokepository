package Pokemons.Logic;

public enum PkEffectsEnum{
    // Null Effect
    None,
    
    // Persistent Status Effects
    Paralyze,
    Burn,
    Poison,
    BadlyPoisoned,
    Sleep,
    Freeze,
    
    // Volatile Status Effects
    Confuse,
    Flinch,
    Trap,
    LeechSeed,
    Protect,
    Infatuate,

    // Self Buffs
    AttackUp1, AttackUp2,
    DefenseUp1, DefenseUp2,
    SpeedUp1, SpeedUp2,
    SpecialAttackUp1, SpecialAttackUp2,
    SpecialDefenseUp1, SpecialDefenseUp2,
    AccuracyUp1,
    EvasionUp1,
    AttackDefenseUp1,
    DragonDance,
    CalmMind,
    QuiverDance,
    AttackAccuracyUp1,
    AttackDefenseAccuracyUp1,
    AllStatsUp1,

    // Target Debuffs
    AttackDown1, AttackDown2,
    DefenseDown1, DefenseDown2,
    SpeedDown1, SpeedDown2,
    SpecialAttackDown1, SpecialAttackDown2,
    SpecialDefenseDown1, SpecialDefenseDown2,
    AccuracyDown1,
    EvasionDown1,

    // Special Logic Damage / Heal / Suicide
    DamageEqualsLevel,
    DamageRandom,
    Recoil33,
    Recoil50,
    RechargeTurn,
    HealHalfHp,
    DrainHalfDamage,
    Drain75Damage,
    Suicide,
    
    // Field Logic Weather & Hazards
    SetWeatherSun,
    SetWeatherRain,
    SetWeatherSand,
    SetWeatherSnow,
    SetHazardStealthRock,
    SetHazardSpikes,
    SetHazardToxicSpikes,
    SetHazardStickyWeb,
    
    // Switch Logic
    SwitchUser,
    ForceSwitchTarget,
    
    // Item Logic
    HealFixedAmount,
    HealPercentage,
    CurePoison,
    CureParalysis,
    CureBurn,
    CureSleep,
    CureFreeze,
    CureAllStatus,
    ReviveHalfHp,
    ReviveFullHp
}