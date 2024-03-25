/*
Kong Khai
Last updated: 22/03/2024
Jia Lin
Last updated: 24/03/2024
*/
package GameManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import java.util.Map;
import java.util.Random;
import java.util.Collections;

import Controller.GameController;
import Entity.Game.Player;
import Entity.Game.Deck;
import Entity.Game.Pot;
import Entity.Game.Card;
//I didnt remove these yet because its to test whether the package factoring fcked things up
// import Entity.Player;
// import Entity.Deck;
// import Entity.Pot;
// import Entity.Card;

public class PokerGame {
    Deck deck;
    Player[] players; //reference to players to saMoneyta after game
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
    boolean bankrupted = false; //tracks if player has lost all chips n therefore cannot play anymore
    String displayCards = "";
    Card[] flop;
    Card turn;
    Card river;
    

    GameController gameController = null;

    public Pot getPot(){
        return pot;
    }


    //JL added 24/03/2024
    //using the total combi class to gauge how much the AI will bet
    public String botPlayerMoves(BotPlayer p, Pot pot){
        totalCombi gauge = new totalCombi(p, this.river);
        Map<Integer ,Integer> freqmap = gauge.numSameValue();
        int playerBets = pot.getBetToContinue();

        // Create a random number generator
        Random random = new Random();

        // if bot gets flush or more
        if(gauge.getTier(freqmap) >= 5){
            
            if(playerBets == 0){
                // AI raises or checks
                double bias = 0.7; // 70% chance of landing raise
                // Generate a random number between 0 and 1
                double randomNumber = random.nextDouble();
                String result = (randomNumber < bias) ? "raise" : "check";
                
                return result;
            }else{
                //AI calls or raise
                double bias = 0.8; // 80% chance of landing call
                
                // Generate a random number between 0 and 1
                double randomNumber = random.nextDouble();
                String result = (randomNumber < bias) ? "call" : "raise";
                
                return result;
            }
            
        }else if(gauge.getTier(freqmap) >= 1){
            if(playerBets == 0){
                // AI checks or raises
                double bias = 0.4; // 40% chance of landing raise

                // Generate a random number between 0 and 1
                double randomNumber = random.nextDouble();
                String result = (randomNumber < bias) ? "raise" : "check";
                
                return result;
            }else{
                // AI folds or call
                double bias = 0.6; // 60% chance of landing call

                // Generate a random number between 0 and 1
                double randomNumber = random.nextDouble();
                String result = (randomNumber < bias) ? "call" : "fold";
                
                return result;
            }
        
        }else{
            if(playerBets == 0){
                // AI checks or raises
                // AI raises or calls
                double bias = 0.5; // 50% chance of landing fold
                
                // Generate a random number between 0 and 1
                double randomNumber = random.nextDouble();
                
                // Simulate coin flip based on bias
                String result = (randomNumber < bias) ? "fold" : "check";
                
                return result;
            }else{
                // AI folds or call
                double bias = 0.5; // 50% chance of landing call

                // Generate a random number between 0 and 1
                double randomNumber = random.nextDouble();
                String result = (randomNumber < bias) ? "call" : "fold";
                
                return result;
            }
        }
    }


    //JL added 24/03/2024
    //only need to call this is bot raises
    public int botPlayerRaise(BotPlayer p, Pot pot){
        Random random = new Random();
        int randomBet = random.nextInt(301) + 50;
        return randomBet;
    }



