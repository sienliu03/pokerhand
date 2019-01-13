package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Tzuhsien Liu
 * 
 *         Description: PokerHand.java creates a array list to hold cards made
 *         by card.java, which is identified by enum Rank.java and Suit.java.
 *         PokerHand sets rules that of a poker game, setting different ranks
 *         different types of hand. Uses a compareTo method to rank hands.
 */

public class PokerHand implements Comparable<PokerHand> {

	private ArrayList<Card> cards;

	// Poker five card hand
	public PokerHand(Card c1, Card c2, Card c3, Card c4, Card c5) {
		cards = new ArrayList<Card>();
		cards.add(c1);
		cards.add(c2);
		cards.add(c3);
		cards.add(c4);
		cards.add(c5);
		Collections.sort(cards);
		// checkdupthis();
	}
	/*
	 * // check if there are duplicate cards in hand. private boolean
	 * checkdupthis() { for (int i = 0; i < cards.size() - 1; i++) for (int j =
	 * i + 1; j < cards.size(); j++) if (cards.get(i).equals(cards.get(j)))
	 * throw new DuplicateCardException("Duplicate Card"); return true; }
	 * 
	 * // check if there are duplicate cards in other hand. private boolean
	 * checkdupother(PokerHand other) { for (int i = 0; i < cards.size(); i++)
	 * for (int j = 0; j < cards.size(); j++) if
	 * (this.cards.get(i).equals(other.cards.get(i))) throw new
	 * DuplicateCardException("Duplicate Card");
	 * 
	 * return true; }
	 */

	// get rank of the five cards in a poker hand
	// index >= 0 and index <= 4
	private int getValue(int index) {
		return cards.get(index).getValue();
	}

	// hand has same suit
	public boolean hasSameSuit() {
		for (int i = 1; i < cards.size(); i++) {
			if (cards.get(0).getSuit() != cards.get(i).getSuit())
				return false;
		}
		return true;
	}

	// Return true if there is a straight flush
	// Precondition: cards is sorted.
	public boolean hasStraightFlush() {
		if (hasSameSuit()) {
			if (straightAceLow())
				return true;
			for (int i = 0; i < 4; i++) {
				if (cards.get(i).getValue() + 1 != cards.get(i + 1).getValue())
					return false;
			}
		} else
			return false;
		return true;
	}

	// Return true if there is a four of a kind
	// Precondition: cards is sorted.
	public boolean hasFourKind() {
		for (int i = 0; i < 2; i++) {
			if (cards.get(i).getValue() == cards.get(i + 1).getValue()
					&& cards.get(i).getValue() == cards.get(i + 2).getValue()
					&& cards.get(i).getValue() == cards.get(i + 3).getValue())
				return true;
		}
		return false;
	}

	// Return true if there is a full house
	// Precondition: cards is sorted.
	public boolean hasFullHouse() {
		if (getOnePairInThreeKind() && hasThreeKind())
			return true;
		return false;

	}

	// Return true if there is a flush
	public boolean hasFlush() {
		return hasSameSuit();
	}

	// Return true if there is a straight
	// Precondition: cards is sorted.
	public boolean hasStraight() {
		if (!hasSameSuit()) {
			if (straightAceLow())
				return true;
			else
				for (int i = 0; i < 4; i++) {
					if (cards.get(i).getValue() + 1 != cards.get(i + 1).getValue())
						return false;
				}
		} else
			return false;
		return true;
	}

	// Return true if there is a three of a kind
	// Precondition: cards is sorted.
	public boolean hasThreeKind() {
		for (int i = 0; i < 3; i++) {
			if (cards.get(i).getValue() == cards.get(i + 1).getValue()
					&& cards.get(i).getValue() == cards.get(i + 2).getValue())
				return true;
		}
		return false;
	}

	// Return true if there are exactly two pairs in this hand
	// Precondition: cards is sorted.
	public boolean hasTwoPair() {
		int numPairs = 0;
		for (int i = 0; i < cards.size() - 1; i++) {
			if (cards.get(i).getValue() == cards.get(i + 1).getValue())
				numPairs++;
		}
		return numPairs == 2;
	}

	// Return true if there is a one pair
	// Precondition: cards is sorted.
	public boolean hasOnePair() {
		int numPairs = 0;
		for (int i = 0; i < cards.size() - 1; i++) {
			if (cards.get(i).getValue() == cards.get(i + 1).getValue())
				numPairs++;
		}
		return numPairs == 1;
	}

