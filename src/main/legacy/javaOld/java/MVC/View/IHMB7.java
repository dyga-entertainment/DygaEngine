package java.MVC.View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class IHMB7 extends Bandeau {
	
	private int nbBouton;
	// Normalement un tableau
	private JPButton jouer;
	private JPButton quitter;
	
	public IHMB7() {
		this.nbBouton = 2;
		this.setLayout(new BorderLayout());
		this.add(new JPanel(), BorderLayout.NORTH);
		JPanel jpa2 = new JPanel(); 
		jpa2.setLayout(new GridLayout(2,1,250,0));
		
		JPanel jpa3 = new JPanel();
		jpa3.setLayout(new FlowLayout(FlowLayout.CENTER,100,0));
		
		this.quitter = new JPButton("Retour","bouton_retour1.png","bouton_retour2.png");

		this.jouer = new JPButton("Jouer","bouton_jouer1.png","bouton_jouer2.png");
		
		jpa3.add(this.quitter);
		jpa3.add(this.jouer);
		jpa2.add(jpa3);
		jpa2.add(new JPanel());
		
		this.add(jpa2, BorderLayout.CENTER);
	}
	
	public JPButton getButton(int i) {
		JPButton b;
		switch(i) {
			case 0 :
				b = this.quitter;
				break;
			default : // case 1
				b = this.jouer;
				break;
		}
		return b;
	}
	
	public int getNbButton() {
		return this.nbBouton;
	}
}