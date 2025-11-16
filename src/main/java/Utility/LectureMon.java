package Utility;

import Persons.NurseJoy;
import Pokemons.Pokemon;
import Persons.Person;
import Persons.Trader;
import Persons.Trainer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public final class LectureMon {
       
    public static ArrayList<Pokemon> pokeLecture(String filePath){
        ArrayList <Pokemon> pokemons = new ArrayList<>();
        
        String line = "";
        
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
    
    
        public static ArrayList<Person> pLecture(String filePath){
        ArrayList<Person> persons = new ArrayList<>();
        
        String line = "";
        String pName = null;
        String pRegion = null;
        String pID = null;
        LocalDate pBirthDate = null;
        int pPokeDollars= 0;
        
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            reader.readLine();
            
            while((line=reader.readLine())!=null){
                try{
                    String[] parts = line.split(",");
                    if(parts.length>=2){
                            pName = parts[0];
                            pRegion = parts[1];
                            pID = parts[2];
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            try {
                                pBirthDate = LocalDate.parse(parts[3], formatter);
                            } catch (DateTimeParseException e) {
                                System.out.println("Fecha inválida: " + parts[3] + ", se asignará null");
                                pBirthDate = null; 
                            }
                            pPokeDollars= Integer.parseInt(parts[4]);     
                    }
                    persons.add(new Trainer());
                    persons.add(new Trader());
                    persons.add(new NurseJoy());
                }catch(Exception e){
                    System.out.println("Error creating the object: " + e.getMessage());
                }    
            }
            reader.close();    
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return persons;
    }
    
    /*
    public static ArrayList<Pokemon> gameLecture(String filePath){
        ArrayList <Pokemon> gPokemonList = new ArrayList<>();
        String line = "";
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            
            while((line=reader.readLine())!=null){    
                String[] parts = line.split(",");
            }
            reader.close();    
        }catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return gPokemonList;
    }*/
}