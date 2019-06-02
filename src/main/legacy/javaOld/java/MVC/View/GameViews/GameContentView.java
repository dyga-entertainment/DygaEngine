package java.MVC.View.GameViews;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

import java.MVC.Model.MainModelLegacy;
import java.MVC.Model.item.Item;
import java.MVC.Model.jeu.Sprite;
import java.MVC.Model.bloc.Bloc;
import java.MVC.Model.personnage.Heros;
import java.MVC.Model.personnage.Personnage;

public class GameContentView extends JPanel implements Observer {
	
    private final MainModelLegacy modele;
    private BufferedImage image;
    private final Graphics2D gr ;
    private final JPanel ecranJeu;
    private final JPanel hud;
    private final int widthJeu;
    private final int heightJeu;
    private final int widthHud;
    private final int heightHud;
	
    public GameContentView(MainModelLegacy m) {
        this.modele = m;
        this.ecranJeu = new JPanel();
        this.hud = new JPanel();

        java.Utils.helper.Images i = new java.Utils.helper.Images();
        java.Utils.helper.ImagesMadMonk j = new java.Utils.helper.ImagesMadMonk();

        //this.bloc = new LinkedList<Heros>();
        this.widthJeu = 960;
        this.heightJeu = 640;
        this.widthHud = 960;
        this.heightHud = 63;
        //this.image = i.getImageFromPath(ResourcesPaths.SPRITE_BACKGROUND_PATH + "background_jungle.png");
        this.image = i.getImageFromPath("background_jungle.png");

        //this.addSprite("Images/mini1.png", 80, 100);
        this.gr = this.image.createGraphics() ;
        this.gr.setColor(new Color(0,0,0,0)) ;
        gr.fillRect(0,0,this.widthJeu, this.heightJeu);  

        this.modele.startThread();
    }

    public void paintComponent(Graphics g) {
        if (image != null) {
                    g.drawImage(this.image, 0, 0, this);
            for (Bloc[] her : this.modele.AccesNiveau()) {
                for (Bloc b : her) {
                    if (b != null) {
                        b.getSprite().dessine(g, this);
                    }
                }
            }
            for (Item[] her : this.modele.AccesObjets()) {
                for (Item i : her) {
                    if (i != null) {
                        i.getSprite().dessine(g, this);
                    }
                }
            }
            // this.modele.getJoueur(0).getSprite().dessine (g, this) ;
            for (Personnage cour : this.modele.getListPerso()) {
                if(cour instanceof Heros) {
                    cour.getSprite().dessine(g, this);
                    if (cour.isMouvement()) {
                        cour.getSprite().jouerAnimation(g, this,5/* Images.moveLeft.length */);                           
                    }					
                /* Le personnage est un MadMonk */
                } else {
                    if(cour.estVivant()) {
                        cour.getSprite().dessine(g, this);
                        if (cour.isMouvement()) {
                            cour.getSprite().jouerAnimation(g, this, 5/* Images.moveLeft.length */);
                        }
                    }
                }	
				
            }
        }
       this.hud.repaint();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof MainModelLegacy) {
            if(!arg.equals("lingots")) {
                /* On reecoute les blocs */
                for(Bloc[] h : this.modele.AccesNiveau()) {
                    for(Bloc b : h) {
                        if(b != null) {
                            b.addObserver(this);
                            b.getSprite().addObserver(this);
                        }
                    }
                }
                /* On reecoute les personnages */
                for (Personnage cour : this.modele.getListPerso()) {
                    cour.addObserver(this);
                    cour.getSprite().addObserver(this);
                }
                java.Utils.helper.Images i = new java.Utils.helper.Images();
                this.image = i.getImageFromPath((String)arg);
                this.modele.startThread();
                this.repaint(0, this.widthJeu, this.heightJeu, this.widthJeu, this.heightJeu);
            }
        } else if (o instanceof Personnage) {
            try {
                int[] tab = (int[]) arg;
                this.repaint(0, tab[0], tab[1], this.widthJeu, this.heightJeu);
            } catch(ClassCastException e) {}
        } else if (o instanceof Sprite) {
            Sprite h = (Sprite) o;
            int[] tab = (int[]) arg;
            this.repaint(0, tab[0], tab[1], this.widthJeu, this.heightJeu);
        } else if (o instanceof Bloc) {
            Bloc h = (Bloc) o;
            int[] tab = (int[]) arg;
            this.repaint(0, tab[0], tab[1], this.widthJeu, this.heightJeu);
        } else {
            int[] tab = (int[]) arg;
            this.repaint(0, tab[0], tab[1], this.widthJeu, this.heightJeu);
        }
    }
}
