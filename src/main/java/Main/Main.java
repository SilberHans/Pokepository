package Main;

import GameDesing.Game;
import Persons.Person;
import Persons.Trainer;
import Pokemons.Pokemon;
import Utility.PokeWriting;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        System.out.println(game.testPokemon());
        
        System.out.println("READING THE FILE");
        ArrayList <Person> persons = Utility.LectureMon.pLecture("./PersonList.txt");
        System.out.println("PRINTING THE TRAINERS CREATED");
        for(Person p: persons){
            System.out.println(p.toString()+"\n\n");
        }
        Utility.PokeWriting.pWriting("./infoPersonList.txt", persons);
    }
}