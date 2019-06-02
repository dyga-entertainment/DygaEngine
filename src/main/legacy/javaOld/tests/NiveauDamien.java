package tests;

import java.MVC.Model.jeu.Coordonnees;
import java.MVC.Model.jeu.Niveau;
import java.MVC.Model.bloc.BlocBrique;
import java.MVC.Model.bloc.BlocEchelle;
import java.MVC.Model.bloc.BlocGlu;
import java.MVC.Model.item.Lingot;
import java.MVC.Model.item.PetiteBombe;
import java.MVC.Model.bloc.Bloc;

public class NiveauDamien {
	
	private static Niveau damien;
	
	/** Demo
	 * Permet de construite le niveau.
	 */
	private static void Damien(){
		damien = new Niveau(false);
		Coordonnees pos;
		Bloc courant;
		/* Bordures verticales */
		for (int i=0; i<30; i++) {
			pos = new Coordonnees(i, 0);
			courant = new BlocBrique(pos);
			damien.ajouterBloc(courant);
			pos = new Coordonnees(i, 19);
			courant = new BlocBrique(pos);
			damien.ajouterBloc(courant);
		}
		/* Bordures horizontales */
		for (int i=1; i<19; i++) {
			pos = new Coordonnees(0, i);
			courant = new BlocBrique(pos);
			damien.ajouterBloc(courant);
			pos = new Coordonnees(29, i);
			courant = new BlocBrique(pos);
			damien.ajouterBloc(courant);
		}
		
		for (int i=0;i<16;i++){
			pos = new Coordonnees(i, 5);
			courant = new BlocGlu(pos);
			damien.ajouterBloc(courant);
		}
		
		
		for (int i=5;i<19;i++){
			pos = new Coordonnees(16, i);
			courant = new BlocEchelle(pos);
			damien.ajouterBloc(courant);
		}
		
		pos = new Coordonnees(6, 18);
		Lingot l = new Lingot(pos, 1);
		damien.ajouterItem(l);
		
		pos = new Coordonnees(5, 18);
		l = new Lingot(pos, 2);
		damien.ajouterItem(l);
		
		pos = new Coordonnees(4, 18);
		l = new Lingot(pos, 3);
		damien.ajouterItem(l);
		
		pos = new Coordonnees(3, 18);
		l = new Lingot(pos, 4);
		damien.ajouterItem(l);
		
		pos = new Coordonnees(2, 18);
		l = new Lingot(pos, 5);
		damien.ajouterItem(l);
		
		pos = new Coordonnees(7, 18);
		l = new Lingot(pos, 6);
		damien.ajouterItem(l);
		
		pos = new Coordonnees(8, 18);
		PetiteBombe p = new PetiteBombe(pos);
		damien.ajouterItem(p);

		pos = new Coordonnees(9, 18);
		p = new PetiteBombe(pos);
		damien.ajouterItem(p);
		pos = new Coordonnees(10, 18);
		p = new PetiteBombe(pos);
		damien.ajouterItem(p);
		pos = new Coordonnees(11, 18);
		p = new PetiteBombe(pos);
		damien.ajouterItem(p);
		pos = new Coordonnees(12, 18);
		p = new PetiteBombe(pos);
		damien.ajouterItem(p);
	}
	
	/** getDemo
	 * Permet de recuperer le niveau construit.
	 * @return le niveau construit
	 */
	public static Niveau getDamien() {
		NiveauDamien.Damien();
		return damien;
	}

}
