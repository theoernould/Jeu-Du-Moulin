package horizons_mmet_c1_1.horizons_mmet_c1_1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class GameBase {

	private static Scanner scanner = new Scanner(System.in);
	private static final int DELAY = 10;


	private static void progressivePrint(String str, int delay) throws InterruptedException {
		for(String c : str.split("")) {
			System.out.print(c);
			Thread.sleep(delay);
		}
	}

	/**Algo principal de jeu avec les actions*/
	public static void game()throws Exception,IOException, InterruptedException {

		int nb;
		Player p1 = null;
		Player p2 = null;
		Player currentPlayer = p1;


		BoardGame plateau = GameBase.createBoardGame();

		Thread.sleep(1000);

		do {
			nb = GameBase.numberPlayer();
			if(nb == 1) {
				p1 = GameBase.createPlayer("Joueur 1");
				p2 = GameBase.createPlayer();
			}else if(nb == 2) {
				p1 = GameBase.createPlayer("Joueur 1");
				p2 = GameBase.createPlayer("Joueur 2");
			}

		}while(nb != 1 && nb != 2);


		List<Player> joueurs = new ArrayList<Player>();
		joueurs.add(p1);
		joueurs.add(p2);

		Iterator<Player> it = new Player_IT(joueurs);

		Thread.sleep(1000);

		while(true) {

		    currentPlayer = p1;
			
	
			showPlate(plateau);
			action(plateau, currentPlayer);
					


			currentPlayer = p2;
			showPlate(plateau);
			action(plateau, currentPlayer);


		}
	}

	/**Crée le plateau de jeu selon les entrées*/             
	public static BoardGame createBoardGame() throws Exception {

		progressivePrint("Bienvenue sur LineUp 3\n", DELAY);

		Thread.sleep(1000);

		progressivePrint("Veuillez entrer le nombre de côtés des formes du plateau : \n", DELAY);

		int nbSides = scanner.nextInt();

		progressivePrint("Veuillez entrer le nombre de formes du plateau : \n", DELAY);

		int nbShapes = scanner.nextInt();

		return new BoardGame(nbSides, nbShapes);
	}
	

	/**Retourne le nombre de joueur saisit par l'utilisateur*/
	public static int numberPlayer()throws IOException, InterruptedException {

		progressivePrint("Combien de joueurs ? (1 ou 2)\n" , DELAY);

		Thread.sleep(1000);

		int number = scanner.nextInt();
		return number;
	}
	

	/**Crée un joueur avec le nom saisit par l'utilisateur */
	public static Player createPlayer(String name) throws IOException, InterruptedException {

		progressivePrint("Pseudo du " + name +  " ?\n", DELAY);

		return new Player(scanner.next(), false);
	}

	/**Crée par défaut un joueur nommé IA*/
	public static Player createPlayer() {
		return new Player("IA", true);
	}

	/**Montre le plateau*/
	public static void showPlate(BoardGame plateau) throws Exception,IOException, InterruptedException {
		progressivePrint("Voici le plateau \n", DELAY);
		System.out.println(plateau.toStringIntelligent());
	}

	/**Execute les méthodes selon les choix d'actions du joueur*/
	public static void action(BoardGame plateau, Player p) {

		if(p.pawnsLeft() > 0) {
			System.out.println(p + " : " + "que voulez vous faire ?\n\t1. Placer un pion (pions restants : " + p.pawnsLeft() + ")\n\t2. Déplacer un pion");
			int choice = scanner.nextInt();
			if(choice == 1 && p.canPlacePawn()) {
				actionPlacePawn(plateau, p);
			} else if(choice == 2) {
				// rien
			} else {
				System.out.println("Impossible");
			}
		} else {
			System.out.println(p + " : " + "que voulez vous faire ?\n\t1. Déplacer un pion\n\t2. Placer un piège");
			int choice = scanner.nextInt();
			if(choice == 1) {
				actionMovePawn(plateau,p);
			} else if(choice == 2) {
				// Pas encore de pièges !
			} else {
				System.out.println("Impossible");
			}
		}
	}
	/**Fait l'action de placer un pion*/
	public static void actionPlacePawn(BoardGame plateau, Player p1) {
		System.out.print("Veuillez entrer les coordonnées :\nx : ");
		int x = scanner.nextInt()-1;
		System.out.print("y : ");
		int y = scanner.nextInt()-1;
		plateau.placePawn(x, y, p1);
	}

	/**Fait l'action de déplacer un pion*/
	public static void actionMovePawn(BoardGame plateau, Player p) {

		System.out.print("Veuillez entrer les coordonnées d'origine:\nx : ");
		int x1 = scanner.nextInt()-1;
		System.out.print("y : ");
		int y1 = scanner.nextInt()-1;

		System.out.print("\nVeuillez entrer les coordonnées de destination:\nx : ");
		int x2 = scanner.nextInt()-1;
		System.out.print("y : ");
		int y2 = scanner.nextInt()-1;
		if(!plateau.movePawn(x1, y1, x2, y2, p)) System.out.println("Impossible de déplacer le pion !");;

	}
}