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
		while(this.plateau.pieces[xtmp--][ytmp--] != null && xtmp >= 0 && ytmp >= 0){
			pos[i++] = new Position(xtmp, ytmp);
		}
		// Diagonale +x -y : 
		while(this.plateau.pieces[xtmp++][ytmp--] != null && xtmp >= 0 && ytmp >= 0){
			pos[i++] = new Position(xtmp, ytmp);
		}
		// Diagonale -x +y : 
		while(this.plateau.pieces[xtmp--][ytmp++] != null && xtmp >= 0 && ytmp >= 0){
			pos[i++] = new Position(xtmp, ytmp);
		}
		// Diagonale +x +y : 
		while(this.plateau.pieces[xtmp++][ytmp++] != null && xtmp >= 0 && ytmp >= 0){
			pos[i++] = new Position(xtmp, ytmp);
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
}