/* EntityTest.DeckTest.java - Chan Si Jie - 3 March 2024
 * Implementation of Unit Testing Deck.java
 */
package EntityTest;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import Entity.*;
import javax.swing.*;

public class DeckTest {
    @Test
    @DisplayName("Test that adding a card to the deck increases the deck size by 1")
    public void testAddCardAddsCardToDeck() {
        Deck deck = new Deck();
        Card card = new Card(Suit.SPADES, Rank.ACE, new ImageIcon("images/as.gif"));
        deck.addCard(card);
        assertEquals(1, deck.getSizeOfDeck());
    }

    @Test
    @DisplayName("Test that an empty deck has a size of 0")
    public void testGetSizeOfDeckEmptyDeck() {
        Deck deck = new Deck();
        assertEquals(0, deck.getSizeOfDeck());
    }

    @Test
    @DisplayName("Test that the number of cards remaining in an empty deck is 0")
    public void testGetNumberOfCardsRemainingEmptyDeck() {
        Deck deck = new Deck();
        assertEquals(0, deck.getNumberOfCardsRemaining());
    }

    @Test
    @DisplayName("Test that the number of cards remaining reflects the number of cards added")
    public void testGetNumberOfCardsRemainingAfterAddingCards() {
        Deck deck = new Deck();
        deck.addCard(new Card(Suit.HEARTS, Rank.KING, new ImageIcon("images/kh.gif")));
        deck.addCard(new Card(Suit.CLUBS, Rank.QUEEN, new ImageIcon("images/qc.gif")));
        assertEquals(2, deck.getNumberOfCardsRemaining());
    }

    @Test
    @DisplayName("Test that dealing from an empty deck returns null")
    public void testDealCardEmptyDeck() {
        Deck deck = new Deck();
        assertNull(deck.dealCard());
    }

    @Test
    @DisplayName("Test that dealing from a deck with multiple cards returns non-null cards and that they are different")
    public void testDealCardMultipleCards() {
        Deck deck = new Deck();
        deck.addCard(new Card(Suit.DIAMONDS, Rank.TEN, new ImageIcon("images/td.gif")));
        deck.addCard(new Card(Suit.SPADES, Rank.JACK, new ImageIcon("images/js.gif")));
        Card firstCard = deck.dealCard();
        Card secondCard = deck.dealCard();
        assertNotNull(firstCard);
        assertNotNull(secondCard);
        assertNotEquals(firstCard, secondCard);
    }

    // Figure out how to test is shuffle is called
//    @Test
//    public void testShuffleChangesCardOrder() {
//        Deck deck = new Deck();
//        deck.addCard(new Card(Suit.HEARTS, Rank.ACE, new ImageIcon("images/ah.gif")));
//        deck.addCard(new Card(Suit.CLUBS, Rank.KING, new ImageIcon("images/kc.gif")));
//        List<Card> initialOrder = deck.;
//        deck.shuffle();
//        List<Card> shuffledOrder = new ArrayList<>(deck);
//        assertNotEquals(initialOrder, shuffledOrder);
//    }

    @Test
    @DisplayName("Test that an empty deck returns true for isEmpty()")
    public void testIsEmptyEmptyDeck() {
        Deck deck = new Deck();
        assertTrue(deck.isEmpty());
    }

    @Test
    @DisplayName("Test that isEmpty() returns true after dealing the only card from a deck")
    public void testIsEmptyAfterDeal() {
        Deck deck = new Deck();
        deck.addCard(new Card(Suit.DIAMONDS, Rank.QUEEN, new ImageIcon("images/qd.gif")));
        deck.dealCard();
        assertTrue(deck.isEmpty());
    }

    @Test
    @DisplayName("Test that isEmpty() returns true after dealing all cards from a full deck")
    public void testIsEmptyAfterFullDeal() {
        Deck deck = new Deck();
        deck.addCard(new Card(Suit.SPADES, Rank.EIGHT, new ImageIcon("images/8s.gif")));
        deck.dealCard();
        assertTrue(deck.isEmpty());
    }

    @Test
    @DisplayName("Test that restoring the deck resets the dealing index so the same card is dealt again")
    public void testRestoreDeckResetsIndex() {
        Deck deck = new Deck();
        Card card = new Card(Suit.CLUBS, Rank.JACK, new ImageIcon("images/jc.gif"));
        deck.addCard(card);
        deck.dealCard();
        deck.restoreDeck();
        assertEquals(deck.dealCard(), card);
    }

}