/**
 * Assignment 1: Video Poker
 * Zach Sheppard (zsheppa) and Jose Pedroza (jpedroz)
 * CPSC 215-001
 * 2-22-2015
 */

package cu.cs.cpsc215.project1;

/**
 * Deck
 * Defines a Deck class to hold all 52 Card objects and issue them
 * out to Player objects via draw().
 *
 */
public interface Deck {
	static final int DECK_SIZE = 52;
	
	Card draw();
	void shuffle();
}
