package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import horizons_mmet_c1_1.horizons_mmet_c1_1.BoardGame;
import horizons_mmet_c1_1.horizons_mmet_c1_1.Player;
import horizons_mmet_c1_1.horizons_mmet_c1_1.Utils;
import horizons_mmet_c1_1.horizons_mmet_c1_1.Square;

class testTest {
	
	/**Classe de test*/

	/**Test des voisins et alignements*/
	@Test
	public void BoardGameTest() throws IOException{
		BoardGame terrain = new BoardGame(3,3);
		//exist() :
			//triangle :
			assertTrue(terrain.squaresNeighbors(1,1,1,6));
			assertTrue(terrain.squaresNeighbors(1,6,1,1));//comparer dans les 2 sens
			assertFalse(terrain.squaresNeighbors(1,6,1,2));//qui marche pas : 1.6 et 1.2
			// carré :
			assertTrue(terrain.squaresNeighbors(1,1,1,8));
			assertTrue(terrain.squaresNeighbors(1,8,1,1));//comparer dans les 2 sens
			assertFalse(terrain.squaresNeighbors(1,8,1,2));//qui marche pas : 1.6 et 1.2
						
		//alignement() :
			//fonctionne :
			Square sA1 = new Square(1,1);
			Square sA2 = new Square(1,2);
			Square sA3 = new Square(1,3);
			assertTrue(terrain.alignement(sA1,sA2,sA3));
			Square sA4 = new Square(3,2);
			Square sA5 = new Square(2,2);
			Square sA6 = new Square(1,2);
			assertTrue(terrain.alignement(sA4,sA5,sA6));
			//fonctionne pas :
			Square sA7 = new Square(3,2);
			Square sA8 = new Square(3,3);
			Square sA9 = new Square(3,4);
		 	assertFalse(terrain.alignement(sA7,sA8,sA9));
	}
	
	/**Test de la classe Player*/
	@Test
	public void PlayerTest() {
		assertEquals("J1",new Player());
		Player testp = new Player();
		testp.placePawn();
		assertTrue(testp.getPlacedPawn()>0);
	}
	
	/**Test de la classe Utils*/
	@Test
	public void UtilsTest() {
		int a = 1; int b = 2; int c = 3; int d = a;
		assertTrue(Utils.isBetween(b, a, c));
		assertFalse(Utils.isBetween(a, b, c));
		assertTrue(Utils.isBetween(d, a, c));
		int min = 10; int max = 100;
		int testr = Utils.random(min, max);
		assertTrue(testr >= 10);
		assertTrue(testr <= 100);
		assertFalse(testr < 10);
		assertFalse(testr > 100);
	}

	/**Test de la condition de victoire*/
	@Test
	public void WinTest() {
		
		//test condition de victoire
	}
	
	/**Test des pièges*/
	@Test
	public void TrapTest(){
		//test des pièges
	}

}