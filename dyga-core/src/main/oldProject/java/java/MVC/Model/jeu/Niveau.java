package java.MVC.Model.jeu;

import java.MVC.Model.bloc.Bloc;
import java.MVC.Model.bloc.BlocTerre;
import java.MVC.Model.bloc.C;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/** Classe Niveau
 * Contient toutes les informations necessaires a la
 * construction d'un niveau.
 * 
 * @author Yann Tireau, Axel Grau, Grégoire Boiron
 * @version 2.0
 *
 */
public class Niveau {

	/** liste des blocs du niveau */
	private Queue<Bloc> blocs;
	/** liste des items du niveau */
	private Queue<Item> items;
	/** liste des coordonnes des MadMonks */
	private Queue<java.MVC.Model.jeu.Coordonnees> originesMadMonks;
	/** coordonnees du heros */
	private java.MVC.Model.jeu.Coordonnees origineHeros;
	/** image de fond */
	private String theme;
	/** score a atteindre pour finir le niveau */
	private int scoreFinal;
	/** bloc de fin de niveau */
	private BlocPortailFinNiveau theEnd;
	/** chemin de fichier Data.audio */
	private String cheminFichierAudioAmbiance;
	// private boolean estEditable;
	private String imageUrlPreview;

	public Niveau(boolean estEditable) {
		this.blocs = new LinkedList<Bloc>();
		this.items = new LinkedList<Item>();
		this.originesMadMonks = new LinkedList<java.MVC.Model.jeu.Coordonnees>();
		this.scoreFinal = 0;
		/* On met une musique par defaut */
		this.cheminFichierAudioAmbiance = "/ressources/Musiques/never_gonna_give_you_up.wav";
		// this.Identifiant = 0;
		// this.estEditable = estEditable;
	}

	public void setImageUrl(String url) {
		this.imageUrlPreview = url;
	}

	public void setMusiqueAmbiance(String cheminNewAudio){
		this.cheminFichierAudioAmbiance = cheminNewAudio;
	}
	
	public String getMusiqueAmbiance(){
		return this.cheminFichierAudioAmbiance;
	}

	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String fond) {
		this.theme = fond;
	}
	
	public int getScoreFinal() {
		return new Integer(this.scoreFinal);
	}
	
	public BlocPortailFinNiveau getTheEnd() {
		return this.theEnd;
	}

		public void remplirNiveau(C[][] pattern) {
		java.MVC.Model.jeu.Coordonnees pos;
		Bloc bloc;
		Item item;
		Random rd = new Random();
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 30; j++) {
                            pos = new java.MVC.Model.jeu.Coordonnees(j, i);
                            /* Si c'est un bloc a ajouter */
                            if(pattern[i][j].toString().startsWith("b")) {
                                switch(pattern[i][j]) { 
                                    case bx: bloc = new BlocBrique(pos);
                                        break;
                                    case bt: bloc = new BlocTerre(pos, false);
                                        break;
                                    case be: bloc = new BlocEchelle(pos);
                                        break;
                                    case bg: bloc = new BlocGlu(pos);
                                        break;
                                    case bf: bloc = new BlocBarreTraversee(pos);
                                        break;
                                    default: bloc = null;
                                }
                                this.ajouterBloc(bloc);
                            /* Si c'est un item a ajouter */
                            } else if(pattern[i][j].toString().startsWith("i")) {
                                switch(pattern[i][j]) { 
                                    case ib: item = new PetiteBombe(pos);
                                        break;
                                    case iB: item = new GrosseBombe(pos);
                                        break;
                                    case iz: item = new Gaz(pos);
                                        break;
                                    case ip: item = new Pioche(pos);
                                        break;
                                    case ig: item = new PotDeGlu(pos);
                                        break;
                                    case il: item = new Lingot(pos,rd.nextInt(7));
                                        this.scoreFinal += ((Lingot) item).getValeur();
                                        break;
                                    default: item = null;
                                }
                                this.ajouterItem(item);
                            /* Si c'est une objet special */
                            } else {
                                switch(pattern[i][j]) {
                                case pp: this.theEnd = new BlocPortailFinNiveau(pos);
                                    break;
                                case ph: this.origineHeros = pos;
                                    break;
                                case pm: this.originesMadMonks.add(pos);
                                    break;
                                default :
                                	break;
                                }
                            }
			}
		}
	}

	public void ajouterHeros(java.MVC.Model.jeu.Coordonnees pos) {
		this.origineHeros = new java.MVC.Model.jeu.Coordonnees(pos.getX(), pos.getY());
	}

	public void ajouterMadMonk(java.MVC.Model.jeu.Coordonnees pos) {
		this.originesMadMonks.add(pos);
	}

	public void ajouterBloc(Bloc bloc) {
		this.blocs.add(bloc);
	}

	public Queue<Bloc> recupererListeBlocs() {
		return this.blocs;
	}

	public Queue<Item> recupererListeItems() {
		return this.items;
	}

	public Queue<java.MVC.Model.jeu.Coordonnees> recupererPosMadMonk() {
		return originesMadMonks;
	}

	public java.MVC.Model.jeu.Coordonnees posHeros() {
		return origineHeros;
	}

	public void ajouterItem(Item item) {
		this.items.add(item);
	}

    public ViewPanel getApercu() {
		// Should return a image of the world ?
		//return new ViewPanel(ResourcesPaths.SPRITE_BACKGROUND_PATH + "background_custom.png");
		return new ViewPanel(this.imageUrlPreview);
    }
}
