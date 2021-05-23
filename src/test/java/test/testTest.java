package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import horizons_mmet_c1_1.horizons_mmet_c1_1.Player;
import horizons_mmet_c1_1.horizons_mmet_c1_1.Square;

class testTest {
	
	int x = 0;
	int y = 0;
	public Player playerTest = new Player();
	public Player playerTest2 = new Player();
	public Square squareTest = new Square(x,y);

	@Test
	public void BoardGameTest() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void GameBaseTest() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void GameStepsTest() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void HorizonsTest() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void PlayerTest() {
		assertEquals("J1",new Player());
	}
	
	@Test
	public void SquareTest() {
		x = 2 ; y= 2;
		assertEquals(new Square(x,y), squareTest);
	}

}