	// compare two straight flush, flush, and straight hands
	// have to check the type of hand
	// 1 if (this) is better than (other)
	// -1 if (other) is better than (this)
	// 0 if (this) is tied to (other)
	public int compareSnFlush(PokerHand other) {
		int thisbreak = getTieBreaker(this);
		int otherbreak = getTieBreaker(other);
		// highest card value breaks tie
		if (thisbreak != otherbreak) {
			if (thisbreak > otherbreak)
				return 1;
			else
				return -1;
		} else if (thisbreak == otherbreak)
			return this.compareNothing(other);

		return 0;
	}

	public int compareFullHouse(PokerHand other) {
		int thisThreeKind = getThreeKind(this);
		int otherThreeKind = getThreeKind(other);
		// highest three kind breaks tie
		if (thisThreeKind != otherThreeKind) {
			if (thisThreeKind > otherThreeKind)
				return 1;
			else
				return -1;
		} else {
			for (int i = 0; i < 4; i += 3) {
				if (this.getValue(i) != other.getValue(i) && this.getValue(i) != thisThreeKind) {
					if (this.getValue(i) > other.getValue(i))
						return 1;
					else
						return -1;
				}
			}
		}
		return 0;
	}

	// compare two hands with full house or three of a kind
	// 1 if (this) is better than (other)
	// -1 if (other) is better than (this)
	// 0 if (this) is tied to (other)
	public int compareThreeKind(PokerHand other) {
		int thisThreeKind = getThreeKind(this);
		int otherThreeKind = getThreeKind(other);
		// highest three kind breaks tie
		if (thisThreeKind != otherThreeKind) {
			if (thisThreeKind > otherThreeKind)
				return 1;
			else
				return -1;
		} else {
			return this.compareNothing(other);
		}
	}

	// compare two hands with two pair
	// 1 if (this) is better than (other)
	// -1 if (other) is better than (this)
	// 0 if (this) is tied to (other)
	public int compareTwoPFourK(PokerHand other) {
		int thisHighPairValue = getHighPair(this);
		int otherHighPairValue = getHighPair(other);
		// If the high pair differs, the larger wins
		if (thisHighPairValue != otherHighPairValue) {
			if (thisHighPairValue > otherHighPairValue)
				return 1;
			else
				return -1;
		} else {
			int thisLowPairValue = getLowPair(this);
			int otherLowPairValue = getLowPair(other);
			// If the high pair is the same, and the low pair differs, larger
			// wins
			if (thisLowPairValue != otherLowPairValue) {
				if (thisLowPairValue > otherLowPairValue)
					return 1;
				else
					return -1;
			}
			// If high pair and low pair are the same then last card breaks the
			// tie
			int thisfifth = getfifth(this);
			int otherfifth = getfifth(other);
			if (thisfifth > otherfifth)
				return 1;
			if (thisfifth < otherfifth)
				return -1;
			// Both hands has the same rank cards
			else
				return 0;
		}
	}

	// compare two hands with two pair
	// 1 if (this) is better than (other)
	// -1 if (other) is better than (this)
	// 0 if (this) is tied to (other)
	public int compareOnePair(PokerHand other) {
		int thisPairValue = getHighPair(this);
		int otherPairValue = getHighPair(other);
		// high value pair breaks tie
		if (thisPairValue != otherPairValue) {
			if (thisPairValue > otherPairValue)
				return 1;
			else
				return -1;
		}
		// Three remaining cards are compared find the high card high to be
		// winner
		for (int i = 4; i >= 0; i--) {
			if (this.getValue(i) != other.getValue(i) && this.getValue(i) != getHighPair(this)) {
				if (this.getValue(i) > other.getValue(i))
					return 1;
				else
					return -1;
			}
		}
		return 0;
	}

	// Compare nothing hands compare this to other till there is a difference
	// high card wins
	public int compareNothing(PokerHand other) {
		for (int i = 4; i >= 0; i--) {
			if (this.getValue(i) != other.getValue(i)) {
				if (this.getValue(i) > other.getValue(i))
					return 1;
				else
					return -1;
			}
		}
		return 0;
	}

	// Hand Type Ranks
	// -------------------
	// Straight Flush = 8
	// Four Kind = 7
	// Full House = 6
	// Flush = 5
	// Straight = 4
	// Three Kind = 3
	// Two Pair = 2
	// One Pair = 1
	// Nothing = 0
	private int getHandType(PokerHand ph) {
		if (ph.hasStraightFlush())
			return 8;
		if (ph.hasFourKind())
			return 7;
		if (ph.hasFullHouse())
			return 6;
		if (ph.hasFlush())
			return 5;
		if (ph.hasStraight())
			return 4;
		if (ph.hasThreeKind())
			return 3;
		if (ph.hasTwoPair())
			return 2;
		if (ph.hasOnePair())
			return 1;
		else
			return 0;

	}

