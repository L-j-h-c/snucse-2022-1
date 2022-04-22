package relay.player.animal;

import relay.player.Swimmable;
import relay.map.Map;

public class Turtle extends Animal implements Swimmable {
    private double swimSpeed;

    public Turtle(Map map){this(0, map);}
    public Turtle(double position, Map map) {
        super(position,1,map);
        this.swimSpeed= 2.5;
    }

    @Override
    public void move() {
        //TODO: Problem 2.2
        //Use swim method.

        // 물 위이면 swim한다
        if(this.getMap().getOnWater(this.getPosition())) {
            swim();
        } // 물 위가 아닌 경우
        else {
            this.setPosition(this.getPosition()+this.getMovableDistance(this.getVelocity()));
        }
    }
    public void swim(){
        //TODO: Problem 2.2

        this.setPosition(this.getPosition()+this.getMovableDistance(this.swimSpeed));
    }

    @Override
    public String toCustomString() {
        //TODO: Problem 2.2
        return this.getPlayerNumString()+" animal player, turtle";
    }
    @Override
    public boolean getThrowUp(){return false;}
}
