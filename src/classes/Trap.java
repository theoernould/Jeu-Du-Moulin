package classes;

public class Trap {
	private Player p;
	private int roundLeft = 3;
	private final int X;
	private final int Y;
	
	Trap(int x, int y, Player p) {
		this.p = p;
		this.X = x;
		this.Y = y;
	}
	
	public void roundPass() {
		roundLeft--;
	}
	
	public boolean isTrapDead() {
		return roundLeft <= 0;
	}
	
	public int getX() {
		return X;
	}
	
	public int getY() {
		return Y;
	}
	
	public Player getPlayer() {
		return p;
	}
}
