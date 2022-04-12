package evolution_of_truth.agent;

import evolution_of_truth.match.Match;

public class Devil extends Agent {
    public Devil() { super("Devil");}

    @Override
    public int choice(int previousOpponentChoice) {
        return Match.CHEAT;
    }
}
