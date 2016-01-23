/**
 * Assignment 1: Video Poker
 * Zach Sheppard (zsheppa) and Jose Pedroza (jpedroz)
 * CPSC 215-001
 * 2-22-2015
 */

package cu.cs.cpsc215.project1;

/**
 * Player
 * Defines a Player class that holds a Player's score and
 * current deck.
 *
 */
public interface Player {
	static final int HAND_SIZE = 5;
	
	void dealHand();
	void discard(Card[] cards, int count);
	Card getCard(int loc);
	String getName();
	int getScore();
	void payout();
	void printHand();
}
