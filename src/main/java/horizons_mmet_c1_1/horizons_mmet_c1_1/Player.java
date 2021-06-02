

package horizons_mmet_c1_1.horizons_mmet_c1_1;

public class Player {
	
	private String name;
	private int placedPawns = 0;
	private static final int PAWNS_NUMBER = 3;
	public final int number;
	private static int incrementNumber = 1;
	private final boolean IA;
	
	
	/**Contructeur d'un joueur avec un nom et la condition d'un vrai joueur ou IA*/
	public Player(String name, boolean ia) {
		this.name = name;
		this.IA = ia;
		this.number = incrementNumber;
		incrementNumber++;
	}
	
	public Player(String name, int number, boolean ia) {
		this.name = name;
		this.number = number;
		this.IA = ia;
		incrementNumber++;
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
	
	/**Retourne combien de pion il reste à placer pour le joueur*/
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
	
	public String getName() {
		return name;
	}
	
	public int getNumber() {
		return number;
	}


}

