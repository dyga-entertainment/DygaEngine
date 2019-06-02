package java.MVC.View.Buttons;

import java.MVC.View.MenuViews.View;

import javax.swing.*;
import java.awt.*;

import static java.Utils.helper.Images.getImageFromPath;

public class StandardButton extends JButton {

    /** Basic description of the button */
    private String name;
    private String standardImage;
    private String selectedImage;

    /** Control the transparancy of the button */
    private float alpha = 1.0f;

    public StandardButton(View parentView, String text, String standardImage, String selectedImage) {
        this(parentView, text, standardImage, selectedImage, true);
    }

    public StandardButton(View parentView, String text, String standardImage, String selectedImage, boolean isEnable) {
        super();

        this.name = text;
        this.standardImage = standardImage;
        this.selectedImage = selectedImage;

        this.setBorder(null);
        this.setEnabled(isEnable);
        this.setContentAreaFilled(false);

        if(!standardImage.isEmpty()) {
            //this.setIcon(new ImageIcon(getImageFromPath(ResourcesPaths.SPRITE_UI_BUTTONS_PATH + standardImage)));
            this.setIcon(new ImageIcon(getImageFromPath(standardImage)));
        }

        //this.lis

        /*
        this.addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseClicked(MouseEvent e) {
            }
            public void mousePressed(MouseEvent e) {
                if(!selectedImage.isEmpty()) {
                    setIcon(new ImageIcon(getImageFromPath(ResourcesPaths.SPRITE_UI_BUTTONS_SELECTED_PATH + selectedImage)));
                }

            }
            public void mouseReleased(MouseEvent e) {
                if(!standardImage.isEmpty()) {
                    setIcon(new ImageIcon(getImageFromPath(ResourcesPaths.SPRITE_UI_BUTTONS_PATH + standardImage)));
                }
            }
            public void mouseExited(MouseEvent e) {
            }
        });*/
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

    @Override
    public void paint(Graphics g) {
        //System.out.println("[Rendering] Repaint button " + this.name);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.alpha));
        super.paint(g2);
        g2.dispose();

    }
}
