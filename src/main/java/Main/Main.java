package Main;

import GameDesing.Game;
import Pokemons.Pokemon;
import Utility.PokeWriting;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        System.out.println(game.testPokemon());
        
        PokeWriting w = new PokeWriting();
        System.out.println("POKELIST");
        ArrayList <Pokemon> pokemons = Utility.LectureMon.pokeLecture("./Pokelist.txt");
        
        System.out.println("PRINTING THE LIST CREATED");
        for(Pokemon p: pokemons){
            System.out.println(p.toString());
        }
        Utility.PokeWriting.pokeWriting("./PokeList.txt", pokemons);
        
        //
        System.out.println("PERSONLIST");
        ArrayList <Pokemon> pPokemons = Utility.LectureMon.pokeLecture("./PersonList.txt");
        
        System.out.println("PRINTING THE LIST CREATED");
        for(Pokemon p: pokemons){
            System.out.println(p.toString());
        }
        Utility.PokeWriting.pokeWriting("./PersonList.txt", pPokemons);

    }
}