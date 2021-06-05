package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import horizons_ihm.GameBase;
import horizons_ihm.Horizons;
import horizons_ihm.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class SauvegardesController implements Initializable {
	@FXML ListView<Label> liste;
	
	public void choisir() throws IOException {
		String saveName = liste.getSelectionModel().getSelectedItem().getId();
		Horizons.plateau = GameBase.generateBoardGameFromSave(saveName);
		Horizons.joueurs = GameBase.generatePlayersAndPlacePawnsFromSave(Horizons.plateau, saveName);
		Horizons.setSceneFromFile(saveName, "Plateau");
	}
	
	public void supprimer() {
		Label item = liste.getSelectionModel().getSelectedItem();
		String saveName = item.getId();
		File saveFile = new File(Utils.dir + "saves/" + saveName + ".txt");
		saveFile.delete();
		liste.getItems().remove(item);
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
