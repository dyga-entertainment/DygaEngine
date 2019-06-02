package java.MVC.View.MenuViews;

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import javax.swing.*;

import java.MVC.Controler.MainControler;
import java.MVC.Model.MainModelLegacy;
import java.MVC.View.GameViews.GameView;
import Data.audio.SoundSystem;

public class ViewManager {

    private JFrame windowFrame;

    private MainModelLegacy model;

    private static View[] views;

	public ViewManager(JFrame fenetre, MainModelLegacy model, MainControler gameControler) {
        this.windowFrame = fenetre;
        this.model = model;

        // Create and store all the possible views in the array
        this.views = new View[] {
            null,
            new HomeMenuView(this, gameControler),
            new CreditView(this, gameControler),
            new ProfilsView(this, gameControler),
            new SettingsView(this, gameControler),
            new WorldSelectionView(this, gameControler),
            new LevelSelectionView(this, gameControler),
            new LoadingView(this, gameControler),
        };

        refreshView();
        //ChangeView(ViewType.HomeMenu);

        // Basic layout


        // Not right now.
        //launchJukebox();

        // Useful later
        //loadProfil();

        //pistes.retirerPiste(numeroPisteAmbiance)
	}

	/*
    public ActionListener getContextTransitionActionListener() {
        // Create an uniform actionListener that will just change the context
        return actionEvent -> ChangeView((ContextTransitionButton) actionEvent.getSource());
    }

    public ActionListener getReturnContextActionListener() {
        // Create an uniform actionListener that will just change the context
        return actionEvent -> ReturnLastView();
    }

    public void ChangeView(ContextTransitionButton button) {
        ChangeView(ViewType.values()[button.getNextView().ordinal()]);
    }

    public void ChangeView(ViewType newView) {
        ChangeView(newView, false);
    }*/

    /*
    public void ReturnLastView() {
        ChangeView(this.lastVisitedViews.pop(), true);
    }

    public java.MVC.View getNextView() {
        return this.views[currentView.ordinal()];
    }*/

    public JFrame getFrame() {
	    return this.windowFrame;
    }

    public void launchGame(MainModelLegacy modele) {
        GameView gameVue = new GameView(modele);

        // Should call ChangeView here with the view created... ?



        // Clean up everything
        this.windowFrame.getContentPane().removeAll();

        // Finally add the gameVue to the windows itself
        this.windowFrame.getContentPane().setLayout(new GridLayout(1,1));
        this.windowFrame.getContentPane().add(gameVue);
        this.windowFrame.repaint();
        this.windowFrame.pack();
    }


    /**
     * This method should be called by the Controller
     * The java.MVC.View should read the modele an update itself.
     */
    public void refreshView() {
        //System.out.println("[Context Changement] from " + model.GetLastVisitedView().toString()  + " to " + model.GetCurrentView().toString());

        // Retrive possible parameters from the current view.
        // TODO Later
        /*
        Dictionary<String, Object> parameters = new Hashtable<>();
        if(this.views[currentView.ordinal()] != null) {
            parameters = this.views[currentView.ordinal()].getParameters();
        }*/

        // Clean up everything
        this.windowFrame.getContentPane().removeAll();

        // Prepare the next view to be printed
        View nextView = null;// = this.views[model.GetCurrentView()];

        // Give the parameters to the next view if needed.
        //nextView.setParameters(parameters);

        // Setup the view to be display
        /*
        nextView.setup(FenetrePrincipale.WINDOW_WIDTH, FenetrePrincipale.WINDOW_HEIGHT, FenetrePrincipale.CONTENT_PANEL_WIDTH,
            FenetrePrincipale.CONTENT_PANEL_HEIGHT);*/

        //
        // Could make a transition here ?
        //

        // Finally, Add the MainView to the windows
        this.windowFrame.getContentPane().add(nextView);
        this.windowFrame.repaint();
        this.windowFrame.pack();
    }


    ///////////////// OLD STUFFS /////////////////

	public void majFichierProfils(Data.levels.ProfilData p) {
		/** Re-ecriture dans le fichier apres modif */
		FileWriter fw2 = null;
		try {
			fw2 = new FileWriter("./ressources/gamedata/Profils.txt");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}     // ecrase le fichier s'il existe deja
		BufferedWriter bw2 = new BufferedWriter(fw2);
		try {
			bw2.write(p.inv());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			bw2.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

    //private JPanel[] IHMaffichage;
    //private Bandeau[] IHMBouton;
    //private IHMMenu IHMCourante;


    private Data.levels.ProfilData profils;
    private int numIHMCourante;

    /** Surcharge de la fonction paint() pour afficher notre image */
    /*
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("Repaint MainView called !");
    	//g.drawImage(bg,0,0,getSize().width, getSize().height, this);
    	this.views[this.currentView.ordinal()].repaint();

    	//this.IHMBouton[this.numIHMCourante].repaint();
    }*/

    private void launchJukebox() {
        SoundSystem pistes = new SoundSystem();
        int numeroPisteAmbiance = pistes.ajouterPiste(-1, "/ressources/Musiques/sons_of_skyrim.wav");
    }

    private void loadProfil() {
        String ligneLue = "";
        BufferedReader fluxEntree=null;
        try {
            URL url = getClass().getResource("/ressources/gamedata/Profils.txt");
            // Creation du flux vers le fichier texte
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
        this.profils = new Data.levels.ProfilData(ligneLue.split(" "));
    }
}
