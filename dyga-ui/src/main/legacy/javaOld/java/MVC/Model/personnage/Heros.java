package java.MVC.Model.personnage;

import java.MVC.Model.item.Gadget;
import java.MVC.Model.item.Item;
import java.Utils.exceptions.InventaireVideException;
import java.MVC.Model.item.PetiteBombe;
import java.MVC.Model.jeu.Coordonnees;

/** Classe du Heros du java.MVC.Model.jeu.
 * 
 * @author	Axel Grau
 * @version	0.1
 *
 */
public class Heros extends Personnage {
	
	/** invPetitesBombes
	 * Inventaire des petites bombes du heros
	 */
	private int invPetitesBombes;
	/** inventaire 
	 * Inventaire de gadget du heros
	 */
	private Gadget inventaire;
	
	/**
	 * Score total du joueur dependant des lingots collectés
	 */
	private int score;
	
	
	public Heros(Coordonnees position, String img, int vitesse) {
            super(position,img,vitesse);
            this.invPetitesBombes = 0;
            this.inventaire = null;
            this.score = 0;
	}

	public void utiliserInv(Gadget element) throws InventaireVideException {
            if (element instanceof PetiteBombe) {
            //if(utiliser instanceof Object) {
                /* si l'objet a utiliser est une petite bombe */
                if (this.invPetitesBombes == 0) {
                    /* s'il n'y a pas de petites bombes disponible
                     *  => exception */
                    throw new InventaireVideException("pas de petite bombe dans l'inventaire");
                } else {
                    /* sinon on decroit le nombre de petites bombes */
                    this.invPetitesBombes -= 1;
                    this.setChanged();
                    this.notifyObservers("bombe");
                }
            } else {
                /* sinon c'est un autre gadget */
                if (this.inventaire != null) {
                    /* si c'est le gadget de l'inventaire
                     * on l'utilise */
                	this.inventaire.utiliser();
                } else {
                    /* ce n'est pas le bon gadget */
                	throw new InventaireVideException("pas de gadget de l'inventaire");
                }
                this.setChanged();
                this.notifyObservers("gadget");
            }
        }

    @Override
    public void deposer() {
        /* On le lache a l'endroit du heros */
        this.inventaire.setPosition(this.getPosition());
        /* Le rajouter au niveau */
        //????
    }

    public boolean creuser(boolean direction) {
        if (direction) {
            /* true = droite */
            /* essayer de creuser a droite */
            return true;
        } else {
            /* false  = gauche */
            /* essayer de creuser a gauche */
            return false;
        }
    }
	
    /** ramasser 
     * 
     * @param item l'item que personnage ramasse.
     * 
     */
    @Override
    public void ramasser(Item item) {
        if (item instanceof PetiteBombe && this.invPetitesBombes<5) {
            // A Remplacer PetiteBombe Object
            //if (item instanceof Object) {
            /* si c'est une petite bombe, elle vient alimenter l'inventaire 
             * des petites bombes.
             */
            this.invPetitesBombes += 1;
            this.setChanged();
            this.notifyObservers("bombe");
        } else {
            /* sinon c'est un gadget normal */
            if (this.inventaire == null) {
                /* si l'inventaire est vide, on rempli l'inventaire */
                this.inventaire = (Gadget) item;
                /* notifier le ramassage */
            } else {
                /* sinon on depose le gadget possede, et on prend celui a terre */
                this.deposer();
                /* notifier le depot */
                /* on ramasse le gadget voulu */
                this.inventaire = (Gadget) item;
                /* notifier le ramassage */
            }
            this.setChanged();
            this.notifyObservers("gadget");
        }
    }

    public void augmenterScore(int points) {
        this.score += points;
//        System.out.println(this.score);
        this.setChanged();
        this.notifyObservers("lingot");
    }
    
    public int getScore() {
    	return new Integer(this.score);
    }

	public int getInvPetitesBombes() {
		return invPetitesBombes;
	}

	public Gadget getInventaire() {
		return inventaire;
	}
}
