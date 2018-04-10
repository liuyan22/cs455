
import java.util.Scanner;
import java.util.*;
import java.util.ArrayList;

//Name:
//USC NetID:
//CSCI455 PA2
//Spring 2018


/**
<add main program comment here>
*/

public class BulgarianSolitaireSimulator {

	public static final int TOTAL_CARDS = 45;

	public static void main(String[] args) {
  
		boolean singleStep = false;
		boolean userConfig = false;

		Scanner scanner = new Scanner(System.in);

		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-u")) {
				userConfig = true;
			}
			else if (args[i].equals("-s")) {
				singleStep = true;
		    }
	    }

		SolitaireBoard board = initilizeBoard(userConfig, scanner);
		simulateBulgarianSolitaire(singleStep, board, scanner);
		
	}

	/**
	 * Initilize the Solitaire Board based on userConfig-mode or auto-generate-mode.
	 * @param userConfig: the configuration parameter indicate if is userConfig mode
	 * @param scannder: the Scanner object used for system I/O
	 * @return the initilized Solicatire Board based on congiguration
	 */
	private static SolitaireBoard initilizeBoard(boolean userConfig, Scanner scanner) {
		if (userConfig) {
			System.out.println("Number of total cards is 45");
		    System.out.println("You will be entering the initial configuration of the cards (i.e., how many in each pile)");
		    System.out.println("Please enter a space-separated list of positive integers followed by newline");

			// Read input from user and reprompt if error found:
			String input = scanner.nextLine();
			while (!isValidInput(input)) {
				System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be 45");
				System.out.println("Please enter a space-separated list of positive integers followed by newline:");
				input = scanner.nextLine();
			}

			ArrayList<Integer> initialPiles = buildArrayList(input);
			return new SolitaireBoard(initialPiles);
			
		} else {
			return new SolitaireBoard();
		}
	}

	/**
	 * Simulate the Bulgarian Solitare process
	 * @param singleStep: the simulate configuration of playRound
	 * @param board: the SolitaireBoard to simulate on
	 * @param scanner: the scanner object used for pause for singlestep
	 */
	private static void simulateBulgarianSolitaire(boolean singleStep, SolitaireBoard board, Scanner scanner) {
		System.out.println("Initial configuration: " + board.configString());
		int counter = 1;
		while (!board.isDone()) {
			board.playRound();
			System.out.printf("[%d] Current configuration: %s\n", counter, board.configString());
			counter++;
			if (singleStep) {
				System.out.print("<Type return to continue>");
				scanner.nextLine(); // waiting for return to continue the loop
			}
		}
		System.out.println("Done!");
	}

	/**
	 * helper: check if the user's input is a valid initial pile
	 * @param input: the pile-literal that the user input
	 * @return: return true if user's input is valid, false otherwise
	 */
	private static boolean isValidInput(String input) {
		int sum = 0;
		String[] piles = input.split(" ");
		for (int i = 0; i < piles.length; i++) {
			try {
				int pile = Integer.parseInt(piles[i]);
				if (pile <= 0) {
					// invalid if a pile is zero or negative number
					return false; 
				}
				sum += pile;
			}
			catch (NumberFormatException ex) {
				// return false if user typed alphabets or other invalid char
				return false; 
			}
		}
		return sum == TOTAL_CARDS; // invalid if the sum of piles is incorrect
	}

	/** 
	 * helper: build an arrayList to represents piles
	 * @param input: the user input, the pile-literal string
	 * @return: the build arrayList for piles
	 */
	private static ArrayList<Integer> buildArrayList(String input) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		String[] piles = input.split(" ");
		for (int i = 0; i < piles.length; i++) {
			result.add(Integer.parseInt(piles[i]));
		}
		return result;
	}
}