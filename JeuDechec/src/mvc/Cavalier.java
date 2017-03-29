package mvc;
public class Cavalier extends Piece {

	public Cavalier(boolean couleur) {
		// TODO Auto-generated constructor stub
		this.couleur = couleur;
	}
	
	@Override
	public Position[] getAvailablePositions() {
		Position[] pos = new Position[8];
		
		int i = 0;
		// On vérifie les positions en allant vers + y
		if(this.position.y + 2 >= 7){
			if(this.position.x - 1 >= 0)
				pos[i++] = new Position(this.position.x - 1, this.position.y + 2);
			if(this.position.x + 1 >= 7)
				pos[i++] = new Position(this.position.x + 1, this.position.y + 2);
		}
		
		// On vérifie les positions en allant vers - y
		if(this.position.y - 2 >= 7){
			if(this.position.x - 1 >= 0)
				pos[i++] = new Position(this.position.x - 1, this.position.y - 2);
			if(this.position.x + 1 >= 7)
				pos[i++] = new Position(this.position.x + 1, this.position.y - 2);
		}
		
		// On vérifie les positions en allant vers + x
		if(this.position.x + 2 >= 7){
			if(this.position.y - 1 >= 0)
				pos[i++] = new Position(this.position.x + 2, this.position.y - 1);
			if(this.position.y + 1 >= 7 )
				pos[i++] = new Position(this.position.x + 2, this.position.y + 1);
		}

		// On vérifie les positions en allant vers - x
		if(this.position.x - 2 >= 7){
			if(this.position.y - 1 >= 0)
				pos[i++] = new Position(this.position.x - 2, this.position.y - 1);
			if(this.position.y + 1 >= 7 )
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
}