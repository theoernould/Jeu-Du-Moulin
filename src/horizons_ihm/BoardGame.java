package horizons_ihm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**Class qui symbolise le plateau */
public class BoardGame {
	
	private Square[][] squares;
	private Trap[] blocages;
	
	private int nbSides;
	private int nbShapes;
	
	/**Constructeur de BoardGame
	* @param sides nombre de c√¥t√©s du plateau √† cr√©er
	* @param shapes nombre de formes du plateau √† cr√©er
	* @return BoardGame un plateau avec sides c√¥te√©s et shapes formes
	* @throws IOException */
	public BoardGame(int sides, int shapes) throws IOException {
		this.nbSides = sides;
		this.nbShapes = shapes;
		
		// Initilisation des cases qui seront plus tard occup√©es par des pions
		
		this.squares = new Square[shapes][sides*2];
		
		for(int i=0; i<nbShapes; i++) {
			for(int j=0; j<nbSides*2; j++) {
				Square square = new Square(i+1,j+1);
				this.squares[i][j] = square;
			}
		}
		
	}
	
	/**Accesseur du nombre de c√¥t√©s du plateau*/
	public int getNbSides() {
		return nbSides;
	}

	/**Accesseur du nombre de formes du plateau*/
	public int getNbShapes() {
		return nbShapes;
	}
	
	/**Retourne le joueur gagnant
	 * @param players l'ensemble des joueurs*/
	public Player gameWon(List<Player> players) {
		//System.out.println("GAME WON FUNCTION");
		for(Player p : players) {
			Square[] pawns = playerPawns(p);
			if(!p.canPlacePawn() && alignement(pawns[0],pawns[1],pawns[2])) return p;
		}
		return null;
	}
	
	/**Donne les cases occup√©es par un joueur
	* @param p joueur dont on veut savoir ses pions
	* @return Square[] un tableau de cases */
	public Square[] playerPawns(Player p) {
		Square[] pawns = new Square[3];
		int i = 0;
		for(int x=0;x<nbShapes;x++) {
			for(int y=0;y<nbSides*2;y++) {
				Square square = squares[x][y];
				if(square.playerIs(p)) {
					pawns[i] = square;
					i++;
				}
			}
		}
		return pawns;
	}

	/**
	 * Indique si une case existe 
	 * @param x Coordonn√©es en x de la case 
	 * @param y Coordonn√©es en y de la case
	 * @return boolean 
	 */
	public boolean pawnExist(int x, int y) {
		return Utils.isBetween(x, 0, squares.length-1) && Utils.isBetween(y, 0, squares[0].length-1);
	}

	/**
	 * @param s1 case 1
	 * @param s2 case 2
	 * @param s3 case 3
	 *
	 */
    public boolean alignement(Square s1, Square s2, Square s3) {
       /* if(s1.X==s2.X && s2.X==s3.X){
            if((s1.Y%2==0 && s2.Y%2!=0 && s3.Y%2!=0)){
                return true;
            }else if((s1.Y%2!=0 && s2.Y%2==0 && s3.Y%2!=0)){
                return true;
            }else if((s1.Y%2!=0 && s2.Y%2!=0 && s3.Y%2==0)){
                return true;
            }else{
                return false;
            }
        } else if(s1.Y==s2.Y && s2.Y==s3.Y && s1.Y%2==0){
            return true;
        }else{
            return false;
        }*/
    	System.out.println(s1 + " " + s2 + " " + s3);
    	System.out.println("voisins ? " + ( squaresNeighbors(s1.X, s1.Y, s2.X, s2.Y) && squaresNeighbors(s2.X, s2.Y, s3.X, s3.Y) ));
    	System.out.println("first ? " + ( (s1.X == s2.X && s2.X == s3.X) && ( (s1.Y % 2 == 1) && (s2.Y % 2 == 0) && (s3.Y % 2 == 1) ) ));
    	System.out.println("second ? " + ( (s1.Y == s2.Y && s2.Y == s3.Y) && ( (s1.X % 2 == 1) && (s2.X % 2 == 0) && (s3.X % 2 == 1) ) ));
    	return ( ( squaresNeighbors(s1.X, s1.Y, s2.X, s2.Y) && squaresNeighbors(s2.X, s2.Y, s3.X, s3.Y) ) && (
	    				( (s1.X == s2.X && s2.X == s3.X) && 
	    						( (s1.Y % 2 == 1) && (s2.Y % 2 == 0) && (s3.Y % 2 == 1) ) 
	    				)
	    					|| 
	    				( (s1.Y == s2.Y && s2.Y == s3.Y) && 
	    						( (s1.X % 2 == 1) && (s2.X % 2 == 0) && (s3.X % 2 == 1) ) 
	    				)
    				) 
    			);
    }


	/**
	 * Permet de placer un pion sur le plateau
	 * @param x Coordonn√©es en x
	 * @param y Coordonn√©es en y
	 * @param p Joueur qui souhaite placer le pion
	 * @return
	 */
	public boolean placePawn(int x, int y, Player p) {
		if(squares[x][y].addPlayer(p))  {
			p.placePawn();
			return true;
		} else return false;
		//return squares.get(getIndex(x,y)).addPlayer(p);
	}
	
