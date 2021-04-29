package horizons_mmet_c1_1.horizons_mmet_c1_1;

public class Square {
	private final int X;
	private final int Y;
	
	private Player player;
	
	Square(int coordX, int coordY) {
		this.X = coordX;
		this.Y = coordY;
	}
	
	public boolean addPlayer(Player p) {
		if(p != null && this.player == null) {
			this.player = p;
			return true;
		} else return false;
	}
	
	public Player removePlayer() {
		Player toReturn = this.player;
		this.player = null;
		return toReturn;
	}
	
	public void moveTo(Square s2) {
		s2.addPlayer(this.removePlayer());
	}
	
	public String toString() {
		return "(" + X + ", " + Y + ") " + player;
	}
	
}
