package tests;

import java.MVC.Model.jeu.Coordonnees;
import java.MVC.Model.jeu.Niveau;
import java.MVC.Model.bloc.BlocBarreTraversee;
import java.MVC.Model.bloc.BlocEchelle;
import java.MVC.Model.bloc.BlocPortailFinNiveau;
import java.MVC.Model.item.Lingot;
import java.MVC.Model.bloc.Bloc;
import java.MVC.Model.bloc.BlocTerre;

public class NiveauGregoire {
	
	private static Niveau gregoire;
	
	/** Demo
	 * Permet de construite le niveau.
	 */
	private static void Gregoire(){
		gregoire = new Niveau(false);
		Coordonnees pos;
		Bloc courant;
		/* Bordures Horizontales */
		
		for (int i=0; i<30; i++) {
			pos = new Coordonnees(i, 0);
			courant = new BlocTerre(pos, false);
			gregoire.ajouterBloc(courant);
			pos = new Coordonnees(i, 19);
			courant = new BlocTerre(pos, false);
			gregoire.ajouterBloc(courant);
		}
		/* Bordures verticales */
		/*for (int i=1; i<19; i++) {
			pos = new Coordonnees(0, i);
			courant = new BlocTerre(pos, false);
			gregoire.ajouterBloc(courant);
			pos = new Coordonnees(29, i);
			courant = new BlocTerre(pos, false);
			gregoire.ajouterBloc(courant);
		}
		
		/* Ajout d'echelle */
		for(int i = 1 ; i < 19; i++) {
			pos = new Coordonnees(12, i);
			courant = new BlocEchelle(pos);
			gregoire.ajouterBloc(courant);
		}
		/* Bloc */
		for(int i = 1 ; i < 28 ; i++) {
			if(i < 12 || i > 17) {
				pos = new Coordonnees(i, 9);
				courant = new BlocTerre(pos, false);
				gregoire.ajouterBloc(courant);
			} else {
				if(i != 12) {
				pos = new Coordonnees(i, 8);
				courant = new BlocBarreTraversee(pos);
				gregoire.ajouterBloc(courant);
				}
			}
		}
		/* Ajout barre de franchissement */
		pos = new Coordonnees(6, 18);
		Lingot l = new Lingot(pos, 2);
		gregoire.ajouterItem(l);
		pos = new Coordonnees(5, 18);
		Lingot l1 = new Lingot(pos, 2);
		gregoire.ajouterItem(l1);
		pos = new Coordonnees(17, 18);
		Lingot l3 = new Lingot(pos, 2);
		gregoire.ajouterItem(l3);
		pos = new Coordonnees(19, 18);
		BlocPortailFinNiveau f = new BlocPortailFinNiveau(pos);
		f.setConcret(false);
		gregoire.ajouterBloc(f);
	}
	
	/** getDemo
	 * Permet de recuperer le niveau construit.
	 * @return le niveau construit
	 */
	public static Niveau getGregoire() {
		NiveauGregoire.Gregoire();
		return gregoire;
	}

}
