package life.client.StorageItems;

import life.client.DeckItems.Card;

/**
 * Represents a FreeCell in the game FreeCell
 * @author Alex Life
 *
 */
public class FreeCell extends Storage {

	/**
	 * Instantiates a FreeCell class
	 */
	public FreeCell() {
		super();
	}
	
	@Override
	public boolean addCard(Card card) {
		if(storage.isEmpty()) {
			storage.push(card);
			return true;
		}
		
		throw new IllegalArgumentException("FreeCell can only have one card.");
	}
}
