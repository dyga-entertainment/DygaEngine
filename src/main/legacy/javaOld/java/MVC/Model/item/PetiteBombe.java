package java.MVC.Model.item;

import java.MVC.Model.jeu.Positionnable;
import java.MVC.Model.jeu.Coordonnees;

public class PetiteBombe extends Gadget {

	/*
	 * Le rayon d'action contient les blocs affectes et sont identifies par leur
	 * coordonnees relatives par rapport a la position du personnage utilisateur
	 */
	int[][] rayonActionPetiteBombe = {{0,0},{0,1},{0,2},{1,1},{1,0},{2,0},{1,-1},{0,-1},{0,-2},{-1,-1},{-1,0},{-2,0},{-1,1}};
	int delaiPetiteBombe = 3000;
	
	public PetiteBombe(Coordonnees position) {
		//super(position, ResourcesPaths.SPRITE_ITEMS_WEAPONS_PATH + "gadget_petite_bombe.png");
		super(position, "gadget_petite_bombe.png");
		this.rayonAction = rayonActionPetiteBombe;
		this.delai = delaiPetiteBombe;
		this.usageUnique = true;
		this.actif = false;
	}
	
	/* Nom : utiliser
	 * Semantique : Procedure gerant l'utilisation d'une petite bombe en fonction de la position du joueur la posant
	 * Parametres : positionX : position suivant X du joueur au moment ou il pose la bombe
	 * 				positionY : position suivant Y du joueur au moment ou il pose la bombe
	 * Type retour : /
	 */
	public void utiliser(){
		/* TODO : gerer le sprite de bombe allumee a la position designee */
		this.actif = true;
                Positionnable.GESTION_ANIM.ajouterPositionnableAnime(this, java.Utils.helper.Images.petiteBombeAllumee);
		/* Attente du delai d'explosion */
		/*
                try {
			Thread.sleep(this.delai);
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}*/
		/* On detruit chaque bloc contenu dans le rayon d'action */
		//for (int i[] : this.rayonAction) {
			/* Destruction des blocs de terre */
			// NOTIFIER LE MODELE
			/*
			courant = this.modele.getMatrice()[0][i[0]+positionX][i[1]+positionY];
			if courant = instanceof(BlocTerre){
				courant.destruction();
			}
			*/
		//}
		/* On enleve la bombe du modele maintenant qu'elle a explose */ 
		//this.actif = false;
		//this.destruction();
	}
        
        public Coordonnees[] getBlocsExploses(){
            Coordonnees[] BlocsExploses = new Coordonnees[this.rayonAction.length];
            for (int i=0; i<rayonAction.length; i++){
                BlocsExploses[i] = new Coordonnees(rayonAction[i][0] + this.getPosition().getX(), rayonAction[i][1] + this.getPosition().getY());
            }
            return BlocsExploses;
	}
}
