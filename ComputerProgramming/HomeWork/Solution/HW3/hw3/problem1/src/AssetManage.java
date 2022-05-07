import java.util.*;

public class AssetManage {

    private Map<Integer, Asset> idAsset = new HashMap<>();
    private Map<String, Lab> nameLab = new HashMap<>();

    public Map<Integer, Asset> getIdAsset() {return idAsset;}
    public Map<String, Lab> getNameLab() {return nameLab;}

    public boolean addAsset(int id, String item, int price, String location){
        // TODO sub-problem 1
        if(price<0 || price>100000) return false;
        if(idAsset.containsKey(id) || id<0) return false;

        Asset newAsset = new Asset(id, item, price, location);
        idAsset.put(id, newAsset);

        return true;
    }
    public boolean addLab(String labname){
        // TODO sub-problem 1
        if(nameLab.containsKey(labname)) return false;
        if(labname == null || labname.equals("")) return false;

        Lab newLab = new Lab(labname);
        nameLab.put(labname, newLab);

        return true;
    }
    public Asset findAsset(int id){
        // TODO sub-problem 1
        return idAsset.getOrDefault(id, null);
    }
    public Lab findLab(String labname){
        // TODO sub-problem 1
        return nameLab.getOrDefault(labname, null);
    }
//    public List<Asset> findAssetsWithConditions(int minprice, int maxprice, String item, String location){
//        // TODO sub-problem 2
//        return;
//    }
//    public boolean buyNewAsset(Lab lab, int id) {
//        // TODO sub-problem 3
//        return;
//    }
//    public boolean tradeBtwLabs(Lab buyer, Lab seller, int id){
//        // TODO sub-problem 3
//        return;
//    }
//   public boolean assetOnShare(Lab sharer, int id) {
//       // TODO sub-problem 4
//       return;
//   }

}
