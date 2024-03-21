import java.util.ArrayList;
import java.util.List;

public class pot {
    private int totalAmount;
    private List<playermaybe> contributors;

    public pot() {
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
