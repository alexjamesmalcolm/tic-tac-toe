package tictactoe;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.TreeMap;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

public class RobotTest {

	private TreeMap<Integer, String> allXBoard;
	private TreeMap<Integer, String> allOBoard;
	private TreeMap<Integer, String> emptyBoard;

	@Before
	public void setup() {
		allXBoard = new TreeMap<>();
		for (int i = 0; i < 9; i++) {
			allXBoard.put(i, "x");
		}
		allOBoard = new TreeMap<>();
		for (int i = 0; i < 9; i++) {
			allOBoard.put(i, "o");
		}
		emptyBoard = new TreeMap<>();
		for (int i = 0; i < 9; i++) {
			emptyBoard.put(i, "");
		}
	}

	@Test
	public void shouldRateBoardsHighlyInWhichItHasWon() {
		String symbol = "x";
		Robot underTest = new Robot(symbol);
		Board board = new Board(allXBoard);
		int rating = underTest.rate(board);
		assertThat(rating, is(10));
	}

	@Test
	public void shouldRateBoardsHighly() {
	}
}
