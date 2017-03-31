package mvc;

public class Fou extends Piece {

	public Fou(boolean couleur) {
		// TODO Auto-generated constructor stub
		this.couleur = couleur;
		this.nbDeplacementsPossibles = 13;
	}

	@Override
	public Position[] getAvailablePositions() {
		Position[] pos = new Position[this.nbDeplacementsPossibles];
		int xtmp = this.position.x;
		int ytmp = this.position.y;
		int i = 0;
		// On doit parcourire les 4 diagonales qui partent du fou 
		// jusqu'à ce qu'il sorte du terrain ou rencontre une case occupée.
		
		
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
		if(this.plateau.pieces[x][y] == null){
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
	public void appliquerCoup(Coup coup) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void coupValide(Coup coup) {
		// TODO Auto-generated method stub
		
	}
	
	
	public boolean caseOccupable(int x, int y){
		if(this.plateau.pieces[x][y].couleur == this.couleur)
			return false;
		else return true;
	}
}