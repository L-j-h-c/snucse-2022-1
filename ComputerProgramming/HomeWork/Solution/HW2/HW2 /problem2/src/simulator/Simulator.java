package relay.simulator;

import relay.player.Player;
import relay.map.Map;
import java.util.ArrayList;

public class Simulator {
    final static int maxTeamPlayerNum=4;
    private final ArrayList<Player> humanPlayers;
    private final ArrayList<Player> animalPlayers;
    private final Map map;
    private boolean raceFinish = false; // For testbench
    public String[] raceLogForEval = new String[200]; // For evaluation, don't remove

    private Player currentHuman;
    private Player currentAnimal;

    private int animalIndex = 1;
    private int humanIndex = 1;

    public Simulator(ArrayList<Player> humanPlayers, ArrayList<Player> animalPlayers, Map map){
        //TODO: Problem 2.3
	    //for ascending order of position
        this.humanPlayers = humanPlayers;
        this.animalPlayers = animalPlayers;
        this.map = map;

        java.util.Collections.sort(humanPlayers);
        java.util.Collections.sort(animalPlayers);

        currentHuman = humanPlayers.get(0);
        currentAnimal = animalPlayers.get(0);
        currentHuman.setPlayerNum(1);
        currentAnimal.setPlayerNum(1);
        currentHuman.setNextPlayerPosition(humanPlayers.get(1).getPosition());
        currentAnimal.setNextPlayerPosition(animalPlayers.get(1).getPosition());
    }


    public void snapshot() {
        //TODO: Problem 2.3
        if(currentAnimal.getPosition() == animalPlayers.get(animalIndex).getPosition() &&
        animalPlayers.size() > animalIndex && animalIndex < animalPlayers.size()) {
            currentAnimal.passBaton(animalPlayers.get(animalIndex));
            currentAnimal = animalPlayers.get(animalIndex);
            currentAnimal.setPlayerNum(animalIndex+1);
            if(animalIndex<animalPlayers.size()-1) currentAnimal.setNextPlayerPosition(animalPlayers.get(animalIndex+1).getPosition());
            if(animalIndex<animalPlayers.size()-1) animalIndex++;
        }
        if(currentHuman.getPosition() == humanPlayers.get(humanIndex).getPosition() &&
        humanPlayers.size() > humanIndex && humanIndex < humanPlayers.size()) {
            currentHuman.passBaton(humanPlayers.get(humanIndex));
            currentHuman = humanPlayers.get(humanIndex);
            currentHuman.setPlayerNum(humanIndex+1);
            if(humanIndex<humanPlayers.size()-1) currentHuman.setNextPlayerPosition(humanPlayers.get(humanIndex+1).getPosition());
            if(humanIndex<humanPlayers.size()-1) humanIndex++;
        }
    }

    public Message talk(int time) {
        Message msg = new Message(currentHuman, currentAnimal, map, time);
        raceLogForEval[time]=msg.toString();
        return msg;
    }

    public boolean getRaceFinish(){return raceFinish;}

    private boolean checkError() {
        for(Player p : humanPlayers) {
            if(p.toString().contains("animal")) return true;
        }

        for(Player p : animalPlayers) {
            if(p.toString().contains("human")) return true;
        }

        if(humanPlayers.size()==0 || humanPlayers.size() > maxTeamPlayerNum) return true;
        if(animalPlayers.size()==0 || animalPlayers.size() > maxTeamPlayerNum) return true;

        return false;
    }

     public void simulate() {
        //TODO: Problem 2.3
         int time = 0;

         while(!getRaceFinish()) {
             if(checkError() || (time==0&&currentHuman.getPosition()!=0&&currentAnimal.getPosition()!=0)) {
                 raceLogForEval[0] = "[ERROR] Team building error";
                 raceFinish = true;
                 break;
             }
             currentHuman.hear(talk(time));
             if(!currentHuman.getThrowUp()) currentHuman.move();
             if(!currentAnimal.getThrowUp()) currentAnimal.move();
             snapshot();
             time++;
             if(currentAnimal.getPosition()==map.getMapEnd()
                     || currentHuman.getPosition()==map.getMapEnd()) {
                 currentHuman.hear(talk(time));
                 raceFinish=true;
             }
             else if (currentAnimal.getThrowUp() && currentHuman.getThrowUp()) {
                 currentHuman.hear(talk(time));
                 raceFinish=true;
             }
         }
    }
}
