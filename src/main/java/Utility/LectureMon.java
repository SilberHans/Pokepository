package Utility;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import Utility.PokeWriting;
import Pokemons.Pokemon;
import Persons.Person;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public final class LectureMon {
    
    public void Lecture(){
        try{
            String contend = "C:/pokeList";
            System.out.println(contend);
        }catch(Exception e){
            e.printStackTrace();
        } 
    }
    
    public static ArrayList<Person> pLecture(String filePath){
        
        ArrayList<Person> persons = new ArrayList<>();
        
        String line = "";
        String Line = null;
        String pName = null;
        String pID = null;
        String pRegion = null;
        LocalDate pBirthDate = null;
        int pAge = 0;
        int pPokeDollars= 0;

        String njAdmissionDate = null;
        int njExpYears= 0;
        boolean njAvailability = true;

        int tMedals = 0;
        
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            reader.readLine();
            
            while((line=reader.readLine())!=null){
                String[] parts = line.split(",");
            }
            reader.close();    
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return persons;
    }
   
    public static ArrayList<Pokemon> pokeLecture(String filePath, ArrayList<Person> persons){
        
        ArrayList <Pokemon> pokemons = new ArrayList<>();
        
        String line = "";
        String Line = null;
        String pName = null;
        String pID = null;
        String pRegion = null;
        LocalDate pBirthDate = null;
        int pAge = 0;
        int pPokeDollars= 0;

        String njAdmissionDate = null;
        int njExpYears= 0;
        boolean njAvailability = true;

        int tMedals = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            
            while((line=reader.readLine())!=null){    
                String[] parts = line.split(",");
            }
            reader.close();    
        }catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return pokemons;
    }
}