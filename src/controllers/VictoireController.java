package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import classes.Horizons;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class VictoireController implements Initializable {
	@FXML Label text;
	
	public void revenir() throws IOException {
		Horizons.setSceneFromFile("commencer", "LineUp3");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(!Horizons.winner.isIA()) {
			text.setText("Félicitations " + Horizons.winner.getName() + ", tu as gagné !");
		} else {
			text.setText("Malheuresement, l'Intelligence Artificielle t'as battu !");
		}
	}
	
}
