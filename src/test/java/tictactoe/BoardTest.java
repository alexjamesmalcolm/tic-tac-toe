package tictactoe;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

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
		Board underTest = new Board();
		String expected = "x|x|x\nx|x|x\nx|x|x";
		String returned = underTest.toString();
		assertThat(returned, is(expected));
	}
}
