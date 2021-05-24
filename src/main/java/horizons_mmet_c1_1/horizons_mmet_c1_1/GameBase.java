package horizons_mmet_c1_1.horizons_mmet_c1_1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class GameBase {

	private static Scanner scanner = new Scanner(System.in);
	private static final int DELAY = 10;

	/**Algo principal de jeu avec les actions*/
	public static void game()throws Exception,IOException, InterruptedException {

		BoardGame plateau = GameBase.createBoardGame();

		Thread.sleep(1000);

		int nb = GameBase.numberPlayer();
		
		List<Player> joueurs = new ArrayList<Player>();
		
		if(nb == 1) {
			joueurs.add(GameBase.createPlayer("Joueur"));
			joueurs.add(GameBase.createPlayer());
		}else {
			for(int i=1;i<=nb;i++) joueurs.add(GameBase.createPlayer("Joueur " + i));
		}

		Iterator<Player> it = new Player_IT(joueurs);

		Thread.sleep(1000);

		GameBase.generationAleatoire(plateau, joueurs);

		while(true) {
			
			showPlate(plateau);
			action(plateau, it.next());

		}
	}

	/**Crée le plateau de jeu selon les entrées*/             
	public static BoardGame createBoardGame() throws Exception {

		Utils.progressivePrint("Bienvenue sur LineUp 3\n", DELAY);

		Thread.sleep(1000);

		Utils.progressivePrint("Veuillez entrer le nombre de côtés des formes du plateau : \n", DELAY);

		int nbSides = scanner.nextInt();

		Utils.progressivePrint("Veuillez entrer le nombre de formes du plateau : \n", DELAY);

		int nbShapes = scanner.nextInt();

		return new BoardGame(nbSides, nbShapes);
	}
	

	/**Retourne le nombre de joueur saisit par l'utilisateur*/
	public static int numberPlayer()throws IOException, InterruptedException {

		Utils.progressivePrint("Combien de joueurs ? (entre 1 et 9)\n" , DELAY);

		Thread.sleep(1000);

		return Utils.entrerInt(1, 9);
	}
	

	/**Crée un joueur avec le nom saisit par l'utilisateur */
	public static Player createPlayer(String name) throws IOException, InterruptedException {

		Utils.progressivePrint("Pseudo du " + name +  " ?\n", DELAY);

		return new Player(scanner.next(), false);
	}

	/**Crée par défaut un joueur nommé IA*/
	public static Player createPlayer() {
		return new Player("IA", true);
	}
	
	public static void generationAleatoire(BoardGame plateau, List<Player> players) throws InterruptedException {
		Utils.progressivePrint("Génération aléatoire des pions ? (true ou false)\n", DELAY);
		boolean aleatoire = scanner.nextBoolean();
		if(aleatoire) {
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
		Thread.sleep(1000);
		Utils.progressivePrint("Génération aléatoire effectuée, bon jeu !\n", DELAY);
	}

	/**Montre le plateau
	 * @param plateau Plateau à afficher*/
	public static void showPlate(BoardGame plateau) throws Exception,IOException, InterruptedException {
		Utils.progressivePrint("Voici le plateau \n", DELAY);
		System.out.println(plateau.toStringIntelligent());
	}

	/**Execute les méthodes selon les choix d'actions du joueur*/
	public static void action(BoardGame plateau, Player p) {
		int choice;
		if(p.pawnsLeft() > 0) {
			System.out.println(p + " : " + "que voulez vous faire ?\n\t1. Placer un pion (pions restants : " + p.pawnsLeft() + ")");
			choice = Utils.entrerInt(1,1);
			if(choice == 1 && p.canPlacePawn()) {
				while(!actionPlacePawn(plateau, p)) System.out.println("Impossible de placer le pion !");
			} else if(choice == 2) {
				// rien
			}
		} else {
			System.out.println(p + " : " + "que voulez vous faire ?\n\t1. Déplacer un pion\n\t2. Placer un piège");
			choice = Utils.entrerInt(1,2);
			if(choice == 1) {
				while(!actionMovePawn(plateau,p)) System.out.println("Impossible de déplacer le pion !");
			} else if(choice == 2) {
				// Pas encore de pièges !
			}
		}
	}
	/**Fait l'action de placer un pion*/
	public static boolean actionPlacePawn(BoardGame plateau, Player p1) {
		System.out.print("Veuillez entrer les coordonnées :\nx : ");
		int x = scanner.nextInt()-1;
		System.out.print("y : ");
		int y = scanner.nextInt()-1;
		return plateau.placePawn(x, y, p1);
	}

	/**Fait l'action de déplacer un pion*/
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
}