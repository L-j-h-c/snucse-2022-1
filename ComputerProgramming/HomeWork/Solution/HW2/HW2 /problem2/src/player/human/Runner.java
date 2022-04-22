package relay.player.human;

import relay.player.Throwable;
import relay.map.Map;
import relay.simulator.Message;

public class Runner extends Human implements Throwable{

    public Runner(Map map){this(0, map);}
    public Runner(double position, Map map) {
        super(position, 2, map);
    }

    @Override
    public void hear(Message message) {
        //TODO: Problem 2.2
        if(!this.getMap().getOnWater(this.getPosition())) {
            if(message.getDistance()<2) {
                if(this.getEyesight().canSeeNextPlayer(this.getPosition()) || this.getEyesight().canSeeMapEnd(this.getPosition())) {
                    this.setVelocity(2.5);
                } else this.setVelocity(2);
            } else setVelocity(2);
        }
    }

    @Override
    public void move() {
        //TODO: Problem 2.2

        // 물 위에 있냐? 물 위에 있다면 이미 포기하지 않았다는 가정임
        if(this.getMap().getOnWater(this.getPosition())) {
            this.setPosition(this.getPosition()+this.getMovableDistance(1.0));
        } // 물 위가 아닌 경우
        else {
            this.setPosition(this.getPosition()+this.getMovableDistance(this.getVelocity()));
        }
    }

    @Override
    public String toCustomString() {
        //TODO: Problem 2.2
        return this.getPlayerNumString()+" human player, runner";
    }

    public boolean throwUp(double position, Map map){
        //TODO: Problem 2.2
        if(this.getMap().getOnWater(this.getPosition())) {
            if(this.getEyesight().canSeeNextPlayer(this.getPosition())
                    || this.getEyesight().canSeeMapEnd(this.getPosition())) {
                return false;
            } else {
                return true;
            }
        } else return false;
    }

    @Override
    public boolean getThrowUp(){
        //TODO: Problem 2.2
        return throwUp(this.getPosition(), this.getMap());
    }
}

