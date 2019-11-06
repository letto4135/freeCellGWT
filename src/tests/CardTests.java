package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import life.client.DeckItems.Card;

class CardTests {

	@Test
	void test() {
		Card card = new Card("diamonds", "2");
		Card strCard = new Card();
		strCard = strCard.create("Ace of clubs");
		
		System.out.println("Testing Card class");
		System.out.println("card is 2 of diamonds");
		System.out.println("strCard is Ace of clubs");
		System.out.println("Compare card to strCard, expect 1, card value is less than strCard");
		System.out.println("\tAnswer: " + card.compareTo(strCard));
		System.out.println("Compare strCard to card, expect -1, stCard value is greater than card");
		System.out.println("\tAnswer: " + strCard.compareTo(card));
		assertTrue(card.compareTo(strCard) == 1);
		assertTrue(strCard.compareTo(card) == -1);
		
		System.out.println("card color should be red");
		System.out.println("\t" + card.getColor());
		assertTrue(card.getColor().equals("Red"));
		
		System.out.println("strCard color should be red");
		System.out.println("\t" + strCard.getColor());
		assertTrue(strCard.getColor().equals("Black"));
		
		System.out.println("card suit should be diamonds");
		System.out.println("\t" + card.getFace());
		assertTrue(card.getFace().equals("diamonds"));
		
		System.out.println("strCard suit should be clubs");
		System.out.println("\t" + strCard.getFace());
		assertTrue(strCard.getFace().equals("clubs"));
		
		System.out.println("card value should be 2");
		System.out.println("\t" + card.getValue());
		assertTrue(card.getValue().equals("2"));
		
		System.out.println("strCard value should be 1");
		System.out.println("\t" + strCard.getValue());
		assertTrue(strCard.getValue().equals("1"));
		
		System.out.println("card toString should be 2 of diamonds");
		System.out.println("\t" + card.toString());
		assertTrue(card.toString().equals("2 of diamonds"));
		
		System.out.println("strCard toString should be Ace of clubs");
		System.out.println("\t" + strCard.toString());
		assertTrue(strCard.toString().equals("Ace of clubs"));
		
		System.out.println("card name should be 2");
		System.out.println("\t" + card.getName());
		assertTrue(card.getName().equals("2"));
		
		System.out.println("strCard name should be Ace");
		System.out.println("\t" + strCard.getName());
		assertTrue(strCard.getName().equals("Ace"));
	}

}
