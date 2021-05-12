package horizons_mmet_c1_1.horizons_mmet_c1_1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class BoardGame {
	
	private Square[][] squares;
	
	private int nbSides;
	private int nbShapes;
	
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
	
	/*public int getIndex(int x, int y) {
		return (x - 1) * (nbSides * 2) + (y - 1);
	}*/
	
	public boolean placePawn(int x, int y, Player p) {
		return squares[x-1][y-1].addPlayer(p);
		//return squares.get(getIndex(x,y)).addPlayer(p);
	}
	
	public boolean movePawnAbsolute(int x1, int y1, int x2, int y2) {
		return !squares[x2-1][y2-1].moveTo(squares[x1-1][y1-1]);
		//return !squares.get(getIndex(x2,y2)).moveTo(squares.get(getIndex(x1,y1)));
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
	
	public String advancedDisplay() throws IOException {
		StringBuilder display = new StringBuilder();
		Path file = Paths.get("src/main/triangle.txt");
		ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(file);
		for(String line_ : lines) {
			String line = line_;
			for(int i=0;i<line.length();i++) {
				char car = line.charAt(i);
				if(Character.isLetter(car)) {
					int alphabetIndex = ((int) (car - 'A'));
					int x = (int) Math.ceil(alphabetIndex/(nbSides*2));
					int y = alphabetIndex % (nbSides*2);
					System.out.println("remplace " + car + " en " + squares[x][y].getPlayerNumber());
					line.replace("" + car, "" + squares[x][y].getPlayerNumber());
					//System.out.println(car + " x=" + x + " y=" + y);
				}
			}
			System.out.println(line);
			display.append(line + "\n");
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
