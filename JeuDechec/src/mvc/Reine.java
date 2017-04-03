package mvc;

public class Reine extends Piece {

	public final static int nbDeplacementsMaxPossible = 100;

	public Reine(boolean couleur, Plateau plateau, Position position) {
		// TODO Auto-generated constructor stub
		this.couleur = couleur;
		this.plateau = plateau;
		this.position = position;
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
		Position[] ret = new Position[this.nbDeplacementsMaxPossible];
		// TODO Auto-generated method stub
		return ret;
	}

	@Override
	public boolean caseOccupable(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}
}