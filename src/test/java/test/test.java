package test;

import org.junit.jupiter.api.Test;

import horizons_mmet_c1_1.horizons_mmet_c1_1.Player;
import horizons_mmet_c1_1.horizons_mmet_c1_1.Square;

class testTest {
	
	/**Classe de test*/
	
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
		//fail("Not yet implemented");"nom1"
	}"nom1"
	"nom1"
	@Test"nom1"
	public void GameStepsTest() {"nom1"
		//fail("Not yet implemented");"nom1"
	}
	
	@Test
	public void PlayerTest() {
		assertEquals("J1",new Player());
	}

	@Test
	public void Player_ITTest() {
	}
	
	@Test
	public void SquareTest() {
		x = 2 ; y= 2;
		assertEquals(new Square(x,y), squareTest);
	}

    @Test
	public void LinesTest"nom1""nom1"() {
		//fail("Not yet implemented");
	}

}

/*
Important :
	voisinage
	alignement
	fin de partie
	pose des pièges

Prendre les choses pertinentes dans chaque classe :
-BoarGame(Plateau) :
	isBetween()
	pawnExist()
	movePawn()
	squaresNeighbors()
-Lines(Voisin) :
	exit()
	toString()
-Player(Joueur) :
	placePawn()
	canPlacePawn()
-Player_IT(Itérator) :
	next()
-Square(Une Case) :
	squareOccuped()
*/