package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import horizons_ihm.Horizons;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class SauvegardesController implements Initializable {
	@FXML ListView<Label> liste;
	
	public void choisir() {
		System.out.println(liste.getSelectionModel().getSelectedItem().getId());
	}
	
	public void supprimer() {
		
	}
	
	public void retour() throws IOException {
		Horizons.setSceneFromFile("menu", "LineUp3");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Label last = new Label("Dernière sauvegarde");
			last.setId("last");
		liste.getItems().add(last);
	}
}
