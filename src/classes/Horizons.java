package classes;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Classe principale
	@author Elisa Adriaenssens, Mexane Bonaventure, Maxime Bimont, Théo Ernould
*/
public class Horizons extends Application {
	public static Stage mainStage;
	public static BoardGame plateau;
	public static List<Player> joueurs;
	public static Player winner;

	/**Boucle principale de jeu
	 * @throws IOException */
	public void start(Stage stage) throws IOException {
		mainStage = stage;
		setSceneFromFile("commencer", "LineUp3");
		//stage.getIcons().add(new Image("file:icon.png"));
		stage.show();
	}
	
	/**Change la sc�ne de l'interface graphique
	* @param fileName Nom de la scène
	* @param title Affichage du nom de la fenètre
	* @throws IOException */
	public static void setSceneFromFile(String fileName, String title) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		URL fxmlFileUrl = Horizons.class.getResource(fileName + ".fxml");
		if (fxmlFileUrl == null) {
			System.out.println("Impossible de charger le fichier fxml");
			System.exit(-1);
		}
		loader.setLocation(fxmlFileUrl);
		Parent root = loader.load();

		Scene scene = new Scene(root);
		mainStage.setScene(scene);
		mainStage.setTitle("LineUp3 - " + title);
		mainStage.getIcons().add(new Image(Horizons.class.getResourceAsStream("jeu_moulins.png")));
	}

	/**Choix du type d'affichage pour une partie
	* @param args Param�tre d'entr�e de l'utilisateur
	* @throws Exception
	* @throws IOException
	* @throws InterruptedException */
	public static void main(String[] args) throws IOException, InterruptedException,Exception {

		OptionsMenu choix = Utils.afficherMenu(Menus.CHOIX_INTERFACE);
		
		if(choix == OptionsMenu.TEXTUEL) {
			GameBase.game();
		} else if(choix == OptionsMenu.GRAPHIQUE) {
			Application.launch(args);
		} else {
			Utils.shutdown();
		}

	}

}
