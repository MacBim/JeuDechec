package mvc;
public class Pion extends Piece {

	public Pion(boolean couleur) {
		// TODO Auto-generated constructor stub
		this.couleur = couleur;
	}

	@Override
	public Position getAvailablePosition(Coup coup) {
		// TODO Auto-generated method stub
		return null;
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