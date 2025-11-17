package GameDesing.BattleSystem.Actions;

import GameDesing.BattleSystem.BattleSystem;
import GameDesing.BattleSystem.Terrain;
import GameDesing.BattleSystem.Weather;
import GameDesing.BattleSystem.TurnResult;
import Persons.Trainer;
import Pokemons.Logic.Movements.Move;
import Pokemons.Pokemon;
import Utility.Constants.PkEffectsEnum;
import Utility.Constants.PkLogicEffectsEnum;
import java.util.ArrayList;

public class MoveAction implements TurnAction {
    private final Trainer trainer;
    private final Pokemon attacker;
    private final Pokemon target;
    private final Move move;

    public MoveAction(Trainer trainer, Pokemon attacker, Pokemon target, Move move) {
        this.trainer = trainer;
        this.attacker = attacker;
        this.target = target;
        this.move = move;
    }

    @Override
    public int getPriority() {
        PkLogicEffectsEnum logic = move.getMvPkLogicEffect();
        if (logic == PkLogicEffectsEnum.PrioritySimple) {
            return 1;
        }
        if (logic == PkLogicEffectsEnum.Protect) {
            return 7;
        }
        if (logic == PkLogicEffectsEnum.AttackAndForceSwitch) {
            return -6;
        }
        return 0;
    }

    @Override
    public Pokemon getTarget() {
        return this.target;
    }

    @Override
    public Pokemon getAttacker() {
        return this.attacker;
    }
    
    @Override
    public Trainer getTrainer() {
        return this.trainer;
    }

    @Override
    public ArrayList<TurnResult> execute(Pokemon opponent, Weather weather, Terrain terrain, ArrayList<PkEffectsEnum> opponentHazards) {
        return BattleSystem.executeMove(this.trainer, this.attacker, this.target, this.move, weather, terrain, opponentHazards);
    }
    
    @Override
    public boolean isMove() {
        return true;
    }
}