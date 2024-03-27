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
    Player[] players;
    boolean bankrupted = false;
    PokerGame game;
    Player[] roundResult;

    public Round(Player[] players, PokerGame game) {
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
        pot = new Pot(Arrays.asList(players));
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
        currentPlayer = players[currentIndex];
        roundResult = new Player[] {};
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
        // if (currentPlayer instanceof BotPlayer) {

        //     BotPlayer botPlayer = (BotPlayer) currentPlayer; // casting current player to a botplayer objekct
        //     int action = BotMoves.botPlayerMoves(botPlayer, pot, this); // check botMoves class

        //     switch (action) {

        //         case 0:
        //             // if bot folds
        //             currentPlayer.setPlayed(true);
        //             currentPlayer.setFolded(true);
        //             numOfFolds++;
        //             break;
        //         case 1:
        //             // Call OR check
        //             if (pot.getBetToContinue() == currentPlayer.getAmount()) {
        //                 // bot check
        //                 currentPlayer.setPlayed(true);
        //             } else {
        //                 // bot calls
        //                 pot.updateBetToContinue(currentPlayer);
        //                 currentPlayer.setPlayed(true);

        //             }
        //             break;
        //         /*case 2:
        //             // bot raises
        //             int raiseAmount = botPlayerMoves.botPlayerRaise(botPlayer, pot);
        //             pot.updateBetToContinue(raiseAmount, currentPlayer);
        //             currentPlayer.setPlayed(true);*/

        //     }

            // boolean allPlayed = true;
            // // Checks if all players have played
            // for (Player player : players) {
            //     if (!player.getPlayed()) {
            //         allPlayed = false;
            //         break;
            //     }
            // }
            // if (allPlayed) {
            //     nextPhase();
            //     return;
            // }
        //}

            int currentBet = pot.getPlayerBets().get(currentPlayer);

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

            System.out.println(currentPlayer.getName() + "\'s chips: " + currentPlayer.getAmount());
            System.out.println(currentPlayer.getName() + "\'s current bet: " + currentBet);

            if (currentBet == currentPlayer.getAmount()
                    || (currentPlayer.getAmount() == 0 && currentPlayer.getPlayed() == true)) {
                System.out.println("All in, press enter to continue");
                enterContinue(scan);
                currentPlayer.setPlayed(true);
                currentIndex++;
                if (currentIndex == players.length) { // equals num of players
                    currentIndex = 0;
                }

                currentPlayer = players[currentIndex];
                //number of all ins and folds
                int numOfAlls = 0;
                for (Player player : players) {
                    if (player.getFolded() == true || player.getAmount() == currentBet) {
                        numOfAlls++;
                    }
                }
                if (numOfAlls == players.length) {
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
                System.out.println("Folded, press enter to continue");
                enterContinue(scan);
                currentIndex++;
                if (currentIndex == players.length) { // equals num of players
                    currentIndex = 0;
                }

                currentPlayer = players[currentIndex];
                startTurn();
            } else if (currentPlayer.getPlayed() == false) { // player has to take an action
                enterContinue(scan);

                printHand(currentPlayer);
                System.out.println();

                // input handling, can replace with UI later
                System.out.println("Input: ");
                System.out.println("0 to Fold");

                if (currentBet == pot.getBetToContinue()) { // no one has raised yet
                    oneAction = "check";
                } else if (currentPlayer.getAmount() <= pot.getBetToContinue()) { // if cannot afford to call/raise
                    oneAction = "all in";
                } else {
                    oneAction = "call (" + (pot.getBetToContinue() - currentBet) + " chips)";
                }

                System.out.println("1 to " + oneAction);
                if (!(oneAction.equals("all in"))) {
                    System.out.println("2 to raise");
                }
                System.out.println("3 to exit");

                //JL added this part
                int input;
                // instance here so botPlayer can call raise method later
                BotPlayer botPlayer = null;
                if(currentPlayer instanceof BotPlayer){
                    botPlayer = (BotPlayer) currentPlayer; // casting current player to a botplayer objekct
                    input = BotMoves.botPlayerMoves(botPlayer, pot, this); // check botMoves class
                }else{
                     input = scan.nextInt();
                }
                if (input == 0) {
                    currentPlayer.setPlayed(true);
                    currentPlayer.setFolded(true);
                    numOfFolds++;

                    System.out.println(currentPlayer.getName() + " folded");

                    if (numOfFolds == players.length - 1) { // everyone but 1 person folded, end the round
                        int index = 0;
                        for (int i = 0; i < players.length; i++) {
                            if (players[i].getFolded() == false) {
                                index = i;
                                break;
                            }
                        }
                        // deducts the bet amt from their money
                        pot.endTurnPot();
                        // then award the non folded player the whole pot
                        endRound(players[index]);
                        return; // round has ended, dont handle for next turns;
                    }
                } else if (input == 1) {
                    if (oneAction.equals("all in")) {
                        pot.updateBetToContinuePoor(currentPlayer.getAmount(), currentPlayer);
                        System.out.println(currentPlayer.getName() + " went all in!");

                    } else if (oneAction.substring(0, 4).equals("call")) {
                        pot.updateBetToContinue(currentPlayer);
                        System.out.println(currentPlayer.getName() + " called");
                    }

                    currentPlayer.setPlayed(true);

                } else if (input == 2) {
                    System.out.println("input raise amount (excluding call amt)");
                    int raise = 0;
                    //JL added this part
                    if(currentPlayer instanceof BotPlayer){
                        raise = BotMoves.botPlayerRaise(botPlayer, pot);
                        System.out.println(currentPlayer.getName() + " raised " + raise);
                    }else{
                        raise = scan.nextInt();
                    }
                    pot.updateBetToContinue(raise, currentPlayer);
                    currentPlayer.setPlayed(true); // in the case of raised all-in
                    for (Player player : players) { // && player not folded
                        if (player.getAmount() != currentBet && player.getFolded() == false) { // reset input for
                                                                                               // players that are still
                                                                                               // in the round
                            player.setPlayed(false); // miya: 25.3, could we use active instead to check if theyve
                                                     // folded
                        }
                    }
                } else if (input == 3){
                    MainMenu.mainMenu();
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
                    if (currentIndex == players.length) { // equals num of players
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
            if (currentIndex == players.length) { // equals num of players
                currentIndex = 0;
            }

            currentPlayer = players[currentIndex];

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
        // handle showdown here, compare hand values with flop river and turn
        ArrayList<totalCombi> combinations = new ArrayList<>();
        for (Player p : players) {
            if (p.getFolded() == false) {
                combinations.add(new totalCombi(p, river));
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

        // possible to have error?
        /*
         * System.out.println();
         * int winAmt = pot.getTotalPot() / winners.size();
         * for(Player p : winners){
         * p.addAmount(winAmt);
         * }
         */

        endRound(winners.toArray(new Player[winners.size()]));

        // clearTerminal();
        // System.out.println("Showdown Time!");
    }

    public void endRound(Player... winner) {
        // Sry qn M, how will the winner hashmap be passed into this?
        System.out.println();
        int winAmt = pot.getTotalPot() / winner.length;
        for (Player p : winner) {
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

        // handle win conditions
        /*
         * for the win conditions is it referring to the individual player? If
         * everyone's folded but one? or
         * the role specific win conditions? can we make a wonRound attribute?
         * 3.25 Miya: changed if bankrupted == true to if bankrupted. no need to have an
         * equality statement
         */

        System.out.println("We finished a Round!");
        game.postGame(results, bankrupted);
    }

    public void printHand(Player player) {
        System.out.println(player.getName() + "\'s cards: ");
        System.out.println(player.getpHand().getCard(0));
        System.out.println(player.getpHand().getCard(1));
    }

    public void clearTerminal() {
        System.out.print("\033\143");
        // System.out.print("\n\n\n\n\n");

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
