package java.MVC.Model.jeu;

/** Classe joueur du java.MVC.Model.jeu Lode Runner.
 * Correspond au "profil" du joueur.
 * 
 * @author 	Axel Grau
 * @version	0.2
 *
 */
public class Joueur {
	
	/** Nom */
	private String nom;
	/** imageProfil */
	private String imageProfil;
	/** unlockWorld */
	private int unlockedWorld;
	/** unlockLevel */
	private int unlockedLevel;
	/** tempsDeJeu */
	//private Time tempsDeJeu;
	/** nbVieRestantes */
	private int nbVieRestantes;
	
	public Joueur(String nom, String image, int m, int n, int v) {
		this.nom = nom;
		this.imageProfil = image;
		this.unlockedWorld = m;
		this.unlockedLevel = n;
		//this.tempsDeJeu = new Time(0);
		this.nbVieRestantes = v;
	}
	
	public Joueur(String nom, String image) {
		this.nom = nom;
		this.imageProfil = image;
		this.unlockedWorld = 2;
		this.unlockedLevel = 1;
		//this.tempsDeJeu = new Time(0);
		this.nbVieRestantes = 5;
	}
	
	/** Getters et Setters generes automatiquement */
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getImageProfil() {
		return imageProfil;
	}
	public void setImageProfil(String imageProfil) {
		this.imageProfil = imageProfil;
	}
	public int getUnlockedWorld() {
		return unlockedWorld;
	}
	public void setUnlockedWorld(int unlockedWorld) {
		this.unlockedWorld = unlockedWorld;
	}
	public int getUnlockedLevel() {
		return unlockedLevel;
	}
	public void setUnlockedLevel(int unlockedLevel) {
		this.unlockedLevel = unlockedLevel;
	}
	/*public Time getTempsDeJeu() {
		return tempsDeJeu;
	}
	public void setTempsDeJeu(Time tempsDeJeu) {
		this.tempsDeJeu = tempsDeJeu;
	}*/
	public int getNbVieRestantes() {
		return nbVieRestantes;
	}
	public void setNbVieRestantes(int nbVieRestantes) {
		this.nbVieRestantes = nbVieRestantes;
	}
}
