package java.MVC.View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class IHMB4 extends Bandeau {
	
	private int nbBouton;
	private JPButton quitter;
	private JPButton valider;
	
	public IHMB4() {
		this.nbBouton = 2;
		this.setLayout(new BorderLayout());
		this.add(new JPanel(), BorderLayout.NORTH);
		JPanel jpa2 = new JPanel();
		jpa2.setLayout(new GridLayout(2,2,0,0));
		
		this.quitter = new JPButton("Retour","bouton_retour1.png","bouton_retour2.png");
		this.quitter.setEnabled(true);
		
		this.valider = new JPButton("Valider Creation","bouton_valider1.png","bouton_valider2.png");
		this.valider.setEnabled(true);
		
		jpa2.add(this.quitter);
		jpa2.add(this.valider);
		jpa2.add(new JPanel());
		jpa2.add(new JPanel());
		
		this.add(jpa2, BorderLayout.CENTER);
	}


	public JPButton getButton(int i) {
		JPButton b;
		switch(i) {
			case 0 :
				b = this.quitter;
				break;
			default :
				b = this.valider;
				break;
					
		}
		return b;
	}

	public int getNbButton() {
		return this.nbBouton;
	}
}