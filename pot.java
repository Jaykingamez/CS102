import java.util.ArrayList;
import java.util.List;

//Denxi 3.21.24

public class Pot {
    private int totalAmount;
    private List<playermaybe> contributors;

    public Pot() {
        totalAmount = 0;
        contributors = new ArrayList<>();
    }

    // Method to add a player's contribution to the pot
    public void addContribution(playermaybe player, int amount) {
        contributors.add(player);
        totalAmount += amount;
    }

    // Method to distribute the pot to the winners
    public void distributePot(List<playermaybe> winners) {
        int amountPerWinner = totalAmount / winners.size();
        for (playermaybe winner : winners) {
            winner.addBalance(amountPerWinner);
        }
        totalAmount = 0; // Reset pot
        contributors.clear(); // Clear contributors list
    }
      

    // Getter method for total amount in the pot
    public int getTotalAmount() {
        return totalAmount;
    }

    // Getter method for contributors to the pot
    public List<playermaybe> getContributors() {
        return contributors;
    }
}

/*
Comments from miya
1. playermaybe is kind of ambiguous, but if we do use it it should be in lower camel case e.g playerMaybe, frogValue
2. I'm assuming the pot will be divided evenly with rounding to an integer?
3. Please communicate with jialin on how to integrate yalls pot files since u both made them omg sry im sleepy 

*/
