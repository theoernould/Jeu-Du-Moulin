package mainpackage;

import java.util.ArrayList;

public class PlateauV2 {
	
	private ArrayList<Pion> LesPions;
	private int type;
	private int space;
	
	public PlateauV2(int t) {
		this.type = t;
		//if(t==3 || t==4);
		for(int i=1; i<=t; i++) {
			for(int j=1; j<=t*2; j++) {
				LesPions.add(new Pion(i,j));
			}
		}
	}
	
	public int getType() {
		return this.type;
	}
	
	public String toString() {
		if(this.type==4) {
			this.space=1;
		}
	}
	
	
}
