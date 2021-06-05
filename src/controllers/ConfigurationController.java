package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import horizons_ihm.Horizons;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;

public class ConfigurationController implements Initializable {
	@FXML ComboBox listeConfigs;
	@FXML CheckBox generation;
	@FXML Spinner nbJoueurs;
	@FXML Spinner nbCotesFormes;
	@FXML Spinner nbFormes;
	
	public void continuer() {
		
	}
	
	public void nbJoueursClicked(MouseEvent e) {
		
	}

	public void nbCotesFormesClicked(MouseEvent e) {
		
	}
	
	public void nbFormesClicked(MouseEvent e) {
		
	}
	
	public void retour() throws IOException {
		Horizons.setSceneFromFile("menu", "LineUp3");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
}
