package evolution_of_truth.agent;

import evolution_of_truth.Match;

public class Devil extends Agent {

    @Override
    public int choice() {
        return Match.CHEAT;
    }
}
