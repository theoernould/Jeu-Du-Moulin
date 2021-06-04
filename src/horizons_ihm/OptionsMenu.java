package horizons_ihm;

public enum OptionsMenu {
	GRAPHIQUE("Graphique"),
	TEXTUEL("Textuel"),
		JOUER("Jouer"),
			CONTINUER("Continuer la dernière partie"),
			NOUVELLE("Commencer une nouvelle partie"),
				RIEN("A partir de rien"),
				CONFIGURATION("A partir d'une configuration pré-définie"),
		REGLES("Règles"),
		QUITTER("Quitter"),
		RETOUR("Retour");
	
	private String msg;
	
	OptionsMenu(String msg) {
		this.msg = msg;
	}
	
	public String toString() {
		return msg;
	}
}
