package Platform;

import Platform.Games.Dice;
import Platform.Games.RockPaperScissors;

import java.util.Scanner;

public class Platform {
    private int rounds = 1;
    private boolean isSetRounds = false;

    public float run() {
        int sum = 0;
        int total = rounds;
        Dice dice = new Dice();
        RockPaperScissors rsp = new RockPaperScissors();

        Scanner scanner = new Scanner(System.in);
        int gameType = scanner.nextInt();

        for (int i=1; i<=rounds; i++) {
            if (gameType == 0) {
                int status = dice.playGame();
                if(status == 1) sum += 1;
            } else {
                int status = rsp.playGame();
                if(status == 1) sum += 1;
            }
        }

        return (sum / (float)total);
    }

    public void setRounds(int num) {
        if (!isSetRounds) {
            rounds = num;
            isSetRounds = true;
        }
    }
}
