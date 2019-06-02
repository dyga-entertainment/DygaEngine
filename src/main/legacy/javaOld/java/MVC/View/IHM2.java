package java.MVC.View;

import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IHM2 extends JPanel {
	
	private JLabel pa;
    private Image bg;

    /** Surcharge de la fonction paint() pour afficher notre image */ 
     public void paint(Graphics g) { 
    	 g.drawImage(bg,0,0,null); 
    }
    
	public IHM2() {
		this.setLayout(new BorderLayout());
		this.pa = new JLabel();
		//this.pa.setIcon(new ImageIcon("doge.jpg"));
		this.add(this.pa, BorderLayout.CENTER);

		//this.bg = Images.getImageFromPath(ResourcesPaths.SPRITE_UI_PATH + "Views.png");
	}

}
