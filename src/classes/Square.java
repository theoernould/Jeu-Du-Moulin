package classes;

/**Class qui permet d'utiliser les pions du plateau */
public class Square {
	
	protected final int X;
	protected final int Y;
	private Player player;
	private Trap trap;
	
	/**Constructeur avec les coordonnées de x et y*/
	public Square(int coordX, int coordY) {
		this.X = coordX;
		this.Y = coordY;
	}
	
	/**Renvoie si oui ou non une case est occupée*/
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

	/**V�rifier si oui ou non le joueur pass� en param�tre est le m�me que celui sur la case */
	public boolean playerIs(Player p) {
		return squareOccuped() && this.player.equals(p);
	}
	
	/**Supprimer le joueur sur la case et le retourne */
	public Player removePlayer() {
		Player toReturn = this.player;
		this.player = null;
		return toReturn;
	}
	
	/**D�place le pion d'une case � une autre
	* @param s2 case de destination */
	public boolean moveTo(Square s2) {
		return s2.addPlayer(this.removePlayer());
	}

	/**Renvoie si oui ou non l'id d'un joueur est correct
	* @param p joueur pour s�lectionn� son id */
	public boolean equals(Player p) {
		return this.player.number == p.number;
	}
	
	public void placeTrap(Player p) {
		Trap t = new Trap(this.X, this.Y, p);
		this.trap = t;
		p.setTrap(t);
	}
	
	public boolean isTrapped() {
		return trap != null;
	}
	
	public void roundPass() {
		if(isTrapped()) {
			trap.roundPass();
			if(trap.isTrapDead()) trap = null;
		}
	}
	
	public Trap getTrap() {
		return trap;
	}
	
	/**Affichage des coordonn�es d'un pion d'un joueur */
	public String toString() {
		return "(" + X + ", " + Y + ") " + player;
	}

	/**Getteur de Player*/
	public Player getPlayer() {
		return player;
	}

	
}
