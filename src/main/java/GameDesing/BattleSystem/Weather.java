package GameDesing.BattleSystem;

import Utility.Constants.BsWeatherEnum;

public class Weather{
    private BsWeatherEnum BsWeather;
    private int BsWeatherTurnsLeft;

    public Weather() {
        this.BsWeather = null;
        this.BsWeatherTurnsLeft = 0;
    }

    public Weather(BsWeatherEnum BsWeather, int BsWeatherTurnsLeft) {
        this.BsWeather = BsWeather;
        this.BsWeatherTurnsLeft = BsWeatherTurnsLeft;
    }

    public void setBsWeather(BsWeatherEnum BsWeather){
        this.BsWeather = BsWeather;
    }
    public void setBsWeatherTurnsLeft(int BsWeatherTurnsLeft){
        this.BsWeatherTurnsLeft = BsWeatherTurnsLeft;
    }

    public BsWeatherEnum getBsWeather(){
        return this.BsWeather;
    }
    public int getBsWeatherTurnsLeft(){
        return this.BsWeatherTurnsLeft;
    }
    
    public void updateBsWeatherTurnsLeft(){
        this.setBsWeatherTurnsLeft(this.getBsWeatherTurnsLeft() - 1);
        if(this.getBsWeatherTurnsLeft() <= 0){
            this.setBsWeather(null);
            this.setBsWeatherTurnsLeft(0);
        }
    }
}