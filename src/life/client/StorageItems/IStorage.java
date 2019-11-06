package life.client.StorageItems;

import life.client.DeckItems.Card;

/**
 * Interface for a storage class to hold playing cards
 * @author Alex Life
 *
 */
public interface IStorage {
	
	/**
	 * Adds a card to this instance.
	 * @param card Card to be added
	 * @return true if card is added
	 * @throws thrown if the card cannot be added to this instance.
	 */
	public boolean addCard(Card card) throws IllegalArgumentException;
	
	/**
	 * Removes the newest card from this instance
	 * @return Card that was removed
	 * @throws thrown if card being removed is not the card on the top of the pile
	 */
	public Card removeCard() throws IllegalArgumentException;
	
	/**
	 * Gets the newest card from this instance
	 * @return newest card added to storage
	 * @throws IllegalArgumentException thrown if no elements are in storage
	 */
	public Card getCard() throws IllegalArgumentException;
	
	/**
	 * Gets all Cards in storage
	 * @return array of cards
	 */
	public Card[] getCards();
	
	/**
	 * Gets the number of elements in the storage container
	 * @return number of elements being stored
	 */
	public int size();
}
