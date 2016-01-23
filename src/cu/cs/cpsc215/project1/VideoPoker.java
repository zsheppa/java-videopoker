/**
 * Assignment 1: Video Poker
 * Zach Sheppard (zsheppa) and Jose Pedroza (jpedroz)
 * CPSC 215-001
 * 2-17-2015
 */

package cu.cs.cpsc215.project1;

import java.util.Scanner;

/**
 * MainDriver
 * Creates and shuffles the Deck, creates Player instances,
 * and deals each hand to a player. Reiterates for
 * ten rounds and then shows the score.
 *
 */
public class VideoPoker {

	private static final int ROUNDS = 10;
	private static Scanner scanner;
	
	public static void main(String[] args) {
		System.out.println("Welcome to Video Poker!");
		// create the deck and shuffle
		Deck deck = new IDeck();
		deck.shuffle();
		// initialize the scanner
		scanner = new Scanner(System.in);
		// create player instances
		Player player = new IPlayer("Jose", deck);
		Player AI1 = new IPlayer("Zach", deck);
		Player AI2 = new IPlayer("Quagmire", deck);
		Player AI3 = new IPlayer("Stewie", deck);
		for (int i = 0; i < ROUNDS; i++) {
			// start each round with output
			System.out.println("Round " + i);
			System.out.println("========");
			// distribute a hand to each player
			distribute(player, AI1, AI2, AI3);
			// prompt the user if they want to discard any cards
			promptDiscard(player);
			// determine the payouts each player gets
			payout(player, AI1, AI2, AI3);
		}
		scanner.close();
		// announce the points and the winner
		winners(player, AI1, AI2, AI3);
	}
	
	// distributes cards to each player
	private static void distribute(Player... players) {
		for (Player player : players) {
			player.dealHand();
		}
	}
	
	// prompts the user if they want to discard any cards
	// 0: discard card
	// 1: keep card
	// input: 11111, keep all cards
	private static void promptDiscard(Player user) {
		boolean enabled = true;
		user.printHand();
		System.out.print("\nWhich cards do you want to discard? ");
		String input = scanner.nextLine();
		if (input.length() != Player.HAND_SIZE) {
			System.out.println("Invalid input; skipping discard");
			return;
		}
		// declare the discards array and counter of that array
		int count = 0;
		Card[] discards = new Card[5];
		// for each 1 or 0 input by the user find the card the
		// user wants to discard
		for (int i = 0; i < Player.HAND_SIZE; i++) {
			int tmp = Integer.parseInt(input.substring(i, (i + 1)));
			if (tmp == 0) {
				discards[count++] = user.getCard(i);
			} else if (tmp != 1) {
				System.out.println("Invalid input; skipping discard");
				enabled = false;
				break;
			}
		}
		if (enabled == true) {
			user.discard(discards, count);
		}
	}
	
	// determine the payouts each player gets
	private static void payout(Player... players) {
		for (Player player : players) {
			player.payout();
		}
	}

	private static void winners(Player... players) {
		Player winner = players[0];
		for (Player player : players) {
			System.out.println(player.getName() + " earned " + player.getScore() +
					" MagicDollars.");
			if (winner.getScore() < player.getScore()) {
				winner = player;
			}
		}
		System.out.println(winner.getName() + " wins!");
	}
}
