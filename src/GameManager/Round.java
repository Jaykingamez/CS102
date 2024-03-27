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

public class Round {
    public Deck deck;
    Scanner scan = new Scanner(System.in);
    Pot pot;
    public River river; // JL added 24/03/2024
    int firstPlayerIndex = 0; // the first player of the round's every phase, increments after every round
    int currentIndex = 0; // current player's index
    Player currentPlayer;
    String oneAction = "check"; // current available action for inputting 1
    String[] phase = { "preFlop", "Flop", "Turn", "River", "Showdown" }; // poker game phases
    int currentPhase = 0; // poker game phase index
    int numOfFolds = 0; // number of players folded
    String displayCards = "";
    ArrayList<Player> players;
    boolean bankrupted = false;
    PokerGame game;
    ArrayList<Player> roundResult;
    int currentRound = 0;

    public Round(ArrayList<Player> players, PokerGame game) {
        this.players = players;
        this.game = game;
    }

    public void startRound() {
        //human gets role
        //if human is always 1st in array
        
        for(Player p : players){
            if(!(p instanceof BotPlayer)){
                p.setRole(new Role());
            }
        }
        pot = new Pot(players);
        deck = new Deck();
        deck.shuffle();

        for (Player player : players) {
            // reset player states, draw 2 cards
            player.setFolded(false);
            player.setPlayed(false);
            player.getpHand().addCard(deck.dealCard());
            player.getpHand().addCard(deck.dealCard());

            // if player's current money left is less than the initial bet of a round
            if (player.getAmount() < (pot.getBetToContinue() - pot.getPlayerBets().get(player))) {
                pot.updateBetToContinuePoor(player.getAmount(), player);
            } else { // player bets the initial bet of the round
                pot.updateBetToContinue(player);
            }
        }

        // reset variables for new round
        bankrupted = false;
        currentPhase = 0;
        numOfFolds = 0;
        displayCards = "";
        currentIndex = firstPlayerIndex;
        currentPlayer = players.get(currentIndex);
        currentRound++;
        roundResult = new ArrayList<Player>();
        river = new River(new ArrayList<Card>());
        System.out.println("DEBUG LINE: ROUND SUCCESSFULLY STARTED");
        beforeTurnStarts();
    }

    // to call at the start of the players turns after card is added into river
    public void beforeTurnStarts() {
            // passing in the human player
        for(Player p : players){
            if(!(p instanceof BotPlayer)){
                if (ActivateRole.Result(deck, p)) {
                        endRound(p);
                        return;
                }
            }
        }
                

        startTurn();
    }

