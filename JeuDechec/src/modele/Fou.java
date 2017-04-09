package modele;

import java.util.ArrayList;

public class Fou extends Piece {

	public final static int nbDeplacementsMaxPossibles = 13;
	
	public Fou(boolean couleur, Plateau plateau, Position position) {
		// TODO Auto-generated constructor stub
		this.couleur = couleur;
		this.plateau = plateau;
		this.position = position;
		setImagePath();
	}

	@Override
	public ArrayList<Position> getAvailablePositions() {
		ArrayList<Position> pos = new ArrayList<>();
		int xtmp = this.position.x;
		int ytmp = this.position.y;
		int i = 0;
		// On doit parcourire les 4 diagonales qui partent du fou 
		// jusqu'à ce qu'il sorte du terrain ou rencontre une case occupée.
		
		
		// Diagonale -x -y : 
		while(--xtmp >= 0 && --ytmp >= 0){
			if(!encoreDesPositions(xtmp, ytmp, pos)){
				break;
			}
		}
		
		xtmp = this.position.x;
		ytmp = this.position.y;
		// Diagonale +x -y : 
		while(++xtmp < 8 && --ytmp >= 0){
			if(!encoreDesPositions(xtmp, ytmp, pos)){
				break;
			}
		}
		
		xtmp = this.position.x;
		ytmp = this.position.y;
		// Diagonale -x +y : 
		while(--xtmp >= 0 && ++ytmp < 8){
			if(!encoreDesPositions(xtmp, ytmp, pos)){
				break;
			}
		}
		
		xtmp = this.position.x;
		ytmp = this.position.y;
		// Diagonale +x +y : 
		while(++xtmp < 8 && ++ytmp < 8){
			if(!encoreDesPositions(xtmp, ytmp, pos)){
				break;
			}
		}
		
		return pos;
	}
	
	public boolean encoreDesPositions(int x, int y, ArrayList<Position> pos) {
		if(this.plateau.cases[x][y].piece == null){
			pos.add(new Position(x, y));
			return true;
		}
		else if(caseOccupable(x, y)){
			pos.add(new Position(x, y));
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
		if(this.plateau.cases[x][y].piece.couleur == this.couleur)
			return false;
		return true;
	}

	@Override
	public boolean getDirValide(Coup coup) {
		// TODO Auto-generated method stub
		return false;
	}
}