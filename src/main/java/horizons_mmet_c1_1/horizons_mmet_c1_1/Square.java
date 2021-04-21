package horizons_mmet_c1_1.horizons_mmet_c1_1;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Square {
	private int x;
	private int y;
	//La matrice d'adjacence
	private static final boolean [][] MATRICE_ADJ;
	
	
	Square(int coordX, int coordY, int typePlat) {
		this.x = coordX;
		this.y = coordY;
		
		Path path = Paths.get("src/main/" + t + ".txt");
		ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(path);
		
		int nbOfPoints = typePlat * 2 * typePlat;
		
		MATRICE_ADJ = new boolean[nbOfPoints][nbOfPoints];
	
		int x = 0;
		
		for(String line : lines) {
			if(x > 0) {
				String[] boolsOfLine = line.substring(4).trim().split(",");
				int y = 0;
				for(String boolStr : boolsOfLine) {
					MATRICE_ADJ[x][y] = Boolean.getBoolean(boolStr);
					y++;
				}
			}
			x++;
		}
	}
	
	//Renvoie si oui ou non une case est voisine avec une autre
	boolean isNeighborWith(Square otherSquare) {
		
		int ligne = (this.x - 1) * 6 + (this.y - 1);
		int colonne = (otherSquare.x - 1) * 6 + (otherSquare.y - 1);
		return MATRICE_ADJ[ligne][colonne];
		
	}
	
}