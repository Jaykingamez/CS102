package EntityTest;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import Entity.*;
import javax.swing.*;

public class DeckTest {
    @Test
    public void testAddCardAddsCardToDeck() {
        Deck deck = new Deck();
        Card card = new Card(Suit.SPADES, Rank.ACE, new ImageIcon("images/as.gif"));
        deck.addCard(card);
        assertEquals(1, deck.getSizeOfDeck());
    }

    @Test
    public void testGetSizeOfDeckEmptyDeck() {
        Deck deck = new Deck();
        assertEquals(0, deck.getSizeOfDeck());
    }

    @Test
    public void testGetNumberOfCardsRemainingEmptyDeck() {
        Deck deck = new Deck();
        assertEquals(0, deck.getNumberOfCardsRemaining());
    }

    @Test
    public void testGetNumberOfCardsRemainingAfterAddingCards() {
        Deck deck = new Deck();
        deck.addCard(new Card(Suit.HEARTS, Rank.KING, new ImageIcon("images/kh.gif")));
        deck.addCard(new Card(Suit.CLUBS, Rank.QUEEN, new ImageIcon("images/qc.gif")));
        assertEquals(2, deck.getNumberOfCardsRemaining());
    }

    @Test
    public void testDealCardEmptyDeck() {
        Deck deck = new Deck();
        assertNull(deck.dealCard());
    }

    @Test
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

//    @Test
//    public void testShuffle_changesCardOrder() {
//        Deck deck = new Deck();
//        deck.addCard(new Card(Suit.HEARTS, Rank.ACE, new ImageIcon("images/ah.gif")));
//        deck.addCard(new Card(Suit.CLUBS, Rank.KING, new ImageIcon("images/kc.gif")));
//        List<Card> initialOrder = deck.;
//        deck.shuffle();
//        List<Card> shuffledOrder = new ArrayList<>(deck);
//        assertNotEquals(initialOrder, shuffledOrder);
//    }

    @Test
    public void testIsEmptyEmptyDeck() {
        Deck deck = new Deck();
        assertTrue(deck.isEmpty());
    }

    @Test
    public void testIsEmptyAfterDeal() {
        Deck deck = new Deck();
        deck.addCard(new Card(Suit.DIAMONDS, Rank.QUEEN, new ImageIcon("images/qd.gif")));
        deck.dealCard();
        assertTrue(deck.isEmpty());
    }

    @Test
    public void testIsEmptyAfterFullDeal() {
        Deck deck = new Deck();
        deck.addCard(new Card(Suit.SPADES, Rank.EIGHT, new ImageIcon("images/8s.gif")));
        deck.dealCard();
        assertTrue(deck.isEmpty());
    }

    @Test
    public void testRestoreDeckResetsIndex() {
        Deck deck = new Deck();
        Card card = new Card(Suit.CLUBS, Rank.JACK, new ImageIcon("images/jc.gif"));
        deck.addCard(card);
        deck.dealCard();
        deck.restoreDeck();
        assertEquals(deck.dealCard(), card);
    }

}