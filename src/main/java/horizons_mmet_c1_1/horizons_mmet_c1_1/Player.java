package horizons_mmet_c1_1.horizons_mmet_c1_1;

public class Player {
	
	private String name;
	private int placedPawns = 0;
	private static final int PAWNS_NUMBER = 3;
	public int number = 1;
	
	public Player(String name) {
		this.name = name;
	}
	
	public Player(){
		this("J1");
	}
	
	public void placePawn() {
		placedPawns++;
	}
	
	public boolean canPlacePawn() {
		return placedPawns < Player.PAWNS_NUMBER;
	}
	
	public int pawnsLeft() {
		return PAWNS_NUMBER - placedPawns;
	}
	
	public String toString() {
		return name;
	}
}
