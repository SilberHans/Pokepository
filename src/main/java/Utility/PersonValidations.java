package Utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

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
        if(pBirthDateStr == ""){
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
    
    public static ArrayList<MedalsEnum> gentMedalsList(){
        ArrayList<MedalsEnum> tMedalsList = new ArrayList<>();
        tMedalsList.addAll(Arrays.asList(MedalsEnum.values()));
        return tMedalsList;
    }
}