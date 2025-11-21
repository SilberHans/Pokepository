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
            pkItemsList.add(new Item("Potion", "Cura 20 PS de un Pokémon.", 300, PkEffectsEnum.HealFixedAmount, 20));
            pkItemsList.add(new Item("Super Potion", "Cura 60 PS de un Pokémon.", 700, PkEffectsEnum.HealFixedAmount, 60));
            pkItemsList.add(new Item("Hyper Potion", "Cura 120 PS de un Pokémon.", 1200, PkEffectsEnum.HealFixedAmount, 120));
            pkItemsList.add(new Item("Max Potion", "Cura todos los PS de un Pokémon.", 2500, PkEffectsEnum.HealPercentage, 100));

            // --- Status (PkStatus) Healing Items ---
            pkItemsList.add(new Item("Antidote", "Cura el envenenamiento.", 100, PkEffectsEnum.CurePoison, 0));
            pkItemsList.add(new Item("Paralyze Heal", "Cura la parálisis.", 200, PkEffectsEnum.CureParalysis, 0));
            pkItemsList.add(new Item("Awakening", "Despierta a un Pokémon dormido.", 250, PkEffectsEnum.CureSleep, 0));
            pkItemsList.add(new Item("Burn Heal", "Cura quemaduras.", 250, PkEffectsEnum.CureBurn, 0));
            pkItemsList.add(new Item("Ice Heal", "Descongela a un Pokémon.", 250, PkEffectsEnum.CureFreeze, 0));
            pkItemsList.add(new Item("Full Heal", "Cura todos los problemas de estado.", 600, PkEffectsEnum.CureAllStatus, 0));

            // --- Battle (Buff) Items ---
            pkItemsList.add(new Item("X Attack", "Sube el Ataque en batalla.", 500, PkEffectsEnum.AttackUp2, 0));
            pkItemsList.add(new Item("X Defense", "Sube la Defensa en batalla.", 550, PkEffectsEnum.DefenseUp2, 0));
            pkItemsList.add(new Item("X Speed", "Sube la Velocidad en batalla.", 350, PkEffectsEnum.SpeedUp2, 0));
            pkItemsList.add(new Item("X Sp. Atk", "Sube el At. Especial en batalla.", 350, PkEffectsEnum.SpecialAttackUp2, 0));
            pkItemsList.add(new Item("X Sp. Def", "Sube la Def. Especial en batalla.", 350, PkEffectsEnum.SpecialDefenseUp2, 0));
            pkItemsList.add(new Item("X Accuracy", "Sube la Precisión en batalla.", 950, PkEffectsEnum.AccuracyUp1, 0));
    }
}