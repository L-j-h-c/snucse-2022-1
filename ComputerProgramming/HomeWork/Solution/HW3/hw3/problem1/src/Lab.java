import java.util.*;

public class Lab {
    private String labname;
    private int balance;
    private Map<Integer, Asset> assetInventory;

    public Lab(String labname){
        // initial balance is 100,000 for each lab
        this.balance = 100000;
        this.labname = labname;
        assetInventory = new HashMap<>();
    }
    public int getBalance(){return balance;}

    public void buyAsset(Integer id, Asset newAsset) {
        this.balance -= newAsset.getPrice();
        assetInventory.put(id, newAsset);
    }

    public void sellAsset(int id, Asset asset) {
        this.balance += asset.getPrice();
        assetInventory.remove(id);
    }

    // TODO sub-problem 1-4

    @Override
    public String toString() {
        return this.labname;
    }
}
