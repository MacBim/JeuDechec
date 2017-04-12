package modele;

import java.util.ArrayList;
import java.util.Random;

public class JoueurIA extends Joueur {

	public JoueurIA(Partie partie, boolean couleur) {
		super(partie, couleur);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void play(int x, int y) {
		// TODO Auto-generated method stub

		ArrayList<Piece> pieces = new ArrayList<>();
		for (int xtmp = 0; xtmp < 8; xtmp++) {
			for (int ytmp = 0; ytmp < 8; ytmp++) {
				this.partie.getCaseAt(xtmp, ytmp).removeHighlight();
			}
		}
		this.partie.notifyAllObservers();

		// on récupère les pièces de la meme couleur que le joueurIA
		if (this.couleur == true) // aka blanc
			pieces = this.partie.plateau.getWhitePieces();
		else
			pieces = this.partie.plateau.getBlackPieces();

		Random rand = new Random();
		int index = rand.nextInt(pieces.size());
		Piece currentPiece = pieces.get(index);
		ArrayList<Piece> pieceVisited = new ArrayList<>();
		while (currentPiece.getAvailablePositions().size() == 0) {
			if (!pieceVisited.contains(currentPiece))
				pieceVisited.add(currentPiece);
			if (pieceVisited.size() == pieces.size()) { // on a parcouru toute
														// les pièces sans en
														// trouver une bonne
				// on est donc en échec
				if (this.couleur == true) // blanc
					this.partie.setWhiteInCheck();
				else
					this.partie.setBlackInCheck();
				return;
			}
			index = rand.nextInt(pieces.size());
			currentPiece = pieces.get(index);
		}

		this.partie.setlastPieceClicked(currentPiece);
		ArrayList<Position> pos = currentPiece.getAvailablePositions();
		for (Position p : pos) {
			if (p != null) {
				int xtmp = p.x;
				int ytmp = p.y;
				this.partie.getCaseAt(xtmp, ytmp).highlightCase();
			}
		}
		// on tire une positions aléatoire parmis les positions possibles;

		index = rand.nextInt(pos.size());
		Position choosedPos = pos.get(index);

		int oldX = this.partie.getLastPieceClicked().position.x;
		int oldY = this.partie.getLastPieceClicked().position.y;
		this.partie.getCaseAt(oldX, oldY).removePiece();

		int newX = choosedPos.x;
		int newY = choosedPos.y;
		currentPiece.position = choosedPos;
		this.partie.getCaseAt(newX, newY).addPiece(currentPiece);

		for (int xtmp = 0; xtmp < 8; xtmp++) {
			for (int ytmp = 0; ytmp < 8; ytmp++) {
				this.partie.getCaseAt(xtmp, ytmp).removeHighlight();
			}
		}
		
		this.partie.switchSide();
		this.partie.updateGameStatus();
		this.partie.notifyAllObservers();
	}

}
