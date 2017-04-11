package modele;

import java.util.ArrayList;

public class JoueurHumain extends Joueur {

	public JoueurHumain(Partie partie, boolean couleur) {
		super(partie, couleur);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void play(int p_x, int p_y) {
		// TODO Auto-generated method stub
		Case c = this.partie.getCaseAt(p_x, p_y);

		Piece lastPieceClicked = this.partie.getLastPieceClicked();

		if (!c.isLit) { // premier clic
			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {
					this.partie.getCaseAt(x, y).removeHighlight();
				}
			}
			this.partie.notifyAllObservers();

			if (c.piece == null)
				return;
			this.partie.setlastPieceClicked(c.piece);
			if (this.couleur == c.piece.couleur) {
				ArrayList<Position> pos = c.piece.getAvailablePositions();
				System.out.println(pos.size());
				for (Position p : pos) {
					if (p != null) {
						int x = p.x;
						int y = p.y;
						this.partie.getCaseAt(x, y).highlightCase();
					}
				}
			}
		} else { // second clic sur une position valide
			if (lastPieceClicked.couleur == this.couleur) {
				
				if (lastPieceClicked instanceof Pion) {
					((Pion) lastPieceClicked).premierDeplacement = false;
				}

				ArrayList<Position> pos = lastPieceClicked.getAvailablePositions();
				for (Position p : pos) {
					if (p != null) {
						int xtmp = p.x;
						int ytmp = p.y;
						this.partie.getCaseAt(xtmp, ytmp).removeHighlight();
					}
				}

				// on recupere la position de la derni�re pi�ce cliqu�e
				int xp = lastPieceClicked.position.x;
				int yp = lastPieceClicked.position.y;

				// on l'enl�ve de sont ancienne position
				this.partie.getCaseAt(xp, yp).removePiece();

				// on met dans la case destination la piece precedemment
				// cliquee
				c.piece = lastPieceClicked;
				// on modifie egalement sa position pour la rendre consiente
				// du
				// mouvement
				c.piece.position = c.position;

				for (int x = 0; x < 8; x++) {
					for (int y = 0; y < 8; y++) {
						this.partie.getCaseAt(x, y).removeHighlight();
					}
				}

				if (this.partie.getBlackPlayer() instanceof JoueurIA)
					this.partie.getBlackPlayer().play(p_x, p_y); // note : x et
																	// y servent
																	// a rien
			}
		}
		this.partie.notifyAllObservers();

	}

}
