package GameDesing.BattleSystem.Actions;

import GameDesing.BattleSystem.Terrain;
import GameDesing.BattleSystem.Weather;
import GameDesing.BattleSystem.TurnResult;
import Persons.Trainer;
import Pokemons.Pokemon;
import Utility.Constants.PkEffectsEnum;
import java.util.ArrayList;

public interface TurnAction {
    int getPriority(); 
    Pokemon getTarget(); 
    Pokemon getAttacker();
    Trainer getTrainer();
    ArrayList<TurnResult> execute(Pokemon opponent, Weather weather, Terrain terrain, ArrayList<PkEffectsEnum> opponentHazards);
    boolean isMove(); 
}