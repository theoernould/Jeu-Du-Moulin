package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import classes.GameBase;
import classes.Horizons;
import classes.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class SauvegardesController implements Initializable {
	@FXML ListView<Label> liste;
	
	public void choisir() throws IOException {
		Label selectedItem = liste.getSelectionModel().getSelectedItem();
		if(selectedItem != null) {
			String saveName = selectedItem.getId().replace(".txt", "");
			Horizons.plateau = GameBase.generateBoardGameFromSave(saveName);
			Horizons.joueurs = GameBase.generatePlayersAndPlacePawnsFromSave(Horizons.plateau, saveName);
			Horizons.setSceneFromFile("plateau_" + Horizons.plateau.getNbSides() + "_" + Horizons.plateau.getNbShapes(), "Plateau");
		}
	}
	
	public void supprimer() {
		Label item = liste.getSelectionModel().getSelectedItem();
		if(item != null) {
			String saveName = item.getId();
			File saveFile = new File(Utils.dir + "saves/" + saveName);
			saveFile.delete();
			liste.getItems().remove(item);
		}
	}
	
	public void retour() throws IOException {
		Horizons.setSceneFromFile("menu", "LineUp3");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		File savesFolder = new File(Utils.dir + "saves");
		for(File file : savesFolder.listFiles()) {
			String name = file.getName();
			Label save = null;
			try {
				save = new Label(name + " - " + Utils.dateToString(LocalDateTime.parse(Files.readAllLines(file.toPath()).get(0))));
					save.setId(name);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			liste.getItems().add(save);
		}
	}
}
