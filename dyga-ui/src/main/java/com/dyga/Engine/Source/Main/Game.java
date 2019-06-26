package com.dyga.Engine.Source.Main;

import javax.swing.*;
import com.dyga.Engine.Source.MVC.Controler.MainControler;
import com.dyga.Engine.Source.MVC.Model.MainModel;
import com.dyga.Engine.Source.MVC.Model.Menu.Component.ModelComponent;
import com.dyga.Engine.Source.MVC.Model.Menu.Component.ModelPanel;
import com.dyga.Engine.Source.MVC.Model.Menu.Enums.ModelLayout;
import com.dyga.Engine.Source.MVC.Model.Menu.ModelView;
import com.dyga.Engine.Source.MVC.View.Game.Scene;
import com.dyga.Engine.Source.MVC.View.MainView;
import com.dyga.Engine.Source.Utils.GameStatsHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static com.dyga.Engine.Source.Utils.Math.Converter.*;

/**
 * This class will add all the initialization of the game to the coder.
 * It will instantiate the Source.MVC.Model-Source.MVC.View-Source.MVC.Controler (Source.MVC) pattern back-stage.
 * --
 * Those functions can be called by the programmer when building his game.
 */
public class Game {

    // Number of frames with a delay of 0 ms before the animation thread yields
    // to other running threads.
    private static final int NO_DELAYS_PER_YIELD = 16;

    /**
     * Game Model.
     * Will be in charge of representing the game state at every moment.
     */
    private static MainModel mainModel;

    /**
     * Game View
     * Will be in charge of representing the display of the game at every moment.
     */
    private static MainView mainView;

    /**
     * Game Controller.
     * Will be in charge of handling player input and notify the model and the view.
     */
    private static MainControler mainControler;

    /**
     * Storage of all the scenes (kind of Build settings)
     * Those will be fed to the mainModel and the mainView when the player request those.
     * So they can update them as usual.
     */
    private List<Scene> scenes;

    /** Others **/
    private static String gameName;

    /** Game **/
    private static String[] levels;
    private static String[] gameEntities;

    /** Game Loop parameters **/
    private long overSleepTime = 0L;
    private long excess = 0L;
    private int noDelays = 0;

    /** FPS / UPS Stats **/
    static GameStatsHelper gameStatsHelper;

    private long frameTimeNs;       // period of time between each drawing in ms

    // TODO
    public boolean running = false;    // stops the animation
    public boolean gameOver = false;   // for game termination

    private static final int MAX_FRAME_SKIPS = 5;   // was 2;
    // no. of frames that can be skipped in any one animation loop
    // i.e the games state is updated but not rendered

    /**
     *
     * @param gameName
     * @param activeRendering if true, turn off all paint events.
     */
    public Game(String gameName, int targetFPS, boolean activeRendering) {
        Game.gameName = gameName;

        Game.mainModel = new MainModel();
        Game.mainControler = new MainControler();
        Game.mainView = new MainView(gameName);

        // a period of time each loop should take (in nanoseconds)
        this.frameTimeNs = convertMillisecToNanosec((long) 1000.0/targetFPS);
        System.out.println("fps: " + targetFPS + "; period: " + (long) 1000.0/targetFPS + " ms");
        this.gameStatsHelper = new GameStatsHelper();

        // Create game components
        this.scenes = new ArrayList<>();
    }

    public void init() {
        loadScene(0);

        // Add the link Source.MVC.Controler -> Source.MVC.View to notify the view an input as been made and that, maybe the model changed.
        Game.mainControler.addView(mainView);

        // Only for stats
        gameStatsHelper.initStatsVariables();
    }

    public void start() {
        // Start the game loop
        this.running = true;
        while (this.running) {

            // Take a snapshot of the time
            long timeBeginFrame = java.lang.System.nanoTime();
            gameUpdate();
            gameRender();
            paintScreen();
            long timeEndFrame = java.lang.System.nanoTime();

            this.endFrameIteration(timeBeginFrame, timeEndFrame);

            this.gameStatsHelper.storeStats(this.frameTimeNs);
        }

        // Exit the application
        System.exit(0);
    }

    private void loadScene(int index) {
        // Init the modele of the game
        Game.mainModel.init(this.scenes.get(index));

        // Init the controller of the game
        // Init the link Controller -> Source.MVC.Model in order to update the model if an input has been made.
        Game.mainControler.init(mainModel);

        // Init the view of the game with the windows (??)
        // and the controller to be able to add him on buttons for example
        Game.mainView.init(mainModel, mainControler, true);
    }

    private void loadScene(String sceneName) {

    }

    private void endFrameIteration(long timeBeforeUpdate, long timeAfterUpdate) {
        // Compute how much time the frame computation took
        long timeDiff = timeAfterUpdate - timeBeforeUpdate;

        // time left in this loop
        long timeLeft = (this.frameTimeNs - timeDiff) - this.overSleepTime;

        // if there is some time left, sleep until it's done
        if (timeLeft > 0) {   // some time left in this cycle
            try {
                Thread.sleep(convertNanosecToMillisec(timeLeft));  // nano -> ms
            }
            catch (InterruptedException ex) {
                // Nothing
            }

            // Since the process contains arbitrary delays, compute the delay
            this.overSleepTime = (java.lang.System.nanoTime() - timeAfterUpdate) - timeLeft;
        } else {
            // sleepTime <= 0; the frame took longer than the period
            this.excess -= timeLeft;  // store excess time value
            this.overSleepTime = 0L;

            // We do not catch up this
            if (++this.noDelays >= NO_DELAYS_PER_YIELD) {
                Thread.yield();   // give another thread a chance to run
                this.noDelays = 0;
            }
        }
    }

    public void stopGameLoop() {
        this.running = false;
    }

    private static void gameUpdate() {
        // Update the MainModel which will update the model representation of the scene
        mainModel.update();
    }

    private static void gameRender() {
        // Passive rendering for now
        mainView.renderCurrentScene(gameStatsHelper.getAverageFPS(), gameStatsHelper.getAverageUPS());
    }

    private static void paintScreen() {

        mainView.paintScreen();
    }

    /*
    public void addJsonViews(String[] views) {
        Game.views = views.clone();
    }
    */

    public void addJsonLevels(String[] levels) {
        Game.levels = levels.clone();
    }

    public void addJsonEntities(String[] gameEntities) {
        Game.gameEntities = gameEntities.clone();
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

    public static void launchGame() {
        mainModel.loadGame(gameEntities);
        //mainView.paint(); not anymore !

        // The game loop should already be trigger
        //startGameLoop();
    }

    /**
     *
     * @param scene
     * @return
     */
    public int addScene(Scene scene) {
        this.scenes.add(scene);

        //this.mainModel.addScene(sceneName);
        //this.mainView.addScene(sceneName);

        return 0;
    }
}
