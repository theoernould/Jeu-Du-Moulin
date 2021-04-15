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
	}

}
