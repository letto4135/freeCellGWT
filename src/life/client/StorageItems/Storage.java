package life.client.StorageItems;

import java.util.Stack;

import life.client.DeckItems.Card;

/**
 * Represents a storage unit for playing cards
 * @author Alex Life
 *
 */
public abstract class Storage implements IStorage {
	// Other classes in this package need access to storage to perform comparisons
	protected Stack<Card> storage = new Stack<Card>();
	
	// Needs defined more specifically by classes implementing this method
	public abstract boolean addCard(Card card);
	
	public Card removeCard() {
		if(storage.size() != 0) {
			return storage.pop();
		}
		
		throw new IllegalArgumentException("No elements to remove");
	}
	
	public Card getCard() {
		if(storage.size() != 0) {
			return storage.peek();
		}
		
		throw new IllegalArgumentException("No elements to remove");
	}
	
	public Card[] getCards() {
		return storage.toArray(new Card[1]);
	}
	
	public int size() {
		return storage.size();
	}
}
