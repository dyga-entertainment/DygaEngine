package com.dyga.Engine.Source.MVC.View.Menu.Component;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * This class describe how to handle a background image.
 * If a view class need an image as backgroud, it should use composition to gather that class.
 */
public class Background {

    /** Current display background image */
    protected Image backgroundImage;
    protected Point startingCoordinate;
    protected Dimension preferredSize;

    /**
     * TODO
     * @param g
     * @param width
     * @param height
     * @param observer
     */
    public void paintComponent(Graphics g, int width, int height, ImageObserver observer) {
        //System.out.println("[Rendering] PaintComponent call from ViewPanel");
        if(startingCoordinate == null || preferredSize == null) {
            g.drawImage(
                backgroundImage,
                0,
                0,
                width,
                height,
                observer
            );
        } else {
            g.drawImage(
                backgroundImage,
                startingCoordinate.x,
                startingCoordinate.y,
                preferredSize.width,
                preferredSize.height,
                observer
            );
        }
    }
}
