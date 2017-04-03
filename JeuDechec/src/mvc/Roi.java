package mvc;

public class Roi extends Piece {

	public final static int nbDeplacementsMaxPossibles = 8;

	public Roi(boolean couleur, Plateau plateau, Position position) {
		// TODO Auto-generated constructor stub
		this.couleur = couleur;
		this.plateau = plateau;
		this.position = position;
	}

	@Override
	public Position[] getAvailablePositions() {
		Position[] pos = new Position[this.nbDeplacementsMaxPossibles];

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

	@Override
	public boolean caseOccupable(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}
}