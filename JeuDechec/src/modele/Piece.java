package modele;

import java.util.ArrayList;

public abstract class Piece {

	public boolean vivante;
	public Position position;
	public boolean couleur;
	public Plateau plateau;
	public String imagePath;

	public final static boolean BLANC = true;
	public final static boolean NOIR = false;

	public abstract ArrayList<Position> getAvailablePositions();

	public abstract boolean caseOccupable(int x, int y);

	public void setImagePath() {
		if (this.couleur == Piece.BLANC) {
			this.imagePath = "images/white_" + this.getClass().getSimpleName() + ".png";
		} else {
			this.imagePath = "images/black_" + this.getClass().getSimpleName() + ".png";
		}
	}

}