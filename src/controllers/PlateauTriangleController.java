package controllers;

import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import horizons_ihm.Horizons;
import horizons_ihm.Player;
import horizons_ihm.Player_IT;
import horizons_ihm.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class PlateauTriangleController implements Initializable {
	@FXML Label tourJoueur;
	@FXML Circle couleurJoueur;
	@FXML VBox joueurs;
	@FXML FlowPane pions;
	@FXML FlowPane pieges;
	@FXML FlowPane blocages;
	@FXML Pane plateau;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		for(Player p : Horizons.joueurs) {
			if(p.getName() != "Ajouter un joueur") {
				HBox box = new HBox();
					Circle circle = new Circle(12.5, p.getColor());
					Label name = new Label(p.getName());
					box.getChildren().addAll(circle, name);
				joueurs.getChildren().add(box);
				box.setSpacing(15);
			}
		}
		
		/*Iterator<Player> it = new Player_IT(Horizons.joueurs);

		Player gagnant = null;
		
		while(gagnant == null) {

			Player p = it.next();
			displayPlayer(p);
			gagnant = Horizons.plateau.gameWon(Horizons.joueurs);
			
			try {
				Thread.sleep(50000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		File saveFile = new File(Utils.dir + "saves/last.txt");
			saveFile.delete();*/
	}
	
	private void displayPlayer(Player p) {
		tourJoueur.setText("Au tour de " + p.getName());
		couleurJoueur.setFill(p.getColor());
		System.out.println(p.pawnsLeft());
		for(int i=0;i<p.pawnsLeft();i++) {
			Circle circle = new Circle(75.0, p.getColor());
			pions.getChildren().add(circle);
		}
	}
	
}
