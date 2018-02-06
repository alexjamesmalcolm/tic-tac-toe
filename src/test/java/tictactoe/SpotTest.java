package tictactoe;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SpotTest {
	
	@Test
	public void shouldBeEmpty() {
		String symbol = "";
		Spot underTest = new Spot(symbol);
		String returnedSymbol = underTest.getSymbol();
		assertThat(returnedSymbol, is(""));
	}
	
	@Test
	public void shouldBeX() {
		String symbol = "x";
		Spot underTest = new Spot(symbol);
		String returnedSymbol = underTest.getSymbol();
		assertThat(returnedSymbol, is("x"));
	}
}
