package GameDesing;

import Persons.Trainer;
import Persons.Trader;
import Persons.NurseJoy;
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

public class Game {
    private Trainer gTrainer1;
    private Trainer gTrainer2;
    private Trader gTrader;
    private NurseJoy gNurseJoy;
    private ArrayList<Pokemon> gPokemonList;
    
    public Game(String trainer1Name,String trainer2Name){
        this.gTrainer1 = new Trainer(trainer1Name);
        this.gTrainer2 = new Trainer(trainer2Name);
        this.gTrader = new Trader();
        this.gNurseJoy = new NurseJoy();
        this.gPokemonList = new ArrayList<>();
        int gRndmTempMinLevel = ThreadLocalRandom.current().nextInt(1, 91);
        this.gPokemonList.add(new Alakazam(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        this.gPokemonList.add(new Electrode(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        this.gPokemonList.add(new Arcanine(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        this.gPokemonList.add(new Bulbasaur(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        this.gPokemonList.add(new Gyarados(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        this.gPokemonList.add(new Charmander(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        this.gPokemonList.add(new Dodrio(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        this.gPokemonList.add(new Dugtrio(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        this.gPokemonList.add(new Hitmonlee(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        this.gPokemonList.add(new Victreebel(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        this.gPokemonList.add(new Squirtle(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        this.gPokemonList.add(new Pikachu(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
    }
    
    public String testPokemon(){
        String str = "";
        for(Pokemon tryPokemon: this.gPokemonList){
            str += tryPokemon.toString();
        }
        return str + "\n\n" + this.gTrader.toString() + "\n\n" + this.gNurseJoy.toString();
    }

    public Trainer getgTrainer1() {
        return gTrainer1;
    }

    public void setgTrainer1(Trainer gTrainer1) {
        this.gTrainer1 = gTrainer1;
    }

    public Trainer getgTrainer2() {
        return gTrainer2;
    }

    public void setgTrainer2(Trainer gTrainer2) {
        this.gTrainer2 = gTrainer2;
    }

    public Trader getgTrader() {
        return gTrader;
    }

    public void setgTrader(Trader gTrader) {
        this.gTrader = gTrader;
    }

    public NurseJoy getgNurseJoy() {
        return gNurseJoy;
    }

    public void setgNurseJoy(NurseJoy gNurseJoy) {
        this.gNurseJoy = gNurseJoy;
    }

    public ArrayList<Pokemon> getgPokemonList() {
        return gPokemonList;
    }

    public void setgPokemonList(ArrayList<Pokemon> gPokemonList) {
        this.gPokemonList = gPokemonList;
    }
    
    
}