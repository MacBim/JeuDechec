package modele;

import java.util.ArrayList;

import javafx.geometry.Pos;

public class Partie extends Observable {

	public Plateau plateau;
	public boolean whitesTurn;
	
	public Partie(){
		this.plateau = new Plateau();
		this.observers = new ArrayList<ObserveurEchec>();
	}

	public void start() {
		
	}

	public void remplirPlateau(){
		System.out.println("remplir plat");
		this.plateau.cases[0][0].addPiece(new Tour(Piece.NOIR,this.plateau,new Position(0, 0)));
		this.plateau.cases[7][0].addPiece(new Tour(Piece.NOIR,this.plateau,new Position(7, 0)));

		this.plateau.cases[1][0].addPiece(new Cavalier(Piece.NOIR,this.plateau,new Position(1, 0)));
		this.plateau.cases[6][0].addPiece(new Cavalier(Piece.NOIR,this.plateau,new Position(6, 0)));

		this.plateau.cases[2][0].addPiece(new Fou(Piece.NOIR,this.plateau,new Position(2, 0)));
		this.plateau.cases[5][0].addPiece(new Fou(Piece.NOIR,this.plateau,new Position(5, 0)));

		this.plateau.cases[3][0].addPiece(new Reine(Piece.NOIR,this.plateau,new Position(3, 0)));
		this.plateau.cases[4][0].addPiece(new Roi(Piece.NOIR,this.plateau,new Position(4, 0)));

		for (int x = 0; x < 8; x++) {
			this.plateau.cases[x][1].addPiece(new Pion(Piece.NOIR,this.plateau,new Position(x, 1)));
			this.plateau.cases[x][6].addPiece(new Pion(Piece.BLANC,this.plateau,new Position(x, 6)));
		}
		
		this.plateau.cases[0][7].addPiece(new Tour(Piece.BLANC,this.plateau,new Position(0, 7)));
		this.plateau.cases[7][7].addPiece(new Tour(Piece.BLANC,this.plateau,new Position(7, 7)));

		this.plateau.cases[1][7].addPiece(new Cavalier(Piece.BLANC,this.plateau,new Position(1, 7)));
		this.plateau.cases[6][7].addPiece(new Cavalier(Piece.BLANC,this.plateau,new Position(6, 7)));

		this.plateau.cases[2][7].addPiece(new Fou(Piece.BLANC,this.plateau,new Position(2, 7)));
		this.plateau.cases[5][7].addPiece(new Fou(Piece.BLANC,this.plateau,new Position(5, 7)));

		this.plateau.cases[3][7].addPiece(new Reine(Piece.BLANC,this.plateau,new Position(3, 7)));
		this.plateau.cases[4][7].addPiece(new Roi(Piece.BLANC,this.plateau,new Position(4, 7)));
		
		notifyAllObservers();
	}
	
	public Boolean coupValide(Coup coup) {
		return null;
	}

	public Piece getPieceDep() {
		return null;
	}

	public void creerPlateau() {
		// TODO Auto-generated method stub
		
	}
	public void onClick(int p_x, int p_y){
		Case c = this.plateau.cases[p_x][p_y];
		if(c.piece == null)
			return;
		if(this.whitesTurn == c.piece.couleur){
			Position[] pos = c.piece.getAvailablePositions();
			for(Position p : pos){
				int x = p.x;
				int y = p.y;
				this.plateau.cases[x][y].highlightCase();
			}
		}
		notifyAllObservers();
	}
	
	
}