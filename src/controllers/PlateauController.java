package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import classes.GameBase;
import classes.Horizons;
import classes.Player;
import classes.Player_IT;
import classes.Trap;
import classes.Utils;
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

public class PlateauController implements Initializable {
	@FXML Label tourJoueur;
	@FXML Circle couleurJoueur;
	@FXML VBox joueurs;
	@FXML FlowPane pions;
	@FXML FlowPane pieges;
	@FXML FlowPane blocages;
	@FXML Pane plateau;
	
	Iterator<Player> it;
	Player actualPlayer;
	
	public void retour() throws IOException {
		Horizons.setSceneFromFile("menu", "Menu");
	}
	
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
		
		for(Node n : plateau.getChildren()) {
			if(n instanceof Circle) {
				Circle circle = (Circle) n;
			    int[] coords = getCoordsFromCircle(circle);
			    	Player p = Horizons.plateau.getSquarePlayer(coords[0], coords[1]);
			    	if(p != null) {
			    		circle.setFill(p.getColor());
			    	}
					circle.setOnDragDetected(mouseEvent -> {
						if(Horizons.plateau.getSquarePlayer(coords[0], coords[1]) == actualPlayer) {
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
						}
					});
					circle.setOnDragOver(dragEvent -> { 
					    final Dragboard dragBroard = dragEvent.getDragboard(); 
					    DataFormat paintFormat = DataFormat.lookupMimeType(Paint.class.getName()); 
					    paintFormat = (paintFormat == null) ? new DataFormat(Paint.class.getName()) : paintFormat;
					    System.out.println();
					    if (dragEvent.getGestureSource() != circle && dragBroard.hasContent(paintFormat) && ((Node) dragEvent.getGestureSource()).getParent().getId() != "blocages" && Horizons.plateau.getSquarePlayer(coords[0], coords[1]) == null) { 
					    	Circle circleOrigin = (Circle) dragEvent.getGestureSource();
					    	String parentId = circleOrigin.getParent().getId();
					    	if(parentId.equals("plateau")) {
						        int[] originCoords = getCoordsFromCircle(circleOrigin);
						        if(Horizons.plateau.squaresNeighbors(coords[0]+1, coords[1]+1, originCoords[0]+1, originCoords[1]+1)) {
						        	Color originColor = (Color) circleOrigin.getFill();
						        	circle.setFill(new Color(originColor.getRed(), originColor.getGreen(), originColor.getBlue(), 0.5));
						        	dragEvent.acceptTransferModes(TransferMode.COPY);
						        }
					        } else if(parentId.equals("pieges")){
					        	if(actualPlayer.canPlaceTrap()) {
						        	circle.setStroke(new Color(1, 0, 0, 0.5));
						        	circle.setStrokeWidth(2);
						        	dragEvent.acceptTransferModes(TransferMode.COPY);
					        	}
					        } else if(parentId.equals("pions")) {
					        	Color originColor = (Color) circleOrigin.getFill();
					        	circle.setFill(new Color(originColor.getRed(), originColor.getGreen(), originColor.getBlue(), 0.5));
					        	dragEvent.acceptTransferModes(TransferMode.COPY);
					        }
					    }
					    dragEvent.consume();
					});
					circle.setOnDragExited(dragEvent -> {
						final Dragboard dragBroard = dragEvent.getDragboard(); 
					    DataFormat paintFormat = DataFormat.lookupMimeType(Paint.class.getName()); 
					    paintFormat = (paintFormat == null) ? new DataFormat(Paint.class.getName()) : paintFormat;
					    if (dragEvent.getGestureSource() != circle && dragBroard.hasContent(paintFormat) && Horizons.plateau.getSquarePlayer(coords[0], coords[1]) == null) {
					    	Circle circleOrigin = (Circle) dragEvent.getGestureSource();
				    		Node parentNode = circleOrigin.getParent();
				    		Trap trap = Horizons.plateau.getSquareTrap(coords[0], coords[1]);
				    		if(trap == null || (trap != null && trap.getPlayer() != actualPlayer)) {
					        	circle.setFill(Color.WHITE);
					        	circle.setStroke(Color.BLACK);
					        	circle.setStrokeWidth(1);
				    		}
				    		if(parentNode != null) {
				    			String id = parentNode.getId().trim();
				    			if(id.equals("plateau")) {
							        int[] originCoords = getCoordsFromCircle(circleOrigin);
							        if(Horizons.plateau.squaresNeighbors(coords[0]+1, coords[1]+1, originCoords[0]+1, originCoords[1]+1)) {
							        	circle.setFill(Color.WHITE);
							        }
				    			} else {
				    				
				    			}
				    		}
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
					    				if(Horizons.plateau.placePawn(coords[0], coords[1], actualPlayer)) {
							    			play();
					    				}
					    			} else if(id.equals("pieges")) {
					    				if(Horizons.plateau.placeTrap(coords[0], coords[1], actualPlayer)) {
					    					play();
					    				}
					    			}
					    		} else if(parentNode instanceof Pane) {
					    			int[] originCoords = getCoordsFromCircle(circleOrigin);
					    			if(Horizons.plateau.movePawn(originCoords[0], originCoords[1], coords[0], coords[1], actualPlayer)) {
					    				play();
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
		
		try {
			actualPlayer = it.next();
			displayPlayer(actualPlayer);
			refreshBoardGame();
    		GameBase.saveGame(Horizons.plateau, Horizons.joueurs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void play() throws IOException {
    	refreshBoardGame();
		GameBase.saveGame(Horizons.plateau, Horizons.joueurs);
		if(Horizons.plateau.gameWon(Horizons.joueurs) == null) {
			Player nextPlayer = it.next();
			if(nextPlayer.isIA()) {
				playIA(nextPlayer);
				displayPlayer(it.next());
			}
			else displayPlayer(nextPlayer);
	    	refreshBoardGame();
		} else {
	    	refreshBoardGame();
    		File saveFile = new File(Utils.dir + "saves/" + Horizons.plateau.getSaveName() + ".txt");
			saveFile.delete();
    		Horizons.setSceneFromFile("commencer", "Victoire");
		}
	}
	
	private void playIA(Player iaPlayer) {
		if(iaPlayer.getTrap() != null) iaPlayer.getTrap().roundPass();
		if(iaPlayer.canPlacePawn()) {
			int rX;
			int rY;
			do {
				rX = Utils.random(0, Horizons.plateau.getNbShapes());
				rY = Utils.random(0, Horizons.plateau.getNbSides()*2);
			} while (!Horizons.plateau.placePawn(rX, rY, iaPlayer));
		} else {
			int choice = Utils.random(1, 3);
			if(choice == 1 || !iaPlayer.canPlaceTrap()) { // Déplacer pion
				GameBase.iaMove(Horizons.plateau, iaPlayer);
			} else if(choice == 2) { // Placer piège
				if(iaPlayer.canPlaceTrap()) {
					int rX;
					int rY;
					do {
						rX = Utils.random(0, Horizons.plateau.getNbShapes());
						rY = Utils.random(0, Horizons.plateau.getNbSides()*2);
					} while (!Horizons.plateau.placeTrap(rX, rY, iaPlayer));
				}
			} else if(choice == 3) { // Placer blocage d'arc
				
			}
		}
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
						Trap trap = Horizons.plateau.getSquareTrap(coords[0], coords[1]);
						circle.setFill(Color.WHITE);
						circle.setCursor(Cursor.DEFAULT);
						if(trap != null && trap.getPlayer() == actualPlayer) {
							circle.setStroke(Color.RED);
							circle.setStrokeWidth(2);
						} else {
							circle.setStroke(Color.BLACK);
							circle.setStrokeWidth(1);
						}
					}
			}
		}
	}
	
	private void displayPlayer(Player p) throws IOException {
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
		
		if(p.canPlaceTrap()) {
			Circle circle = new Circle(25.0, Color.WHITE);
			circle.setStroke(Color.RED);
			circle.setStrokeWidth(5);
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
		pieges.getChildren().add(circle);
		} else {
			pieges.getChildren().add(new Label("Plus de piège à placer"));
		}
	}
	
}
