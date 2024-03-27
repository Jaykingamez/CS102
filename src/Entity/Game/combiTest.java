import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Map;

public class totalCombiTest {

    @Test
    public void testOnePair() {
        Player player = new Player();
        Hand hand = new Hand();
        hand.addCard(new Card(2, Suit.HEARTS));
        hand.addCard(new Card(2, Suit.DIAMONDS));
        hand.addCard(new Card(5, Suit.CLUBS));
        hand.addCard(new Card(8, Suit.SPADES));
        hand.addCard(new Card(10, Suit.HEARTS));
        player.setpHand(hand);

        River river = new River();
        river.addCard(new Card(3, Suit.HEARTS));
        river.addCard(new Card(6, Suit.DIAMONDS));
        river.addCard(new Card(7, Suit.CLUBS));

        totalCombi handEvaluator = new totalCombi(player, river);

        Map<Integer, Integer> frequencyMap = handEvaluator.numSameValue();
        int tier = handEvaluator.getTier(frequencyMap);

        assertEquals(1, tier);
    }

    @Test
    public void testTwoPairs() {
        Player player = new Player();
        Hand hand = new Hand();
        hand.addCard(new Card(2, Suit.HEARTS));
        hand.addCard(new Card(2, Suit.DIAMONDS));
        hand.addCard(new Card(3, Suit.CLUBS));
        hand.addCard(new Card(3, Suit.SPADES));
        hand.addCard(new Card(6, Suit.HEARTS));
        player.setpHand(hand);

        River river = new River();
        river.addCard(new Card(4, Suit.HEARTS));
        river.addCard(new Card(5, Suit.DIAMONDS));
        river.addCard(new Card(7, Suit.CLUBS));

        totalCombi handEvaluator = new totalCombi(player, river);

        Map<Integer, Integer> frequencyMap = handEvaluator.numSameValue();
        int tier = handEvaluator.getTier(frequencyMap);

        assertEquals(2, tier);
    }

    // Add more test cases for other hand combinations

    @Test
    public void testHighCard() {
        Player player1 = new Player();
        Hand hand1 = new Hand();
        hand1.addCard(new Card(2, Suit.HEARTS));
        hand1.addCard(new Card(4, Suit.DIAMONDS));
        hand1.addCard(new Card(6, Suit.CLUBS));
        hand1.addCard(new Card(8, Suit.SPADES));
        hand1.addCard(new Card(10, Suit.HEARTS));
        player1.setpHand(hand1);

        Player player2 = new Player();
        Hand hand2 = new Hand();
        hand2.addCard(new Card(3, Suit.HEARTS));
        hand2.addCard(new Card(5, Suit.DIAMONDS));
        hand2.addCard(new Card(7, Suit.CLUBS));
        hand2.addCard(new Card(9, Suit.SPADES));
        hand2.addCard(new Card(11, Suit.HEARTS));
        player2.setpHand(hand2);

        River river = new River();
        river.addCard(new Card(2, Suit.SPADES));
        river.addCard(new Card(4, Suit.CLUBS));
        river.addCard(new Card(6, Suit.DIAMONDS));

        totalCombi handEvaluator1 = new totalCombi(player1, river);
        totalCombi handEvaluator2 = new totalCombi(player2, river);

        int result = handEvaluator1.compareTo(handEvaluator2);

        assertTrue(result < 0); // Player 1 should have a lower hand
    }
}
