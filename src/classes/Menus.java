package classes;

/**Affichage du Menu sous forme textuel
* @param title titre du menu sélectionné
* @param options choix dans le menu
* @param previousMenu permet de retourner dans le menu précédent */
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
					+ "\t" + "Bienvenue dans les coulisses du jeu LineUp3\n" 
					+ "\t" + "Le But ? Soyez le premier à aligner vos 3 pions !\n" 
					+ "\n"
					+ "\t" + "Pour jouer, c'est très simple :\n"
					+ "\t" + "- Que ce soit seul ou à plusieurs, déposer vos 3 pions 1 à 1 et chacun votre tour sur le plateau que vous aurez choisi au préalable.\n"
					+ "\t" + "Magnifique n'est ce pas ?\n"
					+ "\t" +  "- Chacun son tour ( le plus petit commence toujours ) vous pourrez soit :\n"
					+ "\t" + "\t" + "1 - déplacer l'un de vos pions\n"
					+ "\t" + "\t" + "2 - poser un piège\n"
					+ "\t" + "La mécanique des pièges vous permettra de ralentir vos adversaires pour être sûr d'être le premier à aligner ses pions.\n"
					+ "\t" +  "- Le premier à aligner ses 3 pions sur un coté du plateau ( les angles ne comptent pas ) gagne la partie.\n"
					+ "\n" 
					+ "\t" + "Good game, good luck and have fun :D",  new OptionsMenu[] {
							OptionsMenu.RETOUR
					}, PRINCIPAL);
	
	private String title;
	/*String optionName;*/
	private OptionsMenu[] options;
	public Menus previousMenu;
	
	private Menus(String t, OptionsMenu[] o, Menus pm) {
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
