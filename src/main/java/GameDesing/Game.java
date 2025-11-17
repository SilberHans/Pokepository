package GameDesing;

import Persons.Trainer;
import Persons.Trader;
import Persons.NurseJoy;
import Pokemons.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
    private Trainer gTrainer1;
    private Trainer gTrainer2;
    private Trader gTrader;
    private NurseJoy gNurseJoy;
    private ArrayList<Pokemon> gPokemonList;
    private ArrayList<Trainer> gTrainerList;
    
    public Game(){
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
    }
}