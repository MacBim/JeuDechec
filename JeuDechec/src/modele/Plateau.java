package modele;

import java.util.ArrayList;

public class Plateau {

	public Case[][] cases;

	public Plateau() {
		this.cases = new Case[8][8];
		// haut

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				this.cases[x][y] = new Case(new Position(x, y));
			}
		}
	}

	protected Plateau clone() {
		Plateau ret = new Plateau();
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (this.cases[x][y].piece == null)
					ret.cases[x][y] = new Case(new Position(x, y));
				else {
					Piece tmp = this.cases[x][y].piece;
					switch (tmp.getClass().getSimpleName()) {
					case "Pion":
						ret.cases[x][y].addPiece(new Pion(tmp.couleur, this, new Position(x, y)));
						break;
					case "Cavalier":
						ret.cases[x][y].addPiece(new Cavalier(tmp.couleur, this, new Position(x, y)));
						break;
					case "Roi":
						ret.cases[x][y].addPiece(new Roi(tmp.couleur, this, new Position(x, y)));
						break;
					case "Reine":
						ret.cases[x][y].addPiece(new Reine(tmp.couleur, this, new Position(x, y)));
						break;
					case "Tour":
						ret.cases[x][y].addPiece(new Tour(tmp.couleur, this, new Position(x, y)));
						break;
					case "Fou":
						ret.cases[x][y].addPiece(new Fou(tmp.couleur, this, new Position(x, y)));
						break;
					}
				}
			}
		}
		return ret;
	}
	
	public ArrayList<Piece> getWhitePieces(){
		ArrayList<Piece> ret = new ArrayList<>();
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (this.cases[x][y].piece != null) {
					Piece currentPiece = this.cases[x][y].piece;
					if (currentPiece.couleur == Piece.BLANC)
						ret.add(currentPiece);
				}
			}
		}
		return ret;
	}
	
	public ArrayList<Piece> getBlackPieces(){
		ArrayList<Piece> ret = new ArrayList<>();
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (this.cases[x][y].piece != null) {
					Piece currentPiece = this.cases[x][y].piece;
					if (currentPiece.couleur == Piece.NOIR)
						ret.add(currentPiece);
				}
			}
		}
		return ret;
	}

}