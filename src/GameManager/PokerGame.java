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
    ArrayList<Player> players; //players of the game
    Pot pot;

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

    
    public void postGame(Map<String, Integer> results) {
        //handle post game stuff - new game with same ppl or leave, saving      
        System.out.println("Game Over!");
        for (String player : results.keySet()) {
            System.out.println(player + "- " + results.get(player) + " dollars");
        }
    }

}
