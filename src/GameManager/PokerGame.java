/*
Kong Khai
Last updated: 22/03/2024
Jia Lin
Last updated: 25/03/2024
Kamiya: 
Last updated: 26/03/2024
Si Jie:
Last updated: 27/03/2024
*/
package GameManager;
import java.util.*;

import Entity.Game.*;


public class PokerGame {
    Deck deck;
    ArrayList<Player> players; //reference to players to save data after game
    Scanner scan = new Scanner(System.in);
    Pot pot;
    River river; //JL added 24/03/2024
    int initialBet = 10;             // game's initial bet 
    //private int firstPlayerIndex = 0; // first player at start of a new round
    int currentIndex = 0; //current player's index
    Player currentPlayer;
    String oneAction = "check";     // current available action for inputting 1
    String[] phase = {"preFlop", "Flop", "Turn", "River", "Showdown"}; //poker game phases
    int currentPhase = 0;    //poker game phase index
    int numOfFolds = 0;     //number of players folded
    //boolean bankrupted = false; //if player has no more money in their balance- they can't continue playing.
    String displayCards = "";

    public PokerGame(ArrayList<Player> players) {
        this.players = players;
    }

    public Pot getPot(){
        return pot;
    }

    public void startGame() {
        Round round = new Round(players, this);
        round.startRound();
    }

    
    public void postGame(Map<String, Integer> results, boolean bankruptcy) {
        //handle post game stuff - new game with same ppl or leave, saving      
        System.out.println("Game Over!");
        for (String player : results.keySet()) {
            System.out.println(player + "- " + results.get(player) + " dollars");
        }
        //if 1 or more player has 0 money
        if (bankruptcy == true) {
            System.out.println("There is a bankrupt Player!");
        }

    }

}
