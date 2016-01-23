/**
 * Assignment 1: Video Poker
 * Zach Sheppard (zsheppa) and Jose Pedroza (jpedroz)
 * CPSC 215-001
 * 2-22-2015
 */

package cu.cs.cpsc215.project1;

/**
 * Payout
 * Defines a Payout class that returns the number of MagicDollars
 * a user will receive for the specified hand.
 *
 */
public interface Payout {
	int getPayout(Card[] hand);
}
