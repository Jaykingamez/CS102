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

    public GameManager(Player[] players) {
        this.players = players;
        game = new PokerGame(players);
    }

    public static void main(String[] args) {
        Player player = new Player("Tester", null, null, 0, null);
        Player bot1 = new BotPlayer("Bot 1", null, null);
        Player bot2 = new BotPlayer("Bot 2", null, null);
        Player bot3 = new BotPlayer("Bot 3", null, null);
        Player[] players = new Player[]{tester, bot1, bot2, bot3};
        GameManager manager = new GameManager(players);
        MainMenuController mainMenuController = new MainMenuController(manager);
        //GameController ctrl = new GameController(manager);
        //manager.gameStarted(ctrl);
    }



    public void gameStarted(GameController ctrl) {
        game.startGame();
    }

    public Player getPlayer() {
        return players[0];
    }

    public Pot getPot(){
        return game.getPot();
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
