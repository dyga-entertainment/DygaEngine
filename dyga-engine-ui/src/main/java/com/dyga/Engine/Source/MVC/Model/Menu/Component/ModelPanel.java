package com.dyga.Engine.Source.MVC.Model.Menu.Component;

import com.dyga.Engine.Source.MVC.Model.Menu.Enums.DisplayOption;
import com.dyga.Engine.Source.MVC.Model.Menu.Enums.ModelLayout;
import com.dyga.Engine.Source.MVC.Model.Menu.Structs.Font;
import com.dyga.Engine.Source.MVC.Model.Menu.Structs.Layouts.CardLayout;
import com.dyga.Engine.Source.MVC.Model.Menu.Structs.Layouts.Layout;
import java.util.ArrayList;
import java.util.List;

public class ModelPanel extends ModelComponent {

    private Layout layout;
    private List<ModelComponent> components;
    private DisplayOption option;

    /** Value only useful for CardLayout */

    // ♥
    public ModelPanel() {
        super();
        this.components = new ArrayList<>();
        this.layout = new Layout();
        this.layout.name = ModelLayout.FlowLayout;
    }

    @Override
    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public Layout getLayout() {
        return this.layout;
    }

    public void add(ModelComponent component) {
        this.components.add(component);
    }

    public List<ModelComponent> getChildrenComponents() {
        return this.components;
    }

    @Override
    public String toString() {
        return super.toString() + ", Background image = " + this.backgroundImageUrl + ", layout = " + layout +
            "Children = " + components.toString();
    }

    public void previousCardView() {
        changeCardView(-1);
    }

    public void nextCardView() {
        changeCardView(1);
    }

    public void changeCardView(int value) {
        // If the layout is CardLayout then apply the next operation
        if(this.layout instanceof CardLayout) {
            ((CardLayout)this.layout).currentActiveView += value;
        }
        System.out.println("[DEBUG] Final view in card layout = " + ((CardLayout)this.layout).currentActiveView);
    }

    /** Empty implementations */
    @Override
    public void addText(String text) { }
    @Override
    public void setNextView(String nextView) { }
    @Override
    public void setPreferredSize(int width, int height) { }
    @Override
    public void addPressedImageUrl(String pressedImage) { }
    @Override
    public void addFont(Font font) { }
}
