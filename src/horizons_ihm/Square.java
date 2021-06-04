package horizons_ihm;

public class Square {
	
	protected final int X;
	protected final int Y;
	private Player player;
	
	
	public Square(int coordX, int coordY) {
		this.X = coordX;
		this.Y = coordY;
	}
	/**Renvoie si oui ou non une case est occupée*/
	public boolean squareOccuped() {
		return this.player != null;
	}
	
	public int getPlayerNumber() {
		if(this.player == null) return 0;
		else return this.player.number;
	}
	
	public boolean addPlayer(Player p) {
		if(p != null && !squareOccuped()) {
			this.player = p;
			return true;
		} else return false;
	}

	public boolean playerIs(Player p) {
		return squareOccuped() && this.player.equals(p);
	}
	
	public Player removePlayer() {
		Player toReturn = this.player;
		this.player = null;
		return toReturn;
	}
	
	public boolean moveTo(Square s2) {
		return s2.addPlayer(this.removePlayer());
	}

	public boolean equals(Player p) {
		return this.player.number == p.number;
	}
	
	public String toString() {
		return "(" + X + ", " + Y + ") " + player;
	}
	
}
