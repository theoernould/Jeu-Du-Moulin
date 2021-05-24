package horizons_mmet_c1_1.horizons_mmet_c1_1;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 * Classe principale
	@author Elisa Adrianssens, Mexane Bonaventure, Maxime Bimont, Théo Ernould
*/
public class Horizons extends Application {

	/**Boucle principale de jeu*/
	public void start(Stage stage) {
		BorderPane root = new BorderPane();

		Scene scene = new Scene(root);
		stage.setTitle("Hello Paint");
		stage.setScene(scene);
		stage.show();
	}



	public static void main(String[] args) throws IOException, InterruptedException,Exception {

		
		GameBase.game();



	}


}
