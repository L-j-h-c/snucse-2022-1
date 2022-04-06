package Platform.Games;
import java.util.Scanner;

public class RockPaperScissors {
    public int playGame() {
        Scanner scanner = new Scanner(System.in);
        String userRCP = scanner.nextLine();

        String opponentRCP = makeOpponentRCP((int) (1+ Math.random() * 3));

        System.out.println(userRCP + " " + opponentRCP);

        if(userRCP == opponentRCP) {
            return 0;
        } else if (userRCP == "rock") {
            if (opponentRCP == "scissor") return 1;
            else return -1;
        } else if (userRCP == "scissor") {
            if (opponentRCP == "paper") return 1;
            else return -1;
        } else {
            if (opponentRCP == "rock") return 1;
            else return -1;
        }
    }

    private String makeOpponentRCP(int opponentCase) {
        switch (opponentCase) {
            case 1: return "scissor";
            case 2: return "rock";
            case 3: return "paper";
            default: {
                System.out.println("error");
                return "error";
            }
        }
    }
}
