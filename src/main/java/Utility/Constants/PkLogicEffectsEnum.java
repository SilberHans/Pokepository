package Utility.Constants;

public enum PkLogicEffectsEnum {
   // Standard Logic: Damage + 1 simple effect
    Standard,
    
    // Priority Logic: Attacks first
    PrioritySimple,
    
    // Fixed Damage Logic: Damage = Level
    FixedDamageEqualsLevel,
    
    // High Crit Rate Logic
    HighCritRate,
    
    // Protection Logic
    Protect,
    
    // Weather Logic
    SetWeather,
    
    // Field Hazard Logic
    SetHazard,
    
    // Switch Logic
    AttackAndSwitch,

    // Force Switch Logic: Attacks and forces target switch
    AttackAndForceSwitch,
    
    // Double Power Logic: Power doubled on condition
    DoublePowerIfStatus,
    
    // Multi-Hit Logic: Hits 2-5 times
    MultiHit,
    
    // Self-Debuff Logic: Strong attack with penalty
    AttackWithSelfDebuff,
    
    // Multi-Stat Change Logic: Raises/Lowers multiple stats
    MultiStatChange,
    
    // Two-Turn Logic: Charges 1 turn, attacks 2nd
    TwoTurn,
    
    // Never-Miss Logic:
    NeverMiss,
    
    // Counter Logic:
    Counter,

    // Suicide Logic:
    Suicide,

    // Variable Power Logic: Power depends on condition
    VariablePower
}