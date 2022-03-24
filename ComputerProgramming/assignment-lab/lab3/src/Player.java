class Player {
    String userId;
    private int health = 50;

    Player(String userId) {
        this.userId = userId;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    // TODO: problem 1
    public void attack(Player opponent) {
        int attackValue = (int) (1 + Math.random() * 5);
        int currentHealth = opponent.getHealth();
        if (currentHealth < attackValue) {
            opponent.setHealth(0);
        } else {
            opponent.setHealth(currentHealth - attackValue);
        }
    }

    public void heal() {
        int healValue = (int) (1 + Math.random() * 3);
        int currentHealth = this.getHealth();
        this.setHealth(Math.min(currentHealth + healValue, 50));
    }

    public boolean alive() {
        return this.getHealth() > 0;
    }

    public char getTactic() {
        int attackValue = (int) (Math.random() * 10);
        if (attackValue >= 7) {
            return 'h';
        } else {
            return 'a';
        }
    }
}

