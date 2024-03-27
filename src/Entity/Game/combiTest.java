import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Map;

public class totalCombiTest {

    @Test
    public void testOnePair() {
        // Create a player with a hand containing one pair
        Player player = new Player();
        Hand hand = new Hand();
        hand.addCard(new Card(2, Suit.HEARTS)); // Pair: 2
        hand.addCard(new Card(2, Suit.DIAMONDS)); // Pair: 2
        hand.addCard(new Card(5, Suit.CLUBS));
        hand.addCard(new Card(8, Suit.SPADES));
        hand.addCard(new Card(10, Suit.HEARTS));
        player.setpHand(hand);

        // Create a river with community cards
        River river = new River();
        river.addCard(new Card(3, Suit.HEARTS));
        river.addCard(new Card(6, Suit.DIAMONDS));
        river.addCard(new Card(7, Suit.CLUBS));

        // Create a totalCombi object to evaluate the hand
        totalCombi handEvaluator = new totalCombi(player, river);

        // Get the tier of the hand
        Map<Integer, Integer> frequencyMap = handEvaluator.numSameValue();
        int tier = handEvaluator.getTier(frequencyMap);

        // Verify that the hand is correctly identified as having one pair
        assertEquals(1, tier);
    }
}
