package com.dyga.Engine.Source.MVC.View.Menu.Component;

import javax.swing.*;
import com.dyga.Engine.Source.MVC.Model.Menu.ModelView;
import java.util.Dictionary;
import java.util.List;

public class ViewLayer extends JLayeredPane {

    /** We store ModelView in order to their zDepth */
    Dictionary<Integer, List<ModelView>> viewsByDepth;

    public ViewLayer(Dictionary<Integer, List<ModelView>>views) {
        this.viewsByDepth = views;
    }

    public List<ModelView> getModelViewsByDepth(int zDEpth) {
        return this.viewsByDepth.get(zDEpth);
    }

    // Useful ?
    /*
    public ModelView getModelViewByName(String name) {
        return this.viewsByDepth.get(name);
    }*/

    public void addModelView(ModelView model) {
        this.viewsByDepth.get(model.getZDepth()).add(model);
    }
}
