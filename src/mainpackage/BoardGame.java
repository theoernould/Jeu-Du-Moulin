package mainpackage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

public class BoardGame {
	
	private Map<Coords,Pawn> Pawns;
	
	private boolean[][] matriceAdjacence;
	
	private int type;
	
	public BoardGame(int t) throws IOException {
		this.type = t;
		
		// Initilisation des cases qui seront plus tard occupées par des pions
		
		for(int i=1; i<=t; i++) {
			for(int j=1; j<=t*2; j++) {
				Coords coords = new Coords(i,j);
				Pawns.put(coords, new Pawn(coords));
				
			}
		}
		
		// Initialisation de la matrice d'adjacence
		
		Path path = Paths.get("src/main/" + t + ".txt");
		ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(path);
		
		int nbOfPoints = t * 2 * t;
		
		matriceAdjacence = new boolean[nbOfPoints][nbOfPoints];
	
		int x = 0;
		
		for(String line : lines) {
			String[] boolsOfLine = line.split(",");
			int y = 0;
			for(String boolStr : boolsOfLine) {
				matriceAdjacence[x][y] = Boolean.getBoolean(boolStr);
				y++;
			}
			x++;
		}
		
	}
	
	public int getType() {
		return this.type;
	}
	
	public void movePawn(Coords lastCoords, Coords newCoords) {
		
	}
	
	/*public String toString() {
		if(this.type==4) {
			this.space=1;
			String display ="";
			System.out.println(1 + "-----------");
			return display;
		}
	}*/
	
	
}
