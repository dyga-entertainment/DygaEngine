package com.dyga.Engine.Source.MVC.View;

import javax.swing.*;
import com.dyga.Engine.Source.MVC.Controler.MainControler;
import com.dyga.Engine.Source.MVC.Model.MainModel;
import com.dyga.Engine.Source.MVC.Model.Menu.Component.ModelButton;
import com.dyga.Engine.Source.MVC.Model.Menu.Component.ModelComponent;
import com.dyga.Engine.Source.MVC.Model.Menu.Component.ModelLabel;
import com.dyga.Engine.Source.MVC.Model.Menu.Component.ModelPanel;
import com.dyga.Engine.Source.MVC.Model.Menu.ModelView;
import com.dyga.Engine.Source.MVC.Model.Menu.Structs.Layouts.Layout;
import com.dyga.Engine.Source.MVC.View.Game.EntityView;
import com.dyga.Engine.Source.MVC.View.Menu.Component.ViewButton;
import com.dyga.Engine.Source.MVC.View.Menu.Component.ViewLayer;
import com.dyga.Engine.Source.MVC.View.Menu.Component.ViewPanel;
import com.dyga.Engine.Source.Utils.WrapLayout;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.UUID;

// <!> This should be call SCENE !

/**
 * Source.Main Source.MVC.View - VIEW
 * Takes care of showing to the player the UI describe by the model.
 * The controler will be attached to it in order to react to user events.
 */
public class MainView {

    private static int WIDTH = 300;
    private static int HEIGHT = 100;

    /** The view know the model in order to get information from him */
    private MainModel mainModel;
    /** The view know the controler as well to attach him to each view. */
    private MainControler mainControler;

    private static JFrame gameFrame;
    private static ViewPanel activeGameView;
    //private static JPanel activeGamePanel;


    /** This let the user get view component by using UUID from the model components */
    private static Dictionary<UUID, JComponent> activeViewComponentsByModel;

    /**  */
    private static Dictionary<UUID, ViewLayer> activeLayersView;

    // SHOULD BE IN THE MODEL AND THAT'S IT
    private Dictionary<UUID, EntityView> activeEntitiesByModel;


    /** Others **/
    // off-screen rendering
    private Graphics dbg;
    private static Image screenImage;

    private DecimalFormat df = new DecimalFormat("0.##");  // 2 dp

    /**
     * Basic contructor
     */
    public MainView(String gameName) {
        this.activeViewComponentsByModel = new Hashtable<>();
        activeLayersView = new Hashtable<>();
        activeEntitiesByModel = new Hashtable<>();

        this.gameFrame = new JFrame(gameName);
    }

    /**
     * Init the game view
     * @param mainModel
     * @param mainControler
     * @param activeRendering if true, turn off all paint events.
     */
    public void init(MainModel mainModel, MainControler mainControler, boolean activeRendering) {
        this.mainModel = mainModel;
        this.mainControler = mainControler;

        // Setup the container window
        this.setupJFrame(activeRendering);

        // Create the view
        this.createFirstView();
    }

    private void setupJFrame(boolean activeRendering) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension scrDim = tk.getScreenSize();
        WIDTH = scrDim.width;
        HEIGHT = scrDim.height;

