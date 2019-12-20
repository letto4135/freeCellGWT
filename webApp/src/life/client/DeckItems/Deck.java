package life.client.DeckItems;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a deck of cards for playing a card game.
 * @author Alex Life
 *
 */
public class Deck {
	private ArrayList<Card> deck = new ArrayList<Card>();
	
	/**
	 * Creates a deck of 52 playing cards, using suits and values to identify cards.
	 */
	public Deck() {
		Suits suits[] = Suits.values();
		
		for(int i = 0; i < suits.length; i++) {
			for(int j = 2; j < 15; j++) {
				// add to the deck a card where the suit is
				// = to suits[i].toString and value is = to values[j].toString
				deck.add(new Card(suits[i].toString(), String.valueOf(j)));
			}
		}
	}
	
	/**
	 * Shuffles the deck to randomize order of cards.
	 */
	public void shuffle() {
		Collections.shuffle(deck);
		Collections.shuffle(deck);
		Collections.shuffle(deck);
		// Because you can never get a new deck shuffled very well on the first try.
	}
	
	/**
	 * Deals a card from the deck
	 * @return the top card on the deck.
	 */
	public Card deal() {
		return deck.remove(0);
	}
	
	public int deckSize() {
		return deck.size();
	}
	
	/**
	 * For testing purposes
	 * @return ArrayList of properties; deck size at element 0 followed by each card in the deck
	 */
	public ArrayList<String> getDeckProperties() {
		ArrayList<String> props = new ArrayList<String>();
		
		props.add(String.valueOf(deck.size()));
		for(int i = 0; i < deck.size(); i++) {
			props.add(deck.get(i).toString());
		}
		
		return props;
	}
}

