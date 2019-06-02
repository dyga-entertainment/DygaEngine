package java.MVC.View.MenuViews;

import java.MVC.Controler.MainControler;

import java.awt.*;

public class LoadingView extends View {

    public LoadingView(ViewManager vueManager, MainControler controler) {
        super(vueManager, controler);

        this.setBackground(Color.BLACK);
    }

    @Override
    public void setupHeaderPanel() {
        super.setupHeaderPanel();

        this.headerPanel.setVisible(false);
    }

    @Override
    public void setupContentPanel() {
        super.setupContentPanel();

        this.contentPanel.setVisible(false);
    }

    @Override
    public void setupButtonsPanel() {
        super.setupButtonsPanel();

        this.buttonsPanel.setVisible(false);
    }

    @Override
    public void setup(int windowWidth, int windowHeight, int contentPanelWidth, int contentPanelHeight) {
        super.setup(windowWidth, windowHeight, contentPanelWidth, contentPanelHeight);

        // Load the desired level
        if(!this.parameters.isEmpty()) {
            LoadLevel((String) this.parameters.get("worldName"), (int)this.parameters.get("levelNumber"));
        }
    }

    private void LoadLevel(String worldName, int levelNumber) {

        // Should use worldName instead of hard code.
        //MondeDemonstration.LoadDemoWorld().getNiveau(levelNumber);

        //modele.chargerNiveau(0, levelNumber);

        // Make some fancy animation here during loading ?

        System.out.println("[Loading] Finished loading level.");

        //this.vueManager.launchGame(modele);

        System.out.println("[Loading] Finished entire loading.");
    }
}
