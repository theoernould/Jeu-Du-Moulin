package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import horizons_ihm.Horizons;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class SauvegardesController implements Initializable {
	@FXML ListView liste;
	
	public void choisir() {
		
	}
	
	public void supprimer() {
		
	}
	
	public void retour() throws IOException {
		Horizons.setSceneFromFile("menu", "LineUp3");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
}
