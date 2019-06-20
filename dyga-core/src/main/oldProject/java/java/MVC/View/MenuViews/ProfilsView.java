package java.MVC.View.MenuViews;

import java.MVC.Controler.MainControler;
import java.Utils.helper.Images;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretListener;

public class ProfilsView extends View {
	
	private JLabel pa;
	private String nomChoisi;
	private JLabel apercu;
	private int imageChoisie;
	private int diffChoisie;
	private String[] avatarIcons;
	
	public ProfilsView(ViewManager vueManager, MainControler controler) {
		super(vueManager, controler);

		String[] images = {"image 1", "image 2", "image 3","image 4"};
		this.avatarIcons = new String[] {"avatar_axel.png","avatar_yann.png","avatar_damien.png","avatar_greg.png"};
		String[] difficultes = {"Debutant", "Intermediaire", "Expert"};

		this.setLayout(new GridLayout(6,1,0,0));

		this.pa = new JLabel("Veuillez creer votre profil");
		Font pf = pa.getFont();
		pa.setFont(new Font(pf.getName(), Font.PLAIN, 22));
		this.add(this.pa, BorderLayout.CENTER);
		
		JPanel p1 = new JPanel();
		p1.add(new JLabel("Nom de profil"));
		
		JTextField jtf = new JTextField("");
		jtf.setPreferredSize(new Dimension(200,30));
        jtf.addCaretListener(new CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent e) {
                JTextField text = (JTextField)e.getSource();
                nomChoisi = text.getText();
                System.out.println("Nom : " + nomChoisi);
            }
        });
        
		p1.add(jtf);
		p1.setPreferredSize(new Dimension(200,150));
		p1.validate();
		
		JPanel p2 = new JPanel();
		//p2.setLayout(new GridLayout());
		p2.add(new JLabel("Image"));
		JComboBox<String> comboBoxImages = new JComboBox<String>(images);
		comboBoxImages.setPreferredSize(new Dimension(150, 30));
		comboBoxImages.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e)
			{
				imageChoisie = comboBoxImages.getSelectedIndex();
				//ImageIcon imageIcon = new ImageIcon(Images.getImageFromPath(ResourcesPaths.SPRITE_UI_ICON_PATH + avatarIcons[imageChoisie]));
				ImageIcon imageIcon = new ImageIcon(Images.getImageFromPath(avatarIcons[imageChoisie]));
				apercu.setIcon(imageIcon);
				apercu.repaint();
				System.out.println("Image number " + imageChoisie);
			}
		});
	    //combo.setForeground(Color.blue);
		p2.add(comboBoxImages);
		
		JPanel p3 = new JPanel();
		p3.setLayout(new GridLayout(1,4,0,0));
		p3.add(new JLabel(""));
		p3.add(new JLabel("Apercu"));
		this.apercu = new JLabel();
		/*ImageIcon imageIcon = new ImageIcon(Images.getImageFromPath(ResourcesPaths.SPRITE_UI_ICON_PATH + avatarIcons[this.imageChoisie]));
		this.apercu.setIcon(imageIcon);*/
		p3.add(this.apercu);
		p3.add(new JLabel(""));
		p3.validate();
		
		JPanel p4 = new JPanel();
		p4.add(new JLabel("Difficulte"));
		
		JComboBox<String> combodiff = new JComboBox<String>(difficultes);
		combodiff.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e)
			{
				diffChoisie = combodiff.getSelectedIndex();
				System.out.println("Difficulty : " + diffChoisie);
			}
		});
		p4.add(combodiff);
				
		this.add(p1);
		this.add(p2);
		this.add(p3);
		this.add(p4);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		//g.drawImage(this.backgroundImage,0,0,getSize().width, getSize().height, this);
		for(int i = 0; i < buttons.length; i++) {
			this.buttons[i].repaint();
		}
	}
	
	public String getNomChoisi() {
		return this.nomChoisi;
	}
	
	public String getImageChoisie() {
		return this.avatarIcons[this.imageChoisie];
	}
	
	public int getDiffChoisie() {
		return this.diffChoisie;
	}
}
