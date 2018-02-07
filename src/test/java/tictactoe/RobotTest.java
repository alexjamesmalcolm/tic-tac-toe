package tictactoe;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

public class RobotTest {

	private Map<Integer, String> emptyBoard;

	@Before
	public void setup() {
		emptyBoard = new TreeMap<>();
		for (int i = 0; i < 9; i++) {
			emptyBoard.put(i, "");
		}
	}

	// @Test
	// public void should() {
	// emptyBoard.put(0, "o");
	// emptyBoard.put(1, "o");
	// Board board = new Board(emptyBoard);
	// Robot underTest = new Robot(board, "x");
	// Map<Integer, Integer> ratings = underTest.decideSomething();
	// Map<Integer, Integer> expected = new TreeMap<>();
	// expected.put(3, -10);
	// expected.put(4, -10);
	// expected.put(5, -10);
	// expected.put(6, -10);
	// expected.put(7, -10);
	// expected.put(8, -10);
	// assertThat(ratings, is(expected));
	// }

//	@Test
//	public void shouldPickBestMove() {
//		emptyBoard.put(0, "o");
//		emptyBoard.put(1, "o");
//		 Board board = new Board(emptyBoard);
//		 Robot underTest = new Robot(board, "o");
//		 underTest.getBest
//	}
}