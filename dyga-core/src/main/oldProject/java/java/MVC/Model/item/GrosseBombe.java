package java.MVC.Model.item;


import java.MVC.Model.jeu.Coordonnees;

public class GrosseBombe extends Gadget {
	
	/* Le rayon d'action contient les blocs affectes et sont identifies par leur coordonnees
	 * relatives par rapport a la position du personnage utilisateur */
	int[][] rayonActionGrosseBombe = {{0,0},{0,1},{0,2},{1,1},{1,0},{2,0},{1,-1},{0,-1},{0,-2},{-1,-1},{-1,0},{-2,0},{-1,1}};
	int delaiGrosseBombe = 3000;
	
	
	public GrosseBombe(Coordonnees position) {
		super(position, "Images/GrosseBombe.png");
		this.rayonAction = rayonActionGrosseBombe;
		this.delai = delaiGrosseBombe;
		this.usageUnique = true;
		this.actif = false;
	}
	
	/* Nom : utiliser
	 * Semantique : Procedure gerant l'utilisation d'une grosse bombe en fonction de la position du joueur la posant
	 * Parametres : positionX : position suivant X du joueur au moment ou il pose la bombe
	 * 				positionY : position suivant Y du joueur au moment ou il pose la bombe
	 * Type retour : /
	 */
	public void utiliser(int positionX, int positionY){
		/* TODO : gerer le sprite de bombe allumee a la position designee */
		this.actif = true;
		/* Attente du delai d'explosion */
		try {
			Thread.sleep(this.delai);
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		/* On detruit chaque bloc contenu dans le rayon d'action */
		for (int i[] : this.rayonAction) {
			/* Destruction des blocs */
			// NOTIFIER LA MATRICE
			//this.modele.getMatrice()[0][i[0]+positionX][i[1]+positionY].destruction();
			/* Destruction des objets */
			//this.modele.getMatrice()[1][i[0]+positionX][i[1]+positionY].destruction();
		}
		/* On enleve la bombe du modele maintenant qu'elle a explose */ 
		this.actif = false;
		this.destruction();
	}

    @Override
    public void utiliser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
