package modele;

public abstract class Joueur {

	public Partie partie;
	boolean couleur;

	public Joueur(Partie partie, boolean couleur) {
		this.partie = partie;
		this.couleur = couleur;
	}

	protected abstract void play(int x, int y);

}