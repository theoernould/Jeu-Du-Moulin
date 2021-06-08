package classes;

/**Intitulé de chaque menu
* @param msg titre d'un menu */
public enum OptionsMenu {
	GRAPHIQUE("Graphique"),
	TEXTUEL("Textuel"),
		JOUER("Jouer"),
			CONTINUER("Sauvegardes"),
			NOUVELLE("Commencer une nouvelle partie"),
				RIEN("A partir de rien"),
				CONFIGURATION("A partir d'une configuration pré-définie"),
		REGLES("Règles"),
		QUITTER("Quitter"),
		RETOUR("Retour");
	
	private String msg;
	
	/**Constructeur de l'enum */
	OptionsMenu(String msg) {
		this.msg = msg;
	}
	
	/**Retourne l'intitul� d'un menu */
	public String toString() {
		return msg;
	}
}
