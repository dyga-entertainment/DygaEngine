package java.MVC.View;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JPButton extends JButton{
    private String texte;

    public JPButton(String text, String image1, String image2) {
        this.texte = text;
        this.repaint();
        //this.setIcon(new ImageIcon(Thread.currentThread().getContextClassLoader().getResource(ResourcesPaths.SPRITE_UI_BUTTONS_PATH + image1)));
        this.setVisible(true);
        this.setBorder(null);
        //this.repaint();
        this.setContentAreaFilled(false);
        this.addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseClicked(MouseEvent e) {
            }
            public void mousePressed(MouseEvent e) {
                //setIcon(new ImageIcon(Thread.currentThread().getContextClassLoader().getResource(ResourcesPaths.SPRITE_UI_BUTTONS_SELECTED_PATH + image2)));
                setIcon(new ImageIcon(Thread.currentThread().getContextClassLoader().getResource(image2)));
                /*try {
                        bg = ImageIO.read(new File("./ressources/Images/"+image2));
                } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                }*/
            }
            public void mouseReleased(MouseEvent e) {
                //setIcon(new ImageIcon(Thread.currentThread().getContextClassLoader().getResource(ResourcesPaths.SPRITE_UI_BUTTONS_PATH + image1)));
                setIcon(new ImageIcon(Thread.currentThread().getContextClassLoader().getResource(image1)));
                /*try {
                        bg = ImageIO.read(new File("./ressources/Images/"+image1));
                } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                }*/
            }
            public void mouseExited(MouseEvent e) {
            }
        });
        this.repaint();
        this.validate();
    }

    public String getTextO() {
            return this.texte;
    }

    void addActionListener() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
}
