package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import life.client.DeckItems.Card;
import life.client.StorageItems.Foundation;

class FoundationTests {

	@Test
	void test() {
		Foundation f = new Foundation();
		// adding cards that are not ace(1/14) should throw error
		assertThrows(IllegalArgumentException.class, () -> f.addCard(new Card("diamonds", "5")));
		assertThrows(IllegalArgumentException.class, () -> f.addCard(new Card("diamonds", "2")));
		// add ace, card is made with value 14 but converts to 1(ace) in creation.
		assertTrue(f.addCard(new Card("diamonds", "14")));
		// add next card of different suit should throw error
		assertThrows(IllegalArgumentException.class, () -> f.addCard(new Card("clubs", "2")));
		// add card of same suit but out of order should throw error
		assertThrows(IllegalArgumentException.class, () -> f.addCard(new Card("diamonds", "5")));
		// add next could cards
		assertTrue(f.addCard(new Card("diamonds", "2")));
		assertTrue(f.addCard(new Card("diamonds", "3")));
		// check 3 cards are in foundation
		assertTrue(f.size() == 3);
		// check top card is 3 of diamonds
		assertTrue(f.getCard().toString().equals("3 of diamonds"));
		// remove card and check size and top card again
		assertTrue(f.removeCard().toString().equals("3 of diamonds"));
		assertTrue(f.size() == 2);
		assertTrue(f.getCard().toString().equals("2 of diamonds"));
	}

}
