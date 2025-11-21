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
        return 9; // Prioridad alta para cambios
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
        
        // 1. Realiza el cambio lógico
        trainer.settActivePokemon(pokemonToSwitchIn); 
        
        // 2. ¡LÍNEA CORREGIDA!
        // Crea el TurnResult usando el constructor de 3 argumentos (key, message, target)
        // BattlePanel usará 'pokemonToSwitchIn' (que es el target) para la animación.
        results.add(new TurnResult(
            "TRAINER_SWITCHED_POKEMON", 
            trainer.getpName() + " saca a " + pokemonToSwitchIn.getPkNickName() + "!",
            pokemonToSwitchIn // Pasa el Pokémon que está entrando
        ));
        
        return results;
    }
    
    @Override
    public boolean isMove() {
        return false;
    }
}