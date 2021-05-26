package test;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import horizons_mmet_c1_1.horizons_mmet_c1_1.BoardGame;
import horizons_mmet_c1_1.horizons_mmet_c1_1.Lines;
import horizons_mmet_c1_1.horizons_mmet_c1_1.Player;
import horizons_mmet_c1_1.horizons_mmet_c1_1.Square;

class testTest {
	
	/**Classe de test*/
	
	int x = 0;
	int y = 0;
	public Player playerTest = new Player();
	public Player playerTest2 = new Player();
	public Square squareTest = new Square(x,y);

	/**Test de la classe BoardGame*/
	@Test
	public void BoardGameTest() {
		/* int testsides = 3;
		int testshapes = 6;
		BoardGame testb = new BoardGame(testsides,testshapes);
		isBetween() / pawnExist() / movePawn() */
	}
	
	/**Test de la classe Player*/
	@Test
	public void PlayerTest() {
		assertEquals("J1",new Player());
		Player testp = new Player();
		testp.placePawn();
		assertTrue(testp.getPlacedPawn()>0);
		//canPlacePawn();
	}

	/**Test de la classe Player_IT*/
	@Test
	public void Player_ITTest() {
		//test condition de victoire
	}
	
	/**Test de la classe Square*/
	@Test
	public void SquareTest() {
		/* int x = 0; 
		int y = 0;
		Square tests = new Square(x,y);
		tests.getPlayeur(); */
	}

	/**Test de la classe Lines*/
	//va peut-être migrer dans BoardGame et donc dans BoardGameTest
    @Test
	public void LinesTest() {
		Lines testl = new Lines();
		BoardGame terrain = new BoardGame(3,3);
		//exist() :
			//triangle :
			Square s1 = new Square(1,1);
			Square s2 = new Square(1,6);
			/* carré :
				s1 = 1.1
				s2 = 1.8 */
			Square s2 = new Square(1,2); //pour les 2 plateaux
			//comparer dans les 2 sens
			//qui marche pas : 1.6 et 1.2
		//alignement() :
			/*1.1
			1.2
			1.3
				3.2
				2.2
				1.2
			fonctionne pas :
			3.2
			3.3
			3.4
				*/

	}

}

/* Important :
	voisinage / alignement / fin de partie / pose des pièges */