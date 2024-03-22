public class Player {
    private String playerName;
    private int value; // Player's current balance
    private int currentChips = 200;
    private int currentBet;

    public Player(int initialValue) {
        this.value = initialValue;
    }

    // Method to deduct money from player's balance
    public void deductBalance(int amount) {
        this.value -= amount;
    }

    // Method to add money to player's balance
    public void addBalance(int amount) {
        this.value += amount;
    }

    // Getter method for player's balance
    public int getValue() {
        return this.value;
    }

    public String getName(){
        return this.playerName;
    }

    public void setCurrentChips(int chips) {
        this.currentChips = chips;
    }

    public int getCurrentChips() {
        return this.currentChips;
    }

    public int getCurrentBet() {
        return currentBet;
    }

}


