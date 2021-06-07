package classes;

public class Blocage extends Square {
	private int x2;
	private int y2;
	private static final int DUREE = 3;
	
	Blocage(int x1, int y1, int x2, int y2) {
		super(x1,y1);
		this.x2 = x2;
		this.y2 = y2;
	}
	public void tpTrap(Square S1){
		int x2= (int) (Math.random()*3);
		int y2= (int) (Math.random()*3);
		Square S2= new Square(x2,y2);
		while((x2==S1.X && y2==S1.Y) || S2.getPlayerNumber()!=0){
			x2= (int) (Math.random()*3);
			y2= (int) (Math.random()*3);
			S2= new Square(x2,y2);
		}
		S1.moveTo(S2);
	}
}
