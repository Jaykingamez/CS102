/*
Jialin + Miya 
Last edit: 25/3/2024
*/

package Entity.Game;
import java.util.Scanner;
import Entity.Utility.*;
import Entity.Data.*;
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
        int raiseAmt = sc.nextInt();
        
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