    //JL added 24/03/2024
    //using the total combi class to gauge how much the AI will bet
    public String botPlayerMoves(BotPlayer p, Pot pot){
        totalCombi gauge = new totalCombi(p, this.river);
        Map<Integer ,Integer> freqmap = gauge.numSameValue();
        int playerBets = pot.getBetToContinue();

        // Create a random number generator
        Random random = new Random();

        // if bot gets flush or more
        if(gauge.getTier(freqmap) >= 5){
            
            if(playerBets == 0){
                // AI raises or checks
                double bias = 0.7; // 70% chance of landing raise
                // Generate a random number between 0 and 1
                double randomNumber = random.nextDouble();
                String result = (randomNumber < bias) ? "raise" : "check";
                
                return result;
            }else{
                //AI calls or raise
                double bias = 0.8; // 80% chance of landing call
                
                // Generate a random number between 0 and 1
                double randomNumber = random.nextDouble();
                String result = (randomNumber < bias) ? "call" : "raise";
                
                return result;
            }
            
        }else if(gauge.getTier(freqmap) >= 1){
            if(playerBets == 0){
                // AI checks or raises
                double bias = 0.4; // 40% chance of landing raise

                // Generate a random number between 0 and 1
                double randomNumber = random.nextDouble();
                String result = (randomNumber < bias) ? "raise" : "check";
                
                return result;
            }else{
                // AI folds or call
                double bias = 0.6; // 60% chance of landing call

                // Generate a random number between 0 and 1
                double randomNumber = random.nextDouble();
                String result = (randomNumber < bias) ? "call" : "fold";
                
                return result;
            }
        
        }else{
            if(playerBets == 0){
                // AI checks or raises
                // AI raises or calls
                double bias = 0.5; // 50% chance of landing fold
                
                // Generate a random number between 0 and 1
                double randomNumber = random.nextDouble();
                
                // Simulate coin flip based on bias
                String result = (randomNumber < bias) ? "fold" : "check";
                
                return result;
            }else{
                // AI folds or call
                double bias = 0.5; // 50% chance of landing call

                // Generate a random number between 0 and 1
                double randomNumber = random.nextDouble();
                String result = (randomNumber < bias) ? "call" : "fold";
                
                return result;
            }
        }
    }


    //JL added 24/03/2024
    //only need to call this is bot raises
    public int botPlayerRaise(BotPlayer p, Pot pot){
        Random random = new Random();
        int randomBet = random.nextInt(301) + 50;
        return randomBet;
    }


    public PokerGame(Player[] players) {//, GameController gameController) {    // construct game with set of players
        this.players = players;
        this.gameController = gameController;
    }

    public void startGame() {
        initialBet = 10;
        startRound();
    }

    public void startRound() {
        pot = new Pot(Arrays.asList(players));
        deck = new Deck();
        deck.shuffle();

        for (Player player: players) {
            player.setFolded(false);
            player.setPlayed(false);
            player.getpHand().addCard(deck.dealCard());
            player.getpHand().addCard(deck.dealCard());
        

            if (player.getMoney() < (pot.getBetToContinue() - pot.getPlayerBets().get(player))) {
                pot.updateBetToContinuePoor(player, player.getMoney());
                player.setMoney(player.getMoney() - player.getMoney());
                // player.deductBalance(player.getMoney()); //player balance = 0 from initial bet
            }
            else {
                player.setMoney(player.getMoney() - (pot.getBetToContinue() - pot.getPlayerBets().get(player)));
                pot.updateBetToContinue(player);
                // player.deductBalance();
            }
        }

        //reset variables for new round
        currentPhase = 0;
        numOfFolds = 0;
        displayCards = "";
        bankrupted = false;
        currentPlayer = players[currentIndex];
        flop = new Card[3];
        turn = null;
        river = null;
        System.out.println("DEBUG LINE: ROUND SUCCESSFULLY STARTED");
        //currentIndex = firstPlayerIndex;
        startTurn();
    }

