package modele;

import java.util.ArrayList;

import javafx.geometry.Pos;

public class Reine extends Piece {

	public final static int nbDeplacementsMaxPossible = 29;

	public Reine(boolean couleur, Plateau plateau, Position position) {
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
		
		// ******************** Mouvements "tour" ***********************
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
		
		// ******************** Mouvements "fou" ***********************
		xtmp = this.position.x;
		ytmp = this.position.y;
		
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