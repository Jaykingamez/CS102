/*
Kong Khai
Last updated: 22/03/2024
*/
package GameManager;

import java.util.*;

import Controller.GameController;
import Controller.MainMenuController;
import Entity.*;

public class GameManager {
    private PokerGame game;
    private Player[] players;
    private GameController ctrl;

    public GameManager(Player[] players) {
        this.players = players;
    }

    public void startGame(){
        Player player = new Player("Tester", null, null, 0, null);
        Player bot1 = new BotPlayer("Bot 1", null, null);
        Player bot2 = new BotPlayer("Bot 2", null, null);
        Player bot3 = new BotPlayer("Bot 3", null, null);
        Player[] players = new Player[]{player, bot1, bot2, bot3};
        GameManager manager = new GameManager(players);
        ctrl = new GameController(manager);
        game = new PokerGame(players, ctrl);
        game.startGame();
    }

    public Player getPlayer() {
        return players[0];
    }

    public Pot getPot(){
        return game.getPot();
    }

    public GameController getGameController(){
        return ctrl;
    }

    public List<Card> getTableCards() {
        return null;
    }

    public boolean showFold() {
        return false;
    }

    public boolean showCheck() {
        return false;
    }

    public boolean showRaise() {
        return false;
    }

    public boolean showCall() {
        return false;
    }
    //fill in game inputs
}
