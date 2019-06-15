package java.MVC.Model;

import Data.audio.SoundSystem;
import java.MVC.Model.bloc.*;
import java.MVC.Model.item.*;
import java.Utils.exceptions.BlocNonCreusableException;
import java.Utils.exceptions.InventaireVideException;
import java.MVC.Model.Actions.ThreadAnimation;
import java.MVC.Model.bloc.Bloc;
import java.MVC.Model.bloc.BlocTerre;
import java.MVC.Model.personnage.Heros;
import java.MVC.Model.personnage.MadMonk;
import java.MVC.Model.personnage.Personnage;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


/**
 * Classe MainModelLegacy. Classe qui represente le niveau de java.MVC.Model.jeu actuel. Permet de
 * gerer les interactions entre les differentes classes en java.MVC.Model.jeu.
 * @author Gregoire Boiron
 * @author Axel Grau
 * @version 1.8
 *
 */
public class MainModelLegacy extends Observable {

	private java.MVC.Model.jeu.Carte matrice;              // matrice de java.MVC.Model.jeu

	private List<Personnage> personnages;
	private List<java.MVC.Model.jeu.Monde> mondes;

    private SoundSystem audioTracks; // ?? List<son> ??
    private int pisteAmbiance;

	// booleens de direction ???
	private boolean up, down, left, right;
	// threaddy mercury de java.MVC.Model.jeu utile ?
	private Thread LodeRunner;

	private boolean fini;
	private int indiceMondeCourant = 0;
	private int indiceNiveauCourant = 0;

	// score a atteindre pour le niveau courant
	private int scoreNivCourant;

	// le portail de fin de niveau ??
	private BlocPortailFinNiveau sortie;


	// le theme actuel ???
	//private String themeActuel;
	/* ***** ****** ********* ***** ***** */

	private MenuModel menuModel;

	/**
	 * Constructeur Permet de construire le Modele du Jeu : initialiser tous les
	 * mondes et niveaux disponibles.
	 */
	public MainModelLegacy() {

		this.menuModel = new MenuModel();

		//-------- java.Main.Game --------

		// initialisation des listes
		this.personnages = new ArrayList<Personnage>();
		this.mondes = new ArrayList<java.MVC.Model.jeu.Monde>();

		// initialisation de la carte avec la taille des tableaux
		this.matrice = new java.MVC.Model.jeu.Carte(20, 30);

		// On remplit la liste de monde
		// this.mondes.add(MondeSnow.Snow());
		this.mondes.add(Data.levels.MondeDemonstration.LoadDemoWorld());
		//this.mondes.add(MondeJungle.Jungle());
		// Création du jukebox
		this.audioTracks = new SoundSystem();
		// le modele est pret
	}

	public void chargerNiveau() {
		fini = false;
		/* On recupere le niveau voulu */
		java.MVC.Model.jeu.Niveau courant = this.mondes.get(this.indiceMondeCourant).getNiveau(this.indiceNiveauCourant);
		/* On construit la matrice du niveau */
		this.construireMatrice(courant);
		/* On charge le heros */
		java.MVC.Model.jeu.Coordonnees c = new java.MVC.Model.jeu.Coordonnees(courant.posHeros().getX(), courant.posHeros().getY());
		//this.ajouterHeros(new Heros(c, ResourcesPaths.ANIM_PLAYER_IDLE_PATH + "hero_fry_stop_left.png", 2));
		/* On charge les MadMonks */
		for (java.MVC.Model.jeu.Coordonnees cour : courant.recupererPosMadMonk()) {
			c = new java.MVC.Model.jeu.Coordonnees(cour.getX(), cour.getY());
			/*this.ajouterMadMonk(new MadMonk(c, ResourcesPaths.ANIM_ENEMY_IDLE_PATH + "mad_monk_stop_left.png", 1, this.matrice, 0),
					getHeros());*/
		}
		/* On retient le theme de fond */
		//this.themeActuel = ;
		/* On lance le java.MVC.Model.jeu */
		this.LodeRunner = new Thread(new ThreadJoueur());
		/* On lance la musique d'ambiance */
		this.pisteAmbiance = this.audioTracks.ajouterPiste(-1, this.mondes.get(this.indiceMondeCourant).getNiveau(this.indiceNiveauCourant).getMusiqueAmbiance());
		// On met a jour la vue */
		this.notifierVue(new String(courant.getTheme()));
	}

