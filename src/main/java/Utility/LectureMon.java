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
    public static ArrayList<Trainer> tLecture(String filePath){
        ArrayList <Trainer> trainers = new ArrayList<>();
        
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
        return trainers;
    }
    
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
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
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
                    if(parts.length>=5){
                            pName = parts[0];
                            pRegion = parts[1];
                            pID = parts[2];
                            pBirthDate = null;
                            pPokeDollars= Integer.parseInt(parts[4]);
                            try {
                                pBirthDate = LocalDate.parse(parts[3].trim(), formatter);
                            } catch (DateTimeParseException e) {
                                System.out.println("Fecha inválida para " + pName + ": " + parts[3] + ". Se asignará null."); // pBirthDate ya es null, no es necesario reasignar.
                            }
                            
                            try {
                                 pPokeDollars = Integer.parseInt(parts[4].trim());
                            } catch (NumberFormatException e) {
                                System.out.println("PokeDollars inválidos para " + pName + ": " + parts[4] + ". Se asignará 0.");
                                pPokeDollars = 0;
                            }
                            
                            Trainer newTrainer = new Trainer(pName, pRegion, pID, pBirthDate, pPokeDollars);
                            persons.add(newTrainer);
                                 
                    } else {
                        System.out.println("Línea incompleta, saltando: " + line);
                    }
                }catch(Exception e){
                    System.out.println("Error creating the object: " + e.getMessage());
                }    
            }
            persons.add(new Trader());
            persons.add(new NurseJoy());
            reader.close();    
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return persons;
    }
}