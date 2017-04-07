package modele;

public class Case {
	public Position position;
	public boolean isLit = false;
	public boolean pieceMoved = false;
	
	public Piece piece;
	
	public Case(Position position){
		this.piece = null;
		this.position = position;
	}
	
	public void addPiece(Piece p){
		this.piece = p;
	}
	
	public void removePiece(){
		this.piece = null;
	}
	
	public void removeHighlight(){
		this.isLit = false;
	}

	public void highlightCase() {
		this.isLit = true;
	}
	
	public String toString(){
		String ret = "";
		ret += "x:"+this.position.x+"y:"+this.position.y+"|";
		ret += (this.piece != null) ? this.piece.getClass().getSimpleName() : "VIDE";
		ret += (this.piece != null && this.piece.couleur == Piece.BLANC) ? "|BLANC" : "|NOIR";
		ret += (this.isLit ) ? "|Lit" : "";
		ret += (this.pieceMoved ) ? "|PIECEMOVED" : "";
		return ret;
	}
}
