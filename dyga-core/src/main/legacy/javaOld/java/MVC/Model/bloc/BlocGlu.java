package java.MVC.Model.bloc;

import java.MVC.Model.jeu.Coordonnees;

public class BlocGlu extends Bloc {
	
	public BlocGlu(Coordonnees position){
		//super(position, ResourcesPaths.SPRITE_BLOCS_GLUE_PATH + "bloc_glu.png");
		super(position, "bloc_glu.png");
		this.concret = true;
	}

}