	/**
	 * chargerNiveau Permet de charge un niveau parmis ceux disponibles
	 * @param iMonde indice du monde
	 * @param iNiv indice du niveau
	 */
	public void chargerNiveau(int iMonde, int iNiv) {
		/*
		 * On ne verifie pas les indices; s'il y a erreur l'exception engendree
		 * sera catch par une entite superieure
		 */
		this.indiceMondeCourant = iMonde;
		this.indiceNiveauCourant = iNiv;
		this.chargerNiveau();
	}

	/**
	 * construireMatrice. Construit la matrice avec un niveau en parametre.
	 * Permet de "charger" un niveau.
	 * @param niveauCourant le niveau a charger
	 */
	private void construireMatrice(java.MVC.Model.jeu.Niveau niveauCourant) {
		/* initialisation de la carte avec la taille des tableaux */
		this.matrice = new java.MVC.Model.jeu.Carte(20, 30);
		/* positionnement des blocs */
		/* un bloc etant Positionnable, il possede ses propres coordonnees */
		for (Bloc bloci : niveauCourant.recupererListeBlocs()) {
			this.matrice.setBloc(bloci);
		}
		/* positionnement des items */
		/* un item etant Positionnable, il possede ses propres coordonnees */
		for (Item courant2 : niveauCourant.recupererListeItems()) {
			this.matrice.setItem(courant2);
		}
		/* Recuperation du score a atteindre pour le joueur courant */
		this.scoreNivCourant = niveauCourant.getScoreFinal();
		/* Recuperation du portail de fin de niveau */
		this.sortie = niveauCourant.getTheEnd();
		java.MVC.Model.jeu.Positionnable.GESTION_ANIM.setMatrice(this.matrice);
	}

	/**
	 * ajouterHeros Permet d'ajouter un heros a la liste des personnges.
	 * @param player le heros a ajouter
	 */
	private void ajouterHeros(Heros player) {
		this.personnages.add(player);
	}

	/**
	 * ajouterMadMonk Permet d'ajouter un MadMonk a la liste des personnages
	 * present sur la carte.
	 * @param madMonk le MadMonk a ajouter
	 * @param personnage le personnage que le MadMonk doit cibler
	 */
	private void ajouterMadMonk(MadMonk madMonk, Personnage personnage) {
		/* on precise le joueur au MadMonk pour qu'il puisse le suivre */
		madMonk.cibler(personnage);
		this.personnages.add(madMonk);
	}
	/* ********** ********************************* ********** */

	/**
	 * reebootNiveau Permet de reset un niveau.
	 */
	public void rebootNiveau() {
		fini = true;
		java.MVC.Model.jeu.Positionnable.GESTION_ANIM.finirAnimation();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.personnages = new ArrayList<Personnage>();
		this.LodeRunner = new Thread(new ThreadJoueur());
		java.MVC.Model.jeu.Positionnable.GESTION_ANIM = new ThreadAnimation();

	}

    /**
     * Permet de jouer au java.MVC.Model.jeu.
     */
	public void BoucleDeJeu() {
	    // Recuperer les inputs

        // Mettre a jour la logique

	    // Mettre a jour l'image du java.MVC.Model.jeu

    }

	/**
	 * startThread Permet de démarrer le modele de java.MVC.Model.jeu sur le niveau
	 * actuellement charge
	 */
	public void startThread() {
		//LodeRunner.start();
		/* mise en route de MadMonks */
		this.activerMadMonk();
	}

