package GameDesing.BattleSystem.Actions;

import GameDesing.BattleSystem.Terrain;
import GameDesing.BattleSystem.Weather;
import GameDesing.BattleSystem.TurnResult;
import Persons.Trainer;
import Pokemons.Pokemon;
import Utility.Constants.PkEffectsEnum;
import java.util.ArrayList;

public class SwitchAction implements TurnAction {
    private final Trainer trainer;
    private final Pokemon pokemonToSwitchIn;

    public SwitchAction(Trainer trainer, Pokemon pokemonToSwitchIn) {
        this.trainer = trainer;
        this.pokemonToSwitchIn = pokemonToSwitchIn;
    }

    @Override
    public int getPriority() {
        return 9;
    }

    @Override
    public Pokemon getTarget() {
        return this.pokemonToSwitchIn;
    }

    @Override
    public Pokemon getAttacker() {
        return this.pokemonToSwitchIn;
    }
    
    @Override
    public Trainer getTrainer() {
        return this.trainer;
    }

    @Override
    public ArrayList<TurnResult> execute(Pokemon opponent, Weather weather, Terrain terrain, ArrayList<PkEffectsEnum> opponentHazards) {
        ArrayList<TurnResult> results = new ArrayList<>();
        trainer.settActivePokemon(pokemonToSwitchIn); //
        results.add(new TurnResult("TRAINER_SWITCHED_POKEMON"));
        return results;
    }
    
    @Override
    public boolean isMove() {
        return false;
    }
}