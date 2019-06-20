package java.MVC.View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class IHMB8 extends Bandeau {
	
	private int nbBouton;
	// Normalement un tableau
	private JPButton validern;
	private JPButton quitter;
	
	public IHMB8() {
		this.nbBouton = 2;
		this.setLayout(new BorderLayout());
		this.add(new JPanel(), BorderLayout.NORTH);
		JPanel jpa2 = new JPanel(); 
		jpa2.setLayout(new GridLayout(1,2,20,0));
		
		this.quitter = new JPButton("Retour","bouton_retour1.png","bouton_retour2.png");

		this.validern = new JPButton("Jouer","bouton_jouer1.png","bouton_jouer2.png");
		
		jpa2.add(this.quitter);
		jpa2.add(this.validern);
		
		this.add(jpa2, BorderLayout.CENTER);
	}
	
	public JPButton getButton(int i) {
		JPButton b;
		switch(i) {
			case 0 :
				b = this.quitter;
				break;
			default : // case 1
				b = this.validern;
				break;
		}
		return b;
	}
	
	public int getNbButton() {
		return this.nbBouton;
	}
}