	public String getHandTypePrint(PokerHand ph) {
		if (ph.hasStraightFlush())
			return "Straight Flush";
		if (ph.hasFourKind())
			return "Four of a Kind";
		if (ph.hasFullHouse())
			return "Full House";
		if (ph.hasFlush())
			return "Flush";
		if (ph.hasStraight())
			return "Straight";
		if (ph.hasThreeKind())
			return "Three of a Kind";
		if (ph.hasTwoPair())
			return "Two Pair";
		if (ph.hasOnePair())
			return "One Pair";
		else
			return "High Card";

	}

	// Special straight with ace as low card value 1
	private boolean straightAceLow() {
		if (cards.get(4).getValue() == 14 && cards.get(0).getValue() == 2 && cards.get(1).getValue() == 3
				&& cards.get(2).getValue() == 4 && cards.get(3).getValue() == 5) {
			return true;
		} else
			return false;
	}

	// find three kind value
	private int getThreeKind(PokerHand ph) {
		int three = 0;
		for (int i = 0; i < 3; i++) {
			if (ph.getValue(i) == ph.getValue(i + 1) && ph.getValue(i) == ph.getValue(i + 2))
				three = ph.getValue(i);
		}
		return three;
	}

	// find one pair in full house
	// sorted
	private boolean getOnePairInThreeKind() {
		// three of kind value
		int three = 0;
		for (int i = 0; i < 3; i += 2) {
			if (cards.get(i).getValue() == cards.get(i + 1).getValue()
					&& cards.get(i).getValue() == cards.get(i + 2).getValue())
				three = cards.get(i).getValue();
		}
		// Confirming two pair
		for (int i = 0; i < 4; i += 3) {
			if (cards.get(i).getValue() == cards.get(i + 1).getValue() && cards.get(i).getValue() != three)
				return true;
		}
		return false;
	}

	// tie breaking last card/ high card for straight, flush , straight flush
	// (sorted)
	private int getTieBreaker(PokerHand ph) {
		if (ph.straightAceLow())
			return ph.getValue(3);
		return ph.getValue(4);
	}

	// get fifth card in two pair
	private int getfifth(PokerHand ph) {
		int low = getLowPair(ph);
		int hig = getHighPair(ph);
		int fifth = 0;
		for (int i = 0; i < 5; i++) {
			if (ph.getValue(i) != low && ph.getValue(i) != hig) {
				fifth = ph.getValue(i);
			}
		}
		return fifth;
	}

	// Return the value of the lower pair in a two pair hand (or the pair in a
	// Pair hand).
	// Precondition: ph is a valid PokerHand that is sorted
	private int getLowPair(PokerHand ph) {
		int low = 0;
		for (int i = 0; i < 4; i++) {
			if (ph.getValue(i) == ph.getValue(i + 1)) {
				low = ph.getValue(i);
			}
		}
		return low;
	}

	// Return the value of the larger pair in a two pair hand (or the pair in a
	// Pair hand).
	// Precondition: ph is a valid PokerHand that is sorted
	private int getHighPair(PokerHand ph) {
		int high = 0;
		for (int i = 4; i > 0; i--) {
			if (ph.getValue(i) == ph.getValue(i - 1)) {
				high = ph.getValue(i);
			}
		}
		return high;
	}

	// Hand Type Ranks
	// -------------------
	// Straight Flush = 8
	// Four Kind = 7
	// Full House = 6
	// Flush = 5
	// Straight = 4
	// Three Kind = 3
	// Two Pair = 2
	// One Pair = 1
	// Nothing = 0
	// Compare hand rank and call tie breaker if hand rank is equal
	public int compareTo(PokerHand other) {
		// this.checkdupother(other);
		int thishand = getHandType(this);
		int otherhand = getHandType(other);
		if (thishand > otherhand)
			return 1;
		else if (thishand < otherhand)
			return -1;
		else {
			// straight flush
			if (thishand == 8)
				return this.compareSnFlush(other);
			// four kind
			if (thishand == 7)
				return this.compareTwoPFourK(other);
			// full house
			if (thishand == 6)
				return this.compareFullHouse(other);
			// flush
			if (thishand == 5)
				return this.compareSnFlush(other);
			// straight
			if (thishand == 4)
				return this.compareSnFlush(other);
			// three kind
			if (thishand == 3)
				return this.compareThreeKind(other);
			// two pair
			if (thishand == 2)
				return this.compareTwoPFourK(other);
			// one pair
			if (thishand == 1)
				return this.compareOnePair(other);
			// nothing
			else
				return this.compareNothing(other);
		}
	}

	// Provide a textual version of this PokerHand
	public String toString() {
		String result = "";
		result += cards.get(0) + " ";
		result += cards.get(1) + " ";
		result += cards.get(2) + " ";
		result += cards.get(3) + " ";
		result += cards.get(4) + " ";
		return result;
	}

}
