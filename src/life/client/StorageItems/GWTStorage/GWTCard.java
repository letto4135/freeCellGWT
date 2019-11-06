package life.client.StorageItems.GWTStorage;

import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.DragEndEvent;
import com.google.gwt.event.dom.client.DragEndHandler;
import com.google.gwt.event.dom.client.DragOverEvent;
import com.google.gwt.event.dom.client.DragOverHandler;
import com.google.gwt.event.dom.client.DragStartEvent;
import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

import life.client.DeckItems.Card;

/**
 * Represents a standard poker style playing card
 * @author Alex Life
 *
 */
public class GWTCard extends HTML {
	Card card = null;
	Widget CHILD = this;
	Widget PARENT = this.getParent();
	SimpleEventBus bus = new SimpleEventBus();
	
	/**
	 * Instantiates a blank card
	 */
	public GWTCard() {
		super();
		String uniqueID = generateID();
		this.setHeight("12rem");
		this.setWidth("7rem");
		this.getElement().getStyle().setColor("transparent");
		this.getElement().getStyle().setBorderWidth(2, Unit.PX);
		this.getElement().getStyle().setBorderColor("black");
		this.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
		this.getElement().getStyle().setMargin(5, Unit.PX);
		this.getElement().setDraggable("false");

		this.addDomHandler(new DragOverHandler() {
            @Override
            public void onDragOver(DragOverEvent event) {
            	
            }
        }, DragOverEvent.getType());
		
		this.addDragEndHandler(new DragEndHandler() {
			@Override
			public void onDragEnd(DragEndEvent event) {
				event.setData("card", uniqueID);
			}
		});
	}
	
	/**
	 * Instantiates a card based off of the card passed in to this constructor
	 * @param card standard poker playing card
	 */
	public GWTCard(Card card) {
		super();
		this.card = card;
		
		this.setHeight("12rem");
		this.setWidth("7rem");
		this.addStyleName("card");
		
		String name = card.getName();
		if(name.length() > 1) {
			if(Character.isDigit(name.charAt(1))) {
				name = name.substring(0, 2);
			}else {
				name = name.substring(0, 1);
			}
		}
		if(this.card.getFace().equals("diamonds")) {
			this.setHTML("<h2 style='margin-top:.5em;'>" + name + "◆</h2>"
					    +"<h1 style='text-align:center; font-size: -webkit-xxx-large;'>◆</h1>"
						+"<h2 style='text-align:right'>" + name + "◆</h2>");
		}
		else if(this.card.getFace().equals("hearts")) {
			this.setHTML("<h2 style='margin-top:.5em;'>" + name + "♥</h2>"
				    +"<h1 style='text-align:center; font-size: -webkit-xxx-large;'>♥</h1>"
					+"<h2 style='text-align:right'>" + name + "♥</h2>");
		}
		else if(this.card.getFace().equals("spades")) {
			this.setHTML("<h2 style='margin-top:.5em;'>" + name + "♠</h2>"
				    +"<h1 style='text-align:center; font-size: -webkit-xxx-large;'>♠</h1>"
					+"<h2 style='text-align:right'>" + name + "♠</h2>");
		}
		else if(this.card.getFace().equals("clubs")) {
			this.setHTML("<h2 style='margin-top:.5em;'>" + name + "♣</h2>"
				    +"<h1 style='text-align:center; font-size: -webkit-xxx-large;'>♣</h1>"
					+"<h2 style='text-align:right'>" + name + "♣</h2>");
		}
		else {
			this.setHTML(this.card.getFace());
		}
		
		this.getElement().getStyle().setColor(this.card.getColor());
		this.getElement().getStyle().setBorderWidth(2, Unit.PX);
		this.getElement().getStyle().setBorderColor("black");
		this.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);;
		this.getElement().getStyle().setMargin(5, Unit.PX);
		this.getElement().setDraggable("true");

		this.addDragStartHandler(new DragStartHandler() {
		    @Override
		    public void onDragStart(DragStartEvent event) {
		    	// check that card is top of stack
		    	event.setData("card", card.toString());
		      }
		});
		
		this.addDomHandler(new DragOverHandler(){
		     @Override
		     public void onDragOver(DragOverEvent event) {
		    	 
		     }}, DragOverEvent.getType()
		);
		
		this.addDragEndHandler(new DragEndHandler() {
			@Override
			public void onDragEnd(DragEndEvent event) {
				// check that card is able to be placed before removing from parent
				CHILD.removeFromParent();
				event.setData("card", card.toString());
			}
		});
	}
	
	/*
	 * Generates a unique id for blank cards. Without this
	 * cards will not render to screen.
	 */
	private String generateID() {
		String id = "b";
		
		for(int i = 0; i < 15; i++) {
			int number = (int)(Math.random() * 10);
			id = id + number;
		}
		
		return id;
	}
}
	
