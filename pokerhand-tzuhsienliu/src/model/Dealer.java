package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Tzuhsien Liu
 * 
 *         Description: Dealer deck class make community cards and add player to
 *         game, decides winner and ties
 */

public class Dealer {

	private ArrayList<Card> deck;
	private ArrayList<Card> com;
	private double pot = 0;
	private ArrayList<Player> players;

	// deck with 52 cards
	public Dealer() {
		players = new ArrayList<Player>();
		deck = new ArrayList<Card>(52);
		for (Suit suit : Suit.values())
			for (Rank rank : Rank.values())
				deck.add(new Card(rank, suit));

		Collections.shuffle(deck);
	}
	public ArrayList<Card> newdeck(){
		for (Suit suit : Suit.values())
			for (Rank rank : Rank.values())
				deck.add(new Card(rank, suit));
		Collections.shuffle(deck);
		return this.deck;
	}
	public void emptydeck(){
		this.deck.clear();
	}
	// deal a card and remove form deck array
	public Card deal() {
		return deck.remove(0);
	}

	// size of deck
	public int size() {
		return deck.size();
	}

	// dealer's money
	public double getPot(int n) {
		this.pot = 2 * n;
		return this.pot;
	}

	// array list of community cards
	public ArrayList<Card> getComm() {
		return this.com;
	}

	// community cards 5 cards from dealer deck
	public ArrayList<Card> community(Dealer deck) {
		com = new ArrayList<Card>();
		for (int i = 0; i < 5; i++)
			com.add(deck.deal());
		Collections.sort(com);
		return com;
	}

	// community cards to string
	public String toString() {
		String result = "";
		result += com.get(0).toString() + " ";
		result += com.get(1).toString() + " ";
		result += com.get(2).toString() + " ";
		result += com.get(3).toString() + " ";
		result += com.get(4).toString();
		return result;
	}

	// add players to a list
	public void addPlayers(Player player) {
		// the game needs at least one player
		players.add(players.size(), player);
	}

	// list of players
	public ArrayList<Player> playerList() {
		return players;
	}

	// decides winner
	public int winnerH(Dealer d, int n) {
		PokerHand win = playerList().get(0).bestHand(d);
		int winner = -1;
		if (playerList().size() == 1) {
			return winner;
		}
		for (int i = 0; i < n; i++) {
			PokerHand next = playerList().get(i).bestHand(d);
			if (win.compareTo(next) <= 0) {
				win = next;
				winner = i;
			}
		}
		return winner;
	}

	// Decides tie
	public List<Integer> tie(Dealer d, int n) {
		PokerHand win = playerList().get(0).bestHand(d);
		List<Integer> ties = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			PokerHand next = playerList().get(i).bestHand(d);
			if (win.compareTo(next) == 0) {
				win = next;
				ties.add(i + 1);
			}

		}

		return ties;
	}

}
