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
        pkItemsList.add(new Item("Potion", "Restores 20 HP.", 300, PkEffectsEnum.HealFixedAmount, 20));
        pkItemsList.add(new Item("Super Potion", "Restores 60 HP.", 700, PkEffectsEnum.HealFixedAmount, 60));
        pkItemsList.add(new Item("Hyper Potion", "Restores 120 HP.", 1200, PkEffectsEnum.HealFixedAmount, 120));
        pkItemsList.add(new Item("Max Potion", "Fully restores HP.", 2500, PkEffectsEnum.HealPercentage, 100));
        
        // --- Status (PkStatus) Healing Items ---
        pkItemsList.add(new Item("Antidote", "Cures Poison.", 100, PkEffectsEnum.CurePoison, 0));
        pkItemsList.add(new Item("Paralyze Heal", "Cures Paralysis.", 200, PkEffectsEnum.CureParalysis, 0));
        pkItemsList.add(new Item("Awakening", "Cures Sleep.", 250, PkEffectsEnum.CureSleep, 0));
        pkItemsList.add(new Item("Burn Heal", "Cures Burn.", 250, PkEffectsEnum.CureBurn, 0));
        pkItemsList.add(new Item("Ice Heal", "Cures Freeze.", 250, PkEffectsEnum.CureFreeze, 0));
        pkItemsList.add(new Item("Full Heal", "Cures all status conditions.", 600, PkEffectsEnum.CureAllStatus, 0));

        // --- Battle (Buff) Items ---
        pkItemsList.add(new Item("X Attack", "Raises Attack in battle.", 500, PkEffectsEnum.AttackUp2, 0));
        pkItemsList.add(new Item("X Defense", "Raises Defense in battle.", 550, PkEffectsEnum.DefenseUp2, 0));
        pkItemsList.add(new Item("X Speed", "Raises Speed in battle.", 350, PkEffectsEnum.SpeedUp2, 0));
        pkItemsList.add(new Item("X Sp. Atk", "Raises Special Attack in battle.", 350, PkEffectsEnum.SpecialAttackUp2, 0));
        pkItemsList.add(new Item("X Sp. Def", "Raises Special Defense in battle.", 350, PkEffectsEnum.SpecialDefenseUp2, 0));
        pkItemsList.add(new Item("X Accuracy", "Raises Accuracy in battle.", 950, PkEffectsEnum.AccuracyUp1, 0));
    }
}