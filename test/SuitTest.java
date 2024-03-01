import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import Entity.Suit;

public class SuitTest {

    @Test
    public void testGetNameReturnsCorrectName() {
        assertEquals("Clubs", Suit.CLUBS.getName());
        assertEquals("Diamonds", Suit.DIAMONDS.getName());
        assertEquals("Hearts", Suit.HEARTS.getName());
        assertEquals("Spades", Suit.SPADES.getName());
    }

    @Test
    public void testGetSymbolReturnsCorrectSymbol() {
        assertEquals("c", Suit.CLUBS.getSymbol());
        assertEquals("d", Suit.DIAMONDS.getSymbol());
        assertEquals("h", Suit.HEARTS.getSymbol());
        assertEquals("s", Suit.SPADES.getSymbol());
    }

    @Test
    public void testToStringReturnsName() {
        assertEquals("Clubs", Suit.CLUBS.toString());
        assertEquals("Diamonds", Suit.DIAMONDS.toString());
        assertEquals("Hearts", Suit.HEARTS.toString());
        assertEquals("Spades", Suit.SPADES.toString());
    }

    @Test
    public void testCompareToSortsSuitsCorrectly() {
        assertEquals(-1, Suit.CLUBS.compareTo(Suit.DIAMONDS));
        assertEquals(0, Suit.DIAMONDS.compareTo(Suit.DIAMONDS));
        assertEquals(1, Suit.SPADES.compareTo(Suit.HEARTS));
    }

    @Test
    public void testValuesContainsAllSuits() {
        assertEquals(4, Suit.VALUES.size());
        assertTrue(Suit.VALUES.contains(Suit.CLUBS));
        assertTrue(Suit.VALUES.contains(Suit.DIAMONDS));
        assertTrue(Suit.VALUES.contains(Suit.HEARTS));
        assertTrue(Suit.VALUES.contains(Suit.SPADES));
    }

    @Test
    public void testValuesIsUnmodifiable() {
        assertThrows(UnsupportedOperationException.class, () -> Suit.VALUES.add(Suit.CLUBS));
    }
}

