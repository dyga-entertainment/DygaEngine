package java.MVC.Model.item;

import java.MVC.Model.jeu.Coordonnees;
import java.MVC.Model.jeu.Positionnable;

public class Gaz extends Gadget {

    int[][] rayonActionGaz = {{1,0},{2,0},{3,0}};
    int delaiGaz = 500;

    public Gaz(Coordonnees position){
        //super(position, ResourcesPaths.SPRITE_ITEMS_WEAPONS_PATH + "gaz.png");
        super(position, "gaz.png");
        this.rayonAction = rayonActionGaz;
        this.delai = delaiGaz;
        this.actif = false;
        this.usageUnique = false;
    }

    /* Nom : utiliser
     * S�mantique : Proc�dure g�rant l'utilisation d'un Gaz par le joueur
     * Param�tres : positionX : position du joueur suivant X lors de l'utilisation
     * 				positionY : position du joueur suivant Y lors de l'utilsiation
     * Type retour : /
     */
    public void utiliser() {
        this.setActif(true);
        /* TODO : gerer les blocs gazes en fonction de l'orientation du joueur */
        Positionnable.GESTION_ANIM.ajouterPositionnableAnime(this, java.Utils.helper.Images.gaz);
        /*for (int i[] : this.rayonAction){
                // NOTIFIER LE MODELE
                //this.modele.getMatrice()[0][positionX + i[0]][positionY+i[1]].engaze();
        }
        this.actif = false;*/
    }
}
