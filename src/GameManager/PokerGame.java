/*
3.27.24 PLEASE don't use the term chip anymore it's outdated from the current files ya
Kong Khai
Last updated: 22/03/2024
Jia Lin
Last updated: 25/03/2024
Kamiya: 26/03/2024
Qns: Line 17 and 21: Please change the spelling or make it clearer i dont know what it's trying to say
Added in method to Call botplayer's action. 
Please check: PM me if you have questions.
*/
package GameManager;
import java.util.*;

import Entity.Game.*;


public class PokerGame {
    Deck deck;
    ArrayList<Player> players; //reference to players to saMoneyta after game
    Scanner scan = new Scanner(System.in);
    Pot pot;
    River river; //JL added 24/03/2024
    int initialBet = 10;             // game's initial bet eMoneyround
    //private int firstPlayerIndex = 0; // first player at start of a new round
    int currentIndex = 0; //current player's index
    Player currentPlayer;
    String oneAction = "check";     // current available action for inputting 1
    String[] phase = {"preFlop", "Flop", "Turn", "River", "Showdown"}; //poker game phases
    int currentPhase = 0;    //poker game phase index
    int numOfFolds = 0;     //number of players folded
    //boolean bankrupted = false; //if player has no more money in their balance- they can't continue playing.
    String displayCards = "";

    public PokerGame(Player[] players) {
        this.players = players;
    }

    public Pot getPot(){
        return pot;
    }

    public void startGame() {
        Round round = new Round(players, this);
        round.startRound();
    }

    public void startTestRound() {
    /*
    For testing card hand mechanics 
    */
    }


    public void startTestGame() {

        /* method to start game completely in terminal purely for checking game mechanics from start to end*/ 
        
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
        //new game system here
    }

}
