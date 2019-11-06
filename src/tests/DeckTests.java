package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import life.client.DeckItems.Card;
import life.client.DeckItems.Deck;

class DeckTests {

	@Test
	void test() {
		System.out.println("Testing Deck class\n\tTesting getDeckProperties()");
		// create deck and properties of deck
		Deck deck = new Deck();
		ArrayList<String> props = deck.getDeckProperties();
//		int size = deck.deckSize();
//		for(int i = 0; i < size; i++) {
//			System.out.println(deck.deal().toString());
//		}
//		
//		deck = new Deck();
		
		// test size of deck
		System.out.println("\t\tMaking sure the size of the deck is 52.");
		System.out.println("\t\t\t" + props.get(0));
		assertTrue(props.get(0).equals("52"));
		
		// test first card created is second element in properties
		System.out.println("\t\tMaking sure second value in deck properties is the first card created.");
		System.out.println("\t\t\t" + props.get(1));
		assertTrue(props.get(1).equals("2 of diamonds"));
		
		// shuffle deck and reassign properties
		deck.shuffle();
		props = deck.getDeckProperties();
		
		// test that size of deck is still 52
		System.out.println("\t\tMaking sure the size of the deck is 52 after shuffle.");
		System.out.println("\t\t\t" + props.get(0));
		assertTrue(props.get(0).equals("52"));
		// not really a way to assert that it is shuffled so just going to print the deck now..
		System.out.println("\t\tTesting that the deck is shuffled using first 5 cards.");
		for(int i = 1; i < 6; i++) {
			System.out.println("\t\t\t" + props.get(i));
		}
		
		// test that dealing a card removes it from the deck and decrements size by 1
		String firstCard = props.get(1);
		Card dealtCard = deck.deal();
		// firstCard should equal the card at the 0 index in the deck. Saving it to compare
		// dealtCard with firstCard to make sure that it is the same card.
		System.out.println("\t\tCard has been dealt, check to make sure that card dealt is indeed the first card in the deck");
		System.out.println("\t\t\tFirst card: " + firstCard + " dealtCard: " + dealtCard.toString());
		assertTrue(firstCard.equals(dealtCard.toString()));
		System.out.println("\t\tDeck should now have 51 cards, checking that that is true.");
		props = deck.getDeckProperties();
		System.out.println("\t\t\t" + props.get(0));
		assertTrue(props.get(0).equals("51"));
		
		// tests of deck properties have confirmed shuffle and deal
		System.out.println("\tTesting of deck properties has confirmed shuffle and deal as well.");
		System.out.println("Tests complete");
	}

}
