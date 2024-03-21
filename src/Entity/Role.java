/* 
Jia Lin
Last edit: 21/03/2024
*/

package Entity;
import java.util.*;

public class Role {
    private String roleName;

    public Role(){
        // Create an instance of the Random class
        Random random = new Random();

        // Generate a random number between 0 and 99
        int randomNumber = random.nextInt(100); // Generates numbers in the range [0, 100)

        // Define a probability threshold for getting 0 --- probability of getting 0 is 60%
        int zeroProbabilityThreshold = 30;

        // Check if the random number falls below the probability threshold
        if (randomNumber < zeroProbabilityThreshold) {
            // Set the generated number to 0
            randomNumber = 0;
        }

        if(randomNumber == 1){
            roleName = "King";
            System.out.println("ROLE: King");
            System.out.println("description: At the beginning of each turn, flip over a card from the deck and make a guess: if you guess it correctly you win the game!");
        }
        if(randomNumber == 2){
            roleName = "Jester";
            System.out.println("ROLE: Jester");
            System.out.println("description: At the beginning of each turn, draw a card from the deck and if all of your cards add up to less than 10 you win the game!");
    
        if(randomNumber == 0){
            roleName = "Citizen";
            System.out.println("ROLE: Citizen");
            System.out.println("description: Aim for the best hand and bet wisely to win!"); //added a short line for the normie
        }

        
    }

    public String getRoleName(){
        return this.roleName;
    }

    

}
    /*edited by miya 3.22.24
    */
