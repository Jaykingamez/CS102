package cardgame.stuff;
import java.util.ArrayList;
import java.util.List;

public class SimpleComputerPlayer {
    private List<Card> hand;
    private Wealth money;

    public SimpleComputerPlayer(int startingMoney) {
        this.hand = new ArrayList<>();
        this.Wealth.setValue(startingMoney);
    }

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public void playTurn(int currentBet) {
   
        //Check hand
        HandEvaluator evaluator = new HandEvaluator();
        HandType handType = evaluator.evaluateHand(hand);
        int handStrength = handType.ordinal(); // Assigns a numerical value to the hand type

        int callAmount = currentBet;
        int raiseAmount = currentBet * 2; // Simple strategy: raise by doubling the current bet

        if (handStrength >= HandType.ONE_PAIR.ordinal()) {
            // If the hand is one pair or better, call or raise
            if (money > raiseAmount) {
                // Raise if enough money are available
                money -= raiseAmount;
                System.out.println("Computer raises by " + raiseAmount);
            } else {
                // Otherwise, call
                money -= callAmount;
                System.out.println("Computer calls");
            }
        } else {
            // Otherwise, fold
            System.out.println("Computer folds");
            hand.clear(); // Discard hand
        }
    }

    
}
