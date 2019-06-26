package com.dyga.Engine.Source.Components.Renderer;

import com.dyga.Engine.Source.Components.Component;
import com.dyga.Engine.Source.Utils.Images;

import java.awt.image.BufferedImage;

public class SpriteRenderer extends Component {

    /** sprite associated to the entity **/
    private BufferedImage sprite;

    /** BufferedImage parameters **/
    private int width;
    private int height;

    public SpriteRenderer(String path) {
        this(path, 100, 100);
    }

    public SpriteRenderer(String path, int width, int height) {
        this.sprite = Images.getImageFromPath(path);
        this.width = width;
        this.height = height;
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


    // TODO
}
