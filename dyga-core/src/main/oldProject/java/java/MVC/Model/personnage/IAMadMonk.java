package java.MVC.Model.personnage;
import java.MVC.Model.jeu.Coordonnees;

/** Interface de l'Intelligence Artificielle des MadMonks
 * 
 * @author	Axel Grau
 * @version	0.1
 *
 */
public interface IAMadMonk {
	
	/** majDestination
	 * Permet de mettre a jour la position que le MadMonk souhaite attendre.
	 * Suivant son intelligence, la mise a jour se fait de differentes
	 * manieres.
	 * @param cible les coordonnees de la cible.
	 * @return Vrai si la destination a ete mise a jour, Faux sinon.
	 */
	boolean majDestination(Coordonnees cible);
	
	/** itineraire
	 * Permet de calculer la prochaine position que le MadMonk doit atteindre
	 * pour se rapprocher du point qu'il souhaite atteindre.
	 * @param origine coordonnees de l'origine
	 */
	Coordonnees itineraire(Coordonnees origine);
	
	/** relacherLingot
	 * Permet de relacher le lingot.
	 */
	boolean relacherLingot();

}
