package tictactoe;

import java.util.ArrayList;
import java.util.Collection;
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

	public Spot getSpot(String col, String row) {
		int colOutput = -1;
		if (col.equals("Left")) {
			colOutput = 0;
		}
		if (col.equals("Center") || col.equals("Middle")) {
			colOutput = 1;
		}
		if (col.equals("Right")) {
			colOutput = 2;
		}

		int rowOutput = -1;
		if (row.equals("Top")) {
			rowOutput = 0;
		}
		if (row.equals("Center") || row.equals("Middle")) {
			rowOutput = 1;
		}
		if (row.equals("Bottom")) {
			rowOutput = 2;
		}
		return getSpot(colOutput, rowOutput);
	}

	public Spot getSpot(int col, int row) {
		return board.get(col + row * 3);
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

	public boolean hasXWon() {
		Collection<Spot> row;
		boolean status = true;
		for (int i = 0; i < 3; i++) {
			row = getRow(i);
			status = true;
			for (Spot spot : row) {
				if (!spot.getSymbol().equals("x")) {
					status = false;
				}
			}
			if (status) {
				return status;
			}
		}
		return status;
	}

	private Collection<Spot> getRow(int i) {
		Collection<Spot> row = new ArrayList<>();
		row.add(board.get(0 + i * 3));
		row.add(board.get(1 + i * 3));
		row.add(board.get(2 + i * 3));
		return row;
	}

}
