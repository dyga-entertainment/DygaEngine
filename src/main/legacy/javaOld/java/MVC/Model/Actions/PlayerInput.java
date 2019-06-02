package java.MVC.Model.Actions;

/**
 * @author Grégoire Boiron
 */
/*
public class PlayerInput {


    public void getInputs() {
        boolean surGlu = false;
        // Instanciation et lancement du traitement
        //while (!fini && getHeros().estVivant()) {
            // test de chute
            this.testerSiFall();
            while ((up || down || left || right) && getHeros().estVivant() && !fini) {
                // ButtonsCallback de chute des l'apparition
                testerSiFall();
                while (down && getHeros().estVivant() && !fini) {
                    // Gestion de la collision
                    if (getHeros().getCentre().getY() < 625) {
                        // Tant que le joueur n'est pas "dans" la case
                        if (getHeros().getPixels().getY() < getHeros().getPosition().getY() * 32) {
                            getHeros().bas();
                        } else {
                            Coordonnees enDessous = new Coordonnees(getHeros().getPosition().getX(),
                                    getHeros().getPosition().getY() + 1);
                            // S'il y a rien en dessous du tout
                            if (!(getEnv(enDessous) instanceof Bloc)
                                    && !(getEnv(getHeros().getPosition()) instanceof BlocBarreTraversee)) {
                                activerAnimation(Images.fall);
                                getHeros().bas();
                                // S'il y a quelque chose
                            } else {
                                //Cas ou le personnage n'est pas sur une
                                // barre de franchissement
                                if (!(getEnv(getHeros().getPosition()) instanceof BlocBarreTraversee)) {
                                    // on test si on n'est pas sur une
                                    // echelle

                                    //Attention : doit gerer le bas de
                                    // l'echelle et le haut (pas sur l'echelle)
                                    if ((getEnv(getHeros().getPosition()) instanceof BlocEchelle
                                            && !(getEnv(enDessous) instanceof Bloc))
                                            || getEnv(enDessous) instanceof BlocEchelle) {
                                        activerAnimation(Images.climbLadder);
                                        getHeros().bas();
                                        // on test si c'est pas une trap
                                    } else if (!getEnv(enDessous).isConcret()) {
                                        activerAnimation(Images.fall);
                                        getHeros().bas();
                                    } else {
                                        // sinon on ne descend plus
                                        setDown(false);
                                        mettreHeroFixe();
                                        getHeros().setMouvement(false);
                                    }
                                } else {
                                    activerAnimation(Images.fall);
                                    getHeros().bas();
                                }
                            }
                        }
                        // On test si le joueur ne pas pas collecter de lingot
                        this.detecterObjet(getHeros().getPosition());
                    } else {
                        arreterMouvement();
                    }
                    pauseThread(20);
                    testerSiFall();
                    testerSiFin();
                }
                while (left && getHeros().estVivant() && !fini) {
                    // ButtonsCallback pour savoir si on est sur de la glue
                    if (getEnv(new Coordonnees(getHeros().getPosition().getX(),
                            getHeros().getPosition().getY() + 1)) instanceof BlocGlu) {
                        surGlu = true;
                    } else {
                        surGlu = false;
                    }
                    activerAnimation(Images.moveLeft);
                    // Gestion de la collision a gauche
                    if (getHeros().getCentre().getX() > 15) {
                        // On test si le personnage est "dans" la case
                        if (getHeros().getPixels().getX() < getHeros().getPosition().getX() * 32) {
                            Coordonnees aGauche = new Coordonnees(getHeros().getPosition().getX() - 1,
                                    getHeros().getPosition().getY());
                            if (!(getEnv(aGauche) instanceof Bloc)) {
                                if (surGlu) {
                                    getHeros().gauche(getHeros().getMoveSpeed() / 2);
                                } else {
                                    getHeros().gauche();
                                }
                            } else {
                                // S'il y a un bloc a gauche on regarde s'il est concret
                                if (!getEnv(aGauche).isConcret() || getEnv(aGauche) instanceof BlocBarreTraversee) {
                                    // Si on est sur de la Glue, on se déplace deux fois moins vite
                                    if (surGlu) {
                                        getHeros().gauche(getHeros().getMoveSpeed() / 2);
                                    } else {
                                        getHeros().gauche();
                                    }
                                    // sinon on ne va plus a gauche
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
                        // On test si le joueur ne pas pas collecter de lingot
                        this.detecterObjet(getHeros().getPosition());
                    } else {
                        arreterMouvement();
                    }
                    pauseThread(20);
                    testerSiFall();
                    testerSiFin();
                }
                while (up && getHeros().estVivant() && !fini) {
                    activerAnimation(Images.climbLadder);
                    // Gestion de la collision en haut
                    if (getHeros().getCentre().getY() > 16) {
                        // tant que le perso n'ai pas arrivé dans la case
                        if (getHeros().getPixels().getY() > getHeros().getPosition().getY() * 32) {
                            getHeros().haut();
                        } else {
                            Coordonnees auDessus = new Coordonnees(getHeros().getPosition().getX(),
                                    getHeros().getPosition().getY() - 1);
                            // si le bloc au dessus de lui est un bloc autre que echelle
                            if (getEnv(auDessus) instanceof Bloc && ((Bloc) getEnv(auDessus)).isConcret()) {
                                // on arrete le mouvement
                                setUp(false);
                                getHeros().setMouvement(false);
                                // si le bloc "derriere" lui est une echelle
                            } else if (getEnv(getHeros().getPosition()) instanceof BlocEchelle) {
                                getHeros().haut();
                            } else {
                                setUp(false);
                                getHeros().setMouvement(false);
                            }
                        }
                        // On test si le joueur ne pas pas collecter de lingot
                        this.detecterObjet(getHeros().getPosition());
                    } else {
                        arreterMouvement();
                    }
                    pauseThread(20);
                    testerSiFall();
                    testerSiFin();
                }
                while (right && getHeros().estVivant() && !fini) {
                    // ButtonsCallback pour savoir si on est sur de la glue
                    if (getEnv(new Coordonnees(getHeros().getPosition().getX(),
                            getHeros().getPosition().getY() + 1)) instanceof BlocGlu) {
                        surGlu = true;
                    } else {
                        surGlu = false;
                    }
                    activerAnimation(Images.moveRight);
                    // Gestion de la collision a droite

                    if (getHeros().getCentre().getX() < 945) {
                        // On regarde si le heros est "dans" la case
                        if ((getHeros().getPixels().getX()) < getHeros().getPosition().getX() * 32) {
                            Coordonnees aDroite = new Coordonnees(getHeros().getPosition().getX() + 1,
                                    getHeros().getPosition().getY());
                            // on test s'il y a un bloc a droite
                            if (!(getEnv(aDroite) instanceof Bloc) || ((getEnv(aDroite) instanceof Bloc)
                                    && (getHeros().getPixels().getX() + 34 < aDroite.getX() * 32))) {
                                if (surGlu) {
                                    getHeros().droite(getHeros().getMoveSpeed() / 2);
                                } else {
                                    getHeros().droite();
                                }
                                // on a un bloc a droite
                            } else {
                                // on test si ce bloc n'est pas concret ou qu'il s'agit d'une barre de franchissement

                                if (!(getEnv(aDroite).isConcret())
                                        || getEnv(aDroite) instanceof BlocBarreTraversee) {
                                    if (surGlu) {
                                        getHeros().droite(getHeros().getMoveSpeed() / 2);
                                    } else {
                                        getHeros().droite();
                                    }
                                    // sinon on ne va plus a droite
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
                        // On test si le joueur ne pas pas collecter de lingot
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
        // Si le joueur s'est fait tuer
        if (!fini) {
            // Joue l'animation de mort
            audioTracks.ajouterPiste(0, "/ressources/Musiques/lose.wav");
            Coordonnees c = new Coordonnees(getHeros().getPixels().getX(), getHeros().getPixels().getY() - 32);
            getHeros().setMouvement(false);
            getHeros().getSprite().setPosition(c.getX(), c.getY());
            if (getHeros().isRegardeAGauche()) {
                // Animation a gauche
                Positionnable.GESTION_ANIM.ajouterPositionnableAnime(getHeros(), Images.deathRight);
            } else {
                Positionnable.GESTION_ANIM.ajouterPositionnableAnime(getHeros(), Images.deathLeft);
            }
            // Sinon le joueur a gagné le niveau
        } else {
            getHeros().setMouvement(false);
            audioTracks.ajouterPiste(0, "/ressources/Musiques/win.wav");
            // Joue l'animation et la musique de fin de niveau
            Positionnable.GESTION_ANIM.ajouterPositionnableAnime(getHeros(), Images.win);
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            audioTracks.clearListePistes();

            // Passe au prochain niveau
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

    private void detecterObjet(Coordonnees posHeros) {
        // On test si le joueur ne se trouve pas sur un lingot
        Item item = getObj(posHeros);
        if (item instanceof Lingot) {
            // On incremente le score du joueur
            Heros h = (Heros) getHeros();
            h.augmenterScore(((Lingot) item).getValeur());
            // On teste si le Heros a tous les lingots
            checkScore(h.getScore());
            // On joue le son de lingot ramassé
            audioTracks.ajouterPiste(0, "/ressources/Musiques/ding.wav");
            // On le retire de la carte
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
        // Si le joueur fini le niveau
        if (getEnv(getHeros().getPosition()) instanceof BlocPortailFinNiveau) {
            fini = true;
            // Si le joueur se fait attraper
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
}*/
