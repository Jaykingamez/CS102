/*
Miya 
Last edit: 25/3/2024
Si Jie
Last edit: 26/3/2024
*/
package Entity.Game;

public class Card implements Comparable<Card> {

    public final static int SPADES = 0;   
    public final static int HEARTS = 1; 
    public final static int DIAMONDS = 2;
    public final static int CLUBS = 3;
    public final static int JOKER = 4;

    public final static int ACE = 1;      
    public final static int JACK = 11;    
    public final static int QUEEN = 12;   
    public final static int KING = 13;


    private final int suit;                      
    private final int value;

 
    public Card() {
        this(1, JOKER);
    }

    public Card(int theValue, int theSuit) {
        if (theSuit != SPADES && theSuit != HEARTS && theSuit != DIAMONDS && 
                theSuit != CLUBS && theSuit != JOKER)
            throw new IllegalArgumentException("Illegal playing card suit");
        if (theSuit != JOKER && (theValue < 1 || theValue > 13))
            throw new IllegalArgumentException("Illegal playing card value");
        value = theValue;
        suit = theSuit;
    }

    public int getSuit() {
        return suit;
    }


    public int getValue() {
        return value;
    }

    public String getSuitAsString() {
        switch ( suit ) {
        case SPADES:   return "Spades";
        case HEARTS:   return "Hearts";
        case DIAMONDS: return "Diamonds";
        case CLUBS:    return "Clubs";
        default:       return "Joker";
        }
    }


    public String getValueAsString() {
        if (suit == JOKER)
            return "" + value;
        else {
            switch ( value ) {
            case 1:   return "Ace";
            case 2:   return "2";
            case 3:   return "3";
            case 4:   return "4";
            case 5:   return "5";
            case 6:   return "6";
            case 7:   return "7";
            case 8:   return "8";
            case 9:   return "9";
            case 10:  return "10";
            case 11:  return "Jack";
            case 12:  return "Queen";
            default:  return "King";
            }
        }
    }

    public String toString() {
        if (suit == JOKER) {
            if (value == 1)
                return "Joker";
            else
                return "Joker #" + value;
        }
        else
            return getValueAsString() + " of " + getSuitAsString();
    }

    @Override
    public int compareTo(Card otherCard) {
        // First, compare the suits
        if (this.suit < otherCard.suit) {
            return -1;
        } else if (this.suit > otherCard.suit) {
            return 1;
        } else {
            // Suits are the same, now compare the values
            if (this.value < otherCard.value) {
                return -1;
            } else if (this.value > otherCard.value) {
                return 1;
            } else {
                return 0; // The cards are the same
            }
        }
    }


} 
