package Pokemons.Logic;

public class PkStatus {
    private PkStatusEnum pkStatus;
    private int pkStatusTurnsLeft;

    public PkStatus(PkStatusEnum pkStatus, int pkStatusTurnsLeft) {
        this.pkStatus = pkStatus;
        this.pkStatusTurnsLeft = pkStatusTurnsLeft;
    }

    public void setPkStatus(PkStatusEnum pkStatus) {
        this.pkStatus = pkStatus;
    }
    public void setPkStatusTurnsLeft(int pkStatusTurnsLeft) {
        this.pkStatusTurnsLeft = pkStatusTurnsLeft;
    }

    public PkStatusEnum getPkStatus() {
        return pkStatus;
    }
    public int getPkStatusTurnsLeft() {
        return pkStatusTurnsLeft;
    }
    
    @Override 
    public String toString(){
        return "Status:\t" + this.getPkStatus() + "Turns Left:\t" + this.getPkStatusTurnsLeft();
    }
    
    public void decreasePkStatusTurnsLeft(){
        if(this.pkStatusTurnsLeft > 0){
            this.setPkStatusTurnsLeft(pkStatusTurnsLeft--);
        }
    }
}