package java.MVC.Model.personnage;

import java.util.Random;

import java.MVC.Model.bloc.Bloc;
import java.MVC.Model.bloc.BlocBarreTraversee;
import java.MVC.Model.bloc.BlocEchelle;
import java.MVC.Model.bloc.BlocTerre;
import java.MVC.Model.item.Item;
import java.MVC.Model.item.Lingot;
import java.MVC.Model.jeu.Carte;
import java.MVC.Model.jeu.Coordonnees;

/**
 * Classe des MadMonks
 * 
 * @author Axel Grau
 * @version 1.0
 *
 */
public class MadMonk extends Personnage {

	/* ***** ***** ATTRIBUTS ***** ***** */
	/** invLingot */
	private Lingot invLingot;
	/** cerveau Intelligence du MadMonk */
	private IAMadMonk cerveau;
	/** carte Matrice de java.MVC.Model.jeu dans laquelle le MadMonk evolue */
	private Carte carte;
	/** destination Coordonnees que le MadMonk souhaite rejoindre */
	private Coordonnees destination;
	/** player Le heros que le MadMonk poursuivra */
	private Heros cible;
	/**
	 * vie thread de "vie" du MadMonk. Contient toutes les actions qu'un MadMonk
	 * executera en boucle dans un niveau.
	 */
	private Thread vie;
	/* ***** ***** ********* ***** ***** */
	
	/**
	 * Constructeur Permet de creer un MadMonk
	 * 
	 * @param position
	 *            position de depart sur la carte
	 * @param img
	 *            image du MadMonk
	 * @param vitesse
	 *            vitesse de depart du MadMonk
	 * @param terrain
	 *            terrain sur lequel le MadMonk evolue
	 * @param difficulte
	 *            intelligence du MadMonk (de base naif)
	 */
	public MadMonk(Coordonnees position, String img, int vitesse, Carte terrain, int difficulte) {
            super(position, img, vitesse);
            this.invLingot = null;
            this.carte = terrain;
            this.destination = position;
            /* Par defaut la difficulte des MadMonk est : naive */
            this.cerveau = new IANaive(position);
            if (difficulte == 2) {
                    // this.cerveau = new IAExpert();
            }
            this.setMouvement(false);
	}
	
	/**
	 * cibler Permet de donner le heros que le MadMonk doit essayer d'attraper.
	 * 
	 * @param personnage
	 *            le personnage a attraper
	 */
	public void cibler(Personnage personnage) {
            this.cible = (Heros) personnage;
            this.vie = new Thread(new ThreadMadMonk());
	}

	/**
	 * vivre Permet de demarrer le thread vie du MadMonk.
	 */
	public void vivre() {
            this.vie.start();
	}

	/** testChute
	 * Permet de faire chuter le MadMonk s'il n'y a pas de bloc en dessous de lui.
	 */
	private void chuter() {
            Coordonnees enDessous = new Coordonnees(getPosition().getX(), getPosition().getY() + 1);
            /* tant qu'il n'y a pas de bloc en dessous de nous OU qu'on ne touche
             * pas le bloc */
            while (!(carte.getBloc(getPosition()) instanceof BlocEchelle)
            		&& (!(carte.getBloc(enDessous) instanceof Bloc)
            		|| (getPixels().getY()+32) < (enDessous.getY() * 32))) {
                /* mise a jour du bloc enDessous */
                enDessous = new Coordonnees(getPosition().getX(), getPosition().getY() + 1);
                /* on tombe */
                if (!isMouvement()) {
                    getSprite().playSequence(java.Utils.helper.ImagesMadMonk.fall, true);
                    setMouvement(true);
                }
                bas();
                pauseThread(20);
            }
            /* une fois arrive en bas, le MadMonk est a l'arret */
            this.setArret();
	}
	
