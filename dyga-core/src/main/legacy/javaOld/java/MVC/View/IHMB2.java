package java.MVC.View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class IHMB2 extends Bandeau {
	
	public IHMB2() {
		this.setLayout(new BorderLayout());
		JPanel jpa2 = new JPanel(); 
		jpa2.setLayout(new GridLayout(1,2,20,0));
		
		ButtonGroup b = new ButtonGroup();
		JRadioButton br1 = new JRadioButton("Yes");
		JRadioButton br2 = new JRadioButton("No");
		b.add(br1);
		b.add(br2);
		
		jpa2.add(br1);
		jpa2.add(br2);
		
		this.add(jpa2, BorderLayout.CENTER);
	}

	@Override
	public JPButton getButton(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNbButton() {
		// TODO Auto-generated method stub
		return 0;
	}
}
