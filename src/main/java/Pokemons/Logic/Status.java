package Pokemons.Logic;

import Utility.Constants.PkStatusEnum;

public class Status {
    private PkStatusEnum Status;
    private int StatusCounter;

    public Status(PkStatusEnum pkStatus, int pkStatusCounter){
        this.Status = pkStatus;
        this.StatusCounter = pkStatusCounter;
    }

    public void setStatus(PkStatusEnum pkStatus) {
        this.Status = pkStatus;
    }
    public void setStatusCounter(int pkStatusCounter){
        this.StatusCounter = pkStatusCounter;
    }

    public PkStatusEnum getStatus(){
        return Status;
    }
    public int getStatusCounter(){
        return StatusCounter;
    }
    
    @Override 
    public String toString(){
        return "Status:\t" + this.getStatus() + "Counter:\t" + this.getStatusCounter();
    }
    
    public void updateStatusCounter(){
        if (this.Status == PkStatusEnum.BadlyPoisoned){
            this.setStatusCounter(this.getStatusCounter() + 1);
        } else if (this.Status == PkStatusEnum.Asleep || this.Status == PkStatusEnum.Frozen){
            if(this.StatusCounter > 0){
                this.setStatusCounter(this.getStatusCounter() - 1);
            }
        }
    }
}