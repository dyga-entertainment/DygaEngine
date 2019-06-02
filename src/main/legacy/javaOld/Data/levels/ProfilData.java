package Data.levels;

import java.util.ArrayList;
import java.util.List;

import java.MVC.Model.jeu.Joueur;

public class ProfilData {
	private List<Joueur> joueurs;
	private int nbJoueur;
	
	public ProfilData() {
		this.nbJoueur = 2;
		this.joueurs = new ArrayList<Joueur>();
		this.joueurs.add(new Joueur("Damien","avatar_damien.png",3,2,5));
		this.joueurs.add(new Joueur("Axel","avatar_axel.png",2,1,4));
	}
	
	public ProfilData(String[] text) {
		this.nbJoueur = Integer.parseInt(text[0]);
		this.joueurs = new ArrayList<Joueur>();
		for (int i = 0; i < this.nbJoueur; i++) {
			this.joueurs.add(new Joueur(text[1+i*5],text[2+i*5],Integer.parseInt(text[3+i*5]),Integer.parseInt(text[4+i*5]),Integer.parseInt(text[5+i*5])));
		}
	}
	
	public String inv(){
		String s = String.valueOf(this.nbJoueur);
		for ( int i = 0; i < this.nbJoueur; i++) {
		s = s + " " + this.joueurs.get(i).getNom() + " " + this.joueurs.get(i).getImageProfil() 
				  + " " + String.valueOf(this.joueurs.get(i).getUnlockedWorld()) + " " + String.valueOf(this.joueurs.get(i).getUnlockedLevel())
				  + " " + String.valueOf(this.joueurs.get(i).getNbVieRestantes());
		}
		return s;
	}
	
	public int getNbJ(){
		return this.nbJoueur;
	}
	
	public void setNbJ(int nb){
		assert ( nb >= 0 && nb <= 3);
		this.nbJoueur = nb;
	}
	
	public Joueur getJ(int num){
		return this.joueurs.get(num);
	}
	
	public void addJ(Joueur j){
		this.joueurs.add(j);
		this.nbJoueur++;
	}
	
	public void supprJ(int k) {
		this.joueurs.remove(k);
		this.nbJoueur--;
	}
}
