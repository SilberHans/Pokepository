package GameDesing;

import GameDesing.BattleSystem.Actions.MoveAction;
import GameDesing.BattleSystem.Actions.SwitchAction;
import GameDesing.BattleSystem.Actions.TurnAction;
import GameDesing.BattleSystem.BattleSystem;
import GameDesing.BattleSystem.Terrain;
import GameDesing.BattleSystem.TurnResult;
import GameDesing.BattleSystem.Weather;
import Persons.*;
import Pokemons.*;
import Pokemons.Logic.Movements.Move;
import Pokemons.Logic.TypeChart;
import Utility.Constants.PkEffectsEnum;
import Utility.Constants.PkStatusEnum;
import Utility.Constants.PkTypeEnum;
import Utility.DataBase.PokemonDataBase;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Game {
    private ArrayList<Trainer> gTrainerList;
    private Trainer gTrainer1;
    private Trainer gTrainer2;
    private Trader gTrader;
    private NurseJoy gNurseJoy;
    private ArrayList<Pokemon> gPokemonList;
    private Weather gBattleWeather;
    private Terrain gBattleTerrain;
    private ArrayList<TurnResult> gTurnResults;
    private ArrayList<PkEffectsEnum> gPlayer1Hazards;
    private ArrayList<PkEffectsEnum> gPlayer2Hazards;
    
    public Game(){
        this.gTrainerList = new ArrayList<>();
        this.gTrainer1 = new Trainer();
        this.gTrainerList.add(gTrainer1);
        this.gTrainer2 = new Trainer();
        this.gTrainerList.add(gTrainer2);
        this.gTrader = new Trader();
        this.gNurseJoy = new NurseJoy();
        this.gPokemonList = new ArrayList<>();
        this.gPokemonList = PokemonDataBase.pkPokemonList;
        this.gBattleWeather = new Weather();
        this.gBattleTerrain = new Terrain();
        this.gTurnResults = new ArrayList<>();
        this.gPlayer1Hazards = new ArrayList<>();
        this.gPlayer2Hazards = new ArrayList<>();
    }

    public Game(Trainer gTrainer1, Trainer gTrainer2, Trader gTrader, NurseJoy gNurseJoy, ArrayList<Pokemon> gPokemonList, ArrayList<Trainer> gTrainerList) {
        this.gTrainer1 = gTrainer1;
        this.gTrainer2 = gTrainer2;
        this.gTrader = gTrader;
        this.gNurseJoy = gNurseJoy;
        this.gPokemonList = gPokemonList;
        this.gTrainerList = gTrainerList;
        this.gBattleWeather = new Weather();
        this.gBattleTerrain = new Terrain();
        this.gTurnResults = new ArrayList<>();
        this.gPlayer1Hazards = new ArrayList<>();
        this.gPlayer2Hazards = new ArrayList<>();
    }

    public void battle() {
        // Clear hazards from previous battles
        this.gPlayer1Hazards.clear();
        this.gPlayer2Hazards.clear();
        
        // Get first Pokémon
        Pokemon pk1 = this.gTrainer1.getActivePokemon();
        Pokemon pk2 = this.gTrainer2.getActivePokemon();
        this.gTurnResults.clear();
        this.gTurnResults.add(new TurnResult("BATTLE_START"));

        // --- Main Battle Loop ---
        while (this.gTrainer1.hastAlivePokemon() && this.gTrainer2.hastAlivePokemon()) {
            this.gTurnResults.clear(); 
            pk1 = this.gTrainer1.getActivePokemon();
            pk2 = this.gTrainer2.getActivePokemon();

            // --- PHASE 1: ACTION SELECTION ---
            TurnAction action1 = getPlayerAction(pk1, pk2); 
            TurnAction action2 = getOpponentAction(pk2, pk1); 

            // --- PHASE 2: DETERMINE ACTION ORDER ---
            List<TurnAction> actionOrder = determineActionOrder(action1, action2);
            TurnAction firstAction = actionOrder.get(0);
            TurnAction secondAction = actionOrder.get(1);

            // --- PHASE 3: EXECUTION (ACTION 1) ---
            Pokemon firstTarget = (firstAction.getTrainer() == this.gTrainer1) ? pk2 : pk1;
            ArrayList<PkEffectsEnum> firstTargetHazards = (firstAction.getTrainer() == this.gTrainer1) ? gPlayer2Hazards : gPlayer1Hazards;
            
            if(firstAction instanceof SwitchAction) {
                ArrayList<PkEffectsEnum> hazardsOnSwitch = (firstAction.getTrainer() == this.gTrainer1) ? gPlayer1Hazards : gPlayer2Hazards;
                handleSwitchAction(firstAction, hazardsOnSwitch); 
            } else {
                ArrayList<TurnResult> results1 = firstAction.execute(firstTarget, gBattleWeather, gBattleTerrain, firstTargetHazards);
                gTurnResults.addAll(results1);
            }

            // Check for faint
            if (pk1.getPkHp() <= 0 || pk2.getPkHp() <= 0) {
                if (pk1.getPkHp() <= 0) handleFaint(this.gTrainer1, gPlayer1Hazards);
                if (pk2.getPkHp() <= 0) handleFaint(this.gTrainer2, gPlayer2Hazards);
                if (!this.gTrainer1.hastAlivePokemon() || !this.gTrainer2.hastAlivePokemon()) break;
            }

            // --- PHASE 4: EXECUTION (ACTION 2) ---
            Pokemon secondAttacker = secondAction.getAttacker();
            Pokemon secondTarget = (secondAction.getTrainer() == this.gTrainer1) ? this.gTrainer2.getActivePokemon() : this.gTrainer1.getActivePokemon();
            ArrayList<PkEffectsEnum> secondTargetHazards = (secondAction.getTrainer() == this.gTrainer1) ? gPlayer2Hazards : gPlayer1Hazards;

            if (secondAttacker.getPkHp() > 0 && !secondAttacker.getPkEffects().contains(PkEffectsEnum.Flinch)) {
                if(secondAction instanceof SwitchAction) {
                     ArrayList<PkEffectsEnum> hazardsOnSwitch = (secondAction.getTrainer() == this.gTrainer1) ? gPlayer1Hazards : gPlayer2Hazards;
                    handleSwitchAction(secondAction, hazardsOnSwitch);
                } else {
                    ArrayList<TurnResult> results2 = secondAction.execute(secondTarget, gBattleWeather, gBattleTerrain, secondTargetHazards);
                    gTurnResults.addAll(results2);
                }
            }

            // Check for faint
            if (pk1.getPkHp() <= 0 || pk2.getPkHp() <= 0) {
                 if (pk1.getPkHp() <= 0) handleFaint(this.gTrainer1, gPlayer1Hazards);
                 if (pk2.getPkHp() <= 0) handleFaint(this.gTrainer2, gPlayer2Hazards);
                 if (!this.gTrainer1.hastAlivePokemon() || !this.gTrainer2.hastAlivePokemon()) break;
            }

            // --- PHASE 5: END OF TURN ---
            pk1 = this.gTrainer1.getActivePokemon();
            pk2 = this.gTrainer2.getActivePokemon();
            
            // Apply end-of-turn effects (fastest first)
            Pokemon fasterPk = (pk1.getEffectivePkSpeed() > pk2.getEffectivePkSpeed()) ? pk1 : pk2;
            Pokemon slowerPk = (fasterPk == pk1) ? pk2 : pk1;

            if (fasterPk.getPkHp() > 0)
                gTurnResults.addAll(BattleSystem.applyEndOfTurnEffects(fasterPk, slowerPk, gBattleWeather, gBattleTerrain));
            if (slowerPk.getPkHp() > 0)
                gTurnResults.addAll(BattleSystem.applyEndOfTurnEffects(slowerPk, fasterPk, gBattleWeather, gBattleTerrain));

            gBattleWeather.updateBsWeatherTurnsLeft();
            gBattleTerrain.updateBsTerrainTurnsLeft();
            
            // Check for faint from end-of-turn effects
            if (pk1.getPkHp() <= 0 || pk2.getPkHp() <= 0) {
                 if (pk1.getPkHp() <= 0) handleFaint(this.gTrainer1, gPlayer1Hazards);
                 if (pk2.getPkHp() <= 0) handleFaint(this.gTrainer2, gPlayer2Hazards);
            }
        }

        // --- PHASE 6: END OF BATTLE ---
        if (this.gTrainer1.hastAlivePokemon()) gTurnResults.add(new TurnResult("BATTLE_WON_PLAYER_1"));
        else gTurnResults.add(new TurnResult("BATTLE_WON_PLAYER_2"));
    }

    // Handles the logic for a switch-in
    private void handleSwitchAction(TurnAction switchAction, ArrayList<PkEffectsEnum> hazardsOnField) {
        switchAction.execute(null, gBattleWeather, gBattleTerrain, null); // Perform the switch
        Pokemon newPokemon = switchAction.getTarget(); // The Pokémon entering
        
        // --- HAZARD LOGIC ON ENTRY ---
        if (hazardsOnField.contains(PkEffectsEnum.SetHazardStealthRock)) {
            double effectiveness = TypeChart.getPkEffectiveness(PkTypeEnum.Rock, newPokemon.getPkType1(), newPokemon.getPkType2());
            int damage = (int) (newPokemon.getPkMaxHp() * (effectiveness / 8.0));
            newPokemon.pkTakeDamage(damage);
            gTurnResults.add(new TurnResult("POKEMON_HURT_BY_HAZARD"));
        }
        
        if (hazardsOnField.contains(PkEffectsEnum.SetHazardToxicSpikes)) {
             newPokemon.setPkStatus(PkStatusEnum.Poisoned, -1);
             // (Missing logic for 2 layers -> BadlyPoisoned)
        }
        
        // (Add logic for Spikes, Sticky Web)

        if(newPokemon.getPkHp() <= 0) {
            handleFaint(((SwitchAction)switchAction).getTrainer(), hazardsOnField);
        }
    }
    
    // Handles the logic for a fainted Pokémon
    private void handleFaint(Trainer trainer, ArrayList<PkEffectsEnum> opponentHazards) {
        gTurnResults.add(new TurnResult("POKEMON_FAINTED"));
        if (trainer.hastAlivePokemon()) {
            // Simulating: switch to next available Pokémon
            trainer.settActivePokemon(null); // Force getActivePokemon to find the next alive one
            Pokemon newPk = trainer.getActivePokemon();
            gTurnResults.add(new TurnResult("TRAINER_SWITCHED_POKEMON"));
            // Apply hazard damage to the new Pokémon
            handleSwitchAction(new SwitchAction(trainer, newPk), opponentHazards);
        } else {
            gTurnResults.add(new TurnResult("TRAINER_HAS_NO_POKEMON"));
        }
    }

    // Determines which action executes first
    private List<TurnAction> determineActionOrder(TurnAction action1, TurnAction action2) {
        return List.of(action1, action2).stream()
                .sorted(Comparator
                        .comparing(TurnAction::getPriority, Comparator.reverseOrder()) // 1. By Priority
                        .thenComparing(a -> a.getAttacker().getEffectivePkSpeed(), Comparator.reverseOrder())) // 2. By Speed
                .toList();
    }

    // --- Simulation (Placeholder) Methods ---
    // (These would be replaced by GUI input or AI)

    private TurnAction getPlayerAction(Pokemon attacker, Pokemon opponent) {
        // Simulation: Player always chooses their first move
        Move move = attacker.getPkMove(0);
        return new MoveAction(this.gTrainer1, attacker, opponent, move);
    }

    private TurnAction getOpponentAction(Pokemon attacker, Pokemon player) {
        // Simulation: Opponent always chooses their first move
        Move move = attacker.getPkMove(0);
        return new MoveAction(this.gTrainer2, attacker, player, move);
    }
}