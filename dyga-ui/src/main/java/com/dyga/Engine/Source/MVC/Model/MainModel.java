package com.dyga.Engine.Source.MVC.Model;

import com.dyga.Engine.Source.MVC.Model.Game.EntityModel;
import com.dyga.Engine.Source.MVC.Model.Menu.ModelView;
import com.dyga.Engine.Source.Utils.JsonLoaders.EntityLoader;
import com.dyga.Engine.Source.Utils.JsonLoaders.MenuLoader;
import java.util.*;

/**
 * Source.Main Source.MVC.Model - MODEL
 * Takes care of making an abstract representation of the game that the view will use.
 * It will be notify by the controler when changes has to be made.
 */
public class MainModel {

    ///=================
    /// MENU Fields
    ///=================
    /** Data structure holding all the possible views */
    private static Dictionary<String, ModelView> menuViews;
    /** Current view name display to the player */
    private static ModelView activeModelView;
    /** Stack useful to comeback to previous views */
    private static Stack<String> lastVisitedViews;

    ///=================
    /// GAME Fields
    ///=================
    /** Data structure holding all the current loaded levels the player can play */
    private static Dictionary<String, ModelView> loadedLevelsView;
    //private static Dictionary<String, World> loadedGameWorlds;
    /** The name of the world currently being play */
    private static String currentWorldName;
    private static String currentLevelNumber;

    private static Dictionary<String, List<EntityModel>> teams;

    /**
     * Basic contructor
     */
    public MainModel() {
        this.menuViews = new Hashtable<>();
        this.lastVisitedViews = new Stack<>();

        this.teams = new Hashtable<>();
    }

    /**
     * Init the model
     * Load, create and store all the UI views needed to navigate through the menu.
     * Assign the first one to the current active view.
     * @param views List of resources url of all the UI views.
     */
    public void init(String[] views) {
        // Right now we can't really tell if a menu is connected to another.
        // So we load them all at the start.
        MenuLoader menuLoader = new MenuLoader();

        for (int i = 0; i < views.length; i++) {
            ModelView view = menuLoader.parse(views[i]);

            if(view != null) {
                String keyName = view.getName().toLowerCase();

                // Add the view to the data struct
                menuViews.put(keyName, view);

                // Set the default active modelView
                if(this.activeModelView == null) {
                    this.activeModelView = menuViews.get(keyName);
                }
            }
        }
    }

    /**
     * This method allow to retrieve the model of the current view display.
     * @return
     */
    public ModelView getCurrentView() {
        return this.activeModelView;
    }

    /**
     * This method let you changed the current view to the one identify by his name
     * @param nextView the name of the view the model will switch to.
     */
    public void updateView(String nextView) {
        this.lastVisitedViews.push(this.activeModelView.getName());
        this.activeModelView = menuViews.get(nextView);
    }

    /**
     * This method return to the last visited view before the current one
     */
    public void returnLastView() {
        if(!this.lastVisitedViews.isEmpty()) {
            this.activeModelView = menuViews.get(this.lastVisitedViews.pop());
        }
    }

    // TODO
    public boolean isDirty() {
        return true;
    }

    public void setSelectedWorld(String selectedWorldName) {
        currentWorldName = selectedWorldName;
    }

    public void setSelectedLevel(String selectedLevelName) {
        currentLevelNumber = selectedLevelName;
    }

    public void launchGame() {


        InitGame();


    }

    // Maybe later ?
    private void InitGame() {
        this.loadedLevelsView = new Hashtable<>();
        this.teams = new Hashtable<>();

        // Add both team. So they can target each other.
    }

    public void loadGame(String[] gameEntities) {
        System.out.println(currentWorldName);
        System.out.println(currentLevelNumber);

        // We will load the level after experimentation !
        //LevelLoader.loadLevel();

        List<EntityModel> team1 = new ArrayList<>();
        team1.addAll(loadEntity(gameEntities));
        this.teams.put("team1", team1);

        System.out.println(this.teams.toString());
    }

    private  List<EntityModel> loadEntity(String[] gameEntities) {
        EntityLoader entityLoader = new EntityLoader();

        List<EntityModel> team1 = new ArrayList<>();
        for (int i = 0; i < gameEntities.length; i++) {
            EntityModel entity = entityLoader.parse(gameEntities[i]);

            if(entity != null) {

                team1.add(entity);
                System.out.println(entity);
                /*
                String keyName = view.getName().toLowerCase();

                // Add the view to the data struct
                menuViews.put(keyName, view);

                // Set the default active modelView
                if(this.activeModelView == null) {
                    this.activeModelView = menuViews.get(keyName);
                }*/
            }
        }
        return team1;
    }

    public List<EntityModel> getCurrentEntities() {
        return this.teams.get("team1");
    }
}
