package java.MVC.View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class IHMB5 extends Bandeau {
	
	private int nbBouton;
	// Normalement un tableau
	private JPButton appliquer;
	private JPButton config;
	private JPButton quitter;
	
	public IHMB5() {
		this.nbBouton = 3;
		this.setLayout(new BorderLayout());
		this.add(new JPanel(), BorderLayout.NORTH);
		JPanel jpa2 = new JPanel(); 
		jpa2.setLayout(new GridLayout(2,3,20,0));

		this.appliquer = new JPButton("Appliquer","bouton_appliquer1.png","bouton_appliquer2.png");
		this.config = new JPButton("Configuration des touches","bouton_config1.png","bouton_config2.png");
		this.quitter = new JPButton("Retour","bouton_retour1.png","bouton_retour2.png");
		
		jpa2.add(this.appliquer);
		jpa2.add(this.config);
		jpa2.add(this.quitter);
		jpa2.add(new JPanel());
		jpa2.add(new JPanel());
		jpa2.add(new JPanel());
		this.add(jpa2, BorderLayout.CENTER);
	}
	
	public JPButton getButton(int i) {
		JPButton b;
		switch(i) {
			case 0 :
				b = this.appliquer;
				break;
			case 1 :
				b = this.config;
				break;
			default : // case 2
				b = this.quitter;
				break;
		}
		return b;
	}
	
	public int getNbButton() {
		return this.nbBouton;
	}
}
