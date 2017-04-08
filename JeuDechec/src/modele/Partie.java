package modele;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jeanb
 *
 */
public class Partie extends Observable {

	public Plateau plateau;
	public boolean whitesTurn = true;
	private Piece lastPieceClicked = null;
	private int gameStatus;

	public Partie() {
		this.plateau = new Plateau();
		this.observers = new ArrayList<ObserveurEchec>();
	}

	public void start() {

	}

	private List<Piece> getWhitePieces() {
		ArrayList<Piece> ret = new ArrayList<>();
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (this.plateau.cases[x][y].piece != null) {
					Piece currentPiece = this.plateau.cases[x][y].piece;
					if (currentPiece.couleur == Piece.BLANC)
						ret.add(currentPiece);
				}
			}
		}
		return ret;
	}

	private List<Piece> getBlackPieces() {
		ArrayList<Piece> ret = new ArrayList<>();
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (this.plateau.cases[x][y].piece != null) {
					Piece currentPiece = this.plateau.cases[x][y].piece;
					if (currentPiece.couleur == Piece.NOIR)
						ret.add(currentPiece);
				}
			}
		}
		return ret;
	}

	public Piece getLastPieceClicked() {
		return this.lastPieceClicked;
	}

	public void remplirPlateau() {
		this.plateau.cases[0][0].addPiece(new Tour(Piece.NOIR, this.plateau, new Position(0, 0)));
		this.plateau.cases[7][0].addPiece(new Tour(Piece.NOIR, this.plateau, new Position(7, 0)));

		this.plateau.cases[1][0].addPiece(new Cavalier(Piece.NOIR, this.plateau, new Position(1, 0)));
		this.plateau.cases[6][0].addPiece(new Cavalier(Piece.NOIR, this.plateau, new Position(6, 0)));

		this.plateau.cases[2][0].addPiece(new Fou(Piece.NOIR, this.plateau, new Position(2, 0)));
		this.plateau.cases[5][0].addPiece(new Fou(Piece.NOIR, this.plateau, new Position(5, 0)));

		this.plateau.cases[3][0].addPiece(new Reine(Piece.NOIR, this.plateau, new Position(3, 0)));
		this.plateau.cases[4][0].addPiece(new Roi(Piece.NOIR, this.plateau, new Position(4, 0)));

		for (int x = 0; x < 8; x++) {
			this.plateau.cases[x][1].addPiece(new Pion(Piece.NOIR, this.plateau, new Position(x, 1)));
			this.plateau.cases[x][6].addPiece(new Pion(Piece.BLANC, this.plateau, new Position(x, 6)));
		}

		this.plateau.cases[0][7].addPiece(new Tour(Piece.BLANC, this.plateau, new Position(0, 7)));
		this.plateau.cases[7][7].addPiece(new Tour(Piece.BLANC, this.plateau, new Position(7, 7)));

		this.plateau.cases[1][7].addPiece(new Cavalier(Piece.BLANC, this.plateau, new Position(1, 7)));
		this.plateau.cases[6][7].addPiece(new Cavalier(Piece.BLANC, this.plateau, new Position(6, 7)));

		this.plateau.cases[2][7].addPiece(new Fou(Piece.BLANC, this.plateau, new Position(2, 7)));
		this.plateau.cases[5][7].addPiece(new Fou(Piece.BLANC, this.plateau, new Position(5, 7)));

		this.plateau.cases[3][7].addPiece(new Reine(Piece.BLANC, this.plateau, new Position(3, 7)));
		this.plateau.cases[4][7].addPiece(new Roi(Piece.BLANC, this.plateau, new Position(4, 7)));

		notifyAllObservers();
	}

	/*
	 * retourne 0 si la partie n'est pas terminée -1 si c'est les noirs qui ont
	 * gagné 1 si c'est les blancs qui ont gagné
	 */
	private int updateGameStatus() {
		ArrayList<Piece> whitePieces = (ArrayList<Piece>) getWhitePieces();
		ArrayList<Piece> blackPieces = (ArrayList<Piece>) getBlackPieces();
		if (whitePieces.size() == 0) // si il n'y a plus de pièce blanche
			return 1;
		if (blackPieces.size() == 0)
			return -1;
		return 0;
	}

	public void onClickPiece(int p_x, int p_y) {

		this.gameStatus = updateGameStatus();

		Case c = this.plateau.cases[p_x][p_y];
		if (!c.isLit) {
			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {
					this.plateau.cases[x][y].removeHighlight();
				}
			}
			notifyAllObservers();

			if (c.piece == null)
				return;
			this.lastPieceClicked = c.piece;
			if (this.whitesTurn == c.piece.couleur) {
				Position[] pos = c.piece.getAvailablePositions();
				System.out.println(pos.length);
				for (int i = 0; i < pos.length; i++) {
					if (pos[i] != null) {
						int x = pos[i].x;
						int y = pos[i].y;
						System.out.println(x + " " + y);
						this.plateau.cases[x][y].highlightCase();
					}
				}
			}
		} else {
			if (this.lastPieceClicked instanceof Pion) {
				((Pion) this.lastPieceClicked).premierDeplacement = false;
			}

			Position[] pos = this.lastPieceClicked.getAvailablePositions();
			for (Position p : pos) {
				if (p != null) {
					int xtmp = p.x;
					int ytmp = p.y;
					this.plateau.cases[xtmp][ytmp].removeHighlight();
				}
			}

			// on recupere la position de la derniï¿½re piï¿½ce cliquï¿½e
			int xp = this.lastPieceClicked.position.x;
			int yp = this.lastPieceClicked.position.y;

			// on l'enlï¿½ve de sont ancienne position
			this.plateau.cases[xp][yp].removePiece();

			// on met dans la case destination la piï¿½ce prï¿½cedemment
			// cliquï¿½e
			c.piece = this.lastPieceClicked;
			// on modifie ï¿½galement sa position pour la rendre consiente du
			// mouvement
			c.piece.position = c.position;

			this.whitesTurn = !this.whitesTurn; // on change de tour

		}
		notifyAllObservers();
	}

	public int getGameStatus() {
		return this.gameStatus;
	}

}