/*
Jia Lin
Last updated: 21/03/2024
*/
package Entity;
import java.util.*;

public class ActivateRole {


    // place this at the start of each turn after dealing the first card
    public ActivateRole(Deck currentDeck, Player p){
        //rules of the King is that they draw a card at the begining of the turn and try to guess it if the guess it correctly they win the game
        if((p.getPRole().getRoleName()) == "King"){

            int suitGuess;
            //do while loop for repeated asking of player's input if they do not input 0/1/2/3
            do {
                Scanner sc = new Scanner(System.in);
                System.out.println("Guess suit: ");
                System.out.println("0. spades\n1. heart\n2. diamond\n3. clubs");
                suitGuess = sc.nextInt();
    
                if (suitGuess < 0 || suitGuess > 3) {
                    System.out.println("Invalid input! Please enter 0, 1, 2, or 3.");
                }
            } while (suitGuess < 0 || suitGuess > 3);

            

            //player input for guessing card number
            int rankGuess;
            do {
                Scanner sc1 = new Scanner(System.in);
                System.out.println("Guess numerical card number: ");
                System.out.println("Note: Ace = 14, King = 13, Queen = 12, Jack = 11 and the rest of the cards are their own values respectively");
                rankGuess = sc1.nextInt();
    
                if (suitGuess < 1 || suitGuess > 4) {
                    System.out.println("Invalid input! Please enter 1, 2, 3, or 4.");
                }
            } while (rankGuess < 2 || suitGuess > 13);

            Card newCard = currentDeck.dealCard();
            if(newCard.getSuit() == suitGuess && newCard.getValue() == rankGuess){
                //code for game to end with player being the winner
            }
        }

        //rules of jester: draw a card at the beggining of the turn and if all 3 cards add up to less than 10
        //they win the game
        if((p.getPRole().getRoleName()) == "Jester"){
            Card newCard = currentDeck.dealCard();
            int sum = 0;
            List<Card> currentHand = p.getpHand().getHand();
            for(int i = 0; i < 2; i++){
                sum += (currentHand.get(i)).getValue();
            }
            if(newCard.getValue() + sum < 10){
                //code for game to end with player being the winner
            }
        }
    }
}

// converting the option to corresponding suit in string
            // String suitGuess;
            // switch(suitOption) {
            //     case 1:
            //         suitGuess = "diamond";
            //       break;
            //     case 2:
            //         suitGuess = "club";
            //       break;
            //     case 3:
            //         suitGuess = "clover";
            //     default:
            //         suitGuess = "heart";
            //   }
