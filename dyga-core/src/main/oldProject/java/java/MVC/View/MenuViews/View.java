package java.MVC.View.MenuViews;

import java.MVC.Controler.MainControler;
import java.MVC.View.Buttons.ContextTransitionButton;
import javax.swing.*;
import java.awt.*;
import java.util.Dictionary;
import java.util.EventListener;
import java.util.Hashtable;

import java.MVC.View.Buttons.StandardButton;
import java.MVC.View.Menu.Component.ViewPanel;

public class View extends ViewPanel implements EventListener {

    protected ViewManager vueManager;

    /** JPanel in charge of buttons layout */
    protected JPanel headerPanel;
    protected JPanel contentPanel;
    protected JPanel buttonsPanel;

    /** List of buttons presents in the view */
    protected JButton[] buttons = new JButton[0];

    /** List of object that the previous view gave before handling to the next */
    protected Dictionary<String, Object> parameters;

    /** TESTSTSTSTS */
    private MainControler controler;
    /***/

    public View(String urlImage, MainControler gameControler) {
        this(null, urlImage, gameControler);
    }

    public View(ViewManager vueManager, MainControler gameControler) {
        this(vueManager, "", gameControler);
    }

    public View(ViewManager vueManager, String urlImage, MainControler gameControler) {
        super(urlImage);
        this.parameters = new Hashtable<>();
        this.vueManager = vueManager;

        controler = gameControler;

        // Default Settings for most of the Views
        this.setLayout(new BorderLayout());

        // Default initialization
        setupHeaderPanel();
        setupContentPanel();
        setupButtonsPanel();

        // Add both panel to the view
        this.add(this.headerPanel, BorderLayout.NORTH);
        this.add(this.contentPanel, BorderLayout.CENTER);
        this.add(this.buttonsPanel, BorderLayout.SOUTH);
    }

    public void setupHeaderPanel() {
        this.headerPanel = new JPanel();

        // Make the background invisible
        this.headerPanel.setOpaque(false);
    }

    public void setupContentPanel() {
        this.contentPanel = new JPanel();

        // Make the background invisible
        this.contentPanel.setOpaque(false);
    }

    public void setupButtonsPanel() {
        //this.buttonsPanel = new ViewPanel(ResourcesPaths.SPRITE_UI_PATH + "bandeau_menu.png");
        //this.buttonsPanel = new ViewPanel("bandeau_menu.png");
        this.buttonsPanel.setLayout(new BorderLayout());

        // Back button by default
        JButton backButton = NewContextTransitionButton("Back", "bouton_retour1.png", "bouton_retour2.png", "HomeView", true);
        this.buttonsPanel.add(backButton, BorderLayout.WEST);
    }

    protected JButton NewContextTransitionButton(String text, String standardImage, String selectedImage, String nextView) {
        System.out.println(text);
        return NewContextTransitionButton(text, standardImage, selectedImage, nextView, true, false);
    }

    protected JButton NewContextTransitionButton(String text, String standardImage, String selectedImage, String nextView, boolean isBackButton) {
        return NewContextTransitionButton(text, standardImage, selectedImage, nextView, true, isBackButton);
    }

    protected JButton NewContextTransitionButton(String text, String standardImage, String selectedImage, String nextView, boolean isEnable, boolean isBackButton) {
        ContextTransitionButton button = new ContextTransitionButton(text, standardImage, selectedImage, isEnable, nextView);
        System.out.println("isBackButton = " + isBackButton + " button = " + text);
        if(isBackButton) {
            controler.addReturnContextActionListener(button);
            button.addActionListener(controler);
        }
        else
        {
            controler.addContextTransitionActionListener(button);
            button.addActionListener(controler);
        }
        return button;
    }

    protected StandardButton CreateActionParamsButton(String text, String standardImage, String selectedImage) {
        return CreateActionParamsButton(text, standardImage, selectedImage, null);
    }

    protected StandardButton CreateActionParamsButton(String text, String standardImage, String selectedImage, View parentView) {
        StandardButton button = new StandardButton(parentView, text, standardImage,selectedImage);
        controler.addContextTransitionActionListener(button);
        button.addActionListener(controler);

        return button;
    }

    public void setup(int windowWidth, int windowHeight) {
        setup(windowWidth, windowHeight, 0, 0);
    }

    public void setup(int windowWidth, int windowHeight, int contentPanelWidth, int contentPanelHeight) {
        this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.contentPanel.setPreferredSize(new Dimension(contentPanelWidth, contentPanelHeight));
    }

    public void setParameters(Dictionary<String, Object> parameters) {
        this.parameters = parameters;
    }

    public Dictionary<String, Object> getParameters() {
        return this.parameters;
    }

    /** Surcharge de la fonction paintComponent() pour afficher notre image */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Centered content
        //this.contentPanel.paintComponents(g);
    }
}