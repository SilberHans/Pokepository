package GameDesing.BattleSystem;

import Pokemons.Logic.Items.Item;
import Pokemons.Logic.Movements.Move;
import Utility.Constants.PkStatusEnum;

public class TurnResult {
    private final String messageKey;
    private final int damageDealt;
    private final boolean targetFainted;
    private final boolean attackerFainted;
    private final PkStatusEnum statusApplied;
    private final Move moveUsed;
    private final Item itemUsed;
    private final boolean moveMissed;

    public TurnResult(String messageKey) {
        this.messageKey = messageKey;
        this.damageDealt = 0;
        this.targetFainted = false;
        this.attackerFainted = false;
        this.statusApplied = null;
        this.moveUsed = null;
        this.itemUsed = null;
        this.moveMissed = false;
    }
    public TurnResult(String messageKey, Move moveUsed, int damageDealt, boolean targetFainted, boolean attackerFainted, PkStatusEnum statusApplied, boolean moveMissed) {
        this.messageKey = messageKey;
        this.moveUsed = moveUsed;
        this.damageDealt = damageDealt;
        this.targetFainted = targetFainted;
        this.attackerFainted = attackerFainted;
        this.statusApplied = statusApplied;
        this.moveMissed = moveMissed;
        this.itemUsed = null; 
    }
    public TurnResult(String messageKey, Item itemUsed, boolean targetFainted) {
        this.messageKey = messageKey;
        this.itemUsed = itemUsed;
        this.targetFainted = targetFainted;
        this.damageDealt = 0;
        this.attackerFainted = false;
        this.statusApplied = null;
        this.moveUsed = null;
        this.moveMissed = false;
    }

    public String getMessageKey() {
        return messageKey;
    }
    public int getDamageDealt() {
        return damageDealt;
    }
    public boolean didTargetFaint() {
        return targetFainted;
    }
    public boolean didAttackerFaint() {
        return attackerFainted;
    }  
    public PkStatusEnum getStatusApplied() {
        return statusApplied;
    }
    public Move getMoveUsed() {
        return moveUsed;
    }
    public Item getItemUsed() {
        return itemUsed;
    }
    public boolean didMoveMiss() {
        return moveMissed;
    }
}