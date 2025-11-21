package GameDesing.BattleSystem.Actions;

import GameDesing.BattleSystem.BattleSystem;
import GameDesing.BattleSystem.Terrain;
import GameDesing.BattleSystem.Weather;
import GameDesing.BattleSystem.TurnResult;
import Persons.Trainer;
import Pokemons.Logic.Items.Item;
import Pokemons.Pokemon;
import Utility.Constants.PkEffectsEnum;
import java.util.ArrayList;

public class ItemAction implements TurnAction {
    private final Trainer trainer;
    private final Pokemon target;
    private final Item item;

    public ItemAction(Trainer trainer, Pokemon target, Item item) {
        this.trainer = trainer;
        this.target = target;
        this.item = item;
    }

    @Override
    public int getPriority() {
        return 8;
    }

    @Override
    public Pokemon getTarget() {
        return this.target;
    }

    @Override
    public Pokemon getAttacker() {
        return this.target; 
    }
    
    @Override
    public Trainer getTrainer() {
        return this.trainer;
    }

    @Override
    public ArrayList<TurnResult> execute(Pokemon opponent, Weather weather, Terrain terrain, ArrayList<PkEffectsEnum> opponentHazards) {
        return BattleSystem.executeItem(this.trainer, this.target, this.item);
    }
    
    @Override
    public boolean isMove() {
        return false;
    }
}