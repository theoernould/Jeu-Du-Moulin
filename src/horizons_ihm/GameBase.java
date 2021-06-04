package horizons_ihm;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


/**Class qui permet de créer toutes les méthodes relatives au déroulement de la partie*/
public class GameBase {

	private static Scanner scanner = new Scanner(System.in);
	private static final int DELAY = 50;

	/**Algo principal de jeu avec les actions*/
	public static void game()throws Exception,IOException, InterruptedException {
		
		BoardGame plateau = null;
		List<Player> joueurs = new ArrayList<Player>();
		
		int nbPlayers = 0;
		boolean genAlea = false;
		
		boolean display = true;
		
		Menus menuActuel = Menus.PRINCIPAL;
		OptionsMenu choix = null;
		
		while(display) {
			choix = Utils.afficherMenu(menuActuel);
			
			Path path;
			
			switch(choix) {
				case JOUER:
					menuActuel = Menus.JOUER;
					break;
					
					case CONTINUER:
						path = Paths.get(Utils.dir + "files/last_save.txt");
						if(path.toFile().exists()) {
							ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(path);
							int lineNumber=1;
							LocalDateTime date = null;
							for(String line : lines) {
								if(lineNumber == 1) {
									date = LocalDateTime.parse(line);
									//System.out.println(date);
								} else if(lineNumber == 2) {
									String[] options = line.split(":");
									plateau = new BoardGame(Integer.parseInt(options[0]),Integer.parseInt(options[1]));
								} else {
									String[] options = line.split(";");
									
									String[] firstOptions = options[0].split(":");
									boolean ia = false;
									//System.out.println(firstOptions[0]);
									if(firstOptions[0].equals("IA")) ia = true;
									Player p = new Player(firstOptions[0], Integer.parseInt(firstOptions[1]), ia);
									joueurs.add(p);
									
									for(int i=1;i<options.length;i++) {
										String[] coords = options[i].split(":");
										int x = Integer.parseInt(coords[0])-1;
										int y = Integer.parseInt(coords[1])-1;
										plateau.placePawn(x, y, p);
									}
									
								}
								lineNumber++;
							}
							Utils.progressivePrint("Chargement de la sauvegarde du " + Utils.dateToString(date) + "\n", GameBase.DELAY);
							display = false;
						} else {
							System.out.println("Impossible de charger la dernière sauvegarde.");
						}
						break;
					case NOUVELLE:
						menuActuel = Menus.NOUVELLE;
						break;
						case RIEN:

							plateau = GameBase.createBoardGame();

							Thread.sleep(1000);

							nbPlayers = GameBase.numberPlayer();

							Thread.sleep(1000);

							Utils.progressivePrint("Génération aléatoire des pions ? (true ou false)\n", DELAY);
	//BOUCLER TANT QUE L'ENTREE EST INVALIDE !!!				
							try{
								genAlea = scanner.nextBoolean();
							}catch(InputMismatchException e){
								System.out.println("Entrée invalide !");
							}
							
							display = false;
							break;
						case CONFIGURATION:
							path = Paths.get(Utils.dir + "files/default_config.txt");
							ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(path);
							int nbCotesFormes = 0;
							int nbFormes = 0;
							for(String line : lines) {
								String[] options = line.split(" ");
								switch(options[0]) {
									case "@cotesformes":
										nbCotesFormes = Integer.parseInt(options[1]);
										break;
									case "@nbformes":
										nbFormes = Integer.parseInt(options[1]);
										break;
									case "@nbplayers":
										nbPlayers = Integer.parseInt(options[1]);
										break;
									case "@genalea":
										genAlea = Boolean.valueOf(options[1]);
										break;
								}
							}
							
							plateau = new BoardGame(nbCotesFormes, nbFormes);
							
							Utils.progressivePrint("Chargement de la configuration par défaut\n", GameBase.DELAY);
							
							display = false;
							break;
				case REGLES:
					menuActuel = Menus.REGLES;
					break;
				case QUITTER:
					display = false;
					break;
				case RETOUR:
					menuActuel = menuActuel.previousMenu;
			}
		}
		
		if(choix != OptionsMenu.QUITTER) {

			if(joueurs.size() == 0) {
				if(nbPlayers == 1) {
					joueurs.add(GameBase.createPlayer("Joueur"));
					joueurs.add(GameBase.createPlayer());
				}else {
					for(int i=1;i<=nbPlayers;i++) {
						joueurs.add(GameBase.createPlayer("Joueur " + i));
						Thread.sleep(500);
					}
				}
			}
			
			if(genAlea) generationAleatoire(plateau,joueurs);

			Iterator<Player> it = new Player_IT(joueurs);

			Player gagnant = null;
			
			while(gagnant == null) {

				GameBase.saveGame(plateau, joueurs);
				Player p = it.next();
				if(!p.isIA()) showPlate(plateau);
				action(plateau, p);
				gagnant = plateau.gameWon(joueurs);

			}
			
			Utils.progressivePrint(gagnant + "  a gagné la partie ! GG :D\n", GameBase.DELAY);
			File saveFile = new File(Utils.dir + "files/last_save.txt");
				saveFile.delete();
		}

		Utils.shutdown();
	}
	