	/* ********** METHODES DE DIRECTION ********** */
	/**
	 * setUp Permet de mettre a jour la direction Haut
	 * @param up booleen
	 */
	public void setUp(boolean up) {
		this.up = up;
	}

	/**
	 * setDown Permet de mettre a jour la direction Bas
	 * @param down booleen
	 */
	public void setDown(boolean down) {
		this.down = down;
	}

	/**
	 * setLeft Permet de mettre a jour la direction Gauche
	 * @param left booleen
	 */
	public void setLeft(boolean left) {
		this.left = left;
	}

	/**
	 * setRight Permet de mettre a jour la directoin Droite
	 * @param right booleen
	 */
	public void setRight(boolean right) {
		this.right = right;
	}
	/* ********** ********************* ********** */

	public Heros getHeros() {
		return (Heros) this.personnages.get(0);
	}

	public Personnage getMadMonk() {
		return this.personnages.get(1);
	}

	/**
	 * getListPerso Permet de recuperer la liste des personnages dans le niveau
	 * en cours
	 * @return la liste des personnages en java.MVC.Model.jeu
	 */
	public List<Personnage> getListPerso() {
		return this.personnages;
	}

	/*
	public String getThemeActuel() {
		return this.themeActuel;
	}*/

	/**
	 * getEnv Methode qui permet d'avoir l'environnement pour un x et y donnes.
	 * @param pos coordonnees ou l'on souhaite recuperer le bloc
	 * @return le bloc situe a cette position
	 */
	public Bloc getEnv(java.MVC.Model.jeu.Coordonnees pos) {
		return this.matrice.getBloc(pos);
	}
	
	/**
	 * getEnv Methode qui permet d'avoir l'environnement pour un x et y donnes.
	 * @return le bloc situe a cette position
	 */
	public List<java.MVC.Model.jeu.Monde> getMondes() {
		return this.mondes;
	}
	/**
	 * getObj Permet de recuperer l'objet situe a la position donnee/
	 * 
	 * @param pos
	 *            coordonnees ou l'on souhaite recuperer l'objet
	 * @return l'objet situe a cette position
	 */
	public Item getObj(java.MVC.Model.jeu.Coordonnees pos) {
		return this.matrice.getItem(pos);
	}

	public Bloc[][] AccesNiveau() {
		return this.matrice.getTerrain();
	}

	public Item[][] AccesObjets() {
		return this.matrice.getObjet();
	}

	/**
	 * activerMadMonk Permet "d'activer" les MadMonks du niveau, c'est a dire de
	 * demarrer leur activite.
	 */
	public void activerMadMonk() {
		for (Personnage courant : this.personnages) {
			if (courant instanceof MadMonk) {
				((MadMonk) courant).vivre();
			}
		}
	}

	public void mettreHeroFixe() {
		if (this.getHeros().isRegardeAGauche()) {
			// this.getPerso().setSprite("./src/ressources/Images/hero_fry_stop_left.png");
			this.getHeros().setSprite("/ressources/Images/hero_fry_stop_left.png");
		} else {
			// this.getPerso().setSprite("./src/ressources/Images/hero_fry_stop_right.png");
			this.getHeros().setSprite("/ressources/Images/hero_fry_stop_right.png");
		}
	}

