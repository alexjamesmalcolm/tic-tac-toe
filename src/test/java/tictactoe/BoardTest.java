package tictactoe;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class BoardTest {
	
	@Test
	public void shouldHaveTopLeftBeSpot() {
		Board underTest = new Board();
		Spot spot = underTest.getSpot("Top", "Left");
		assertNotNull(spot);
	}
}
