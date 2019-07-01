package java.MVC.Model.bloc;

import java.MVC.Model.jeu.Coordonnees;
import java.MVC.Model.jeu.Positionnable;

/** Classe abstraire Bloc
 * 
 * @author Damien Maveyaud, (modifications : Axel Grau)
 * @version 0.1
 *
 */
public abstract class Bloc extends Positionnable {
	
    /* ***** ***** ATTRIBUTS ***** ***** */
    /* Un attribut estConcret permettra de determiner si les blocs entrent en collision avec le joueur
     * ou si ce dernier peut les traverser */
    protected boolean concret;
    boolean seDetruit;

    /** Construteur.
     * 
     * @param position position du bloc dans la matrice
     * @param img image du bloc
     */
    public Bloc(Coordonnees position, String img) {
        super(position, img);
        /* par default le bloc n'est pas traversable */
        this.concret = true;
        this.seDetruit = false;
        
        // TODO Auto-generated constructor stub
    }
    
    public boolean isConcret() {
        return concret;
    }

    public void setConcret(boolean estConcret) {
        this.concret = estConcret;
    }

    /** destruction
     * Permet de notifier au modele que le bloc se detruit.
     */
    public void destruction() {
        this.setConcret(false);
        this.setSprite("/ressources/Images/bloc_vide.png");
    }
    /* utiliser la fait que c'est un observable */
    /*this.seDetruit = true;
    int i = 1;
    //while(i < 5) {
        //this.getSprite().modifierImage("/ressources/Images/bloc_terre_destruction"+i+".png");
        //i++;
        System.out.println(i);
        this.mettreAJour();
        /*
        try {
            System.out.println("Pause");
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(Bloc.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    //}
    //this.modele.retirerBloc(this.positionX, this.positionY)

/*    public boolean seDetruit() {
        return this.seDetruit;
    }*/
    /*
    public void setDetruit(boolean detruit) {
        this.seDetruit = detruit;
    }*/

    
}
