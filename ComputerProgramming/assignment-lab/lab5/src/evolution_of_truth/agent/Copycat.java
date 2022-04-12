package evolution_of_truth.agent;

import evolution_of_truth.match.Match;

public class Copycat extends Agent {
    public Copycat() { super("Copycat");}

    @Override
    public int choice(int previousOpponentChice) {
        if (previousOpponentChice == Match.UNDEFINED) {
            return Match.COOPERATE;
        } else {
            return previousOpponentChice;
        }
    }
}
