package Source.MVC.View.Menu.Component;

import javax.swing.*;
import com.dyga.Engine.Source.MVC.Model.Menu.Enums.DisplayOption;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

import static com.dyga.Engine.Source.Utils.Images.getImageFromPath;

public class ViewButton extends JButton {

    /** Basic description of the button */
    private String name;
    private String standardImage;
    private String selectedImage;
    private Dimension dimension;

    private Source.MVC.View.Menu.Component.Background background;
    private DisplayOption option;

    /** Control the transparancy of the button */
    private float alpha = 1.0f;

    /** Interaction field */
    private String nextView;
    private Dictionary<String, String> listeners = new Hashtable<>();

    public ViewButton() {
        this("",
            "",
            true,
            "");
    }

    public ViewButton(String text, String standardImage, boolean isEnable, String nextView) {
        super();
        this.background = new Source.MVC.View.Menu.Component.Background();

        this.name = text;
        this.standardImage = standardImage;
        this.nextView = nextView;

        this.setBorder(null);
        this.setEnabled(isEnable);
        //this.setContentAreaFilled(false);
    }

    public void setBackgroundImage(String standardImage, Point startingPoint, Dimension preferredSize) {
        //this.selectedImage = selectedImage;
        this.standardImage = standardImage;
        if(!standardImage.isEmpty()) {
            // Create the new image
            //ImageIcon image = new ImageIcon(getImageFromPath(ResourcesPaths.SPRITE_UI_BUTTONS_PATH + standardImage));
            ImageIcon image = new ImageIcon(getImageFromPath(standardImage));
            // Create a new image scaled
            Image scaledImage;
            if(this.option == DisplayOption.Scale && getPreferredSize().width != 0 && getPreferredSize().height != 0) {
                    scaledImage = image.getImage().getScaledInstance(getPreferredSize().width, getPreferredSize().height, Image.SCALE_DEFAULT);
            } else {
                scaledImage = image.getImage();
            }
            //scaledImage = image.getImage();
            // Finally add it to the button
            this.background.backgroundImage = new ImageIcon(scaledImage).getImage();
            //this.setIcon(new ImageIcon(scaledImage));
        }
    }

    public Image getImage() {
        return this.background.backgroundImage;
    }

    public String getImageUrl() {
        return this.standardImage;
    }

    public void setDisplayOption(DisplayOption option) {
        this.option = option;
    }

    @Override
    public void setPreferredSize(Dimension desiredDimension) {
        super.setPreferredSize(desiredDimension);
        this.dimension = desiredDimension;
    }

    public void setNextView(String nextViewName) {
        this.nextView = nextViewName;
    }

    public String getNextView() {
        return this.nextView;
    }

    public String getButtonName() {
        return this.name;
    }

    public String getStandardImage() {
        return this.standardImage;
    }

    public String getSelectedImage() {
        return this.selectedImage;
    }

    public void setTransparency(float alpha) {
        this.alpha = alpha;
    }

    public void setListenerMethodsDict(Dictionary<String, String> dict) {
        this.listeners = dict;
    }

    public Dictionary<String, String> getListenersMap() {
        return this.listeners;
    }

    @Override
    public void paint(Graphics g) {
        //System.out.println("[Rendering] Repaint button " + this.name);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.alpha));
        super.paint(g2);
        g2.dispose();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        /** Delegate the work to the background class */
        if(background != null) {
            background.paintComponent(g, getWidth(), getHeight(), this);
        }
    }
}