	public void creuser() {
		java.MVC.Model.jeu.Coordonnees c;
		if (this.getHeros().isRegardeAGauche()) {
			/* Creuser a gaucher */
			c = new java.MVC.Model.jeu.Coordonnees(this.getHeros().getPosition().getX() - 1, this.getHeros().getPosition().getY() + 1);
			getHeros().setSprite("/ressources/Images/hero_fry_dig_left.png");
		} else {
			/* Creuser a droite */
			c = new java.MVC.Model.jeu.Coordonnees(this.getHeros().getPosition().getX() + 1, this.getHeros().getPosition().getY() + 1);
			getHeros().setSprite("/ressources/Images/hero_fry_dig_right.png");
		}
		/* On creuse */
		if (this.getEnv(c) instanceof BlocTerre && this.getEnv(c).isConcret()) {
			// this.matrice.getBloc(c).getSprite().playSequence(Images.blocExplosion,
			// false);
			// this.matrice.getBloc(c).setDetruit(true);
			((BlocTerre) this.matrice.getBloc(c)).creuser();
			// this.matrice.getBloc(c).setDetruit(false);
		} else {
			throw new BlocNonCreusableException("Impossible de creuser ce bloc");
		}
	}

	public void utiliserGadget(char keyChar) {
		try {
			java.MVC.Model.jeu.Coordonnees posItem;
			switch (keyChar) {
			case 'x':
				posItem = new java.MVC.Model.jeu.Coordonnees(this.getHeros().getPosition().getX(), this.getHeros().getPosition().getY());
				((Heros) this.getHeros()).utiliserInv(new PetiteBombe(posItem));
				// this.matrice.getBloc(c).getSprite().playSequence(Images.explosion,false);
				PetiteBombe b = new PetiteBombe(posItem);
				this.matrice.setItem(b);
				b.utiliser();
				break;
			case 'p':
				if (getHeros().isRegardeAGauche()) {
					posItem = new java.MVC.Model.jeu.Coordonnees(this.getHeros().getPosition().getX() - 1,
							this.getHeros().getPosition().getY() - 1);
				} else {
					posItem = new java.MVC.Model.jeu.Coordonnees(this.getHeros().getPosition().getX() + 1,
							this.getHeros().getPosition().getY() - 1);
				}
				((Heros) this.getHeros()).utiliserInv(new Pioche(posItem));
				// this.matrice.getBloc(c).getSprite().playSequence(Images.explosion,false);
				Pioche p = new Pioche(posItem);
				// this.matrice.setItem(p);
				p.utiliser();
				break;
			case 'g':
//				System.out.println("GAZZZ");
				/* On créer la positon ou l'on va executer l'action */
				if (getHeros().isRegardeAGauche()) {
					/* Gaz a gauche */
					posItem = new java.MVC.Model.jeu.Coordonnees(this.getHeros().getPosition().getX() - 3,
							this.getHeros().getPosition().getY());
				} else {
					/* Gaz a droite */
					posItem = new java.MVC.Model.jeu.Coordonnees(this.getHeros().getPosition().getX() + 1,
							this.getHeros().getPosition().getY());
				}
				((Heros) this.getHeros()).utiliserInv(new Gaz(posItem));
				Gaz g = new Gaz(posItem);
				g.getSprite().modifierImage("/ressources/Images/bloc_gaz1.png");
				this.matrice.setItem(g);
				g.utiliser();
				break;
			default:
//				System.out.println("nothing");
				break;
			}
		} catch (InventaireVideException e) {
			System.out.println(e);
		}
	}

	public void ramasserGadget() {
		// try {
		Item i = this.getObj(this.getHeros().getPosition());
		if (i instanceof Gadget) {
			Gadget g = (Gadget) i;
			((Heros) this.getHeros()).ramasser(g);
			this.matrice.removeItem(g);
		}
		/*
		 * } catch() {
		 * 
		 * }
		 */
	}

	/**
	 * checkScore Verification du score de joueur. Si le score du joueur est
	 * egal a celui du niveau, alors il a gagne et le portail de fin de niveau
	 * apparait.
	 * 
	 * @param scoreHeros
	 *            le score actuel du joueur
	 */
	private void checkScore(int scoreHeros) {
		if (scoreHeros == this.scoreNivCourant) {
//			System.out.println("GGGGGGGGG");
			/* Demarrage de l'animation */
			java.MVC.Model.jeu.Positionnable.GESTION_ANIM.ajouterPositionnableAnime(this.sortie, java.Utils.helper.Images.gate);
			/* Faire apparaitre le portail de fin de niveau */
			this.matrice.setBloc(this.sortie);
		}
	}

