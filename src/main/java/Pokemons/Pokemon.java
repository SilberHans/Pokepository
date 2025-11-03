package Pokemons;

public class Pokemon {
    private int pkLevel;
    private String pkNickName;
    private int pkMaxHp;
    private int pkHp;
    private int pkSpeed;
    private int pkPrice;
    
    
    public Pokemon(){
        
    }
    public Pokemon(int pkLevel){
        
    }

    public int getPkLevel() {
        return pkLevel;
    }

    public void setPkLevel(int pkLevel) {
        this.pkLevel = pkLevel;
    }

    public String getPkNickName() {
        return pkNickName;
    }

    public void setPkNickName(String pkNickName) {
        this.pkNickName = pkNickName;
    }

    public int getPkMaxHp() {
        return pkMaxHp;
    }

    public void setPkMaxHp(int pkMaxHp) {
        this.pkMaxHp = pkMaxHp;
    }

    public int getPkHp() {
        return pkHp;
    }

    public void setPkHp(int pkHp) {
        this.pkHp = pkHp;
    }

    public int getPkSpeed() {
        return pkSpeed;
    }

    public void setPkSpeed(int pkSpeed) {
        this.pkSpeed = pkSpeed;
    }

    public int getPkPrice() {
        return pkPrice;
    }

    public void setPkPrice(int pkPrice) {
        this.pkPrice = pkPrice;
    }
    
    @Override
    public String toString(){
        String str="";
        return str;
    }
}