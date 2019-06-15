package java.MVC.Model.jeu;

import java.util.Observable;
import java.MVC.Model.Actions.ThreadAnimation;

/**
 * Classe abstraite Positionnable. Permet au modele de deplacer les objets de ce
 * type.
 * 
 * @author Axel Grau
 * @version 1.0
 *
 */
public abstract class Positionnable extends Observable {

	/* ***** ***** ATTRIBUTS ***** ***** */
	/** L'image liee a ce positionnable */
	private java.MVC.Model.jeu.Sprite sprite;
	/** Les coordonnes sur la matrice de ce positionnable */
	private java.MVC.Model.jeu.Coordonnees position;
	/** Les coordonnees en pixels de ce positionnable */
	private java.MVC.Model.jeu.Coordonnees pixels;
    /** Tous les positionnable vont partager le meme thread gestonnaire 
        Qui s'occupera des animations */
    public static ThreadAnimation GESTION_ANIM = new ThreadAnimation();

	/** Constructeur
	 * Permet d'initialiser un positionnable avec ses coordonnees sur la
	 * matrice ainsi que son image.
	 * @param position sa position sur la matrice
	 * @param img son image
	 */
	public Positionnable(java.MVC.Model.jeu.Coordonnees position, String img) {
		this.position = position;
		this.pixels = new java.MVC.Model.jeu.Coordonnees(this.position.getX() * 32, this.position.getY() * 32);
		this.sprite = new java.MVC.Model.jeu.Sprite(img, this.pixels);
                //Positionnable.gestionnaireAnim;
	}

	/**
	 * getPosition Methode utlilisee par l'entité pour connaitre sa position.
	 * 
	 * @return coordonnees sur la matrice.
	 */
	public java.MVC.Model.jeu.Coordonnees getPosition() {
		return position;
	}

	/**
	 * getPixels Methode qui retourne la position en pixels de l'entite.
	 * 
	 * @return coordonnees en pixels.
	 */
	public java.MVC.Model.jeu.Coordonnees getPixels() {
		return this.pixels;
	}

	/**
	 * setPosition Methode utlilisee par le modele pour modifier la position des
	 * entites positionnables
	 * 
	 * @param position
	 *            la nouvelle position de l'entite
	 */
	public void setPosition(java.MVC.Model.jeu.Coordonnees position) {
		this.position = position;
//		System.out.println("MAJJJ PIXELS");
		this.pixels = new java.MVC.Model.jeu.Coordonnees(this.position.getX() * 32, this.position.getY() * 32);
		this.sprite.setPosition(this.pixels.getX(), this.pixels.getY());
	}

	/**
	 * deplacer Permet de deplacer l'entite d'un certain nombre de pixels.
	 * 
	 * @param
	 */
	public void deplacer(int x, int y) {
		this.pixels.setX(this.pixels.getX() + x);
		this.pixels.setY(this.pixels.getY() + y);
		this.majPosition();
//		System.out.println("MAJ deplacement");
		this.mettreAJour();
	}

	private void majPosition() {
		this.position.setX((this.pixels.getX() + 16) / 32);
		this.position.setY((this.pixels.getY() + 16) / 32);
	}

	/** getSprite
	 * Permet d'obtenir le Sprite lie
	 * @return le sprite de ce positionnable.
	 */
	public java.MVC.Model.jeu.Sprite getSprite() {
		return sprite;
	}

	public void setSprite(String img) {
		this.sprite.modifierImage(img);
//		System.out.println("MAJ Sprite");
		this.mettreAJour();
	}

	public void mettreAJour() {
//		System.out.println("MAJ positionnable");
		this.setChanged();
		this.notifyObservers(new int[] { this.position.getX(), this.position.getY() });
		this.clearChanged();
	}
}
