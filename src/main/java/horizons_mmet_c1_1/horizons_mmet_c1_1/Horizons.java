package horizons_mmet_c1_1.horizons_mmet_c1_1;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Horizons extends Application {
	
	public void start(Stage stage) {
        BorderPane root = new BorderPane();
        
        Scene scene = new Scene(root);
        stage.setTitle("Hello Paint");
        stage.setScene(scene);
        stage.show();
	}
	
	public static void main(String[] args) throws IOException {
	        //Application.launch(args);
	        
	        // Demander type plateau
	        
	        int type = 3;

			BoardGame plateau = new BoardGame(type);
			
			Player p1 = new Player("Joueur 1");
			Player p2 = new Player("Joueur 2");
			
			
			
			System.out.println("placement : " + plateau.placePawn(1, 1, p1));
			System.out.println("placement : " + plateau.placePawn(1, 1, p1));
				
			System.out.println(plateau);
				
	}

}