	public static void getConfigFromName(String name) throws IOException {
		Path path = Paths.get(Utils.dir + "files/" + name + "_config.txt");
		ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(path);
		for(String line : lines) {
			String[] options = line.split(" ");
			switch(options[0]) {
				case "@cotesformes":
					
					break;
				case "@nbformes":
					
					break;
				case "@nbplayers":
					
					break;
				case "@genalea":
					
					break;
			}
		}
	}

	/**Crée le plateau de jeu selon les entrées*/             
	public static BoardGame createBoardGame() throws Exception {
		boolean erreur = true;
		int nbSides = 0;
		
		Utils.progressivePrint("Bienvenue sur LineUp 3\n", DELAY);

		Thread.sleep(1000);

		Utils.progressivePrint("Veuillez entrer le nombre de côtés des formes du plateau : \n", DELAY);
//ATTENTION BOUCLE INFINIE !!!!
		while(erreur) {
			try{
				nbSides = scanner.nextInt();
			}catch(InputMismatchException e) {
				System.out.println("Entrée invalide !");
			}
			erreur = false;
		}
		

		Utils.progressivePrint("Veuillez entrer le nombre de formes du plateau : \n", DELAY);

		int nbShapes = scanner.nextInt();

		return new BoardGame(nbSides, nbShapes);
	}
	

	/**Retourne le nombre de joueur saisit par l'utilisateur*/
	public static int numberPlayer()throws IOException, InterruptedException {

		Utils.progressivePrint("Combien de joueurs ? (entre 1 et 4)\n" , DELAY);

		Thread.sleep(1000);

		return Utils.entrerInt(1, 4);
	}
	

	/**Crée un joueur avec le nom saisit par l'utilisateur */
	public static Player createPlayer(String name) throws IOException, InterruptedException {

		Utils.progressivePrint("Pseudo du " + name +  " ?\n", DELAY);

		return new Player(scanner.nextLine(), false);
	}

	/**Crée par défaut un joueur nommé IA*/
	public static Player createPlayer() {
		return new Player("IA", true);
	}
	/**Permet la génération aléatoire des pions de tout les joueurs*/
	public static void generationAleatoire(BoardGame plateau, List<Player> players) throws InterruptedException {
			for(Player p : players) {
				while(p.canPlacePawn()) {
					int x;
					int y;
					do {
						x = Utils.random(0,plateau.getNbShapes());
						y = Utils.random(0,plateau.getNbSides()*2);
					} while(!plateau.placePawn(x, y, p) /*|| plateau.gameWon(players)*/);
				}
			}
		Thread.sleep(1000);
		Utils.progressivePrint("Génération aléatoire effectuée, bon jeu !\n", DELAY);
	}

	/**Montre le plateau
	 * @param plateau Plateau à afficher*/
	public static void showPlate(BoardGame plateau) throws Exception,IOException, InterruptedException {
		Utils.progressivePrint("Voici le plateau \n", DELAY);
		System.out.println(plateau.toStringIntelligent());
	}
	
	public static void saveGame(BoardGame plateau, List<Player> joueurs) throws IOException {
		File saveFile = new File(Utils.dir + "files/last_save.txt");
		try {
			saveFile.createNewFile();
		} catch(IOException e) {
			saveFile.delete();
			saveFile.createNewFile();
		}
		PrintWriter saveFileWriter = new PrintWriter(Utils.dir + "files/last_save.txt");
		/*FORMAT
		 * D@date
		 * NP@nbjoueurs
		 * S@nbcotes;@nbformes
		 * P@joueur1:@numjoueur1:@coordsPion1:@coordsPion2:@coordsPion3
		 * P@joueur2@numjoueur2:@coordsPion1:@coordsPion2:@coordsPion3
		 */
			saveFileWriter.println(LocalDateTime.now());
			saveFileWriter.println(plateau.getNbSides() + ":" + plateau.getNbShapes());
			for(Player p : joueurs) {
				saveFileWriter.print(p.getName() + ":" + p.getNumber() + ";");
				Square[] pawns = plateau.playerPawns(p);
				for(int i=0;i<pawns.length;i++) saveFileWriter.print(pawns[i].X + ":" + pawns[i].Y + ";");
				saveFileWriter.println();
			}
		saveFileWriter.close();
	}

