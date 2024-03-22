/*
 * Jia Lin 
 * Last change: 23/03/2024
 */

import test.Card;

import java.util.*;

public class totalCombi implements Comparable<totalCombi>{
    private ArrayList<Card> allCards;
    private Player player;

    public totalCombi(Player p, int n, River r){
        ArrayList<Card> totalCards = new ArrayList<>();
        Hand cards = p.getpHand();
        for(int i = 0; i < cards.getCardCount(); i++){
            totalCards.add(cards.getCard(i));
        }
        ArrayList<Card> river = r.getRiver();

        for(int i = 0; i < river.size(); i++){
            totalCards.add(river.get(i));
        }

        this.allCards = totalCards;
        this.player = p;
    }

    public ArrayList<Card> getAllCards(){
        return this.allCards;
    }


    public int compareTo(totalCombi anotherPlayer){
        Map<Integer, Integer> ValuefrequencyMap1 = this.numSameValue();
        Map<Integer, Integer> ValuefrequencyMap2 = anotherPlayer.numSameValue();

        // the combinations of player 1 and 2
        // 0 - normal hand, 1 - pair, 2 - doublePair, 3 - three of a kind
        // 4 - straight, 5 - flush, 6 - full house, 7 - four of a kind
        // 8 - straight flush, 9 - royal flush
        int tier1 = this.getTier(ValuefrequencyMap1);
        int tier2 = anotherPlayer.getTier(ValuefrequencyMap2);
        
        if(tier1 != tier2){
            return tier1 - tier2;
        }
        else{
            // if both are normal compare high cards
            if(tier1 == 0 && tier2 == 0){
                int highCard1 = this.getHighCard(ValuefrequencyMap1);
                int highCard2 = anotherPlayer.getHighCard(ValuefrequencyMap2);

                if(highCard1 != highCard2){
                    return highCard1 - highCard2;
                }
            }

            // if both players have 4 of a kind
            if(tier1 == 7 && tier2 == 7){
                return this.highestFour(ValuefrequencyMap1) - anotherPlayer.highestFour(ValuefrequencyMap2);
            }

            //if both players have full house
            if(tier1 == 6 && tier2 == 6){
                // highest 3 there is no way that they are equal as one deck only has 4 copies of same card
                return this.highestThree(ValuefrequencyMap1) - anotherPlayer.highestThree(ValuefrequencyMap2);
                
            }

            //if both players gets triplets compare by their triplet values
            if(tier1 == 3 && tier2 == 3){
                return this.highestThree(ValuefrequencyMap1) - anotherPlayer.highestThree(ValuefrequencyMap2);
            }

            //if both equals double pair or pair
            if((tier1 == 2 && tier2 == 2) || (tier1 == 1 && tier2 == 1)){
                return this.highestTwo(ValuefrequencyMap1) - anotherPlayer.highestTwo(ValuefrequencyMap2);
            }

            //if both player have sequences
            if((tier1 == 4 && tier2 == 4) || (tier1 == 8 && tier2 == 8)){
                return this.getMaxValueInConsecutiveSequence() - anotherPlayer.getMaxValueInConsecutiveSequence();
            }
        
            
            int sum1 = 0;
            int sum2 = 0;
            for(int i = 0; i < this.allCards.size(); i++){
                sum1 += allCards.get(i).getValue();
            }

            for(int i = 0; i < anotherPlayer.getAllCards().size(); i++){
                sum2 += anotherPlayer.getAllCards().get(i).getValue();
            }

            return sum1 - sum2;
            
        }
        
    }

    //to get which combination the player got
    private int getTier(Map<Integer, Integer> ValuefrequencyMap){
        //checking for royal flush
        if(this.hasRoyalSequence() == true && this.numSameSuit().containsValue(5)){
            return 9;
        }

        //checking for straight flush
        Map <Integer, Integer> suitMap = this.numSameSuit();
        if(this.hasConsecutive(ValuefrequencyMap) && suitMap.containsValue(5)){
            return 8;
        }

        //checking for four of a kind
        if(this.isFourOfAKind(ValuefrequencyMap) == true){
            return 7;
        }

        //checking for full house
        if(this.isThreeOfAKind(ValuefrequencyMap) == true && this.numPair(ValuefrequencyMap) > 0){
            return 6;
        }

        //checking for flush
        if(this.numSameSuit().containsValue(5)){
            return 5;
        }

        //checking for straight
        if(this.hasConsecutive(ValuefrequencyMap)){
            return 4;
        }

        //checking for three of a kind
        if(this.isThreeOfAKind(ValuefrequencyMap)){
            return 3;
        }

        if(this.numPair(ValuefrequencyMap) > 1){
            return 2;
        }

        if(this.numPair(ValuefrequencyMap) == 1){
            return 1;
        }
        

        return 0;
    }

    //returning the highest card in the player's hand
    public int getHighCard(Map<Integer, Integer> ValuefrequencyMap){
        int highestKey = 0;
        for(int value : ValuefrequencyMap.keySet()){
            if(value > highestKey){
                highestKey = value;
            }
        }

        return highestKey;
    }


