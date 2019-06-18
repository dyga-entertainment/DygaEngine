package Scripts.Inputs;

import com.dyga.Engine.Source.MVC.Model.Menu.Component.ModelPanel;
import com.dyga.Engine.Source.MVC.Model.Menu.Enums.ModelLayout;
import com.dyga.Engine.Source.MVC.View.Menu.Component.ViewButton;
import com.dyga.Engine.Source.MVC.View.Menu.Component.ViewPanel;
import com.dyga.Engine.Source.Main.Game;
import java.awt.*;

/**
 * Class made specially for com.dyga.LodeRunner menu.
 */
public class ButtonsCallback {

    public ButtonsCallback(){
        System.out.println("This is a constructor");
    }

    public void changeView(ViewButton sourceButton) {
        System.out.println("[Debug] React to button = " + sourceButton.getButtonName());
        System.out.println("[Debug] Next view " + sourceButton.getNextView());
        Game.changeView(sourceButton.getNextView());
    }

    public void back(ViewButton sourceButton) {
        System.out.println("[Debug] Back to the previous view");
        Game.backLastView();
    }

    public void changeWorld(ViewButton sourceButton) {
        System.out.println("[Debug] ChangeWorld = React to button = " + sourceButton.getButtonName());

        // Change the model
        Game.setSelectedWorld(sourceButton.getButtonName());

        // Change the View directly
        String buttonImageUrl = sourceButton.getImageUrl();
        Game.getCurrentView().getModelComponent().setBackgroundImageUrl(buttonImageUrl);
    }

    public void previousLevel(ViewButton sourceButton) {
        System.out.println("[Debug] ChangeLevel = React to button = " + sourceButton.getButtonName());

        // Change the model
        Game.setSelectedLevel(sourceButton.getButtonName());

        // Update the view
        ModelPanel panel = Game.FindPanelByLayout(ModelLayout.CardLayout).get(0);
        panel.previousCardView();

        // Force the view repaint
        ViewPanel viewPanel = (ViewPanel)Game.getViewComponentByUuid(panel.getUuid());
        ((CardLayout)viewPanel.getLayout()).previous(viewPanel);
    }

    public void nextLevel(ViewButton sourceButton) {
        System.out.println("[Debug] ChangeLevel = React to button = " + sourceButton.getButtonName());

        // Change the model
        Game.setSelectedLevel(sourceButton.getButtonName());

        // Update the view
        ModelPanel panel = Game.FindPanelByLayout(ModelLayout.CardLayout).get(0);
        panel.nextCardView();

        // Force the view repaint
        ViewPanel viewPanel = (ViewPanel)Game.getViewComponentByUuid(panel.getUuid());
        ((CardLayout)viewPanel.getLayout()).next(viewPanel);
    }

    public void launchGame(ViewButton sourceButton) {
        changeView(sourceButton);

        // TODO
        Game.launchGame();
    }
}
