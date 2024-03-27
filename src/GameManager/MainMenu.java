package GameManager;

import java.util.Scanner;

import Entity.Data.PlayerNameSaver;

public class MainMenu {

    public static String playerName = "Player";
    public static boolean exit = false;

    
    public static void main(String[] args) {
        mainMenu();
    }
    
    public static void mainMenu(){
        while (!exit) {
            System.out.println("Poker Game Main Menu");
            System.out.println("Input: ");
            System.out.println("0 to Start Game");
            System.out.println("1 to Create Profile");
            System.out.println("2 to Exit Game");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (!input.matches("[012]")){
                System.out.println("Invalid input. Please enter 0, 1, or 2.");
                continue;
            }
            int choice = Integer.parseInt(input);
            switch (choice) {
                case 0:
                    System.out.println("Configuring the game...");
                    gameConfig();
                    break;
                case 1:
                    System.out.println("Creating a profile...");
                    createProfile();
                    // Add profile creation logic here
                    break;
                case 2:
                    System.out.println("Exiting the game. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Incorrect input");
            }
        }
        System.exit(0);
    }

    public static void createProfile(){
        System.out.println("What is your name?");
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (input.isEmpty() || playerName.equals("Player")){
            input = scanner.nextLine();
            if (input.isEmpty()){
                System.out.println("Please type in an appropriate name");
                continue;
            }
            playerName = input;
        }
        PlayerNameSaver.saveName(input);
    }

    public static void gameConfig(){
        Scanner scanner = new Scanner(System.in);
        int totalNumbers = 0;
        int humanPlayers = 0;
        int botPlayers = 0;
        while (totalNumbers != 4){
            try {
                totalNumbers = 0;
                System.out.println("Configure the number of players in the game, Max Players is 4");
                System.out.println("How many human players are there?");
                humanPlayers = scanner.nextInt();
                System.out.println("How many AI players are there?");
                botPlayers = scanner.nextInt();
                totalNumbers = humanPlayers + botPlayers;
                if (totalNumbers != 4){
                    System.out.println("Insufficient players");
                } else {
                    System.out.println("There are " + humanPlayers + " humanPlayers and " + botPlayers + " botPlayers");
                }
            } catch (Exception e){
                System.out.println("Incorrect input");
            }
        }
        GameManager gameManager = new GameManager();
        gameManager.gameStarted(playerName, humanPlayers, botPlayers);
    }
}

