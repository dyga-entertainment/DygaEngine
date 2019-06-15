package tests;

import java.MVC.Model.jeu.Coordonnees;
import java.MVC.Model.jeu.Niveau;
import java.MVC.Model.bloc.Bloc;
import java.MVC.Model.bloc.BlocTerre;

public class NiveauDemo {
	
	private static Niveau demo;
	
	/** Demo
	 * Permet de construite le niveau.
	 */
	private static void Demo(){
		demo = new Niveau(false);
		Coordonnees pos;
		Bloc courant;
		/* Bordures Horizontales */
		for (int i=0; i<30; i++) {
			pos = new Coordonnees(i, 0);
			courant = new BlocTerre(pos, false);
			demo.ajouterBloc(courant);
			pos = new Coordonnees(i, 19);
			courant = new BlocTerre(pos, false);
			demo.ajouterBloc(courant);
		}
		/* Bordures verticales */
		for (int i=1; i<19; i++) {
			pos = new Coordonnees(0, i);
			courant = new BlocTerre(pos, false);
			demo.ajouterBloc(courant);
			pos = new Coordonnees(29, i);
			courant = new BlocTerre(pos, false);
			demo.ajouterBloc(courant);
		}
		/* Lignes des blocs */
		for (int i=1; i<29; i++) {
			pos = new Coordonnees(i, 2);
			courant = new BlocTerre(pos, false);
			demo.ajouterBloc(courant);
			pos = new Coordonnees(i, 5);
			courant = new BlocTerre(pos, false);
			demo.ajouterBloc(courant);
			pos = new Coordonnees(i, 8);
			courant = new BlocTerre(pos, false);
			demo.ajouterBloc(courant);
			pos = new Coordonnees(i, 9);
			courant = new BlocTerre(pos, false);
			demo.ajouterBloc(courant);
			pos = new Coordonnees(i, 10);
			courant = new BlocTerre(pos, false);
			demo.ajouterBloc(courant);
			pos = new Coordonnees(i, 13);
			courant = new BlocTerre(pos, false);
			demo.ajouterBloc(courant);
			pos = new Coordonnees(i, 16);
			courant = new BlocTerre(pos, false);
			demo.ajouterBloc(courant);
		}
	}
	
	/** getDemo
	 * Permet de recuperer le niveau construit.
	 * @return le niveau construit
	 */
	public static Niveau getDemo() {
		NiveauDemo.Demo();
		return demo;
	}

}
