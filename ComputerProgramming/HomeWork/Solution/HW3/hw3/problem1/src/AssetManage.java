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
    public List<Asset> findAssetsWithConditions(int minprice, int maxprice, String item, String location){
        // TODO sub-problem 2

        List<Asset> assets = new ArrayList<>();
        // 비어있으면 null
        if(idAsset.isEmpty()) return assets;

        Map<Integer, Asset> tmpAssets = new HashMap<>();

        // 가격 wildCard 아니면 필터링
        Set<Integer> keySet = idAsset.keySet();
        if(!((minprice == -1) && (maxprice == -1))) {
            for (Integer key : keySet) {
                if(idAsset.get(key).getPrice()>=minprice && idAsset.get(key).getPrice()<=maxprice)
                    tmpAssets.put(key, idAsset.get(key));
            }
        } else {
            tmpAssets = idAsset;
        }

        // location으로 filter

        Map<Integer, Asset> tmpSubAssets = new HashMap<>();
        keySet = tmpAssets.keySet();
        if(!item.equals("All")) {
            for (Integer key : keySet) {
                if(tmpAssets.get(key).getItem().equals(item))
                    tmpSubAssets.put(key, tmpAssets.get(key));
            }
        } else {
            tmpSubAssets = tmpAssets;
        }

        Map<Integer, Asset> finalAssets = new HashMap<>();
        keySet = tmpSubAssets.keySet();

        if(!location.equals("All")) {
            for (Integer key : keySet) {
                if(tmpSubAssets.get(key).getLocation().equals(location))
                    finalAssets.put(key, tmpSubAssets.get(key));
            }
        } else {
            finalAssets = tmpSubAssets;
        }

        Object[] arrayKey = finalAssets.keySet().toArray();
        Arrays.sort(arrayKey);

        if(finalAssets.isEmpty()) {
            return assets;
        } else {
            for (Object key : arrayKey) {
                assets.add(finalAssets.get(key));
            }
            return assets;
        }
    }
//   public boolean assetOnShare(Lab sharer, int id) {
//       // TODO sub-problem 4
//       return;
//   }

}
