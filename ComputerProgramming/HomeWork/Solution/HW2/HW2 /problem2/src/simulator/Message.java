package relay.simulator;

import relay.player.Player;
import relay.map.Map;

public class Message {
    private Player currentHuman;
    private Player currentAnimal;
    private Map map;
    private int time;

    private double humanPosition;
    private double animalPosition;


    public Message(Player human, Player animal, Map map, int time){
        this.currentHuman = human;
        this.currentAnimal = animal;
        this.map = map;
        this.time = time;
        humanPosition = currentHuman.getPosition();
        animalPosition = currentAnimal.getPosition();
    }

    public double getDistance(){
    //TODO: Problem 2.1
        double animalPosition = currentAnimal.getPosition();
        double humanPosition = currentHuman.getPosition();
        return animalPosition-humanPosition >=0
                ? animalPosition-humanPosition
                : humanPosition - animalPosition;
    }

    @Override
    public String toString() {
        //TODO: Problem 2.1
        if (animalPosition==map.getMapEnd() && humanPosition<map.getMapEnd()) {
            return time+": [FINISH] Animal team wins";
        } else if (animalPosition<map.getMapEnd() && humanPosition==map.getMapEnd()) {
            return time+": [FINISH] Human team wins";
        } else if (animalPosition==map.getMapEnd() && humanPosition==map.getMapEnd()) {
            return time+": [FINISH] Both teams reach the goal at the same time";
        } else if (animalPosition==0 && humanPosition ==0) {
            return time+": [READY] Human team : "+currentHuman.toString()+" / Animal team : "+ currentAnimal.toString()+" are at 0";
        } else {
            return time+": [RUNNING] Human team : "+currentHuman.toString()+" is at "+humanPosition
                    +" / Animal team : "+currentAnimal.toString()+" is at "+animalPosition;
        }
    }
}
