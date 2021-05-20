package horizons_mmet_c1_1.horizons_mmet_c1_1;

public class Square {
	
	private final int X;
	private final int Y;
	private Player player;
	
	
	public Square(int coordX, int coordY) {
		this.X = coordX;
		this.Y = coordY;
	}
	
	private boolean squareOccuped() {
		return this.player == null;
	}
	
	public int getPlayerNumber() {
		if(this.player == null) {
			return 0;
		} else return this.player.number;
	}
	
	public boolean addPlayer(Player p) {
		if(p != null && !squareOccuped() && p.canPlacePawn()) {
			this.player = p;
			this.player.placePawn();
			return true;
		} else return false;
	}
	
	public Player removePlayer() {
		Player toReturn = this.player;
		this.player = null;
		return toReturn;
	}
	
	public boolean moveTo(Square s2) {
		return s2.addPlayer(this.removePlayer());
	}
	
	public String toString() {
		return "(" + X + ", " + Y + ") " + player;
	}
	
}
