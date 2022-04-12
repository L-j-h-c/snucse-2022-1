package evolution_of_truth.agent;

import evolution_of_truth.match.Match;
import population.Individual;

public class Copykitten extends Agent {
    public Copykitten() { super("Copykitten");}

    @Override
    public Individual clone() {
        return new Copykitten();
    }

    @Override
    public int choice(int previousOpponentChoice, int prePreviousChoice) {
        if (previousOpponentChoice == Match.CHEAT && prePreviousChoice == Match.CHEAT) {
            return Match.CHEAT;
        } else {
            return Match.COOPERATE;
        }
    }
}
