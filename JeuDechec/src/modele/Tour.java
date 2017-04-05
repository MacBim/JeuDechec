package modele;

public class Tour extends Piece {

	public final static int nbDeplacementsMaxPossibles = 16;

	public Tour(boolean couleur, Plateau plateau, Position position) {
		this.plateau = plateau;
		this.couleur = couleur;
		this.position = position;
		setImagePath();
	}

	@Override
	public Position[] getAvailablePositions() {
		Position[] pos = new Position[nbDeplacementsMaxPossibles];
		int xtmp = this.position.x;
		int ytmp = this.position.y;
		int i = 0;
		/* Pour les X a droite */
		while (xtmp < 8) {
			if (this.plateau.cases[xtmp + 1][ytmp].piece == null)
				pos[i++] = new Position(xtmp++, ytmp);
			else if (caseOccupable(xtmp + 1, ytmp))
				pos[i++] = new Position(xtmp++, ytmp);
			else
				break;
		}

		xtmp = this.position.x;
		/* Pour les X a gauche */
		while (xtmp > 0) {
			if (this.plateau.cases[xtmp - 1][ytmp].piece == null)
				pos[i++] = new Position(xtmp--, ytmp);
			else if (caseOccupable(xtmp - 1, ytmp))
				pos[i++] = new Position(xtmp--, ytmp);
			else
				break;
		}

		xtmp = this.position.x;
		ytmp = this.position.y;
		/* Pour les Y qui monte */
		while (xtmp > 0) {
			if (this.plateau.cases[xtmp][ytmp - 1].piece == null)
				pos[i++] = new Position(xtmp, ytmp--);
			else if (caseOccupable(xtmp, ytmp - 1))
				pos[i++] = new Position(xtmp, ytmp--);
			else
				break;
		}

		xtmp = this.position.x;
		ytmp = this.position.y;
		/* Pour les Y qui descende */
		while (xtmp < 8) {
			if (this.plateau.cases[xtmp][ytmp + 1].piece == null)
				pos[i++] = new Position(xtmp, ytmp++);
			else if (caseOccupable(xtmp, ytmp + 1))
				pos[i++] = new Position(xtmp, ytmp++);
			else
				break;
		}
		return pos;
	}

	@Override
	public boolean getDirValide(Coup coup) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void appliquerCoup(Coup coup) {
		// TODO Auto-generated method stub

	}

	@Override
	public void coupValide(Coup coup) {
		// TODO Auto-generated method stub

	}

	public boolean caseOccupable(int x, int y) {
		if (this.plateau.cases[x][y].piece.couleur == this.couleur)
			return false;
		return true;
	}
}