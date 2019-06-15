package java.MVC.View.MenuViews;

import java.MVC.Controler.MainControler;
import java.MVC.View.IHM2;
import java.MVC.Model.jeu.Monde;
import Data.levels.MondeDemonstration;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LevelSelectionView extends View {

    private int niveauChoisi = 0;

    private JPanel panelPreview;

	public LevelSelectionView(ViewManager vueManager, MainControler controler) {
        super(vueManager, controler);
	}

	@Override
	public void setupHeaderPanel() {
        super.setupHeaderPanel();

        JLabel title = new JLabel("Veuillez selectionner votre niveau");
        Font pf = title.getFont();
        title.setFont(new Font(pf.getName(), Font.PLAIN, 22));

        this.headerPanel.add(title);
    }

    @Override
    public void setupButtonsPanel() {
        super.setupButtonsPanel();

        // Init the parameters structure
        parameters.put("levelNumber", niveauChoisi);

        // level selection buttons
        JPanel selectionButtons = new JPanel();
        selectionButtons.setOpaque(false);
        selectionButtons.setLayout(new FlowLayout());
        JButton previousPreview = new JButton("Previous");
        previousPreview.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)panelPreview.getLayout()).previous(panelPreview);

                // TODO
                // Update the parameters with the selected level
                parameters.put("levelNumber", --niveauChoisi);

                System.out.println("[java.MVC.View parameters] " + parameters.toString());
            }
        });
        JLabel n = new JLabel("Selected level : " + this.niveauChoisi);
        JButton nextPreview = new JButton("Next");
        nextPreview.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)panelPreview.getLayout()).next(panelPreview);

                // TODO
                // Update the parameters with the selected level
                parameters.put("levelNumber", ++niveauChoisi);

                System.out.println("[java.MVC.View parameters] " + parameters.toString());
            }
        });
        selectionButtons.add(previousPreview);
        selectionButtons.add(n);
        selectionButtons.add(nextPreview);
        this.buttonsPanel.add(selectionButtons, BorderLayout.CENTER);

        // Accept button
        JButton acceptButton = NewContextTransitionButton("accept", "bouton_valider1.png", "bouton_valider2.png", "LoadingView");
        this.buttonsPanel.add(acceptButton, BorderLayout.EAST);
    }

    @Override
    public void setup(int windowWidth, int windowHeight, int contentPanelWidth, int contentPanelHeight) {
        super.setup(windowWidth, windowHeight, contentPanelWidth, contentPanelHeight);

        this.contentPanel.setOpaque(true);
        this.contentPanel.setLayout(new BorderLayout());

        if(this.parameters != null) {
            System.out.println("[java.MVC.View parameters] " + this.parameters.toString());
        }

        // Creation du panel aperçu des niveaux
        this.panelPreview = new JPanel();
        this.panelPreview.setLayout(new CardLayout());

        // Retrieve all the Data.levels from the selected world
        // Takes time
        // TODO
        Monde currentWorld = MondeDemonstration.LoadDemoWorld();

        for(int i = 0 ; i < currentWorld.getNbNiveau() ; i++) {
            this.panelPreview.add(currentWorld.getNiveau(i).getApercu());
        }

        JPanel apercu = new JPanel();
        apercu.add(new IHM2());

        // Update the parameters structure
        parameters.put("levelNumber", niveauChoisi);

        this.contentPanel.add(this.panelPreview, BorderLayout.CENTER);
    }
}
