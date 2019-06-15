package com.dyga.Engine.Source.Main;

import javax.swing.*;
import com.dyga.Engine.Source.MVC.Controler.MainControler;
import com.dyga.Engine.Source.MVC.Model.MainModel;
import com.dyga.Engine.Source.MVC.Model.Menu.Component.ModelComponent;
import com.dyga.Engine.Source.MVC.Model.Menu.Component.ModelPanel;
import com.dyga.Engine.Source.MVC.Model.Menu.Enums.ModelLayout;
import com.dyga.Engine.Source.MVC.Model.Menu.ModelView;
import com.dyga.Engine.Source.MVC.View.MainView;

import java.text.DecimalFormat;
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

    /* Number of frames with a delay of 0 ms before the animation thread yields
     to other running threads. */
    private static final int NO_DELAYS_PER_YIELD = 16;


    /** Source.MVC.Model **/
    private static MainModel mainModel;
    /** Source.MVC.View **/
    private static MainView mainView;
    /** Source.MVC.Controler **/
    private static MainControler mainControler;

    /** GameLoop **/
    //private static GameLoop gameLoop;

    // Should be here ??

    /** Others **/
    private static String gameName;
    private static String[] views;

    /** Game **/
    private static String[] levels;
    private static String[] gameEntities;

    /** FPS **/
    private static long frameComputationTime;       // period of time between each drawing in ms
    private static int targetFPS;

    private static boolean running = false;    // stops the animation
    private static boolean gameOver = false;   // for game termination

    /** Othersssssss **/
    private static final long MAX_STATS_INTERVAL = 1000000000L;
    // private static long MAX_STATS_INTERVAL = 1000L;
    // record stats every 1 second (roughly)

    private static final int MAX_FRAME_SKIPS = 5;   // was 2;
    // no. of frames that can be skipped in any one animation loop
    // i.e the games state is updated but not rendered

    private static final int NUM_FPS = 10;
    // number of FPS values stored to get an average


    private int pWidth, pHeight;     // panel dimensions

    // used for gathering statistics
    private static long statsInterval = 0L;    // in ns
    private static long prevStatsTime;
    private static long totalElapsedTime = 0L;
    private static long gameStartTime;
    private static int timeSpentInGame = 0;    // in seconds

    private static long frameCount = 0;
    private static double fpsStore[];
    private static long statsCount = 0;
    private static double averageFPS = 0.0;

    private static long framesSkipped = 0L;
    private static long totalFramesSkipped = 0L;
    private static double upsStore[];
    private static double averageUPS = 0.0;

    private DecimalFormat timedf = new DecimalFormat("0.####");  // 4 dp


    /**
     *
     * @param gameName
     * @param activeRendering if true, turn off all paint events.
     */
    public Game(String gameName, int targetFPS, boolean activeRendering) {
        Game.gameName = gameName;

        Game.mainModel = new MainModel();
        Game.mainControler = new MainControler();
        Game.mainView = new MainView(gameName, activeRendering);

        // a period of time each loop should take (in ms)
        Game.targetFPS = targetFPS;
        Game.frameComputationTime = (long) 1000.0/targetFPS;
        // Conversion to nano seconds
        Game.frameComputationTime *= 1000000L;

        System.out.println("fps: " + targetFPS + "; period: " + Game.frameComputationTime + " ms");

        // initialise timing elements
        fpsStore = new double[NUM_FPS];
        upsStore = new double[NUM_FPS];
        for (int i=0; i < NUM_FPS; i++) {
            fpsStore[i] = 0.0;
            upsStore[i] = 0.0;
        }


        // Create game components
        // TODO here ??
    }

    public static void run() {
        long overSleepTime = 0L;
        long excess = 0L;
        int noDelays = 0;

        // init the Game Engine
        initGameEngine();

        running = true;
        while (running) {

            // Take a snapshot of the time
            long timeBeforeUpdate = java.lang.System.nanoTime();

            gameUpdate();
            gameRender();
            paintScreen();

            long timeAfterUpdate = java.lang.System.nanoTime();

            // Compute how much time the frame computation took
            long timeDiff = timeAfterUpdate - timeBeforeUpdate;

            // time left in this loop
            long timeLeft = (frameComputationTime - timeDiff) - overSleepTime;

            // if there is some time left, sleep until it's done
            if (timeLeft > 0) {   // some time left in this cycle
                try {
                    Thread.sleep(timeLeft/1000000L);  // nano -> ms
                }
                catch(InterruptedException ex){}

                // Since the process contains arbitrary delays, compute the delay
                overSleepTime = (java.lang.System.nanoTime() - timeAfterUpdate) - timeLeft;
            } else {
                // sleepTime <= 0; the frame took longer than the period
                excess -= timeLeft;  // store excess time value
                overSleepTime = 0L;

                // We do not catch up this
                if (++noDelays >= NO_DELAYS_PER_YIELD) {
                    Thread.yield();   // give another thread a chance to run
                    noDelays = 0;
                }
            }

            // TODO : Take a look at that
            storeStats();
        }
        System.exit(0);
    }

    public static void stopGameLoop() {
        running = false;
    }

    private static void gameUpdate() {

    }

    private static void gameRender() {
        // Passive rendering for now
        mainView.paint(averageFPS, averageUPS);
    }

    private static void paintScreen() {
        // Nothing yet
        //mainView.paintScreen();
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

    public static void initGameEngine() {
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
        //mainView.paint();  // not anymore !
    }

    public static void launchGame() {
        mainModel.loadGame(gameEntities);
        //mainView.paint(); not anymore !

        // THe game loop should already be trigger
        //startGameLoop();
    }


    /// THIS SHOULD BE IN AN UTIL CLASS

    private static void storeStats()
  /* The statistics:
       - the summed periods for all the iterations in this interval
         (period is the amount of time a single frame iteration should take),
         the actual elapsed time in this interval,
         the error between these two numbers;

       - the total frame count, which is the total number of calls to run();

       - the frames skipped in this interval, the total number of frames
         skipped. A frame skip is a game update without a corresponding render;

       - the FPS (frames/sec) and UPS (updates/sec) for this interval,
         the average FPS & UPS over the last NUM_FPSs intervals.

     The data is collected every MAX_STATS_INTERVAL  (1 sec).
  */
    {
        frameCount++;
        statsInterval += frameComputationTime;

        if (statsInterval >= MAX_STATS_INTERVAL) {     // record stats every MAX_STATS_INTERVAL
            long timeNow = java.lang.System.nanoTime();
            timeSpentInGame = (int) ((timeNow - gameStartTime)/1000000000L);  // ns --> secs

            long realElapsedTime = timeNow - prevStatsTime;   // time since last stats collection
            totalElapsedTime += realElapsedTime;

            double timingError =
                ((double)(realElapsedTime - statsInterval) / statsInterval) * 100.0;

            totalFramesSkipped += framesSkipped;

            double actualFPS = 0;     // calculate the latest FPS and UPS
            double actualUPS = 0;
            if (totalElapsedTime > 0) {
                actualFPS = (((double)frameCount / totalElapsedTime) * 1000000000L);
                actualUPS = (((double)(frameCount + totalFramesSkipped) / totalElapsedTime)
                    * 1000000000L);
            }

            // store the latest FPS and UPS
            fpsStore[ (int)statsCount%NUM_FPS ] = actualFPS;
            upsStore[ (int)statsCount%NUM_FPS ] = actualUPS;
            statsCount = statsCount+1;

            double totalFPS = 0.0;     // total the stored FPSs and UPSs
            double totalUPS = 0.0;
            for (int i=0; i < NUM_FPS; i++) {
                totalFPS += fpsStore[i];
                totalUPS += upsStore[i];
            }

            if (statsCount < NUM_FPS) { // obtain the average FPS and UPS
                averageFPS = totalFPS/statsCount;
                averageUPS = totalUPS/statsCount;
            }
            else {
                averageFPS = totalFPS/NUM_FPS;
                averageUPS = totalUPS/NUM_FPS;
            }
/*
      System.out.println(timedf.format( (double) statsInterval/1000000000L) + " " +
                    timedf.format((double) realElapsedTime/1000000000L) + "s " +
			        df.format(timingError) + "% " +
                    frameCount + "c " +
                    framesSkipped + "/" + totalFramesSkipped + " skip; " +
                    df.format(actualFPS) + " " + df.format(averageFPS) + " afps; " +
                    df.format(actualUPS) + " " + df.format(averageUPS) + " aups" );
*/
            framesSkipped = 0;
            prevStatsTime = timeNow;
            statsInterval = 0L;   // reset
        }
    }  // end of storeStats()

}
