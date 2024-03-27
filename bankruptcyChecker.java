import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class PotTest {

    @Test
    public void testBankruptcyWithOnePlayer() {
        // Create a single player with an initial amount of 10
        Player player = new Player();
        player.setAmount(10);

        // Create a Pot with only this player
        List<Player> players = new ArrayList<>();
        players.add(player);
        Pot pot = new Pot(players);

        // Simulate player going all in and losing
        pot.updateBetToContinuePoor(10, player);
        pot.endTurnPot();

        // Check that the player's amount is now 0
        assertEquals(0, player.getAmount());
    }

    @Test
    public void bankruptcyChecker() {
        // Create two players with an initial amount of 20 each
        Player player1 = new Player();
        player1.setAmount(20);

        Player player2 = new Player();
        player2.setAmount(20);

        // Create a Pot with these players
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        Pot pot = new Pot(players);

        // Simulate player 1 going all in and losing
        pot.updateBetToContinuePoor(20, player1);
        pot.endTurnPot();

        // Check that player 1's amount is now 0
        assertEquals(0, player1.getAmount());

        // Simulate player 2 going all in and losing
        pot.updateBetToContinuePoor(20, player2);
        pot.endTurnPot();

        // Check that player 2's amount is now 0
        assertEquals(0, player2.getAmount());
    }
}
