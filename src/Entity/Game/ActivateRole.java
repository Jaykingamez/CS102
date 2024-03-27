/*
Jia Lin
Last updated: 21/03/2024
Miya
Last updated: 24/03/2024
*/
package Entity.Game;
import java.util.*;;

public class ActivateRole {

//I thinkt he next int read might throw an exception if a non number is input so im adding an exception
    // place this at the start of each turn after dealing the first card
    public static boolean Result(Deck currentDeck, Player p){
        //rules of the King is that they draw a card at the begining of the turn and try to guess it if the guess it correctly they win the game
        if((p.getPRole().getRoleName()) == "King"){

            int suitGuess;
            //do while loop for repeated asking of player's input if they do not input 0/1/2/3
            do {
                Scanner sc = new Scanner(System.in);
                System.out.println("Guess suit: ");
                System.out.println("0. spades\n1. heart\n2. diamond\n3. clubs");
                try {
                suitGuess = sc.nextInt();
                } catch (InputMismatchException e) {
                System.out.println("Enter a number between 0 and 3.");
                suitGuess = sc.nextInt();
                }
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
                //Ace should be possible to interpret as ONE as well. 
    
                if (suitGuess < 1 || suitGuess > 4) {
                    System.out.println("Invalid input! Please enter 1, 2, 3, or 4.");
                }
            } while (rankGuess < 2 || suitGuess > 13);

            Card newCard = currentDeck.dealCard();
            if(newCard.getSuit() == suitGuess && newCard.getValue() == rankGuess){
                return true;
            }
        }

        //rules of jester: draw a card at the beginning of the turn and if all 3 cards add up to less than 10
        //Im thinking about implementing a cheat code soemhat for the jester to demonstrate each of the hands? Because if not itll be hard to test whether e verhything works for each hand
        //they win the game
        if((p.getPRole().getRoleName()) == "Jester"){
            Card newCard = currentDeck.dealCard();
            int sum = 0;
            List<Card> currentHand = p.getpHand().getHand();
            for(int i = 0; i < 2; i++){
                sum += (currentHand.get(i)).getValue();
            }
            if(newCard.getValue() + sum < 10){
                return true;
            }
        }

        return false;
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
