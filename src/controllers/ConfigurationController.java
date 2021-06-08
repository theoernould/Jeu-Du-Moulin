package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import classes.BoardGame;
import classes.GameBase;
import classes.Horizons;
import classes.Player;
import classes.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ConfigurationController implements Initializable {
	@FXML ComboBox<Label> listeConfigs;
	@FXML CheckBox generation;
	@FXML GridPane grid;
	@FXML ComboBox<Label> listeTypes;
	@FXML ListView<HBox> listeJoueurs;
	HBox ajouterJoueur;
	
	List<ColorPicker> pickers = new ArrayList<ColorPicker>();
	int incrementNumber = 1;
	
	public void continuer() throws IOException, InterruptedException {
		String boardgameName = listeTypes.getSelectionModel().getSelectedItem().getId();
		String[] options = boardgameName.replace("plateau_", "").split("_");
		Horizons.plateau = new BoardGame(Integer.parseInt(options[0]),Integer.parseInt(options[1]));
		Horizons.joueurs = new ArrayList<Player>();
		int nbPlayers = 0;
		for(HBox box : listeJoueurs.getItems()) {
			Node labelNode = box.getChildren().get(0);
			if(labelNode instanceof Label) {
				Label label = (Label) labelNode;
				if(label.getText() != "Ajouter un joueur") {
					String name = label.getText();
					ColorPicker colorPicker;
					int i=0;
					do {
						colorPicker = pickers.get(i);
						i++;
					} while(!colorPicker.getId().equals(label.getId() + "_picker") && i<pickers.size());
					Color color = colorPicker.getValue();
					Horizons.joueurs.add(new Player(name.trim(), color, false));
					nbPlayers++;
				}
			}
		}
		if(nbPlayers >= 1 ) {
			if(nbPlayers == 1) Horizons.joueurs.add(new Player("IA", Color.rgb(Utils.random(0, 255), Utils.random(0, 255), Utils.random(0, 255)), true));
			if(generation.isSelected()) GameBase.generationAleatoire(Horizons.plateau, Horizons.joueurs);
			Horizons.setSceneFromFile(boardgameName, "Plateau");
		}
	}
	
	public void retour() throws IOException {
		Horizons.setSceneFromFile("menu", "LineUp3");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Label triangulaire = new Label("Triangulaire");
			triangulaire.setId("plateau_3_3");
			triangulaire.setTextFill(Color.BLACK);
		Label carre = new Label("Carré");
			carre.setId("plateau_4_3");
			carre.setTextFill(Color.BLACK);
		listeTypes.getItems().setAll(triangulaire, carre);
		ajouterJoueur = new HBox();
			ajouterJoueur.getChildren().add(new Label("Ajouter un joueur"));
			Button btn = new Button("+");
				btn.setOnMouseClicked(e -> {
					if(listeJoueurs.getItems().size() <= 4) createPlayer("",true);
				});
			ajouterJoueur.getChildren().add(btn);
			ajouterJoueur.setAlignment(Pos.CENTER_LEFT);
			ajouterJoueur.setSpacing(50);
		listeJoueurs.getItems().add(ajouterJoueur);
		
		try {
			String[] returns = GameBase.loadConfig("default");
			
			int nbCotesFormes = Integer.parseInt(returns[0]);
			int nbFormes = Integer.parseInt(returns[1]);
			
			if(nbCotesFormes == 3 && nbFormes == 3) listeTypes.getSelectionModel().select(0);
			else if(nbCotesFormes == 4 && nbFormes == 3) listeTypes.getSelectionModel().select(1);
			
			int nbPlayers = Integer.parseInt(returns[2]);
			for(int i=0;i<nbPlayers;i++) createPlayer("...", true);
			
			generation.setSelected(Boolean.parseBoolean(returns[3]));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void createPlayer(String name, boolean temporary) {
		HBox box = new HBox();
		if(temporary) {
			TextField text = new TextField("...");
			text.setId("" + incrementNumber);
			text.setOnKeyPressed(ev -> {
				if(ev.getCode() == KeyCode.ENTER) {
					String pseudo = text.getText();
					if(!pseudo.equals("...") && !pseudo.trim().isEmpty()) {
						Label label = new Label(pseudo);
							label.setId("" + text.getId());
							label.fontProperty().set(Font.font(Font.getDefault().getName(), Font.getDefault().getSize()+5));
						box.getChildren().add(0,label);
						box.getChildren().remove(text);
					}
				} else {
					if(text.getText().trim().startsWith("...")) text.setText(text.getText().trim().replace("...", ""));
				}
			});
			box.getChildren().add(text);
		}
			ColorPicker picker = new ColorPicker();
				picker.setId(incrementNumber + "_picker");
			pickers.add(picker);
			Button removeButton = new Button("-");
			removeButton.setOnMouseClicked(ev -> {
				pickers.remove(picker);
				listeJoueurs.getItems().remove(box);
			});
			ajouterJoueur.setAlignment(Pos.CENTER_LEFT);
			box.setSpacing(25);
			box.getChildren().addAll(picker,removeButton);
		listeJoueurs.getItems().add(listeJoueurs.getItems().size()-1, box);
		incrementNumber++;
	}
}
