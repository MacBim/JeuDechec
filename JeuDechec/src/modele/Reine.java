package modele;

public class Reine extends Piece {

	public final static int nbDeplacementsMaxPossible = 29;

	public Reine(boolean couleur, Plateau plateau, Position position) {
		// TODO Auto-generated constructor stub
		this.couleur = couleur;
		this.plateau = plateau;
		this.position = position;
		setImagePath();
		
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

	@Override
	public Position[] getAvailablePositions() {
		Position[] pos = new Position[this.nbDeplacementsMaxPossible];
		int xtmp = this.position.x;
		int ytmp = this.position.y;
		int i = 0;
		
		// ******************** Mouvements "tour" ***********************
		/* Pour les X a droite */
		while (xtmp < 8) 
		{
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
		
		// ******************** Mouvements "fou" ***********************
		
		i = 0;
		xtmp = this.position.x;
		ytmp = this.position.y;
		
		// Diagonale -x -y : 
		while(--xtmp < 8 && --ytmp >= 0){
			if(!encoreDesPositions(xtmp, ytmp, pos, i))
				break;
		}
		
		xtmp = this.position.x;
		ytmp = this.position.y;
		// Diagonale +x -y : 
		while(++xtmp < 8 && --ytmp >= 0){
			if(!encoreDesPositions(xtmp, ytmp, pos, i))
				break;
		}
		
		xtmp = this.position.x;
		ytmp = this.position.y;
		// Diagonale -x +y : 
		while(--xtmp >= 0 && ++ytmp < 8){
			if(!encoreDesPositions(xtmp, ytmp, pos, i))
				break;
		}
		
		xtmp = this.position.x;
		ytmp = this.position.y;
		// Diagonale +x +y : 
		while(++xtmp < 8 && ++ytmp < 8){
			if(!encoreDesPositions(xtmp, ytmp, pos, i))
				break;
		}
		
		return pos;
	}
	
	public boolean encoreDesPositions(int x, int y, Position[] pos, int i) {
		if(this.plateau.cases[x][y].piece == null){
			pos[i++] = new Position(x, y);
			return true;
		}
		else if(caseOccupable(x, y)){
			pos[i++] = new Position(x, y);
			return false;
		}
		else return false;
	}

	@Override
	public boolean caseOccupable(int x, int y) {
		if (this.plateau.cases[x][y].piece.couleur == this.couleur)
			return false;
		return true;
	}
	
}