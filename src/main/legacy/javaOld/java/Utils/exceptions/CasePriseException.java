package java.Utils.exceptions;

/** Indique qu'une case de la matrice est deja prise.
 * 
 * @author Axel Grau
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
public class CasePriseException extends RuntimeException {

	public CasePriseException(String message) {
		super(message);
	}
	
}
