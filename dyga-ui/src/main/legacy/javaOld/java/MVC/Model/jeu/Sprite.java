package java.MVC.Model.jeu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import javax.imageio.ImageIO;
import java.net.URL;

import java.MVC.View.GameViews.GameContentView;

/**
 *   Un sprite est une image qui a vocation a se deplacer.
 *   (bas niveau)
 */
public class Sprite extends Observable {

    // Position, dimension et image
    private java.MVC.Model.jeu.Coordonnees position ;
    public int width, height ;
    public BufferedImage image ;

    // ViewPanel ou se trouve le sprite
    //private GameContentView vue;

    // Animation du robot : sequence d'images
    private BufferedImage[] seq ;

    // Index dans la sequence. -1 signifie qu'on ne la joue pas.
    private int indexSeq ; 

    // Nombre d'expositions de chaque image de la sequence
    private final int dureeExposition = 6 ;
    private int compteurExposition ;

    private boolean repeat ;

    /** Renvoie la largeur du sprite */
    public int getLarg() { return this.width ; }

    /** Renvoie la hauteur du sprite */
    public int getHaut() { return this.height ; }

    public Sprite (String img, java.MVC.Model.jeu.Coordonnees xy) {
    	this.position = xy;
    	this.seq = null ;
    	this.indexSeq = -1 ;
        this.modifierImage(img);
    }

    public void modifierImage(String lien) {
    	try {
            URL url = Thread.currentThread().getContextClassLoader().getResource(lien);
            this.modifierImage(ImageIO.read(url));
    	} catch (IOException e) {
            e.printStackTrace();
    	}
    }
    
    public void modifierImage(BufferedImage img) {
        this.image = img;
        this.notifierVueChangement();
    }
    
    public void setPosition(int x, int y) {
    	this.position.setX(x);
    	this.position.setY(y);
    }
    
    public void moveTo(java.MVC.Model.jeu.Coordonnees xy) {
        this.notifierVueChangement();
        this.position.setX(xy.getX());
        this.position.setY(xy.getY());
        this.notifierVueChangement();
    }

    public void playSequence(BufferedImage[] seq, boolean repeat) {
	this.seq = seq ;
	this.indexSeq = 0;
	this.compteurExposition = this.dureeExposition ;
	this.repeat = repeat ;
    }

    public void dessine(Graphics g, GameContentView im) {
    	g.drawImage(this.image, this.position.getX(), this.position.getY(), im) ;
    }

	public void jouerAnimation(Graphics g, GameContentView im, int length) {
		if (this.indexSeq >= 0 && this.indexSeq < this.seq.length) {
		    this.image = this.seq[this.indexSeq] ;

		    this.width  = this.image.getWidth();
		    this.height = this.image.getHeight();

		    if (this.compteurExposition-- <= 0) {
			this.compteurExposition = this.dureeExposition ;
			this.indexSeq ++ ;
		    }

		    if (this.indexSeq >= this.seq.length && this.repeat) {
			this.indexSeq = 0;
		    }
		}
		g.drawImage(this.image, this.position.getX(), this.position.getY(), im) ;
	}
        
        public boolean finSequence() {
            return (!this.repeat) && this.indexSeq == 0;
        }
	
	public void notifierVueChangement() {
		this.setChanged();
		this.notifyObservers(new int[] {this.position.getX(), this.position.getY()});
		this.clearChanged();
	}
}