    public void startTurn() {
        // replace scanning and printing with actual UI
        // TODO: handle every player has played thru currentPlayer.playedTurn
        clearTerminal();

        if(!(currentPlayer instanceof BotPlayer)){
            System.out.println(currentPlayer.getPRole().getRoleDescription() + ": " + currentPlayer.getPRole().getRoleDescription());
        }
       

            int currentBet = pot.getPlayerBets().get(currentPlayer);

            System.out.println("Round " + currentRound);
            System.out.println("Pot: " + pot.getTotalPot());
            System.out.println("Current Phase: " + phase[currentPhase]);
            if (currentPhase != 0) {
                System.out.print("Community Cards: ");
                if (currentPhase > 0) { // flop and after
                    System.out.print(river.getRiver().get(0) + ", "
                            + river.getRiver().get(1) + ", "
                            + river.getRiver().get(2));
                }
                if (currentPhase > 1) { // turn and after
                    System.out.print(", " + river.getRiver().get(3));
                }
                if (currentPhase > 2) { // river and after
                    System.out.print(", " + river.getRiver().get(4));
                }
                System.out.println();
            }

            System.out.println(currentPlayer.getName() + "\'s money: " + currentPlayer.getAmount());
            System.out.println(currentPlayer.getName() + "\'s current bet: " + currentBet);

            if (currentBet == currentPlayer.getAmount()
                    || (currentPlayer.getAmount() == 0 && currentPlayer.getPlayed() == true)) {
                System.out.print(currentPlayer.getName() + " All in, ");
                enterContinue(scan);
                currentPlayer.setPlayed(true);
                currentIndex++;
                if (currentIndex == players.size()) { // equals num of players
                    currentIndex = 0;
                }

                currentPlayer = players.get(currentIndex);
                //number of all ins and folds
                int numOfAlls = 0;
                for (Player player : players) {
                    if (player.getFolded() == true || player.getAmount() == currentBet) {
                        numOfAlls++;
                    }
                }
                if (numOfAlls == players.size()) {
                    while (river.getRiver().size() < 5) {
                        river.getRiver().add(deck.dealCard());
                    }
                    System.out.println("Community Cards: "
                            + river.getRiver().get(0) + ", "
                            + river.getRiver().get(1) + ", "
                            + river.getRiver().get(2) + ", "
                            + river.getRiver().get(3) + ", "
                            + river.getRiver().get(4));
                    showDown();
                } else {
                    startTurn();
                }
            } else if (currentPlayer.getFolded() == true) {
                System.out.print(currentPlayer.getName() + " Folded, ");
                enterContinue(scan);
                currentIndex++;
                if (currentIndex == players.size()) { // equals num of players
                    currentIndex = 0;
                }

                currentPlayer = players.get(currentIndex);
                startTurn();
            } else if (currentPlayer.getPlayed() == false) { // player has to take an action
                enterContinue(scan);

                if (currentBet == pot.getBetToContinue()) { // no one has raised yet
                    oneAction = "check";
                } else if (currentPlayer.getAmount() <= pot.getBetToContinue()) { // if cannot afford to call/raise
                    oneAction = "all in";
                } else {
                    oneAction = "call (" + (pot.getBetToContinue() - currentBet) + " dollars)";
                }

                boolean validInput = true;
                if (!(currentPlayer instanceof BotPlayer)) {
                    validInput = false;
                    printHand(currentPlayer);   
                    System.out.println();

                    printPrompt();
                }

                //JL added this part
                int input = -1;
                // instance here so botPlayer can call raise method later
                BotPlayer botPlayer = null;
                if(currentPlayer instanceof BotPlayer){
                    botPlayer = (BotPlayer) currentPlayer; // casting current player to a botplayer objekct
                    input = BotMoves.botPlayerMoves(botPlayer, pot, this); // check botMoves class
                }else{
                    while (!validInput) { //validate user option select
                        try {
                            input = scan.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid Input! (Choose 0, 1, 2 or 3)");
                            System.out.println();
                            printPrompt();
                            scan.nextLine();
                        } 

                        if (input == 0 || input == 1 || input == 2 || input == 3) {
                            validInput = true;
                        } else {
                            System.out.println("Invalid Input! (Choose 0, 1, 2 or 3)");
                            System.out.println();
                            printPrompt();
                            scan.nextLine();
                        }
                    }
                }
                if (input == 0) {
                    currentPlayer.setPlayed(true);
                    currentPlayer.setFolded(true);
                    numOfFolds++;

                    if (currentPlayer instanceof BotPlayer) {
                        System.out.print(currentPlayer.getName() + " Folded, ");
                        enterContinue(scan);
                    }

                    if (numOfFolds == players.size() - 1) { // everyone but 1 person folded, end the round
                        int index = 0;
                        for (int i = 0; i < players.size(); i++) {
                            if (players.get(i).getFolded() == false) {
                                index = i;
                                break;
                            }
                        }
                        // deducts the bet amt from their money
                        pot.endTurnPot();
                        // then award the non folded player the whole pot
                        endRound(players.get(index));
                        return; // round has ended, dont handle for next turns;
                    }
                } else if (input == 1) {
                    if (oneAction.equals("all in")) {
                        pot.updateBetToContinuePoor(currentPlayer.getAmount(), currentPlayer);

                        if (currentPlayer instanceof BotPlayer) {
                            System.out.print(currentPlayer.getName() + " went All In! ");
                        }

                    } else if (oneAction.substring(0, 4).equals("call")) {
                        pot.updateBetToContinue(currentPlayer);

                        if (currentPlayer instanceof BotPlayer) {
                            System.out.print(currentPlayer.getName() + " Called, ");
                        }
                    } else {
                        if (currentPlayer instanceof BotPlayer) {
                            System.out.print(currentPlayer.getName() + " Checked, ");
                        }
                    }

                    if (currentPlayer instanceof BotPlayer) {
                        enterContinue(scan);
                    }

                    currentPlayer.setPlayed(true);

                } else if (input == 2) {
                    int raise = 0;
                    //JL added this part
                    if(currentPlayer instanceof BotPlayer){
                        raise = BotMoves.botPlayerRaise(botPlayer, pot);
                        System.out.println(currentPlayer.getName() + " Raised " + raise + " dollars, ");
                        enterContinue(scan);
                    }else{
                        boolean validRaise = false;
                        while(!validRaise) {
                            try {
                                System.out.println("input raise amount (excluding call amount)");
                                raise = scan.nextInt();
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid Raise! (Enter an Integer)");
                                System.out.println();
                                scan.nextLine();
                                raise = 0;
                                continue; //this prevents double "Invalid Raise!" posting
                            }

                            // raise more than 0 and less/equal to what player can afford to raise
                            if (raise > 0 && raise <= currentPlayer.getAmount() - currentBet) {
                                validRaise = true;
                            } else {
                                System.out.println("Invalid Raise! (Raise must be in valid range; Remember that raise amount excludes call amount)");
                                System.out.println();
                                scan.nextLine();
                                raise = 0;
                            }
                        }
                    }
                    pot.updateBetToContinue(raise, currentPlayer);
                    currentPlayer.setPlayed(true); // in the case of raised all-in
                    for (Player player : players) { // && player not folded
                        if (player.getAmount() != currentBet && player.getFolded() == false) { // reset input for
                                                                                               // players that are still
                                                                                               // in the round
                            player.setPlayed(false); 
                        }  
                    }
                } else if (input == 3){
                    System.exit(0);
                }

                boolean allPlayed = true;
                for (Player player : players) {
                    if (player.getPlayed() == false) {
                        allPlayed = false;
                        break;
                    }
                }

                if (allPlayed == false) {
                    // handling for next turn
                    currentIndex++;
                    if (currentIndex == players.size()) { // equals num of players
                        currentIndex = 0;
                    }

                    currentPlayer = players.get(currentIndex);
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
            river.getRiver().add(deck.dealCard());
            river.getRiver().add(deck.dealCard());
            river.getRiver().add(deck.dealCard());
        } else if (phase[currentPhase].equals("Turn") || phase[currentPhase].equals("River")) {
            // Both turn and river deals 1 card
            river.getRiver().add(deck.dealCard());
        }

        if (phase[currentPhase].equals("Showdown")) {
            showDown();
        } else {
            currentIndex++;
            if (currentIndex == players.size()) { // equals num of players
                currentIndex = 0;
            }

            currentPlayer = players.get(currentIndex);

            for (Player player : players) {
                // reset Played state for players who are not all in or folded
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
        clearTerminal();
        // handle showdown here, compare hand values with flop river and turn
        ArrayList<TotalCombi> combinations = new ArrayList<>();
        for (Player p : players) {
            if (p.getFolded() == false) {
                combinations.add(new TotalCombi(p, river));
            }
        }
        Collections.sort(combinations);

        ArrayList<Player> winners = new ArrayList<>();
        winners.add(combinations.get(combinations.size() - 1).getPlayer());

        for (int i = 0; i < combinations.size() - 1; i++) {
            if (combinations.get(i).compareTo(combinations.get(combinations.size() - 1)) == 0) {
                winners.add(combinations.get(i).getPlayer());
            }
        }

        System.out.println("Community Cards: " + river.getRiver().get(0) + ", " 
        +  river.getRiver().get(1) + ", " 
        + river.getRiver().get(2) + ", " 
        + river.getRiver().get(3) + ", " 
        + river.getRiver().get(4));

        for (TotalCombi combi : combinations) {
            Hand hand = combi.getPlayer().getpHand();
            System.out.println(combi.getPlayer().getName() + "\'s Hand - " 
            + hand.getCard(0) + ", "
            + hand.getCard(1));

            Map<Integer, Integer> ValuefrequencyMap = combi.numSameValue();
            int tier = combi.getTier(ValuefrequencyMap);
            String combination = getCombination(tier);
            
            System.out.println("Value: " + combination);
            System.out.println();
        }

        enterContinue(scan);

        endRound(winners.toArray(new Player[winners.size()]));
    }

    public void endRound(Player... winner) {
        clearTerminal();
        int winAmt = pot.getTotalPot() / winner.length;
        for (Player p : winner) {
            System.out.println(p.getName() + " has won " + winAmt + "dollars!"); 
            p.addAmount(winAmt);
        }

        HashMap<String, Integer> results = new HashMap<String, Integer>();

        for (Player player : players) {
            player.getpHand().discardHand();

            results.put(player.getName(), player.getAmount());
            if (player.getAmount() == 0) {
                bankrupted = true;
            }
        }

        enterContinue(scan);

        if (bankrupted) {
            game.postGame(results);           
        } else {
            firstPlayerIndex++;
            startRound();
        }
    }

    public void printHand(Player player) {
        System.out.println(player.getName() + "\'s cards: ");
        System.out.println(player.getpHand().getCard(0));
        System.out.println(player.getpHand().getCard(1));
    }

    public void printPrompt() {
        System.out.println("Input: ");
        System.out.println("0 to Fold");

        System.out.println("1 to " + oneAction);
        if (!(oneAction.equals("all in"))) {
            System.out.println("2 to raise");
        }
        System.out.println("3 to exit");
    }

    public String getCombination(int tier) {
        // 0 - normal hand, 1 - pair, 2 - doublePair, 3 - three of a kind
        // 4 - straight, 5 - flush, 6 - full house, 7 - four of a kind
        // 8 - straight flush, 9 - royal flush
        String combination = "";
        switch(tier) {
            case 0:
                combination = "0 - High Card";
                break;
            case 1:
                combination = "1 - One Pair";
                break;
            case 2:
                combination = "2 - Two Pair";
                break;
            case 3:
                combination = "3 - Three of a Kind";
                break;
            case 4:
                combination = "4 - Straight";
                break;
            case 5:
                combination = "5 - Flush";
                break;
            case 6:
                combination = "6 - Full House";
                break;
            case 7:
                combination = "7 - Four of a Kind";
                break;
            case 8:
                combination = "8 - Straight Flush";
                break;
            case 9:
                combination = "9 - Royal Flush";
                break;
        }

        return combination;
    }

    public void clearTerminal() {
        System.out.print("\033\143");

        

    }

    public void enterContinue(Scanner scanner) {
        System.out.println("Press Enter to Continue...");
        try {
            System.in.read();
            scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace(); // added debugging line

        }
    }
}
