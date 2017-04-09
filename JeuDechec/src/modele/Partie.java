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

	private Joueur blackPlayer;
	private Joueur whitePlayer;

	private Piece lastPieceClicked = null;
	private int gameStatus;

	public Partie() {
		this.plateau = new Plateau();
		this.observers = new ArrayList<ObserveurEchec>();
		this.blackPlayer = new JoueurHumain(this, false);
		this.whitePlayer = new JoueurHumain(this, true);
	}

	public void start() {

	}

	public List<Piece> getWhitePieces() {
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

	public List<Piece> getBlackPieces() {
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

	public void setPlayerTwoAsIA() {
		this.blackPlayer = new JoueurIA(this, false);
	}

	public List<Piece> getAllPieces() {
		ArrayList<Piece> ret = new ArrayList<>();

		ArrayList<Piece> blackPieces = (ArrayList<Piece>) getBlackPieces();
		ArrayList<Piece> whitePieces = (ArrayList<Piece>) getWhitePieces();

		if (blackPieces.size() != 0)
			ret.addAll(blackPieces);
		if (whitePieces.size() != 0)
			ret.addAll(whitePieces);

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
	public void updateGameStatus() {
		ArrayList<Piece> whitePieces = (ArrayList<Piece>) getWhitePieces();
		ArrayList<Piece> blackPieces = (ArrayList<Piece>) getBlackPieces();
		if (whitePieces.size() == 0) // si il n'y a plus de pièce blanche
			this.gameStatus = 1;
		if (blackPieces.size() == 0)
			this.gameStatus = -1;
		this.gameStatus = 0;
	}

	public void onClickPiece(int p_x, int p_y) {
		if (this.whitesTurn) {
			this.whitePlayer.play(p_x, p_y);
		} else {
			this.blackPlayer.play(p_x, p_y);
		}
	}

	public int getGameStatus() {
		return this.gameStatus;
	}

	public void setlastPieceClicked(Piece piece) {
		// TODO Auto-generated method stub
		this.lastPieceClicked = piece;
	}

	public Case getCaseAt(int x, int y) {
		return this.plateau.cases[x][y];
	}

	public void setWhiteInCheck() {
		this.gameStatus = 1;
	}

	public void setBlackInCheck() {
		this.gameStatus = 1;
	}

	public void switchSide() {
		this.whitesTurn = !this.whitesTurn;
	}

}