/*
Kong Khai
Last updated: 22/03/2024
*/
package GameManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import Entity.Player;
import Entity.Deck;
import Entity.Pot;

public class PokerGame {
    Deck deck;
    Player[] players; //reference to players to save data after game
    Scanner scan = new Scanner(System.in);
    Pot pot;
    int initialBet = 10;             // game's initial bet every round
    private int firstPlayerIndex = 0; // first player at start of a new round
    int currentIndex = firstPlayerIndex; //current player's index
    Player currentPlayer;
    String oneAction = "check";     // current available action for inputting 1
    String[] phase = {"preFlop", "Flop", "Turn", "River", "Showdown"}; //poker game phases
    int currentPhase = 0;    //poker game phase index
    int numOfFolds = 0;     //number of players folded
    boolean bankrupted = false; //tracks if player has lost all chips n therefore cannot play anymore

    public Pot getPot(){
        return pot;
    }

    public PokerGame(Player[] players) {    // construct game with set of players
        this.players = players;
    }

    public void startGame() {
        pot = new Pot(Arrays.asList(players));
        startRound();
    }

    public void startRound() {
        deck = new Deck();
        deck.shuffle();

        

        for (Player player: players) {
            player.getpHand().addCard(deck.dealCard());
            player.getpHand().addCard(deck.dealCard());
        

            if (player.getValue() < pot.getBetToContinue() - pot.getPlayerBets().get(player)) {
                pot.updateBetToContinue(player);
                player.deductBalance(player.getValue()); //player balance = 0 from initial bet
            }
            else {
                pot.updateBetToContinue(player);
                player.deductBalance(pot.getBetToContinue() - pot.getPlayerBets().get(player));
            }
        }

        currentPlayer = players[firstPlayerIndex];
        currentIndex = firstPlayerIndex;
        startTurn();
    }

    public void startTurn() {
        // replace scanning and printing with actual UI
        //TODO: handle every player has played thru currentPlayer.playedTurn

        if (currentPlayer.getValue() == 0 && currentPlayer.getActive() == true) {
            //if (currentPlayer.lastStandAcknowledge == false) {      //for if player is all-in from initial bet
                System.out.println(currentPlayer.getName() + "\'s turn.");
                System.out.println(currentPlayer.getName() + "\'s cards: ");
                System.out.println(currentPlayer.getpHand().getCard(0));  //prints cards in hand
                System.out.println(currentPlayer.getpHand().getCard(1));

                System.out.println("All in, press enter to continue");
                scan.nextLine();
                //currentPlayer.lastStandAcknowledge = true;
            //}

            currentPlayer.setActive(false);
        } else if (currentPlayer.getActive() == true) {
            System.out.println(currentPlayer.getName() + "\'s turn.");
            System.out.println(currentPlayer.getName() + "\'s cards: ");
            System.out.println(currentPlayer.getpHand().getCard(0));  //prints cards in hand
            System.out.println(currentPlayer.getpHand().getCard(1));
            scan.nextLine();

            // input handling, can replace with UI later
            System.out.println("Input: "); 
            System.out.println("0 to Fold");

            int currentBet = pot.getPlayerBets().get(currentPlayer);

            if (currentBet == pot.getBetToContinue()) { // no one has raised yet
                oneAction = "check";
            } else if (currentPlayer.getValue() <= pot.getBetToContinue() - currentBet){   // if cannot afford to call/raise
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
                //need folded state for player
                currentPlayer.setActive(false);
                numOfFolds++;

                if (numOfFolds == players.length - 1) { //everyone but 1 person folded, end the round
                    int index = 0;
                    for (int i = 0; i < players.length; i++) {
                        if (players[i].getActive() == true) {
                            index = i;
                            break;
                        }
                    }

                    endRound(players[index]);
                    return; //round has ended, dont handle for next turns;
                }
            } else if (input == 1) {
                if (oneAction.equals("all in")) {
                    pot.updateBetToContinue(currentPlayer);
                    currentPlayer.setValue(0);

                    
                } else if (oneAction.substring(0, 4).equals("call")) {
                    pot.updateBetToContinue(currentPlayer);
                    currentPlayer.setValue(pot.getBetToContinue() - currentBet);
                }

                currentPlayer.setActive(false);

            } else if (input == 2) {
                System.out.println("input raise amount");
                int raise = scan.nextInt();
                //raise -= pot.getBetToContinue();
                currentPlayer.setValue(raise - pot.getPlayerBets().get(currentPlayer));
                pot.updateBetToContinue(raise, currentPlayer);
                for (Player player : players) {         //&& player not folded
                    if (currentPlayer.getValue() != 0) { //reset input for players that are still in the round
                        currentPlayer.setActive(true);
                    }
                }
            }

            boolean allPlayed = true;
            for (Player player: players) {
                if (player.getActive() == true) {
                    allPlayed = false;
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
            } else {
                nextPhase();
            }
        }
    }

    public void nextPhase() {
        pot.endTurnPot();

        currentPhase++;
        if (phase[currentPhase].equals("Flop")) {
            //display flop here
        } else if (phase[currentPhase].equals("Turn") || phase[currentPhase].equals("River")) {
            //display turn/river, both draw 1 card from deck
        }

        if (phase[currentPhase].equals("Showdown")) {
            showDown();
        } else {
            currentIndex = firstPlayerIndex;
            startTurn();
        }
    }

    public void showDown() {
        // handle showdown here, compare hand values with flop river and turn
    }

    public void endRound(Player... winner) {
        pot.distributePot();

        for (Player player: players) {
            player.getpHand().discardHand();;
            if (player.getValue() == 0) {
                bankrupted = true;
            }
        }

        //handle win conditions
        if (bankrupted == true) { //or win condition met
            postGame();
        } else {
            //reset stuff to prep for new round
            //clear river flop turn display here
            startRound();
        }

    }

    public void postGame() {
        //handle post game stuff - new game with same ppl or leave, saving
        firstPlayerIndex++;
        if (firstPlayerIndex == players.length) {
            firstPlayerIndex = 0;
        }
    }
}
