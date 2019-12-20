package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import life.client.DeckItems.Card;
import life.client.StorageItems.FreeCell;

class FreeCellTests {

	@Test
	void test() {
		FreeCell f = new FreeCell();
		
		// insert initial card into freecell
		assertTrue(f.addCard(new Card("diamonds", "2")));
		// inserting second card should throw error
		assertThrows(IllegalArgumentException.class, () -> f.addCard(new Card("diamonds", "3")));
		assertThrows(IllegalArgumentException.class, () -> f.addCard(new Card("diamonds", "2")));
		// check size
		assertTrue(f.size() == 1);
		// remove card and check size == 0
		assertTrue(f.removeCard().toString().equals("2 of diamonds"));
		assertTrue(f.size() == 0);
		// add new card to the freecell
		assertTrue(f.addCard(new Card("clubs", "5")));
		assertTrue(f.getCard().toString().equals("5 of clubs"));
	}

}
