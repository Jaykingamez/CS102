import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//Denxi 3.22.24
public class Player { 
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


/*
1. Changed class name to Player with a capital 'P' for consistency with the rest of the methods.
2. Since Player is an object that can be instantiated, "value" in the methods could be changed to "this.value" just for extra clarity. Note that it's not actually wrong to not put it though because if there's
only one of "value" within the method, it automatically takes it as "this.value". Itll be clearer that it's an instance variable and could makethings less confusing as new methods are added.
3. in line 10, the player method should not be void because it doesn't return anything. It's only to instantiate a new player, so just "public Player(int initialValue)..." will be sufficient! :) 

*/
