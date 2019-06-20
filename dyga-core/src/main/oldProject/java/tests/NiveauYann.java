package tests;

import java.MVC.Model.jeu.Coordonnees;
import java.MVC.Model.jeu.Niveau;
import java.MVC.Model.bloc.Bloc;
import java.MVC.Model.bloc.BlocTerre;

public class NiveauYann {
	
	private static Niveau yann;
	
	/** Demo
	 * Permet de construite le niveau.
	 */
	private static void Yann(){
		yann = new Niveau(false);
		Coordonnees pos;
		Bloc courant;
		/* Bordures Horizontales */
		for (int i=0; i<30; i++) {
			pos = new Coordonnees(i, 0);
			courant = new BlocTerre(pos, false);
			yann.ajouterBloc(courant);
			pos = new Coordonnees(i, 19);
			courant = new BlocTerre(pos, false);
			yann.ajouterBloc(courant);
		}
		/* Bordures verticales */
		for (int i=1; i<19; i++) {
			pos = new Coordonnees(0, i);
			courant = new BlocTerre(pos, false);
			yann.ajouterBloc(courant);
			pos = new Coordonnees(29, i);
			courant = new BlocTerre(pos, false);
			yann.ajouterBloc(courant);
		}
	}
	
	/** getDemo
	 * Permet de recuperer le niveau construit.
	 * @return le niveau construit
	 */
	public static Niveau getYann() {
		NiveauYann.Yann();
		return yann;
	}

}
