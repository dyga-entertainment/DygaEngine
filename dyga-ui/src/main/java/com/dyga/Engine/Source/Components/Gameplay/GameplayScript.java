package com.dyga.Engine.Source.Components.Gameplay;

import com.dyga.Engine.Source.Components.Component;
import com.dyga.Engine.Source.Components.ComponentType;
import com.dyga.Engine.Source.Entity.Entity;

/**
 * MonoBehavior class.
 */
public abstract class GameplayScript extends Component {

    // The entity attached to this script component
    protected Entity entity;

    public void setParentEntity(Entity entity) {
        this.entity = entity;
    }

    // Useful ?
    private ComponentType type;

    public void awake() {}

    public void start() {}

    public void update() {}

    public void onTriggerEnter() {}

    public void onTriggerExit() {}

    public void onCollisionsEnter() {} // ??

}
