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
        HashMap<Item, Integer> pkItemsMap = new HashMap<>();

        // 1. Separa las listas (esto estaba bien)
        ArrayList<Item> pkHealingItems = new ArrayList<>();
        ArrayList<Item> pkStatusItems = new ArrayList<>();
        ArrayList<Item> pkBattleItems = new ArrayList<>();

        // Asegúrate de que ItemsDataBase.pkItemsList se esté cargando
        if (ItemsDataBase.pkItemsList.isEmpty()) {
            System.out.println("DEBUG: ¡ERROR! ItemsDataBase.pkItemsList está vacía.");
            return pkItemsMap; // Devuelve un mapa vacío si la BD de items no cargó
        }

        for(Item tryItem: ItemsDataBase.pkItemsList){
            if(tryItem.getItEffect() == PkEffectsEnum.HealFixedAmount || tryItem.getItEffect() == PkEffectsEnum.HealPercentage){
                pkHealingItems.add(tryItem);
            }
            else if(tryItem.getItEffect() == PkEffectsEnum.CurePoison || tryItem.getItEffect() == PkEffectsEnum.CureParalysis || tryItem.getItEffect() == PkEffectsEnum.CureSleep || tryItem.getItEffect() == PkEffectsEnum.CureBurn || tryItem.getItEffect() == PkEffectsEnum.CureFreeze || tryItem.getItEffect() == PkEffectsEnum.CureAllStatus){
                pkStatusItems.add(tryItem);
            }
            else if(tryItem.getItEffect() == PkEffectsEnum.AttackUp2 || tryItem.getItEffect() == PkEffectsEnum.DefenseUp2 || tryItem.getItEffect() == PkEffectsEnum.SpeedUp2 || tryItem.getItEffect() == PkEffectsEnum.SpecialAttackUp2 || tryItem.getItEffect() == PkEffectsEnum.SpecialDefenseUp2 || tryItem.getItEffect() == PkEffectsEnum.AccuracyUp1){
                pkBattleItems.add(tryItem);
            }
        }

        // 2. Lógica de selección ¡CORREGIDA!
        // Barajamos las listas y tomamos los primeros items.
        // Esto evita bucles infinitos y garantiza items únicos.
        java.util.Collections.shuffle(pkHealingItems);
        java.util.Collections.shuffle(pkStatusItems);
        java.util.Collections.shuffle(pkBattleItems);

        // 3. Añadimos un número aleatorio de items
        // Usamos un Set temporalmente para asegurarnos de que no hay duplicados
        java.util.Set<Item> itemsToAdd = new java.util.HashSet<>();

        // Añade 3 items de curación (si hay tantos)
        for (int i = 0; i < 3 && i < pkHealingItems.size(); i++) {
            itemsToAdd.add(pkHealingItems.get(i));
        }
        // Añade 3 items de estado (si hay tantos)
        for (int i = 0; i < 3 && i < pkStatusItems.size(); i++) {
            itemsToAdd.add(pkStatusItems.get(i));
        }
        // Añade 2 items de batalla (si hay tantos)
        for (int i = 0; i < 2 && i < pkBattleItems.size(); i++) {
            itemsToAdd.add(pkBattleItems.get(i));
        }

        // 4. Asigna un stock aleatorio a cada item seleccionado
        for(Item tryItem: itemsToAdd){
            pkItemsMap.put(tryItem, ThreadLocalRandom.current().nextInt(1, 5));
        }

        return pkItemsMap;
    }
}