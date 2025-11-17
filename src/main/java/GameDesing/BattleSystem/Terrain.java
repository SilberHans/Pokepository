package GameDesing.BattleSystem;

import Utility.Constants.BsTerrainEnum;

public class Terrain{
    private BsTerrainEnum BsTerrain;
    private int BsTerrainTurnsLeft;

    public Terrain(){
        this.BsTerrain = null;
        this.BsTerrainTurnsLeft = 0;
    }
    
    public Terrain(BsTerrainEnum BsTerrain, int BsTerrainTurnsLeft) {
        this.BsTerrain = BsTerrain;
        this.BsTerrainTurnsLeft = BsTerrainTurnsLeft;
    }

    public void setBsTerrain(BsTerrainEnum BsTerrain){
        this.BsTerrain = BsTerrain;
    }
    public void setBsTerrainTurnsLeft(int BsTerrainTurnsLeft){
        this.BsTerrainTurnsLeft = BsTerrainTurnsLeft;
    }

    public BsTerrainEnum getBsTerrain(){
        return this.BsTerrain;
    }
    public int getBsTerrainTurnsLeft(){
        return this.BsTerrainTurnsLeft;
    }
    
    public void updateBsTerrainTurnsLeft(){
        this.setBsTerrainTurnsLeft(this.getBsTerrainTurnsLeft() - 1);
        if(this.getBsTerrainTurnsLeft() <= 0){
            this.setBsTerrain(null);
            this.setBsTerrainTurnsLeft(0);
        }
    }
}