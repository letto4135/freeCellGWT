package life.client.DeckItems;

import java.util.ArrayList;

/**
 * Represents a playing card for card games.
 * @author Alex Life
 *
 */
public class Card implements Comparable<Card> {
	private boolean hidden = true;
	private String suit = "";
	private String value = "";
	private String color = "";
	private String name = "";
	
	/**
	 * Creates a card which will either mimic a playing card
	 * @param face Ex: Diamonds, Hearts, Spades, Red, Blue, Green, Draw4
	 * @param value numerical identifier of a playing card, can also be left blank for special cards with no values Ex: Uno draw 4 card..
	 */
	public Card(String face, String value) {
		face = face.toLowerCase();
		setSuit(face);
		setValue(value);
		setName();
		setColor();
	}
	
	/**
	 * Creates a blank card, for use with create method.
	 */
	public Card() {
		
	}
	
	/**
	 * Creates a card from a string ex 'Ace of clubs', '2 of diamonds'
	 * @param data card string
	 * @return new Card instance
	 */
	public Card create(String data) {
		// second to last character is unique among the suits, using that
		// to set suit = to the correct card suit
		String suit = "";
		if(((Character)data.charAt(data.length() - 2)).equals('d')) {
			suit = "diamonds";
		}else if(((Character)data.charAt(data.length() - 2)).equals('b')) {
			suit = "clubs";
		}else if(((Character)data.charAt(data.length() - 2)).equals('t')) {
			suit = "hearts";
		}else {
			suit = "spades";
		}
		
		String number = "";
		if(Character.isDigit((Character)data.charAt(0))){
			if(Character.isDigit((Character)data.charAt(1))){
				number = data.substring(0,2);
			}else {
				number = ((Character)data.charAt(0)).toString();
			}
		}else if(((Character)data.charAt(0)).equals('J')){
			number = "11";
		}else if(((Character)data.charAt(0)).equals('Q')) {
			number = "12";
		}else if(((Character)data.charAt(0)).equals('K')) {
			number = "13";
		}else if(((Character)data.charAt(0)).equals('A')) {
			number = "14";
		}
		
		return new Card(suit, number);
	}
	
	/**
	 * Check to see if card is hidden from view.
	 * @return boolean hidden
	 */
	public boolean getHidden() {
		return hidden;
	}
	
	/**
	 * Returns the string representation of a playing card. This method should be overridden
	 * by anything other than a normal deck of poker cards.
	 */
	public String toString() {
		return name + " of " + suit;
	}
	
	/**
	 * Compares the equivalence of two cards according to value/rank and suit/color of the card.
	 * @param c Card to be compared
	 * @return boolean indicating whether the value is the same in both cards
	 */
	public boolean equals(Card c) {
		if(value.equals(c.getValue()) && suit.equals(c.getFace())) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Compares two cards based on value/rank of the card for ordering cards.
	 * @param o Card object to be compared
	 * @return Returns -1 if the calling instance value is lower, 0 if the values are equal, 1 if the calling instance is larger
	 */
	public int compareTo(Card c) {
		int cValue = Integer.parseInt(c.getValue());
		int tValue = Integer.parseInt(value);
		
		if(tValue < cValue) {
			return -1;
		}else if(tValue > cValue) {
			return 1;
		}else {
			return 0;
		}
	}
	
	/**
	 * Changes the card so that it can be viewed by other players or hidden depending.
	 * Default hidden value is false. **** Hidden does no actual 'hiding' of a card
	 * this must be done by the client. ****
	 */
	public void flipCard() {
		if(hidden) {
			hidden = false;
		}else {
			hidden = true;
		}
	}
	
	/*
	 * Returns an arraylist of card properties; suit, value, color and hidden.
	 * Specifically for testing
	 */
	protected ArrayList<String> cardProps(){
		ArrayList<String> props = new ArrayList<String>();
		
		props.add(suit);
		props.add(value);
		props.add(String.valueOf(hidden));
		
		return props;
	}
	
	private void setSuit(String suit) {
		if(!suit.equals("")) {
			this.suit = suit;
		}
	}
	
	public String getFace() {
		return suit;
	}
	
	private void setValue(String value) {
		if(!value.equals("") && !value.equals("14")) {
			this.value = value;
		}else if(!value.equals("")) {
			this.value = "1";
		}
	}
	
	public String getValue() {
		return value;
	}
	
	private void setColor() {
		if(suit.equals("hearts") || suit.equals("diamonds")
		|| suit.equals("Hearts") || suit.equals("Diamonds")) {
			color = "Red";
		}else {
			color = "Black";
		}
	}
	
	public String getColor() {
		return color;
	}
	
	public void setName() {
		// if what is shown to the player should be a named card then
		// set name to preserve value field.
		if(value.equals("11")) {
			name = "Jack";
		}else if(value.equals("12")) {
			name = "Queen";
		}else if(value.equals("13")) {
			name = "King";
		}else if(value.equals("1")){
			name = "Ace";
		}else {
			name = value;
		}
	}
	
	public String getName() {
		return name;
	}
}
