import java.util.*;

public class Asset {
    private int id;
    private String item;
    private int price;
    private String location;
    private List<Lab> owners;

    public Asset(int id, String item, int price, String location){
        this.id = id;
        this.item = item;
        this.price = price;
        this.location = location;
        this.owners = new ArrayList<>();
    }

    public int getPrice() {
        return this.price;
    }

    public String getItem() {
        return this.item;
    }

    public String getLocation() {
        return this.location;
    }
    public List<Lab> getOwners(){return owners;}
    // TODO sub-problem 1-4

    @Override
    public String toString() {
        return ""+this.id;
    }
}
