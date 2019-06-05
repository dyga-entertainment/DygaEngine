package java.MVC.View.MenuViews;

import java.MVC.Controler.MainControler;

import java.awt.*;

import javax.swing.*;

public class SettingsView extends View {
	
	private JLabel title;

	public SettingsView(ViewManager vueManager, MainControler controler) {
		super(vueManager, controler);
	}

	@Override
	public void setupContentPanel() {
		super.setupContentPanel();

		this.contentPanel.setLayout(new GridLayout(5,1,0,0));

		this.title = new JLabel("Settings");
		Font pf = title.getFont();
		title.setFont(new Font(pf.getName(), Font.PLAIN, 22));
		this.contentPanel.add(this.title, BorderLayout.CENTER);

		String[] resolution = {"resolution 1", "resolution 2", "resolution 3"};

		JPanel resolutionComboBox = new JPanel();
		resolutionComboBox.add(new JLabel("Resolution"));
		JComboBox<String> combo = new JComboBox<String>(resolution);
		combo.setPreferredSize(new Dimension(150, 30));
		combo.setForeground(Color.blue);
		resolutionComboBox.add(combo);
		this.contentPanel.add(resolutionComboBox);


		// Volume UI slider
		JSlider volumeUISlider = new JSlider();
		this.contentPanel.add(volumeUISlider);

		// Volume SFX slider
		JSlider volumeSFXSlider = new JSlider();
		this.contentPanel.add(volumeSFXSlider);

		// Volume Music slider
		JSlider volumeMusicSlider = new JSlider();
		this.contentPanel.add(volumeMusicSlider);

		// Others settings ?
	}
}