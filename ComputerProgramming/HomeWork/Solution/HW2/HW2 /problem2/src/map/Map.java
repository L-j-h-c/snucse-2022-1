package relay.map;

public class Map {
    private double waterStart;
    private double waterEnd;
    private double mapEnd;

    public Map(double waterStart, double waterEnd, double mapEnd) {
        this.waterStart = waterStart;
        this.waterEnd = waterEnd;
        this.mapEnd = mapEnd;
    }


    public boolean getOnWater(double position) {
        //TODO: Problem 2.1
        if(position>=waterStart && position<waterEnd) return true;
        else return false;
    }

    //2.1.을 위해 구현
    public double getWaterStart() {
        return this.waterStart;
    }

    public double getWaterEnd() {
        return this.waterEnd;
    }

    public double getMapEnd() {
        return this.mapEnd;
    }
}

