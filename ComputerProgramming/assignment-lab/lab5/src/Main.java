import evolution_of_truth.agent.Agent;

import evolution_of_truth.Match;
import evolution_of_truth.agent.Copycat;
import evolution_of_truth.agent.Devil;

public class Main {
    public static void main(String args[]) {
        Agent agentA = new Copycat();
        Agent agentB = new Devil();

        Match match = new Match(agentA, agentB);
        match.playGame();
        match.playGame();
        match.playGame();
        match.playGame();
        match.playGame();

        System.out.println(agentA.toString());
        System.out.println(agentB.toString());
    }
}
