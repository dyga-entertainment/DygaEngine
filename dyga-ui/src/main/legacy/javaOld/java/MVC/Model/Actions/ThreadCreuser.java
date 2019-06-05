/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.MVC.Model.Actions;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.MVC.Model.bloc.Bloc;
import java.MVC.Model.bloc.BlocTerre;
import java.MVC.Model.personnage.Heros;
import java.Utils.helper.Images;
import java.MVC.Model.jeu.Carte;
import java.MVC.Model.jeu.Coordonnees;
import java.MVC.Model.jeu.Positionnable;
import java.MVC.Model.item.Gaz;
import java.MVC.Model.item.PetiteBombe;

/**
 *
 * @author jarvis
 */
public class ThreadCreuser extends Thread implements Observer {
    
    private boolean fini;
    private Carte matrice;
    private List<Queue<BufferedImage>> animations;
    private List<Positionnable> elements;
    
    //private BlocTerre bloc;
    
    //private BufferedImage[] animation;
    
    public ThreadCreuser() {
        this.elements = new ArrayList<Positionnable>();
        this.animations = new ArrayList<Queue<BufferedImage>>();
        fini = false;
    }
    
    public void ajouterPositionnableAnime(Positionnable p, BufferedImage[] a) {
        this.elements.add(p);
        this.animations.add(new LinkedList(Arrays.asList(a)));
    }
    
    public void finirAnimation() {
        this.fini = true;
    }

