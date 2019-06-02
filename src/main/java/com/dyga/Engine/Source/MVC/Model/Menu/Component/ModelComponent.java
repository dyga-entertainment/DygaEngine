package com.dyga.Engine.Source.MVC.Model.Menu.Component;

import com.dyga.Engine.Source.MVC.Model.Menu.Enums.DisplayOption;
import com.dyga.Engine.Source.MVC.Model.Menu.Structs.Font;
import com.dyga.Engine.Source.MVC.Model.Menu.Structs.Layouts.Layout;
import java.awt.*;
import java.util.UUID;

public abstract class ModelComponent {

    protected UUID uuid;

    /** Background image related info **/
    protected String backgroundImageUrl;
    protected Point backgroundImageStartingPoint;
    protected Dimension backgroundImagePreferredSize;
    private DisplayOption option;

    /** Others settings info **/
    protected boolean opaque;
    protected boolean enable;

    /** Layout related */
    protected int layoutIndex;
    protected String borderLayoutConstraint;

    public ModelComponent() {
        this.backgroundImageUrl = "";
        this.opaque = true;
        this.enable = true;
        this.layoutIndex = -1;
        this.borderLayoutConstraint = "";
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public void generateRandomUuid() {
        this.uuid = UUID.randomUUID();
    }

    public void addBackgroundImageUrl(String backgroundImage) {
        this.backgroundImageUrl = backgroundImage;
    }

    public String getBackgroundImageUrl(){
        return this.backgroundImageUrl;
    }

    public void setOpaque(boolean isOpaque) {
        this.opaque = isOpaque;
    }

    public boolean isOpaque() {
        return opaque;
    }

    public void setEnable(boolean isEnable) {
        this.enable = isEnable;
    }

    public boolean isEnable() {
        return this.enable;
    }

    @Override
    public String toString() {
        return "isOpaque = " + opaque +
            ", enable = " + enable;
    }

    public boolean hasLayoutConstraint() {
        return !this.borderLayoutConstraint.isEmpty() || this.layoutIndex != -1;
    }

    public boolean hasBorderLayoutLayoutConstraint() {
        return !this.borderLayoutConstraint.isEmpty();
    }

    public int getIndexLayout() {
        return this.layoutIndex;
    }

    public void addBorderLayoutConstraints(String borderLayoutConstraints) {
        switch (borderLayoutConstraints) {
            case "NORTH": this.borderLayoutConstraint = BorderLayout.NORTH; break;
            case "WEST": this.borderLayoutConstraint = BorderLayout.WEST; break;
            case "EAST": this.borderLayoutConstraint = BorderLayout.EAST; break;
            case "SOUTH": this.borderLayoutConstraint = BorderLayout.SOUTH; break;
            default: this.borderLayoutConstraint = BorderLayout.CENTER; break;
        }
    }

    public void setBackgroundStartingPoint(int x, int y) {
        this.backgroundImageStartingPoint = new Point(x, y);
    }

    public Point getBackgroundStartingPoint() {
        return this.backgroundImageStartingPoint;
    }

    public void setBackgroundPreferredSize(int width, int height) {
        this.backgroundImagePreferredSize = new Dimension(width, height);
    }

    public Dimension getBackgroundPreferredSize() {
        return this.backgroundImagePreferredSize;
    }

    public String getBorderLayoutContraints() {
        return this.borderLayoutConstraint;
    }

    public void setBackgroundOptions(String displayOptions) {
        switch (displayOptions) {
            case "Scale": this.option = DisplayOption.Scale; break;
            case "Tile": this.option = DisplayOption.Tile; break;
            default: this.option = DisplayOption.Plain; break;
        }
    }

    public DisplayOption getBackgroundOptions() {
        return this.option;
    }

    public void setBackgroundImageUrl(String newUrl) {
        this.backgroundImageUrl = newUrl;
    }

    /** Abstract methods */
    public abstract void setLayout(Layout layout);
    public abstract void addText(String text);
    public abstract void setNextView(String nextView);
    public abstract void setPreferredSize(int width, int height);
    public abstract void addPressedImageUrl(String pressedImage);
    public abstract void addFont(Font font);
}
