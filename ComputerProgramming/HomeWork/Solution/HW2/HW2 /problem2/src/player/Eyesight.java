package relay.player;

import relay.map.Map;

public class Eyesight {
    private static final double range = 3;
    private double nextPlayerPosition;	
    private Map map;

    public Eyesight(Map map){
            this.map = map;
        }
	public void setNextPlayerPosition(double position){
        nextPlayerPosition=position;
    }
    public double getRange(){return range;}

    public double getDistanceToBoundary(double playerPosition){
        //TODO: Problem 2.1
        if(map.getOnWater(playerPosition)) {
            if(map.getWaterEnd()-playerPosition<3) return map.getWaterEnd()-playerPosition;
            else return range;
        } else {
            if(map.getWaterStart()-playerPosition<3 && map.getWaterStart()-playerPosition > 0) return map.getWaterStart()-playerPosition;
            else if(map.getMapEnd()-playerPosition<3 && map.getMapEnd()-playerPosition > 0) return map.getMapEnd()-playerPosition;
            else return range;
        }
    }
    public double getDistanceToNextPlayer(double playerPosition){
        //TODO: Problem 2.1
        double distance = nextPlayerPosition - playerPosition;
        if(distance < range && distance > 0) return distance;
        else return range;
    }

    public boolean canSeeNextPlayer(double playerPosition) {
        double distance = nextPlayerPosition - playerPosition;
        if(distance < range && distance > 0) return true;
        else return false;
    }

    public boolean canSeeMapEnd(double playerPosition) {
        double distance = map.getMapEnd() - playerPosition;
        if(distance < range && distance > 0) return true;
        else return false;
    }
}
