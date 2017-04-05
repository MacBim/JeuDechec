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
		
//		this.cases[0][0] = new Case(new Position(0, 0));
//		this.cases[7][0] = new Case( new Position(7, 0));
//
//		this.cases[1][0] = new Case(new Position(1, 0));
//		this.cases[6][0] = new Case(new Position(6, 0));
//
//		this.cases[2][0] = new Fou(Piece.NOIR, this, new Position(2, 0));
//		this.cases[5][0] = new Fou(Piece.NOIR, this, new Position(5, 0));
//
//		this.cases[3][0] = new Reine(Piece.NOIR, this, new Position(3, 0));
//		this.cases[4][0] = new Roi(Piece.NOIR, this, new Position(4, 0));
//
//		for (int x = 0; x < 8; x++) {
//			this.pieces[x][1] = new Pion(Piece.NOIR, this, new Position(x, 1));
//			this.pieces[x][6] = new Pion(Piece.BLANC, this, new Position(x, 6));
//		}
//
//		this.pieces[0][7] = new Tour(Piece.BLANC, this, new Position(0, 7));
//		this.pieces[7][7] = new Tour(Piece.BLANC, this, new Position(7, 7));
//
//		this.pieces[1][7] = new Cavalier(Piece.BLANC, this, new Position(1, 7));
//		this.pieces[6][7] = new Cavalier(Piece.BLANC, this, new Position(6, 7));
//
//		this.pieces[2][7] = new Fou(Piece.BLANC, this, new Position(2, 7));
//		this.pieces[5][7] = new Fou(Piece.BLANC, this, new Position(5, 7));
//
//		this.pieces[3][7] = new Reine(Piece.BLANC, this, new Position(3, 7));
//		this.pieces[4][7] = new Roi(Piece.BLANC, this, new Position(4, 7));
	}

}