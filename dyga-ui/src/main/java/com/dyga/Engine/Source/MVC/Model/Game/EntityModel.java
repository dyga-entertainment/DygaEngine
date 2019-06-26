package com.dyga.Engine.Source.MVC.Model.Game;

import com.dyga.Engine.Source.Components.Component;
import com.dyga.Engine.Source.Components.Physics.Transform;
import com.dyga.Engine.Source.Entity.Entity;
import com.dyga.Engine.Source.Components.Gameplay.GameplayScript;
import com.dyga.Engine.Source.Components.Renderer.SpriteRenderer;
import com.dyga.Engine.Source.MVC.Model.Game.Structs.Stats;
import com.dyga.Engine.Source.Utils.Images;
import com.dyga.Engine.Source.Utils.Math.Position2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
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

    private List<GameplayScript> modelComponents;

    /**
     * Constructors
     */
    public EntityModel() {
        this("", "", null, null);
    }

    public EntityModel(Entity entity) {
        this(entity.getClass().toString(), entity.getComponent(SpriteRenderer.class).getSprite(), null, entity.getComponent(Transform.class).getPosition());

        for(Component component : entity.getComponents(GameplayScript.class)) {
            // cast
            GameplayScript script = (GameplayScript) component;
            this.modelComponents.add(script);
        }
    }

    public EntityModel(String name, String imageUrl, Stats stats, Position2D position) {
        this(name, !imageUrl.isEmpty() ? Images.getImageFromPath(imageUrl) : null, stats, position);
    }

    public EntityModel(String name, BufferedImage imageUrl, Stats stats, Position2D position) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.currentSprite = imageUrl;
        this.stats = stats;
        this.position = position;

        modelComponents = new ArrayList<>();
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

    public void update() {
        System.out.println("Entity update called !");
        for (GameplayScript scripts : modelComponents) {
            scripts.update();
        }
    }
}
