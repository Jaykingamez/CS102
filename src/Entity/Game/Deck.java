/* Deck.java - John K. Estell - 8 May 2003
 * last modified: 23 February 2004
 * Implementation of a deck of playing cards.  Uses the Card class.
 */
/*
Miya Last edit 3/26/24
Idk how i missed this but i didnt implement "shuffle" properly. Hopefully it shuffles automatically upon creation as it was intended to be
*/
package Entity.Game;

import java.util.*;

/**
 * Represents a deck of playing cards.  In order to have maximum flexibility,
 * this class does not implement a standard deck of playing cards; it only
 * provides the functionality of a deck of cards.  The client programmer must
 * instantiate a Deck object, then populate it with the set of playing cards
 * appropriate for the card game being implemented.  This allows for proper
 * implementation of card games such as pinochle (a 48-card deck containing
 * only aces, nines, tens, jacks, queens, and kings in all four suits, with
 * each card present twice in the deck) or casino-style blackjack (where six
 * standard decks are used for a game).  
 * @author John K. Estell
 * @version 1.0
 */
public class Deck {
   private final ArrayList<Card> deck;
   private int index;

  /**
   * Creates an empty deck of cards.
   */
   public Deck() {
      deck = new ArrayList<>();
      int[] suits = {Card.SPADES, Card.HEARTS, Card.DIAMONDS, Card.CLUBS};
      for (int suit : suits) {
         for (int value = 1; value <= 13; value++) {
            deck.add(new Card(value, suit));
         }
      }
      index = 0;
      shuffle(); //Shuffles deck immediately after creation.
    }
   /* Creates a deck with no Jokers. 

*/
      

  /**
   * Adds a card to the deck.
   * @param card card to be added to the deck.
   */
   public void addCard(Card card) {
      deck.add(card);
   }

  /**
   * The size of a deck of cards.
   * @return the number of cards present in the full deck.
   */
   public int getSizeOfDeck() {
      return deck.size();
   }

  /**
   * The number of cards left in the deck.
   * @return the number of cards left to be dealt from the deck.
   */
   public int getNumberOfCardsRemaining() {
      return deck.size() - index;
   }

  /**
   * Deal one card from the deck.
   * @return a card from the deck, or the null reference if there
   * are no cards left in the deck.
   */
   public Card dealCard() {
       return (index >= deck.size()) ? null : deck.get(index++);
   }

  /**
   * Shuffles the cards present in the deck.
   */
   public void shuffle() {
      Collections.shuffle(deck);
   }

  /**
   * Looks for an empty deck.
   * @return <code>true</code> if there are no cards left to be dealt from the deck.
   */
   public boolean isEmpty() {
       return index >= deck.size();
   }

  /**
   * Restores the deck to "full deck" status.
   */
   public void restoreDeck() {
      index = 0;
   }
}
