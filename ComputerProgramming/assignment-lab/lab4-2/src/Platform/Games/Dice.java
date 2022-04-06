package Platform.Games;

public class Dice {
    public int playGame() {
        int userDice = (int) (Math.random() * 100);
        int opponentDice = (int) (Math.random() * 100);

        int result = Integer.compare(userDice, opponentDice);

        System.out.println(userDice + " " + opponentDice);

        if (result==1) return 1;
        else if (result==-1) return -1;
        else return 0;
    }
}
