package java.MVC.View.MenuViews;

import java.MVC.Controler.MainControler;

public class CreditView extends View {
	
	public CreditView(ViewManager vueManager, MainControler controler) {
		//super(vueManager, ResourcesPaths.SPRITE_UI_PATH + "credits.png", controler);
		super(vueManager, "credits.png", controler);
	}
}
