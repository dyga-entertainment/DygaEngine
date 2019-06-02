package Data.audio;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Damien Maveyraud
 * @author Grégoire Boiron
 */
public class SoundSystem {

	private List<ThreadPiste> listePistes;

	public SoundSystem() {
		this.listePistes = new ArrayList<ThreadPiste>();
	}

	/* Nom : ajouterPiste
	 * Semantique : Permet de lancer un thread jouant un fichier Data.audio sans bloquer le programme appelant
	 * et renvoyant le numero de piste ("PID du thread")
	 * Parametres : int loopNumber : entier représentant le nombre de fois que l'on souhaite répéter la piste
	 * 								 (-1 pour répéter à l'infini)
	 * 				String cheminFichierAudio : adresse du fichier Data.audio que l'on souhaite jouer
	 * Type retour : int : numéro de la piste lancée
	 */
	public int ajouterPiste(int loopNumber, String cheminFichierAudio) {
		// Clear the current song
		clearListePistes();
		// Create the next song with parameters
		ThreadPiste piste = new ThreadPiste(cheminFichierAudio, loopNumber);
		this.listePistes.add(piste);
		piste.start();
		return this.listePistes.indexOf(piste);
	}


	public void retirerPiste(int indexThread) {
	    this.listePistes.get(indexThread).stopLecture();
	}
	
	public void afficherPistes(){
		clearListePistes();
		String etat;
		for (int i = 0; i<this.listePistes.size(); i++){
			if (this.listePistes.get(i)!=null){
				if (this.listePistes.get(i).isAlive()){
					etat = "Lecture en cours";
				} else {
					etat ="Lecture terminée";
				}
				System.out.println("Piste #" + i + " : " + this.listePistes.get(i).getNomPiste() + etat);
			}
		}
		System.out.println();
	}
	
	public void clearListePistes(){
		for (int i = 0; i<this.listePistes.size(); i++){
			if (!this.listePistes.get(i).isAlive()){
				this.listePistes.remove(i);
			}
		}
	}

}
