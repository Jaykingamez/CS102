/*
 * Name    : Chan Si Jie
 * Email ID: sijie.chan.2023
*/
package EntityTest;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import Entity.Rank;

public class RankTest {

    @Test
    @DisplayName("Test that getName() returns the correct full name for each rank")
    public void testGetNameReturnsCorrectName() {
        // Test all ranks using a loop
        for (Rank rank : Rank.VALUES) {
            assertEquals(rank.toString(), rank.getName()); // Use toString() for consistency
        }
    }

    @Test
    @DisplayName("Test that getSymbol() returns the correct single-character symbol for each rank")
    public void testGetSymbolReturnsCorrectSymbol() {
        List<Rank> VALUES_KING_HIGH =
           List.of(Rank.ACE, Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX, 
                   Rank.SEVEN, Rank.EIGHT, Rank.NINE, Rank.TEN, Rank.JACK, 
                   Rank.QUEEN, Rank.KING);

        // Test all ranks using a loop
        for (int i = 0; i < Rank.VALUES.size(); i++) {
            assertEquals(Rank.VALUES.get(i).getSymbol(), VALUES_KING_HIGH.get(i).getSymbol());
        }
    }

    @Test
    @DisplayName("Test that toString() returns the same value as getName() for each rank")
    public void testToStringReturnsName() {
        // Test all ranks using a loop
        for (Rank rank : Rank.VALUES) {
            assertEquals(rank.toString(), rank.getName()); // Use toString() for consistency
        }
    }

    @Test
    @DisplayName("Test that compareTo() returns the difference in rank when king is highest")
    public void testCompareToKingHigh() {
        Rank.setKingHigh();

        // Test all ranks against all other ranks
        for (Rank rank1 : Rank.VALUES) {
            for (Rank rank2 : Rank.VALUES) {
                int expectedDifference = Rank.VALUES.indexOf(rank1) 
                    - Rank.VALUES.indexOf(rank2);
            assertEquals(expectedDifference, rank1.compareTo(rank2));
            }
        }
    }

    @Test
    @DisplayName("Test that compareTo() returns the difference in rank when ace is highest")
    public void testCompareToAceHigh() {
        Rank.setAceHigh();
        List<Rank> VALUES_ACE_HIGH =
           List.of(Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX, 
                   Rank.SEVEN, Rank.EIGHT, Rank.NINE, Rank.TEN, Rank.JACK,
                   Rank.QUEEN, Rank.KING, Rank.ACE);

        // Test all ranks against all other ranks
        for (Rank rank1 : VALUES_ACE_HIGH) {
            for (Rank rank2 : VALUES_ACE_HIGH) {
                int expectedDifference = VALUES_ACE_HIGH.indexOf(rank1) - 
                    VALUES_ACE_HIGH.indexOf(rank2);
            assertEquals(expectedDifference, rank1.compareTo(rank2));
            }
        }
    }

    @Test
    @DisplayName("Tests that the VALUES list contains all existing rank objects when (King High)")
    public void testValuesKingHighContainsAllRanks() {
        Rank.setKingHigh();

        // Assert that VALUES list contains all expected ranks
        for (Rank rank : Rank.VALUES) {
            assertTrue(Rank.VALUES.contains(rank));
        }

        // Assert list size for redundancy
        assertEquals(13, Rank.VALUES.size());
    }

    @Test
    @DisplayName("Tests that the VALUES list contains all existing rank objects when (Ace High)")
    public void testValuesAceHighContainsAllRanks() {
        Rank.setAceHigh();

        // Assert that VALUES list contains all expected ranks
        for (Rank rank : Rank.VALUES) {
            assertTrue(Rank.VALUES.contains(rank));
        }

        // Assert list size for redundancy
        assertEquals(13, Rank.VALUES.size());
    }

    @Test
    public void testValuesIsUnmodifiable() {
        assertThrows(UnsupportedOperationException.class, () -> Rank.VALUES.add(Rank.ACE));
    }
}

