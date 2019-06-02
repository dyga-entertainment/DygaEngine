package com.dyga.Engine.Source.MVC.Model.Menu.Component;

import com.dyga.Engine.Source.MVC.Model.Menu.Structs.Color;
import com.dyga.Engine.Source.MVC.Model.Menu.Structs.Font;
import com.dyga.Engine.Source.MVC.Model.Menu.Structs.Layouts.Layout;

public class ModelLabel extends ModelComponent {

    private String text;

    private Font font;

    public ModelLabel() {
        super();
        this.text = "";
    }

    @Override
    public void addText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setFont(String name, int size, Color color, int style) {
        this.font = new Font();
        // Assign struct fields
        this.font.name = name;
        this.font.size = size;
        this.font.color = color;
        this.font.style = style;
    }

    @Override
    public void addFont(Font font) {
        this.font = font;
    }

    public Font getFont() {
        return this.font;
    }

    /** Empty implementations */
    @Override
    public void setNextView(String nextView) { }
    @Override
    public void setPreferredSize(int width, int height) { }
    @Override
    public void addPressedImageUrl(String pressedImage) { }
    @Override
    public void setLayout(Layout layout) { }
    @Override
    public void setBackgroundOptions(String displayOptions) { }
}