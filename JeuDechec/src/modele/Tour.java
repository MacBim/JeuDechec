package modele;

import java.util.ArrayList;

public class Tour extends Piece {

	public final static int nbDeplacementsMaxPossibles = 16;

	public Tour(boolean couleur, Plateau plateau, Position position) {
		this.plateau = plateau;
		this.couleur = couleur;
		this.position = position;
		setImagePath();
	}

	@Override
	public ArrayList<Position> getAvailablePositions() {
		ArrayList<Position> pos = new ArrayList<>();
		int xtmp = this.position.x;
		int ytmp = this.position.y;
		int i = 0;
	
		/* Pour les X + */
		while(++xtmp < 8 ){
			if(!encoreDesPositions(xtmp, ytmp, pos)){
				break;
			}
		}

		xtmp = this.position.x;
		/* Pour les X - */
		while(--xtmp >= 0 ){
			if(!encoreDesPositions(xtmp, ytmp, pos)){
				break;
			}
		}

		xtmp = this.position.x;
		ytmp = this.position.y;
		/* Pour les Y - */
		while(++ytmp < 8 ){
			if(!encoreDesPositions(xtmp, ytmp, pos)){
				break;
			}
		}

		xtmp = this.position.x;
		ytmp = this.position.y;
		/* Pour les Y + */
		while(--ytmp >= 0 ){
			if(!encoreDesPositions(xtmp, ytmp, pos)){
				break;
			}
		}
		return pos;
	}


	public boolean caseOccupable(int x, int y){
		if(this.plateau.cases[x][y].piece.couleur == this.couleur)
			return false;
		return true;
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
}

