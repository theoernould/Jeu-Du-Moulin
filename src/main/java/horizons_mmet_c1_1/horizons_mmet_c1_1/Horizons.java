package horizons_mmet_c1_1.horizons_mmet_c1_1;

import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Horizons extends Application {
	private static Scanner scanner = new Scanner(System.in);
	private static final int DELAY = 10;
	
	public void start(Stage stage) {
        BorderPane root = new BorderPane();
        
        Scene scene = new Scene(root);
        stage.setTitle("Hello Paint");
        stage.setScene(scene);
        stage.show();
	}
	
	private static void progressivePrint(String str, int delay) throws InterruptedException {
		for(String c : str.split("")) {
			System.out.print(c);
			Thread.sleep(delay);
		}
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
	        //Application.launch(args);
			
			progressivePrint("Bienvenue sur LineUp 3 !\nVous allez configurer le plateau de jeu...\n", DELAY);
			
			Thread.sleep(1000);
			
			progressivePrint("Veuillez entrer le nombre de côtés des formes du plateau : \n", DELAY);
			
			int nbSides = scanner.nextInt();
			
			progressivePrint("Veuillez entrer le nombre de formes du plateau : \n", DELAY);
			
			int nbShapes = scanner.nextInt();
			
			Thread.sleep(1000);
			
			progressivePrint("Comment t'appelles-tu ?\n", DELAY);
			
			Player p1 = new Player(scanner.next());
			
			Thread.sleep(1000);
			
			progressivePrint("\nVoici le plateau :\n", DELAY);

			BoardGame plateau = new BoardGame(nbSides, nbShapes);
			
			GameSteps etape = GameSteps.PLACEMENT;
			
			while(true) {
				System.out.println(plateau.advancedDisplay());
				System.out.println("Que voulez vous faire ?\n\t1. Placer un pion (pions restants : " + p1.pawnsLeft() + ")\n\t2. Déplacer un pion");
				int choice = scanner.nextInt();
				if(choice == 1 && p1.canPlacePawn()) {
					System.out.print("Veuillez entrer les coordonnées :\nx : ");
					int x = scanner.nextInt();
					System.out.print("y : ");
					int y = scanner.nextInt();
					plateau.placePawn(x, y, p1);
				} else if(choice == 2) {
					System.out.print("Veuillez entrer les coordonnées d'origine:\nx : ");
					int x1 = scanner.nextInt();
					System.out.print("y : ");
					int y1 = scanner.nextInt();
					
					System.out.print("\nVeuillez entrer les coordonnées de destination:\nx : ");
					int x2 = scanner.nextInt();
					System.out.print("y : ");
					int y2 = scanner.nextInt();
					plateau.movePawn(x1, y1, x2, y2);
				} else {
					System.out.println("Impossible");
				}
			}
				
	}

}
