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
    
    public Game(String name1,String name2){
        this.gTrainerList = new ArrayList<>();
        this.gTrainer1 = new Trainer(name1);
        this.gTrainerList.add(gTrainer1);
        this.gTrainer2 = new Trainer(name2);
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
    
    
    public void startBattle() {
    this.gPlayer1Hazards.clear();
    this.gPlayer2Hazards.clear();
    this.gTurnResults.clear();
    this.gTurnResults.add(new TurnResult("BATTLE_START")); 

    // Asigna el primer Pokémon activo (¡importante!)
    this.gTrainer1.getActivePokemon(); 
    this.gTrainer2.getActivePokemon();
    }
    
    
    // En Game.java
    public ArrayList<TurnResult> executeTurn(TurnAction playerAction) {
        this.gTurnResults.clear(); // Limpia los resultados del turno anterior

        Pokemon pk1 = this.gTrainer1.getActivePokemon();
        Pokemon pk2 = this.gTrainer2.getActivePokemon();

        // 1. Obtener acción de la IA (simple por ahora)
        Move aiMove = pk2.getPkMove(0); // IA tonta: siempre usa el primer movimiento
        TurnAction aiAction = new MoveAction(this.gTrainer2, pk2, pk1, aiMove); 

        // 2. Determinar Orden
        List<TurnAction> actionOrder = determineActionOrder(playerAction, aiAction);
        TurnAction firstAction = actionOrder.get(0);
        TurnAction secondAction = actionOrder.get(1);

        // 3. Ejecutar Acción 1
        Pokemon firstTarget = (firstAction.getTrainer() == this.gTrainer1) ? pk2 : pk1;
        ArrayList<PkEffectsEnum> firstTargetHazards = (firstAction.getTrainer() == this.gTrainer1) ? gPlayer2Hazards : gPlayer1Hazards;

        if (firstAction instanceof SwitchAction) {
            ArrayList<PkEffectsEnum> hazardsOnSwitch = (firstAction.getTrainer() == this.gTrainer1) ? gPlayer1Hazards : gPlayer2Hazards;
            handleSwitchAction(firstAction, hazardsOnSwitch); 
        } else {
            ArrayList<TurnResult> results1 = firstAction.execute(firstTarget, gBattleWeather, gBattleTerrain, firstTargetHazards);
            gTurnResults.addAll(results1);
        }

        // 4. Revisar si alguien se debilitó
        if (pk1.getPkHp() <= 0 || pk2.getPkHp() <= 0) {
            if (pk1.getPkHp() <= 0) handleFaint(this.gTrainer1, gPlayer1Hazards);
            if (pk2.getPkHp() <= 0) handleFaint(this.gTrainer2, gPlayer2Hazards);
        }

        // 5. Ejecutar Acción 2 (si el atacante y el objetivo siguen vivos)
        Pokemon secondAttacker = secondAction.getAttacker();
        Pokemon secondTarget = (secondAction.getTrainer() == this.gTrainer1) ? this.gTrainer2.getActivePokemon() : this.gTrainer1.getActivePokemon();
        ArrayList<PkEffectsEnum> secondTargetHazards = (secondAction.getTrainer() == this.gTrainer1) ? gPlayer2Hazards : gPlayer1Hazards;

        // Comprueba que el atacante no se haya debilitado y que el objetivo tampoco
        if (secondAttacker.getPkHp() > 0 && secondTarget.getPkHp() > 0 && !secondAttacker.getPkEffects().contains(PkEffectsEnum.Flinch)) {
            if(secondAction instanceof SwitchAction) {
                ArrayList<PkEffectsEnum> hazardsOnSwitch = (secondAction.getTrainer() == this.gTrainer1) ? gPlayer1Hazards : gPlayer2Hazards;
                handleSwitchAction(secondAction, hazardsOnSwitch);
            } else {
                ArrayList<TurnResult> results2 = secondAction.execute(secondTarget, gBattleWeather, gBattleTerrain, secondTargetHazards);
                gTurnResults.addAll(results2);
            }
        }

        // 6. Revisar de nuevo si alguien se debilitó
        if (pk1.getPkHp() <= 0 && this.gTrainer1.hastAlivePokemon()) handleFaint(this.gTrainer1, gPlayer1Hazards);
        if (pk2.getPkHp() <= 0 && this.gTrainer2.hastAlivePokemon()) handleFaint(this.gTrainer2, gPlayer2Hazards);

        // 7. Aplicar Efectos de Fin de Turno (SOLO si la batalla no ha terminado)
        if (this.gTrainer1.hastAlivePokemon() && this.gTrainer2.hastAlivePokemon()) {
            pk1 = this.gTrainer1.getActivePokemon(); // Re-obtener por si hubo un switch
            pk2 = this.gTrainer2.getActivePokemon();

            Pokemon fasterPk = (pk1.getEffectivePkSpeed() > pk2.getEffectivePkSpeed()) ? pk1 : pk2;
            Pokemon slowerPk = (fasterPk == pk1) ? pk2 : pk1;

            if (fasterPk.getPkHp() > 0)
                gTurnResults.addAll(BattleSystem.applyEndOfTurnEffects(fasterPk, slowerPk, gBattleWeather, gBattleTerrain));
            if (slowerPk.getPkHp() > 0)
                gTurnResults.addAll(BattleSystem.applyEndOfTurnEffects(slowerPk, fasterPk, gBattleWeather, gBattleTerrain));

            gBattleWeather.updateBsWeatherTurnsLeft();
            gBattleTerrain.updateBsTerrainTurnsLeft();

            if (pk1.getPkHp() <= 0) handleFaint(this.gTrainer1, gPlayer1Hazards);
            if (pk2.getPkHp() <= 0) handleFaint(this.gTrainer2, gPlayer2Hazards);
        }

        // 8. Revisar Fin de Batalla
        if (!this.gTrainer1.hastAlivePokemon()) gTurnResults.add(new TurnResult("BATTLE_WON_PLAYER_2"));
        else if (!this.gTrainer2.hastAlivePokemon()) gTurnResults.add(new TurnResult("BATTLE_WON_PLAYER_1"));

        // 9. Devolver la lista de todo lo que pasó
        return this.gTurnResults;
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