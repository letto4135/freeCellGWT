package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import life.client.DeckItems.Card;
import life.client.StorageItems.Column;

class ColumnTests {

	@Test
	void test() {
		Column column = new Column();
		// add initial card, cards after this must be lower and opposite color
		column.addCard(new Card("spades", "5"));
		assertTrue(column.getCard().compareTo(new Card("spades", "5")) == 0);
		// add new red card
		assertTrue(column.addCard(new Card("diamonds", "4")));
		// attempt to add new card of higher value
		assertThrows(IllegalArgumentException.class, () -> column.addCard(new Card("spades", "10")));
		// attempt to add new card of same color
		assertThrows(IllegalArgumentException.class, () -> column.addCard(new Card("hearts", "3")));
		assertTrue(column.addCard(new Card("spades", "3")));
		// 3 cards added, check size
		assertTrue(column.size() == 3);
		// check newest is 3 of spades
		assertTrue(column.getCard().toString().equals("3 of spades"));
		assertTrue(column.removeCard().toString().equals("3 of spades"));
		// after removal check newest is 4 of diamonds
		assertTrue(column.getCard().toString().equals("4 of diamonds"));
		// get array of cards
		Card[] cards = column.getCards();
		// check that first element is 5 of spades and second is 4 of diamonds
		assertTrue(cards[0].toString().equals("5 of spades"));
		assertTrue(cards[1].toString().equals("4 of diamonds"));
	}

}
