package java.MVC.View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class IHMB9 extends Bandeau {
	
	private int nbBouton;
	// Normalement un tableau
	private JPButton appliquer;
	private JPButton quitter;
	
	public IHMB9() {
		this.nbBouton = 2;
		this.setLayout(new BorderLayout());
		this.add(new JPanel(), BorderLayout.NORTH);
		JPanel jpa2 = new JPanel(); 
		jpa2.setLayout(new GridLayout(1,2,20,0));

		this.appliquer = new JPButton("Appliquer","bouton_appliquer1.png","bouton_appliquer2.png");
		this.quitter = new JPButton("Retour","bouton_retour1.png","bouton_retour2.png");
		
		jpa2.add(this.appliquer);
		jpa2.add(this.quitter);
		
		this.add(jpa2, BorderLayout.CENTER);
	}
	
	public JPButton getButton(int i) {
		JPButton b;
		switch(i) {
			case 0 :
				b = this.appliquer;
				break;
			default : // case 1
				b = this.quitter;
				break;
		}
		return b;
	}
	
	public int getNbButton() {
		return this.nbBouton;
	}
}