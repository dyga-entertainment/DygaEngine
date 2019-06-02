package java.MVC.Model.personnage;

import java.MVC.Model.jeu.Coordonnees;

/** Intelligence Artificielle Naive des MadMonks
 * 
 * @author	Axel Grau
 * @version	0.1
 *
 */
public class IANaive implements IAMadMonk {

	/* ***** ATTRIBUT ***** */
	private Coordonnees but;
	
	public IANaive(Coordonnees base) {
		this.but = base;
	}
	
	public boolean majDestination(Coordonnees cible) {
		boolean retour = false;
		but = cible;
		retour = true;
//		System.out.println(but.getX()+"  "+but.getY());
//		System.out.println(cible.getX()+"  "+cible.getY());
//		System.out.println(distance(but, cible));
//		if (distance(but, cible) > 3) {
//			System.out.println("MAJ DESTINATION MAD MONKKKK");
//			this.but = new Coordonnees(cible.getX(),cible.getY());
//			retour = true;
//		}
		return retour;
	}
	
	/** itineraire
	 * L'IA est naive, elle se contente de chercher la distance minimale
	 * entre le depart et l'arrivee puis renvoie la position qui reduit
	 * cette distance.
	 */
	@Override
	public Coordonnees itineraire(Coordonnees origine) {
		/* _|1|_ */
		/* 3|0|4 */
		/* _|2|_ */
		Coordonnees posSuiv = new Coordonnees(origine.getX(), origine.getY());
		if (origine.getX() != this.but.getX()) {
                    posSuiv.setX(this.but.getX());
		}
		if (origine.getY() != this.but.getY()) {
                    posSuiv.setY(this.but.getY());
		}
		return posSuiv;
	}

	@Override
	public boolean relacherLingot() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
