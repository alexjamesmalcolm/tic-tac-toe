package tictactoe;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class BoardTest {

	@Test
	public void shouldHaveTopLeft() {
		Board underTest = new Board();
		Spot spot = underTest.getSpot("Top", "Left");
		assertNotNull(spot);
	}

	@Test
	public void shouldHaveToStringAllX() {
		Map<Integer, String> board = new TreeMap<Integer, String>();
		for (int i = 0; i < 9; i++) {
			board.put(i, "x");
		}
		Board underTest = new Board(board);
		String expected = "x|x|x\nx|x|x\nx|x|x";
		String returned = underTest.toString();
		assertThat(returned, is(expected));
	}

	@Test
	public void shouldHaveToStringAllO() {
		Map<Integer, String> board = new TreeMap<Integer, String>();
		for (int i = 0; i < 9; i++) {
			board.put(i, "o");
		}
		Board underTest = new Board(board);
		String returned = underTest.toString();
		assertThat(returned, is("o|o|o\no|o|o\no|o|o"));
	}
}
