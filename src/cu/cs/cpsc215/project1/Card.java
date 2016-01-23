/**
 * Assignment 1: Video Poker
 * Zach Sheppard (zsheppa) and Jose Pedroza (jpedroz)
 * CPSC 215-001
 * 2-22-2015
 */

package cu.cs.cpsc215.project1;

/**
 * Card
 * Defines a Card class representing an individual card
 * in a 52-card deck.
 *
 */
public interface Card {
	String getSuit();
	void setSuit(String suit);
	int getValue();
	void setValue(int value);
	
	boolean equals(Card other);
	String toString();
	//boolean winner(Card otherC);
}
