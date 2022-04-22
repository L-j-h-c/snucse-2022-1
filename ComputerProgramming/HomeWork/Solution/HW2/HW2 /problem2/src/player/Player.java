package relay.player;

import relay.map.Map;
import relay.simulator.Message;

public abstract class Player implements Comparable<Player>{
    private double position;
    private double velocity;
    private boolean currentPlayer;
    private double nextPlayerPosition;
    private Eyesight eyesight;
    private Map map;
    private int playerNum;
	private final String playerType;

    public Player(double velocity, Map map, String playerType){
        this(0, velocity, map, playerType);
    }
    public Player(double position, double velocity, Map map, String playerType) {
        this.position = position;
        this.velocity = velocity;
        this.map = map;
        currentPlayer = position == 0;
        this.eyesight = new Eyesight(map);
		this.playerType = playerType;
    }

    // 2.1.을 위해 구현
    public double getPosition() {
        return this.position;
    }

    public void setNextPlayerPosition(double position) {
        nextPlayerPosition = position;
		eyesight.setNextPlayerPosition(position);
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public String getPlayerNumString() {
        if(this.getPlayerNum()==1) return "1st";
        else if(this.getPlayerNum()==2) return "2nd";
        else if(this.getPlayerNum()==3) return "3rd";
        else return this.getPlayerNum()+"th";
    }

    public void setPlayerNum(int num) {
        playerNum = num;
    }

    abstract public void move();
    abstract public boolean getThrowUp();
    abstract public void hear(Message message);

    public void passBaton(Player nextPlayer){
        //TODO: Problem 2.2
        currentPlayer = false;
        nextPlayer.currentPlayer = true;
    }

    protected double getMovableDistance(double velocity){
        //TODO: Problem 2.2
        double closer;
        if(eyesight.getDistanceToNextPlayer(this.position) <= eyesight.getDistanceToBoundary(this.position)) {
            if(eyesight.getDistanceToNextPlayer(this.position)<=velocity)
                    closer = eyesight.getDistanceToNextPlayer(this.position);
            else closer = velocity;
        } else {
            if(eyesight.getDistanceToBoundary(this.position)<=velocity)
                closer = eyesight.getDistanceToBoundary(this.position);
            else closer = velocity;
        }
       return closer;
    }

    public double getVelocity() {
        return this.velocity;
    }

    public Eyesight getEyesight() {
        return this.eyesight;
    }

    public Map getMap() {
        return this.map;
    }

    @Override
    public String toString() {
        return toCustomString();
    }
    public abstract String toCustomString();

    public void setPosition(double position) {
        this.position = position;
    }

    @Override
    public int compareTo(Player o) {
        if(this.getPosition()>=o.getPosition()) return 1;
        else return -1;
    }
}
