/**
 * Assignment 1: Video Poker
 * Zach Sheppard (zsheppa) and Jose Pedroza (jpedroz)
 * CPSC 215-001
 * 2-17-2015
 */

package cu.cs.cpsc215.project1;

/**
 * ICard
 * Declares the Card class that is used to hold suit and
 * card value properties and is initialized in a list
 * in the Deck class.
 *
 */
public class ICard implements Card {
	
	private String suit;
	private int value;
	
	// initializes a card from the values passed
	public ICard(String suit, int value) {
		this.suit = suit;
		this.value = value;
	}
	
	// initializes a card by copying
	public ICard(Card other) {
		this.suit = other.getSuit();
		this.value = other.getValue();
	}
	
	public boolean equals(Card other) {
		if (this.getValue() == other.getValue() &&
				this.getSuit() == other.getSuit()) {
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {	
		return ("" + this.getValueStr() + " of " + this.suit);
	}
	
	public String getSuit() {
		return this.suit;
	}
	
	public int getValue() {
		return this.value;
	}
	
	// parses the value of a card
	private String getValueStr() {
		switch(this.value) {
			case 11:
				return "Jack";
			case 12:
				return "Queen";
			case 13:
				return "King";
			case 14:
				return "Ace";
			default:
				return Integer.toString(this.value);
		}
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

}
