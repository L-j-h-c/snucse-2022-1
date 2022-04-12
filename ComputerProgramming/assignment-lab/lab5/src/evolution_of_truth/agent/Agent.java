package evolution_of_truth.agent;

abstract public class Agent {

    private int score;
    private String name;

    public Agent() {
        score = 0;
    }

    protected Agent(String name) { this.name = name; }

    public int getScore() {
        return this.score;
    }

    public void setScore(int newScore) {
        this.score = newScore;
    }

    abstract public int choice(int previousOpponentChoice);

    @Override
    public String toString() {
        return name + ": " + getScore();
    }
}
