package tictactoe;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Robot {

	private String symbol;

	public Robot(String symbol) {
		this.symbol = symbol;
	}

	public int rate(Board board) {
		if (symbol.equals("x")) {
			if (board.hasXWon()) {
				return 10;
			} else {
				return 0;
			}
		} else {
			if (board.hasOWon()) {
				return 10;
			} else {
				return 0;
			}
		}
	}

	public Map<Integer, Integer> rateDecision(Board board) {
		Set<Integer> choices = board.getChoices();
		Map<Integer, Integer> ratings = new HashMap<>();
		for (Integer choice : choices) {
			Board currentBoard = board.clone();
			currentBoard.pick(choice, symbol);
			int rating = rate(currentBoard);
			ratings.put(choice, rating);
		}
		return ratings;
	}

}
