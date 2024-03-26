package Entity.Game;

import java.util.Map;
import java.util.Random;

import GameManager.PokerGame;

public class BotMoves {
    // JL added 24/03/2024
    // using the total combi class to gauge how much the AI will bet
    public int botPlayerMoves(BotPlayer p, Pot pot, PokerGame game) {
        totalCombi gauge = new totalCombi(p, game.river);
        Map<Integer, Integer> freqmap = gauge.numSameValue();
        int playerBets = pot.getBetToContinue();

        // Create a random number generator
        Random random = new Random();

        // if bot gets flush or more
        if (gauge.getTier(freqmap) >= 5) {

            if (playerBets == 0) {
                // AI raises or checks
                double bias = 0.7; // 70% chance of landing raise
                // Generate a random number between 0 and 1
                double randomNumber = random.nextDouble();
                int result = (randomNumber < bias) ? 2 : 1;

                return result;
            } else {
                // AI calls or raise
                double bias = 0.8; // 80% chance of landing call

                // Generate a random number between 0 and 1
                double randomNumber = random.nextDouble();
                int result = (randomNumber < bias) ? 1 : 2;

                return result;
            }

        } else if (gauge.getTier(freqmap) >= 1) {
            if (playerBets == 0) {
                // AI checks or raises
                double bias = 0.4; // 40% chance of landing raise

                // Generate a random number between 0 and 1
                double randomNumber = random.nextDouble();
                int result = (randomNumber < bias) ? 2 : 1;

                return result;
            } else {
                // AI folds or call
                double bias = 0.6; // 60% chance of landing call

                // Generate a random number between 0 and 1
                double randomNumber = random.nextDouble();
                int result = (randomNumber < bias) ? 1 : 0;

                return result;
            }

        } else {
            if (playerBets == 0) {
                // AI checks or raises
                // AI raises or calls
                double bias = 0.5; // 50% chance of landing fold

                // Generate a random number between 0 and 1
                double randomNumber = random.nextDouble();

                // Simulate coin flip based on bias
                int result = (randomNumber < bias) ? 0 : 1;

                return result;
            } else {
                // AI folds or call
                double bias = 0.5; // 50% chance of landing call

                // Generate a random number between 0 and 1
                double randomNumber = random.nextDouble();
                int result = (randomNumber < bias) ? 1 : 0;

                return result;
            }
        }
    }

    // JL added 24/03/2024
    // only need to call this is bot raises
    public int botPlayerRaise(BotPlayer p, Pot pot) {
        Random random = new Random();
        int randomBet = random.nextInt(301) + 50;
        return randomBet;
    }


}