package modele;

public class Case {
	public Position position;
	public boolean isLit;
	
	public Piece piece;
	
	public Case(Position position){
		this.piece = null;
		this.position = position;
//		int positionSum = position.x + position.y;
	}
	
	public void addPiece(Piece p){
		this.piece = p;
	}
	
	public void removePiece(){
		this.piece = null;
	}

	public void highlightCase() {
		// TODO Auto-generated method stub
		this.isLit = true;
	}
}
