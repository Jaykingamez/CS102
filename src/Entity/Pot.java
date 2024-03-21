import java.util.Scanner;

public class Action {
    Pot thisPot;
    Player thisPlayer;


    public Action(String input, Game thisGame, Player thisPlayer){
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
        thisPlayer.setActive() = false; 
    }

}
