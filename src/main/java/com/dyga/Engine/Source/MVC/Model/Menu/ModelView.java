package com.dyga.Engine.Source.MVC.Model.Menu;

import com.dyga.Engine.Source.MVC.Model.Menu.Component.ModelComponent;
import com.dyga.Engine.Source.MVC.Model.Menu.Component.ModelPanel;

public class ModelView {

    /** A model view doesn't need UUID since it use unique name directly */
    private String name;

    private int zDepth;

    private ModelComponent viewContent;

    public ModelView() {
        this("", 0, null);
    }

    public ModelView(String name) {
        this(name, 0, null);
    }

    public ModelView(String name, int zDepth, ModelComponent viewContent) {
        this.name = name.toLowerCase();
        this.zDepth = zDepth;
        this.viewContent = viewContent;
    }

    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    public String getName() {
        return this.name;
    }

    public void setZDepth(int zDepth) {
        this.zDepth = zDepth;
    }

    public int getZDepth() {
        return this.zDepth;
    }

    public void addView(ModelComponent viewContent) {
        this.viewContent = viewContent;
    }

    public ModelPanel getModelComponent() {
        return (ModelPanel)this.viewContent;
    }
}
