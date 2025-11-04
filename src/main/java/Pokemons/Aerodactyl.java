package Pokemons;
import Iinterfaces.Flying;
import Iinterfaces.Rock;

public class Aerodactyl extends Pokemon implements Flying, Rock{
    @Override
    public void fly(){}
    @Override
    public void gust(){}
    @Override
    public void stoneEdge(){}
    @Override
    public void rockThrow(){}
}