	/** volerLingot
	 * Permet au MadMonk de voler un Lingot.
	 * S'il passe sur un lingot, il essaye de le ramasser,
	 * sinon il essaye de deposer son lingot.
	 */
	private void volerLingot() {
            /* A chaque pas, le MadMonk essaye de prendre un lingot
             * ou d'en relacher un */
            Item lingotPotentiel = this.carte.getItem(this.getPosition());
            if (lingotPotentiel instanceof Lingot) {
                /* s'il y a un lingot, on essaye de le prendre */
                this.ramasser(lingotPotentiel);
            } else if (lingotPotentiel == null) {
                /* sinon il essaie de relacher */
                this.deposer();
            }
	}

	/**
	 * poursuivre Methode qui permet au MadMonk de decider son chemin sur la
	 * carte. Si le joueur est visible, le MadMonk suivra le joueur pour essayer
	 * de l'atteindre. Si le joueur n'est pas visible, le MadMonk se fixera une
	 * position à atteindre jusqu'a ce qu'il l'ait atteinte ou que le joueur
	 * devienne visible.
	 * 
	 * @param player
	 *            le heros a atteindre
	 */
	private void poursuivre(Heros player) {
            /*
             * A chaque pas, le MadMonk va peut etre relacher le lingot qu'il
             * possede eventuellement
             */
            // Random rnd = new Random();
            // /* si la position Random est nulle ou si on l'a atteinte, on en
            // choisit une nouvelle */
            // if ((this.destination == null) ||
            // this.getPosition().egal(this.destination)) {
            // this.destination = new Coordonnees((int) rnd.nextInt(30), (int)
            // rnd.nextInt(20));
            // }
            /* si le heros est visible, on poursuit le heros */
            //setArret();
            if (player.estVisible() && cerveau.majDestination(player.getPosition())) {
                destination = this.cerveau.itineraire(this.getPosition());
                setMouvement(true);
            }
            /* le choix de la destination est maintenant fait */
	}

	/** setArret
	 * Permet de mettre le MadMonk a l'arret.
	 */
	private void setArret() {
            this.setMouvement(false);
            if (this.isRegardeAGauche()) {
                /* s'il regarde a gauche, on le met sur la frame gauche */
                this.setSprite("/ressources/Images/mad_monk_stop_left.png");
            } else {
                /* sinon, frame droite */
                this.setSprite("/ressources/Images/mad_monk_stop_right.png");
            }
	}

	/** ramasser
	 * Permet au MadMonk de ramasser un Lingot. Le MadMonk ne peut
	 * ramasser que des Lingots. Quand il passe sur un lingot, le MadMonk
	 * decide de le ramasser ou pas.
	 */
	@Override
	public void ramasser(Item item) {
            Random rd = new Random();
            /* on ne ramasse un lingot que si l'inventaire est vide */
            if (this.invLingot == null && (rd.nextInt(2)== 0)) {
                /* on met le lingot dans l'inventaire */
                this.invLingot = (Lingot) item;
                /* on enleve le lingot du terrain */
                this.carte.removeItem(item);
                /* notifications */
                item.destruction();
            }
	}

	/** deposer
	 * Permet au MadMonk de deposer le lingot qu'il a en inventaire
	 * (s'il en a un). Le MadMonk depose son lingot aleatoirement sur la carte.
	 */
	@Override
	public void deposer() {
            Random rd = new Random();
            /* on ne relache un lingot que si l'inventaire est plein et que la case est libre */
            if ((this.invLingot != null) && (this.carte.getItem(this.getPosition()) == null)) {
                /* on ne peut deposer un objet que sur une case libre */
                if (rd.nextInt(4) == 0) {
                    /* on met a jour la position du Lingot */
                    this.invLingot.setPosition(new Coordonnees(this.getPosition().getX(),this.getPosition().getY()));
                    /* on pose le lingot sur la carte */
                    this.carte.setItem(this.invLingot);
                    /* et on le supprime de l'inventaire */
                    this.invLingot = null;
                }
            }
	}
	
