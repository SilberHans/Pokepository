package Utility.DataBase;

import Pokemons.Logic.Items.Item;
import Utility.Constants.PkEffectsEnum;
import java.util.ArrayList;
import java.util.List;

public final class ItemsDataBase {
    private ItemsDataBase(){}
    
    public static final List<Item> pkItemsList = new ArrayList<>();
    
    static {
        // --- HP Healing Items ---
        pkItemsList.add(new Item("Potion", 300, PkEffectsEnum.HealFixedAmount, 20));
        pkItemsList.add(new Item("Super Potion", 700, PkEffectsEnum.HealFixedAmount, 60));
        pkItemsList.add(new Item("Hyper Potion", 1200, PkEffectsEnum.HealFixedAmount, 120));
        pkItemsList.add(new Item("Max Potion", 2500, PkEffectsEnum.HealPercentage, 100));
        
        // --- Status (PkStatus) Healing Items ---
        pkItemsList.add(new Item("Antidote", 100, PkEffectsEnum.CurePoison, 0));
        pkItemsList.add(new Item("Paralyze Heal", 200, PkEffectsEnum.CureParalysis, 0));
        pkItemsList.add(new Item("Awakening", 250, PkEffectsEnum.CureSleep, 0));
        pkItemsList.add(new Item("Burn Heal", 250, PkEffectsEnum.CureBurn, 0));
        pkItemsList.add(new Item("Ice Heal", 250, PkEffectsEnum.CureFreeze, 0));
        pkItemsList.add(new Item("Full Heal", 600, PkEffectsEnum.CureAllStatus, 0));

        // --- Battle (Buff) Items ---
        pkItemsList.add(new Item("X Attack", 500, PkEffectsEnum.AttackUp2, 0));
        pkItemsList.add(new Item("X Defense", 550, PkEffectsEnum.DefenseUp2, 0));
        pkItemsList.add(new Item("X Speed", 350, PkEffectsEnum.SpeedUp2, 0));
        pkItemsList.add(new Item("X Sp. Atk", 350, PkEffectsEnum.SpecialAttackUp2, 0));
        pkItemsList.add(new Item("X Sp. Def", 350, PkEffectsEnum.SpecialDefenseUp2, 0));
        pkItemsList.add(new Item("X Accuracy", 950, PkEffectsEnum.AccuracyUp1, 0));
    }
}