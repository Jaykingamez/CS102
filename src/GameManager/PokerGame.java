/*
Kong Khai
Last updated: 22/03/2024
Jia Lin
Last updated: 25/03/2024
Kamihya 
Last edit: 25/03/2024
*/
package GameManager;
import java.util.*;

import Entity.Game.*;


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
    boolean bankrupted = false; //if player has no more money in their balance- they can't continue playing.
    String displayCards = "";
    Card[] flop;
    Card turn;
    Card riverPhase;

    public Pot getPot(){
        return pot;
    }


    //JL added 24/03/2024
    //using the total combi class to gauge how much the AI will bet
    public int botPlayerMoves(BotPlayer p, Pot pot){
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
                int result = (randomNumber < bias) ? 2 : 1;
                
                return result;
            }else{
                //AI calls or raise
                double bias = 0.8; // 80% chance of landing call
                
                // Generate a random number between 0 and 1
                double randomNumber = random.nextDouble();
                int result = (randomNumber < bias) ? 1 : 2;
                
                return result;
            }
            
        }else if(gauge.getTier(freqmap) >= 1){
            if(playerBets == 0){
                // AI checks or raises
                double bias = 0.4; // 40% chance of landing raise

                // Generate a random number between 0 and 1
                double randomNumber = random.nextDouble();
                int result = (randomNumber < bias) ? 2 : 1;
                
                return result;
            }else{
                // AI folds or call
                double bias = 0.6; // 60% chance of landing call

                // Generate a random number between 0 and 1
                double randomNumber = random.nextDouble();
                int result = (randomNumber < bias) ? 1 : 0;
                
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
                int result = (randomNumber < bias) ? 0 : 1;
                
                return result;
            }else{
                // AI folds or call
                double bias = 0.5; // 50% chance of landing call

                // Generate a random number between 0 and 1
                double randomNumber = random.nextDouble();
                int result = (randomNumber < bias) ? 1 : 0;
                
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

    public PokerGame(Player[] players) {//, GameController gameController) {//, ) {    // construct game with set of players
        this.players = players;
        //this.gameController = gameController;
    }

    public void startGame() {
        initialBet = 10;
        startRound();
    }

    public void startTestRound() {
    /*
    For testing card hand mechanics 
    */
    }


    public void startTestGame() {

        /* method to start game completely in terminal purely for checking game mechanics from start to end*/ 
        
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
        

            if (player.getAmount() < (pot.getBetToContinue() - pot.getPlayerBets().get(player))) {
                pot.updateBetToContinuePoor(player.getAmount(), player);
                //player.setAmount(player.getAmount() - player.getAmount());
                // player.deductBalance(player.getAmount()); //player balance = 0 from initial bet
            }
            else {
                //player.setAmount(player.getAmount() - (pot.getBetToContinue() - pot.getPlayerBets().get(player)));
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
        riverPhase = null;
        System.out.println("DEBUG LINE: ROUND SUCCESSFULLY STARTED");
        //currentIndex = firstPlayerIndex;
        startTurn();
    }

    public void startTurn() {
        // replace scanning and printing with actual UI
        //TODO: handle every player has played thru currentPlayer.playedTurn
        clearTerminal();
        if (currentPlayer instanceof BotPlayer) {

            BotPlayer botPlayer = (BotPlayer) currentPlayer; //casting current player to a botplayer objekct 
            int action = botPlayerMoves(botPlayer, pot);

            switch(action) {

                case 0:
                 //if bot folds
                    currentPlayer.setPlayed(true);
                    currentPlayer.setFolded(true); 
                    numOfFolds++;
                    break;
                case 1:
                    //Call OR check 
                if (pot.getBetToContinue() == currentPlayer.getAmount()) {
                     //bot check
                    currentPlayer.setPlayed(true); 
                } else {
                    //bot calls
                    pot.updateBetToContinue(currentPlayer);
                    currentPlayer.setPlayed(true); 

                }
                    break;
                case 2: 
                    //bot raises
                    int raiseAmount = botPlayerRaise(botPlayer, pot);
                    pot.updateBetToContinue(raiseAmount, currentPlayer);
                    currentPlayer.setPlayed(true);
 

        }

            boolean allPlayed = true;
            //Checks if all players have played 
            for (Player player : players) {
                if (!player.getPlayed()) {
                allPlayed = false;
                break;
                }
            }
            if (allPlayed) {
                endRound(player); 
                return;
            }
        //JL added - 25/03/2024
        for(Player p : players){
        
            if(ActivateRole.Result(deck, p)){
                endRound(p);
                return;
            }
        }
        /*if (currentPlayer == players[0]){
            gameController.updatePlayerInfo();
            gameController.updateRole();
        }*/
        int currentBet = pot.getPlayerBets().get(currentPlayer);

        System.out.println("Pot: " + pot.getTotalPot());
        System.out.println("Current Phase: " + phase[currentPhase]);
        if (currentPhase != 0) {
            System.out.print("Community Cards: ");
            if (currentPhase > 0) { //flop and after
                System.out.print(flop[0] + ", " + flop[1] + ", " + flop[2]);
            }
            if (currentPhase > 1) { //turn and after
                System.out.print(", " + turn);
            }
            if (currentPhase > 2) { //river and after
                System.out.print(", " + riverPhase);
            }
            System.out.println();
        }

        System.out.println(currentPlayer.getName() + "\'s chips: " + currentPlayer.getAmount());
        System.out.println(currentPlayer.getName() + "\'s current bet: " + currentBet);
        System.out.println(currentPlayer.getName() + "\'s cards: ");
        System.out.println(currentPlayer.getpHand().getCard(0));  //prints cards in hand
        System.out.println(currentPlayer.getpHand().getCard(1));

        if (currentBet == currentPlayer.getAmount() || (currentPlayer.getAmount() == 0 && currentPlayer.getPlayed() == true)) {
            System.out.println("All in, press enter to continue");
            enterContinue(scan);
            currentPlayer.setPlayed(true);
            currentIndex++;
            if (currentIndex == players.length) { //equals num of players
                currentIndex = 0;
            }

            currentPlayer = players[currentIndex];
            int numOfAlls = 0;
            for (Player player : players) {
                if (player.getAmount() == currentBet) {
                    numOfAlls++;
                }
            }
            if (numOfAlls == 4) {
                if (currentPhase < 1) {
                    flop[0] = deck.dealCard();
                    flop[1] = deck.dealCard();
                    flop[2] = deck.dealCard();
                }
                if (currentPhase < 2) {
                    turn = deck.dealCard();
                }
                if (currentPhase < 3) {
                    riverPhase = deck.dealCard();
                }
                System.out.println("Community Cards: " 
                + flop[0] + ", " + flop[1] + ", " + flop[2] + ", " + turn + ", " + riverPhase);
                showDown();
            } else {
                startTurn();
            }
        } else if (currentPlayer.getFolded() == true) {
            System.out.println("Folded, press enter to continue");
            enterContinue(scan);
            currentIndex++;
                if (currentIndex == players.length) { //equals num of players
                    currentIndex = 0;
                }

                currentPlayer = players[currentIndex];
            startTurn();
        }
        else if (currentPlayer.getPlayed() == false) { //player has to take an action
            enterContinue(scan);

            // input handling, can replace with UI later
            System.out.println("Input: "); 
            System.out.println("0 to Fold");

            if (currentBet == pot.getBetToContinue()) { // no one has raised yet
                oneAction = "check";
            } else if (currentPlayer.getAmount() <= pot.getBetToContinue() - currentBet){   // if cannot afford to call/raise
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
                    pot.updateBetToContinuePoor(currentPlayer.getAmount(), currentPlayer);

                    
                } else if (oneAction.substring(0, 4).equals("call")) {
                    pot.updateBetToContinue(currentPlayer);
                }

                currentPlayer.setPlayed(true);

            } else if (input == 2) {
                System.out.println("input raise amount (excluding call amt)");
                int raise = scan.nextInt();
                pot.updateBetToContinue(raise, currentPlayer);
                currentPlayer.setPlayed(true); //in the case of raised all-in
                for (Player player : players) {         //&& player not folded
                    if (player.getAmount() != currentBet && player.getFolded() == false) { //reset input for players that are still in the round
                        player.setPlayed(false); //miya: 25.3, could we use active instead to check if theyve folded
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
                nextPhase();
                return;
            }
        } else {
            startTurn();
        }
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
            riverPhase = deck.dealCard();
        }

        if (phase[currentPhase].equals("Showdown")) {
            showDown();
        } else {
            currentIndex++;
            if (currentIndex == players.length) { //equals num of players
                currentIndex = 0;
            }

            currentPlayer = players[currentIndex];

            for (Player player : players) {
                if (player.getAmount() != 0 && player.getFolded() == false) {
                    player.setPlayed(false);
                }
            }

            startTurn();
        }
    }

    // JL added 24/03/2024
    // overlap with end round pls change accordingly
    public void showDown() {
        // handle showdown here, compare hand values with flop river and turn
        ArrayList<totalCombi> combinations = new ArrayList<>();
        river = new River(new ArrayList<>(Arrays.asList(flop)));
        river.addCard(turn);
        river.addCard(riverPhase);
        for(Player p : players){
            if(p.getFolded() == false){
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
        /*System.out.println();
        int winAmt = pot.getTotalPot() / winners.size();
        for(Player p : winners){
            p.addAmount(winAmt);
        }*/

        endRound(winners.toArray(new Player[winners.size()]));
        

        //clearTerminal(); 
        //System.out.println("Showdown Time!");
    }

    public void endRound(Player... winner) {
        //Sry qn M, how will the winner hashmap be passed into this?
        System.out.println();
        int winAmt = pot.getTotalPot() / winner.length;
        for(Player p : winner){
            p.addAmount(winAmt);
        }
        for (Player player: players) {
            player.getpHand().discardHand();;
            if (player.getAmount() == 0) {
                bankrupted = true;
            }
        }

        //handle win conditions
        if (bankrupted) { //or win condition met
            postGame();
        } //else if () {}
        /*for the win conditions is it referring to the individual player? If everyone's folded but one? or 
        the role specific win conditions? can we make a wonRound attribute?
        3.25 Miya: changed if bankrupted == true to if bankrupted. no need to have an equality statement 
                   */
        
            else {
            //reset stuff to prep for new round
            //clear river flop turn display here
            //startRound();
            System.out.println("We finished a Round!");
            postGame();
        }

    }

    public void postGame() {
        //handle post game stuff - new game with same ppl or leave, saving      
        System.out.println("Game Over!");
        for (Player player : players) {
            System.out.println(player.getName() + "- " + player.getAmount() + " dollars");
        }
    }

    public void clearTerminal() {
        System.out.print("\033\143");
        //System.out.print("\n\n\n\n\n"); 
        
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
