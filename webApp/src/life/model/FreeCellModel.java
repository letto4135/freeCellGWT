package life.model;

import java.util.ArrayList;

import life.client.DeckItems.Deck;
import life.client.StorageItems.Column;
import life.client.StorageItems.Foundation;
import life.client.StorageItems.FreeCell;

public class FreeCellModel {
	// chose to leave public for ease of access.
	// Column Foundation and Freecell are the classes
	// that need to be better controlled.
	public ArrayList<Column> columns = new ArrayList<Column>();;
	public ArrayList<Foundation> foundations = new ArrayList<Foundation>();
	public ArrayList<FreeCell> freecells= new ArrayList<FreeCell>();
	
	public FreeCellModel() {
		
	}
	
	public void StartGame() {
		for(int i = 0; i < 8; i++) {
			if(i < 4) {
				foundations.add(new Foundation());
				freecells.add(new FreeCell());
			}
			columns.add(new Column());
		}
		
		Deck deck = new Deck();
		
		deck.shuffle();
		
		int size = deck.deckSize();
		for(int i = 0; i < size; i ++) {
			// get column and place dealt card in the column
			Column column = columns.get(i % 8); // uses 0-7 to place cards in column index 0-7
			column.creation(deck.deal());
		}
	}
}
