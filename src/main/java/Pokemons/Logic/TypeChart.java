package Pokemons.Logic;

import Utility.Constants.PkTypeEnum;
import java.util.HashMap;
import java.util.Map;

public final class TypeChart {
    private TypeChart(){}
    
    private static final Map<PkTypeEnum, Map<PkTypeEnum, Double>> typeChart = new HashMap<>();

public static double getPkEffectiveness(PkTypeEnum attackType, PkTypeEnum defenseType1, PkTypeEnum defenseType2) {
    if (!typeChart.containsKey(attackType)) {
        return 1.0; 
    } 
    Map<PkTypeEnum, Double> interactions = typeChart.get(attackType);
    double pkEffectiveness = interactions.getOrDefault(defenseType1, 1.0);
    if (defenseType2 != null) {
        pkEffectiveness *= interactions.getOrDefault(defenseType2, 1.0);
    }
    return pkEffectiveness;
}

    static{
        // --- Normal ---
        Map<PkTypeEnum, Double> normalMap = new HashMap<>();
        normalMap.put(PkTypeEnum.Rock, 0.5);
        normalMap.put(PkTypeEnum.Steel, 0.5);
        normalMap.put(PkTypeEnum.Ghost, 0.0);
        typeChart.put(PkTypeEnum.Normal, normalMap);

        // --- Fire ---
        Map<PkTypeEnum, Double> fireMap = new HashMap<>();
        fireMap.put(PkTypeEnum.Grass, 2.0);
        fireMap.put(PkTypeEnum.Ice, 2.0);
        fireMap.put(PkTypeEnum.Bug, 2.0);
        fireMap.put(PkTypeEnum.Steel, 2.0);
        fireMap.put(PkTypeEnum.Fire, 0.5);
        fireMap.put(PkTypeEnum.Water, 0.5);
        fireMap.put(PkTypeEnum.Rock, 0.5);
        fireMap.put(PkTypeEnum.Dragon, 0.5);
        typeChart.put(PkTypeEnum.Fire, fireMap);

        // --- Water ---
        Map<PkTypeEnum, Double> waterMap = new HashMap<>();
        waterMap.put(PkTypeEnum.Fire, 2.0);
        waterMap.put(PkTypeEnum.Ground, 2.0);
        waterMap.put(PkTypeEnum.Rock, 2.0);
        waterMap.put(PkTypeEnum.Water, 0.5);
        waterMap.put(PkTypeEnum.Grass, 0.5);
        waterMap.put(PkTypeEnum.Dragon, 0.5);
        typeChart.put(PkTypeEnum.Water, waterMap);

        // --- Grass ---
        Map<PkTypeEnum, Double> grassMap = new HashMap<>();
        grassMap.put(PkTypeEnum.Water, 2.0);
        grassMap.put(PkTypeEnum.Ground, 2.0);
        grassMap.put(PkTypeEnum.Rock, 2.0);
        grassMap.put(PkTypeEnum.Fire, 0.5);
        grassMap.put(PkTypeEnum.Grass, 0.5);
        grassMap.put(PkTypeEnum.Poison, 0.5);
        grassMap.put(PkTypeEnum.Flying, 0.5);
        grassMap.put(PkTypeEnum.Bug, 0.5);
        grassMap.put(PkTypeEnum.Dragon, 0.5);
        grassMap.put(PkTypeEnum.Steel, 0.5);
        typeChart.put(PkTypeEnum.Grass, grassMap);

        // --- Electric ---
        Map<PkTypeEnum, Double> electricMap = new HashMap<>();
        electricMap.put(PkTypeEnum.Water, 2.0);
        electricMap.put(PkTypeEnum.Flying, 2.0);
        electricMap.put(PkTypeEnum.Electric, 0.5);
        electricMap.put(PkTypeEnum.Grass, 0.5);
        electricMap.put(PkTypeEnum.Dragon, 0.5);
        electricMap.put(PkTypeEnum.Ground, 0.0);
        typeChart.put(PkTypeEnum.Electric, electricMap);

        // --- Ice ---
        Map<PkTypeEnum, Double> iceMap = new HashMap<>();
        iceMap.put(PkTypeEnum.Grass, 2.0);
        iceMap.put(PkTypeEnum.Ground, 2.0);
        iceMap.put(PkTypeEnum.Flying, 2.0);
        iceMap.put(PkTypeEnum.Dragon, 2.0);
        iceMap.put(PkTypeEnum.Fire, 0.5);
        iceMap.put(PkTypeEnum.Water, 0.5);
        iceMap.put(PkTypeEnum.Ice, 0.5);
        iceMap.put(PkTypeEnum.Steel, 0.5);
        typeChart.put(PkTypeEnum.Ice, iceMap);

        // --- Fighting ---
        Map<PkTypeEnum, Double> fightingMap = new HashMap<>();
        fightingMap.put(PkTypeEnum.Normal, 2.0);
        fightingMap.put(PkTypeEnum.Ice, 2.0);
        fightingMap.put(PkTypeEnum.Rock, 2.0);
        fightingMap.put(PkTypeEnum.Dark, 2.0);
        fightingMap.put(PkTypeEnum.Steel, 2.0);
        fightingMap.put(PkTypeEnum.Poison, 0.5);
        fightingMap.put(PkTypeEnum.Flying, 0.5);
        fightingMap.put(PkTypeEnum.Psychic, 0.5);
        fightingMap.put(PkTypeEnum.Bug, 0.5);
        fightingMap.put(PkTypeEnum.Fairy, 0.5);
        fightingMap.put(PkTypeEnum.Ghost, 0.0);
        typeChart.put(PkTypeEnum.Fighting, fightingMap);

        // --- Poison ---
        Map<PkTypeEnum, Double> poisonMap = new HashMap<>();
        poisonMap.put(PkTypeEnum.Grass, 2.0);
        poisonMap.put(PkTypeEnum.Fairy, 2.0);
        poisonMap.put(PkTypeEnum.Poison, 0.5);
        poisonMap.put(PkTypeEnum.Ground, 0.5);
        poisonMap.put(PkTypeEnum.Rock, 0.5);
        poisonMap.put(PkTypeEnum.Ghost, 0.5);
        poisonMap.put(PkTypeEnum.Steel, 0.0);
        typeChart.put(PkTypeEnum.Poison, poisonMap);

        // --- Ground ---
        Map<PkTypeEnum, Double> groundMap = new HashMap<>();
        groundMap.put(PkTypeEnum.Fire, 2.0);
        groundMap.put(PkTypeEnum.Electric, 2.0);
        groundMap.put(PkTypeEnum.Poison, 2.0);
        groundMap.put(PkTypeEnum.Rock, 2.0);
        groundMap.put(PkTypeEnum.Steel, 2.0);
        groundMap.put(PkTypeEnum.Grass, 0.5);
        groundMap.put(PkTypeEnum.Bug, 0.5);
        groundMap.put(PkTypeEnum.Flying, 0.0);
        typeChart.put(PkTypeEnum.Ground, groundMap);

        // --- Flying ---
        Map<PkTypeEnum, Double> flyingMap = new HashMap<>();
        flyingMap.put(PkTypeEnum.Grass, 2.0);
        flyingMap.put(PkTypeEnum.Fighting, 2.0);
        flyingMap.put(PkTypeEnum.Bug, 2.0);
        flyingMap.put(PkTypeEnum.Electric, 0.5);
        flyingMap.put(PkTypeEnum.Rock, 0.5);
        flyingMap.put(PkTypeEnum.Steel, 0.5);
        typeChart.put(PkTypeEnum.Flying, flyingMap);

        // --- Psychic ---
        Map<PkTypeEnum, Double> psychicMap = new HashMap<>();
        psychicMap.put(PkTypeEnum.Fighting, 2.0);
        psychicMap.put(PkTypeEnum.Poison, 2.0);
        psychicMap.put(PkTypeEnum.Psychic, 0.5);
        psychicMap.put(PkTypeEnum.Steel, 0.5);
        psychicMap.put(PkTypeEnum.Dark, 0.0);
        typeChart.put(PkTypeEnum.Psychic, psychicMap);

        // --- Bug ---
        Map<PkTypeEnum, Double> bugMap = new HashMap<>();
        bugMap.put(PkTypeEnum.Grass, 2.0);
        bugMap.put(PkTypeEnum.Psychic, 2.0);
        bugMap.put(PkTypeEnum.Dark, 2.0);
        bugMap.put(PkTypeEnum.Fire, 0.5);
        bugMap.put(PkTypeEnum.Fighting, 0.5);
        bugMap.put(PkTypeEnum.Poison, 0.5);
        bugMap.put(PkTypeEnum.Flying, 0.5);
        bugMap.put(PkTypeEnum.Ghost, 0.5);
        bugMap.put(PkTypeEnum.Steel, 0.5);
        bugMap.put(PkTypeEnum.Fairy, 0.5);
        typeChart.put(PkTypeEnum.Bug, bugMap);

        // --- Rock ---
        Map<PkTypeEnum, Double> rockMap = new HashMap<>();
        rockMap.put(PkTypeEnum.Fire, 2.0);
        rockMap.put(PkTypeEnum.Ice, 2.0);
        rockMap.put(PkTypeEnum.Flying, 2.0);
        rockMap.put(PkTypeEnum.Bug, 2.0);
        rockMap.put(PkTypeEnum.Fighting, 0.5);
        rockMap.put(PkTypeEnum.Ground, 0.5);
        rockMap.put(PkTypeEnum.Steel, 0.5);
        typeChart.put(PkTypeEnum.Rock, rockMap);

        // --- Ghost ---
        Map<PkTypeEnum, Double> ghostMap = new HashMap<>();
        ghostMap.put(PkTypeEnum.Psychic, 2.0);
        ghostMap.put(PkTypeEnum.Ghost, 2.0);
        ghostMap.put(PkTypeEnum.Dark, 0.5);
        ghostMap.put(PkTypeEnum.Normal, 0.0);
        typeChart.put(PkTypeEnum.Ghost, ghostMap);

        // --- Dragon ---
        Map<PkTypeEnum, Double> dragonMap = new HashMap<>();
        dragonMap.put(PkTypeEnum.Dragon, 2.0);
        dragonMap.put(PkTypeEnum.Steel, 0.5);
        dragonMap.put(PkTypeEnum.Fairy, 0.0);
        typeChart.put(PkTypeEnum.Dragon, dragonMap);

        // --- Dark ---
        Map<PkTypeEnum, Double> darkMap = new HashMap<>();
        darkMap.put(PkTypeEnum.Psychic, 2.0);
        darkMap.put(PkTypeEnum.Ghost, 2.0);
        darkMap.put(PkTypeEnum.Fighting, 0.5);
        darkMap.put(PkTypeEnum.Dark, 0.5);
        darkMap.put(PkTypeEnum.Fairy, 0.5);
        typeChart.put(PkTypeEnum.Dark, darkMap);

        // --- Steel ---
        Map<PkTypeEnum, Double> steelMap = new HashMap<>();
        steelMap.put(PkTypeEnum.Ice, 2.0);
        steelMap.put(PkTypeEnum.Rock, 2.0);
        steelMap.put(PkTypeEnum.Fairy, 2.0);
        steelMap.put(PkTypeEnum.Fire, 0.5);
        steelMap.put(PkTypeEnum.Water, 0.5);
        steelMap.put(PkTypeEnum.Electric, 0.5);
        steelMap.put(PkTypeEnum.Steel, 0.5);
        typeChart.put(PkTypeEnum.Steel, steelMap);

        // --- Fairy ---
        Map<PkTypeEnum, Double> fairyMap = new HashMap<>();
        fairyMap.put(PkTypeEnum.Fighting, 2.0);
        fairyMap.put(PkTypeEnum.Dragon, 2.0);
        fairyMap.put(PkTypeEnum.Dark, 2.0);
        fairyMap.put(PkTypeEnum.Fire, 0.5);
        fairyMap.put(PkTypeEnum.Poison, 0.5);
        fairyMap.put(PkTypeEnum.Steel, 0.5);
        typeChart.put(PkTypeEnum.Fairy, fairyMap);
    }
}