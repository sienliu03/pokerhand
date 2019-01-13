package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.PokerHand;
import model.Card;
import model.Rank;
import model.Suit;


/**
 * @author Tzuhsien Liu and Ricks tests
 * 
 *         Tests the PokerHand class and the enums
 * 
 *         Rick includes all 52 cards to save you time (see end of file, after
 *         the @Test methods)
 * 
 *         There are also some additional test cases here. But this is in no way
 *         complete. Many more are needed
 * 
 *         Has all the has(handtype) method tests
 */
public class PokerHandTest {
	
	@Test
	public void test() {
		//Deck deck = new Deck();
		
	}

	@Test
	public void testSuitEnum() {
		String result = "";
		for (Suit suit : Suit.values())
			result += suit + " ";
		assertEquals("CLUBS DIAMONDS HEARTS SPADES", result.trim());
	}

	@Test
	public void testRankEnum() {
		String result = "";
		for (Rank rank : Rank.values())
			result += rank + " ";
		assertEquals("DEUCE THREE FOUR FIVE SIX SEVEN EIGHT NINE TEN JACK QUEEN KING ACE", result.trim());
	}

	@Test
	public void testhasSameSuit() {
		PokerHand a = new PokerHand(C4, CA, C6, C3, C7);
		PokerHand b = new PokerHand(C4, C10, DA, CA, S4);
		PokerHand c = new PokerHand(H4, C10, HA, SA, S4);

		assertTrue(a.hasSameSuit());
		assertFalse(b.hasSameSuit());
		assertFalse(c.hasSameSuit());
	}

	@Test
	public void testhasStriaghtFlush() {
		PokerHand a = new PokerHand(C4, C5, C6, C7, C8);
		PokerHand b = new PokerHand(S3, H4, C5, C6, D7);
		PokerHand c = new PokerHand(H9, H10, HJ, HQ, HK);
		PokerHand d = new PokerHand(H9, H10, DJ, HQ, HK);
		PokerHand e = new PokerHand(H10, HJ, DQ, HK, HA);
		PokerHand f = new PokerHand(H10, HJ, DQ, HK, H2);
		PokerHand g = new PokerHand(H10, HJ, HQ, HK, HA);
		PokerHand h = new PokerHand(CA, C2, C3, C4, C5);
		PokerHand i = new PokerHand(C5, C3, C10, C4, C6);
		PokerHand j = new PokerHand(C10, CJ, CQ, CK, CA);

		assertTrue(a.hasStraightFlush());
		assertFalse(b.hasStraightFlush());
		assertTrue(c.hasStraightFlush());
		assertFalse(d.hasStraightFlush());
		assertFalse(e.hasStraightFlush());
		assertFalse(f.hasStraightFlush());
		assertTrue(g.hasStraightFlush());
		assertTrue(h.hasStraightFlush());
		assertFalse(i.hasStraightFlush());
		assertTrue(a.compareTo(c) < 0);
		assertTrue(a.compareTo(d) > 0);
		assertTrue(h.compareTo(e) > 0);
		assertTrue(g.compareTo(a) > 0);
		assertTrue(a.compareTo(e) > 0);
		assertTrue(a.compareTo(h) > 0);
		assertTrue(g.compareTo(j) == 0);

	}

	@Test
	public void testhasFourKind() {
		PokerHand a = new PokerHand(S4, D4, C4, H4, C8);
		PokerHand b = new PokerHand(SK, DK, CK, HK, C9);
		PokerHand c = new PokerHand(S4, D4, C4, H5, C8);
		PokerHand d = new PokerHand(SA, D5, HA, H5, CA);
		PokerHand e = new PokerHand(S8, D4, C4, H5, C8);
		PokerHand f = new PokerHand(S4, D4, C5, H5, C8);
		PokerHand g = new PokerHand(S4, D5, C8, H5, C5);
		PokerHand h = new PokerHand(SA, DA, CA, HA, C7);

		assertTrue(a.hasFourKind());
		assertTrue(b.hasFourKind());
		assertFalse(c.hasFourKind());
		assertFalse(d.hasFourKind());
		assertFalse(e.hasFourKind());
		assertFalse(f.hasFourKind());
		assertFalse(g.hasFourKind());
		assertTrue(a.compareTo(b) < 0);
		assertTrue(h.compareTo(b) > 0);
		assertTrue(h.compareTo(e) > 0);
	}

