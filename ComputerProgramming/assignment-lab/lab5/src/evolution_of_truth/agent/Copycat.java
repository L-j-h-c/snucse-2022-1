package evolution_of_truth.agent;

import evolution_of_truth.match.Match;
import population.Individual;

public class Copycat extends Agent {
    public Copycat() { super("Copycat");}

    @Override
    public Individual clone() {
        return new Copycat();
    }

    @Override
    public int choice(int previousOpponentChoice, int prePreviousChoice) {
        if (previousOpponentChoice == Match.UNDEFINED) {
            return Match.COOPERATE;
        } else {
            return previousOpponentChoice;
        }
    }
}
