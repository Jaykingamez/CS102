/*
Kong Khai
Last updated: 22/03/2024
*/
package GameManager;

import Controller.GameController;
import Entity.*;

public class GameManager {
    private PokerGame game;

    public GameManager(Player[] players) {
        game = new PokerGame(players);
    }

    public static void main(String[] args) {
        Player tester = new Player("Tester", null, null, null, null);
        Player bot1 = new BotPlayer("Bot 1", null, null);
        Player bot2 = new BotPlayer("Bot 2", null, null);
        Player bot3 = new BotPlayer("Bot 3", null, null);
        Player[] players = new Player[]{tester, bot1, bot2, bot3};
        GameManager manager = new GameManager(players);
        GameController ctrl = new GameController(manager);
        manager.gameStarted(ctrl);
    }



    public void gameStarted(GameController ctrl) {
        game.startGame();
    }

    public Player getPlayer() {
        return null;
    }

    public Card[] getTableCards() {
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
