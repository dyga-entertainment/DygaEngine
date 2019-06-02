package java.MVC.View.MenuViews;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.MVC.Controler.MainControler;
import java.MVC.Model.MainModelLegacy;
import java.MVC.View.Buttons.StandardButton;
import java.Utils.helper.Images;
import java.MVC.Model.jeu.Joueur;

public class WorldSelectionView extends View {

    /** title of the selected world */
    private JLabel title;

	/** buttons used to choose between world */
	private StandardButton[] buttons;

    private int nbMondes;		// Should be in a txt file or something...
    private String[] nomImages;     // Should be in a txt filev

	private String selectedWorld;

	// TODO
	private Joueur profilCourant;

	public WorldSelectionView(ViewManager vueManager, MainControler controler) {
		//super(vueManager, ResourcesPaths.SPRITE_BACKGROUND_PATH + "background_jungle.png", controler);
		super(vueManager, "background_jungle.png", controler);

		selectedWorld = "Jungle";
	}

	@Override
	public void setupHeaderPanel() {
		super.setupHeaderPanel();

		this.title = new JLabel("Veuillez selectionner votre monde et votre niveau");
        this.title.setOpaque(true);
		this.title.setFont(new Font(title.getFont().getName(), Font.BOLD, 32));
		this.title.setForeground(Color.WHITE);
        this.title.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
		this.title.setBackground(Color.BLACK);

		this.headerPanel.add(this.title);
	}

	@Override
	public void setupContentPanel() {
		super.setupContentPanel();
		this.contentPanel.setLayout(new BorderLayout());

		// Get the number of available world
		this.nbMondes = 5;

        buttons = new StandardButton[this.nbMondes];
		this.nomImages = new String[] {
				"jungle",
				"snow",
				"fire",
				"hell",
                "custom",
		};

		JPanel worldPanel = new JPanel();
        worldPanel.setOpaque(false);

        worldPanel.setLayout(new GridLayout(1,6,0,0));

		for (int i = 0; i < this.nbMondes; i++) {

			StandardButton jbutton = CreateActionParamsButton(this.nomImages[i].toUpperCase(), "","");


            //StandardButton jbutton = new StandardButton(null, this.nomImages[i].toUpperCase(), "","");

            jbutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // reset the transparency of all other buttons
                    resetButtonsTransparancy();

                    StandardButton sourceButton = (StandardButton)e.getSource();
                    title.setText(sourceButton.getButtonName());

					// Update the list of parameters for the next view
					parameters.put("worldName", sourceButton.getButtonName());

                    // Update the variable for the level screen
                    selectedWorld = sourceButton.getButtonName();

                    // Change the background image to match the selected world
                    //backgroundImage = Images.getImageFromPath(jbutton.getActionCommand());

                    // Change the transparency of the current button
                    sourceButton.setTransparency(0.2f);
                    repaint();
                }
            });

			// TODO
			/*
			if ( i >= profilCourant.getUnlockedWorld()) {
				b.setEnabled(false);
				b.setIcon(new ImageIcon(this.getClass().getResource("/ressources/Images/monde_verrouille.png")));
			} else {
				b.setEnabled(true);
				b.setText(mondes[i].getText().split(" ")[0]);
				b.setIcon(new ImageIcon(this.getClass().getResource("/ressources/Images/background_"+nomImages[i]+".png")));
			}*/

			jbutton.setText(this.nomImages[i].substring(1,1).toUpperCase());
            //jbutton.setActionCommand(ResourcesPaths.SPRITE_BACKGROUND_PATH + "background_" + nomImages[i] + ".png");

			Image image = Images.getImageFromPath(jbutton.getActionCommand());

			image.getGraphics().setColor(new Color(1,1,1,0));
			jbutton.setIcon(new ImageIcon(image));
			jbutton.setBackground(new Color(1,1,1,0));
			jbutton.setBorder(null);
			jbutton.setPreferredSize(new Dimension(0,100));
			jbutton.setVerticalTextPosition(SwingConstants.CENTER);
			jbutton.setHorizontalTextPosition(SwingConstants.CENTER);

            buttons[i] = jbutton;
            worldPanel.add(jbutton);
		}

        this.contentPanel.add(worldPanel, BorderLayout.SOUTH);
	}

    @Override
    public void setupButtonsPanel() {
        super.setupButtonsPanel();

        //JButton acceptButton = NewContextTransitionButton("Accept", "bouton_valider1.png", "bouton_valider2.png", ViewType.LevelSelection, false);
        //this.buttonsPanel.add(acceptButton, BorderLayout.EAST);
    }

    @Override
    public void setup(int windowWidth, int windowHeight, int contentPanelWidth, int contentPanelHeight) {
		super.setup(windowWidth, windowHeight, contentPanelWidth, contentPanelHeight);

		// Init the parameters structure
		parameters.put("worldName", selectedWorld);
	}

    public void resetButtonsTransparancy() {
        for (int i = 0; i < this.buttons.length; i++) {
            this.buttons[i].setTransparency(1.0f);
        }
    }

	public void majIHM(Joueur j) {
		MainModelLegacy m = new MainModelLegacy();
		this.removeAll();

		this.profilCourant = j;

		//this.backgroundImage = Images.getImageFromPath(ResourcesPaths.SPRITE_BACKGROUND_PATH + "monde_jungle.png");
		
		int seuil = 5;
		/*if ( this.mondeChoisi == this.profilCourant.getUnlockedWorld()){
			seuil = this.profilCourant.getUnlockedLevel();
		} else {
			seuil = 5;
		}*//*
		String[] niveaux = new String[seuil];
		
		for (int i = 1; i <= seuil; i++){
			niveaux[i-1] = "Niveau " + String.valueOf(i);
		}
		this.cbniveaux = new JComboBox<String>(niveaux);
		this.cbniveaux.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e)
			{
				niveauChoisi = cbniveaux.getSelectedIndex();
				System.out.println("Niveau " + (niveauChoisi+1) + " choisi");
				repaint();
			}
		});
		JPanel pvide = new JPanel();
		pvide.setOpaque(false);*/
		/*
		this.pcb = new JPanel();
		this.pcb.add(this.cbniveaux);
		this.pcb.setOpaque(false);
		this.pcb.setBackground(null);
		this.pcb.repaint();
		this.headerPanel.add(pvide);
		this.headerPanel.add(this.pcb);*/
		

		//this.repaint();
		//this.add(this.apercu);
	}

	public void setProfilCourant(Joueur p){
		this.profilCourant = p;
	}
}