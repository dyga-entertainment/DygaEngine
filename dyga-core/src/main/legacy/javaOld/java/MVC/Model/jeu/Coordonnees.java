package java.MVC.Model.jeu;

/** Classe de coordonnees
 * Permet une manipulation des coordonnees
 * dans une matrice de java.MVC.Model.jeu ou pour des pixels
 * en assurant de ne pas partager le meme 
 * objet d'une entite a une autre.
 * 
 * @author	Axel Grau
 * @version	2.0
 *
 */
public class Coordonnees {

	/** abscisse */
	private int x;
	/** ordonnee */
	private int y;
	
	/** Constructeur..
	 * @param x abscisse
	 * @param y ordonnee
	 */
	public Coordonnees(int x, int y) {
		this.x= new Integer(x);
		this.y= new Integer(y);
	}

	/** Getters et Setters generes automatiquement */
	public int getX() {
		return new Integer(x);
	}
	public int getY() {
		return new Integer(y);
	}
	public void setX(int pX) {
		this.x = new Integer(pX);
	}
	public void setY(int pY) {
		this.y = new Integer(pY);
	}

	@Override
	public boolean equals(Object obj) {
        if (obj instanceof Coordonnees) {
            Coordonnees other = (Coordonnees) obj;
            return ((this.x == other.getX()) && (this.y == other.getY()));
        } else {
            return false;
        }
	}

}
