package GameManager;

public class PokerPlayer {
    String name = "Yeow";
    int currentChips = 200;
    Hand hand;
    boolean allIn = false;
    boolean lastStandAcknowledge = false;
    boolean firstTurn = false;
    boolean finishedTurn = false;
    boolean finishedRound = false;
    int currentBet = 5;

    public PokerPlayer(String name, int currentChips) {
        this.name = name;
        this.currentChips = currentChips;
    }

    public void setCurrentChips(int chips) {
        this.currentChips = chips;
    }

    public int getCurrentChips() {
        return currentChips;
    }

    public String getName() {
        return name;
    }

    public int getCurrentBet() {
        return currentBet;
    }
}
