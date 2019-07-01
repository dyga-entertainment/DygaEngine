package com.dyga.Engine.Source.Entity;

import com.dyga.Engine.Source.Components.Component;
import com.dyga.Engine.Source.Components.Gameplay.GameplayScript;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Entity {

    private List<Component> components;

    public Entity() {
        this.components = new ArrayList<>();
    }

    public void addComponent(Component component) {
        this.components.add(component);
    }

    public void addComponents(List<Component> components) {
        this.components.addAll(components);

        // Register himself to his components
        for (Component component : components) {
            if(component instanceof GameplayScript) {
                ((GameplayScript) component).setParentEntity(this);
            }
        }
    }

    public <T extends Component> T getComponent(Class<T> componentClass) {
        Optional component = components.stream()
            .filter(c -> componentClass.isInstance(c))
            .findFirst();
        return component.isPresent() ? (T)component.get() : null;
    }


    public <T extends Component> Component[] getComponents(Class<T> componentClass) {
        Component[] component = components.stream()
            .filter(c -> componentClass.isInstance(c))
            .toArray(Component[]::new);
        return component;
    }

    public void update() {
        for (Component component : this.components) {
            // Is it the only component with update ?
            if(component instanceof GameplayScript) {
                ((GameplayScript) component).update();
            }
        }
    }
}
