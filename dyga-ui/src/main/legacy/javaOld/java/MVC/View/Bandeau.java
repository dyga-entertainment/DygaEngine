package java.MVC.View;

import java.Utils.helper.Images;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public abstract class Bandeau extends JPanel {
    
    private Image background;
    
    /*public Bandeau() {
        this.background = Images.getImageFromPath(ResourcesPaths.SPRITE_UI_PATH + "bandeau_menu.png");
    }*/
    public Bandeau() {
        this.background = Images.getImageFromPath("bandeau_menu.png");
    }

    public abstract int getNbButton();

    public abstract JPButton getButton(int i);

    public void paint(Graphics g) { 
    g.drawImage(background,0,0,getSize().width, getSize().height, this);
    for (int i = 0; i < this.getNbButton(); i++) {
        this.getButton(i).repaint();
            this.getButton(i).setOpaque(true);
        }
    }
	
}