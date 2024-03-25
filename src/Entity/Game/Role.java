/* 
Jia Lin
Last edit: 21/03/2024
*/
/*
Kamiya 
Last edit: 25/3/2024
Added roleforcer for debugging.
*/

package Entity.Game;
import java.util.*;

public class Role {
    private String roleName;
    private String roleDescription;

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
            roleDescription = "At the beginning of each turn flip over a card from the deck and if you guess correct you win the game!";
            System.out.println("ROLE: King");
            
            System.out.println("Description:" + roleDescription);
        }
        if(randomNumber == 2){
            roleName = "Jester";
            roleDescription = "At the beginning of each turn draw a card from the deck and if you all of your cards add upp to below 10 you win the game!!";
            System.out.println("ROLE: Jester");
            System.out.println("Description:" + roleDescription);
        }
        if(randomNumber == 0){
            roleName = "Citizen";
            roleDescription = "Survive";
            System.out.println("ROLE: Citizen");
            System.out.println("description:" + roleDescription);
        }

        
    }

    public String getRoleName(){
        return "" + roleName;
    }

    public String getRoleDescription(){
        return "" + roleDescription;
    }

    public void forceRole(int roleNum){
    
        if(roleNum == 1){
            roleName = "King";
            roleDescription = "At the beginning of each turn flip over a card from the deck and if you guess correct you win the game!";
            System.out.println("ROLE: King");
            
            System.out.println("Description:" + roleDescription);
        }
        if(roleNum == 2){
            roleName = "Jester";
            roleDescription = "At the beginning of each turn draw a card from the deck and if you all of your cards add upp to below 10 you win the game!!";
            System.out.println("ROLE: Jester");
            System.out.println("Description:" + roleDescription);
        }
        if(roleNum == 0){
            roleName = "Citizen";
            roleDescription = "Survive";
            System.out.println("ROLE: Citizen");
            System.out.println("description:" + roleDescription);
        }

     
        

        //for implementing a role ona  particular dummy hand.
        //input 1 to force king, 2 for jester, 0 for citizen 
    }

}
