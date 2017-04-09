package modele;

import java.util.ArrayList;

public class Pion extends Piece {

	boolean premierDeplacement = true;

	public final static int nbDeplacementsMaxPossibles = 4;

	public Pion(boolean couleur, Plateau plateau, Position position) {
		// TODO Auto-generated constructor stub
		this.couleur = couleur;
		this.plateau = plateau;
		this.position = position;
		setImagePath();
	}

	@Override
	public ArrayList<Position> getAvailablePositions() {
		// TODO : what ?
		ArrayList<Position> pos = new ArrayList<>();
		int i = 0;
		// On verifie dans quelle direction peut aller le pion en regardant sa
		// couleur
		if (this.couleur == Piece.BLANC) { // si il est blanc on vérifie qu'il
											// peut allez dans le - y
			if (premierDeplacement) { // Si c'est le premier tour
				// Est-ce qu'on peut manger en - x?
				if (caseOccupable(this.position.x - 1, this.position.y - 1))
					pos.add(new Position(this.position.x - 1, this.position.y - 1));
				// Est- ce qu'on peut manger en + x
				if (caseOccupable(this.position.x + 1, this.position.y - 1))
					pos.add( new Position(this.position.x + 1, this.position.y - 1));

				// Est ce qu'on peut allez devant?
				if (caseOccupable(this.position.x, this.position.y - 1)){
					pos.add( new Position(this.position.x, this.position.y - 1));
					if (caseOccupable(this.position.x, this.position.y - 2))
						pos.add( new Position(this.position.x, this.position.y - 2));
				}
			} else {
				// Est-ce qu'on peut manger en - x?
				if (caseOccupable(this.position.x - 1, this.position.y - 1))
					pos.add( new Position(this.position.x -1, this.position.y - 1));
				// Est- ce qu'on peut manger en + x
				if (caseOccupable(this.position.x + 1, this.position.y - 1))
					pos.add( new Position(this.position.x + 1, this.position.y - 1));

				// Est ce qu'on peut allez devant?
				if (caseOccupable(this.position.x, this.position.y - 1))
					pos.add( new Position(this.position.x, this.position.y - 1));
			}
		} else { // si il est noir on vérifie qu'il peut allez dans le + y
			if (premierDeplacement) { // Si c'est le premier tour
				// Est-ce qu'on peut manger en - x?
				if (caseOccupable(this.position.x - 1, this.position.y + 1))
					pos.add( new Position(this.position.x - 1, this.position.y + 1));
				// Est- ce qu'on peut manger en + x
				if (caseOccupable(this.position.x + 1, this.position.y + 1))
					pos.add( new Position(this.position.x + 1, this.position.y + 1));

				// Est ce qu'on peut allez devant?
				if (caseOccupable(this.position.x, this.position.y + 1)){
					pos.add( new Position(this.position.x, this.position.y + 1));
					if (caseOccupable(this.position.x, this.position.y + 2))
						pos.add( new Position(this.position.x, this.position.y + 2));
				}
			} else {
				// Est-ce qu'on peut manger en - x?
				if (caseOccupable(this.position.x - 1, this.position.y + 1))
					pos.add( new Position(this.position.x - 1, this.position.y + 1));
				// Est- ce qu'on peut manger en + x
				if (caseOccupable(this.position.x + 1, this.position.y + 1))
					pos.add( new Position(this.position.x + 1, this.position.y + 1));
				// Est ce qu'on peut allez devant?
				if (caseOccupable(this.position.x, this.position.y + 1))
					pos.add( new Position(this.position.x, this.position.y + 1));

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
	public void coupValide(Coup coup) {

	}

	@Override
	public boolean caseOccupable(int x, int y) {
		if (x > 7 || y > 7 || y < 0 || x < 0)
			return false;
		// Le cas ou c'est une case de devant:
		if (x == this.position.x && (
				y == this.position.y + 1 || y == this.position.y - 1 || y == this.position.y + 2 || y == this.position.y - 2)){
			if (this.plateau.cases[x][y].piece == null){
				return true;
			}
			else
				return false;
		}
		// Si on est dans le cas ou on veut manger une piece adverse :
		else if (this.plateau.cases[x][y].piece != null){
			if (this.plateau.cases[x][y].piece.couleur != this.plateau.cases[this.position.x][this.position.y].piece.couleur)
				return true;
			else
				return false;
		}
		else
			return false;
	}
}