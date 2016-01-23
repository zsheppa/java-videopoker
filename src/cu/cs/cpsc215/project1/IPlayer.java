/**
 * Assignment 1: Video Poker
 * Zach Sheppard (zsheppa) and Jose Pedroza (jpedroz)
 * CPSC 215-001
 * 2-17-2015
 */

package cu.cs.cpsc215.project1;

/**
 * Player
 * Stores a player's hand and name.
 *
 */
public class IPlayer implements Player {
	
	private Deck deck;
	private Card[] hand = new ICard[5];
	private int handSize = 0;
	private Payout payout = new IPayout();
	private String pName = "";
	private int score = 0;
	
	public IPlayer(String name, Deck deck) {
		this.pName = name;
		this.deck = deck;
		this.handSize = 0;
	}
	
	// draws a number of cards from the deck
	// and stores them in the Player's hand
	private void deal(int num) {
		// if the hand is full don't do anything
		if (this.handSize == HAND_SIZE) {
			return;
		}
		for (int i = 0; i < num; i++) {
			this.hand[this.handSize] = this.deck.draw();
			this.handSize++;
		}
	}
	
	// assigns 5 cards to the player
	public void dealHand() {	
		this.deal(5);
	}
	
	// discards an array of cards in the current hand
	public void discard(Card[] cards, int count) {
		for (int i = 0; i < HAND_SIZE; i++) {
			for (int z = 0; z < count; z++) {
				// if the current card in the hand
				// matches any of the cards to be discarded
				// re-draw the card
				if (cards[z].equals(this.hand[i])) {
					Card tmp = this.deck.draw();
					while (tmp == this.hand[i]) {
						tmp = this.deck.draw();
					}
					this.hand[i] = tmp;
				}
			}
		}
	}
	
	// returns the card at a location in the deck
	public Card getCard(int loc) {
		return this.hand[loc];
	}
	
	// returns the player's name
	public String getName() {
		return this.pName;
	}
	
	// returns the player's score
	public int getScore() {
		return this.score;
	}
	
	// increments the player's score by the payout
	// they receive from their hand
	public void payout() {
		this.printHand();
		this.score += this.payout.getPayout(this.hand);
		System.out.println("Their score is: " + this.score);
	}
	
	// outputs the hand
	public void printHand() {
		System.out.print(this.pName + " has: ");
		for (int i = 0; i < HAND_SIZE; i++) {
			System.out.print(this.hand[i].toString());
			if ((i + 1) != HAND_SIZE) {
				System.out.print(", ");
			}
		}
		System.out.print(". ");
	}

}
 