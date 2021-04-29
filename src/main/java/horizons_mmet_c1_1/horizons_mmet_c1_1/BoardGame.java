package horizons_mmet_c1_1.horizons_mmet_c1_1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BoardGame {
	
	
	private ArrayList<Square> squares;
	
	private boolean[][] matriceAdjacence;
	
	private int type;
	
	public BoardGame(int t) throws IOException {
		this.type = t;
		//this.Pawns = new HashMap<>();
		
		// Initilisation des cases qui seront plus tard occupées par des pions
		
		for(int i=1; i<=t; i++) {
			for(int j=1; j<=t*2; j++) {
				Square square = new Square(i,j);
				this.squares.add(square);
				
			}
		}
		
		// Initialisation de la matrice d'adjacence
		//Cette fonction a été déplacée dans "Square"
		
		Path path = Paths.get("src/main/" + t + ".txt");
		ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(path);
		
		int nbOfPoints = t * 2 * t;
		
		matriceAdjacence = new boolean[nbOfPoints][nbOfPoints];
	
		int x = 0;
		
		for(String line : lines) {
			if(x > 0) {
				String[] boolsOfLine = line.substring(4).trim().split(",");
				int y = 0;
				for(String boolStr : boolsOfLine) {
					matriceAdjacence[x][y] = Boolean.getBoolean(boolStr);
					y++;
				}
			}
			x++;
		}
		
	}
	
	public int getType() {
		return this.type;
	}
	
	public static void main(String[] args) {
		
	}
	
	
}
