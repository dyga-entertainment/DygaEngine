package java.Utils.exceptions;

/** Indique un inventaire vide lors l'utilisation d'un objet d'inventaire.
 * 
 * @author Axel Grau
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
public class InventaireVideException extends RuntimeException {
	
	public InventaireVideException(String message) {
		super(message);
	}

}
