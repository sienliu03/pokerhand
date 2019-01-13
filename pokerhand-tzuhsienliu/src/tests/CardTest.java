package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Card;
import model.Rank;
import model.Suit;

/**
 *
 * @author Tzuhsien Liu
 * 
 *         Test cards.java see if rank and suit are working
 */

public class CardTest {

	@Test
	public void testTwoCards() {
		Card C2 = new Card(Rank.DEUCE, Suit.CLUBS);
		Card CA = new Card(Rank.ACE, Suit.CLUBS);
		assertTrue(C2 != CA);
	}

	// Add more @Test methods

}