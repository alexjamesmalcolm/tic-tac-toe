package tictactoe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Board {

	private Map<Integer, Spot> board = new HashMap<Integer, Spot>();

	private Map<Integer, Spot> getBoard() {
		return board;
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

	public Spot getSpot(int key) {
		return board.get(key);
	}

	public Spot getSpot(int col, int row) {
		return board.get(col + row * 3);
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
		this.board.putAll(another.getBoard());
	}

	@Override
	public String toString() {
		String output = "";
		for (int i = 0; i < 9; i++) {
			String currentSymbol = board.get(i).getSymbol();
			if (currentSymbol.equals("")) {
				output += " ";
			} else {
				output += currentSymbol;
			}
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

	public void pick(int key, String value) {
		if (board.get(key).getSymbol().equals("")) {
			Spot spot = new Spot(value);
			board.put(key, spot);
		}
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

	public Map<Integer, Integer> rateChoices(String symbol, boolean myTurn) {
		Map<Integer, Integer> result = new TreeMap<>();
		Set<Integer> choices = getChoices();
		for (int choice : choices) {
			int rating = 0;
			if (myTurn) {
				rating = rate(symbol, choice);
			} else {
				if (symbol.equals("x")) {
					rating = -1 * rate("o", choice);
				}
				if (symbol.equals("o")) {
					rating = -1 * rate("x", choice);
				}
			}
			if (rating != 0) {
				result.put(choice, rating);
			}
		}
		return result;
	}

	public Set<Integer> getGoodChoices(String symbol) {
		Map<Integer, Integer> choices = rateChoices(symbol, true);
		Set<Integer> goodChoices = new TreeSet<Integer>();
		for (Entry<Integer, Integer> choice : choices.entrySet()) {
			if(choice.getValue() == 10) {
				goodChoices.add(choice.getKey());
			}
		}
		return goodChoices;
	}

}
