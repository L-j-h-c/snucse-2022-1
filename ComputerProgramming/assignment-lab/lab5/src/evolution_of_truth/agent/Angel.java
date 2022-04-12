package evolution_of_truth.agent;

import evolution_of_truth.Match;

public class Angel extends Agent {
    public Angel() { super("Angel");}

    @Override
    public int choice(int previousOpponentChoice) {
        return Match.COOPERATE;
    }
}
