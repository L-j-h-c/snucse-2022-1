import evolution_of_truth.Agent;

import evolution_of_truth.Match;

public class Main {
    public static void main(String args[]) {
        Agent agentA = new Agent();
        Agent agentB = new Agent();

        Match.playGame(agentA, agentB);

        System.out.println(agentA.getScore());
        System.out.println(agentB.getScore());
    }
}
