package mvc;

public class Plateau {

  public Piece[][] pieces;
  
  
  public Plateau(){
	  this.pieces = new Piece[8][8];
	  // haut
	  
	  this.pieces[0][0] = new Tour(Piece.NOIR, this, );
	  this.pieces[7][0] = new Tour(Piece.NOIR);
	  
	  this.pieces[1][0] = new Cavalier(Piece.NOIR);
	  this.pieces[6][0] = new Cavalier(Piece.NOIR);
	  
	  this.pieces[2][0] = new Fou(Piece.NOIR);
	  this.pieces[5][0] = new Fou(Piece.NOIR);
	  
	  this.pieces[3][0] = new Reine(Piece.NOIR);
	  this.pieces[4][0] = new Roi(Piece.NOIR);
	  
	  for(int x = 0; x < 8; x++){
		  this.pieces[x][1] = new Pion(Piece.NOIR);
		  this.pieces[x][6] = new Pion(Piece.BLANC);
	  }
	  
	  this.pieces[0][7] = new Tour(Piece.BLANC);
	  this.pieces[7][7] = new Tour(Piece.BLANC);
	  
	  this.pieces[1][7] = new Cavalier(Piece.BLANC);
	  this.pieces[6][7] = new Cavalier(Piece.BLANC);
	  
	  this.pieces[2][7] = new Fou(Piece.BLANC);
	  this.pieces[5][7] = new Fou(Piece.BLANC);
	  
	  this.pieces[3][7] = new Reine(Piece.BLANC);
	  this.pieces[4][7] = new Roi(Piece.BLANC);
  }

  

}