	private void testerSiFall() {

		if (getHeros().getCentre().getY() < 614) {
			java.MVC.Model.jeu.Coordonnees enDessous = new java.MVC.Model.jeu.Coordonnees(getHeros().getPosition().getX(),
					getHeros().getPosition().getY() + 1);
			/*
			 * On tombe si : On n'est pas sur une echelle ou qu'on est pas sur
			 * un madMonk ET 1) s'il n'y a pas de bloc en dessous et qu'on est
			 * pas sur une barre de franchissement OU 2) il y a un bloc en
			 * dessous et il n'est pas concret et on ne se trouve pas sur une
			 * barre de franchissement ou une echelle OU 3) il y a un bloc en
			 * dessous et on est pas arrivé "dans" le bloc
			 */
			if ((!(getEnv(getHeros().getPosition()) instanceof BlocEchelle) && ((!(getEnv(enDessous) instanceof Bloc)
					&& !(getEnv(getHeros().getPosition()) instanceof BlocBarreTraversee))
					|| ((getEnv(enDessous) instanceof Bloc)
							&& (getHeros().getPixels().getY() < getHeros().getPosition().getY() * 32))
					|| ((getEnv(enDessous) instanceof Bloc) && (!getEnv(enDessous).isConcret())
							&& !(getEnv(getHeros().getPosition()) instanceof BlocBarreTraversee))
							&& !(getEnv(enDessous) instanceof BlocEchelle)))) {
				System.out.println("FALL");
				this.setDown(true);
				this.setUp(false);
				this.setLeft(false);
				this.setRight(false);

			}

		} else {
			setDown(false);
			mettreHeroFixe();
			getHeros().setMouvement(false);
		}
	}

	/*
	private boolean surMadMonk() {
		return this.getHeros().getPosition().equals(this.getMadMonk().getPosition());
	}
	*/
	
	public void notifierVue(String event) {
		this.setChanged();
		this.notifyObservers(event);
		this.clearChanged();
	}

	/** Maybe use a pattern here to know which java.MVC.Model is targetted ?? */

	public String GetCurrentView() {
		return this.menuModel.GetCurrentView();
	}

	public void ChangeView(String newView, boolean isBackButton) {
		this.menuModel.ChangeView(newView, isBackButton);
	}

	public void ReturnLastView() {
		this.menuModel.ReturnLastView();
	}

	public void Update() {
		//this.menuModel.Update();
	}


	private class ThreadJoueur implements Runnable {

