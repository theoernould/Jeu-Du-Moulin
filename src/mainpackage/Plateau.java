package mainpackage;

import java.util.ArrayList;
import java.util.List;

public class Plateau {
	List<ArrayList<Pion>> formes;
	
	Plateau(int nbCotes) {
		this.formes = new ArrayList<ArrayList<Pion>>();
			for(int i=1;i<=nbCotes;i++) {
				ArrayList<Pion> forme = new ArrayList<Pion>();
				for(int j=1;j<=nbCotes*2;j++) {
					Pion pion = new Pion(i, j);
					forme.add(pion);
				}
				this.formes.add(forme);
			}
	}
	
	boolean placeOccupee(int x, int y) {
		return this.formes.get(x).get(y) == null;
	}
	
	boolean placerPion(int x, int y) {
		return true;
	}
}
