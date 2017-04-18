package modele;

import java.util.ArrayList;

public class Roi extends Piece {

	public final static int nbDeplacementsMaxPossibles = 8;

	public Roi(boolean couleur, Plateau plateau, Position position) {
		// TODO Auto-generated constructor stub
		this.couleur = couleur;
		this.plateau = plateau;
		this.position = position;
		setImagePath();
	}

	@Override	
	public ArrayList<Position> getAvailablePositions() {
		ArrayList<Position> pos = new ArrayList<>();

		Position[] defaultPositions = { new Position(this.position.x - 1, this.position.y - 1),
				new Position(this.position.x, this.position.y - 1),
				new Position(this.position.x + 1, this.position.y - 1),
				new Position(this.position.x - 1, this.position.y + 1),
				new Position(this.position.x, this.position.y + 1),
				new Position(this.position.x + 1, this.position.y + 1),
				new Position(this.position.x - 1, this.position.y),
				new Position(this.position.x + 1, this.position.y) };

		int i = 0;
		for (Position thePosition : defaultPositions) {
			if (caseOccupable(thePosition.x, thePosition.y)) {
				pos.add(thePosition);
			}
		}

		return pos;
	}
	
	@Override
	public boolean caseOccupable(int x, int y) {
		if (x > 7 || x < 0 || y > 7 || y < 0)
			return false;
		else if (this.plateau.cases[x][y].piece != null) {
			if (this.plateau.cases[x][y].piece.couleur == this.couleur) {
				return false;
			}
			
			// We need do check if king can be eaten where he goes
			// But can do it here like this
			
//			if (canGetKilled(pos)){
//				return false;
//			}
			
			// Because obviously it will lead to an infinite recursive loop
			// causing Stack overflow 
			// TODO: Find a way
		}

		return true;

	}

	
	/*
	 * We need to check if from the selected position the king can be killed
	 */
	
	public boolean canGetKilled(Position pos){
		ArrayList<Piece> oponentPieces = this.plateau.getPiecesFromColor(!this.couleur);
		Roi roiTmp = new Roi(this.couleur, this.plateau, pos);
		Plateau plateauTmp = this.plateau.clone();
		
		// Switch cases
		plateauTmp.cases[roiTmp.position.x][roiTmp.position.y].piece = null;
		plateauTmp.cases[pos.x][pos.y].piece = roiTmp;
		
		for(Piece oponentPiece : oponentPieces){
			ArrayList<Position> oponentPositions = oponentPiece.getAvailablePositions();
			for(Position oponentPos : oponentPositions){
				if (oponentPos == pos)
					return true;
			}
		}
		return false;
	}

}