	/** tomber
	 * Procedure de verification de chute dans un bloc temporairement detruit.
	 * Si le bloc convient, le MadMonk chute et se tient au bord des blocs adjacents.
	 * Apres quelques secondes il remonte sur un cote.
	 */
	private void tomber() {
            /* Recuperation du bloc en dessous du MadMonk */
            Coordonnees enDessous = new Coordonnees(getPosition().getX(), getPosition().getY() + 1);
            /* On verifie si le bloc de terre en dessous n'est pas CONCRET */
            if ((this.carte.getBloc(enDessous) instanceof BlocTerre) && (!carte.getBloc(enDessous).isConcret())) {
                /* dans ce cas la, il faut "tomber" jusqu'au bloc du dessous */
                /* tant que le pixel du haut du MadMonk n'a pas atteint celui du haut du bloc en dessous */
                while ((getPixels().getY()) < (enDessous.getY() * 32)) {
                    /* on tombe */
                    if (!isMouvement()) {
                        getSprite().playSequence(java.Utils.helper.ImagesMadMonk.fall, true);
                        setMouvement(true);
                    }
                    //getSprite().playSequence(ImagesMadMonk.fall, true);
                    bas();
                    pauseThread(20);
                }
                /* un fois ceci fait, le MadMonk est bloque */
                setArret();
                /* on met le bloc ou il se trouve a concret */
                boolean concret = this.carte.getBloc(this.getPosition()).isConcret();
                this.carte.getBloc(this.getPosition()).setConcret(true);
                // SPRITE MADMONK BLOQUE
                /* pendant 5 secondes */
                pauseThread(5000);;
                /* on remet le bloc ou il se trouve a non concret */
                this.carte.getBloc(this.getPosition()).setConcret(concret);
                /* suite a cela, le MadMonk remonte */
                Coordonnees auDessus = new Coordonnees(getPosition().getX(), getPosition().getY()-1);
                while ((getPixels().getY()) > (auDessus.getY() * 32)) {
                    /* on remonte */
                    if (!isMouvement()) {
                        getSprite().playSequence(java.Utils.helper.ImagesMadMonk.climbLadder, true);
                        setMouvement(true);
                    }
                    haut();
                    pauseThread(20);
                }
                /* le MadMonk est libre */
                setArret();
                // mettre le MadMonk a l'arret
            }
	}
	
