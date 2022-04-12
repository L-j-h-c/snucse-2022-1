package evolution_of_truth.environment;

import evolution_of_truth.agent.Agent;
import evolution_of_truth.agent.Angel;
import evolution_of_truth.agent.Devil;
import evolution_of_truth.agent.Copycat;
import evolution_of_truth.match.Match;

public class Tournament {
    Agent[] agents;

    public Tournament() {
        agents = new Agent[25];
        for (int i = 0; i < 15; i++) {
            agents[i] = new Angel();
        }
        for (int i = 0; i < 5; i++) {
            agents[15 + i] = new Devil();
        }
        for (int i = 0; i < 5; i++) {
            agents[20 + i] = new Copycat();
        }
    }

    private Match[] createAllMatches() {
        int n = agents.length;
        Match[] matches = new Match[n*(n-1)/2];
        int index = 0;
        for(int i=0; i<n; i++) {
            for(int j=1+i; j<n; j++) {
                matches[index++] = new Match(agents[i], agents[j]);
            }
        }
        return matches;
    }

    public void playAllGames(int numRounds) {
        Match[] matches = createAllMatches();
        for(int i=0; i<numRounds; i++) {
            for(Match match : matches){
                match.playGame();
            }
        }
    }

    public void describe() {
        for(Agent agent : agents) {
            System.out.print(agent.toString() + " / ");
        }
    }
}