        this.gameFrame.setSize(WIDTH, HEIGHT);
        this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gameFrame.setIgnoreRepaint(activeRendering);
        this.gameFrame.setUndecorated(true);   // no borders or title bar
        this.gameFrame.setIgnoreRepaint(true);  // turn off all paint events since doing active rendering
        //gameFrame.pack();
        this.gameFrame.setResizable(false);
        this.gameFrame.setVisible(true);
    }

    public static void paintScreen() {
        Graphics graphics;
        try {
            // Retrieve the graphics from the current view
            graphics = activeGameView.getGraphics();
            if (graphics != null && screenImage != null) {
                graphics.drawImage(screenImage, 0, 0, null);
            }
            graphics.dispose();
        } catch (Exception exception) {
            System.out.println("Graphics context error: " + exception);
        }
    }


    public void renderActiveView(double averageFPS, double averageUPS) {
        if (this.screenImage == null) {
            System.out.println("screenImage is null");
            return;
        } else {
            this.dbg = this.screenImage.getGraphics();
        }

        // clear the background
        this.dbg.setColor(Color.white);
        this.dbg.fillRect(0, 0, WIDTH, HEIGHT);

        this.dbg.setColor(Color.blue);
        //dbg.setFont(font);

        // report frame count & average FPS and UPS at top left
        // dbg.drawString("Frame Count " + frameCount, 10, 25);
        this.dbg.drawString("Average FPS/UPS: " + df.format(averageFPS) + ", " +
            df.format(averageUPS), 20, 25);

        // report time used and bosex used at bottom left
        // TODO
        this.dbg.drawString("Time Spent: " + 500 + " secs", 10, HEIGHT-15);
        this.dbg.drawString("Boxes used: " + 5000000, 260, HEIGHT-15);

        // draw the pause and quit 'buttons'
        // TODO
        drawButtons(dbg);

        this.dbg.setColor(Color.black);

        this.activeGameView.render();

        // draw game elements: the obstacles and the worm
        // TODO
        //obs.draw(dbg);
        //fred.draw(dbg);
    }

    private void drawButtons(Graphics g)
    {
        Rectangle pauseArea = new Rectangle(WIDTH-100, HEIGHT-45, 70, 15);
        Rectangle quitArea = new Rectangle(WIDTH-100, HEIGHT-20, 70, 15);

        g.setColor(Color.black);

        // draw the pause 'button'
        //if (isOverPauseButton)
            g.setColor(Color.green);

        g.drawOval( pauseArea.x, pauseArea.y, pauseArea.width, pauseArea.height);
        if (true)
            g.drawString("Paused", pauseArea.x, pauseArea.y+10);
        else
            g.drawString("Pause", pauseArea.x+5, pauseArea.y+10);

        if (false)
            g.setColor(Color.black);

        // draw the quit 'button'
        if (true)
            g.setColor(Color.green);

        g.drawOval(quitArea.x, quitArea.y, quitArea.width, quitArea.height);
        g.drawString("Quit", quitArea.x+15, quitArea.y+10);

        if (false)
            g.setColor(Color.black);
    }  // drawButtons()

    private void createFirstView() {
        System.out.println("[DEBUG] CREATE THE VIEW");

        // Temporary for now
        gameFrame.getContentPane().removeAll();
        //gameFrame.getContentPane().validate();

        // Get the current modelView display thanks to the model
        ModelView menuView = this.mainModel.getCurrentView();

        // Create the view based on the model
        System.out.println("[DEBUG] Build a new ViewPanel to display = " + menuView.getName());
        ModelPanel mainComponent = menuView.getModelComponent();
        this.activeGameView = createView(mainComponent, this.mainControler);

        // Add it at the end !
        gameFrame.getContentPane().add(this.activeGameView);

        // Create the image from the view
        this.screenImage = activeGameView.createImage(WIDTH, HEIGHT);

        if (this.screenImage == null) {
            System.out.println("screenImage is null");
            return;
        } else {
            this.dbg = this.screenImage.getGraphics();
        }
    }

    /**
     * Method used to repaint the view.
     * Can be called when an input has been received or (??) when it has been tag explicitly by the game loop
     */
    // Private ? Public ?
    /*
    public void paint(double averageFPS, double averageUPS) {

        // Get the current modelView display thanks to the model
        ModelView menuView = this.mainModel.getCurrentView();

        if(menuView != null) {
            if(this.mainModel.isDirty()) {
                // Check if we need to update the background active view. Should be true only for menu
                if(this.activeGameView == null || !menuView.getName().equals(this.activeGameView.getName())) {
                    // Remove previous panel it
                    this.gameFrame.getContentPane().removeAll();
                    this.gameFrame.getContentPane().validate();

                    System.out.println("[DEBUG] Build a new ViewPanel to display = " + menuView.getName());
                    ModelPanel mainComponent = menuView.getModelComponent();

                    // Create the associated Panel
                    this.activeGameView = createView(mainComponent, this.mainControler);
                    // Useful in order to avoid rebuilding the same windows over and over..
                    this.activeGameView.setName(menuView.getName());
                }

                List<EntityModel> entities = this.mainModel.getCurrentEntities();
                if(entities != null) {
                    for(EntityModel entity : entities) {
                        // Create a EntityView
                        EntityView entityView = new EntityView(entity);

                        // Add it
                        activeEntitiesByModel.put(entity.getUuid(), entityView);

                        this.activeGameView.add(entityView);
                        // Add it to the view and should be paint there
                        //this.activeGameView.addEntity(entityView);

                        //entityView.paint(this.gameFrame.getContentPane().getGraphics(), this.gameFrame.getContentPane());
                        //entityView.paint(this.activeGameView.getGraphics(), this.activeGameView);
                        entityView.paint(this.activeGameView.getGraphics(), this.activeGameView);
                    }
                }

                // Should set dirty = false here

                // Add it at the end !
                this.gameFrame.getContentPane().add(this.activeGameView);
            }
        }

        // Create a GameView for the entity only
        ViewPanel entitiesPanel = new ViewPanel("com.dyga.Engine.LodeRunnerGame/Assets/Sprites/Enemy/Idle/mad_monk_stop_left.png");
        entitiesPanel.setPreferredSize(new Dimension(300, 310));

        //this.gameFrame.getContentPane().add(layeredPane, BorderLayout.CENTER);

        Graphics dbg = this.activeGameView.getGraphics();

        if(dbg != null) {
            // report frame count & average FPS and UPS at top left
            // dbg.drawString("Frame Count " + frameCount, 10, 25);
            dbg.drawString("Average FPS/UPS: " + df.format(averageFPS) + ", " +
                df.format(averageUPS), 20, 25);
        }


        this.activeGameView.repaint();
        this.gameFrame.repaint();
        this.gameFrame.setVisible(true);
    }*/

    /**
     * Helper method to create a swing view from scratch
     * @param mainComponent
     * @param c
     * @return
     */
    private static ViewPanel createView(ModelPanel mainComponent, MainControler c) {
        ViewPanel currentView = new ViewPanel(WIDTH, HEIGHT);
        String imageUrl;

        if((imageUrl = mainComponent.getBackgroundImageUrl()) != null) {
            currentView.setBackgroundImage(imageUrl);
        }
        if(mainComponent.getBackgroundPreferredSize() != null) {
            currentView.setPreferredSize(mainComponent.getBackgroundPreferredSize().width, mainComponent.getBackgroundPreferredSize().height);
        }
        if(mainComponent.getBackgroundStartingPoint() != null) {
            currentView.setStartingCoordinate(mainComponent.getBackgroundStartingPoint().x, mainComponent.getBackgroundStartingPoint().y);
        }
        Layout layout = mainComponent.getLayout();
        switch (layout.name) {
            case BorderLayout: currentView.setLayout(new BorderLayout()); break;
            case GridLayout:
                com.dyga.Engine.Source.MVC.Model.Menu.Structs.Layouts.GridLayout gridLayout = (com.dyga.Engine.Source.MVC.Model.Menu.Structs.Layouts.GridLayout)layout;
                currentView.setLayout(new GridLayout(gridLayout.rows,gridLayout.cols, gridLayout.hgap, gridLayout.vgap));
                break;
            case WrapLayout: currentView.setLayout(new WrapLayout()); break;
            case CardLayout: currentView.setLayout(new CardLayout()); break;
            default: currentView.setLayout(new FlowLayout()); break;
        }

        // Children
        for(ModelComponent child : mainComponent.getChildrenComponents()) {
            JComponent childComponent = null;
            /** Panel section */
            if(child instanceof ModelPanel) {
                ModelPanel childPanel = (ModelPanel) child;
                childComponent = createView((ModelPanel)child, c);
            /** Label section */
            } else if (child instanceof ModelLabel) {
                ModelLabel childLabel = (ModelLabel)child;
                JLabel label = new JLabel(childLabel.getText());
                com.dyga.Engine.Source.MVC.Model.Menu.Structs.Font font = childLabel.getFont();
                label.setFont(new Font(font.name, font.style, font.size));
                label.setForeground(new Color(font.color.red, font.color.green, font.color.blue));

                //this.title.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
                //this.title.setBackground(Color.BLACK);

                childComponent = label;
            /** Button section */
            } else if (child instanceof ModelButton) {
                ModelButton childButton = (ModelButton)child;
                ViewButton button = new ViewButton(
                    childButton.getText(),
                    childButton.getPressedImageUrl(),
                    childButton.isEnable(),
                    childButton.getNextView()
                );

                button.setPreferredSize(childButton.getPreferredSize());//new Dimension(150, 40));
                // Does the image need to be scaled , tiled, etc.
                button.setDisplayOption(childButton.getBackgroundOptions());
                // Set the background image
                button.setBackgroundImage(childButton.getBackgroundImageUrl(),
                    childButton.getBackgroundStartingPoint(),
                    childButton.getBackgroundPreferredSize()
                );

                button.addActionListener(c);
                button.addMouseListener(c);
                button.addKeyListener(c);

                // Listeners
                button.setListenerMethodsDict(childButton.getListenersMap());

                childComponent = button;
            } else {
                childComponent = null; // Unknown
            }

            childComponent.setOpaque(child.isOpaque());

            if(child.hasLayoutConstraint()) {
                if(child.hasBorderLayoutLayoutConstraint()) {
                    currentView.add(childComponent, child.getBorderLayoutContraints());
                } else {
                    currentView.add(childComponent, child.getIndexLayout());
                }
            } else {
                currentView.add(childComponent);
            }

            // Add a link to have the view Component from the model UUID
            activeViewComponentsByModel.put(child.getUuid(), childComponent);

        }
        return currentView;
    }

    public JComponent getViewComponentByUuid (UUID uuid) {
        return activeViewComponentsByModel.get(uuid);
    }

    public JFrame getFrame() {
        return this.gameFrame;
    }
}
