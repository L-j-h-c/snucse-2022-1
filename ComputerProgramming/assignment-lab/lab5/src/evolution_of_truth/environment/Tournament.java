package evolution_of_truth.environment;

import evolution_of_truth.agent.*;
import evolution_of_truth.match.MistakeMatch;
import population.Individual;
import population.Population;

public class Tournament {
    Population agentPopulation;

    public Tournament() {
        agentPopulation = new Population();
        for (int i = 0; i < 15; i++) {
            agentPopulation.addIndividual(new Copykitten());
        }
        for (int i = 0; i < 5; i++) {
            agentPopulation.addIndividual(new Devil());
        }
        for (int i = 0; i < 5; i++) {
            agentPopulation.addIndividual(new Copycat());
        }
    }

    private MistakeMatch[] createAllMatches() {
        int n = agentPopulation.size();
        Individual[] agents = agentPopulation.getIndividuals();
        MistakeMatch[] matches = new MistakeMatch[n*(n-1)/2];
        int index = 0;
        for(int i=0; i<n; i++) {
            for(int j=1+i; j<n; j++) {
                matches[index++] = new MistakeMatch((Agent)agents[i], (Agent)agents[j]);
            }
        }
        return matches;
    }

    public void playAllGames(int numRounds) {
        MistakeMatch[] matches = createAllMatches();
        for(int i=0; i<numRounds; i++) {
            for(MistakeMatch match : matches){
                match.playGame();
            }
        }
    }

    public void describe() {
        Individual[] agents = agentPopulation.getIndividuals();
        for(Individual _agent: agents) {
            Agent agent = (Agent)_agent;
            System.out.print(agent.toString() + " / ");
        }
        System.out.println();
    }

    public void evolvePopulation() {
        agentPopulation.toNextGeneration(5);
    }

    public void resetAgents() {
        Individual[] agents = agentPopulation.getIndividuals();
        for(Individual _agent: agents) {
            Agent agent = (Agent)_agent;
            agent.setScore(0);
        }
    }
}
