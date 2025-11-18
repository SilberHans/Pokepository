package Utility.DataBase;

import Pokemons.*;
import Pokemons.Pokemon;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public final class PokemonDataBase {
    private PokemonDataBase(){}
    
    public static ArrayList<Pokemon> pkPokemonList = new ArrayList<>();
    private static final int gRndmTempMinLevel = ThreadLocalRandom.current().nextInt(1, 91);
    
    static{
        pkPokemonList.add(new Alakazam(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        pkPokemonList.add(new Bulbasaur(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        pkPokemonList.add(new Charmander(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        pkPokemonList.add(new Arcanine(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        pkPokemonList.add(new Victreebel(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        pkPokemonList.add(new Gyarados(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        pkPokemonList.add(new Dodrio(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        pkPokemonList.add(new Hitmonlee(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        pkPokemonList.add(new Electrode(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        pkPokemonList.add(new Pikachu(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        pkPokemonList.add(new Squirtle(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
        pkPokemonList.add(new Dugtrio(ThreadLocalRandom.current().nextInt(gRndmTempMinLevel, gRndmTempMinLevel + 11)));
    }
}