		@Override
		public void run() {
			boolean surGlu = false;
			// Instanciation et lancement du traitement
			while (!fini && getHeros().estVivant()) {

				// Update animations
				java.MVC.Model.jeu.Positionnable.GESTION_ANIM.activerAnimation();

				// test de chute
				testerSiFall();
				while ((up || down || left || right) && getHeros().estVivant() && !fini) {
					// ButtonsCallback de chute des l'apparition
					testerSiFall();
					while (down && getHeros().estVivant() && !fini) {
						/* Gestion de la collision */
						if (getHeros().getCentre().getY() < 625) {
							/* Tant que le joueur n'est pas "dans" la case */
							if (getHeros().getPixels().getY() < getHeros().getPosition().getY() * 32) {
								getHeros().bas();
							} else {
								java.MVC.Model.jeu.Coordonnees enDessous = new java.MVC.Model.jeu.Coordonnees(getHeros().getPosition().getX(),
										getHeros().getPosition().getY() + 1);
								/* S'il y a rien en dessous du tout */
								if (!(getEnv(enDessous) instanceof Bloc)
										&& !(getEnv(getHeros().getPosition()) instanceof BlocBarreTraversee)) {
									activerAnimation(java.Utils.helper.Images.fall);
									getHeros().bas();
									/* S'il y a quelque chose */
								} else {
									/*
									 * Cas ou le personnage n'est pas sur une
									 * barre de franchissement
									 */
									if (!(getEnv(getHeros().getPosition()) instanceof BlocBarreTraversee)) {
										/*
										 * on test si on n'est pas sur une
										 * echelle
										 */
										/*
										 * Attention : doit gerer le bas de
										 * l'echelle et le haut (pas sur
										 * l'echelle)
										 */
										if ((getEnv(getHeros().getPosition()) instanceof BlocEchelle
												&& !(getEnv(enDessous) instanceof Bloc))
												|| getEnv(enDessous) instanceof BlocEchelle) {
											activerAnimation(java.Utils.helper.Images.climbLadder);
											getHeros().bas();
											/* on test si c'est pas une trap */
										} else if (!getEnv(enDessous).isConcret()) {
											activerAnimation(java.Utils.helper.Images.fall);
											getHeros().bas();
										} else {
											/* sinon on ne descend plus */
											setDown(false);
											mettreHeroFixe();
											getHeros().setMouvement(false);
										}
									} else {
										activerAnimation(java.Utils.helper.Images.fall);
										getHeros().bas();
									}
								}
							}
							/*
							 * On test si le joueur ne pas pas collecter de
							 * lingot
							 */
							this.detecterObjet(getHeros().getPosition());
						} else {
							arreterMouvement();
						}
						pauseThread(20);
						testerSiFall();
						testerSiFin();
					}
					while (left && getHeros().estVivant() && !fini) {
						/* ButtonsCallback pour savoir si on est sur de la glue */
						if (getEnv(new java.MVC.Model.jeu.Coordonnees(getHeros().getPosition().getX(),
								getHeros().getPosition().getY() + 1)) instanceof BlocGlu) {
							surGlu = true;
						} else {
							surGlu = false;
						}
						activerAnimation(java.Utils.helper.Images.moveLeft);
						/* Gestion de la collision a gauche */
						if (getHeros().getCentre().getX() > 15) {
							/* On test si le personnage est "dans" la case */
							if (getHeros().getPixels().getX() < getHeros().getPosition().getX() * 32) {
								java.MVC.Model.jeu.Coordonnees aGauche = new java.MVC.Model.jeu.Coordonnees(getHeros().getPosition().getX() - 1,
										getHeros().getPosition().getY());
								if (!(getEnv(aGauche) instanceof Bloc)) {
									if (surGlu) {
										getHeros().gauche(getHeros().getMoveSpeed() / 2);
									} else {
										getHeros().gauche();
									}
								} else {
									/*
									 * S'il y a un bloc a gauche on regarde s'il
									 * est concret
									 */
									if (!getEnv(aGauche).isConcret() || getEnv(aGauche) instanceof BlocBarreTraversee) {
										/*
										 * Si on est sur de la Glue, on se
										 * déplace deux fois moins vite
										 */
										if (surGlu) {
											getHeros().gauche(getHeros().getMoveSpeed() / 2);
										} else {
											getHeros().gauche();
										}
										/* sinon on ne va plus a gauche */
									} else {
										setLeft(false);
										getHeros().setMouvement(false);
									}
								}
							} else {
								if (surGlu) {
									getHeros().gauche(getHeros().getMoveSpeed() / 2);
								} else {
									getHeros().gauche();
								}
							}
							/*
							 * On test si le joueur ne pas pas collecter de
							 * lingot
							 */
							this.detecterObjet(getHeros().getPosition());
						} else {
							arreterMouvement();
						}
						pauseThread(20);
						testerSiFall();
						testerSiFin();
					}
					while (up && getHeros().estVivant() && !fini) {
						activerAnimation(java.Utils.helper.Images.climbLadder);
						/* Gestion de la collision en haut */
						if (getHeros().getCentre().getY() > 16) {
							/* tant que le perso n'ai pas arrivé dans la case */
							if (getHeros().getPixels().getY() > getHeros().getPosition().getY() * 32) {
								getHeros().haut();
							} else {
								java.MVC.Model.jeu.Coordonnees auDessus = new java.MVC.Model.jeu.Coordonnees(getHeros().getPosition().getX(),
										getHeros().getPosition().getY() - 1);
								/*
								 * si le bloc au dessus de lui est un bloc autre
								 * que echelle
								 */
								if (getEnv(auDessus) instanceof Bloc && ((Bloc) getEnv(auDessus)).isConcret()) {
									/* on arrete le mouvement */
									setUp(false);
									getHeros().setMouvement(false);
									/*
									 * si le bloc "derriere" lui est une echelle
									 */
								} else if (getEnv(getHeros().getPosition()) instanceof BlocEchelle) {
									getHeros().haut();
								} else {
									setUp(false);
									getHeros().setMouvement(false);
								}
							}
							/*
							 * On test si le joueur ne pas pas collecter de
							 * lingot
							 */
							this.detecterObjet(getHeros().getPosition());
						} else {
							arreterMouvement();
						}
						pauseThread(20);
						testerSiFall();
						testerSiFin();
					}
					while (right && getHeros().estVivant() && !fini) {
						/* ButtonsCallback pour savoir si on est sur de la glue */
						if (getEnv(new java.MVC.Model.jeu.Coordonnees(getHeros().getPosition().getX(),
								getHeros().getPosition().getY() + 1)) instanceof BlocGlu) {
							surGlu = true;
						} else {
							surGlu = false;
						}
						activerAnimation(java.Utils.helper.Images.moveRight);
						/* Gestion de la collision a droite */

						if (getHeros().getCentre().getX() < 945) {
							/* On regarde si le heros est "dans" la case */
							if ((getHeros().getPixels().getX()) < getHeros().getPosition().getX() * 32) {
								java.MVC.Model.jeu.Coordonnees aDroite = new java.MVC.Model.jeu.Coordonnees(getHeros().getPosition().getX() + 1,
										getHeros().getPosition().getY());
								/* on test s'il y a un bloc a droite */
								if (!(getEnv(aDroite) instanceof Bloc) || ((getEnv(aDroite) instanceof Bloc)
										&& (getHeros().getPixels().getX() + 34 < aDroite.getX() * 32))) {
									if (surGlu) {
										getHeros().droite(getHeros().getMoveSpeed() / 2);
									} else {
										getHeros().droite();
									}
									/* on a un bloc a droite */
								} else {
									/*
									 * on test si ce bloc n'est pas concret ou
									 * qu'il s'agit d'une barre de
									 * franchissement
									 */
									if (!(getEnv(aDroite).isConcret())
											|| getEnv(aDroite) instanceof BlocBarreTraversee) {
										if (surGlu) {
											getHeros().droite(getHeros().getMoveSpeed() / 2);
										} else {
											getHeros().droite();
										}
										/* sinon on ne va plus a droite */
									} else {
										setRight(false);
										getHeros().setMouvement(false);
									}
								}

							} else {
								if (surGlu) {
									getHeros().droite(getHeros().getMoveSpeed() / 2);
								} else {
									getHeros().droite();
								}
							}
							/*
							 * On test si le joueur ne pas pas collecter de
							 * lingot
							 */
							this.detecterObjet(getHeros().getPosition());
						} else {
							arreterMouvement();
						}
						pauseThread(20);
						testerSiFall();
						testerSiFin();
					}
				}
				pauseThread(1);
			}
			getHeros().setSprite("/ressources/Images/bloc_vide.png");
			for (Personnage p : personnages) {
				p.setVivant(false);
			}
//			System.out.println(fini);
			audioTracks.retirerPiste(pisteAmbiance);
			/* Si le joueur s'est fait tuer */
			if (!fini) {
				/* Joue l'animation de mort */
				audioTracks.ajouterPiste(0, "/ressources/Musiques/lose.wav");
				java.MVC.Model.jeu.Coordonnees c = new java.MVC.Model.jeu.Coordonnees(getHeros().getPixels().getX(), getHeros().getPixels().getY() - 32);
				getHeros().setMouvement(false);
				getHeros().getSprite().setPosition(c.getX(), c.getY());
				if (getHeros().isRegardeAGauche()) {
					/* Animation a gauche */
					java.MVC.Model.jeu.Positionnable.GESTION_ANIM.ajouterPositionnableAnime(getHeros(), java.Utils.helper.Images.deathRight);
				} else {
					java.MVC.Model.jeu.Positionnable.GESTION_ANIM.ajouterPositionnableAnime(getHeros(), java.Utils.helper.Images.deathLeft);
				}
				/* Sinon le joueur a gagné le niveau */
			} else {
				getHeros().setMouvement(false);
				audioTracks.ajouterPiste(0, "/ressources/Musiques/win.wav");
				/* Joue l'animation et la musique de fin de niveau */
				java.MVC.Model.jeu.Positionnable.GESTION_ANIM.ajouterPositionnableAnime(getHeros(), java.Utils.helper.Images.win);
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				audioTracks.clearListePistes();

				/* Passe au prochain niveau */
				if (indiceMondeCourant == mondes.size()) {
                    indiceMondeCourant++;
					indiceNiveauCourant = 0;
				}
				rebootNiveau();
				chargerNiveau(indiceMondeCourant, ++indiceNiveauCourant);
			}
		}

