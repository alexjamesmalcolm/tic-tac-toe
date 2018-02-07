package tictactoe;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Map;
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
	public void shouldRateBoardsLowlyInWhichItHasLost() {
		String symbol = "o";
		Robot underTest = new Robot(symbol);
		Board board = new Board(allXBoard);
		int rating = underTest.rate(board);
		assertThat(rating, is(0));
	}

	@Test
	public void shouldDecideHowWellRatedChoices() {
		emptyBoard.put(0, "x");
		emptyBoard.put(3, "x");
		String symbol = "x";
		Robot underTest = new Robot(symbol);
		Board board = new Board(emptyBoard);
		Map<Integer, Integer> ratings = underTest.rateDecision(board);
		Map<Integer, Integer> expectedRatings = new TreeMap<>();
		expectedRatings.put(1, 0);
		expectedRatings.put(2, 0);
		expectedRatings.put(4, 0);
		expectedRatings.put(5, 0);
		expectedRatings.put(6, 10);
		expectedRatings.put(7, 0);
		expectedRatings.put(8, 0);
		System.out.println(ratings);
		assertThat(ratings, is(expectedRatings));
	}
}
