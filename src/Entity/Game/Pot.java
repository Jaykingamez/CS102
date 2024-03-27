/*
Jia Lin
Last updated: 24/03/2024
*/
package Entity.Game;
import java.util.*;

public class Pot {
    
    private int totalpot;
    private int betToContinue;
    private Map<Player,Integer> playerBets;

    // to initialize new pot at the start of the game
    public Pot(List<Player> players){
     //Place 10 at the beginning from all players into the pot
    
        this.playerBets = new HashMap<>();
        for(int i = 0; i < players.size(); i++){
            playerBets.put(players.get(i), 10); // this start bet change accordingly
        }

        this.totalpot = 0;

        this.betToContinue = 10;

    }

    public int getBetToContinue(){
        return this.betToContinue;
    }

    public int getTotalPot(){
        return this.totalpot;
    }

    public void endTurnPot(){
        int total = 0; 
        for(Player p : playerBets.keySet()){
            total += playerBets.get(p);
        }

        this.totalpot += total;

        //code for going through the players and deducting the players
        // turnBets(Values in the HashMap) from their Bank
        for(Player p : playerBets.keySet()){
            p.setAmount(p.getAmount() - playerBets.get(p));
        }

        //all player bets becomes 0
        for(Player p : playerBets.keySet()){
            playerBets.put(p, 0);
        }

        //the highest bet for that turn revert to 0
        this.betToContinue = 0;

    }

    //used for raising the bet
    public void updateBetToContinue(int money, Player p){
        this.betToContinue += money;
        playerBets.put(p, betToContinue);
    }

    //used for calling
    public void updateBetToContinue(Player p){
        playerBets.put(p, betToContinue);
    }

    //used for calling but all in
    public void updateBetToContinuePoor(int money, Player p){
        playerBets.put(p, money);
    }


    public Map<Player,Integer> getPlayerBets() {
        return this.playerBets;
    }

    public void setTotalPot(int newTotal) {
        this.totalpot = newTotal;
    }

     // Method to distribute the pot to the winners
     public void distributePot(Player... winners) {
        int amountPerWinner = totalpot / winners.length;
        for (Player winner : winners) {
            winner.setAmount(winner.getAmount() + amountPerWinner);
        }
        totalpot = 0; // Reset pot
        betToContinue = 10;

        for(Player p : playerBets.keySet()){
            playerBets.put(p, 10);
        }
    }
    
}
