package tictactoe;

import java.util.Map;
import java.util.TreeMap;

public class Board {

	private Map<Integer, Spot> board = new TreeMap<Integer, Spot>();

	public Board(Map<Integer, String> board) {
		for (int i = 0; i < 9; i++) {
			String currentSymbol = board.get(i);
			Spot spot = new Spot(currentSymbol);
			this.board.put(i, spot);
		}
	}

	public Board() {
		this(new TreeMap<>());
	}

	public Spot getSpot(String string, String string2) {
		return new Spot("");
	}

	public String toString() {
		String output = "";
		for (int i = 0; i < 9; i++) {
			String currentSymbol = board.get(i).getSymbol();
			output += currentSymbol;
			if ((i + 1) % 3 == 0 && i < 8) {
				output += "\n";
			} else if (i < 8) {
				output += "|";
			}
		}
		return output;
	}

}
