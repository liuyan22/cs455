
import java.util.Random;
import java.util.ArrayList;
import java.*;
import java.util.Arrays;

//Name: YAN LIU
//USC NetID: liu156
//CSCI455 PA2
//Spring 2018

/*
class SolitaireBoard
The board for Bulgarian Solitaire.  You can change what the total number of cards is for the game
by changing NUM_FINAL_PILES, below.  Don't change CARD_TOTAL directly, because there are only some values
for CARD_TOTAL that result in a game that terminates.
(See comments below next to named constant declarations for more details on this.)
*/


public class SolitaireBoard {

public static final int NUM_FINAL_PILES = 9;
// number of piles in a final configuration
// (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)

public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
// bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
// see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
// the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES

 // Note to students: you may not use an ArrayList -- see assgt description for details.


/**
   Representation invariant:

   <put rep. invar. comment here>

 */

private int[] piles;
private int capacity;
private int theIndexOfLastPile; // store the index of the current last pile

/**
  Creates a solitaire board with the configuration specified in piles.
  piles has the number of cards in the first pile, then the number of cards in the second pile, etc.
  PRE: piles contains a sequence of positive numbers that sum to SolitaireBoard.CARD_TOTAL
*/
public SolitaireBoard(ArrayList<Integer> piles) {
	
	this.capacity = CARD_TOTAL;
	this.piles = new int[capacity]; // use partially filled array to store user prompt, maximum capacity for each pile does not exceed 45
	this.theIndexOfLastPile = piles.size() - 1;
	for (int i = 0; i < piles.size(); i++) {
		this.piles[i] = piles.get(i);
	}
	assert isValidSolitaireBoard();
}


/**
   Creates a solitaire board with a random initial configuration.
*/
public SolitaireBoard() {
	this.capacity = CARD_TOTAL;
	this.piles = new int[this.capacity];
	this.theIndexOfLastPile = -1;

	//generating random cards in each pile to which sum is equal to 45
	Random rand = new Random();
	int sumOfCards = 0;

	while (sumOfCards != CARD_TOTAL) {
		theIndexOfLastPile++;
		this.piles[theIndexOfLastPile] = rand.nextInt(CARD_TOTAL - sumOfCards) + 1;
		sumOfCards += this.piles[theIndexOfLastPile];
	}

	assert isValidSolitaireBoard();
}


/**
   Plays one round of Bulgarian solitaire.  Updates the configuration according to the rules
   of Bulgarian solitaire: Takes one card from each pile, and puts them all together in a new pile.
   The old piles that are left will be in the same relative order as before, 
   and the new pile will be at the end.
 */
public void playRound() {

	// take one card out from each pile to a new pile
	// the new pile will be place one index after the current last pile
	for (int i = 0; i <= theIndexOfLastPile; i++){
		this.piles[i]--;
		piles[theIndexOfLastPile + 1]++;
	}
	// remove the gap zeros and update the index of last pile;
	this.piles = removeZeros(this.piles);
	assert isValidSolitaireBoard();
}	

/**
   Returns true iff the current board is at the end of the game.  That is, there are NUM_FINAL_PILES
   piles that are of sizes 1, 2, 3, . . . , NUM_FINAL_PILES, in any order.
 */
public boolean isDone() {
	if ((theIndexOfLastPile + 1) != NUM_FINAL_PILES) {
		
		// System.out.println("fail condition A");
		// System.out.println(theIndexOfLastPile);
		// System.out.println(NUM_FINAL_PILES);
		return false;
	}
	for (int i = 0; i <= theIndexOfLastPile; i++){
		if (piles[i] > NUM_FINAL_PILES) {
			// System.out.println("fail condition B");
			return false;
		}
		for (int j = i + 1; j <= theIndexOfLastPile; j++){
			if (piles[i] == piles[j]){
				// System.out.println("fail condition C");
				return false;
			}
		}
	}
	
		

	assert isValidSolitaireBoard();
	return true;
	
}


/**
   Returns current board configuration as a string with the format of
   a space-separated list of numbers with no leading or trailing spaces.
   The numbers represent the number of cards in each non-empty pile.
 */
public String configString() {
	String output = Integer.toString(piles[0]);
	for (int i = 1; i <= theIndexOfLastPile; i++) {
		output += " " + Integer.toString(piles[i]);
	}
	assert isValidSolitaireBoard();
	return output;
}


/**
   Returns true iff the solitaire board data is in a valid state
   (See representation invariant comment for more details.)
 */
private boolean isValidSolitaireBoard() {
	int sumOfPiles = 0;
	for (int i = 0; i < this.capacity; i++) {
		if (piles[i] < 0) {
			return false;
		}
		
		sumOfPiles += piles[i];
	}
   return sumOfPiles == CARD_TOTAL;  

}


/**
 * helper: remove gap zeros that generated during the playRound process and update the last pile index
 * Ex: [43 0 2 0 ... 0] ---removeZeros---> [43 2 0 ... 0]
 * @param piles: the piles that need to be remove gap zeros
 * return: the cleand piles(all gap zeroes removed)
 */
private int[] removeZeros(int[] piles) {
	int numberOfZeros = 0;
	int[] newPile = new int[piles.length];
	for (int i = 0; i < piles.length; i++) {
		if (piles[i] != 0) {
			// the pile will be shifted left by #zeros index.
			int correspondingIndex = i - numberOfZeros;
			newPile[correspondingIndex] = piles[i];
			// update the index of last pile during the reassigning.
			this.theIndexOfLastPile = correspondingIndex;
		} else{
			numberOfZeros++;
		}
	}
	return newPile;
}

}