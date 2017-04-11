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

	public boolean verifCheckMate(boolean whitesTurn){
		/* Verification de l'echec et math : 
		 * 		On est en echec et math si pour tous les déplacements possibles
		 * 		aucun empeche que le roi ne se fasse manger au tour suivant.
		 * 		
		 * 		Pour chaqes déplacements (A) possible, on simule un plateau identique
		 * 		mais on fait comme si le coup (A) avait été joué, puis on regarde pour chaques pieces,
		 * 		pour chaqes coups, si une piece peut se rendre à la position du rois, alors le coup (A) 
		 * 		n'est pas valide.
		 * 		Si aucun déplacement n'est valide, on est en situation déchec et math. 
		*/
		
		List<Piece> activePlayerPieces;
		List<Piece> otherPlayerPieces;
		
		// On récupere toutes les pieces.		
		if (whitesTurn){ 	// => c'est le tour des blancs
			activePlayerPieces = getWhitePieces();
			otherPlayerPieces = getBlackPieces();
		} else {			// => c'est le tour des noirs
			activePlayerPieces = getBlackPieces();
			otherPlayerPieces = getWhitePieces();
		}
		
		// On récupere le roi du joueur dont c'est le tour
		Piece king = null;
		for (Piece piece : activePlayerPieces){
			if(piece.getClass().getName() == "modele.Roi"){
				king = piece;
			}
		}
		
		Plateau plateauTmp = null;
		try {
			plateauTmp = (Plateau) this.plateau.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(roiEstEnMat( plateauTmp, activePlayerPieces, otherPlayerPieces, king)){
			return true;
		}
		
		return false;
	}
	
	public boolean roiEstEnMat(Plateau plat, List<Piece> ActivesPieces,List<Piece> othersPieces, Piece kingToTest){
		boolean roiEnEchec ;
		for (Piece pieceJA : ActivesPieces){
			for (Position posJA : pieceJA.getAvailablePositions()){
				plat = this.plateau;
				plat.cases[posJA.x][posJA.y].addPiece(pieceJA);
				Piece kingTmp = new Roi(kingToTest.couleur, plat, kingToTest.position);
				if(!canBeEaten(plat, othersPieces, kingToTest)){
					return false;
				}
			
			}
		}
		return true;
	}
	
	public boolean canBeEaten(Plateau plat, List<Piece> piecesToTest, Piece cible){
		// On vérifie si une piece adverse peut allez sur la case de la cible
		for (Piece piece : piecesToTest){
			for (Position pos : piece.getAvailablePositions()){
				if (pos.x == cible.position.x && pos.y == cible.position.y){
					return true;
				}
			}
		}
		return false;
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
	
	public Joueur getBlackPlayer(){
		return this.blackPlayer;
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
	 * retourne 0 si la partie n'est pas termin�e -1 si c'est les noirs qui ont
	 * gagn� 1 si c'est les blancs qui ont gagn�
	 */
	public void updateGameStatus() {
		ArrayList<Piece> whitePieces = (ArrayList<Piece>) getWhitePieces();
		ArrayList<Piece> blackPieces = (ArrayList<Piece>) getBlackPieces();
		
		// On doit verifier si y a echec et math
		
		if(verifCheckMate(whitesTurn)){
			if(whitesTurn){
				this.gameStatus = -1;
			} else  this.gameStatus = 1;
		}
		
		if (whitePieces.size() == 0) // si il n'y a plus de pi�ce blanche
			this.gameStatus = -1;
		if (blackPieces.size() == 0)
			this.gameStatus = 1;
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