package model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Tzuhsien Liu
 * 
 *         Description: Player class has player name funds and choose the best
 *         hand out of 7 card 2 player hand and 5 community cards.
 */

public class Player {

	private String name;
	private ArrayList<Card> hand;
	private PokerHand best;
	private double funds = 100.00;

	// constructor
	public Player(String name, double funds) {
		this.name = name;
		this.funds = funds;
	}

	// player's hand 2 cards from dealer deck
	public void dealCards(Dealer deck) {
		hand = new ArrayList<Card>();
		hand.add(deck.deal());
		hand.add(deck.deal());
		Collections.sort(hand);
	}

	// player hand
	public ArrayList<Card> playhand() {
		return this.hand;
	}

	// player's hand to string
	public String toStringHand() {
		String result = "";
		result += hand.get(0).toString() + " ";
		result += hand.get(1).toString();
		return result;
	}

	// Player string "name"
	public String getPlayer() {
		return this.name;
	}

	// get player's money
	public double getfunds() {
		return this.funds;
	}

	// add winnings if winner
	public void winnings(Dealer d, int n) {
		this.funds = getfunds() + d.getPot(n);
	}

	// pay move to dealer
	public double pay() {
		this.funds = funds - 2;
		return this.funds;
	}

	// money formatter to string with $ sign
	public String getMoney() {
		NumberFormat formatter = DecimalFormat.getCurrencyInstance();
		return formatter.format(getfunds());
	}

	// list of 21 possible hands
	public ArrayList<PokerHand> possHands(Dealer d) {
		ArrayList<Card> ph = new ArrayList<Card>();
		ArrayList<PokerHand> list21 = new ArrayList<PokerHand>();

		list21.add(toPH(d.getComm()));

		for (int j = 0; j < 2; j++)
			for (int i = 0; i < 5; i++) {
				ph.clear();
				ph.addAll(d.getComm());
				ph.add(ph.size(), hand.get(j));
				ph.remove(i);
				list21.add(toPH(ph));
			}

		for (int j = 0; j < 4; j++) {
			ph.clear();
			ph.addAll(d.getComm());
			ph.add(ph.size(), hand.get(0));
			ph.add(ph.size(), hand.get(1));
			ph.remove(j);
			ph.remove(j + 1);
			list21.add(toPH(ph));
		}
		for (int j = 4; j > 1; j--) {
			ph.clear();
			ph.addAll(d.getComm());
			ph.add(ph.size(), hand.get(0));
			ph.add(ph.size(), hand.get(1));
			ph.remove(0);
			ph.remove(j);
			list21.add(toPH(ph));
		}
		for (int j = 0; j < 2; j++) {
			ph.clear();
			ph.addAll(d.getComm());
			ph.add(ph.size(), hand.get(0));
			ph.add(ph.size(), hand.get(1));
			ph.remove(4);
			ph.remove(j + 2);
			list21.add(toPH(ph));
		}
		ph.clear();
		ph.addAll(d.getComm());
		ph.add(ph.size(), hand.get(0));
		ph.add(ph.size(), hand.get(1));
		ph.remove(1);
		ph.remove(3);
		list21.add(toPH(ph));

		Collections.sort(list21);
		return list21;
	}

	// get best of of list21
	public PokerHand bestHand(Dealer d) {
		best = toPH(d.getComm());
		for (int i = 0; i < 21; i++) {
			if (best.compareTo(possHands(d).get(i)) < 0)
				best = possHands(d).get(i);
		}
		return best;
	}

	// take card array make pokerhand
	private PokerHand toPH(ArrayList<Card> cards) {
		PokerHand ph = new PokerHand(cards.get(0), cards.get(1), cards.get(2), cards.get(3), cards.get(4));
		return ph;
	}

}
