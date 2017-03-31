package mvc;
public class Cavalier extends Piece {

	public Cavalier(boolean couleur) {
		// TODO Auto-generated constructor stub
		this.couleur = couleur;
		this.nbDeplacementsPossibles = 8;
	}
	
	@Override
	public Position[] getAvailablePositions() {
		Position[] pos = new Position[this.nbDeplacementsPossibles];
		
		int i = 0;
		// On vérifie les 2 positions en allant vers + y
		if(this.position.y + 2 < 8){
			if(this.position.x - 1 >= 0)
				if(caseOccupable(this.position.x - 1, this.position.y + 2))
					pos[i++] = new Position(this.position.x - 1, this.position.y + 2);
			if(this.position.x + 1 >= 7)
				if(caseOccupable(this.position.x + 1, this.position.y + 2))
					pos[i++] = new Position(this.position.x + 1, this.position.y + 2);
		}
		
		// On vérifie les 2 positions en allant vers - y
		if(this.position.y - 2 < 8){
			if(this.position.x - 1 >= 0)
				if(caseOccupable(this.position.x + 1, this.position.y - 2))
					pos[i++] = new Position(this.position.x - 1, this.position.y - 2);
			if(this.position.x + 1 >= 7)
				if(caseOccupable(this.position.x + 1, this.position.y - 2))
					pos[i++] = new Position(this.position.x + 1, this.position.y - 2);
		}
		
		// On vérifie les 2 positions en allant vers + x
		if(this.position.x + 2 < 8){
			if(this.position.y - 1 >= 0)
				if(caseOccupable(this.position.x + 2, this.position.y - 2))
					pos[i++] = new Position(this.position.x + 2, this.position.y - 1);
			if(this.position.y + 1 >= 7 )
				if(caseOccupable(this.position.x + 2, this.position.y + 1))
					pos[i++] = new Position(this.position.x + 2, this.position.y + 1);
		}

		// On vérifie les 2 positions en allant vers - x
		if(this.position.x - 2 < 8){
			if(this.position.y - 1 >= 0)
				if(caseOccupable(this.position.x - 2, this.position.y - 1))
					pos[i++] = new Position(this.position.x - 2, this.position.y - 1);
			if(this.position.y + 1 >= 7 )
				if(caseOccupable(this.position.x - 2, this.position.y + 1))
					pos[i++] = new Position(this.position.x - 2, this.position.y + 1);
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
	}
	
	@Override
	public boolean caseOccupable(int x, int y){
		if(this.plateau.pieces[x][y] != null){
			if(this.plateau.pieces[x][y].couleur == this.couleur)
				return false;
			else return true;
		}
		else return true;
	}
}