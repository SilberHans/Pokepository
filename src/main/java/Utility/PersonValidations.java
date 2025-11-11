package Utility;

public class PersonValidations {
    public static String valpName(String pName){
        if(pName == null){
            throw new IllegalArgumentException("Name cannot be empty...");
        }
        if(pName.isBlank()){
            throw new IllegalArgumentException("Name annot be empty...");
        }
        if(!pName.matches("[A-Z a-z]+")){
            throw new IllegalArgumentException("Name can only contain letters...");
        }
        if(pName.length() > 20){
            throw new IllegalArgumentException("Please choose a shorter Name...");
        }
        return pName;
    }
}