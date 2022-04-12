import evolution_of_truth.agent.Agent;

import evolution_of_truth.Match;
import evolution_of_truth.agent.Angel;
import evolution_of_truth.agent.Devil;

public class Main {
    public static void main(String args[]) {
        Agent agentA = new Angel();
        Agent agentB = new Devil();

        Match.playGame(agentA, agentB);

        System.out.println(agentA.getScore());
        System.out.println(agentB.getScore());
    }
}
