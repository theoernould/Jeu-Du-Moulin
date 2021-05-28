package horizons_mmet_c1_1.horizons_mmet_c1_1;

public enum Menus {
	PRINCIPAL("Bienvenue dans LineUp3 !", "Principal", new OptionsMenu[] {
			OptionsMenu.JOUER,
			OptionsMenu.REGLES,
			OptionsMenu.QUITTER
	}),
		JOUER("Continuer votre partie ?", "Jouer", new OptionsMenu[] {
				OptionsMenu.CONTINUER,
				OptionsMenu.NOUVELLE,
				OptionsMenu.RETOUR
		}),
			CONTINUER("Continuer à partir de quelle configuration ?", "Continuer la partie précédente", new OptionsMenu[] {
					OptionsMenu.QUITTER
			}),
			NOUVELLE("Comment démarrer la nouvelle partie", "Commencer une nouvelle partie", new OptionsMenu[] {
				OptionsMenu.RIEN,
				OptionsMenu.CONFIGURATION
			}),
		REGLES("Règles :\n"
				+ "\tDéplacez des pions\n"
				+ "\tPlacer des pièges\n"
				+ "\tBattez votre adversaire !", "Règles", new OptionsMenu[] {
						OptionsMenu.RETOUR
				});
	
	String title;
	String optionName;
	OptionsMenu[] options;
	
	Menus(String t, String on, OptionsMenu[] o) {
		this.title = t;
		this.optionName = on;
		this.options = o;
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
