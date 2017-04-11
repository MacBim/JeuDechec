package modele;

import java.util.ArrayList;

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
		
	}

	public void setPlayerTwoAsIA() {
		this.blackPlayer = new JoueurIA(this, false);
	}

	public boolean verifCheckMate() {
		ArrayList<Piece> activePlayerPieces;

		// On recupere toutes les pieces.
		if (whitesTurn) { // => c'est le tour des blancs
			activePlayerPieces = this.plateau.getWhitePieces();
		} else { // => c'est le tour des noirs
			activePlayerPieces = this.plateau.getBlackPieces();
		}

		// On recupere le roi du joueur dont c'est le tour
		Piece king = null;
		for (Piece piece : activePlayerPieces) {
			if (piece instanceof Roi) {
				king = piece;
				break;
			}
		}
		
		if (isCurrentPlayersKingInCheckMate(king)){
			switchSide();
			return true;
		}

		return false;
	}

	/**
	 * @param king
	 *            The king of the current player
	 * @return The piece responsible of the check, null otherwise
	 */
	public boolean isCurrentPlayerKingInCheck(Piece king) {	
		if(king.getAvailablePositions().size() == 0)
			return true;
		return false;
	}
	
	public boolean isCurrentPlayersKingInCheckMate(Piece king){
		// le roi est en échec donc il ne peut plus bouger
		if(!isCurrentPlayerKingInCheck(king)){
			System.out.println("le roi peut bouger");
			return false;
		}
		
		ArrayList<Piece> playersPieces = new ArrayList<>();
		ArrayList<Piece> oponentPieces = new ArrayList<>();
		
		
		if(!this.whitesTurn) {
			oponentPieces = this.plateau.getWhitePieces();
			playersPieces = this.plateau.getBlackPieces();
		}
		else {
			oponentPieces = this.plateau.getBlackPieces();
			playersPieces = this.plateau.getWhitePieces();
		}
		/* on recherche quelles pièces parmis celles de l'adversaire peuvent manger le Roi du joueur courant */
		ArrayList<Piece> predators = whoCanEatTarget(oponentPieces,king);
		System.err.println("predator.size() " + predators.size());
		if(predators.size() == 0) //si personne ne peut manger le roi, il n'est pas en echec et mat
			return false;
		
		/* Maintenant, parmis les pièces du joueur, les quelles peuvent manger le prédateur ? */	
		ArrayList<Piece> predatorsEliminated = new ArrayList<>();
		
		for(Piece predator : predators){ // pour chaque prédateur...
			for(Piece p : playersPieces){ // on regarde pour toutes les pièces du joueur si...
				for(Position pos : p.getAvailablePositions()){ // chaques positions disponnibles des pièces...
					// ... qui éliminent (mange) un prédateur (pas déja éliminé)
					if(pos.equals(predator.position) && !predatorsEliminated.contains(predator)){ // 
						predatorsEliminated.add(predator);
					}
				}
			}
		}
		
		if(predators.size() != predatorsEliminated.size()) // si au moin UN predateur est encore envie, echec et mat
			return true;
		
		
		return false;
	}

	public ArrayList<Piece> whoCanEatTarget(ArrayList<Piece> pieces,Piece target) {
		// On vÃ©rifie si une piece adverse peut allez sur la case de la cible
		ArrayList<Piece> ret = new ArrayList<>();
		for (Piece piece : pieces) {
			for (Position pos : piece.getAvailablePositions()) {
				if (pos.equals(target.position)) {
					ret.add(piece);
				}
			}
		}
		return ret; // la liste des prédateur de target
	}

	public ArrayList<Piece> getAllPieces() {
		ArrayList<Piece> ret = new ArrayList<>();

		ArrayList<Piece> blackPieces = this.plateau.getBlackPieces();
		ArrayList<Piece> whitePieces = this.plateau.getWhitePieces();

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
		// On doit verifier si y a echec et math
		if (verifCheckMate()) {
			if (this.whitesTurn)
				this.gameStatus = Partie.WHITE_IN_CHECKMATE;
			else
				this.gameStatus = Partie.BLACK_IN_CHECKMATE;
			return;
		}

		ArrayList<Piece> whitePieces = this.plateau.getWhitePieces();
		ArrayList<Piece> blackPieces = this.plateau.getBlackPieces();
		if (whitePieces.size() == 0) // si il n'y a plus de piece blanche
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