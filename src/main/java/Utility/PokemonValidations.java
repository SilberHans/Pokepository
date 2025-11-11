package Utility;

public final class PokemonValidations{
    private PokemonValidations(){}
    
    public static String valPkNickNames(String pkNickName){
        if(pkNickName == null){
            throw new IllegalArgumentException("NickName cannot be empty...");
        }
        if(pkNickName.isBlank()){
            throw new IllegalArgumentException("NickName cannot be empty...");
        }
        if(!pkNickName.matches("[A-Z a-z]+")){
            throw new IllegalArgumentException("NickName can only contain letters...");
        }
        if(pkNickName.length() > 20){
            throw new IllegalArgumentException("Please choose a shorter Name");
        }
        return pkNickName;
    }
}