    public void startTurn() {
        // replace scanning and printing with actual UI
        //TODO: handle every player has played thru currentPlayer.playedTurn
        clearTerminal();

        /*if (currentPlayer == players[0]){
            gameController.updatePlayerInfo();
            gameController.updateRole();
        }*/
        int currentBet = pot.getPlayerBets().get(currentPlayer);

        System.out.println("Pot: " + pot.getTotalPot());
        System.out.println("Current Phase: " + phase[currentPhase]);
        System.out.println(currentPlayer.getName() + "\'s chips: " + currentPlayer.getMoney());
        System.out.println(currentPlayer.getName() + "\'s current bet: " + currentBet);

        if (currentBet == currentPlayer.getMoney() || (currentPlayer.getMoney() == 0 && currentPlayer.getPlayed() == true)) {
            //if (currentPlayer.lastStandAcknowledge == false) {      //for if player is all-in from initial bet
                System.out.println(currentPlayer.getName() + "\'s turn.");
                System.out.println(currentPlayer.getName() + "\'s cards: ");
                System.out.println(currentPlayer.getpHand().getCard(0));  //prints cards in hand
                System.out.println(currentPlayer.getpHand().getCard(1));

                System.out.println("All in, press enter to continue");
                String a = scan.nextLine();
                //currentPlayer.lastStandAcknowledge = true;
            //}

            currentPlayer.setPlayed(true);
        } else if (currentPlayer.getPlayed() == false) { //player has to take an action
            if (currentPhase != 0) {
                System.out.print("Community Cards: ");
                if (currentPhase > 0) { //flop and after
                    System.out.print(flop[0] + ", " + flop[1] + ", " + flop[2]);
                }
                if (currentPhase > 1) { //turn and after
                    System.out.print(", " + turn);
                }
                if (currentPhase > 2) { //river and after
                    System.out.print(", " + river);
                }
                System.out.println();
            }
            System.out.println(currentPlayer.getName() + "\'s cards: ");
            System.out.println(currentPlayer.getpHand().getCard(0));  //prints cards in hand
            System.out.println(currentPlayer.getpHand().getCard(1));

            enterContinue(scan);

            // input handling, can replace with UI later
            System.out.println("Input: "); 
            System.out.println("0 to Fold");

            if (currentBet == pot.getBetToContinue()) { // no one has raised yet
                oneAction = "check";
            } else if (currentPlayer.getMoney() <= pot.getBetToContinue() - currentBet){   // if cannot afford to call/raise
                oneAction = "all in";
            } else {
                oneAction = "call (" + (pot.getBetToContinue() - currentBet) + " chips)";
            }

            System.out.println("1 to " + oneAction);
            if (!(oneAction.equals("all in"))) {
                System.out.println("2 to raise");
            }

            int input = scan.nextInt();
            if (input == 0) {
                currentPlayer.setPlayed(true);
                currentPlayer.setFolded(true);
                numOfFolds++;

                if (numOfFolds == players.length - 1) { //everyone but 1 person folded, end the round
                    int index = 0;
                    for (int i = 0; i < players.length; i++) {
                        if (players[i].getFolded() == false) {
                            index = i;
                            break;
                        }
                    }

                    endRound(players[index]);
                    return; //round has ended, dont handle for next turns;
                }
            } else if (input == 1) {
                if (oneAction.equals("all in")) {
                    pot.updateBetToContinuePoor(currentPlayer, currentPlayer.getMoney());
                    //currentPlayer.setMoney(currentPlayer.getMoney() - currentPlayer.getMoney());

                    
                } else if (oneAction.substring(0, 4).equals("call")) {
                    pot.updateBetToContinue(currentPlayer);
                    //currentPlayer.setMoney(currentPlayer.getMoney() - (pot.getBetToContinue() - currentBet));
                }

                currentPlayer.setPlayed(true);

            } else if (input == 2) {
                System.out.println("input raise amount (excluding call amt)");
                int raise = scan.nextInt();
                //raise -= pot.getBetToContinue();
                //currentPlayer.setMoney(currentPlayer.getMoney() - (raise + pot.getPlayerBets().get(currentPlayer)));
                pot.updateBetToContinue(raise, currentPlayer);
                currentPlayer.setPlayed(true); //in the case of raised all-in
                for (Player player : players) {         //&& player not folded
                    if (currentPlayer.getMoney() != 0 && currentPlayer.getFolded() == false) { //reset input for players that are still in the round
                        currentPlayer.setPlayed(false); //miya: 25.3, could we use active instead to check if theyve folded
                    }
                }
            }

            boolean allPlayed = true;
            for (Player player: players) {
                if (player.getPlayed() == false) {
                    allPlayed = false;
                    break;
                }
            }

            if (allPlayed == false) {
                //handling for next turn
                currentIndex++;
                if (currentIndex == players.length) { //equals num of players
                    currentIndex = 0;
                }

                currentPlayer = players[currentIndex];
                startTurn();
                return;
            } else {
                for (Player player: players) {
                    if (currentPlayer.getMoney() != 0 && currentPlayer.getFolded() == false) {
                        player.setPlayed(false);
                    }
                }
                nextPhase();
                return;
            }
        }
        startTurn();
    }

