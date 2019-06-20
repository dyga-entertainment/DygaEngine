package com.dyga.Engine.Source.MVC.View.Menu.Component;

import javax.swing.*;

import com.dyga.Engine.Source.MVC.View.Game.EntityView;
import com.dyga.Engine.Source.Main.Game;
import com.dyga.Engine.Source.Utils.Images;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ViewPanel extends JPanel {

    private Background background;

    // TEST
    private EntityView entity;

    private int width;
    private int heigth;

    // the thread that performs the animation
    private Thread animator;
    // END TEST

    public ViewPanel(int width, int height) {
        this(width, height, "");
    }

    public ViewPanel(int width, int height, String urlImage) {
        super();
        this.background = new Background();
        this.background.startingCoordinate = null;
        this.background.preferredSize = null;

        this.setBackgroundImage(urlImage);

        // Do we need all that stuff ? ==
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension scrDim = tk.getScreenSize();
        this.width = scrDim.width;
        this.heigth = scrDim.height;

        setBackground(Color.white);
        setPreferredSize(scrDim);

        setFocusable(true);
        requestFocus();

        // need end ==

    }

    public void setBackgroundImage(String urlImage) {
        BufferedImage image;
        if(!urlImage.isEmpty() && (image = Images.getImageFromPath(urlImage)) != null) {
            this.background.backgroundImage = image;
        }
    }

    public void setStartingCoordinate(int x, int y) {
        this.background.startingCoordinate = new Point(x, y);
    }

    public void setPreferredSize(int width, int height) {
        this.background.preferredSize = new Dimension(width, height);
    }

    public void addEntity(EntityView entityView) {
        this.entity = entityView;
    }

    // wait for the JPanel to be added to the JFrame before starting
    public void addNotify() {
        super.addNotify();   // creates the peer

        /* NOP !
        if (animator == null || !Game.running) {  // start the thread
            animator = new Thread(this);
            animator.start();
        }*/
    }

    public void render() {
        // TODO : Render all the entities
    }

    /*
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Delegate the work to the background class
        background.paintComponent(g, getWidth(), getHeight(), this);

        // Additional painting here ?
        if(entity != null)
            entity.paint(g, this);
    }*/
}
