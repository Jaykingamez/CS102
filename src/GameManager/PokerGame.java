package GameManager;
import java.util.ArrayList;
import java.util.Scanner;

public class PokerGame {
    Deck deck = new Deck(false);
    Player[] players; //reference to players to save data after game
    ArrayList<PokerPlayer> gamers = new ArrayList<>(); //players game data
    Scanner scan = new Scanner(System.in);
    int pot = 0;
    int initialBet = 5;             // game's initial bet every round
    int roundBet = initialBet;     // round's current bet, updated after someone raises
    private int firstPlayerIndex = 0; // first player at start of a new round
    int currentIndex = firstPlayerIndex; //current player's index
    PokerPlayer currentPlayer = gamers.get(currentIndex);
    String oneAction = "check";     // current available action for inputting 1
    String[] phase = {"preFlop", "Flop", "Turn", "River", "Showdown"}; //poker game phases
    int currentPhase = 0;    //poker game phase index
    int numOfFolds = 0;     //number of players folded
    boolean bankrupted = false; //tracks if player has lost all chips n therefore cannot play anymore

    public PokerGame(Player[] players) {    // construct game with set of players
        this.players = players;
    }

    public void startGame() {
        for (Player player : players) {
            gamers.add(new PokerPlayer("yeow", 200)); //player.getname(), game's initial chip in
        }
        startRound();
    }

    public void startRound() {
        deck = new Deck(false);
        deck.shuffle();

        for (PokerPlayer player: gamers) {
            player.hand.addCard(deck.dealCard());
            player.hand.addCard(deck.dealCard());

            if (player.getCurrentChips() < initialBet) {
                player.currentBet = player.currentChips;
                player.setCurrentChips(0);
                player.allIn = true;       //if player has less than initial bet, basically force them to all in at start
            }
            else {
                player.currentBet = initialBet;
                player.setCurrentChips(player.getCurrentChips() - initialBet);
            }
        }

        currentPlayer = gamers.get(firstPlayerIndex);
        currentIndex = firstPlayerIndex;
        roundBet = initialBet;
        startTurn();
    }

    public void startTurn() {
        // replace scanning and printing with actual UI
        //TODO: handle every player has played thru currentPlayer.playedTurn

        if (currentPlayer.allIn == true) {
            if (currentPlayer.lastStandAcknowledge == false) {      //for if player is all-in from initial bet
                System.out.println(currentPlayer.getName() + "\'s turn.");
                System.out.println(currentPlayer.getName() + "\'s cards: ");
                System.out.println(currentPlayer.hand.getCard(0));  //prints cards in hand
                System.out.println(currentPlayer.hand.getCard(1));

                System.out.println("All in, press enter to continue");
                scan.nextLine();
                currentPlayer.lastStandAcknowledge = true;
            }

            currentPlayer.playedTurn = true;
            currentPlayer.allIn = true;
            currentPlayer.finishedRound = true;
        } else if (currentPlayer.finishedRound == false) {
            System.out.println(currentPlayer.getName() + "\'s turn.");
            System.out.println(currentPlayer.getName() + "\'s cards: ");
            System.out.println(currentPlayer.hand.getCard(0));  //prints cards in hand
            System.out.println(currentPlayer.hand.getCard(1));
            scan.nextLine();

            // input handling, can replace with UI later
            System.out.println("Input: "); 
            System.out.println("0 to Fold");

            if (currentPlayer.currentBet == roundBet) { // no one has raised yet
                oneAction = "check";
            } else if (currentPlayer.currentChips <= roundBet - currentPlayer.currentBet){   // if cannot afford to call/raise
                oneAction = "all in";
            } else {
                oneAction = "call (" + (roundBet - currentPlayer.currentBet) + " chips)";
            }

            System.out.println("1 to " + oneAction);
            if (!(oneAction.equals("all in"))) {
                System.out.println("2 to raise");
            }

            int input = scan.nextInt();
            if (input == 0) {
                currentPlayer.playedTurn = true;
                currentPlayer.finishedRound = true;
                currentPlayer.folded = true;
                
                numOfFolds++;
                if (numOfFolds == gamers.size() - 1) { //everyone but 1 person folded, end the round
                    int index = 0;
                    for (int i = 0; i < gamers.size(); i++) {
                        if (gamers.get(i).folded == false) {
                            index = i;
                            break;
                        }
                    }

                    endRound(gamers.get(index));
                    return; //round has ended, dont handle for next turns;
                }
            } else if (input == 1) {
                if (oneAction.equals("all in")) {
                    pot += currentPlayer.currentChips;
                    currentPlayer.currentBet = currentPlayer.currentChips;
                    currentPlayer.currentChips = 0;

                    currentPlayer.finishedRound = true;
                    currentPlayer.allIn = true;
                } else if (oneAction.substring(0, 4).equals("call")) {
                    pot += roundBet - currentPlayer.currentBet;
                    currentPlayer.currentChips -= roundBet - currentPlayer.currentBet;
                    currentPlayer.currentBet = roundBet;
                }

                currentPlayer.playedTurn = true;

            } else if (input == 2) {
                System.out.println("input raise amount");
                int raise = scan.nextInt();
                roundBet = raise;
                pot += raise;
                currentPlayer.currentChips -= raise;
                for (PokerPlayer player : gamers) {
                    if (currentPlayer.finishedRound == false) { //reset input for players that are still in the round
                        currentPlayer.playedTurn = false;
                    }
                }
            }

            //handling for next turn
            currentIndex++;
            if (currentIndex == gamers.size()) { //equals num of players
                currentIndex = 0;
            }

            currentPlayer = gamers.get(currentIndex);
            startTurn();
        }
    }

    public void nextPhase() {
        for (PokerPlayer player: gamers) {  //put all betted chips into pot
            if (player.currentBet != 0) {
                pot += player.currentBet;
                player.currentBet = 0;
            }
        }

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

    public void endRound(PokerPlayer... winner) {
        if (winner.length == 1) {
            winner[0].currentChips += pot;
        } else {
            for (int i = 0; i < winner.length; i++) {   // divide pot among multiple winners, currently just split evenly
                winner[i].currentChips += pot / winner.length;
            }
        }

        for (PokerPlayer player: gamers) {
            player.hand.clear();
            if (player.currentChips == 0) {
                bankrupted = true;
            }
        }

        //handle win conditions
        if (bankrupted == true) { //or win condition met
            postGame();
        } else {
            //reset stuff to prep for new round
            //clear river flop turn display here
            pot = 0;
            startRound();
        }

    }

    public void postGame() {
        //handle post game stuff - new game with same ppl or leave, saving
        firstPlayerIndex++;
        if (firstPlayerIndex == gamers.size()) {
            firstPlayerIndex = 0;
        }
    }
}