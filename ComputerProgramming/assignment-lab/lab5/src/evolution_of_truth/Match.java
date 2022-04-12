package evolution_of_truth;

import evolution_of_truth.agent.Agent;

public class Match {

    public static int CHEAT = 0;
    public static int COOPERATE = 1;
    public static int UNDEFINED = -1;

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

    public static void playGame(Agent agentA, Agent agentB) {
        int aChoice = agentA.choice();
        int bChoice = agentB.choice();

        int aScoreChange = Match.ruleMatrix[aChoice][bChoice][0];
        int bScoreChange = Match.ruleMatrix[aChoice][bChoice][1];

        agentA.setScore(agentA.getScore()+aScoreChange);
        agentB.setScore(agentB.getScore()+bScoreChange);
    }
}
