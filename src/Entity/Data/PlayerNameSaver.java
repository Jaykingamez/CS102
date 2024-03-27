/*
Denxi + kamiya
*/
package Entity.Data;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class PlayerNameSaver {
    public static void saveName(String name){
        // Path to the text file
        String filePath = "player_names.txt";

        if (!isPlayerNameExists(name, filePath)) {
            // If it doesn't exist, write the new player name to the text file
            writePlayerName(name, filePath);
            System.out.println("New player name '" + name + "' added to database!");
        } else {
            // If it exists, do nothing
            System.out.println("Player name '" + name + "' already exists!");
        }
    }

    // Method to check if a player name exists in the txt file
    private static boolean isPlayerNameExists(String playerName, String filePath) {
        Set<String> playerNames = readPlayerNames(filePath);
        return playerNames.contains(playerName);
    }

    // Method to read player names from the text file
    private static Set<String> readPlayerNames(String filePath) {
        Set<String> playerNames = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String playerName;
            while ((playerName = reader.readLine()) != null) {
                playerNames.add(playerName);
            }
        } catch (IOException e) {
            System.err.println("Error reading from the file: " + e.getMessage());
        }
        return playerNames;
    }
     // Method to write a player name to the text file
     private static void writePlayerName(String playerName, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(playerName);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
}
