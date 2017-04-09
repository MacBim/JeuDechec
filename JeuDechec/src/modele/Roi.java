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
			if (caseOccupable(thePosition)) {
				pos.add(thePosition);
			}
		}

		return pos;
	}

	public boolean caseOccupable(Position pos) {
		System.out.println(pos.x + " " + pos.y);
		if (pos.x > 7 || pos.x < 0 || pos.y > 7 || pos.y < 0)
			return false;
		else if (this.plateau.cases[pos.x][pos.y].piece != null) {
			if (this.plateau.cases[pos.x][pos.y].piece.couleur == this.couleur) {
				return false;
			}

		}

		// TODO: Ajouter "else if (EchecEtMath()) return false;"

		return true;

	}

	@Override
	public boolean caseOccupable(int x, int y) {
		// TODO Auto-generated method stub
		return false;
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