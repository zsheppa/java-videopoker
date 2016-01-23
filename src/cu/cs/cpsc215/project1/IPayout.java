/**
 * Assignment 1: Video Poker
 * Zach Sheppard (zsheppa) and Jose Pedroza (jpedroz)
 * CPSC 215-001
 * 2-22-2015
 */

package cu.cs.cpsc215.project1;

import java.util.Hashtable;

/**
 * IPayout
 * Determines the payout amount a player should receive
 * according to the card game poker's rules.
 *
 */
public class IPayout implements Payout {
	
	private static enum HAND {
		NONE, PAIR, TWO_PAIRS, THREE_KIND, STRAIGHT,
		FLUSH, FULL_HOUSE, FOUR_KIND, STRAIGHT_FLUSH,
		ROYAL_FLUSH
	}
	private Hashtable<HAND, Integer> payouts = new Hashtable<HAND, Integer>(10);
	
	// store the point value of each payout
	public IPayout() {
		this.payouts.put(HAND.NONE, 0);
		this.payouts.put(HAND.PAIR, 1);
		this.payouts.put(HAND.TWO_PAIRS, 2);
		this.payouts.put(HAND.THREE_KIND, 3);
		this.payouts.put(HAND.STRAIGHT, 4);
		this.payouts.put(HAND.FLUSH, 5);
		this.payouts.put(HAND.FULL_HOUSE, 6);
		this.payouts.put(HAND.FOUR_KIND, 25);
		this.payouts.put(HAND.STRAIGHT_FLUSH, 50);
		this.payouts.put(HAND.ROYAL_FLUSH, 250);
	}
	
	public int getPayout(Card[] hand) {
		String name = "nothing";
		int payout = this.payouts.get(HAND.NONE);
		// royal flush: 250 dollars
		// J, Q, K, and A with the same suit
		if (this.isRoyalFlush(hand) == true) {
			name = "Royal Flush";
			payout = this.payouts.get(HAND.ROYAL_FLUSH);
		// straight flush: 50 dollars
		// straight hand (same values) and a flush hand (same suit)
		} else if (this.isStraight(hand) == true && this.sameSuit(hand) == true) {
			name = "Straight Flush";
			payout = this.payouts.get(HAND.STRAIGHT_FLUSH);
		// four of a kind: 25 dollars
		// four cards with the same values
		} else if (this.sameValue(hand, 4) == true) {
			name = "Four of a Kind";
			payout = this.payouts.get(HAND.FOUR_KIND);
		// full house: 6 dollars
		// three cards of the same value and a pair
		} else if (this.isFullHouse(hand) == true) {
			name = "Full House";
			payout = this.payouts.get(HAND.FULL_HOUSE);
		// flush: 5 dollars
		// same suit
		} else if (this.sameSuit(hand) == true) {
			name = "Flush";
			payout = this.payouts.get(HAND.FLUSH);
		// straight: 4 dollars
		// same values
		} else if (this.isStraight(hand) == true) {
			name = "Straight";
			payout = this.payouts.get(HAND.STRAIGHT);
		// three of a kind: 3 dollars
		// three cards with the same value
		} else if (this.sameValue(hand, 3) == true) {
			name = "Three of a Kind";
			payout = this.payouts.get(HAND.THREE_KIND);
		// two pairs: 2 dollars
		// two pairs of cards with the same value
		} else if (this.pair(hand) == 2) {
			name = "Two Pairs";
			payout = this.payouts.get(HAND.TWO_PAIRS);
		// one pair: 1 dollar
		// one pair of cards with the same value
		} else if (this.pair(hand) == 1) {
			name = "Pair";
			payout = this.payouts.get(HAND.TWO_PAIRS);
		}
		System.out.print("They receive: " + name + "; they are awarded " +
				payout + " MagicDollars. ");
		return payout;
	}
	
	// helper functions
	
	// if any card in the hand contains a specific value
	// return true
	private boolean containsValue(Card[] hand, int value) {
		for (int i = 0; i < hand.length; i++) {
			if (hand[i].getValue() == value) {
				return true;
			}
		}
		return false;
	}
	
	// if cards in the hand contain all the values listed
	// in values return true
	private boolean containsValue(Card[] hand, int[] values) {
		for (int v = 0; v < values.length; v++) {
			if (this.containsValue(hand, values[v]) == false) {
				return false;
			}
		}
		return true;
	}
	
	// if the hand contains consecutive values return true
	private boolean consecutive(Card[] hand) {
		for (int i = 0; i < hand.length; i++) {
			if ((i + 1) <= hand.length) {
				int val1 = hand[i+1].getValue();
				int val0 = hand[i].getValue();
				if ((val1 - val0) != 1) {
					return false;
				}
			}
		}
		return true;
	}
	
	// find the number of pairs in a hand
	private int pair(Card[] hand) {
		int pairs = 0;
		for (int i = 0; i < hand.length; i++) {
			for (int z = (i + 1); z < hand.length; z++) {
				if (hand[i].getValue() == hand[z].getValue()) {
					pairs++;
				}
			}
		}
		return pairs;
	}
	
	// if any of the suits in the hand are
	// different return false
	private boolean sameSuit(Card[] hand) {
		for (int i = 0; i < hand.length; i++) {
			if (hand[0].getSuit() != hand[i].getSuit()) {
				return false;
			}
		}
		return true;
	}
	
	// if a hand contains (count) cards with the
	// same value return true
	private boolean sameValue(Card[] hand, int count) {
		int same = 0;
		for (int i = 0; i < hand.length; i++) {
			for (int z = (i + 1); z < hand.length; z++) {
				if (hand[i].getValue() == hand[z].getValue()) {
					same++;
				}
			}
		}
		return (same == count);
	}
	
	// hand functions
	
	// three cards of the same value and a pair
	private boolean isFullHouse(Card[] hand) {
		if (this.sameValue(hand, 3) == true) {
			return (this.pair(hand) == 1);
		}
		return false;
	}
	
	// if cards are 10, J, Q, K, A and are the same suit
	private boolean isRoyalFlush(Card[] hand) {
		if (this.sameSuit(hand) == true) {
			int[] values = new int[]{11, 12, 13, 14};
			if (this.containsValue(hand, values) == true) {
				return true;
			}
		}
		return false;
	}
	
	// if cards have consecutive values
	private boolean isStraight(Card[] hand) {
		return (this.sameSuit(hand) == true && this.consecutive(hand) == true);
	}

}
