package horizons_ihm;

import javafx.scene.paint.Color;

/**Class qui permet de symboliser les joueurs d'une partie */
public class Player {
	
	private String name;
	private int placedPawns = 0;
	private static final int PAWNS_NUMBER = 3;
	private static int incrementNumber = 1;
	private final boolean IA;
	public final int number;
	private Color color;
	
	
	/**Contructeur d'un joueur avec un nom et la condition d'un vrai joueur ou IA*/
	public Player(String name, boolean ia) {
		this.name = name;
		this.IA = ia;
		this.number = incrementNumber;
		incrementNumber++;
	}
	
	/** Contructeur d'un joueur avec un nom, son id et la condition d'un vrai joueur ou IA*/
	public Player(String name, int number, boolean ia) {
		this.name = name;
		this.number = number;
		this.IA = ia;
		incrementNumber++;
	}
	
	/** Contructeur d'un joueur avec un nom, sa couleur et la condition d'un vrai joueur ou IA*/
	public Player(String name, Color color, boolean ia) {
		this(name,ia);
		this.color = color;
	}
	
	/**Constructeur d'un joueur sans paramètres*/
	public Player(){
		this("J" + incrementNumber,true);
	}
	
	/**Place un pion*/
	public void placePawn() {
		placedPawns++;
	}
	
	/**Vérifie si un joueur peut placer un pion*/
	public boolean canPlacePawn() {
		return placedPawns < Player.PAWNS_NUMBER;
	}
	
	/**Retourne combien de pion il reste � placer pour le joueur*/
	public int pawnsLeft() {
		return PAWNS_NUMBER - placedPawns;
	}

	/**Accesseur de l'attribut IA*/
	public boolean isIA() {
		return this.IA;
	}
	
	/**Méthode d'affichage*/
	public String toString() {
		return this.name + "(" + number + ")";
	}

	/**Getteur PlacedPawn*/
	public int getPlacedPawn(){
		return this.placedPawns;
	}
	
	/**Getteur Name*/
	public String getName() {
		return name;
	}
	
	/**Getteur Number*/
	public int getNumber() {
		return number;
	}
	
	/**Getteur Color*/
	public Color getColor() {
		return color;
	}
	
	public boolean equals(Player p) {
		return this.number == p.number;
	}
}

