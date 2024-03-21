/* 
Jia Lin
Last edit: 21/03/2024
*/


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
            System.out.println("description: At the begining of each turn flip over a card from the deck and if you guess correct you win the game!");
        }
        if(randomNumber == 2){
            roleName = "Jester";
            System.out.println("ROLE: Jester");
            System.out.println("description: At the begining of each turn draw a card from the deck and if you all of your cards add upp to below 10 you win the game!!");
        }
        if(randomNumber == 0){
            roleName = "Citizen";
            System.out.println("ROLE: Citizen");
        }

        
    }

    public String getRoleName(){
        return this.roleName;
    }

    

}
