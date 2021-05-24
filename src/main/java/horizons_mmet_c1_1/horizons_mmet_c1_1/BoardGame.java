package horizons_mmet_c1_1.horizons_mmet_c1_1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BoardGame {
	
	private Square[][] squares;
	
	private int nbSides;
	private int nbShapes;
	
	/**Constructeur de BoardGame*/
	public BoardGame(int sides, int shapes) throws IOException {
		this.nbSides = sides;
		this.nbShapes = shapes;
		
		// Initilisation des cases qui seront plus tard occupées par des pions
		
		this.squares = new Square[shapes][sides*2];
		
		for(int i=0; i<nbShapes; i++) {
			for(int j=0; j<nbSides*2; j++) {
				Square square = new Square(i+1,j+1);
				this.squares[i][j] = square;
			}
		}
		
	}
	
	public int getNbSides() {
		return nbSides;
	}
	
	public int getNbShapes() {
		return nbShapes;
	}
	
	public boolean gameWon(List<Player> players) {
		for(Player p : players) {
			Square tempSquare;
			for(int x=0;x<nbShapes;x++) {
				for(int y=0;y<nbSides*2;y++) {
					Square square = squares[x][y];
					if(square.playerIs(p)) {
						/*if(tempSquare == null) tempSquare = square;
						else if(square.playerIs(p) && )*/
					}
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @param x Coordonnées en x
	 * @param y Coordonnées en y
	 * @return
	 */
	public boolean pawnExist(int x, int y) {
		return Utils.isBetween(x, 0, squares.length) && Utils.isBetween(y, 0, squares[0].length);
	}


	/**
	 * Permet de placer un pion sur le plateau
	 * @param x Coordonnées en x
	 * @param y Coordonnées en y
	 * @param p Joueur qui souhaite placer le pion
	 * @return
	 */
	public boolean placePawn(int x, int y, Player p) {
		if(squares[x][y].addPlayer(p))  {
			p.placePawn();
			return true;
		} else return false;
		//return squares.get(getIndex(x,y)).addPlayer(p);
	}
	
	public boolean movePawnAbsolute(int x1, int y1, int x2, int y2) {
		return squares[x1][y1].moveTo(squares[x2][y2]);
	}
	
	public boolean movePawn(int x1, int y1, int x2, int y2, Player p) {
		/*System.out.println("pion 1 existe ? " + pawnExist(x1, y1));
		System.out.println("pion 2 existe ? " + pawnExist(x2, y2));
		System.out.println("pion 1 occupé ? " + squares[x1][y1].squareOccuped());
		System.out.println("pion 2 occupé ? " + squares[x2][y2].squareOccuped());
		System.out.println("pions voisins ? " + squaresNeighbors(x1, y1, x2, y2));*/
		if( ( !pawnExist(x1, y1) || !pawnExist(x2,y2) ) || !squares[x1][y1].squareOccuped() || squares[x2][y2].squareOccuped() || !squaresNeighbors(x1+1, y1+1, x2+1, y2+1) || !squares[x1][y1].playerIs(p)) return false;
		else {
			//System.out.println("move absolute");
			return movePawnAbsolute(x1, y1, x2, y2);
		}
	}
	
	//Renvoie si oui ou non une case est voisine avec une autre
	public boolean squaresNeighbors(int x1, int y1, int x2, int y2) {
		return ( y1 == y2 && Math.abs(x2 - x1) == 1 ) || ( x1 == x2 && (Math.abs( ( y2 % (nbSides * 2) ) - ( y1 % (nbSides * 2) ) ) == 1) );
	}
	
	/*public boolean squaresAligned(int y1, int y2, int y3) {
		
	}*/

	public String toStringIntelligent() throws Exception {
		if(Utils.isBetween(nbSides,3,4) && nbShapes == 3) return advancedDisplay();
		else return primaryDisplay();
	}
	
	public String advancedDisplay() throws IOException {
		StringBuilder display = new StringBuilder();
		String shapeName;
		if(nbSides == 3) shapeName = "triangle";
		else shapeName = "carre";
		Path file = Paths.get("src/main/" + shapeName + ".txt");
		ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(file);
		Path fileCoords = Paths.get("src/main/" + shapeName + "_coords.txt");
		ArrayList<String> linesCoords = (ArrayList<String>) Files.readAllLines(fileCoords);
		int cpt = 0;
		for(String line_ : lines) {
			String line = line_;
			for(int i=0;i<line.length();i++) {
				char car = line.charAt(i);
				if(Character.isLetter(car)) {
					int alphabetIndex = ((int) (car - 'A'));
					int x = (int) Math.ceil(alphabetIndex/(nbSides*2));
					int y = alphabetIndex % (nbSides*2);
					line = line.replace("" + car, "" + squares[x][y].getPlayerNumber());
				}
			}
			display.append(line + "\t" + linesCoords.get(cpt) + "\n");
			cpt++;
		}
		return display.toString();
	}
	
	public String primaryDisplay() {
		StringBuilder strBuilder = new StringBuilder();
		for(int x=0;x<squares.length;x++) {
			for(int y=0;y<squares[x].length;y++) {
				strBuilder.append(squares[x][y] + "\n");
			}
		}
		return strBuilder.toString();
	}
	
}
