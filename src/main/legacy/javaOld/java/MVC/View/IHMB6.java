package java.MVC.View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;

import javax.swing.JPanel;

import Data.levels.ProfilData;

public class IHMB6 extends Bandeau {
	
	private int nbBouton;
	// Normalement un tableau
	private JPButton validerp;
	private JPButton creerp;
	private JPButton quitter;
	private JPButton supprp;
	
	public IHMB6() {
		this.nbBouton = 4;
		this.setLayout(new BorderLayout());
		this.add(new JPanel(), BorderLayout.NORTH);
		JPanel jpa2 = new JPanel(); 
		jpa2.setLayout(new GridLayout(2,4,20,0));
		
		BufferedReader fluxEntree=null;
	    String ligneLue = "";
	    /*
	    try {
	    	// Creation du flux vers le fichier texte
			URL url = getClass().getResource("/ressources/gamedata/Profils.txt");
	    	fluxEntree = new BufferedReader(new FileReader(url.getPath()));

	    	// Lecture d'une ligne
	    	ligneLue = fluxEntree.readLine();
	    }
	    catch(IOException exc){
	    	exc.printStackTrace();
	    }
	    finally{
	    	try{
	    		if(fluxEntree!=null){
	    			// Fermeture du flux vers le fichier
	    			fluxEntree.close();
	    		}
	    	}
	    	catch(IOException e){
	    		e.printStackTrace();
	    	}
	    }
		ProfilData profils = new ProfilData(ligneLue.split(" "));
		*/
		this.quitter = new JPButton("Retour","bouton_retour1.png","bouton_retour2.png");

		this.validerp = new JPButton("Valider Profil","bouton_valider1.png","bouton_valider2.png");

		this.creerp = new JPButton("Creer Profil","bouton_creer_profil1.png","bouton_creer_profil2.png");
		
		this.supprp = new JPButton("Supprimer Profil","bouton_supprimer_profil1.png","bouton_supprimer_profil2.png");		
		
		//majIHM(profils);
		
		jpa2.add(this.quitter);
		jpa2.add(this.creerp);
		jpa2.add(this.validerp);
		jpa2.add(this.supprp);
		jpa2.add(new JPanel());
		jpa2.add(new JPanel());
		jpa2.add(new JPanel());
		jpa2.add(new JPanel());
		
		this.add(jpa2, BorderLayout.SOUTH);
	}
	
	public void majIHM(ProfilData p) {
		switch(p.getNbJ()) {
		case 0 :
			this.validerp.setEnabled(false);
			this.supprp.setEnabled(false);
			break;
		case 1 :
			this.supprp.setEnabled(true);
			this.validerp.setEnabled(true);
			break;
		case 2 :
			this.creerp.setEnabled(true);
			break;
		default : // case 3
			this.creerp.setEnabled(false);
			break;
		}
	}
	
	public JPButton getButton(int i) {
		JPButton b;
		switch(i) {
			case 0 :
				b = this.quitter;
				break;
			case 1 :
				b = this.creerp;
				break;
			case 2 :
				b = this.validerp;
				break;
			default : // case 3
				b = this.supprp;
				break;
		}
		return b;
	}
	
	public int getNbButton() {
		return this.nbBouton;
	}
}