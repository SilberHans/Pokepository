package GameDesing.BattleSystem;

import Pokemons.Logic.Items.Item;
import Pokemons.Logic.Movements.Move;
import Pokemons.Pokemon;
import Utility.Constants.PkStatusEnum;

public class TurnResult {
    private final String messageKey; // "MOVE_HIT_DAMAGE", "POKEMON_FAINTED", etc.
    private String message; // ¡AÑADIDO! El texto legible (ej. "¡Pikachu usó Placaje!")

    // Información contextual para la GUI
    private Pokemon attacker;
    private Pokemon defender;
    private Move moveUsed;
    private Item itemUsed;
    private int damageDealt;
    private boolean targetFainted;
    private boolean attackerFainted;
    private PkStatusEnum statusApplied;
    private boolean moveMissed;

    // --- CONSTRUCTORES MEJORADOS ---

    /**
     * Constructor simple (para mensajes como "BATTLE_START" o "POKEMON_IS_ASLEEP")
     */
    public TurnResult(String messageKey, String message) {
        this.messageKey = messageKey;
        this.message = message;
    }

    /**
     * Constructor para Efectos de Estado (ej. Veneno, Confusión)
     * Asume que el "objetivo" del efecto es el 'defender'.
     */
    public TurnResult(String messageKey, String message, Pokemon target) {
        this(messageKey, message);
        this.defender = target; // El Pokémon que sufre el efecto
    }

    /**
     * Constructor para Movimientos (el más importante)
     */
    public TurnResult(String messageKey, String message, Pokemon attacker, Pokemon defender, Move move, int damage) {
        this(messageKey, message);
        this.attacker = attacker;
        this.defender = defender;
        this.moveUsed = move;
        this.damageDealt = damage;
    }

    /**
     * Constructor para Items
     */
    public TurnResult(String messageKey, String message, Item itemUsed, Pokemon target) {
        this(messageKey, message);
        this.itemUsed = itemUsed;
        this.defender = target;
    }

    // --- GETTERS (¡Para que el BattlePanel pueda leer la información!) ---
    
    public String getMessageKey() { return messageKey; }
    public String getMessage() { return message; } // ¡AÑADIDO!
    public Pokemon getAttacker() { return attacker; }
    public Pokemon getDefender() { return defender; }
    public Move getMoveUsed() { return moveUsed; }
    public Item getItemUsed() { return itemUsed; } // ¡AÑADIDO!
    public int getDamageDealt() { return damageDealt; }
    public boolean didTargetFaint() { return targetFainted; }
    public boolean didAttackerFaint() { return attackerFainted; }
    public boolean didMoveMiss() { return moveMissed; }
    public PkStatusEnum getStatusApplied() { return statusApplied; } // ¡AÑADIDO!

    // --- SETTERS (¡Para que BattleSystem pueda añadir información!) ---

    public void setTargetFainted(boolean targetFainted) {
        this.targetFainted = targetFainted;
    }
    public void setAttackerFainted(boolean attackerFainted) {
        this.attackerFainted = attackerFainted;
    }
    public void setMoveMissed(boolean moveMissed) {
        this.moveMissed = moveMissed;
    }
    public void setStatusApplied(PkStatusEnum statusApplied) {
        this.statusApplied = statusApplied;
    }
    public void setDefender(Pokemon defender) {
        this.defender = defender;
    }
    public void setAttacker(Pokemon attacker) {
        this.attacker = attacker;
    }
}