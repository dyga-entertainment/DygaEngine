package com.dyga.Engine.Source.MVC.Model.Game;

import com.dyga.Engine.Source.MVC.Model.Game.Structs.Stats;
import com.dyga.Engine.Source.Utils.Images;
import com.dyga.Engine.Source.Utils.Math.Position2D;
import java.awt.image.BufferedImage;
import java.util.UUID;

public class EntityModel {

    protected UUID uuid;

    /** The name of the entity (used as id for JSON) */
    private String name;

    /** sprite associated to the entity */
    private BufferedImage currentSprite;

    /** All the stats that belong to the entity */
    private Stats stats;

    /** The position of this entity */
    private Position2D position;

    /**
     * Empty constructor
     */
    public EntityModel() {
        this.uuid = UUID.randomUUID();
        this.name = "";
        this.currentSprite = null;
        this.stats = null;
        this.position = null;
    }

    /**
     * Default Constructor when initializing an EntityModel
     * @param name
     * @param imageUrl
     * @param stats
     * @param position
     */
    public EntityModel(String name, String imageUrl, Stats stats, Position2D position) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.currentSprite = Images.getImageFromPath(imageUrl);
        this.stats = stats;
        this.position = position;
    }

    public UUID getUuid() {
        return uuid;
    }

    public BufferedImage getSprite() {
        return this.currentSprite;
    }

    public Position2D getPosition() {
        return (Position2D) this.position.clone();
    }
}
