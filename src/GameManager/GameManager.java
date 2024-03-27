/*
Kong Khai
Last updated: 22/03/2024
Si Jie
Last Updated: 26/3/2024
*/
package GameManager;
import Entity.Game.*;

public class GameManager {
    private PokerGame game;

    public void gameStarted(String playerName) {
        Player player = new Player(playerName, new Hand(), null);
        Player bot1 = new BotPlayer("Player 2", new Hand());
        Player bot2 = new BotPlayer("Player 3", new Hand());
        Player bot3 = new BotPlayer("Player 4", new Hand());
        Player[] players = new Player[]{player, bot1, bot2, bot3};

        game = new PokerGame(players);
        game.startGame();
    }

    public Pot getPot(){
        return game.getPot();
    }


}
