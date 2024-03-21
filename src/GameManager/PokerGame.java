package GameManager;
import java.util.ArrayList;
import java.util.Scanner;

public class PokerGame {
    private final int hand_size = 2;
    Deck deck = new Deck(false);
    Player[] players; //reference to players to save data after game
    ArrayList<PokerPlayer> gamers = new ArrayList<>(); //players game data
    Scanner scan = new Scanner(System.in);
    int pot = 0;
    int initialBet = 5;             // game's initial bet every round
    int roundBet = initialBet;     // round's current bet, updated after someone raises
    private int firstPlayerIndex = 0;
    PokerPlayer currentPlayer = gamers.get(firstPlayerIndex);
    String oneAction = "check";     // current available action for inputting 1
    String currentPhase = "preFlop";    //poker game phase

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
        deck.shuffle();

        for (PokerPlayer player: gamers) {
            player.hand.addCard(deck.dealCard());
            player.hand.addCard(deck.dealCard());

            if (player.getCurrentChips() < initialBet) {
                pot += player.getCurrentChips();
                player.setCurrentChips(0);
                player.allIn = true;       //if player has less than initial bet, basically force them to all in at start
            }
            else {
                pot += initialBet;
                player.setCurrentChips(player.getCurrentChips() - initialBet);
                player.currentBet = initialBet;
            }
        }

        roundBet = initialBet;
        startTurn();
    }

    public void startTurn() {
        // replace scanning and printing with actual UI


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

            currentPlayer.finishedTurn = true;
            currentPlayer.finishedRound = true;
            firstPlayerIndex++;
            if (firstPlayerIndex == 4) {
                firstPlayerIndex = 0;
            }

            currentPlayer = gamers.get(firstPlayerIndex);
            startTurn();
        } else if (currentPlayer.finishedRound == false) {
            System.out.println(currentPlayer.getName() + "\'s turn.");
            System.out.println(currentPlayer.getName() + "\'s cards: ");
            System.out.println(currentPlayer.hand.getCard(0));  //prints cards in hand
            System.out.println(currentPlayer.hand.getCard(1));
            scan.nextLine();

            System.out.println("Input: "); 
            System.out.println("0 to Fold");

            if (currentPlayer.currentBet == roundBet) { // no one has raised yet
                oneAction = "check";
            } else if (currentPlayer.currentChips <= roundBet - currentPlayer.currentBet){   // if cannot afford to call/raise
                oneAction = "all in";
            } else {
                oneAction = "call";
            }

            System.out.println("1 to " + oneAction);
            if (!(oneAction.equals("all in"))) {
                System.out.println("2 to raise");
            }

            int input = scan.nextInt();
            if (input == 0) {
                currentPlayer.finishedTurn = true;
                currentPlayer.finishedRound = true;
            } else if (input == 1) {

            } else if (input == 2) {
                System.out.println("input raise amount");
                int raise = scan.nextInt();
            }
        }
    }

}
