package horizons_mmet_c1_1.horizons_mmet_c1_1;

public class Player {
	
	private String name;
	
	public Player(String name) {
		this.name = name;
	}
	
	public Player(){
		this("J1");
	}
	
	public String toString() {
		return name;
	}
}