	/**Execute les méthodes selon les choix d'actions du joueur
	 * @param plateau plateau actuel
	 * @param p joueur actuel
	 * @throws InterruptedException */
	public static void action(BoardGame plateau, Player p) throws InterruptedException {
		int choice;
		Square[] tabCases;
		int randCase;
		boolean canPut;
		

		if(p.pawnsLeft() > 0) {
			if(!p.isIA()){

			
				System.out.println(p + " : " + "que voulez vous faire ?\n\t1. Placer un pion (pions restants : " + p.pawnsLeft() + ")");
				choice = Utils.entrerInt(1,1);
				if(choice == 1 && p.canPlacePawn()) {
					while(!actionPlacePawn(plateau, p)) System.out.println("Impossible de placer le pion !");
				} else if(choice == 2) {
					//pour la pose de piège
				}
			}else{
				System.out.println("Au tour de l'ia de placer un pion !");
				if(p.canPlacePawn()){
					while(!actionPlacePawn(plateau, p)){
						System.out.println("L'ia est sur une case occupée, elle rentente un coup !");
					}
				}
			}
		} else {
			if(!p.isIA()){
				System.out.println(p + " : " + "que voulez vous faire ?\n\t1. Déplacer un pion\n\t2. Placer un piège");
				choice = Utils.entrerInt(1,2);
				if(choice == 1) {
					while(!actionMovePawn(plateau,p)) System.out.println("Impossible de déplacer le pion !");
				} else if(choice == 2) {
					// Pas encore de pièges !
				}
			}else{
				Utils.progressivePrint("Au tour de l'IA de jouer !\n", GameBase.DELAY);
				tabCases =  plateau.playerPawns(p);
				
				int x;
				int y;
				
				randCase = Utils.random(0, 3);
				x = tabCases[randCase].X;
				y = tabCases[randCase].Y;
				
				do{
					randCase = Utils.random(0, 3);
					x = tabCases[randCase].X-1;
					y = tabCases[randCase].Y-1;
				}while(!plateau.IcanMove(x, y));

				int newX;
				int newY;
				
				do{
					newX = x + Utils.random(-2,2);
					newY = y + Utils.random(-2,2);
					canPut = plateau.movePawn(x, y, newX, newY, p);
					System.out.println("infini");
				}while(!canPut);
				
				Utils.progressivePrint("L'IA se déplace de la case " + (x+1) + "," + (y+1) + " à la case " + (newX+1) + "," + (newY+1) + "\n", GameBase.DELAY);

				//System.out.println("Waw ça passe ! :D");
			}
		}
	}
	/**Fait l'action de placer un pion
	 * @param plateau 
	 * @param p joueur actuel
	 * @return boolean indique si l'action de placement a été réalisé*/
	public static boolean actionPlacePawn(BoardGame plateau, Player p) {
		int rX;
		int rY;
		if(!p.isIA()){
			System.out.print("Veuillez entrer les coordonnées :\nx : ");
			int x = scanner.nextInt()-1;
			System.out.print("y : ");
			int y = scanner.nextInt()-1;
			return plateau.placePawn(x, y, p);
		}else{
			rX = Utils.random(0, plateau.getNbShapes());
			rY = Utils.random(0, plateau.getNbSides()*2); 
			return plateau.placePawn(rX, rY, p);
		}
	}

	/**Fait l'action de déplacer un pion
	* @param plateau plateau actuel
	* @param p joueur actuel
	* @return boolean indique si l'action de déplacement a été réalisé  */
	public static boolean actionMovePawn(BoardGame plateau, Player p) {

		System.out.print("Veuillez entrer les coordonnées d'origine:\nx : ");
		int x1 = scanner.nextInt()-1;
		System.out.print("y : ");
		int y1 = scanner.nextInt()-1;

		System.out.print("\nVeuillez entrer les coordonnées de destination:\nx : ");
		int x2 = scanner.nextInt()-1;
		System.out.print("y : ");
		int y2 = scanner.nextInt()-1;
		return plateau.movePawn(x1, y1, x2, y2, p);

	}
	
	public static void closeScanner() {
		scanner.close();
	}
	
	
}