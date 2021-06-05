package horizons_ihm;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe principale
	@author Elisa Adrianssens, Mexane Bonaventure, Maxime Bimont, Théo Ernould
*/
public class Horizons extends Application {
	static Stage mainStage;

	/**Boucle principale de jeu
	 * @throws IOException */
	public void start(Stage stage) throws IOException {
		mainStage = stage;
		setSceneFromFile("commencer", "LineUp3");
		//stage.getIcons().add(new Image("file:icon.png"));
		stage.show();
	}
	
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
		mainStage.setTitle(title);
	}

	

	public static void main(String[] args) throws IOException, InterruptedException,Exception {

		/*OptionsMenu choix = Utils.afficherMenu(Menus.CHOIX_INTERFACE);
		
		if(choix == OptionsMenu.TEXTUEL) {
			GameBase.game();
		} else if(choix == OptionsMenu.GRAPHIQUE) {
			Application.launch(args);
		} else {
			Utils.shutdown();
		}*/
		
		Application.launch(args);

	}

}
