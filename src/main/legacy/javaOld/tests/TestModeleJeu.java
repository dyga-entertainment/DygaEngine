package tests;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

import java.MVC.Model.MainModelLegacy;
import java.MVC.View.GameViews.GameView;

public class TestModeleJeu {
	
	static JFrame f;

	public static void main(String[] args) {
		
		TestModeleJeu.f = new JFrame();
		
		//Jukebox pistes = new Jukebox();
		//pistes.ajouterPiste(-1, "/ressources/Musiques/spooky_scary_skeletons.wav");

		/* Creation du modele a partir du Niveau */
		MainModelLegacy modele = new MainModelLegacy();
		modele.chargerNiveau(0, 0);
		/* Creation de la vue */
		GameView vue = new GameView(modele);
		
		modele.addObserver(vue);
		/* Creation du java.MVC.Controler */
		//MainControler c = new MainControler(vue, modele);
		
		f.setContentPane(vue);
		//f.addKeyListener(c);
		f.setSize(960, 667);
		f.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				TestModeleJeu.f.requestFocus();
			}
		});
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
