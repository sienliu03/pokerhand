package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Tzuhsien Liu
 * 
 *         Description: A game of Arizona Hold-Em, calls calls PokerHand,
 *         Dealer, Player to play game.
 * 
 */

public class ArizonaHoldEm {

	private final static double FUNDS = 100;

	public static void main(String args[]) {
		Scanner reader = new Scanner(System.in);
		System.out.println("How many players: ");
		int n = reader.nextInt();
		char ans = 'y';
		if (n >= 1 && n < 24) {
			Dealer dealer = new Dealer();
			AddNPlayers(dealer, n);
			while (ans == 'y' || ans == 'Y') {
				dealer.emptydeck();
				dealer.newdeck();
				round(dealer, n);
				System.out.println();

				Scanner scan = new Scanner(System.in);
				System.out.println("Play another game?[Y/N] ");
				ans = scan.nextLine().charAt(0);
			}
		}
	}

	// Add n number of players
	public static void AddNPlayers(Dealer dealer, int n) {
		for (int i = 0; i < n; i++) {
			Player np = new Player("Player" + (i + 1), FUNDS);
			dealer.addPlayers(np);
		}
	}

	// deal one round and decide winner, game helper
	public static void round(Dealer dealer, int n) {
		dealer.community(dealer);
		System.out.println("Community Cards: " + dealer.toString());
		System.out.println("++++++++++++++++++++++++++++++++++++");
		for (int i = 0; i < n; i++) {
			Player player = dealer.playerList().get(i);
			dealer.playerList().get(i).pay();
			player.dealCards(dealer);
			System.out.println(player.getPlayer() + ": " + player.getMoney() + "- " + player.toStringHand());
			System.out.println("   BestHand: " + player.bestHand(dealer) + "   - "
					+ player.bestHand(dealer).getHandTypePrint(player.bestHand(dealer)));
			System.out.println();
		}

		int win = dealer.winnerH(dealer, n);
		List<Integer> ties = new ArrayList<Integer>();
		ties = dealer.tie(dealer, n);
		if (win != -1) {
			// if winner
			Player winner = dealer.playerList().get(win);
			winner.winnings(dealer, n);
			System.out.println("Winner: " + winner.getPlayer() + " " + winner.getMoney());
			System.out.println("++++++++++++++++++++++++++++++++++++");
			System.out.println(
					winner.bestHand(dealer).getHandTypePrint(winner.bestHand(dealer)) + " " + winner.bestHand(dealer));
		} else {
			// if tie
			if (ties.size() > 1) {
				System.out.println("Winning hands (tie)");
				System.out.println("++++++++++++++++++++++++++++++++++++");
				if (ties.size() > 1) {
					for (int i = 0; i < ties.size(); i++) {
						Player tie = dealer.playerList().get(ties.get(i));
						System.out.println(tie.bestHand(dealer).getHandTypePrint(tie.bestHand(dealer)) + " "
								+ tie.bestHand(dealer));

					}
				}
			}
		}
	}

}
