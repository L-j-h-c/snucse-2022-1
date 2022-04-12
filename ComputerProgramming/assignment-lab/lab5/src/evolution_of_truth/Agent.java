package evolution_of_truth;

public class Agent {

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

    public int choice() {
        return Match.COOPERATE;
    }
}
