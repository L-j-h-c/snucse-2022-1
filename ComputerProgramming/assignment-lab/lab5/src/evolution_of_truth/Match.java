package evolution_of_truth;

import evolution_of_truth.agent.Agent;

public class Match {

    public static int CHEAT = 0;
    public static int COOPERATE = 1;
    public static int UNDEFINED = -1;

    Agent agentA, agentB;
    int previousChoiceA, previousChoiceB;

    private static int ruleMatrix[][][] = {
            {
                    {0, 0},
                    {3, -1}
            },
            {
                    {-1, 3},
                    {2, 2}
            }
    };

    public Match(Agent agentA, Agent agentB) {
        this.agentA = agentA;
        this.agentB = agentB;

        previousChoiceA = Match.UNDEFINED;
        previousChoiceB = Match.UNDEFINED;
    }

    public void playGame() {
        int aChoice = agentA.choice(previousChoiceB);
        int bChoice = agentB.choice(previousChoiceA);

        previousChoiceA = aChoice;
        previousChoiceB = bChoice;

        int aScoreChange = Match.ruleMatrix[aChoice][bChoice][0];
        int bScoreChange = Match.ruleMatrix[aChoice][bChoice][1];

        agentA.setScore(agentA.getScore()+aScoreChange);
        agentB.setScore(agentB.getScore()+bScoreChange);
    }
}
