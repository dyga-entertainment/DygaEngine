package java.Utils.exceptions;

/** Indique une erreur dans l'utilisation d'un gadget.
 * (mauvais gadget)
 * 
 * @author Axel Grau
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
public class ErreurGadgetException extends RuntimeException {
	
	public ErreurGadgetException(String message) {
		super(message);
	}
}