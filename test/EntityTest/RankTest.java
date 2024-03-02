package EntityTest;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import Entity.Rank;

public class RankTest {

    @Test
    public void testGetNameReturnsCorrectName() {
        assertEquals("Ace", Rank.ACE.getName());
        assertEquals("Two", Rank.TWO.getName());
        assertEquals("Three", Rank.THREE.getName());
        assertEquals("King", Rank.KING.getName());
    }

    @Test
    public void testGetSymbolReturnsCorrectSymbol() {
        assertEquals("a", Rank.ACE.getSymbol());
        assertEquals("2", Rank.TWO.getSymbol());
        assertEquals("3", Rank.THREE.getSymbol());
        assertEquals("k", Rank.KING.getSymbol());
    }

    @Test
    public void testToStringReturnsName() {
        assertEquals("Ace", Rank.ACE.toString());
        assertEquals("Two", Rank.TWO.toString());
        assertEquals("Three", Rank.THREE.toString());
        assertEquals("King", Rank.KING.toString());
    }

    @Test
    public void testCompareToKingHigh() {
        Rank.setKingHigh();

        assertEquals(-9, Rank.ACE.compareTo(Rank.TEN));
        assertEquals(0, Rank.KING.compareTo(Rank.KING));
        assertEquals(1, Rank.QUEEN.compareTo(Rank.JACK));
    }

    @Test
    public void testCompareToAceHigh() {
        Rank.setAceHigh();
        assertEquals(-10, Rank.TWO.compareTo(Rank.QUEEN));
        assertEquals(0, Rank.ACE.compareTo(Rank.ACE));
        assertEquals(1, Rank.KING.compareTo(Rank.QUEEN));
    }

    @Test
    public void testValuesKingHighContainsAllRanks() {
        Rank.setKingHigh();
        assertEquals(13, Rank.VALUES.size());
        assertTrue(Rank.VALUES.contains(Rank.ACE));
        assertTrue(Rank.VALUES.contains(Rank.TWO));
        assertTrue(Rank.VALUES.contains(Rank.KING));
    }

    @Test
    public void testValuesAceHighContainsAllRanks() {
        Rank.setAceHigh();
        assertEquals(13, Rank.VALUES.size());
        assertTrue(Rank.VALUES.contains(Rank.ACE));
        assertTrue(Rank.VALUES.contains(Rank.TWO));
        assertTrue(Rank.VALUES.contains(Rank.KING));
    }

    @Test
    public void testValuesIsUnmodifiable() {
        assertThrows(UnsupportedOperationException.class, () -> Rank.VALUES.add(Rank.ACE));
    }
}

