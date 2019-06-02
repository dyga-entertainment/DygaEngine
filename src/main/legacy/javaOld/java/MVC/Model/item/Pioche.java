package java.MVC.Model.item;

import java.MVC.Model.jeu.Coordonnees;

public class Pioche extends Gadget {

    public Pioche(Coordonnees position) {
        super(position, "/ressources/Images/pioche.png");
    }

    @Override
    public void utiliser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