    // returns freq map based on card values
    public Map<Integer, Integer> numSameValue(){
        int[] cardRanks = new int[allCards.size()];
        // convert allCards into an array
        for(int i = 0; i < allCards.size(); i++){
            cardRanks[i] = (allCards.get(i)).getValue();
        }
        Map<Integer, Integer> ValuefrequencyMap = new HashMap<>();

        // Count the frequency of each element in the array (frequency)
        for (int num : cardRanks) {
            // to make ace the highest value
            if(num == 1){
                ValuefrequencyMap.put(14, ValuefrequencyMap.getOrDefault(num, 0) + 1);
            }
            else{
                ValuefrequencyMap.put(num, ValuefrequencyMap.getOrDefault(num, 0) + 1);
            }
        }

        return ValuefrequencyMap;
    }

    //returns the value of the quad card
    private int highestFour(Map<Integer, Integer> map){
        int highest = 0;
        for(int value : map.keySet()){
            if(map.get(value) == 4){
                if(value > highest){
                    highest = value;
                }
            }
        }

        return highest;
    }


    //returns the value of the triplet
    private int highestThree(Map<Integer, Integer> map){
        int highest = 0;
        for(int value : map.keySet()){
            if(map.get(value) == 3){
                if(value > highest){
                    highest = value;
                }
            }
        }

        return highest;
    }

    //returns total values of pairs
    private int highestTwo(Map<Integer, Integer> map){
        int highest = 0;
        int secondHighest = 0;
        for(int value : map.keySet()){
            if(map.get(value) == 2){
                if(value > highest){
                    highest = value;
                }
            }
        }

        for(int value : map.keySet()){
            if(map.get(value) == 2){
                if(value > secondHighest && value != highest){
                    highest = value;
                }
            }
        }

        return highest + secondHighest;
    }

    // get in the highest card in sequence
    private int getMaxValueInConsecutiveSequence() {
        int maxValue = -1; // Initialize to -1 initially
    
        for (int i = 0; i <= allCards.size() - 5; i++) {
            boolean isConsecutive = true;
            int currentValue = allCards.get(i).getValue(); // Initialize current value
    
            for (int j = 0; j < 5 - 1; j++) {
                if (allCards.get(i + j + 1).getValue() != currentValue + j + 1) {
                    isConsecutive = false;
                    break;
                }
            }
    
            if (isConsecutive) {
                maxValue = currentValue + 4; // Update maxValue to include the highest value in the sequence
                break; // No need to continue checking once a consecutive sequence is found
            }
        }
    
        return maxValue;
    }
    


    //check if there is 4 of a kind
    private boolean isFourOfAKind(Map<Integer, Integer> map){
        if(map.containsValue(4)){
            return true;
        }
        return false;
    }

    //check if there is three of a kind
    private boolean isThreeOfAKind(Map<Integer, Integer> map){
        if(map.containsValue(3)){
            return true;
        }
        return false;
    }

    //returns number of pairs
    private int numPair(Map<Integer, Integer> map){
        int pairs = 0;
        if(map.containsValue(2)){
            pairs++;
        }
        return pairs;
    }

    // to check if there is 5 consequetive cards\
    // idk if this is correct T_T
    private boolean hasConsecutive(){
        List<Integer> handValues = new ArrayList<>();
        for(int value : Map.keySet()){
            handValues.add(value);
        }

        int[] valueArr = new int[handValues.size()];

        for(int i = 0; i < handValues.size(); i++){
            valueArr[i] = handValues.get(i);
        }

        //bubble sort
        int n = valueArr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (valueArr[j] > valueArr[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    int temp = valueArr[j];
                    valueArr[j] = valueArr[j + 1];
                    valueArr[j + 1] = temp;
                }
            }
        }

        // check if 5 consecutives
        for (int i = 0; i <= valueArr.length - 5; i++) {
            boolean isConsecutive = true;
            for (int j = 0; j < 5 - 1; j++) {
                if (valueArr[i + j + 1] != valueArr[i] + j + 1) {
                    isConsecutive = false;
                    break;
                }
            }
            if (isConsecutive) {
                return true;
            }
        }
        return false;
    }


    //to check for sequence that is royal flush
    private boolean hasRoyalSequence(){
        int[] royalFLush = {1,11,12,13,10};
        if(allCards.size() < 5){
            return false;
        }

        for(int i = 0; i < allCards.size(); i++){
            boolean containsValue = false;
            for(int x = 0; x < royalFLush.length; x++){
                if(allCards.get(i).getValue() == royalFLush[x]){
                    containsValue = true;
                    break;
                }
            }
            if(containsValue == false){
                return false;
            }
        }

        return true;
    }


    // to get the Hashmap of the number of cards in each suit
    public Map<Integer, Integer> numSameSuit(){
        int[] cardSuits = new int[allCards.size()];
        // convert allCards into an array
        for(int i = 0; i < allCards.size(); i++){
            cardSuits[i] = (allCards.get(i)).getSuit();
        }
        Map<Integer, Integer> SuitfrequencyMap = new HashMap<>();

        // Count the frequency of each element in the array (frequency)
        for (int num : cardSuits) {
            SuitfrequencyMap.put(num, SuitfrequencyMap.getOrDefault(num, 0) + 1);
        }

        return SuitfrequencyMap;

    }
        
    
    // public boolean checkConsecutive(ArrayList<Integer> list, int consecutiveCount) {
    //     for (int i = 0; i <= list.size() - consecutiveCount; i++) {
    //         boolean isConsecutive = true;
    //         for (int j = 0; j < consecutiveCount - 1; j++) {
    //             if (list.get(i + j + 1) != list.get(i) + j + 1) {
    //                 isConsecutive = false;
    //                 break;
    //             }
    //         }
    //         if (isConsecutive) {
    //             return true;
    //         }
    //     }
    //     return false;
    // }

}