	/** pauseThread
	 * Permet de mettre en pause le Thread pendant un certains temps,
	 * entre deux mouvements par exemple.
	 * @param temps le temps de pause
	 */
	private void pauseThread(int temps) {
    	try {
            Thread.sleep(temps);
    	} catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
    	}
    }

    private class ThreadMadMonk implements Runnable {

        @Override
        public void run() {
            /* demarrage */
            // getSprite().playSequence(ImagesMadMonk.fall, true);
            // setArret();
            // pauseThread(50);
            setRegardeAGauche(true);
            while (estVivant()) {
                /* test de chute */
                Coordonnees enDessous = new Coordonnees(getPosition().getX(), getPosition().getY()+1);
                if (!(carte.getBloc(enDessous) instanceof Bloc)) {
                    chuter();
                }
                /* verification de chute dans un bloc detruit */
                tomber();
                /* voler un lingot potentiel */
                volerLingot();
                /* le MadMonk execute les actions necessaires a son mouvement */
                poursuivre(cible);
                /* sa destination est maintenant fixee */
                /* ***** ***** ANALYSE DU MOUVEMENT ***** ***** */
                if (destination.getX() == getPosition().getX()) {
                    /* Le MadMonk s'arrete */
                    setArret();
                    do {
                        pauseThread(50);
                    } while (!cible.isMouvement());
                    setMouvement(true);
                }
                if (destination.getY() < getPosition().getY() &&
                        carte.getBloc(getPosition()) instanceof BlocEchelle) {
                    /*
                     * Si la cible est au dessus du MadMonk, il peut essayer de
                     * monter au prochain bloc au dessus de lui.*/
                    /*MONTER : n'est
                     * possible que si le bloc sur lequel on se trouve est une
                     * echelle et si le bloc au dessus n'est pas un bloc echelle
                     */
                    Coordonnees auDessus = new Coordonnees(getPosition().getX(), getPosition().getY() - 1);
                    if ((carte.getBloc(getPosition()) instanceof BlocEchelle)
                    		&& ((carte.getBloc(auDessus) instanceof Bloc)
                    				&& (!carte.getBloc(auDessus).isConcret()))
                    		|| !(carte.getBloc(auDessus) instanceof Bloc)) {
                        /*
                         * on monte tant que le bas du MadMonk n'a pas atteint
                         * le bas du bloc au dessus
                         */
                        getSprite().playSequence(java.Utils.helper.ImagesMadMonk.climbLadder, true);
                        while ((getPixels().getY()) > (auDessus.getY() * 32) - 1) {
                            /* on monte */
                            haut();
                            pauseThread(20);
                        }
                    } else {
                        setArret();
                    }
                }
                else if (destination.getY() > getPosition().getY() &&
                        carte.getBloc(enDessous) instanceof BlocEchelle) {
                    /*
                     * Si la destination est en dessous de nous, on peut essayer
                     * de descendre au prochain bloc en dessous de nous. */
                    /* DESCENDRE : n'est possible que si le bloc en dessous est
                     * une echelle
                     */
                    //enDessous = new Coordonnees(getPosition().getX(), getPosition().getY()+1);
                    if (carte.getBloc(enDessous) instanceof BlocEchelle) {
                        /*
                        * on descend tant que le haut du MadMonk n'a pas
                        * atteint le haut du bloc du dessous
                        */
                        getSprite().playSequence(java.Utils.helper.ImagesMadMonk.climbLadder, true);
                        while ((getPixels().getY()) < (enDessous.getY() * 32)) {
                            /* on descend */
                            bas();
                            pauseThread(20);
                        }
                    }
//                  System.out.println("fin2");
                }
                else if (destination.getX() < getPosition().getX()) {
//                  System.out.println("lolol3");
                    /*
                     * Si la destination est a gauche du MadMonk, il peut
                     * essayer d'aller au bloc a sa gauche.*/
                    /*GAUCHE : n'est possible que si le bloc a gauche 
                     * n'est pas concret ou si c'est une echelle.
                     */
                    Coordonnees gauche = new Coordonnees(getPosition().getX() - 1, getPosition().getY());
                    if ((!(carte.getBloc(gauche) instanceof Bloc)) 
                    		|| ((carte.getBloc(gauche) instanceof Bloc)&&!(carte.getBloc(gauche).isConcret()))
                    		|| (carte.getBloc(gauche) instanceof BlocBarreTraversee)) {
                        /*
                         * on va a gauche tant que la gauche du MadMonk n'a pas
                         * atteint le gauche du bloc a gauche
                         */
                        getSprite().playSequence(java.Utils.helper.ImagesMadMonk.moveLeft, true);
                        while ((getPixels().getX()) > (gauche.getX() * 32)) {
                            /* on va a gauche */
                            gauche();
                            pauseThread(20);
                        }
                    }
//                    System.out.println("fin3");
                }
                else if (destination.getX() > getPosition().getX()) {
//                    System.out.println("lolol4");
                    /*
                     * Si la destination est a droite du MadMonk, il peut
                     * essayer d'aller au bloc a sa droite.
                     */
                    /*
                     * DROITE : n'est possible que si le bloc a droite n'est pas
                     * un bloc ou si c'est une echelle
                     */
                    Coordonnees droite = new Coordonnees(getPosition().getX() + 1, getPosition().getY());
                    if ((!(carte.getBloc(droite) instanceof Bloc)) 
                    		|| ((carte.getBloc(droite) instanceof Bloc)&&!(carte.getBloc(droite).isConcret()))
                    		|| (carte.getBloc(droite) instanceof BlocBarreTraversee)) {
                        /*
                         * on va a droite tant que la gauche du MadMonk n'a pas
                         * atteint la gauche du bloc du dessous
                         */
                        getSprite().playSequence(java.Utils.helper.ImagesMadMonk.moveRight, true);
                        while ((getPixels().getX()) < (droite.getX() * 32)) {
                            /* on va a droite */
                            droite();
                            pauseThread(20);
                        }
                    }
//                    System.out.println("fin4");
                } else {
                    setArret();
                }
                /* attente entre les boucles */
                pauseThread(10);
            }
            setMouvement(false);
        }
    }
}