	@Test
	public void testhasFullHouse() {
		PokerHand a = new PokerHand(S4, D4, C4, H5, C5);
		PokerHand b = new PokerHand(SK, DK, SA, HK, CA);
		PokerHand c = new PokerHand(S4, D4, C4, H5, C8);
		PokerHand d = new PokerHand(H4, D8, SJ, HJ, H8);

		assertTrue(a.hasFullHouse());
		assertTrue(b.hasFullHouse());
		assertFalse(c.hasFullHouse());
		assertFalse(d.hasFullHouse());

		assertTrue(a.compareTo(b) < 0);
		assertTrue(b.compareTo(d) > 0);
		assertTrue(c.compareTo(d) > 0);
	}

	@Test
	public void testhasFlush() {
		PokerHand a = new PokerHand(SQ, S9, S4, S5, S7);
		PokerHand b = new PokerHand(DK, DJ, DA, D7, D10);
		PokerHand c = new PokerHand(DK, DJ, DA, D7, H10);
		PokerHand d = new PokerHand(S8, D8, H9, H5, H7);

		assertTrue(a.hasFlush());
		assertTrue(b.hasFlush());
		assertFalse(c.hasFlush());
		assertFalse(d.hasFlush());

		assertTrue(a.compareTo(b) < 0);
		assertTrue(b.compareTo(d) > 0);
		assertTrue(c.compareTo(d) < 0);
	}

	@Test
	public void testhasStraight() {
		PokerHand a = new PokerHand(S5, S4, S3, S6, S7);
		PokerHand b = new PokerHand(D5, D4, H3, D2, D6);
		PokerHand c = new PokerHand(DK, DJ, DA, D7, H10);
		PokerHand d = new PokerHand(S8, D8, H9, H5, H7);
		PokerHand e = new PokerHand(S8, D7, H9, H10, HJ);
		PokerHand f = new PokerHand(SA, S2, H3, H4, H5);
		PokerHand g = new PokerHand(S10, DJ, HQ, HK, HA);

		assertFalse(a.hasStraight());
		assertTrue(b.hasStraight());
		assertFalse(c.hasStraight());
		assertFalse(d.hasStraight());
		assertTrue(e.hasStraight());
		assertTrue(f.hasStraight());
		assertTrue(g.hasStraight());

	}

	@Test
	public void testhasThreeKind() {
		PokerHand a = new PokerHand(S5, H5, D5, S6, S7);
		PokerHand b = new PokerHand(D5, D4, HA, DA, SA);
		PokerHand c = new PokerHand(DK, SK, DA, SA, H10);
		PokerHand d = new PokerHand(S8, D8, H8, H5, H7);
		PokerHand e = new PokerHand(S8, D7, D9, H10, HJ);
		PokerHand f = new PokerHand(D5, D8, H8, S8, SA);
		PokerHand g = new PokerHand(C5, D7, H7, S7, SK);

		assertTrue(a.hasThreeKind());
		assertTrue(b.hasThreeKind());
		assertFalse(c.hasThreeKind());
		assertTrue(d.hasThreeKind());
		assertFalse(e.hasThreeKind());
		assertTrue(f.hasThreeKind());
		assertTrue(a.compareTo(b) < 0);
		assertTrue(b.compareTo(a) > 0);
		assertTrue(b.compareTo(d) > 0);
		assertTrue(a.compareTo(c) > 0);
		assertTrue(b.compareTo(e) < 0);
		assertTrue(a.compareTo(d) < 0);
		assertTrue(f.compareTo(g) > 0);
		assertTrue(g.compareTo(f) < 0);
	}

	@Test
	public void testhasTwoPair() {
		PokerHand a = new PokerHand(S5, H5, D4, S4, S7);
		PokerHand b = new PokerHand(DK, SK, HA, DA, D8);
		PokerHand c = new PokerHand(DK, SK, DQ, SA, H10);
		PokerHand d = new PokerHand(S8, D8, H9, S9, H7);
		PokerHand e = new PokerHand(S8, H7, S7, SJ, HJ);

		assertTrue(a.hasTwoPair());
		assertTrue(b.hasTwoPair());
		assertFalse(c.hasTwoPair());
		assertTrue(d.hasTwoPair());
		assertTrue(e.hasTwoPair());

		PokerHand f = new PokerHand(C4, HK, D4, H3, DK);
		PokerHand g = new PokerHand(S4, SK, H4, S3, CK);
		PokerHand h = new PokerHand(S4, SK, H4, S5, CK);
		assertTrue(f.compareTo(g) == 0);
		assertTrue(f.compareTo(h) < 0);
		assertTrue(h.compareTo(f) > 0);
		PokerHand i = new PokerHand(S4, SK, H4, S3, CK);
		PokerHand j = new PokerHand(C6, HK, D6, H3, DK);
		;
		assertTrue(i.compareTo(j) < 0);
		assertTrue(j.compareTo(i) > 0);

	}

