package java.MVC.Model.bloc;

import java.Utils.helper.Images;
import java.MVC.Model.Actions.Timer;
import java.MVC.Model.jeu.Coordonnees;
import java.util.Observable;
import java.util.Observer;

/** Classe BlocTerre.
 * Implemente les blocs de terre.
 * @author Damien Maveyraud (modifications : Axel Grau)
 * @version 1.0
 *
 */
public class BlocTerre extends Bloc implements Observer {

    public BlocTerre(Coordonnees position, boolean trap){
        //super(position, ResourcesPaths.SPRITE_BLOCS_GROUND_PATH + "bloc_terre.png");
        super(position, "bloc_terre.png");
        this.concret = !trap;
    }
	
    public void reconstruction(){
        System.out.println("coucou");
        this.setConcret(true);
        //getSprite().modifierImage(ResourcesPaths.SPRITE_BLOCS_GROUND_PATH + "bloc_terre.png");
        getSprite().modifierImage("bloc_terre.png");
        mettreAJour();
    }
	
    /** destruction
     * Permet de notifier au modele que le bloc se detruit.
     */
    public void creuser(){
        //this.getSprite().playSequence(Images.blocExplosion, false);
        this.setConcret(false);
        GESTION_ANIM.ajouterPositionnableAnime(this, Images.blocExplosion);
        //Thread t = new Thread(new ThreadCreuser());
        //t.start();
    }
    
    @Override
    public void destruction() {
        super.destruction();
        Timer t = new Timer(8000, this);
        t.addObserver(this);
        new Thread(t).start();
    }
    
    public boolean isConcret(){
    	return this.concret;
    }

    @Override
    public void update(Observable o, Object arg) {
        this.reconstruction();
    }
}
