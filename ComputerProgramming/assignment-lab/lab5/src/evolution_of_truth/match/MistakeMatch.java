package evolution_of_truth.match;

import evolution_of_truth.agent.Agent;

public class MistakeMatch extends Match {

    public MistakeMatch(Agent agentA, Agent agentB) {
        super(agentA, agentB);
    }

    @Override
    public void playGame() {
        int aChoice = agentA.choice(previousChoiceB, prePreviousChoiceB);
        int bChoice = agentB.choice(previousChoiceA, prePreviousChoiceA);

        aChoice = choiceChanger(aChoice);
        bChoice = choiceChanger(bChoice);

        prePreviousChoiceA = previousChoiceA;
        prePreviousChoiceB = previousChoiceB;

        previousChoiceA = aChoice;
        previousChoiceB = bChoice;


        int aScoreChange = Match.ruleMatrix[aChoice][bChoice][0];
        int bScoreChange = Match.ruleMatrix[aChoice][bChoice][1];

        agentA.setScore(agentA.getScore()+aScoreChange);
        agentB.setScore(agentB.getScore()+bScoreChange);
    }

    private int choiceChanger(int originalChoice) {
        int changer = (int) (Math.random() * 20);
        if (changer >= 19) {
            if (originalChoice == Match.CHEAT) { return Match.COOPERATE; }
            else { return Match.CHEAT; }
        } else {
            return originalChoice;
        }
    }
}
