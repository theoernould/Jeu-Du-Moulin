package mainpackage;

import java.util.Map;

public class BoardGame {
	
	private Map<Coords,Pawn> Pawns;
	private int type;
	private int space;
	
	public BoardGame(int t) {
		this.type = t;
		//if(t==3 || t==4);
		for(int i=1; i<=t; i++) {
			for(int j=1; j<=t*2; j++) {
				Coords coords = new Coords(i,j);
				Pawns.put(coords, new Pawn(coords));
			}
		}
	}
	
	public int getType() {
		return this.type;
	}
	
	public void movePawn(Coords lastCoords, Coords newCoords) {
		
	}
	
	public String toString() {
		if(this.type==4) {
			this.space=1;
			String display ="";
			System.out.println(1 + "-----------");
			return display;
		}
	}
	
	
}
