/* Hand.java - John K. Estell - 8 May 2003
 * last modified: 23 February 2004
 */
/*
Miya 
Last edit: 25/3/2024
*/
package Entity.Game;
import java.util.*;

/**
 * Represents the basic functionality of a hand of cards.
 * @author John K. Estell
 * @version 1.0
 */
public class Hand {
    
   private final ArrayList<Card> hand = new ArrayList<>();

   public List<Card> getHand(){
      return hand;
   }

  /**
   * Adds a card to this hand.
   * @param card card to be added to the current hand.
   */
   public void addCard(Card card) {
      hand.add(card);
   }

  /**
   * Obtains the card stored at the specified location in the hand.  Does not
   * remove the card from the hand.
   * @param index position of card to be accessed.
   * @return the card of interest, or the null reference if the index is out of
   * bounds.
   */
   public Card getCard(int index) {
      return hand.get(index);
   }

  /**
   * Removes the specified card from the current hand.
   * @param card the card to be removed.
   * @return the card removed from the hand, or null if the card
   * was not present in the hand.
   */
   public Card removeCard(Card card) {
      int index = hand.indexOf(card);
      return (index < 0) ? null : hand.remove(index);
   }

  /**
   * Removes the card at the specified index from the hand.
   * @param index position of the card to be removed.
   * @return the card removed from the hand, or the null reference if
   * the index is out of bounds.
   */
   public Card removeCard(int index) {
      return hand.remove(index);
   }

  /**
   * Removes all the cards from the hand, leaving an empty hand.
   */
   public void discardHand() {
      hand.clear();
   }

  /**
   * The number of cards held in the hand.
   * @return number of cards currently held in the hand.
   */
   public int getNumberOfCards() {
      return hand.size();
   }

  /**
   * Checks to see if the hand is empty.
   * @return <code>true</code> is the hand is empty.
   */
   public boolean isEmpty() {
      return hand.isEmpty();
   }

  /**
   * Determines whether the hand contains the specified card.
   * @param card the card being searched for in the hand.
   * @return <code>true</code> if the card is present in the hand.
   */
   public boolean containsCard(Card card) {
      return false;
   }

  /**
   * Searches for the first instance of the specified card in the hand.
   * @param card card being searched for.
   * @return position index of card if found, or <code>-1</code> if not found.
   */
   public int findCard(Card card) {
      return hand.indexOf(card);
   }


   /**
    * Returns a description of the hand.
    * @return a list of cards held in the hand.
    */
    public String toString() {
        return hand.toString();
    }

   /**
    * Replaces the specified card with another card.  Only the first
    * instance of the targeted card is replaced.  No action occurs if
    * the targeted card is not present in the hand.
    * @return <code>true</code> if the replacement occurs.
    */
    public boolean replaceCard(Card oldCard, Card replacementCard) {
        int location = findCard(oldCard);
        if (location < 0){
            return false;
        }
        hand.set(location, replacementCard);
        return true;
    }

}
