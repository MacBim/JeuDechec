package modele;

import java.util.ArrayList;

public class Partie extends Observable {

	public Plateau plateau;
	public boolean whitesTurn;
	private Piece lastPieceClicked = null;
	
	public Partie(){
		this.plateau = new Plateau();
		this.observers = new ArrayList<ObserveurEchec>();
	}

	public void start() {
		
	}
	
	public Piece getLastPieceClicked(){
		return this.lastPieceClicked;
	}

	public void remplirPlateau(){
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
	public void onClickPiece(int p_x, int p_y){
		Case c = this.plateau.cases[p_x][p_y];
		if(!c.isLit){
			for(int x = 0; x < 8; x++){
				for(int y = 0; y < 8; y++){
					this.plateau.cases[x][y].removeHighlight();
				}
			}
			notifyAllObservers();
			
			if(c.piece == null)
				return;
			this.lastPieceClicked = c.piece;
			if(this.whitesTurn == c.piece.couleur){
				Position[] pos = c.piece.getAvailablePositions();
				System.out.println(pos.length);
				for(int i = 0; i < pos.length; i++){		
					if(pos[i] != null){
						int x = pos[i].x;
						int y = pos[i].y;
						System.out.println(x + " " + y);
						this.plateau.cases[x][y].highlightCase();
					}
				}
			}
		} else {
			if(this.lastPieceClicked instanceof Pion){
				((Pion) this.lastPieceClicked).premierDeplacement = false;
			}
			
			// on recupere la position de la derni�re pi�ce cliqu�e
			int xp = this.lastPieceClicked.position.x;
			int yp = this.lastPieceClicked.position.y;
			
			// on l'enl�ve de sont ancienne position
			this.plateau.cases[xp][yp].removePiece();
			
			//on met dans la case destination la pi�ce pr�cedemment cliqu�e
			c.piece = this.lastPieceClicked;
			// on modifie �galement sa position pour la rendre consiente du mouvement
			c.piece.position = c.position;
			
			this.whitesTurn = !this.whitesTurn; // on change de tour
			
		}
		notifyAllObservers();
	}
	
	
}