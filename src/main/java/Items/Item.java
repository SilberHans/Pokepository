package Items;

public abstract class Item {
    private String iName;
    private int iPrice;
    private int iStock;
    
    public Item(){
        this.iName = "";
        this.iPrice = 0;
        this.iStock = 0;
    }
    public Item(String iName, int iPrice, int iStock) {
        this.iName = iName;
        this.iPrice = iPrice;
        this.iStock = iStock;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }
    public void setiPrice(int iPrice) {
        this.iPrice = iPrice;
    }
    public void setiStock(int iStock) {
        this.iStock = iStock;
    }

    public String getiName() {
        return this.iName;
    }
    public int getiPrice() {
        return this.iPrice;
    }
    public int getiStock() {
        return this.iStock;
    }
    
    @Override
    public String toString(){
        return "\nName:\t" + this.getiName() + "\nPrice:\t" + this.getiPrice() + "\nStock:\t" + this.iStock;
    }
    
    public abstract String iDescription();
}