	@Test
	public void testhasOnePair() {
		PokerHand a = new PokerHand(S5, H5, S8, S9, S7);
		PokerHand b = new PokerHand(D7, S9, HA, DA, D8);
		PokerHand c = new PokerHand(DK, SK, DQ, SA, H10);
		PokerHand d = new PokerHand(S8, D5, H9, S10, H6);
		PokerHand e = new PokerHand(S8, HA, S7, S6, HJ);
		PokerHand f = new PokerHand(D6, H9, SA, CA, C8);
		PokerHand g = new PokerHand(D6, S10, SA, CA, S8);
		PokerHand h = new PokerHand(D6, S10, SA, CA, D9);
		PokerHand i = new PokerHand(C7, H9, SA, CA, S8);

		assertTrue(a.hasOnePair());
		assertTrue(b.hasOnePair());
		assertTrue(c.hasOnePair());
		assertFalse(d.hasOnePair());
		assertFalse(e.hasOnePair());
		assertTrue(a.compareTo(b) < 0);
		assertTrue(b.compareTo(a) > 0);
		assertTrue(b.compareTo(f) > 0);
		assertTrue(b.compareTo(g) < 0);
		assertTrue(b.compareTo(h) < 0);
		assertTrue(h.compareTo(b) > 0);
		assertTrue(b.compareTo(i) == 0);
	}

	@Test
	public void testTwoPairWhenOnePairIsEqual() {
		PokerHand a = new PokerHand(C4, HK, D4, H3, DK);
		PokerHand b = new PokerHand(H4, C10, CA, DA, S4);
		PokerHand c = new PokerHand(C4, HK, D4, H3, DK);
		PokerHand d = new PokerHand(S4, SK, C4, S3, HK);
		assertTrue(a.compareTo(b) < 0);
		assertTrue(b.compareTo(a) > 0);
		assertFalse(a.compareTo(b) > 0);
		assertFalse(b.compareTo(a) < 0);
		assertTrue(c.compareTo(d) == 0);
	}

	@Test
	public void testAceLowStraight() {
		PokerHand a = new PokerHand(CA, C2, C3, C4, H5);
		PokerHand b = new PokerHand(D2, D3, D4, D5, H6);
		boolean answer = a.compareTo(b) < 0;
		assertTrue(answer);
	}

	@Test
	public void testAceLowStraightVSAceHigh() {
		PokerHand a = new PokerHand(CA, C2, C3, C4, H5);
		PokerHand b = new PokerHand(D10, DJ, DQ, DK, HA);
		boolean answer = a.compareTo(b) < 0;
		assertTrue(answer);
	}

	@Test
	public void testPairBeatsHighHand() {
		PokerHand pair = new PokerHand(C2, D7, HJ, SA, HA);
		PokerHand pair2 = new PokerHand(S2, S7, SJ, CA, DA);
		PokerHand pair3 = new PokerHand(S3, S7, SJ, CA, DA);
		PokerHand aceHigh = new PokerHand(D2, H7, SJ, CA, H3);
		assertTrue(pair.compareTo(aceHigh) > 0);
		assertFalse(aceHigh.compareTo(pair) > 0);
		assertTrue(pair2.compareTo(pair) == 0);
	}

