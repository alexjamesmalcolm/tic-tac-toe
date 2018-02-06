package tictactoe;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	private Map<Integer, String> allXBoard;
	private Map<Integer, String> allOBoard;
	private Map<Integer, String> emptyBoard;

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
	public void shouldHaveTopLeft() {
		Board underTest = new Board();
		Spot spot = underTest.getSpot(0, 0);
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

	@Test
	public void shouldHaveMiddleCenterBeO() {
		allXBoard.put(4, "o");
		Board underTest = new Board(allXBoard);
		Spot spot = underTest.getSpot("Middle", "Center");
		String symbol = spot.getSymbol();
		assertThat(symbol, is("o"));
	}

	@Test
	public void shouldHaveLeftCenterBeX() {
		allOBoard.put(3, "x");
		Board underTest = new Board(allOBoard);
		Spot spot = underTest.getSpot("Left", "Center");
		String symbol = spot.getSymbol();
		assertThat(symbol, is("x"));
	}

	@Test
	public void shouldHaveRightCenterBeX() {
		allOBoard.put(5, "x");
		Board underTest = new Board(allOBoard);
		Spot spot = underTest.getSpot("Right", "Center");
		String symbol = spot.getSymbol();
		assertThat(symbol, is("x"));
	}

	@Test
	public void shouldHaveLeftTopBeX() {
		allOBoard.put(0, "x");
		Board underTest = new Board(allOBoard);
		Spot spot = underTest.getSpot("Left", "Top");
		String symbol = spot.getSymbol();
		assertThat(symbol, is("x"));
	}
	
	@Test
	public void shouldHaveMiddleTopBeX() {
		allOBoard.put(1, "x");
		Board underTest = new Board(allOBoard);
		Spot spot = underTest.getSpot("Middle", "Top");
		String symbol = spot.getSymbol();
		assertThat(symbol, is("x"));
	}
	
	@Test
	public void shouldHaveXWon() {
		emptyBoard.put(0, "x");
		emptyBoard.put(1, "x");
		emptyBoard.put(2, "x");
		Board underTest = new Board(emptyBoard);
		boolean status = underTest.hasXWon();
		assertTrue(status);
	}
	
	@Test
	public void shouldHaveXNotWin() {
		emptyBoard.put(0, "x");
		emptyBoard.put(2, "x");
		Board underTest = new Board(emptyBoard);
		boolean status = underTest.hasXWon();
		assertFalse(status);
	}
}