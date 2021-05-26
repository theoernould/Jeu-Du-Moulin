package horizons_mmet_c1_1.horizons_mmet_c1_1;

public class Trap extends Square {
	private int x2;
	private int y2;
	private static final int DUREE = 3;
	
	Trap(int x1, int y1, int x2, int y2) {
		super(x1,y1);
		this.x2 = x2;
		this.y2 = y2;
	}
	
}
