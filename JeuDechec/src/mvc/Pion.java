package mvc;
public class Pion extends Piece {
	boolean premierDeplacement = true;
	
	public Pion(boolean couleur) {
		// TODO Auto-generated constructor stub
		this.couleur = couleur;
	}

	@Override
	public Position[] getAvailablePositions() {
		Position[] pos = new Position[2];
		// On verifie dans quelle direction peut aller le pion en regardant sa couleur
		if (this.couleur){ //si il est blanc on vérifie qu'il peut allez dans le - y
			if(premierDeplacement){ //Si c'est le premier tour 
				pos[0] = new Position(this.position.x, this.position.y - 1);
				pos[1] = new Position(this.position.x, this.position.y - 2);
			}
			else if(this.position.y - 1 <= 0){
				pos[0] = new Position(this.position.x, this.position.y - 1);
			}
		}
		else{ //si il est noir on vérifie qu'il peut allez dans le + y
			if(premierDeplacement){ //Si c'est le premier tour 
				pos[0] = new Position(this.position.x, this.position.y + 1);
				pos[1] = new Position(this.position.x, this.position.y + 2);
			}
			else if(this.position.y - 1 <= 0){
				pos[0] = new Position(this.position.x, this.position.y + 1);
			}
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
	public boolean coupValide(Coup coup) {
		return false;
	}
}