	// Set up 52 cards so we can use C2 instead of new Card(Rank.Deuce,
	// Suit.Clubs)
	private final static Card C2 = new Card(Rank.DEUCE, Suit.CLUBS);
	private final static Card C3 = new Card(Rank.THREE, Suit.CLUBS);
	private final static Card C4 = new Card(Rank.FOUR, Suit.CLUBS);
	private final static Card C5 = new Card(Rank.FIVE, Suit.CLUBS);
	private final static Card C6 = new Card(Rank.SIX, Suit.CLUBS);
	private final static Card C7 = new Card(Rank.SEVEN, Suit.CLUBS);
	private final static Card C8 = new Card(Rank.EIGHT, Suit.CLUBS);
	private final static Card C9 = new Card(Rank.NINE, Suit.CLUBS);
	private final static Card C10 = new Card(Rank.TEN, Suit.CLUBS);
	private final static Card CJ = new Card(Rank.JACK, Suit.CLUBS);
	private final static Card CQ = new Card(Rank.QUEEN, Suit.CLUBS);
	private final static Card CK = new Card(Rank.KING, Suit.CLUBS);
	private final static Card CA = new Card(Rank.ACE, Suit.CLUBS);

	private final static Card D2 = new Card(Rank.DEUCE, Suit.DIAMONDS);
	private final static Card D3 = new Card(Rank.THREE, Suit.DIAMONDS);
	private final static Card D4 = new Card(Rank.FOUR, Suit.DIAMONDS);
	private final static Card D5 = new Card(Rank.FIVE, Suit.DIAMONDS);
	private final static Card D6 = new Card(Rank.SIX, Suit.DIAMONDS);
	private final static Card D7 = new Card(Rank.SEVEN, Suit.DIAMONDS);
	private final static Card D8 = new Card(Rank.EIGHT, Suit.DIAMONDS);
	private final static Card D9 = new Card(Rank.NINE, Suit.DIAMONDS);
	private final static Card D10 = new Card(Rank.TEN, Suit.DIAMONDS);
	private final static Card DJ = new Card(Rank.JACK, Suit.DIAMONDS);
	private final static Card DQ = new Card(Rank.QUEEN, Suit.DIAMONDS);
	private final static Card DK = new Card(Rank.KING, Suit.DIAMONDS);
	private final static Card DA = new Card(Rank.ACE, Suit.DIAMONDS);

	private final static Card H2 = new Card(Rank.DEUCE, Suit.HEARTS);
	private final static Card H3 = new Card(Rank.THREE, Suit.HEARTS);
	private final static Card H4 = new Card(Rank.FOUR, Suit.HEARTS);
	private final static Card H5 = new Card(Rank.FIVE, Suit.HEARTS);
	private final static Card H6 = new Card(Rank.SIX, Suit.HEARTS);
	private final static Card H7 = new Card(Rank.SEVEN, Suit.HEARTS);
	private final static Card H8 = new Card(Rank.EIGHT, Suit.HEARTS);
	private final static Card H9 = new Card(Rank.NINE, Suit.HEARTS);
	private final static Card H10 = new Card(Rank.TEN, Suit.HEARTS);
	private final static Card HJ = new Card(Rank.JACK, Suit.HEARTS);
	private final static Card HQ = new Card(Rank.QUEEN, Suit.HEARTS);
	private final static Card HK = new Card(Rank.KING, Suit.HEARTS);
	private final static Card HA = new Card(Rank.ACE, Suit.HEARTS);

	private final static Card S2 = new Card(Rank.DEUCE, Suit.SPADES);
	private final static Card S3 = new Card(Rank.THREE, Suit.SPADES);
	private final static Card S4 = new Card(Rank.FOUR, Suit.SPADES);
	private final static Card S5 = new Card(Rank.FIVE, Suit.SPADES);
	private final static Card S6 = new Card(Rank.SIX, Suit.SPADES);
	private final static Card S7 = new Card(Rank.SEVEN, Suit.SPADES);
	private final static Card S8 = new Card(Rank.EIGHT, Suit.SPADES);
	private final static Card S9 = new Card(Rank.NINE, Suit.SPADES);
	private final static Card S10 = new Card(Rank.TEN, Suit.SPADES);
	private final static Card SJ = new Card(Rank.JACK, Suit.SPADES);
	private final static Card SQ = new Card(Rank.QUEEN, Suit.SPADES);
	private final static Card SK = new Card(Rank.KING, Suit.SPADES);
	private final static Card SA = new Card(Rank.ACE, Suit.SPADES);

	// TEST CARD HGH HANDS

