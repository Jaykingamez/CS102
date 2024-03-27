package GameManager;

import java.util.Scanner;

import Entity.Data.PlayerNameSaver;

public class MainMenu {

    public static String playerName = "Player";
    public static boolean exit = false;

    
    public static void main(String[] args) {
        System.out.println("Poker Game Main Menu");
        System.out.println("Input: ");
        System.out.println("0 to Start Game");
        System.out.println("1 to Create Profile");
        System.out.println("2 to Exit Game");
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (!exit) {
            input = scanner.nextLine();
            if (!input.matches("[012]")){
                System.out.println("Invalid input. Please enter 0, 1, or 2.");
                continue;
            }
            int choice = Integer.parseInt(input);
            switch (choice) {
                case 0:
                    System.out.println("Starting the game...");
                    GameManager gameManager = new GameManager();
                    gameManager.gameStarted(playerName);
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
            }
        }
    }

    public static void createProfile(){
        System.out.println("What is your name?");
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.isEmpty()){
            input = scanner.nextLine();
            if (input.isEmpty()){
                System.out.println("Please type in an appropriate name");
            }
        }
        playerName = input;
        PlayerNameSaver.saveName(input);
    }
}

