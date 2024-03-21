package GameManager;

public class PokerPlayer {
    String name = "Yeow";
    int currentChips = 200;
    Hand hand;
    boolean allIn = false; //player in 'all in' state, skip their turns and ignore call/raise resets, include them during showdown
    boolean lastStandAcknowledge = false;   //player acknowledges they are forced to all in during initial bet, skip all turns after
    boolean playedTurn = false;//player has called or checked this turn, resets when raise happens
    boolean finishedRound = false;//player is finished with making inputs via folding or all in
    boolean folded = false;//player in 'folded' state, skip their turns and ignore call/raise resets, ignore them during showdown
    int currentBet = 0;//amount they have bet, added into pot after each phase

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
