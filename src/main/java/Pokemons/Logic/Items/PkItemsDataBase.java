package Pokemons.Logic.Items;

import Pokemons.Logic.PkEffectsEnum;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class PkItemsDataBase {
    private PkItemsDataBase(){}
    
    private final static List<PkItem> pkItemsList = List.of();
    
    public static HashMap<PkItem, Integer> mInventoryGenerator(){
        ArrayList<PkItem> pkItems = new ArrayList<>();
        ArrayList<PkItem> pkHealingItems = new ArrayList<>();
        ArrayList<PkItem> pkStatusItems = new ArrayList<>();
        ArrayList<PkItem> pkBattleItems = new ArrayList<>();
        for(PkItem tryItem: pkItemsList){
            if(tryItem.getItEffect() == PkEffectsEnum.HealFixedAmount || tryItem.getItEffect() == PkEffectsEnum.HealPercentage){
                pkHealingItems.add(tryItem);
            }
            if(tryItem.getItEffect() == PkEffectsEnum.CurePoison || tryItem.getItEffect() == PkEffectsEnum.CureParalysis || tryItem.getItEffect() == PkEffectsEnum.CureSleep || tryItem.getItEffect() == PkEffectsEnum.CureBurn || tryItem.getItEffect() == PkEffectsEnum.CureFreeze || tryItem.getItEffect() == PkEffectsEnum.CureAllStatus){
                pkStatusItems.add(tryItem);
            }
            if(tryItem.getItEffect() == PkEffectsEnum.AttackUp2 || tryItem.getItEffect() == PkEffectsEnum.DefenseUp2 || tryItem.getItEffect() == PkEffectsEnum.SpeedUp2 || tryItem.getItEffect() == PkEffectsEnum.SpecialAttackUp2 || tryItem.getItEffect() == PkEffectsEnum.SpecialDefenseUp2 || tryItem.getItEffect() == PkEffectsEnum.AccuracyUp1){
                pkBattleItems.add(tryItem);
            }
        }
        switch(ThreadLocalRandom.current().nextInt(3)){
            case 0 ->{        
                do{
                    pkItems.add(pkHealingItems.get(ThreadLocalRandom.current().nextInt(pkHealingItems.size())));
                    pkItems.add(pkHealingItems.get(ThreadLocalRandom.current().nextInt(pkHealingItems.size())));
                    pkItems.add(pkHealingItems.get(ThreadLocalRandom.current().nextInt(pkHealingItems.size())));
                    if(pkItems.get(0) == pkItems.get(1) || pkItems.get(0) == pkItems.get(2) || pkItems.get(1) == pkItems.get(2)){
                        pkItems.set(0, null);
                        pkItems.set(1, null);
                        pkItems.set(2, null);
                    }
                }while(pkItems.get(0) == pkItems.get(1) || pkItems.get(0) == pkItems.get(2) || pkItems.get(1) == pkItems.get(2));
                do{
                    pkItems.add(pkStatusItems.get(ThreadLocalRandom.current().nextInt(pkStatusItems.size())));
                    pkItems.add(pkStatusItems.get(ThreadLocalRandom.current().nextInt(pkStatusItems.size())));
                    pkItems.add(pkStatusItems.get(ThreadLocalRandom.current().nextInt(pkStatusItems.size())));
                    if(pkItems.get(3) == pkItems.get(4) || pkItems.get(3) == pkItems.get(5) || pkItems.get(4) == pkItems.get(5)){
                        pkItems.set(3, null);
                        pkItems.set(4, null);
                        pkItems.set(5, null);
                    }
                }while(pkItems.get(3) == pkItems.get(4) || pkItems.get(3) == pkItems.get(5) || pkItems.get(4) == pkItems.get(5));
                do{
                    pkItems.add(pkBattleItems.get(ThreadLocalRandom.current().nextInt(pkBattleItems.size())));
                    pkItems.add(pkBattleItems.get(ThreadLocalRandom.current().nextInt(pkBattleItems.size())));
                    if(pkItems.get(6) == pkItems.get(7)){
                        pkItems.set(6, null);
                        pkItems.set(7, null);
                    }
                }while(pkItems.get(6) == pkItems.get(7));
            }
            case 1 ->{
                do{
                    pkItems.add(pkHealingItems.get(ThreadLocalRandom.current().nextInt(pkHealingItems.size())));
                    pkItems.add(pkHealingItems.get(ThreadLocalRandom.current().nextInt(pkHealingItems.size())));
                    pkItems.add(pkHealingItems.get(ThreadLocalRandom.current().nextInt(pkHealingItems.size())));
                    if(pkItems.get(0) == pkItems.get(1) || pkItems.get(0) == pkItems.get(2) || pkItems.get(1) == pkItems.get(2)){
                        pkItems.set(0, null);
                        pkItems.set(1, null);
                        pkItems.set(2, null);
                    }
                }while(pkItems.get(0) == pkItems.get(1) || pkItems.get(0) == pkItems.get(2) || pkItems.get(1) == pkItems.get(2));
                do{
                    pkItems.add(pkBattleItems.get(ThreadLocalRandom.current().nextInt(pkBattleItems.size())));
                    pkItems.add(pkBattleItems.get(ThreadLocalRandom.current().nextInt(pkBattleItems.size())));
                    pkItems.add(pkBattleItems.get(ThreadLocalRandom.current().nextInt(pkBattleItems.size())));
                    if(pkItems.get(3) == pkItems.get(4) || pkItems.get(3) == pkItems.get(5) || pkItems.get(4) == pkItems.get(5)){
                        pkItems.set(3, null);
                        pkItems.set(4, null);
                        pkItems.set(5, null);
                    }
                }while(pkItems.get(3) == pkItems.get(4) || pkItems.get(3) == pkItems.get(5) || pkItems.get(4) == pkItems.get(5));
                do{
                    pkItems.add(pkStatusItems.get(ThreadLocalRandom.current().nextInt(pkStatusItems.size())));
                    pkItems.add(pkStatusItems.get(ThreadLocalRandom.current().nextInt(pkStatusItems.size())));
                    if(pkItems.get(6) == pkItems.get(7)){
                        pkItems.set(6, null);
                        pkItems.set(7, null);
                    }
                }while(pkItems.get(6) == pkItems.get(7));
            }
            case 2 ->{
                do{
                    pkItems.add(pkStatusItems.get(ThreadLocalRandom.current().nextInt(pkStatusItems.size())));
                    pkItems.add(pkStatusItems.get(ThreadLocalRandom.current().nextInt(pkStatusItems.size())));
                    pkItems.add(pkStatusItems.get(ThreadLocalRandom.current().nextInt(pkStatusItems.size())));
                    if(pkItems.get(0) == pkItems.get(1) || pkItems.get(0) == pkItems.get(2) || pkItems.get(1) == pkItems.get(2)){
                        pkItems.set(0, null);
                        pkItems.set(1, null);
                        pkItems.set(2, null);
                    }
                }while(pkItems.get(0) == pkItems.get(1) || pkItems.get(0) == pkItems.get(2) || pkItems.get(1) == pkItems.get(2));
                do{
                    pkItems.add(pkBattleItems.get(ThreadLocalRandom.current().nextInt(pkBattleItems.size())));
                    pkItems.add(pkBattleItems.get(ThreadLocalRandom.current().nextInt(pkBattleItems.size())));
                    pkItems.add(pkBattleItems.get(ThreadLocalRandom.current().nextInt(pkBattleItems.size())));
                    if(pkItems.get(3) == pkItems.get(4) || pkItems.get(3) == pkItems.get(5) || pkItems.get(4) == pkItems.get(5)){
                        pkItems.set(3, null);
                        pkItems.set(4, null);
                        pkItems.set(5, null);
                    }
                }while(pkItems.get(3) == pkItems.get(4) || pkItems.get(3) == pkItems.get(5) || pkItems.get(4) == pkItems.get(5));
                do{
                    pkItems.add(pkHealingItems.get(ThreadLocalRandom.current().nextInt(pkHealingItems.size())));
                    pkItems.add(pkHealingItems.get(ThreadLocalRandom.current().nextInt(pkHealingItems.size())));
                    if(pkItems.get(6) == pkItems.get(7)){
                        pkItems.set(6, null);
                        pkItems.set(7, null);
                    }
                }while(pkItems.get(6) == pkItems.get(7));
            }
            default -> {return null;}
        }
        HashMap<PkItem, Integer> pkItemsMap = new HashMap<>();
        for(PkItem tryItem: pkItems){
            pkItemsMap.put(tryItem, (Integer) ThreadLocalRandom.current().nextInt(1, 5));
        }
        return pkItemsMap;
    }
    
    static {
        // --- HP Healing Items ---
        pkItemsList.add(new PkItem("Potion", 300, PkEffectsEnum.HealFixedAmount, 20));
        pkItemsList.add(new PkItem("Super Potion", 700, PkEffectsEnum.HealFixedAmount, 60));
        pkItemsList.add(new PkItem("Hyper Potion", 1200, PkEffectsEnum.HealFixedAmount, 120));
        pkItemsList.add(new PkItem("Max Potion", 2500, PkEffectsEnum.HealPercentage, 100));
        
        // --- Status (PkStatus) Healing Items ---
        pkItemsList.add(new PkItem("Antidote", 100, PkEffectsEnum.CurePoison, 0));
        pkItemsList.add(new PkItem("Paralyze Heal", 200, PkEffectsEnum.CureParalysis, 0));
        pkItemsList.add(new PkItem("Awakening", 250, PkEffectsEnum.CureSleep, 0));
        pkItemsList.add(new PkItem("Burn Heal", 250, PkEffectsEnum.CureBurn, 0));
        pkItemsList.add(new PkItem("Ice Heal", 250, PkEffectsEnum.CureFreeze, 0));
        pkItemsList.add(new PkItem("Full Heal", 600, PkEffectsEnum.CureAllStatus, 0));

        // --- Battle (Buff) Items ---
        pkItemsList.add(new PkItem("X Attack", 500, PkEffectsEnum.AttackUp2, 0));
        pkItemsList.add(new PkItem("X Defense", 550, PkEffectsEnum.DefenseUp2, 0));
        pkItemsList.add(new PkItem("X Speed", 350, PkEffectsEnum.SpeedUp2, 0));
        pkItemsList.add(new PkItem("X Sp. Atk", 350, PkEffectsEnum.SpecialAttackUp2, 0));
        pkItemsList.add(new PkItem("X Sp. Def", 350, PkEffectsEnum.SpecialDefenseUp2, 0));
        pkItemsList.add(new PkItem("X Accuracy", 950, PkEffectsEnum.AccuracyUp1, 0));
    }
}