    public void nextPhase() {
        pot.endTurnPot();

        currentPhase++;
        if (phase[currentPhase].equals("Flop")) {
            flop[0] = deck.dealCard();
            flop[1] = deck.dealCard();
            flop[2] = deck.dealCard();
        } else if (phase[currentPhase].equals("Turn")) {
            turn = deck.dealCard();
        } else if (phase[currentPhase].equals("River")) {
            river = deck.dealCard();
        }

        if (phase[currentPhase].equals("Showdown")) {
            showDown();
        } else {
            currentIndex++;
            if (currentIndex == players.length) { //equals num of players
                currentIndex = 0;
            }

            currentPlayer = players[currentIndex];

            startTurn();
        }
    }

    // JL added 24/03/2024
    // overlap with end round pls change accordingly
    public void showDown() {
        // handle showdown here, compare hand values with flop river and turn
        ArrayList<totalCombi> combinations = new ArrayList<>();
        for(Player p : players){
            if(players.getactive() == true){
                combinations.add(new totalCombi(p, river));
            }
        }
        Collections.sort(combinations);

        ArrayList<Player> winners = new ArrayList<>();
        winners.add(combinations.get(combinations.size() - 1).getPlayer());

        for(int i = 0; i < combinations.size() - 1; i++){
            if(combinations.get(i).compareTo(combinations.get(combinations.size() - 1)) == 0){
                winners.add(combinations.get(i).getPlayer());
            }
        }

        // possible to have error?
        System.out.println();
        int winAmt = pot.getTotalPot() / winners.size();
        for(Player p : winners){
            p.addAmount(winAmt);
        }

        clearTerminal(); 
        System.out.println("Showdown Time!");
    }

    public void endRound(Player... winner) {
        //Sry qn M, how will the winner hashmap be passed into this?
        pot.distributePot(winner);

        for (Player player: players) {
            player.getpHand().discardHand();;
            if (player.getMoney() == 0) {
                bankrupted = true;
            }
        }

        //handle win conditions
        if (bankrupted == true) { //or win condition met
            postGame();
        } else if () {}
        /*for the win conditions is it referring to the individual player? If everyone's folded but one? or 
        the role specific win conditions? can we make a wonRound attribute?
                   */
        
            else {
            //reset stuff to prep for new round
            //clear river flop turn display here
            startRound();
        }

    }

    public void postGame() {
        //handle post game stuff - new game with same ppl or leave, saving      
        System.out.println("Game Over!");
        for (Player player : players) {
            System.out.println(player.getName() + "- " + player.getMoney() + " dollars");
        }
    }

    public void clearTerminal() {
        System.out.print("\033\143");
        //
        System.out.print("\n\n\n\n\n"); 
        
    }

    public void enterContinue(Scanner scanner) {
        System.out.println("Press Enter to Continue...");
        try {
            System.in.read();
            scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace(); //added debugging line
            
        }
    }
}
