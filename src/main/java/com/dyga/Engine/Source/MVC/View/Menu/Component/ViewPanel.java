package Source.MVC.View.Menu.Component;

import Source.MVC.View.Game.EntityView;

import javax.swing.*;
import com.dyga.Engine.Source.Utils.Images;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ViewPanel extends JLayeredPane {

    private Source.MVC.View.Menu.Component.Background background;

    // TEST
    private EntityView entity;
    // END TEST

    public ViewPanel(String urlImage) {
        super();
        this.background = new Source.MVC.View.Menu.Component.Background();
        this.background.startingCoordinate = null;
        this.background.preferredSize = null;

        BufferedImage image;
        if((image = Images.getImageFromPath(urlImage)) != null) {
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        /** Delegate the work to the background class */
        background.paintComponent(g, getWidth(), getHeight(), this);

        /** Additional painting here ? */
        if(entity != null)
            entity.paint(g, this);
    }
}
