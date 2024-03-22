import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class player {
    private String playerName;
    private int value; // Player's current balance

    public void Player(int initialValue) {
        this.value = initialValue;
    }

    // Method to deduct money from player's balance
    public void deductBalance(int amount) {
        value -= amount;
    }

    // Method to add money to player's balance
    public void addBalance(int amount) {
        value += amount;
    }

    // Getter method for player's balance
    public int getValue() {
        return value;
    }
}


