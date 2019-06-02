package java.MVC.View;

import java.MVC.Controler.MainControler;
import java.MVC.Model.MainModelLegacy;
import java.MVC.View.MenuViews.ViewManager;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class FenetrePrincipale extends JFrame {

	public static final int WINDOW_WIDTH = 950;
	public static final int WINDOW_HEIGHT = 800;

	public static final int CONTENT_PANEL_WIDTH = 800;
	public static final int CONTENT_PANEL_HEIGHT = 800;

	public FenetrePrincipale() {
		setupWindow();

		// Init the modele of the game
		MainModelLegacy modele = new MainModelLegacy();

		// Init the controller of the game
		// Init the link Controller -> java.MVC.Model in order to update the model if an input has been made.
		MainControler gameControler = null;//new MainControler(modele);

		// Add the link java.MVC.View -> Controller when the user use inputs.
		this.addKeyListener(gameControler);
		this.addMouseListener(gameControler);
		this.requestFocus(); // Needed ?

		// Init the view of the game with the windows (??)
		// and the controller to be able to add him on buttons for example
		java.MVC.View.MenuViews.ViewManager gameVue = new ViewManager(this, modele, gameControler);

		// Add the link java.MVC.Controler -> java.MVC.View to notify the view an input as been made and that, maybe the model changed.
		//gameControler.addView(gameVue);

		// useless ??
		//modele.addObserver(gameVue);

		launchWindow();
	}

	public void setupWindow() {
		this.setTitle("Lode Runner");
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void launchWindow() {
		//this.setResizable(false);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				requestFocus();
			}
		});
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}