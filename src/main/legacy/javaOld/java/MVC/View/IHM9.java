package java.MVC.View;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IHM9 extends JPanel{
	
	private JLabel pa;
	
	public IHM9() {
		String[] joueurs = new String[] {"Joueur 1", "Joueur 2"};
		
		this.setLayout(new GridLayout(11,2,0,0));
		this.pa = new JLabel("Configuration des touches");
		this.add(this.pa);
		
		this.add(new JLabel("Joueur concerne"));
		this.add(new JComboBox<String>(joueurs));
		this.add(new JButton("Droite"));
		this.add(new JPanel());
		
		this.add(new JButton("Gauche"));
		this.add(new JPanel());
		
		this.add(new JButton("Haut"));
		this.add(new JPanel());
		
		this.add(new JButton("Bas"));
		this.add(new JPanel());
		
		this.add(new JButton("Ramasser gadget"));
		this.add(new JPanel());
		
		this.add(new JButton("Deposer gadget"));
		this.add(new JPanel());
		
		this.add(new JButton("Utiliser gadget"));
		this.add(new JPanel());
		
		this.add(new JButton("Creuser a droite"));
		this.add(new JPanel());
		
		this.add(new JButton("Creuser a gauche"));
		this.add(new JPanel());

	}
	
}
