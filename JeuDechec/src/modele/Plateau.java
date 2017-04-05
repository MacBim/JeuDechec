package modele;

public class Plateau {

	public Case[][] cases;

	public Plateau() {
		this.cases = new Case[8][8];
		// haut

		for(int x = 0; x < 8; x++){
			for(int y = 0; y < 8; y++){
				this.cases[x][y] = new Case(new Position(x, y));
			}
		}
	}

}