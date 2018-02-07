package tictactoe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Board {

	private Map<Integer, Spot> board = new HashMap<Integer, Spot>();

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
	
	public Board(Board another) {
		this.board = another.board;
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

	@Override
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
		return hasSomeoneWon("x");
	}

	public boolean hasOWon() {
		return hasSomeoneWon("o");
	}

	private boolean hasSomeoneWon(String winSymbol) {
		Collection<Spot> row;
		boolean status = true;
		for (int i = 0; i < 3; i++) {
			row = getRow(i);
			status = true;
			for (Spot spot : row) {
				if (!spot.getSymbol().equals(winSymbol)) {
					status = false;
				}
			}
			if (status) {
				return status;
			}
		}
		Collection<Spot> col;
		for (int i = 0; i < 3; i++) {
			col = getCol(i);
			status = true;
			for (Spot spot : col) {
				if (!spot.getSymbol().equals(winSymbol)) {
					status = false;
				}
			}
			if (status) {
				return status;
			}
		}
		Collection<Spot> diag;
		for (int i = 0; i < 2; i++) {
			diag = getDiag(i);
			status = true;
			for (Spot spot : diag) {
				if (!spot.getSymbol().equals(winSymbol)) {
					status = false;
				}
			}
			if (status) {
				return status;
			}
		}
		return status;
	}

	private Collection<Spot> getDiag(int diagNum) {
		Collection<Spot> diag = new ArrayList<>();
		if (diagNum == 0) {
			for (int i = 0; i < 3; i++) {
				// 0, 4, 8
				int num = i * 4;
				diag.add(board.get(num));
			}
		}
		if (diagNum == 1) {
			for (int i = 0; i < 3; i++) {
				// 2, 4, 6
				int num = 2 + i * 2;
				diag.add(board.get(num));
			}
		}
		return diag;
	}

	private Collection<Spot> getCol(int colNum) {
		Collection<Spot> col = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			int num = colNum + i * 3;
			col.add(board.get(num));
		}
		return col;
	}

	private Collection<Spot> getRow(int rowNum) {
		Collection<Spot> row = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			int num = i + rowNum * 3;
			row.add(board.get(num));
		}
		return row;
	}

	public Set<Integer> getChoices() {
		Set<Integer> result = new HashSet<>();
		for (int i = 0; i < 9; i++) {
			Spot spot = board.get(i);
			String symbol = spot.getSymbol();
			if (symbol.equals("")) {
				result.add(i);
			}
		}
		return result;
	}

	public void pick(int key, String value) {
		if (board.get(key).getSymbol().equals("")) {
			Spot spot = new Spot(value);
			board.put(key, spot);
		}
	}

	public Spot getSpot(int key) {
		return board.get(key);
	}

	@Override
	public Board clone() {
		return new Board();
	}

	public int rate(String symbol) {
		if (symbol.equals("x")) {
			if (hasXWon()) {
				return 10;
			}
			if (hasOWon()) {
				return -10;
			}
		}
		if (symbol.equals("o")) {
			if (hasXWon()) {
				return -10;
			}
			if (hasOWon()) {
				return 10;
			}
		}
		return 0;
	}

	public int rate(String symbol, int position) {
		Board newBoard = new Board(this);
		newBoard.pick(position, symbol);
		int rating = newBoard.rate(symbol);
		return rating;
	}
}
