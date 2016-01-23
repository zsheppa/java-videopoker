/**
 * Assignment 1: Video Poker
 * Zach Sheppard (zsheppa) and Jose Pedroza (jpedroz)
 * CPSC 215-001
 * 2-17-2015
 */

package cu.cs.cpsc215.project1;

import java.util.Random;

/**
 * Deck
 * Contains a list of Card objects to represent a deck of cards.
 * Implements a shuffling method to randomly shuffle the cards
 * and assign them to players.
 *
 */

public class IDeck implements Deck {
	
	private Card[] deck = new ICard[DECK_SIZE];
	private int deckSize = DECK_SIZE;
	private String[] suits = new String[] {
			"Clubs", "Diamonds", "Hearts", "Spades"
	};
	
	public IDeck() {
		int count = 0;
		for (int value = 2; value <= 14; value++) {		
			for (int suit = 0; suit < 4; suit++) {
				if (count < this.deckSize) {
					// cycles through the deck and appends a card
					this.deck[count++] = new ICard(
						this.suits[suit], value
					);
				}
			}
		}	
	}
	
	
	//draws a card at random and removes it from the deck
	public Card draw() {
		Random rand = new Random();
		int n  = rand.nextInt(this.deckSize);
		Card drawn = this.deck[n];
		for (int i=n; i < 50; i++) {
			this.deck[i] = this.deck[i+1];
		}
		this.deckSize--;
		return drawn;
	}
	
	
	public void shuffle() {
		// for cutting the deck
		Card[] deckCutA = new ICard[26];
		Card[] deckCutB = new ICard[26];
		Random rand = new Random();
		// cuts and shuffles the deck a random amount of times
		for (int h = 0; h < rand.nextInt(25)+4; h++) {
			int j = 0;
			for (int i = 0; i < 26; i++)
				deckCutA[i] = this.deck[i];
			for (int i = 0; i < 26; i++)
				deckCutB[i] = this.deck[i+26];
			for (int i = 0; i < 52 ; i++) {
				this.deck[i] = deckCutB[j];
				i++;
				this.deck[i] = deckCutA[j];
				j++;
			}
		}
	}
	
}
