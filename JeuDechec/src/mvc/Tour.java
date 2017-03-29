package mvc;
public class Tour extends Piece {

	
	public Tour(boolean couleur, Plateau plateau, Position position){
		this.plateau = plateau;
		this.couleur = couleur;
		this.position = position;
	}
	@Override
	public Position[] getAvailablePositions() {
		Position[] pos = new Position[16];
		int xtmp = this.position.x;
		int ytmp = this.position.y;
		/* Pour les X a droite */
		while(this.plateau.pieces[xtmp++][ytmp] != null && xtmp < 8){
			pos[pos.length] = new Position(xtmp, ytmp);
		}
		
		xtmp = this.position.x;
		/* Pour les X a gauche */
		while(this.plateau.pieces[xtmp--][ytmp] != null && xtmp > 0 ){
			pos[pos.length] = new Position(xtmp, ytmp);
		}
		
		xtmp = this.position.x;
		ytmp = this.position.y;
		/* Pour les Y qui monte */
		while(this.plateau.pieces[xtmp][ytmp++] != null && ytmp > 0 ){
			pos[pos.length] = new Position(xtmp, ytmp);
		}
		
		xtmp = this.position.x;
		ytmp = this.position.y;
		/* Pour les Y qui monte */
		while(this.plateau.pieces[xtmp][ytmp--] != null && ytmp < 8 ){
			pos[pos.length] = new Position(xtmp, ytmp);
		}
		// TODO Auto-generated method stub
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