/* EntityTest.SuitTest.java - Chan Si Jie - 2 March 2024
 * Implementation of Unit Testing Suit.java
 */
package EntityTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import Entity.Suit;

public class SuitTest {

    @Test
    @DisplayName("Test that getName() returns the correct name for each suit")
    public void testGetNameReturnsCorrectName() {
        assertEquals("Clubs", Suit.CLUBS.getName());
        assertEquals("Diamonds", Suit.DIAMONDS.getName());
        assertEquals("Hearts", Suit.HEARTS.getName());
        assertEquals("Spades", Suit.SPADES.getName());
    }

    @Test
    @DisplayName("Test that getSymbol() returns the correct symbol for each suit")
    public void testGetSymbolReturnsCorrectSymbol() {
        assertEquals("c", Suit.CLUBS.getSymbol());
        assertEquals("d", Suit.DIAMONDS.getSymbol());
        assertEquals("h", Suit.HEARTS.getSymbol());
        assertEquals("s", Suit.SPADES.getSymbol());
    }

    @Test
    @DisplayName("Test that toString() returns the name of the suit")
    public void testToStringReturnsName() {
        assertEquals("Clubs", Suit.CLUBS.toString());
        assertEquals("Diamonds", Suit.DIAMONDS.toString());
        assertEquals("Hearts", Suit.HEARTS.toString());
        assertEquals("Spades", Suit.SPADES.toString());
    }

    @Test
    @DisplayName("Test that compareTo() sorts suits in their natural order " +
            "(CLUBS < DIAMONDS < HEARTS < SPADES)")
    public void testCompareToSortsSuitsCorrectly() {
        // Test CLUBS against the other suits
        assertEquals(0, Suit.CLUBS.compareTo(Suit.CLUBS));
        assertEquals(-1, Suit.CLUBS.compareTo(Suit.DIAMONDS));
        assertEquals(-2, Suit.CLUBS.compareTo(Suit.HEARTS));
        assertEquals(-3, Suit.CLUBS.compareTo(Suit.SPADES));

        // Test DIAMONDS against the other suits
        assertEquals(1, Suit.DIAMONDS.compareTo(Suit.CLUBS));
        assertEquals(0, Suit.DIAMONDS.compareTo(Suit.DIAMONDS));
        assertEquals(-1, Suit.DIAMONDS.compareTo(Suit.HEARTS));
        assertEquals(-2, Suit.DIAMONDS.compareTo(Suit.SPADES));

        // Test HEARTS against the other suits
        assertEquals(2, Suit.HEARTS.compareTo(Suit.CLUBS));
        assertEquals(1, Suit.HEARTS.compareTo(Suit.DIAMONDS));
        assertEquals(0, Suit.HEARTS.compareTo(Suit.HEARTS));
        assertEquals(-1, Suit.HEARTS.compareTo(Suit.SPADES));

        // Test SPADES against the other suits
        assertEquals(3, Suit.SPADES.compareTo(Suit.CLUBS));
        assertEquals(2, Suit.SPADES.compareTo(Suit.DIAMONDS));
        assertEquals(1, Suit.SPADES.compareTo(Suit.HEARTS));
        assertEquals(0, Suit.SPADES.compareTo(Suit.SPADES));
    }

    @Test
    @DisplayName("Test that the VALUES array contains all four suits")
    public void testValuesContainsAllSuits() {
        assertEquals(4, Suit.VALUES.size());
        assertTrue(Suit.VALUES.contains(Suit.CLUBS));
        assertTrue(Suit.VALUES.contains(Suit.DIAMONDS));
        assertTrue(Suit.VALUES.contains(Suit.HEARTS));
        assertTrue(Suit.VALUES.contains(Suit.SPADES));
    }

    @Test
    @DisplayName("Test that the VALUES array is unmodifiable")
    public void testValuesIsUnmodifiable() {
        assertThrows(UnsupportedOperationException.class, () -> Suit.VALUES.add(Suit.CLUBS));
    }
}

