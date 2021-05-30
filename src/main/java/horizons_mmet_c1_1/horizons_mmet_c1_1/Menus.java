package horizons_mmet_c1_1.horizons_mmet_c1_1;

public enum Menus {
	PRINCIPAL("Bienvenue dans LineUp3 !", "Principal", new OptionsMenu[] {
			OptionsMenu.JOUER,
			OptionsMenu.REGLES,
			OptionsMenu.QUITTER
	}, null),
		JOUER("Continuer votre partie ?", "Jouer", new OptionsMenu[] {
				OptionsMenu.CONTINUER,
				OptionsMenu.NOUVELLE,
				OptionsMenu.RETOUR
		}, PRINCIPAL),
			CONTINUER("Continuer à partir de quelle configuration ?", "Continuer la partie précédente", new OptionsMenu[] {
					OptionsMenu.QUITTER,
					OptionsMenu.RETOUR
			}, JOUER),
			NOUVELLE("Comment démarrer la nouvelle partie", "Commencer une nouvelle partie", new OptionsMenu[] {
				OptionsMenu.RIEN,
				OptionsMenu.CONFIGURATION,
				OptionsMenu.RETOUR
			}, JOUER),
		REGLES("Règles :\n"
				+ "\tDéplacez des pions\n"
				+ "\tPlacer des pièges\n"
				+ "\tBattez votre adversaire !", "Règles", new OptionsMenu[] {
						OptionsMenu.RETOUR
				}, PRINCIPAL);
	
	String title;
	String optionName;
	OptionsMenu[] options;
	Menus previousMenu;
	
	Menus(String t, String on, OptionsMenu[] o, Menus pm) {
		this.title = t;
		this.optionName = on;
		this.options = o;
		this.previousMenu = pm;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getOptionName() {
		return optionName;
	}
	
	public OptionsMenu[] getOptions() {
		return options;
	}
}
