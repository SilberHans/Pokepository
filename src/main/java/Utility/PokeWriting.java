
package Utility;
import Persons.Person;
import Pokemons.Pokemon;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PokeWriting {
    
    public static void pWriting(String filePath, ArrayList<Person> persons){
        
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            
            writer.write("PERSONLIST");
            //
            writer.newLine();
            
            String line = "";
            for(Person p: persons){
                line = p.toString();
                writer.write(line);
                writer.newLine();
            }
            writer.close();
            System.out.println("The file was created: " + filePath);
            
        }catch(IOException e){
            System.err.println("Error: File could not be created" + e.getMessage());
        }
    }
    
    public static void pokeWriting(String filePath, ArrayList<Pokemon> pokemons){
        
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            
            writer.write("POKELIST");
            writer.newLine();
            
            String line = "";
            for(Pokemon p: pokemons){
                line = p.toString();
                writer.write(line);
                writer.newLine();
            }
            writer.close();
            System.out.println("The file was created: " + filePath);
            
        }catch(IOException e){
            System.err.println("Error: File could not be created" + e.getMessage());
        }
    }
    
    public static void gameWriting(String filePath, ArrayList<Pokemon> gPokemonList){
        
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            
            writer.write("GAME");
            //
            writer.newLine();
            
            String line = "";
            for(Pokemon g: gPokemonList){
                line = g.toString();
                writer.write(line);
                writer.newLine();
            }
            writer.close();
            System.out.println("The file was created: " + filePath);
            
        }catch(IOException e){
            System.err.println("Error: File could not be created" + e.getMessage());
        }
    }
}
