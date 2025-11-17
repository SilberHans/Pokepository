package GameDesing;

import Items.Item;
import Persons.Trainer;
import Persons.Trader;
import Persons.NurseJoy;
import Persons.Person;
import Pokemons.Alakazam;
import Pokemons.Bulbasaur;
import Pokemons.Chansey;
import Pokemons.Charmander;
import Pokemons.Arcanine;
import Pokemons.Victreebel;
import Pokemons.Gyarados;
import Pokemons.Dodrio;
import Pokemons.Hitmonlee;
import Pokemons.Electrode;
import Pokemons.Pikachu;
import Pokemons.Pokemon;
import Pokemons.Squirtle;
import Pokemons.Dugtrio;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import Utility.PokeWriting;
import java.time.LocalDate;

public class Game {
    private Trainer gTrainer1;
    private Trainer gTrainer2;
    private Trader gTrader;
    private NurseJoy gNurseJoy;
    private ArrayList<Pokemon> gPokemonList;
    private ArrayList<Person> gPersonList;
    private ArrayList<Trainer> gTrainerList;    
    
    public Game(){
        this.gTrainerList = new ArrayList<>();
        this.gTrainer1 = new Trainer();
        this.gTrainerList.add(gTrainer1);
        this.gTrainer2 = new Trainer();
        this.gTrainerList.add(gTrainer2);
        this.gTrader = new Trader();
        this.gNurseJoy = new NurseJoy();
        this.gPokemonList = new ArrayList<>();
        int gRndmTempMinLevel = ThreadLocalRandom.current().nextInt(1, 91);
        this.gPokemonList.add(new Alakazam(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        this.gPokemonList.add(new Bulbasaur(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        this.gPokemonList.add(new Charmander(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        this.gPokemonList.add(new Arcanine(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        this.gPokemonList.add(new Victreebel(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        this.gPokemonList.add(new Gyarados(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        this.gPokemonList.add(new Dodrio(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        this.gPokemonList.add(new Hitmonlee(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        this.gPokemonList.add(new Electrode(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        this.gPokemonList.add(new Pikachu(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        this.gPokemonList.add(new Squirtle(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        this.gPokemonList.add(new Dugtrio(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        
        System.out.println("TRAINERLIST");
        System.out.println("PRINTING THE LIST CREATED");
        for(Trainer t: this.gTrainerList){
            System.out.println(t.toString());
        }
        Utility.PokeWriting.tWriting("./TrainerList.txt", this.gTrainerList);
        
        System.out.println("POKELIST");
        System.out.println("PRINTING THE LIST CREATED");
        for(Pokemon p: this.gPokemonList){
            System.out.println(p.toString());
        }
        Utility.PokeWriting.pokeWriting("./PokeList.txt", this.gPokemonList);
        
        System.out.println("\nPERSONLIST");
        ArrayList <Person> persons = Utility.LectureMon.pLecture("./PersonList.txt");
        System.out.println("PRINTING THE TRAINERS CREATED");
        for(Person p: persons){
            System.out.println(p.toString()+"\n\n");
        }
        Utility.PokeWriting.pWriting("./infoPersonList.txt", persons);
    }
    
    /*public String testPokemon(){
        String str = "";
        for(Pokemon tryPokemon: this.gPokemonList){
            str += tryPokemon.toString();
        }
        return str + "\n\n" + this.gTrader.toString() + "\n\n" + this.gNurseJoy.toString();
    }*/
}