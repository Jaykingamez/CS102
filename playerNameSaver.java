import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
Denxi 3.21.24 Push

*/

public class playerNameSaver {
        public static void main(String[] args) {
    
        //player enters their name    
        System.out.println("Enter your name: ");
        Scanner sc = new Scanner(System.in);
        String playerName = sc.nextLine();

        // Path to the text file
        String filePath = "player_names.txt";

        if (!isPlayerNameExists(playerName, filePath)) {
            // If it doesn't exist, write the new player name to the text file
            writePlayerName(playerName, filePath);
            System.out.println("New player name '" + playerName + "' added to " + filePath);
        } else {
            // If it exists, do nothing
            System.out.println("Player name '" + playerName + "' already exists in " + filePath);
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
}

/*
Comments 3.22.24 
1. When there's an error reading the file/the file doesn't exist, it's automatically created. I think you could add an additional message that lets the user know that 
a player file has automatically been made, or to just not print out the error message at all since it's not really an issue
2. Lines 26 onward look good! Would it make sense to use the current message e.g "player name john has been added to player_names.txt" as a debug line instead? It'll be for verifying that the 
right name and intended file have been used, but for the game it might be better to say "Player John added!" 
3. I notice most of these methods are static methods, which is okay, but it means it every time we call it we would have to type playerNameSaver.writePlayerName, for example. i think we can discuss
this with the rest of the team so we can standardise it better, because im not really sure whats the best idea either
4. Did you mean "playerExists"? 

*/
