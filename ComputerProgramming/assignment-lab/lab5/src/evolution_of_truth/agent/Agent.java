package evolution_of_truth.agent;

import evolution_of_truth.Match;

abstract public class Agent {

    private int score;

    public Agent() {
        score = 0;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int newScore) {
        this.score = newScore;
    }

    abstract public int choice();
}
