package life.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

import life.client.DeckItems.Card;
import life.client.StorageItems.Column;
import life.client.StorageItems.Foundation;
import life.client.StorageItems.FreeCell;
import life.client.StorageItems.Storage;
import life.client.StorageItems.GWTStorage.GWTCard;
import life.client.StorageItems.GWTStorage.GWTColumn;
import life.client.StorageItems.GWTStorage.GWTFoundation;
import life.client.StorageItems.GWTStorage.GWTFreeCell;
import life.model.FreeCellModel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class FreeCellGame implements EntryPoint {
	private HorizontalPanel topPanel = new HorizontalPanel();
	private HorizontalPanel bottomPanel = new HorizontalPanel();
	private static HorizontalPanel freecells = new HorizontalPanel();
	private static HorizontalPanel foundations = new HorizontalPanel();
	private static HorizontalPanel columns = new HorizontalPanel();
	private static FreeCellModel model = new FreeCellModel();
	/**
   	* This is the entry point method.
   	*/
	public void onModuleLoad() {
		// begin game setup
		model.StartGame();
		
		topPanel.addStyleName("topPanel");
		bottomPanel.addStyleName("bottomPanel");
		freecells.addStyleName("freecells");
		foundations.addStyleName("foundations");
		columns.addStyleName("columns");
		
		freecells.addDomHandler(drop(freecells), DropEvent.getType());
		foundations.addDomHandler(drop(foundations), DropEvent.getType());
		columns.addDomHandler(drop(columns), DropEvent.getType());
		
		freecells.addStyleName("border-me");
		foundations.addStyleName("border-me");
		
		draw();

		// add freecells and foundations to the top div
		topPanel.add(freecells);
		topPanel.add(foundations);
		// add columns to the bottom div
		bottomPanel.add(columns);
		
		// add the top and bottom panels to the root by id
		RootPanel.get("topPanel").add(topPanel);
		RootPanel.get("bottomPanel").add(bottomPanel);
	}
	
	/*
	 * 'Draw' the page, called when any drop action is made. 
	 */
	public static void draw() {
		// clear all contents from page
		freecells.clear();
		foundations.clear();
		columns.clear();
		
		// add 8 columns, 4 freecells and 4 foundations back to the view
		for(int i = 0; i < 8; i ++) {
			columns.add(new GWTColumn(i));
			if(i < 4) {
				foundations.add(new GWTFoundation(i));
				freecells.add(new GWTFreeCell(i));
			}
		}
		
		// add cards from storage to freecells, foundations and columns
		for(int i = 0; i < 8; i++) {
			if(i < 4) {
				// get current freecell and foundation from storage
				FreeCell free = model.freecells.get(i);
				Foundation found = model.foundations.get(i);
				
				// get widget to add cards to
				GWTFreeCell cell = (GWTFreeCell)freecells.getWidget(i);
				cell.addStyleName("freecell");
				GWTFoundation ation = (GWTFoundation)foundations.getWidget(i);
				ation.addStyleName("foundation");
				
				// if the storage freecell and foundation has a card to add add it
				if(free.size() != 0) {
					GWTCard card = new GWTCard(free.getCard());
					card.addStyleName("freecell");
					cell.add(card);
				}else {
					GWTCard card = new GWTCard();
					card.addStyleName("freecell");
					cell.add(card);
				}
				if(found.size() != 0) {
					GWTCard card = new GWTCard(found.getCard());
					card.addStyleName("foundation");
					ation.add(card);
				}else {
					GWTCard card = new GWTCard();
					card.addStyleName("foundation");
					ation.add(card);
				}
			}
			
			// get current column from storage
			Column col = model.columns.get(i);

			// get widget to add cards to
			GWTColumn column = (GWTColumn)columns.getWidget(i);
			column.addStyleName("column");
			
			if(col.size() != 0) {
				Card[] cards = col.getCards();
				for(int j = 0; j < cards.length; j++) {
					GWTCard card = new GWTCard(cards[j]);
					card.addStyleName("column");
					column.add(card);
				}
			}else {
				GWTCard card = new GWTCard();
				card.addStyleName("column");
				column.add(card);
			}
		}
	}
	
	/*
	 * Drop handlers for freecells, columns, and foundations holders.
	 * Calls model methods to move cards around then redraws page after
	 * moves have been validated.
	 */
	private DropHandler drop(HorizontalPanel p) {
		return new DropHandler() {			
			@Override
			public void onDrop(DropEvent event) {
				String source = event.getData("source");
				String destination = "";
				EventTarget target = event.getNativeEvent().getEventTarget();
				Element elementTarget = Element.as(target);
				// destination tag will either be on the current element (blank space) or
				// direct parent element (card)
				destination = elementTarget.getAttribute("class");
				if(destination.equals("")) {
					destination = elementTarget.getParentElement().getAttribute("class");
				}
				// class always has GWT-HTML as first part of the class name, so using this
				// to get rid of it
				destination = destination.substring(9);
				
				// if destination is a card the beginning of the class name on the element
				// will be card so that needs to be removed for easier processing later
				if(destination.substring(0, 4).equals("card")) {
					destination = destination.substring(5);
				}
				
				// finally, add on to the end of the destination the number of storage
				// space the element is for use later
				for(int i = 0; i < p.getWidgetCount(); i++) {
					if(p.getWidget(i).getElement().isOrHasChild(elementTarget)) {
						destination = destination + " " + i;
					}
				}
				
				// begin processing destination by using the beginning of destination string
				// to freecell
				if(destination.substring(0, 3).equals("fre")) {
					// get destination freecell from number at end of destination string
					FreeCell f = model.freecells.get(Integer.parseInt(destination.substring(destination.length() - 1)));
					checkMove(f, source);
				}
				// to foundation
				if(destination.substring(0, 3).equals("fou")) {
					Foundation f = model.foundations.get(Integer.parseInt(destination.substring(destination.length() - 1)));
					checkMove(f, source);
				}
				// to column
				if(destination.substring(0, 3).equals("col")){
					Column c = model.columns.get(Integer.parseInt(destination.substring(destination.length() - 1)));
					checkMove(c, source);
				}
				
				draw();
			}
		};
	}
	
	/*
	 * Checks validity of moves, uses polymorphism with parameter 'c' to determine
	 * the usage of addCard method based on the actual class passed in.
	 */
	private void checkMove(Storage c, String source) {
		// from column
		if(source.substring(0, 3).equals("col")) {
			Column co = model.columns.get(Integer.parseInt(source.substring(source.length() - 1)));
			try {
				c.addCard(co.getCard());
				co.removeCard();
			}catch(IllegalArgumentException e){}
		}
		// from foundation
		if(source.substring(0, 3).equals("fou")) {
			Foundation fo = model.foundations.get(Integer.parseInt(source.substring(source.length() - 1)));
			try {
				c.addCard(fo.getCard());
				fo.removeCard();
			}catch(IllegalArgumentException e){}
		}
		// from freecell
		if(source.substring(0, 3).equals("fre")) {
			FreeCell fr = model.freecells.get(Integer.parseInt(source.substring(source.length() - 1)));
			try {
				c.addCard(fr.getCard());
				fr.removeCard();
			}catch(IllegalArgumentException e){}
		}
	}
}