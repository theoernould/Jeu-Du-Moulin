package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import classes.Horizons;
import classes.Utils;
import javafx.fxml.Initializable;

public class MenuController implements Initializable {
	
	public void continuer() throws IOException {
		Horizons.setSceneFromFile("sauvegardes", "Sauvegardes");
	}
	
	public void nouvelle() throws IOException {
		Horizons.setSceneFromFile("configuration", "Configuration");
	}
	
	public void regles() throws IOException {
		Horizons.setSceneFromFile("regles", "Règles");
	}
	
	public void quitter() {
		Utils.shutdown();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
}
