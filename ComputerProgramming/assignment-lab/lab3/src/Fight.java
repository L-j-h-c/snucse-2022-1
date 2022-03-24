public class Fight {
    int timeLimit = 100;
    int currRound = 0;
    Player p1;
    Player p2;
    Fight(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
    }
    // TODO: problem2
    public void proceed() {
        System.out.println("Round "+currRound);
        currRound++;
        if(p1.getTactic() == 'a') p1.attack(p2);
        else p1.heal();
        if(p2.alive()){
            if (p2.getTactic() == 'a') p2.attack(p1);
            else p2.heal();
        }
        System.out.printf("%s health: %d\n", p1.userId, p1.getHealth());
        System.out.printf("%s health: %d\n", p2.userId, p2.getHealth());
    }
    public boolean isFinished() {
        if(!p1.alive()) return true;
        else if(!p2.alive()) return true;
        else return currRound == timeLimit+1;
    }
    public Player getWinner() {
        if(!p1.alive()) return p2;
        else if(!p2.alive()) return p1;
        else if(p1.getHealth()==p2.getHealth()) return p2;
        else return p1;
    }
}