import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class HandTest {

    private Hand hand;
    private Card card1;
    private Card card2;

    @Before
    public void setUp() {
        hand = new Hand();
        card1 = new Card(5, Suit.HEARTS);
        card2 = new Card(8, Suit.SPADES);
    }

    @Test
    public void testAddCard() {
        hand.addCard(card1);
        assertEquals(1, hand.getNumberOfCards());
        assertTrue(hand.containsCard(card1));
    }

    @Test
    public void testGetCard() {
        hand.addCard(card1);
        assertEquals(card1, hand.getCard(0));
    }

    @Test
    public void testRemoveCardByObject() {
        hand.addCard(card1);
        assertNotNull(hand.removeCard(card1));
        assertEquals(0, hand.getNumberOfCards());
    }

    @Test
    public void testRemoveCardByIndex() {
        hand.addCard(card1);
        assertNotNull(hand.removeCard(0));
        assertEquals(0, hand.getNumberOfCards());
    }

    @Test
    public void testDiscardHand() {
        hand.addCard(card1);
        hand.addCard(card2);
        hand.discardHand();
        assertEquals(0, hand.getNumberOfCards());
        assertTrue(hand.isEmpty());
    }

    @Test
    public void testGetNumberOfCards() {
        assertEquals(0, hand.getNumberOfCards());
        hand.addCard(card1);
        hand.addCard(card2);
        assertEquals(2, hand.getNumberOfCards());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(hand.isEmpty());
        hand.addCard(card1);
        assertFalse(hand
