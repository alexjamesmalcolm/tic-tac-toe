package tictactoe;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {
	
	private Map<Integer, String> allXBoard;
	private Map<Integer, String> allOBoard;

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
	}

	@Test
	public void shouldHaveTopLeft() {
		Board underTest = new Board();
		Spot spot = underTest.getSpot("Top", "Left");
		assertNotNull(spot);
	}

	@Test
	public void shouldHaveToStringAllX() {
		Board underTest = new Board(allXBoard);
		String expected = "x|x|x\nx|x|x\nx|x|x";
		String returned = underTest.toString();
		assertThat(returned, is(expected));
	}

	@Test
	public void shouldHaveToStringAllO() {
		Board underTest = new Board(allOBoard);
		String returned = underTest.toString();
		assertThat(returned, is("o|o|o\no|o|o\no|o|o"));
	}
	
	@Test
	public void shouldHaveMiddleCenterBeX() {
		allOBoard.put(4, "x");
		Board underTest = new Board(allOBoard);
		Spot spot = underTest.getSpot("Middle", "Center");
		String symbol = spot.getSymbol();
		assertThat(symbol, is("x"));
	}
}
