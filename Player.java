import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Player {
    private String playerName;
    private int value; // Player's current balance

    public Player(int initialValue) {
        this.value = initialValue;
    }

    // Method to deduct money from player's balance
    public void deductBalance(int amount) {
        this.value -= amount;
    }

    // Method to add money to player's balance
    public void addBalance(int amount) {
        this.value += amount;
    }

    // Getter method for player's balance
    public int getValue() {
        return this.value;
    }
}


