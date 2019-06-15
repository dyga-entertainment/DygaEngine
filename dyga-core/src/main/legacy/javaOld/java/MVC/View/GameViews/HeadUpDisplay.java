package java.MVC.View.GameViews;

import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.MVC.Model.personnage.Heros;

public class HeadUpDisplay extends JPanel implements Observer {
	
	/** profil */
	private JLabel profil;
	//private String nomProfil;
	/** nombre de vie restantes */
	private JLabel vie;
	//private int nbVie;
	/** score du joueur */
	private JLabel lingots;
	//private int score;
	/** Gadget actuellement contenu dans l'inventaire */
	private JPanel inventaire;
	private BufferedImage gadget;
	/** petites bombes portees par le heros */
	private JLabel bombes;
	
	public HeadUpDisplay(String nom, int nbVie, int score) {
		JPanel nomProfil = new JPanel();
		nomProfil.setLayout(new GridLayout(2,1));
		
		JPanel nbVieRestantes = new JPanel();
		nbVieRestantes.setLayout(new GridLayout(2,1));
		
		JPanel scoreJoueur = new JPanel();
		scoreJoueur.setLayout(new GridLayout(2,1));
		this.inventaire = new JPanel();
		JPanel nbBombes = new JPanel();
		this.bombes = new JLabel();
			
		/* LayOut grille 1x5 */
		this.setLayout(new GridLayout(1, 5));
		
		/* Ajout du nom du joueur */
		this.profil = new JLabel(nom);
		nomProfil.add(new JLabel("Profil"));
		nomProfil.add(this.profil);
		this.add(nomProfil);
		
		/* Ajout du nombre de vies du joueur */
		this.vie = new JLabel(String.valueOf(nbVie));
		nbVieRestantes.add(new JLabel("Vies"));
		nbVieRestantes.add(this.vie);
		this.add(nbVieRestantes);
		
		/* Ajout du score du joueur */
		this.lingots = new JLabel(String.valueOf(score));
		scoreJoueur.add(new JLabel("Score"));
		scoreJoueur.add(this.lingots);
		this.add(scoreJoueur);
		
		/* Ajout du gadget par defaut du joueur */
		/* (bloc vide) */
		//URL url = Thread.currentThread().getContextClassLoader().getResource(ResourcesPaths.SPRITE_BLOCS_PATH + "bloc_vide.png");
		BufferedImage b;
		try {
			//b = ImageIO.read(url);
			b = ImageIO.read(new URL(""));
			this.gadget = b;
			this.inventaire.add(new JLabel(new ImageIcon(b)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.add(this.inventaire);
		
		/* Ajout du nombre de bombes */
		/* par defaut 0 */
		this.bombes.setText("0");
		nbBombes.setLayout(new GridLayout(2,1));
		nbBombes.add(new JLabel("Petites Bombes"));
		nbBombes.add(this.bombes);
		this.add(nbBombes);
		this.repaint();
	}
	/*
	public void paint(Graphics g) { 
		this.profil.repaint();
    	this.bombes.repaint();
    	this.ga.repaint();
    	this.bombes.repaint();
    	this.profil.repaint();
    	this.bombes.repaint();
    }*/

	public JLabel getVie() {
		return vie;
	}

	public void setVie(int vie) {
		this.vie.setText(String.valueOf(vie));
	}

	public JLabel getLingots() {
		return lingots;
	}

	public void setLingots(int scoreCourant) {
		this.lingots.setText(String.valueOf(scoreCourant));;
	}

	public JPanel getInventaire() {
		return inventaire;
	}

	public void setInventaire(JPanel inventaire) {
		this.inventaire = inventaire;
	}

	public BufferedImage getGadget() {
		return gadget;
	}

	public void setGadget(BufferedImage gadget) {
		this.gadget = gadget;
	}

	public int getNbBombes() {
		return new Integer(this.bombes.getText());
	}
	
	public void setNbBombes(int nbBombes) {
		this.bombes.setText(String.valueOf(nbBombes));
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Heros) {
			Heros h = (Heros) o;
			if(arg instanceof String) {
				System.out.println(arg);
				String event = (String) arg;
				switch(event) {
					case "lingot":
						this.lingots.setText(String.valueOf(h.getScore()));
						break;
					case "bombe":
						this.bombes.setText(String.valueOf(h.getInvPetitesBombes()));
						break;
					case "mort":
						this.vie.setText(String.valueOf(Integer.parseInt(this.vie.getText())-1));
						break;
					case "gadget":
						this.gadget = h.getInventaire().getSprite().image;
						break;
					default:
							;
				}
			}
		}
		this.repaint();
	}
	
	

}
