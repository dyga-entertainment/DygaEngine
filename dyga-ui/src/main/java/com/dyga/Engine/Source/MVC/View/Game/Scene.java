package com.dyga.Engine.Source.MVC.View.Game;

import com.dyga.Engine.Source.Entity.Entity;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Scene extends JPanel {

    private List<Entity> entities;

    public Scene() {
        this.entities = new ArrayList<>();
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public List<Entity> getEntities() {
        return entities;
    }
}
