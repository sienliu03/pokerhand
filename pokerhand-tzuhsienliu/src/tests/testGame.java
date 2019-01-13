package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.PokerHand;
import model.Card;
import model.Dealer;
import model.Player;
import model.Rank;
import model.Suit;

/**
 * @author Tzuhsien Liu
 * 
 *         Description: Extra test to help look at printout of methods.
 */

public class testGame {

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

	@Test
	public void testPlayer() {

		Player a = new Player("Player" + 1, 50);
		Player c = new Player("Player" + 2, 75);
		Dealer b = new Dealer();
		a.dealCards(b);
		b.community(b);
		System.out.println(a.getfunds());
		System.out.println(a.getPlayer());
		System.out.println(a.toStringHand());
		System.out.println(b.toString());
		System.out.println(b.size());

	}

	@Test
	public void testPlayerAdd() {

		Player a = new Player("Player" + 1, 50);
		Player c = new Player("Player" + 2, 75);
		Dealer b = new Dealer();
		b.addPlayers(a);
		b.addPlayers(c);
		System.out.println(b.playerList().get(0).getPlayer());
		System.out.println(b.playerList().get(0).getMoney());
		System.out.println(b.playerList().get(1).getPlayer());
		System.out.println(b.playerList().get(1).getMoney());
		System.out.println("llllllllll");

	}

	@Test
	public void testdeal() {
		Dealer a = new Dealer();
		Card deal1 = a.deal();
		Card deal2 = a.deal();
		a.deal();
		System.out.println(deal1.toString() + deal2.toString() + a.size());
		System.out.println();
	}

	@Test
	public void testList21() {

		Player a = new Player("Player" + 1, 50);
		Player b = new Player("Player" + 2, 75);
		Dealer c = new Dealer();
		System.out.println(c.community(c));
		c.addPlayers(a);
		c.playerList().get(0).dealCards(c);
		System.out.println(c.playerList().get(0).playhand().toString());
		System.out.println(a.possHands(c).size());
		System.out.println(a.possHands(c).get(0).toString());
		System.out.println(a.possHands(c).get(1).toString());
		System.out.println(a.possHands(c).get(2).toString());
		System.out.println(a.possHands(c).get(3).toString());
		System.out.println(a.possHands(c).get(4).toString());
		System.out.println(a.possHands(c).get(5).toString());
		System.out.println(a.possHands(c).get(6).toString());
		System.out.println(a.possHands(c).get(7).toString());
		System.out.println(a.possHands(c).get(8).toString());
		System.out.println(a.possHands(c).get(9).toString());
		System.out.println(a.possHands(c).get(10).toString());
		System.out.println(a.possHands(c).get(11).toString());
		System.out.println(a.possHands(c).get(12).toString());
		System.out.println(a.possHands(c).get(13).toString());
		System.out.println(a.possHands(c).get(14).toString());
		System.out.println(a.possHands(c).get(15).toString());
		System.out.println(a.possHands(c).get(16).toString());
		System.out.println(a.possHands(c).get(17).toString());
		System.out.println(a.possHands(c).get(18).toString());
		System.out.println(a.possHands(c).get(19).toString());
		System.out.println(a.possHands(c).get(20).toString());
		System.out.println("______________________");
		System.out.println(a.bestHand(c).toString());
		System.out.println("______________________");

	}
}
