package horizons_ihm;

/**Affichage du Menu sous forme textuel
* @param title titre du menu s�lectionn�
* @param options choix dans le menu
* @param previousMenu permet de retourner dans le menu pr�c�dent */
public enum Menus {
	CHOIX_INTERFACE("Quel affichage souhaitez-vous ?", new OptionsMenu[] {
			OptionsMenu.TEXTUEL,
			OptionsMenu.GRAPHIQUE,
			OptionsMenu.QUITTER
	}, null),
		PRINCIPAL("Bienvenue dans LineUp3 !", new OptionsMenu[] {
				OptionsMenu.JOUER,
				OptionsMenu.REGLES,
				OptionsMenu.QUITTER
		}, null),
			JOUER("Continuer votre partie ?", new OptionsMenu[] {
					OptionsMenu.CONTINUER,
					OptionsMenu.NOUVELLE,
					OptionsMenu.RETOUR
			}, PRINCIPAL),
				/*CONTINUER("Continuer à partir de quelle configuration ?", "Continuer la partie précédente", new OptionsMenu[] {
						OptionsMenu.QUITTER,
						OptionsMenu.RETOUR
				}, JOUER),*/
				NOUVELLE("Comment démarrer la nouvelle partie",  new OptionsMenu[] {
					OptionsMenu.RIEN,
					OptionsMenu.CONFIGURATION,
					OptionsMenu.RETOUR
				}, JOUER),
			REGLES("Règles :\n"
					+ "\tDéplacez des pions\n"
					+ "\tPlacer des pièges\n"
					+ "\tBattez votre adversaire !",  new OptionsMenu[] {
							OptionsMenu.RETOUR
					}, PRINCIPAL);
	
	String title;
	/*String optionName;*/
	OptionsMenu[] options;
	Menus previousMenu;
	
	Menus(String t, OptionsMenu[] o, Menus pm) {
		this.title = t;
		//this.optionName = on;
		this.options = o;
		this.previousMenu = pm;
	}
	
	/**Retourne l'intitul� du menu*/
	public String getTitle() {
		return title;
	}
	
	/*public String getOptionName() {
		return optionName;
	}*/
	
	/**Retourne les choix du menu*/
	public OptionsMenu[] getOptions() {
		return options;
	}
}
