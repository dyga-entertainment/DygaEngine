package java.MVC.View.GameViews;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

import java.MVC.Model.MainModelLegacy;
import java.MVC.Model.bloc.Bloc;
import java.MVC.Model.personnage.Personnage;

public class GameView extends JPanel implements Observer {

    private GameContentView vue;
    private HeadUpDisplay hud;

    public GameView(MainModelLegacy m) {
        // Initialize the game vue
        this.vue = new GameContentView(m);

        // Initialize the HUD
        this.hud = new HeadUpDisplay("This is a name", 10, 0);
        //this.hud.setLayout(new GridLayout(1,2));  // ???

        this.setLayout(new BorderLayout());

        /* Laisons des objets entre eux */
        for(Bloc[] blocs : m.AccesNiveau()) {
            for(Bloc b : blocs) {
                if(b != null) {
                    // Two observers ?
                    b.addObserver(this);
                    b.getSprite().addObserver(this);
                }
            }
        }
        for (Personnage cour : m.getListPerso()) {
                cour.addObserver(this);
                cour.getSprite().addObserver(this);
        }
        this.add(this.vue, BorderLayout.CENTER);
        this.add(this.hud, BorderLayout.SOUTH);
        this.repaint();
    }
	
    public void paint(Graphics g) { 
    	//g.drawImage(bg,0,0,getSize().width, getSize().height, this); 
    	this.hud.repaint();
    	this.vue.repaint();
    }

    @Override
    public void update(Observable o, Object arg) {
        this.hud.update(o, arg);
        this.vue.update(o, arg);
    }
	
}
