package Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandEvaluator {
    public HandType evaluateHand(List<Card> hand) {
        if (isRoyalFlush(hand)) {
            return HandType.ROYAL_FLUSH;
        } else if (isStraightFlush(hand)) {
            return HandType.STRAIGHT_FLUSH;
        } else if (isFourOfAKind(hand)) {
            return HandType.FOUR_OF_A_KIND;
        } else if (isFullHouse(hand)) {
            return HandType.FULL_HOUSE;
        } else if (isFlush(hand)) {
            return HandType.FLUSH;
        } else if (isStraight(hand)) {
            return HandType.STRAIGHT;
        } else if (isThreeOfAKind(hand)) {
            return HandType.THREE_OF_A_KIND;
        } else if (isTwoPair(hand)) {
            return HandType.TWO_PAIR;
        } else if (isOnePair(hand)) {
            return HandType.ONE_PAIR;
        } else {
            return HandType.HIGH_CARD;
        }
    }

    private boolean isRoyalFlush(List<Card> hand) {
        return isStraightFlush(hand) && hand.get(0).getValue() == Card.TEN;
    }

    private boolean isStraightFlush(List<Card> hand) {
        return isFlush(hand) && isStraight(hand);
    }

    private boolean isFourOfAKind(List<Card> hand) {
        for (int i = 0; i <= hand.size() - 4; i++) {
            if (hand.get(i).getValue() == hand.get(i + 1).getValue() &&
                hand.get(i).getValue() == hand.get(i + 2).getValue() &&
                hand.get(i).getValue() == hand.get(i + 3).getValue()) {
                return true;
            }
        }
        return false;
    }

    private boolean isFullHouse(List<Card> hand) {
        boolean threeOfAKind = false;
        boolean pair = false;

        for (int i = 0; i < hand.size() - 2; i++) {
            if (hand.get(i).getValue() == hand.get(i + 1).getValue() &&
                hand.get(i).getValue() == hand.get(i + 2).getValue()) {
                threeOfAKind = true;
                break;
            }
        }

        if (threeOfAKind) {
            for (int i = 0; i < hand.size() - 1; i++) {
                if (hand.get(i).getValue() == hand.get(i + 1).getValue()) {
                    pair = true;
                    break;
                }
            }
        }

        return threeOfAKind && pair;
    }

    private boolean isFlush(List<Card> hand) {
        for (int i = 1; i < hand.size(); i++) {
            if (hand.get(i).getSuit() != hand.get(0).getSuit()) {
                return false;
            }
        }
        return true;
    }

    private boolean isStraight(List<Card> hand) {
        List<Integer> values = new ArrayList<>();
        for (Card card : hand) {
            values.add(card.getValue());
        }
        Collections.sort(values);

        for (int i = 1; i < values.size(); i++) {
            if (values.get(i) != values.get(i - 1) + 1) {
                return false;
            }
        }
        return true;
    }

    private boolean isThreeOfAKind(List<Card> hand) {
        for (int i = 0; i <= hand.size() - 3; i++) {
            if (hand.get(i).getValue() == hand.get(i + 1).getValue() &&
                hand.get(i).getValue() == hand.get(i + 2).getValue()) {
                return true;
            }
        }
        return false;
    }

    private boolean isTwoPair(List<Card> hand) {
        int pairCount = 0;
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).getValue() == hand.get(i + 1).getValue()) {
                pairCount++;
                i++; // Skip the next card to avoid counting it twice
            }
        }
        return pairCount >= 2;
    }

    private boolean isOnePair(List<Card> hand) {
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).getValue() == hand.get(i + 1).getValue()) {
                return true;
            }
        }
        return false;
    }
}
