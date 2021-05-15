package horizons_mmet_c1_1.horizons_mmet_c1_1;

import java.io.IOException;
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
	public static void game()throws IOException, InterruptedException {

		int nb;
		Player p1 = null;
		Player p2 = null;

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

		Thread.sleep(1000);

		while(true) {
			showPlate(plateau);

			action(plateau, p1);

		}
	}

	/**Crée le plateau de jeu selon les entrées utilisateur*/
	public static BoardGame createBoardGame()throws IOException, InterruptedException {


		progressivePrint("Bienvenue sur LineUp 3 !\nVous allez configurer le plateau de jeu...\n", DELAY);

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

		return new Player(scanner.next());
	}

	/**Crée par défaut un joueur nommé IA*/
	public static Player createPlayer() {
		return new Player("IA");
	}

	/**Montre le plateau*/
	public static void showPlate(BoardGame plateau) throws IOException, InterruptedException {

		progressivePrint("Voici le plateau \n", DELAY);
		System.out.println(plateau.advancedDisplay());
	}

	/**Execute les méthodes selon les choix d'actions du joueur*/
	public static void action(BoardGame plateau, Player p1) {

		System.out.println("Que voulez vous faire ?\n\t1. Placer un pion (pions restants : " + p1.pawnsLeft() + ")\n\t2. Déplacer un pion");
		int choice = scanner.nextInt();
		if(choice == 1 && p1.canPlacePawn()) {
			actionPlacePawn(plateau, p1);
		} else if(choice == 2) {
			actionMovePawn(plateau);
		} else {
			System.out.println("Impossible");
		}
	}
	/**Fait l'action de placer un pion*/
	public static void actionPlacePawn(BoardGame plateau, Player p1) {
		System.out.print("Veuillez entrer les coordonnées :\nx : ");
		int x = scanner.nextInt();
		System.out.print("y : ");
		int y = scanner.nextInt();
		plateau.placePawn(x, y, p1);
	}

	/**Fait l'action de déplacer un pion*/
	public static void actionMovePawn(BoardGame plateau) {

		System.out.print("Veuillez entrer les coordonnées d'origine:\nx : ");
		int x1 = scanner.nextInt();
		System.out.print("y : ");
		int y1 = scanner.nextInt();

		System.out.print("\nVeuillez entrer les coordonnées de destination:\nx : ");
		int x2 = scanner.nextInt();
		System.out.print("y : ");
		int y2 = scanner.nextInt();
		plateau.movePawn(x1, y1, x2, y2);

	}
}
