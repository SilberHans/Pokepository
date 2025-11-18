package Utility.Validations;

import Pokemons.Logic.Items.Item;
import Pokemons.Pokemon;
import Utility.Constants.PkEffectsEnum;
import Utility.Constants.TMedalsEnum;
import Utility.DataBase.ItemsDataBase;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public final class PersonValidations {
    private PersonValidations(){}
    
    public static String valpName(String pName){
        if(pName == null){
            throw new IllegalArgumentException("Name cannot be empty...");
        }
        if(pName.isBlank()){
            throw new IllegalArgumentException("Name cannot be empty...");
        }
        if(!pName.matches("[A-Z a-z]+")){
            throw new IllegalArgumentException("Name can only contain letters...");
        }
        if(pName.length() > 20){
            throw new IllegalArgumentException("Please choose a shorter Name...");
        }
        return pName;
    }
    
    public static LocalDate valpBirthDate(LocalDate pBirthDate){
        if(pBirthDate == null){
            throw new IllegalArgumentException("BirthDate cannot be empty...");
        }
        return pBirthDate;
    }
    
    public static LocalDate valpBirthDateStr(String pBirthDateStr){
        if(pBirthDateStr == null){
            throw new IllegalArgumentException("BirthDate cannot be empty...");
        }
        if(pBirthDateStr.equals("")){
            throw new IllegalArgumentException("Birth Date cannot be empty...");
        }
        try{
            LocalDate pBirthDate = LocalDate.parse(pBirthDateStr, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            if(pBirthDate.isAfter(LocalDate.now())){
                throw new IllegalArgumentException("Birth Date cannot be after today's date...");    
            }
            return pBirthDate;
        }catch(DateTimeParseException e){
            throw new IllegalArgumentException("Invalid date format...");    
        }
    }
    
    public static int valMoney(int pMoney){
        if(pMoney < 0){
            throw new IllegalArgumentException("You dont have ebough Money for that...");
        }
        return pMoney;
    }
    
    public static ArrayList<TMedalsEnum> gentMedalsList(){
        ArrayList<TMedalsEnum> tMedalsList = new ArrayList<>();
        tMedalsList.addAll(Arrays.asList(TMedalsEnum.values()));
        return tMedalsList;
    }
    
    public static HashMap<Item, Integer> mInventoryGenerator(){
        ArrayList<Item> pkItems = new ArrayList<>();
        ArrayList<Item> pkHealingItems = new ArrayList<>();
        ArrayList<Item> pkStatusItems = new ArrayList<>();
        ArrayList<Item> pkBattleItems = new ArrayList<>();
        for(Item tryItem: ItemsDataBase.pkItemsList){
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
        HashMap<Item, Integer> pkItemsMap = new HashMap<>();
        for(Item tryItem: pkItems){
            pkItemsMap.put(tryItem, (Integer) ThreadLocalRandom.current().nextInt(1, 5));
        }
        return pkItemsMap;
    }
}