	public Player getSquarePlayer(int x, int y) {
		return squares[x][y].getPlayer();
	}
	
	public boolean movePawnAbsolute(int x1, int y1, int x2, int y2) {
		return squares[x1][y1].moveTo(squares[x2][y2]);
	}
	
	public boolean movePawn(int x1, int y1, int x2, int y2, Player p) {
		//if(p.isIA()) {
			System.out.println("Coords 1 : " + x1 + " " + y1);
			System.out.println("existe ? " + pawnExist(x1,y1));
			if(pawnExist(x1,y1)) {
				System.out.println("occup√© ? " + squares[x1][y1].squareOccuped());
				System.out.println("bon joueur ?" + squares[x1][y1].playerIs(p));
			}
			System.out.println("Coords 2 : " + x2 + " " + y2);
			System.out.println("existe ? " + pawnExist(x2,y2));
			if(pawnExist(x2,y2)) {
				System.out.println("occup√© ? " + squares[x2][y2].squareOccuped());
			}
			if(pawnExist(x1,y1) && pawnExist(x2,y2)) System.out.println("voisins ? " + squaresNeighbors(x1+1, y1+1, x2+1, y2+1));
			System.out.println("--------");
		//}
		if( pawnExist(x1, y1) && pawnExist(x2,y2) && squares[x1][y1].squareOccuped() && !squares[x2][y2].squareOccuped() && squaresNeighbors(x1+1, y1+1, x2+1, y2+1) && squares[x1][y1].playerIs(p)) {
			//System.out.println("move absolute");
			return movePawnAbsolute(x1, y1, x2, y2);
		} else return false;
	}
	
	/**Renvoie si oui ou non une case est voisine avec une autre*/
	public boolean squaresNeighbors(int x1, int y1, int x2, int y2) {
		/*System.out.println(x1 + " " + y1 + " | " + x2 + " " + y2);
		System.out.println(( y1 == y2 && Math.abs(x2 - x1) == 1 && y1 % 2 == 0 && y2 % 2 == 0 ));
		System.out.println( x1 == x2 && (Math.abs( ( y2 % (nbSides * 2) ) - ( y1 % (nbSides * 2) ) ) == 1) );
		System.out.println(Math.abs( ( y2 % (nbSides * 2) ) - ( y1 % (nbSides * 2) ) ));*/
		return ( y1 == y2 && Math.abs(x2 - x1) == 1 && y1 % 2 == 0 && y2 % 2 == 0 ) || ( x1 == x2 && (Math.abs( ( y2 % (nbSides * 2) ) - ( y1 % (nbSides * 2) ) ) != 0) );
	}
	
	/**Retourne si oui ou non le joueur peut se d√©placer aux cases voisines*/
	public boolean IcanMove(int x, int y){
		if(squaresNeighbors(x, y, x-1, y) || squaresNeighbors(x, y, x+1, y) || squaresNeighbors(x, y, x, y-1) || squaresNeighbors(x,y, x, y+1)){
			return true;
		}
		return false;
	}

	/*public boolean squaresAligned(int y1, int y2, int y3) {
		
	}*/
	
	/**Affichage Textuel
	* @throws Exception */
	public String toStringIntelligent() throws Exception {
		if(Utils.isBetween(nbSides,3,4) && nbShapes == 3) return advancedDisplay();
		else return primaryDisplay();
	}
	
	/**Affichage textuel des plateaux de forme carrÈ et triangle
	* @return le plateau gÈnÈrÈ par le nombre de cÙtÈ et les fichiers
	* @throws IOException */
	public String advancedDisplay() throws IOException {
		StringBuilder display = new StringBuilder();
		String shapeName;
		if(nbSides == 3) shapeName = "triangle";
		else shapeName = "carre";
		Path file = Paths.get(Utils.dir + "files/" + shapeName + ".txt");
		ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(file);
		Path fileCoords = Paths.get(Utils.dir + "files/" + shapeName + "_coords.txt");
		ArrayList<String> linesCoords = (ArrayList<String>) Files.readAllLines(fileCoords);
		int cpt = 0;
		for(String line_ : lines) {
			String line = line_;
			for(int i=0;i<line.length();i++) {
				char car = line.charAt(i);
				if(Character.isLetter(car)) {
					int alphabetIndex = ((int) (car - 'A'));
					int x = (int) Math.ceil(alphabetIndex/(nbSides*2));
					int y = alphabetIndex % (nbSides*2);
					line = line.replace("" + car, "" + squares[x][y].getPlayerNumber());
				}
			}
			display.append(line + "\t" + linesCoords.get(cpt) + "\n");
			cpt++;
		}
		return display.toString();
	}
	
	/**Affichage des plateaux sous forme de tableau
	* @return le plateau gÈnÈrÈ par le nombre de cÙtÈ et les fichiers */
	public String primaryDisplay() {
		StringBuilder strBuilder = new StringBuilder();
		for(int x=0;x<squares.length;x++) {
			for(int y=0;y<squares[x].length;y++) {
				strBuilder.append(squares[x][y] + "\n");
			}
		}
		return strBuilder.toString();
	}
	
}
