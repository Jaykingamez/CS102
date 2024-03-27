/*
Jialin + Miya 
Last edit: 25/3/2024
denxi:27/3 736pm
*/

package Entity.Game;
import java.util.Scanner;
import GameManager.GameManager;


public class Action {
    Pot thisPot;
    Player thisPlayer;


    public Action(String input, GameManager thisGame, Player thisPlayer){
        this.thisPot = thisGame.getPot();
        this.thisPlayer = thisPlayer;

        if(input == "Call"){
            call();
        }
        if(input == "Raise"){
            raise();
        }
        if(input == "Fold"){
            fold();
        }
    }

    public void call(){
        thisPot.updateBetToContinue(thisPlayer);
    }

    public void raise(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Input amount to raise: ");
        // Validate input
        int raiseAmt;
        while (true) {
            if (sc.hasNextInt()) {
                raiseAmt = sc.nextInt();
                break;
            } else {
                System.out.println("Your input is not a valid number. Please try again.");
                sc.next(); // Clear the invalid input
            }
        }

        thisPot.updateBetToContinue(raiseAmt, thisPlayer);
    }
        
        thisPot.updateBetToContinue(raiseAmt, thisPlayer);
    }

    public void fold(){
        //create setter in Player
        thisPlayer.setActive(false); 
    }

    // public void playAgain(String input) {
    // /*
        
    //     */
    // }

}
