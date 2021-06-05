package horizons_ihm;

/**Class qui permet d'utiliser les pions du plateau */
public class Square {
	
	protected final int X;
	protected final int Y;
	private Player player;
	
	/**Constructeur avev les coordonnées de x et y*/
	public Square(int coordX, int coordY) {
		this.X = coordX;
		this.Y = coordY;
	}
	
	/**Renvoie si oui ou non une case est occupÃ©e*/
	public boolean squareOccuped() {
		return this.player != null;
	}
	
	/**Renvoie l'id d'un joueur*/
	public int getPlayerNumber() {
		if(this.player == null) return 0;
		else return this.player.number;
	}
	
	/**Renvoi si oui ou non on peut poser un pion 
	* @param p Pion d'un joueur */
	public boolean addPlayer(Player p) {
		if(p != null && !squareOccuped()) {
			this.player = p;
			return true;
		} else return false;
	}

	/**Vérifier si oui ou non le joeurs passé en paramètre est le même que celui sur la case */
	public boolean playerIs(Player p) {
		return squareOccuped() && this.player.equals(p);
	}
	
	/**Supprmier le joeur sur la case et le retourne */
	public Player removePlayer() {
		Player toReturn = this.player;
		this.player = null;
		return toReturn;
	}
	
	/**Déplace le pion d'une case à une autre
	* @param s2 case de destination */
	public boolean moveTo(Square s2) {
		return s2.addPlayer(this.removePlayer());
	}

	/**Renvoie si oui ou non l'id d'un joueur est correct
	* @param p joueur pour sélectionné son id */
	public boolean equals(Player p) {
		return this.player.number == p.number;
	}
	
	/**Affichage des coordonnées d'un pion d'un joueur */
	public String toString() {
		return "(" + X + ", " + Y + ") " + player;
	}
	
}
