package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import junit.framework.TestCase;
import life.model.FreeCellModel;

class ModelTests {
	@Test
	void test() {
		FreeCellModel model = new FreeCellModel();
		
		System.out.println("Testing FreeCellModel class");
		model.StartGame();
		System.out.println("Number of freecells should be 4, answer : " + model.freecells.size());
		System.out.println("Number of foundations should be 4, answer : " + model.foundations.size());
		System.out.println("Number of columns should be 8, answer : " + model.columns.size());
		assertTrue(model.freecells.size() == 4);
		assertTrue(model.foundations.size() == 4);
		assertTrue(model.columns.size() == 8);
		
		System.out.println("Number of cards in first 4 columns should be 7, answer: ");
		System.out.println(model.columns.get(0).size());
		System.out.println(model.columns.get(1).size());
		System.out.println(model.columns.get(2).size());
		System.out.println(model.columns.get(3).size());
		assertTrue(model.columns.get(0).size() == 7);
		assertTrue(model.columns.get(1).size() == 7);
		assertTrue(model.columns.get(2).size() == 7);
		assertTrue(model.columns.get(3).size() == 7);
		System.out.println("Number of cards in last 4 columns should be 6, answer: ");
		System.out.println(model.columns.get(4).size());
		System.out.println(model.columns.get(5).size());
		System.out.println(model.columns.get(6).size());
		System.out.println(model.columns.get(7).size());
		assertTrue(model.columns.get(4).size() == 6);
		assertTrue(model.columns.get(5).size() == 6);
		assertTrue(model.columns.get(6).size() == 6);
		assertTrue(model.columns.get(7).size() == 6);
	}
}