		private void activerAnimation(BufferedImage[] seq) {
			if (!getHeros().isMouvement()) {
				getHeros().getSprite().playSequence(seq, true);
				getHeros().setMouvement(true);
			}
		}

		private void arreterMouvement() {
			setRight(false);
			setUp(false);
			setLeft(false);
			setDown(false);
			getHeros().setMouvement(false);
			mettreHeroFixe();
		}

		private void pauseThread(int temps) {
			try {
				Thread.sleep(temps);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private void detecterObjet(java.MVC.Model.jeu.Coordonnees posHeros) {
			/* On test si le joueur ne se trouve pas sur un lingot */
			Item item = getObj(posHeros);
			if (item instanceof Lingot) {
				/* On incremente le score du joueur */
				Heros h = (Heros) getHeros();
				h.augmenterScore(((Lingot) item).getValeur());
				/* On teste si le Heros a tous les lingots */
				checkScore(h.getScore());
				/* On joue le son de lingot ramassé */
				audioTracks.ajouterPiste(0, "/ressources/Musiques/ding.wav");
				/* On le retire de la carte */
				matrice.removeItem(item);
				item.destruction();
			} else if (item instanceof PetiteBombe) {
				if (!((PetiteBombe) item).isActif()) {
					Heros h = (Heros) getHeros();
					h.ramasser(item);
					matrice.removeItem(item);
					// item.destruction();
				}
			}
		}

		private void testerSiFin() {
			/* Si le joueur fini le niveau */
			if (getEnv(getHeros().getPosition()) instanceof BlocPortailFinNiveau) {
				fini = true;
				/* Si le joueur se fait attraper */
			} else if ((abs(getHeros().getPixels().getX() - getMadMonk().getPixels().getX()) < 20)
					&& (abs(getMadMonk().getPixels().getY() - getHeros().getPixels().getY()) < 10)) {
				// getPerso().getCentre().equals(getMadMonk().getCentre())) {
				// getPerso().getSprite().playSequence(Images.deathLeft,
				// false);
				getHeros().setVivant(false);
			}
		}

		private int abs(int x) {
			int result;
			if (x < 0) {
				result = -x;
			} else {
				result = x;
			}
			return result;
		}

	}

}
