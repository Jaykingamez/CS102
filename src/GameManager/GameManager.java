/*
Kong Khai
Last updated: 22/03/2024
Si Jie
Last Updated: 26/3/2024
*/
package GameManager;
import java.util.*;

import Entity.Game.*;

public class GameManager {
    private PokerGame game;

    public void gameStarted(String playerName, int humanPlayers, int botPlayers) {
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < humanPlayers; i++){
            players.add(new Player(playerName + i, new Hand(), null));
        }

        for (int i = 0; i < botPlayers; i++){
            players.add(new BotPlayer("Bot " + i, new Hand()));
        }

        game = new PokerGame(players);
        game.startGame();
    }

    public Pot getPot(){
        return game.getPot();
    }
}
