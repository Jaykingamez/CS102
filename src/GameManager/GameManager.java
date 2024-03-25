/*
Kong Khai
Last updated: 22/03/2024
*/
package GameManager;

import java.util.*;

//import Controller.GameController;
//import Controller.MainMenuController;
import Entity.Game.*;

public class GameManager {
    private PokerGame game;
    private Player[] players;
    //private GameController ctrl;

    public GameManager(Player[] players) {
        this.players = players;
        game = new PokerGame(players);
    }

    public static void main(String[] args) {
        Player player = new Player("Player 1", new Hand(), null);
        Player bot1 = new Player("Player 2", new Hand(), null);
        Player bot2 = new Player("Player 3", new Hand(), null);
        Player bot3 = new Player("Player 4", new Hand(), null);
        Player[] players = new Player[]{player, bot1, bot2, bot3};
        GameManager manager = new GameManager(players);
        /*MainMenuController mainMenuController = new MainMenuController(manager);
        GameController ctrl = new GameController(manager);*/
        manager.gameStarted();
    }



    public void gameStarted() {
        game.startGame();
    }

    public Player getPlayer() {
        return players[0];
    }

    public Pot getPot(){
        return game.getPot();
    }

    /*public GameController getGameController(){
        return ctrl;
    }*/

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
