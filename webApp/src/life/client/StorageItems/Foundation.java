package life.client.StorageItems;

import life.client.DeckItems.Card;

/**
 * Represents a foundation in FreeCell
 * @author Alex Life
 *
 */
public class Foundation extends Storage {

	/**
	 * Instantiates a Foundation class
	 */
	public Foundation() {
		super();
	}
	
	@Override
	public boolean addCard(Card card) {
		// if top card is smaller than card and top card face == card
		// and card value - 1 == top card value (meaning card being added is next card in order)
		if(storage.size() != 0) {
			if(storage.peek().compareTo(card) < 0 &&
			storage.peek().getFace().equals(card.getFace()) &&
			Integer.parseInt(storage.peek().getValue()) == Integer.parseInt(card.getValue()) - 1) {
				
				storage.push(card);
				return true;
			}
		}else if(storage.size() == 0 && card.getValue().equals("1") || card.getValue().equals("14")) {
			storage.push(card);
			return true;
		}
		
		throw new IllegalArgumentException("That card cannot be placed there.");
	}

}
