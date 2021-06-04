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

	final String dir = System.getProperty("user.dir") + File.separator;

	/**Boucle principale de jeu
	 * @throws IOException */
	public void start(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		//System.out.println(dir + "fxml/accueil.fxml");
		URL fxmlFileUrl = getClass().getResource("accueil.fxml");
		if (fxmlFileUrl == null) {
			System.out.println("Impossible de charger le fichier fxml");
			System.exit(-1);
		}
		loader.setLocation(fxmlFileUrl);
		Parent root = loader.load();

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Sélection");
		stage.show();
	}



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
