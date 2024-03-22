import java.util.*;

public class Pot {
    
    private int totalpot;
    private int betToContinue;
    private Map<Player,Integer> playerBets;

    // to initiaze new pot at the start of the game
    public Pot(List<Player> players){
        // if we not doing big bind and small bind then everyone at the start of the game can place $10
        //into the pot
        this.playerBets = new HashMap<>();
        for(int i = 0; i < players.size(); i++){
            playerBets.put(players.get(i), 10);
        }

        this.totalpot = 0;

        this.betToContinue = 10;

    }

    public void endTurnPot(){
        int total = 0; 
        for(Player p : playerBets.keySet()){
            total += playerBets.get(p);
        }

        this.totalpot += total;

        //code for going through the HashMap and deteucting the players
        // turnBets(Values in the HashMap) from their Bank
        for(Player p : playerBets.keySet){
            p.setMoney() = 0;
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

    
}
