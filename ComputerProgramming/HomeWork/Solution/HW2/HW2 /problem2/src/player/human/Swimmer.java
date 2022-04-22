package relay.player.human;

import relay.player.Swimmable;
import relay.map.Map;
import relay.simulator.Message;

public class Swimmer extends Human implements Swimmable {
    private double swimSpeed;

    public Swimmer(Map map){this(0, map);}
    public Swimmer(double position, Map map) {
        super(position,1.5,map);
        this.swimSpeed= 2;
    }

    private boolean warmUp = true;

    public void hear(Message message) {
        //TODO: Problem 2.2
        // 땅 위에 있을 때 거리가 2보다 작으면
        if(!this.getMap().getOnWater(this.getPosition())) {
            if(message.getDistance()<2) {
                if(this.getEyesight().canSeeNextPlayer(this.getPosition()) || this.getEyesight().canSeeMapEnd(this.getPosition())) {
                    this.setVelocity(2);
                } else this.setVelocity(1.5);
            } else setVelocity(1.5);
        }
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

        // 시작점인 경우 웜업한다.
        if(this.getMap().getWaterStart()==this.getPosition() && warmUp) {
            warmUp = false;
            return;
        }
        // 시작점 아닌 경우 수영한다.
        else {
            this.setPosition(this.getPosition()+this.getMovableDistance(this.swimSpeed));
        }
    }

    @Override
    public String toCustomString() {
        //TODO: Problem 2.2
        return this.getPlayerNumString()+" human player, swimmer";
    }

    @Override
    public boolean getThrowUp(){return false;}

}
