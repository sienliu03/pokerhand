package model;

/**
 *
 * @author Tzuhsien Liu
 * 
 *         Makes Card with suit and rank enums to set 52 cards of a deck
 */

public class Card implements Comparable<Card> {
	private final Rank rank;
	private final Suit suit;

	// Constructor Card with rank and suit
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;

	}

	// suit enum
	public Rank getRank() {
		return rank;
	}

	// rank enum
	public Suit getSuit() {
		return suit;
	}

	// get rank value of the card
	public int getValue() {
		return rank.getValue();
	}

	// Return a textual version of this Card.
	public String toString() {
		// Use these for the solid suit chars
		char suitChar = '\u2663';
		if (suit == Suit.DIAMONDS)
			suitChar = '\u2666';
		if (suit == Suit.HEARTS)
			suitChar = '\u2665';
		if (suit == Suit.SPADES)
			suitChar = '\u2660';

		int value = getValue();
		String cardChar = "" + (value);
		if (value == 10)
			cardChar = "10";
		if (value == 11)
			cardChar = "J";
		if (value == 12)
			cardChar = "Q";
		if (value == 13)
			cardChar = "K";
		if (value == 14)
			cardChar = "A";

		return cardChar + suitChar;
	}

	@Override
	public int compareTo(Card other) {
		return this.getValue() - other.getValue();
	}
}