	package classes;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


/**Class qui permet de créer toutes les méthodes relatives au déroulement de la partie*/
public class GameBase {

	private static final int DELAY = 50;
	
	public static Player joueurActuel;
	
	
	/**Boucle de jeu
	* @param plateau plateau du jeu
	* @param joueurs l'ensemble des joueurs
	* @throws Exception
	* @throws InterruptedException */
	public static void play(BoardGame plateau, List<Player> joueurs) throws InterruptedException, Exception {
		Iterator<Player> it = new Player_IT(joueurs);

		Player gagnant = null;
		
		while(gagnant == null) {

			Player p = it.next();
			joueurActuel = p;
			for(Square s : plateau.playerPawns(p)) s.roundPass();
			if(!p.canPlacePawn()) GameBase.saveGame(plateau, joueurs);
			if(!p.isIA()) showPlate(plateau);
			action(plateau, p);
			gagnant = plateau.gameWon(joueurs);

		}
		
		Utils.progressivePrint(gagnant + "  a gagné la partie ! GG :D\n", GameBase.DELAY);
		File saveFile = new File(Utils.dir + "saves/" + plateau.getSaveName() + ".txt");
			saveFile.delete();
	}

	/**Algorithme principal de jeu avec les actions
	* @throws Exception
	* @throws IOException
	* @throws InterruptedException */
	public static void game()throws Exception,IOException, InterruptedException {
		
		BoardGame plateau = null;
		List<Player> joueurs = new ArrayList<Player>();
		boolean erreur = true;
		
		int nbPlayers = 0;
		boolean genAlea = false;
		
		boolean display = true;
		
		Menus menuActuel = Menus.PRINCIPAL;
		OptionsMenu choix = null;
		
		while(display) {
			choix = Utils.afficherMenu(menuActuel);
			
			switch(choix) {
				case JOUER:
					menuActuel = Menus.JOUER;
					break;
					
					case CONTINUER:
						System.out.println("Liste des sauvegardes : ");
						
						int nbSaves = 0;
						
						File savesFolder = new File(Utils.dir + "saves");
						File[] saves = savesFolder.listFiles();
						for(File file : savesFolder.listFiles()) {
							System.out.println("\t" + (nbSaves+1) + ". " + file.getName().replace(".txt", "") + " - " + Utils.dateToString(LocalDateTime.parse(Files.readAllLines(file.toPath()).get(0))));
							nbSaves++;
						}
						
						if(saves.length == 0) {
							System.out.println("Aucune sauvegarde");
						}
						
						System.out.println("\t" + (nbSaves+1) + ". Retour");
						
						int saveNumber = Utils.entrerInt(1, nbSaves+1);
						
						if(saveNumber == (nbSaves+1)) menuActuel = Menus.JOUER;
						else {
							String saveName = saves[saveNumber-1].getName().replace(".txt", "");
							
							plateau = GameBase.generateBoardGameFromSave(saveName);
							if(plateau != null) {
								joueurs = GameBase.generatePlayersAndPlacePawnsFromSave(plateau, saveName);
								if(joueurs.size() >= 2) {
									Utils.progressivePrint("Chargement de la sauvegarde...\n", GameBase.DELAY);
									display = false;
								} else System.out.println("Impossible de charger la dernière sauvegarde.");
							} else System.out.println("Impossible de charger la dernière sauvegarde.");
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
	//POURQUOI CA NE BOUCLE PAS ???!!					
							while(erreur){
								try{
									genAlea = Boolean.parseBoolean(Utils.scanner.nextLine());
								}catch(InputMismatchException e){
									System.out.println("Entrée invalide !");
								}
								erreur = false;
							}
							
							
							display = false;
							break;
						case CONFIGURATION:
							
							String[] returns = GameBase.loadConfig("default");
							
							plateau = new BoardGame(Integer.parseInt(returns[0]), Integer.parseInt(returns[1]));
							nbPlayers = Integer.parseInt(returns[2]);
							genAlea = Boolean.parseBoolean(returns[3]);
							
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
			
			if(genAlea) {
				generationAleatoire(plateau,joueurs);
				Thread.sleep(1000);
				Utils.progressivePrint("Génération aléatoire effectuée, bon jeu !\n", DELAY);
			};
			
			GameBase.play(plateau, joueurs);
		}

		Utils.shutdown();
	}
	
	/**Méthode pour pouvoir générer le plateau d'une partie ultérieurement commenc�e
	* @param saveName Le nom du fichier correspondant � la partie sauvegarder
	* @return le plateau de jeu
	* @throws IOException */
	public static BoardGame generateBoardGameFromSave(String saveName) throws IOException {
		Path path = Paths.get(Utils.dir + "saves/" + saveName + ".txt");
		BoardGame plateau = null;
		if(path.toFile().exists()) {
			ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(path);
			String line = lines.get(1);
				String[] options = line.split(":");
				plateau = new BoardGame(Integer.parseInt(options[0]),Integer.parseInt(options[1]), saveName);
		}
		return plateau;
	}
	
	/**Méthode pour pouvoir générer la liste de joueurs et les pions de la partie commencée ultérieurement
	* @param saveName Le nom du fichier correspondant à la partie sauvegarder
	* @param plateau le plateau retourner dans la m�thode generateBoardGameFromSave()
	* @return les joueurs
	* @throws IOException */
	public static List<Player> generatePlayersAndPlacePawnsFromSave(BoardGame plateau, String saveName) throws IOException {
		Path path = Paths.get(Utils.dir + "saves/" + saveName + ".txt");
		List<Player> joueurs = new ArrayList<Player>();
		if(path.toFile().exists()) {
			ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(path);
			int lineNumber=1;
			for(String line : lines) {
				if(lineNumber > 2) {
					String[] options = line.split(";");
					
					String[] firstOptions = options[0].split(":");
					boolean ia = firstOptions[0].equals("IA");
					Player p = new Player(firstOptions[0], Integer.parseInt(firstOptions[1]), ia);
						p.setColor((Color) Paint.valueOf(firstOptions[2]));
					if(firstOptions.length == 4) {
						String[] coords = firstOptions[3].split(",");
						int x = Integer.parseInt(coords[0]);
						int y = Integer.parseInt(coords[1]);
						plateau.placeTrap(x, y, p);
					}
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
		}
		return joueurs;
	}
	
	/**Sauvegarde d'une partie commenc�e
	* @param plateau plateau du jeu
	* @param joueurs l'ensemble des joueurs
	* @throws IOException */
	public static void saveGame(BoardGame plateau, List<Player> joueurs) throws IOException {
		File saveFile = new File(Utils.dir + "saves/" + plateau.getSaveName() + ".txt");
		try {
			saveFile.createNewFile();
		} catch(IOException e) {
			saveFile.delete();
			saveFile.createNewFile();
		}
		PrintWriter saveFileWriter = new PrintWriter(Utils.dir + "saves/" + plateau.getSaveName() + ".txt", StandardCharsets.UTF_8);
		/*FORMAT
		 * @date
		 * @nbcotes:@nbformes
		 * @nomjoueur1:@numjoueur1:color;@coordsPion1;@coordsPion2;@coordsPion3;
		 * @nomjoueur2:@numjoueur2:color;@coordsPion1;@coordsPion2;@coordsPion3;
		 */
			saveFileWriter.println(LocalDateTime.now());
			saveFileWriter.println(plateau.getNbSides() + ":" + plateau.getNbShapes());
			for(Player p : joueurs) {
				saveFileWriter.print(p.getName() + ":" + p.getNumber() + ":" + p.getColor());
				Trap trap = p.getTrap();
				if(trap != null) {
					saveFileWriter.print(":" + (trap.getX()-1) + "," + (trap.getY()-1));
				}
				saveFileWriter.print(";");
				if(p.pawnsLeft() < 3) {
					Square[] pawns = plateau.playerPawns(p);
					for(int i=0;i<(3-p.pawnsLeft());i++) {
						saveFileWriter.print(pawns[i].X + ":" + pawns[i].Y + ";");
					}
				}
				saveFileWriter.println();
			}
		saveFileWriter.close();
	}
	
	/**Afficher les param�tres du plateau de la partie sauvegard�e
	* @param name Nom du fichier correspondant � la partie sauvegard�e
	* @return le plateau
	* @throws IOException */
	public static String[] loadConfig(String name) throws IOException {
		Path path = Paths.get(Utils.dir + "configs/" + name + ".txt");
		ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(path);
		String[] returns = new String[4];
		for(String line : lines) {
			String[] options = line.split(" ");
			switch(options[0]) {
				case "@cotesformes":
					returns[0] = options[1];
					break;
				case "@nbformes":
					returns[1] = options[1];
					break;
				case "@nbplayers":
					returns[2] = options[1];
					break;
				case "@genalea":
					returns[3] = options[1];
					break;
			}
		}
		
		return returns;
	}

	/**Crée le plateau de jeu selon les entrées
	* @throws IOException */             
	public static BoardGame createBoardGame() throws Exception {
		boolean erreur1 = true;
		boolean erreur2 = true;
		int nbSides = 0;
		int nbShapes = 0;
		
		Utils.progressivePrint("Bienvenue sur LineUp 3\n", DELAY);

		Thread.sleep(1000);

		Utils.progressivePrint("Veuillez entrer le nombre de côtés des formes du plateau : \n", DELAY);
		nbSides = Utils.entrerInt(1, 1000);
		

		Utils.progressivePrint("Veuillez entrer le nombre de formes du plateau : \n", DELAY);
		nbShapes = Utils.entrerInt(1, 1000);
		

		return new BoardGame(nbSides, nbShapes);
	}
	

	/**Retourne le nombre de joueur saisi par l'utilisateur
	* @throws IOException 
	* @throws InterruptedException */
	public static int numberPlayer()throws IOException, InterruptedException {

		Utils.progressivePrint("Combien de joueurs ? (entre 1 et 4)\n" , DELAY);

		Thread.sleep(1000);

		return Utils.entrerInt(1, 4);
	}
	

	/**Crée un joueur avec le nom saisi par l'utilisateur 
	* @throws IOException 
	* @throws InterruptedException */
	public static Player createPlayer(String name) throws IOException, InterruptedException {

		System.out.println("Pseudo du " + name +  " ?");
		
		String pseudo = Utils.scanner.nextLine();

		return new Player(pseudo, false);
	}

	/**Crée par défaut un joueur nommé IA*/
	public static Player createPlayer() {
		return new Player("IA", true);
	}
	
	/**Permet la génération aléatoire des pions de tout les joueurs
	* @param plateau plateau du jeu
	* @param joueurs l'ensemble des joueurs */
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
	}

	/**Montre le plateau
	 * @param plateau Plateau à afficher*/
	public static void showPlate(BoardGame plateau) throws Exception,IOException, InterruptedException {
		Utils.progressivePrint("Voici le plateau \n", DELAY);
		System.out.println(plateau.toStringIntelligent());
	}

	/**Execute les méthodes selon les choix d'actions du joueur
	 * @param plateau plateau actuel
	 * @param p joueur actuel
	 * @throws InterruptedException */
	public static void action(BoardGame plateau, Player p) throws InterruptedException {
		int choice;
		

		if(p.pawnsLeft() > 0) {
			if(!p.isIA()){

			
				System.out.println(p + " : " + "que voulez vous faire ?\n\t1. Placer un pion (pions restants : " + p.pawnsLeft() + ")");
				choice = Utils.entrerInt(1,1);
				if(choice == 1 && p.canPlacePawn()) {
					while(!actionPlacePawn(plateau, p)) System.out.println("Impossible de placer le pion !");
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
					if(p.canPlaceTrap()) {
						while(!actionPlaceTrap(plateau, p)) System.out.println("Impossible de placer le piège !");
					}
				}
			}else{
				Utils.progressivePrint("Au tour de l'IA de jouer !\n", GameBase.DELAY);
				
				int[] coords = iaMove(plateau,p);
				
				Utils.progressivePrint("L'IA se déplace de la case " + (coords[0]+1) + "," + (coords[1]+1) + " à la case " + (coords[2]+1) + "," + (coords[3]+1) + "\n", GameBase.DELAY);

				//System.out.println("Waw ça passe ! :D");
			}
		}
	}
	
	public static boolean actionPlaceTrap(BoardGame plateau, Player p) {

		System.out.print("Veuillez entrer les coordonnées du piège:\nx : ");
		int x = Utils.scanner.nextInt()-1;
		System.out.print("y : ");
		int y = Utils.scanner.nextInt()-1;
		
		return plateau.placeTrap(x, y, p);
		
	}
	
	public static int[] iaMove(BoardGame plateau, Player p) {
		Square[] tabCases = plateau.playerPawns(p);

		int x;
		int y;
		
		int randCase = Utils.random(0, 3);
		x = tabCases[randCase].X;
		y = tabCases[randCase].Y;
		
		do{
			randCase = Utils.random(0, 3);
			x = tabCases[randCase].X-1;
			y = tabCases[randCase].Y-1;
		}while(!plateau.IcanMove(x, y));

		int newX;
		int newY;

		boolean canPut = false;
		
		do{
			newX = x + Utils.random(-2,2);
			newY = y + Utils.random(-2,2);
			canPut = plateau.movePawn(x, y, newX, newY, p);
		}while(!canPut);
		
		return new int[] {
				x,y,newX,newY
		};
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
			int x = Utils.scanner.nextInt()-1;
			System.out.print("y : ");
			int y = Utils.scanner.nextInt()-1;
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
		int x1 = Utils.scanner.nextInt()-1;
		System.out.print("y : ");
		int y1 = Utils.scanner.nextInt()-1;

		System.out.print("\nVeuillez entrer les coordonnées de destination:\nx : ");
		int x2 = Utils.scanner.nextInt()-1;
		System.out.print("y : ");
		int y2 = Utils.scanner.nextInt()-1;
		return plateau.movePawn(x1, y1, x2, y2, p);

	}
	
}