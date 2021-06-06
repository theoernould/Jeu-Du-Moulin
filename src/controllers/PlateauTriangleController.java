package controllers;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import horizons_ihm.Horizons;
import horizons_ihm.Player;
import horizons_ihm.Player_IT;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class PlateauTriangleController implements Initializable {
	@FXML Label tourJoueur;
	@FXML Circle couleurJoueur;
	@FXML VBox joueurs;
	@FXML FlowPane pions;
	@FXML FlowPane pieges;
	@FXML FlowPane blocages;
	@FXML Pane plateau;
	
	Iterator<Player> it;
	Player actualPlayer;
	
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
		
		/*for(int x=1;x<Horizons.plateau.getNbShapes();x++) {
			for(int y=1;y<Horizons.plateau.getNbSides();y++) {
				
			}
		}*/
		
		for(Node n : plateau.getChildren()) {
			if(n instanceof Circle) {
				Circle circle = (Circle) n;
			    int[] coords = getCoordsFromCircle(circle);
					circle.setOnDragDetected(mouseEvent -> {
						final Dragboard dragBroard = circle.startDragAndDrop(TransferMode.COPY);
						final ClipboardContent content = new ClipboardContent();
						content.putString("drag circle");
						
						DataFormat paintFormat = DataFormat.lookupMimeType(Paint.class.getName());
			            paintFormat = (paintFormat == null) ? new DataFormat(Paint.class.getName()) : paintFormat;
			            
			            final Color color = (Color) circle.getFill(); 
			            final int red = (int) (255 * color.getRed()); 
			            final int green = (int) (255 * color.getGreen()); 
			            final int blue = (int) (255 * color.getBlue()); 
			            final int alpha = (int) (255 * color.getOpacity()); 
			            final int argb = alpha << 24 | red << 16 | green << 8 | blue << 0; 
			            content.put(paintFormat, argb);
			            
						final WritableImage capture = circle.snapshot(null, null); 
			            content.putImage(capture);
			            dragBroard.setContent(content);
			            mouseEvent.consume();
					});
					circle.setOnDragOver(dragEvent -> { 
					    final Dragboard dragBroard = dragEvent.getDragboard(); 
					    DataFormat paintFormat = DataFormat.lookupMimeType(Paint.class.getName()); 
					    paintFormat = (paintFormat == null) ? new DataFormat(Paint.class.getName()) : paintFormat;
					    if (dragEvent.getGestureSource() != circle && dragBroard.hasContent(paintFormat) && ((Node) dragEvent.getGestureSource()).getParent().getId() != "blocages" && Horizons.plateau.getSquarePlayer(coords[0], coords[1]) == null) { 
					        // Indique les modes de transfert autorisés sur cette destination. 
					        dragEvent.acceptTransferModes(TransferMode.COPY); 
					    }
					    dragEvent.consume();
					});
					circle.setOnDragDropped(dragEvent -> { 
					    boolean success = false; 
					    try {
					    	Circle circleOrigin = (Circle) dragEvent.getGestureSource();
					    		Node parentNode = circleOrigin.getParent();
				    			String id = parentNode.getId();
					    		if(parentNode instanceof FlowPane) {
					    			if(id.equals("pions")) {
					    				Horizons.plateau.placePawn(coords[0], coords[1], actualPlayer);
										displayPlayer(it.next());
								    	refreshBoardGame();
					    			} else if(id.equals("pieges")) {
					    				// Placer un piège
					    			}
					    		} else if(parentNode instanceof Pane) {
					    			int[] originCoords = getCoordsFromCircle(circleOrigin);
					    			if(Horizons.plateau.movePawn(originCoords[0], originCoords[1], coords[0], coords[1], actualPlayer)) {
								    	refreshBoardGame();
								    	if(Horizons.plateau.gameWon(Horizons.joueurs) != null) {
								    		Horizons.setSceneFromFile("victoire", "Victoire");
								    	} else {
								    		displayPlayer(it.next());
								    	}
					    			}
					    		}
					        success = true; 
					    } catch (Exception ex) { 
					    	
					    } finally { 
					        dragEvent.setDropCompleted(success); 
					        dragEvent.consume(); 
					    } 
					});
			}
 		}
		
		it = new Player_IT(Horizons.joueurs);
		
		displayPlayer(it.next());
	}
	
	private int[] getCoordsFromCircle(Circle circle) {
		String[] coordsStr = circle.getId().split("_");
		int[] coords = new int[2];
			coords[0] = Integer.parseInt(coordsStr[0])-1;
			coords[1] = Integer.parseInt(coordsStr[1])-1;
		return coords;
	}
	
	private void refreshBoardGame() {
		for(Node n : plateau.getChildren()) {
			if(n instanceof Circle) {
				Circle circle = (Circle) n;
					int[] coords = getCoordsFromCircle(circle);
					Player p = Horizons.plateau.getSquarePlayer(coords[0], coords[1]);
					if(p != null) {
						circle.setFill(p.getColor());
						if(p.equals(actualPlayer)) {
							circle.setCursor(Cursor.OPEN_HAND);
						} else {
							circle.setCursor(Cursor.DEFAULT);
						}
					} else {
						circle.setFill(Color.WHITE);
						circle.setCursor(Cursor.DEFAULT);
					}
			}
		}
	}
	
	private void displayPlayer(Player p) {
		actualPlayer = p;
		tourJoueur.setText("Au tour de " + p.getName());
		couleurJoueur.setFill(p.getColor());
		pions.getChildren().clear();
		pieges.getChildren().clear();
		blocages.getChildren().clear();
		if(p.pawnsLeft() > 0) {
			for(int i=0;i<p.pawnsLeft();i++) {
				Circle circle = new Circle(25.0, p.getColor());
					circle.setCursor(Cursor.OPEN_HAND);
					circle.setOnDragDetected(mouseEvent -> {
						final Dragboard dragBroard = circle.startDragAndDrop(TransferMode.COPY);
						final ClipboardContent content = new ClipboardContent();
						content.putString("drag circle");
						
						DataFormat paintFormat = DataFormat.lookupMimeType(Paint.class.getName());
			            paintFormat = (paintFormat == null) ? new DataFormat(Paint.class.getName()) : paintFormat;
			            
			            final Color color = (Color) circle.getFill(); 
			            final int red = (int) (255 * color.getRed()); 
			            final int green = (int) (255 * color.getGreen()); 
			            final int blue = (int) (255 * color.getBlue()); 
			            final int alpha = (int) (255 * color.getOpacity()); 
			            final int argb = alpha << 24 | red << 16 | green << 8 | blue << 0; 
			            content.put(paintFormat, argb);
			            
						final WritableImage capture = circle.snapshot(null, null); 
			            content.putImage(capture);
			            dragBroard.setContent(content);
			            mouseEvent.consume();
					});
				pions.getChildren().add(circle);
			}
		} else {
			pions.getChildren().add(new Label("Plus aucun pion à placer"));
		}
	}
	
	public void handle(MouseEvent event) {
		
	}
	
}
