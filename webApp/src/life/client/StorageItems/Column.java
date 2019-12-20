package life.client.StorageItems;

import life.client.DeckItems.Card;

/**
 * Represents a column in the game FreeCell
 * @author Alex Life
 *
 */
public class Column extends Storage {
	
	/**
	 * Instantiates a new storage column.
	 */
	public Column() {
		super();
	}

	@Override
	public boolean addCard(Card card) throws IllegalArgumentException {
		// if storage size != 0, make sure card is less than storage top card
		// and storage card value == card value + 1
		// and storage card color is not cards color
		if(storage.size() != 0 
		&& storage.peek().compareTo(card) > 0 
		&& Integer.parseInt(storage.peek().getValue()) == Integer.parseInt(card.getValue()) + 1
		&& !storage.peek().getColor().equals(card.getColor())) {		
			storage.push(card);
			return true;
		}else if(storage.size() == 0) {
			storage.push(card);
			return true;
		}
		
		throw new IllegalArgumentException("That card cannot be placed there.");
	}

	/**
	 * Adds a card to the instance, regardless of whether it is a duplicate card.
	 * @param card Card to add
	 */
	public void creation(Card card) {
		storage.add(card);
	}
}
