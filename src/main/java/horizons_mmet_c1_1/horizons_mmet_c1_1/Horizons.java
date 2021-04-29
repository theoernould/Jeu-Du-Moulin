package horizons_mmet_c1_1.horizons_mmet_c1_1;

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
	
	public static void main(String[] args) {
	        Application.launch(args);
	        

			//Anciennes coords
			int x = 1;
			int y = 1;
			
			//Demande de déplacement
			int newX = 1;
			int newY = 2;
			
			BoardGame plateau = new BoardGame(3);
				
	}

}
