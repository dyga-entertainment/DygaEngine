package com.dyga.Engine.Source.Components.Renderer;

import com.dyga.Engine.Source.Components.Component;
import com.dyga.Engine.Source.Components.Physics.Transform;
import com.dyga.Engine.Source.Utils.Images;
import com.dyga.Engine.Source.Utils.Math.Position2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpriteRenderer extends Component {

    /** sprite associated to the entity **/
    private BufferedImage sprite;

    /** BufferedImage parameters **/
    private int width;
    private int height;

    public SpriteRenderer(String path) {
        this(path, 0, 0);
    }

    public SpriteRenderer(String path, int width, int height) {
        this.sprite = Images.getImageFromPath(path);
        this.width = width == 0 ? this.sprite.getWidth() : width;
        this.height = height == 0 ? this.sprite.getHeight() : height;
    }

    public BufferedImage getSprite() {
        return this.sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public boolean draw(Graphics dbg, Position2D position, Position2D scale) {
        return dbg.drawImage(
            this.sprite,
            (int)position.getX(),
            (int)position.getY(),
            this.width * (int)scale.getX(),
            this.height * (int)scale.getY(),
            null
        );
    }
    // TODO
}
