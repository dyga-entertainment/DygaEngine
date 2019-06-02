package java.MVC.Model.personnage;

import java.MVC.Model.item.Item;
import java.MVC.View.GameViews.GameContentView;
import java.MVC.Model.jeu.Coordonnees;
import java.MVC.Model.jeu.Positionnable;

/** Classe abstraite des personnages du java.MVC.Model.jeu.*
 *   
 * @author 	Axel Grau
 * @version	0.1
 *
 */
public abstract class Personnage extends Positionnable {
	
	/* ***** ***** ATTRIBUTS ***** ***** */
	/** difficulte */
	private String difficulte;
	/** vitesseDeplacement/moveSpeed */
	private int moveSpeed;
	private Coordonnees position;
	private boolean mouvement;
	private GameContentView vue;
	private boolean vivant;
	private boolean regardeAGauche;
	/* ***** ***** ********* ***** ***** */
	
	public Personnage(Coordonnees position, String img, int vitesse) {
            super(position, img);
            this.vivant = true;
            this.position = position;
            this.moveSpeed = vitesse;
            this.mouvement = false;
            this.regardeAGauche = true;
	}
	
	/* ********** DEPLACEMENTS ********** */
	/* le personnage se deplace de "moveSpeed" pixels */
	public void haut() {
		this.deplacer(0, -this.moveSpeed);
		super.getSprite().moveTo(this.getPixels());
	}
	
	public void bas() {
		this.deplacer(0, this.moveSpeed);
		super.getSprite().moveTo(this.getPixels());
	}
	
	public void droite() {
		this.deplacer(this.moveSpeed, 0);
		this.regardeAGauche = false;
		super.getSprite().moveTo(this.getPixels());
	}
	
	public void droite(int vitesse) {
		this.deplacer(vitesse, 0);
		this.regardeAGauche = false;
		super.getSprite().moveTo(this.getPixels());
	}
	
	public void gauche() {
		this.deplacer(-this.moveSpeed, 0);
		this.regardeAGauche = true;
        super.getSprite().moveTo(this.getPixels());
	}
	
	public void gauche(int vitesse) {
		this.deplacer(-vitesse, 0);
		this.regardeAGauche = true;
		super.getSprite().moveTo(this.getPixels());
	}
	/* ********** ************ ********** */
	
	/** Getters et Setters generes automatiquement */
	public String getDifficulte() {
		return difficulte;
	}

	public void setDifficulte(String difficulte) {
		this.difficulte = difficulte;
	}
	
	public void setVivant(Boolean vie) {
		this.vivant = vie;
	}
	
	public boolean estVivant() {
		return this.vivant;
	}

	public int getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	public Coordonnees getPosition() {
		return position;
	}

	public void setPosition(Coordonnees position) {
		this.position = position;
	}

	public Coordonnees getCentre() {
		return new Coordonnees(this.getPixels().getX()+16, this.getPixels().getY()+16);
	}

	public boolean isMouvement() {
		return mouvement;
	}

	public void setMouvement(boolean mouvement) {
		this.mouvement = mouvement;
	}

	public GameContentView getVue() {
		return vue;
	}

	public void setVue(GameContentView vue) {
		this.vue = vue;
	}

	public boolean isRegardeAGauche() {
		return regardeAGauche;
	}

	public void setRegardeAGauche(boolean regardeAGauche) {
		this.regardeAGauche = regardeAGauche;
	}

	public abstract void ramasser(Item item);

	/** deposer
	 * Permet de deposer l'item present dans
	 * est deja vide.
	 */
	public abstract void deposer();
	
	/** estVisible
	 * Indique si le personnage est visible sur la carte.
	 * Si le personnage est dans une cachette ou s'il est mort,
	 * il n'est pas "visible" par les MadMonks.
	 * 
	 * @return boolean de visibilite par les MadMonks
	 */
	public boolean estVisible() {
		return true;
	}
}