	private static PokerHand nothing72 = new PokerHand(C2, C3, C4, C5, D7);
	private static PokerHand nothing73 = new PokerHand(D2, D4, D5, D6, C7);
	private static PokerHand nothingJ = new PokerHand(C8, C9, C10, SJ, D3);
	private static PokerHand nothingK9 = new PokerHand(CK, CQ, CJ, D10, H9);
	private static PokerHand nothingK8 = new PokerHand(HK, HQ, HJ, H10, S8);
	private static PokerHand nothingA = new PokerHand(S9, SJ, SQ, SK, CA);
	private static PokerHand nothing = new PokerHand(S2, S3, S4, S5, C7);

	@Test
	public void testNothing0() {
		assertTrue(nothing73.compareTo(nothing72) > 0);
		assertTrue(nothing72.compareTo(nothing73) < 0);
		assertTrue(nothing72.compareTo(nothing) == 0);
	}

	@Test
	public void testNothing1() {
		assertTrue(nothingJ.compareTo(nothing73) > 0);
	}

	@Test
	public void testNothing2() {
		assertTrue(nothingK8.compareTo(nothingJ) > 0);
	}

	@Test
	public void testNothing3() {
		assertTrue(nothingK9.compareTo(nothingK8) > 0);
	}

	@Test
	public void testNothing4() {
		assertTrue(nothingA.compareTo(nothingK8) > 0);
	}
	
	 // These tests were added after removing DuplicateCardException
	  // because things change when cards can be shared. This assumes
	  // there are no more DuplicateCardException checks. Notice these 
	  // output incorrectly list ties for full house. Only 2 should tie
	  /*
	  Winning hands (tie) 
	  3♣ 3♦ A♦ A♣ A♥    FULL_HOUSE Player 8 $55.0
	  6♠ 6♦ A♦ A♣ A♥    FULL_HOUSE Player 7 $109.0
	  6♠ 6♥ A♦ A♣ A♥    FULL_HOUSE Player 4 $100.0
	  3♣ 3♥ A♦ A♣ A♥    FULL_HOUSE Player 2 $154.0
	  Play another game? <y OR n> 
	  */
	  @Test
	  public void testFullHouseHands() {
	    PokerHand a = new PokerHand(S6, D6, DA, CA, HA);  // Both hands can have the same 3 Aces
	    PokerHand b = new PokerHand(S3, D3, DA, CA, HA);
	    assertTrue(a.compareTo(b) > 0);
	    assertTrue(b.compareTo(a) < 0);
	  }
	 
	  @Test
	  public void testFullHouseHands2() {
	    PokerHand a = new PokerHand(S6, D6, DA, CA, HA);
	    PokerHand b = new PokerHand(C6, H6, DA, CA, HA);
	    assertTrue(a.compareTo(b) == 0);
	    assertTrue(b.compareTo(a) == 0);
	  }
	  
	  @Test
	  public void testFullHouseHands3() {
	    PokerHand a = new PokerHand(S6, D6, DA, CA, HA);
	    PokerHand b = new PokerHand(C7, H7, DA, CA, HA);
	    assertTrue(a.compareTo(b) < 0);
	    assertTrue(b.compareTo(a)> 0);
	  }
	 
	  @Test
	  public void testThreeOfAKind() {
	    PokerHand a = new PokerHand(S6, D7, DA, CA, HA);
	    PokerHand b = new PokerHand(C5, H6, DA, CA, HA);
	    assertTrue(a.compareTo(b) > 0);
	    assertTrue(b.compareTo(a) <  0);
	  }
	  
	  @Test
	  public void testThreeOfAKind3() {
	    PokerHand a = new PokerHand(S7, D6, DA, CA, HA);
	    PokerHand b = new PokerHand(C7, H6, DA, CA, HA);
	    assertTrue(a.compareTo(b) == 0);
	    assertTrue(b.compareTo(a) ==  0);
	  }
	  
	  @Test
	  public void testFourOfAKind() {
	    PokerHand a = new PokerHand(S7, CA, DA, HA, SA);
	    PokerHand b = new PokerHand(D7, CA, DA, HA, SA);
	    assertTrue(a.compareTo(b) == 0);
	    assertTrue(b.compareTo(a) ==  0);
	  }
	  
	  @Test
	  public void testFourOfAKind4() {
	    PokerHand a = new PokerHand(S8, CA, DA, HA, SA);
	    PokerHand b = new PokerHand(D7, CA, DA, HA, SA);
	    assertTrue(a.compareTo(b) > 0);
	    assertTrue(b.compareTo(a)<   0);
	  }

}