package mainpackage;

import java.util.ArrayList;
import java.util.Map;

public class Plateau {
	
	private Map<Coords,Joueur> lesPions;
	private int type;
	private int space;
	
	public Plateau(int t) {
		this.type = t;
		//if(t==3 || t==4);
		for(int i=1; i<=t; i++) {
			for(int j=1; j<=t*2; j++) {
				lesPions.put(new Coords(i,j),null);
			}
		}
	}
	
	public int getType() {
		return this.type;
	}
	
	public void ajoutJoueur() {
		
	}
	
	public String toString() {
		if(this.type==4) {
			this.space=1;
			String affichage ="";
			System.out.println(1 + "-----------");
			return affichage;
		}
	}
	
	
}
