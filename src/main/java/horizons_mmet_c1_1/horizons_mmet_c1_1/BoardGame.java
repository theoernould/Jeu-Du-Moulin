package horizons_mmet_c1_1.horizons_mmet_c1_1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BoardGame {
	
	private ArrayList<Square> squares;
	private int roundNum = 0;
	
	//private boolean[][] matriceAdjacence;
	
	private int nbSides;
	private int nbShapes;
	
	public BoardGame(int sides, int shapes) throws IOException {
		this.nbSides = sides;
		this.nbShapes = shapes;
		
		// Initilisation des cases qui seront plus tard occupées par des pions
		
		this.squares = new ArrayList<Square>();
		
		for(int i=1; i<=nbShapes; i++) {
			for(int j=1; j<=nbSides*2; j++) {
				Square square = new Square(i,j);
				this.squares.add(square);
			}
		}
		
		// Initialisation de la matrice d'adjacence
		
		/*Path path = Paths.get("src/main/" + t + ".txt");
		ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(path);
		
		int nbOfPoints = t * 2 * 3;
		
		matriceAdjacence = new boolean[nbOfPoints][nbOfPoints];
	
		int x = 0;
		
		for(String line : lines) {
			if(x > 0) {
				String[] boolsOfLine = line.substring(4).trim().split(",");
				int y = 0;
				//System.out.println("taille x( " + x + ") : " + boolsOfLine.length);
				for(String boolStr : boolsOfLine) {
					//System.out.println("x = " + x + " y = " + y);
					matriceAdjacence[x-1][y] = Boolean.getBoolean(boolStr);
					y++;
				}
			}
			x++;
		}*/
		
	}
	
	public int getIndex(int x, int y) {
		return (x - 1) * (nbSides * 2) + (y - 1);
	}
	
	public boolean placePawn(int x, int y, Player p) {
		//System.out.println("placement : " + x + " " + y + " : " + getIndex(x,y));
		return squares.get(getIndex(x,y)).addPlayer(p);
	}
	
	public boolean movePawnAbsolute(int x1, int y1, int x2, int y2) {
		return !squares.get(getIndex(x2,y2)).moveTo(squares.get(getIndex(x1,y1)));
	}
	
	public boolean movePawn(int x1, int y1, int x2, int y2) {
		if(!squaresNeighbors(x1, y1, x2, y2)) return false;
		else return movePawnAbsolute(x1, y1, x2, y2);
	}
	
	//Renvoie si oui ou non une case est voisine avec une autre
	public boolean squaresNeighbors(int x1, int y1, int x2, int y2) {
		return ( y1 == y2 && Math.abs(x2 - x1) == 1 ) || ( x1 == x2 && (Math.abs( ( y2 % (nbSides * 2) ) - ( y1 % (nbSides * 2) ) ) == 1) );
	}
	
	/*public boolean squaresAligned(int y1, int y2, int y3) {
		
	}*/
	
	public String advancedDisplay() {
		StringBuilder display = new StringBuilder();
		int sideVoids = 16;
		for(int i=0;i<sideVoids+1;i++,sideVoids--) {
			String line = "";
			for(int y=0;y<sideVoids;y++) {
				line += " ";
			}
			if( (sideVoids <= 8 && sideVoids%2 == 0) || (sideVoids > 8 && sideVoids%4 == 0) ) {
				line += "0";
			} else {
				line += "/";
			}
			display.append(line + "\n");
		}
		return display.toString();
	}
	
	public String primaryDisplay() {
		StringBuilder strBuilder = new StringBuilder();
		for(Square s : squares) strBuilder.append(s + "\n");
		return strBuilder.toString();
	}
	
}
