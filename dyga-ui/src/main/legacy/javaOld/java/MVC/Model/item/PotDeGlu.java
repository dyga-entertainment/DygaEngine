package java.MVC.Model.item;

import java.MVC.Model.jeu.Coordonnees;

public class PotDeGlu extends Gadget {
	
	int[][] rayonActionPotDeGlu = {{1,-1},{2,-1},{3,-1}};
	int delaiPotDeGlu = 500;
	
	public PotDeGlu(Coordonnees position){
		super(position, "Images/PotDeGlu.png");
		this.rayonAction = rayonActionPotDeGlu;
		this.delai = delaiPotDeGlu;
		this.actif = false;
		this.usageUnique = true;
	}
	
	/* Nom : utiliser
	 * Semantique : Procedure gerant l'utilisation d'un pot de glu par le joueur
	 * Parametres : positionX : position du joueur suivant X lors de l'utilisation
	 * 				positionY : position du joueur suivant Y lors de l'utilsiation
	 * Type retour : /
	 */
	public void utiliser(int positionX, int positionY){
		this.actif = true;
		/* TODO : gerer les blocs englues en fonction de l'orientation du joueur */
		for (int i[] : this.rayonAction) {
			/* TODO : gerer l'inexistence des blocs concernes (la glue doit tomber) */
			/*
			courant = new BlocGlue(positionX + i[0],positionY+i[1]);
			this.modele.getMatrice().setBloc(courant);
			*/
		}
		this.actif = false;
	}

    @Override
    public void utiliser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
