package relay.player.animal;

import relay.player.Throwable;
import relay.map.Map;

public class Rabbit extends Animal implements Throwable {

    public Rabbit(Map map){this(0, map);}
    public Rabbit(double position, Map map) {
        super(position,3,map);
    }

    @Override
    public void move() {
        //TODO: Problem 2.2
        // 물 위에 있냐? 물 위에 있다면 이미 포기하지 않았다는 가정임
            this.setPosition(this.getPosition()+this.getMovableDistance(this.getVelocity()));
    }

    @Override
    public String toCustomString() {
        //TODO: Problem 2.2
        return this.getPlayerNumString()+" animal player, rabbit";
    }

    public boolean throwUp(double position, Map map){
        //TODO: Problem 2.2
        if(this.getMap().getOnWater(this.getPosition())) {
            return true;
        } else return false;
    }

    @Override
    public boolean getThrowUp(){
        //TODO: Problem 2.2
        return throwUp(this.getPosition(), this.getMap());
    }
}
