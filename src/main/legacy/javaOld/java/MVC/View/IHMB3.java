package java.MVC.View;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class IHMB3 extends Bandeau {
	
	private int nbBouton;
	// Normalement un tableau
	private JPButton quitter;	
	
	public IHMB3() {
		this.nbBouton = 1;
		
		JPanel jpa1 = new JPanel();
		jpa1.setLayout(new GridLayout(2,3,0,0));
		
		this.quitter = new JPButton("Retour", "bouton_retour1.png", "bouton_retour2.png");
		
		jpa1.add(new JPanel());
		jpa1.add(this.quitter);
		jpa1.add(new JPanel());
		jpa1.add(new JPanel());
		jpa1.add(new JPanel());
		jpa1.add(new JPanel());

		this.add(jpa1);
	}
	
	public JPButton getButton(int i) {
		return this.quitter;
	}
	
	public int getNbButton() {
		return this.nbBouton;
	}
}
