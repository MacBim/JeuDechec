package mvc;

public abstract class Piece {

  public Boolean vivante;
  public Position position;

  public abstract Position getAvailablePosition(Coup coup);

  public abstract Boolean getDirValide(Coup coup);

  public abstract void appliquerCoup(Coup coup);

  public abstract void coupValide(Coup coup);

}