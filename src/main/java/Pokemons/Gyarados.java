package Pokemons;
import Iinterfaces.Water;
import Iinterfaces.Dragon;

public class Gyarados extends Pokemon implements Water,Dragon{
    @Override
    public void surf(){}
    @Override
    public void waterGun(){}
    @Override
    public void dragonDance(){}
    @Override
    public void dragonClaw(){}
}
