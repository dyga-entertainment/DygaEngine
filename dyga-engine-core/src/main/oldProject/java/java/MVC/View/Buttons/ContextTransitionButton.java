package java.MVC.View.Buttons;

import java.util.Dictionary;

public class ContextTransitionButton extends StandardButton {

    /** Interaction field */
    private String nextView;

    /** Fields that will contains useful informations when changing views */
    private Dictionary<String, Object> parameters;

    public ContextTransitionButton(String text, String standardImage, String selectedImage) {
        this(text, standardImage, selectedImage, true, "None");
    }

    public ContextTransitionButton(String text, String standardImage, String selectedImage, boolean isEnable, String nextView) {
        super(null, text, standardImage, selectedImage, isEnable);

        this.nextView = nextView;
    }

    public String getNextView() {
        return this.nextView;
    }

    public void setNextView(String nextView) {
        this.nextView = nextView;
    }

    public void setParameters(Dictionary<String, Object> parameters) {
        this.parameters = parameters;
    }

    public Dictionary<String, Object> getParams() {
        return parameters;
    }
}
