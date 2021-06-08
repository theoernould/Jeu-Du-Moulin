package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import classes.Horizons;
import javafx.fxml.Initializable;

public class CommencerController implements Initializable {
	public void commencer() throws IOException {
		Horizons.setSceneFromFile("menu", "Menu");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
}
