package com.dyga.Engine.Source.Main;

import javax.swing.*;
import com.dyga.Engine.Source.MVC.Controler.MainControler;
import com.dyga.Engine.Source.MVC.Model.MainModel;
import com.dyga.Engine.Source.MVC.Model.Menu.Component.ModelComponent;
import com.dyga.Engine.Source.MVC.Model.Menu.Component.ModelPanel;
import com.dyga.Engine.Source.MVC.Model.Menu.Enums.ModelLayout;
import com.dyga.Engine.Source.MVC.Model.Menu.ModelView;
import com.dyga.Engine.Source.MVC.View.MainView;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * This class will add all the initialization of the game to the coder.
 * It will instantiate the Source.MVC.Model-Source.MVC.View-Source.MVC.Controler (Source.MVC) pattern back-stage.
 * --
 * Those functions can be called by the programmer when building his game.
 */
public class Game {

    /** Source.MVC.Model **/
    private static MainModel mainModel;
    /** Source.MVC.View **/
    private static MainView mainView;
    /** Source.MVC.Controler **/
    private static MainControler mainControler;

    // Should be here ??

    /** Others **/
    private static String gameName;
    private static String[] views;

    /** Game **/
    private static String[] levels;
    private static String[] gameEntities;

    public Game(String gameName) {
        Game.gameName = gameName;

        Game.mainModel = new MainModel();
        Game.mainControler = new MainControler();
        Game.mainView = new MainView(gameName);
    }

    public void addJsonViews(String[] views) {
        Game.views = views.clone();
    }

    public void addJsonLevels(String[] levels) {
        Game.levels = levels.clone();
    }

    public void addJsonEntities(String[] gameEntities) {
        Game.gameEntities = gameEntities.clone();
    }

    public void Run() {
        // Init the modele of the game
        mainModel.init(views);

        // Init the controller of the game
        // Init the link Controller -> Source.MVC.Model in order to update the model if an input has been made.
        mainControler.init(mainModel);

        // Add the link Source.MVC.View -> Controller when the user use inputs.
        //this.addKeyListener(gameControler);
        //this.addMouseListener(gameControler);
        //this.requestFocus(); // Needed ?

        // Init the view of the game with the windows (??)
        // and the controller to be able to add him on buttons for example
        mainView.init(mainModel, mainControler);

        // Add the link Source.MVC.Controler -> Source.MVC.View to notify the view an input as been made and that, maybe the model changed.
        mainControler.addView(mainView);

        mainView.show();
    }

    /** Method useful in order to help the programmer script his desire behavior */
    public static ModelView getCurrentView() {
        return mainModel.getCurrentView();
    }

    public static void setSelectedWorld(String selectedWorldName) {
        mainModel.setSelectedWorld(selectedWorldName);
    }

    public static void setSelectedLevel(String selectedLevelName) {
        mainModel.setSelectedLevel(selectedLevelName);
    }

    /** Method that should be included in the controler
     * Should update the model and notify changes might happened
     * Maybe not... **/
    public static void changeView(String nextView) {
        mainControler.changeView(nextView.toLowerCase());
    }

    public static void backLastView() {
        mainControler.returnLastView();
    }

    /**
     * Find all the panels that contains a specific layout.
     * @param layoutType the layout type we want to find
     * @return a list of panel with the layout layoutType, null if there is none.
     */
    public static List<ModelPanel> FindPanelByLayout(ModelLayout layoutType) {
        return FindPanelByLayout(layoutType, getCurrentView().getModelComponent().getChildrenComponents());
    }

    private static List<ModelPanel> FindPanelByLayout(ModelLayout layoutType, List<ModelComponent> components) {
        List<ModelPanel> panels = new LinkedList<>();

        for(ModelComponent currentComponent : components) {
            if (currentComponent instanceof ModelPanel) {
                // Cast
                ModelPanel currentModelPanel = (ModelPanel) currentComponent;
                if (currentModelPanel.getLayout().name == layoutType){
                    panels.add(currentModelPanel);
                }
                // See if it also contains cardlayout as children
                List<ModelPanel> childrenPanels = FindPanelByLayout(layoutType, currentModelPanel.getChildrenComponents());
                if(childrenPanels != null) {
                    panels.addAll(childrenPanels);
                }
            }
        }
        return panels.size() > 0 ? panels : null;
    }

    public static JComponent getViewComponentByUuid(UUID uuid) {
        return mainView.getViewComponentByUuid(uuid);
    }

    public static void forceViewRepaint() {
        mainView.paint();
    }

    public static void launchGame() {
        mainModel.loadGame(gameEntities);
        mainView.paint();

        startGameLoop();
    }

    private static void startGameLoop() {
    }
}