    @Override
    public void run() {
        BufferedImage img;
        while(!fini) {
            /* Pour toutes les animations a faire dans un instant t */
            for(int i = 0; i < animations.size() ; i++) {
                /* Cas ou l'animation est creuser un bloc */
                Positionnable p = elements.get(i);
                if(p instanceof BlocTerre) {
                    System.out.println("Bloc Terre");
                    /* Si ce n'est pas la fin de l'animation */
                    if((img = this.animations.get(i).poll()) != null) {
                        /* On joue une frame de l'animation */
                        p.getSprite().modifierImage(img);
                        //p.getSprite().notifierVueChangement();
                        /* Petite Pause */
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Bloc.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    /* Si on est arrivé à la fin on lance un timer */
                    } else {
                        /* On fourni le temps et le bloc pour savoir le reconstruire */
                        Timer t = new Timer(8000, p);
                        t.addObserver(this);
                        new Thread(t).start();
                        /* On supprime le bloc de la liste d'animation */
                        /* Ainsi que son animation */
                        this.elements.remove(p);
                        this.animations.remove(i);
                    }
                /* Cas ou l'animation est une bombe */
                } else if (p instanceof Gaz) {
                    System.out.println("Psssshhhh");
                    /* Si la bombe est actif (on fait animation meche) */
                    if(((Gaz) p).isActif()) {
                        /* Si ce n'est pas la fin de l'animation */
                        if((img = this.animations.get(i).poll()) != null) {
                            /* On joue une frame de l'animation */
                            p.getSprite().modifierImage(img);
                            /* Petite Pause */
                            try {
                                System.out.println("Pause");
                                Thread.sleep(300);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Bloc.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            ((Gaz) p).setActif(false);
                            /* On arrete l'animation */
                            this.elements.remove(p);
                            this.animations.remove(i);
                        }
                    } else {
                        ((Gaz) p).setActif(false);
                        /* On arrete l'animation */
                        this.elements.remove(p);
                        this.animations.remove(i);
                    }
                } else if (p instanceof PetiteBombe) {
                    /* Si la bombe est actif (on fait animation meche) */
                    if(((PetiteBombe) p).isActif()) {
                        System.out.println("Meche !!");
                        /* Si ce n'est pas la fin de l'animation */
                        if((img = this.animations.get(i).poll()) != null) {
                            /* On joue une frame de l'animation */
                            p.getSprite().modifierImage(img);
                            /* Petite Pause */
                            try {
                                System.out.println("Pause");
                                Thread.sleep(500);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Bloc.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            /* Si on est arrivé à la fin on lance un timer */
                            /* En fournissant la bombe pour savoir le reconstruire */
                            Timer t = new Timer(100, p);
                            t.addObserver(this);
                            new Thread(t).start();
                            this.elements.remove(p);
                            this.animations.remove(i);
                        }
                    /* sinon on fait l'explosion */
                    } else {
                        System.out.println("EXPLOSION");
                        /* Si ce n'est pas la fin de l'animation */
                        if((img = this.animations.get(i).poll()) != null) {
                            /* On joue une frame de l'animation */
                            p.getSprite().modifierImage(img);
                            /* Petite Pause */
                            try {
                                System.out.println("Pause");
                                Thread.sleep(80);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Bloc.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            /* Si c'est la premiere image */
                            System.out.println(this.animations.get(i).size());
                            if(this.animations.get(i).size() == Images.explosion.length-1) {
                                PetiteBombe bombe = (PetiteBombe) p;
                                /* destruction des blocs environnents */
                                for(Coordonnees c : bombe.getBlocsExploses()) {
                                    try {
                                        this.matrice.getBloc(c).destruction();
                                    } catch(NullPointerException | ArrayIndexOutOfBoundsException e) {}
                                }
                            }
                        } else {
                            /* Si on est arrivé à la fin on lance un timer */
                            /* En fournissant la bombe pour savoir le reconstruire */
                            Timer t = new Timer(50, p);
                            t.addObserver(this);
                            new Thread(t).start();
                            this.elements.remove(p);
                            this.animations.remove(i);
                        }
                    }
                /* Cas ou l'animation est un Heros */
                } else if(p instanceof Heros) {
                    /* On joue la mort du heros */
                    if((img = this.animations.get(i).poll()) != null) {
                        /* On joue une frame de l'animation */
                        p.getSprite().modifierImage(img);
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Bloc.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
            /* Petite Pause */
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Bloc.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.println(this.animations.size());
        }
        System.out.println("THE END");
        this.animations = null;
        this.elements = null;
        /*
        for(Queue<BufferedImage> q : this.animations) {
            q.removeAll(q);
        }*/
    }
    
    /**
     * On reçoit l'objet a mettre a jour pour finir son animation
     * @param o
     * @param arg 
     */
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("UPDATE");
        Positionnable p = (Positionnable) arg;
        if(p instanceof BlocTerre) {
            /* On reconstruit le bloc de terre */
            BlocTerre blocT = (BlocTerre) arg;
            blocT.reconstruction();
            System.out.println("Fin");
            blocT.setConcret(true);
        } else if(p instanceof PetiteBombe) {
            System.out.println("Bombe");
            PetiteBombe bombe = (PetiteBombe) p;
            /* Dans le cas ou on arrive au bout de l'animation meche */
            if(bombe.isActif()) {
                /* On passe a l'explosion */
                Coordonnees c = new Coordonnees(bombe.getPixels().getX()-64, bombe.getPixels().getY() - 64);
                bombe.setActif(false);
                bombe.getSprite().modifierImage("/ressources/Images/explosion1.png");
                bombe.getSprite().setPosition(c.getX(), c.getY());
                this.ajouterPositionnableAnime(p, Images.explosion);
            /* Sinon */
            } else {
                /* On ne fait rien */
            }
        }
    }

    public void activerAnimation() {
        this.start();
    }

    public void setMatrice(Carte c) {
        this.matrice = c;
    }
    /*
    public void start() {
        this.run();
    }
    
    	this.bloc.setConcret(false);
        int i = 1;
        On detruit
        //while(i < 6) {
        for 
            this.bloc.getSprite().modifierImage("/ressources/Images/bloc_terre_destruction"+i+".png");
            this.bloc.mettreAJour();
            i++;
            System.out.println("Destruction : "+i);
            try {
                System.out.println("Pause");
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(Bloc.class.getName()).log(Level.SEVERE, null, ex);
            }


        }
        this.bloc.getSprite().modifierImage("/ressources/Images/bloc_vide.png");
        //this.bloc.setDetruit(false);
        /* Temps d'attente avant reconstruction */


    //}
    

    
}
