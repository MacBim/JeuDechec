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

	public final static int WHITE_IN_CHECK = 1;
	public final static int BLACK_IN_CHECK = -1;

	public final static int WHITE_IN_CHECKMATE = 2;
	public final static int BLACK_IN_CHECKMATE = -2;

	public final static int NOBODY_IN_CHECK = 0;

	public Partie() {
		this.plateau = new Plateau();
		this.observers = new ArrayList<ObserveurEchec>();
		this.blackPlayer = new JoueurHumain(this, false);
		this.whitePlayer = new JoueurHumain(this, true);
	}
	
	public void onClickPiece(int p_x, int p_y) {
		if (this.whitesTurn) {
			this.whitePlayer.play(p_x, p_y);
		} else {
			this.blackPlayer.play(p_x, p_y);
		}
		updateGameStatus();
		switchSide(); // on change de tour
	}

	public ArrayList<Piece> getWhitePieces() {
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

	public ArrayList<Piece> getBlackPieces() {
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

	public boolean verifCheckMate(boolean whitesTurn) {
		/*
		 * Verification de l'echec et math : On est en echec et math si pour
		 * tous les dÃ©placements possibles aucun empeche que le roi ne se fasse
		 * manger au tour suivant.
		 * 
		 * Pour chaqes deplacements (A) possible, on simule un plateau
		 * identique mais on fait comme si le coup (A) avait ete joue, puis
		 * on regarde pour chaques pieces, pour chaqes coups, si une piece peut
		 * se rendre Ã  la position du rois, alors le coup (A) n'est pas valide.
		 * Si aucun dÃ©placement n'est valide, on est en situation dÃ©chec et
		 * math.
		 */

		ArrayList<Piece> activePlayerPieces;
		ArrayList<Piece> oponentPlayerPieces;

		// On recupere toutes les pieces.
		if (whitesTurn) { // => c'est le tour des blancs
			activePlayerPieces = getWhitePieces();
			oponentPlayerPieces = getBlackPieces();
		} else { // => c'est le tour des noirs
			activePlayerPieces = getBlackPieces();
			oponentPlayerPieces = getWhitePieces();
		}

		// On recupere le roi du joueur dont c'est le tour
		Piece king = null;
		for (Piece piece : activePlayerPieces) {
			if (piece instanceof Roi) {
				king = piece;
			}
		}

		Plateau tmpBoard = null;
		tmpBoard = this.plateau.clone();

		Piece checkPiece = isKingInCheck(tmpBoard, activePlayerPieces, oponentPlayerPieces, king);
		if (checkPiece != null) { // dans le cas ou le roi ne peut plus rien
									// faire
			// on regarde si la piece responsable peut etre mangée
			Piece iCanEatIt_Piece = whoCanEatTarget(tmpBoard, oponentPlayerPieces, checkPiece);
			if (iCanEatIt_Piece == null) // si personne ne peut manger la pièce
									// responsable
				return true;
		}
		return false;
	}

	/**
	 * @param plat
	 *            The board to simulate the moves
	 * @param playerPieces
	 *            The player's pieces
	 * @param oponentPieces
	 *            The oponent's pieces
	 * @param kingToTest
	 *            The king of the current player
	 * @return The piece responsible of the check, null otherwise
	 */
	public Piece isKingInCheck(Plateau plat, ArrayList<Piece> playerPieces, ArrayList<Piece> oponentPieces,
			Piece kingToTest) {
		for (Piece pieceJA : playerPieces) {
			for (Position posJA : pieceJA.getAvailablePositions()) {
				plat.cases[posJA.x][posJA.y].addPiece(pieceJA); // on simule le
																// déplacement
				Piece ret = whoCanEatTarget(plat, oponentPieces, kingToTest);
				if (ret != null) {
					return ret;
				}
			}
		}
		return null;
	}

	public Piece whoCanEatTarget(Plateau plat, List<Piece> piecesToTest, Piece target) {
		// On vÃ©rifie si une piece adverse peut allez sur la case de la cible
		for (Piece piece : piecesToTest) {
			for (Position pos : piece.getAvailablePositions()) {
				if (pos.equals(target.position)) {
					return piece;
				}
			}
		}
		return null;
	}

	public ArrayList<Piece> getAllPieces() {
		ArrayList<Piece> ret = new ArrayList<>();

		ArrayList<Piece> blackPieces = (ArrayList<Piece>) getBlackPieces();
		ArrayList<Piece> whitePieces = (ArrayList<Piece>) getWhitePieces();

		if (blackPieces.size() != 0)
			ret.addAll(blackPieces);
		if (whitePieces.size() != 0)
			ret.addAll(whitePieces);

		return ret;
	}

	public Joueur getBlackPlayer() {
		return this.blackPlayer;
	}

	public Piece getLastPieceClicked() {
		return this.lastPieceClicked;
	}

	public void fillCheckBoard() {
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
	 * retourne 0 si la partie n'est pas terminï¿½e -1 si c'est les noirs qui
	 * ont gagnï¿½ 1 si c'est les blancs qui ont gagnï¿½
	 */
	public void updateGameStatus() {
		ArrayList<Piece> whitePieces = (ArrayList<Piece>) getWhitePieces();
		ArrayList<Piece> blackPieces = (ArrayList<Piece>) getBlackPieces();

		// On doit verifier si y a echec et math
		if (verifCheckMate(whitesTurn)) {
			if (whitesTurn)
				this.gameStatus = Partie.WHITE_IN_CHECKMATE;
			else
				this.gameStatus = Partie.BLACK_IN_CHECKMATE;
			return;
		}

		if (whitePieces.size() == 0) // si il n'y a plus de piï¿½ce blanche
			this.gameStatus = Partie.BLACK_IN_CHECK;
		else if (blackPieces.size() == 0)
			this.gameStatus = Partie.WHITE_IN_CHECK;
		else
			this.gameStatus = Partie.NOBODY_IN_CHECK;
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