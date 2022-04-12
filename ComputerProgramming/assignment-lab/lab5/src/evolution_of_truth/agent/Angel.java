package evolution_of_truth.agent;

import evolution_of_truth.match.Match;
import evolution_of_truth.population.Individual;

public class Angel extends Agent {
    public Angel() { super("Angel");}

    @Override
    public Individual clone() {
        return new Angel();
    }

    @Override
    public int choice(int previousOpponentChoice) {
        return Match.COOPERATE;
    }
}
