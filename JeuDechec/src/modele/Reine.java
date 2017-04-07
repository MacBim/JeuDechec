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
		Position[] pos = new Position[Reine.nbDeplacementsMaxPossible];
		int xtmp = this.position.x;
		int ytmp = this.position.y;
		int i = 0;
		
		// ******************** Mouvements "tour" ***********************
		/* Pour les X + */
		while(++xtmp < 8 ){
			if(!encoreDesPositions(xtmp, ytmp, pos, i++)){
				break;
			}
		}

		xtmp = this.position.x;
		/* Pour les X - */
		while(--xtmp >= 0 ){
			if(!encoreDesPositions(xtmp, ytmp, pos, i++)){
				break;
			}
		}

		xtmp = this.position.x;
		ytmp = this.position.y;
		/* Pour les Y - */
		while(++ytmp < 8 ){
			if(!encoreDesPositions(xtmp, ytmp, pos, i++)){
				break;
			}
		}

		xtmp = this.position.x;
		ytmp = this.position.y;
		/* Pour les Y + */
		while(--ytmp >= 0 ){
			if(!encoreDesPositions(xtmp, ytmp, pos, i++)){
				break;
			}
		}
		
		// ******************** Mouvements "fou" ***********************
		xtmp = this.position.x;
		ytmp = this.position.y;
		
		// Diagonale -x -y : 
		while(--xtmp >= 0 && --ytmp >= 0){
			if(!encoreDesPositions(xtmp, ytmp, pos, i++)){
				break;
			}
		}
		
		xtmp = this.position.x;
		ytmp = this.position.y;
		// Diagonale +x -y : 
		while(++xtmp < 8 && --ytmp >= 0){
			if(!encoreDesPositions(xtmp, ytmp, pos, i++)){
				break;
			}
		}
		
		xtmp = this.position.x;
		ytmp = this.position.y;
		// Diagonale -x +y : 
		while(--xtmp >= 0 && ++ytmp < 8){
			if(!encoreDesPositions(xtmp, ytmp, pos, i++)){
				break;
			}
		}
		
		xtmp = this.position.x;
		ytmp = this.position.y;
		// Diagonale +x +y : 
		while(++xtmp < 8 && ++ytmp < 8){
			if(!encoreDesPositions(xtmp, ytmp, pos, i++)){
				break;
			}
		}
		
		return pos;
	}
	
	public boolean caseOccupable(int x, int y){
		if(this.plateau.cases[x][y].piece.couleur == this.couleur)
			return false;
		return true;
	}
	
	public boolean encoreDesPositions(int x, int y, Position[] pos, int i) {
		if(this.plateau.cases[x][y].piece == null){
			pos[i] = new Position(x, y);
			return true;
		}
		else if(caseOccupable(x, y)){
			pos[i] = new Position(x, y);
			return false;
		}
		else return false;
	}
	
}