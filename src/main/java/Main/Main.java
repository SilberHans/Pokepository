package Main;

import GameDesing.Game;
import Pokemons.Pokemon;
import Utility.PokeWriting;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        System.out.println(game.testPokemon());
        
        /*
        
        
        System.out.println("PERSONLIST");
        ArrayList <Pokemon> pPokemons = Utility.LectureMon.pokeLecture("./PersonList.txt");
        
        System.out.println("PRINTING THE LIST CREATED");
        for(Pokemon p: game.getPokemonList()){
            System.out.println(p.toString());
        }
        Utility.PokeWriting.pokeWriting("./PersonList.txt", pPokemons);*/

    }
}