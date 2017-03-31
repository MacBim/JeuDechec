package mvc;
public class Roi extends Piece {

	public Roi(boolean couleur) {
		// TODO Auto-generated constructor stub
		this.couleur = couleur;
		this.nbDeplacementsPossibles = 8;
	}

	@Override
	public Position[] getAvailablePositions() {
		Position[] pos = new Position[this.nbDeplacementsPossibles];
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