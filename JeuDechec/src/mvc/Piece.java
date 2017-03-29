package mvc;

public abstract class Piece {

  public boolean vivante;
  public Position position;
  public boolean couleur;
  
  public final static boolean BLANC = true;
  public final static boolean NOIR = false;

  public abstract Position getAvailablePosition(Coup coup);

  public abstract boolean getDirValide(Coup coup);

  public abstract void appliquerCoup(Coup coup);

  public abstract void coupValide(Coup coup);

}