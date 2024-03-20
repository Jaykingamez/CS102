package cardgame.stuff;

import java.util.Collections;
import java.util.List;

public class TexasHoldemHand extends Hand {

    public TexasHoldemHand(List<Card> cards) {
        super();
        for (Card card : cards) {
            addCard(card);
        }
    }

    public HandType determineHandType() {
        if (isRoyalFlush()) {
            return HandType.ROYAL_FLUSH;
        } else if (isStraightFlush()) {
            return HandType.STRAIGHT_FLUSH;
        } else if (isFourOfAKind()) {
            return HandType.FOUR_OF_A_KIND;
        } else if (isFullHouse()) {
            return HandType.FULL_HOUSE;
        } else if (isFlush()) {
            return HandType.FLUSH;
        } else if (isStraight()) {
            return HandType.STRAIGHT;
        } else if (isThreeOfAKind()) {
            return HandType.THREE_OF_A_KIND;
        } else if (isTwoPair()) {
            return HandType.TWO_PAIR;
        } else if (isOnePair()) {
            return HandType.ONE_PAIR;
        } else {
            return HandType.HIGH_CARD;
        }
    }

    private boolean isRoyalFlush() {
        // Check for Royal Flush: A, K, Q, J, 10 of the same suit
        return isStraightFlush() && getCard(0).getValue() == Card.ACE;
    }

    private boolean isStraightFlush() {
        // Check for Straight Flush: Five consecutive cards of the same suit
        return isFlush() && isStraight();
    }

    private boolean isFourOfAKind() {
        // Check for Four of a Kind: Four cards of the same rank
        for (int i = 0; i <= getCardCount() - 4; i++) {
            if (getCard(i).getValue() == getCard(i + 1).getValue() &&
                    getCard(i).getValue() == getCard(i + 2).getValue() &&
                    getCard(i).getValue() == getCard(i + 3).getValue()) {
                return true;
            }
        }
        return false;
    }

    private boolean isFullHouse() {
        // Check for Full House: Three of a kind and a pair
        return isThreeOfAKind() && isOnePair();
    }

    private boolean isFlush() {
        // Check for Flush: All cards of the same suit
        for (int i = 1; i < getCardCount(); i++) {
            if (getCard(i).getSuit() != getCard(0).getSuit()) {
                return false;
            }
        }
        return true;
    }

    private boolean isStraight() {
        // Check for Straight: Five consecutive cards (not necessarily of the same suit)
        sortByValue();
        for (int i = 0; i < getCardCount() - 1; i++) {
            if (getCard(i + 1).getValue() - getCard(i).getValue() != 1) {
                return false;
            }
        }
        return true;
    }

    private boolean isThreeOfAKind() {
        // Check for Three of a Kind: Three cards of the same rank
        for (int i = 0; i <= getCardCount() - 3; i++) {
            if (getCard(i).getValue() == getCard(i + 1).getValue() &&
                    getCard(i).getValue() == getCard(i + 2).getValue()) {
                return true;
            }
        }
        return false;
    }

    private boolean isTwoPair() {
        // Check for Two Pair: Two pairs of cards with the same rank
        int pairs = 0;
        for (int i = 0; i < getCardCount() - 1; i++) {
            if (getCard(i).getValue() == getCard(i + 1).getValue()) {
                pairs++;
                i++; // Skip the next card to avoid counting it twice
            }
        }
        return pairs == 2;
    }

    private boolean isOnePair() {
        // Check for One Pair: Two cards with the same rank
        for (int i = 0; i < getCardCount() - 1; i++) {
            if (getCard(i).getValue() == getCard(i + 1).getValue()) {
                return true;
            }
        }
        return false;
    }
}
    // Define other methods as needed

    // public static void main(String[] args) {
    //     // Example usage
    //     List<Card> cards = List.of(
    //             new Card(Card.ACE, Card.HEARTS),
    //             new Card(Card.KING, Card.HEARTS),
    //             new Card(Card.QUEEN, Card.HEARTS),
    //             new Card(Card.JACK, Card.HEARTS),
    //             new Card(Card.TEN, Card.HEARTS)
    //     );

    //     TexasHoldemHand hand = new TexasHoldem