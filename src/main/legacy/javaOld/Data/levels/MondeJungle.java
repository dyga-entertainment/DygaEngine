package Data.levels;

import java.MVC.Model.jeu.Monde;
import java.MVC.Model.jeu.Niveau;
import java.MVC.Model.bloc.C;

/** Monde Jungle
 * 
 * @author Axel Grau
 * @version 1.C.__
 *
 */
public class MondeJungle {

	private static Monde jungle;
	
	public static Monde Jungle() {
		jungle = new Monde("Jungle", "/ressources/Images/background_jungle.png");
		/** Creation des niveaux */
		MondeJungle.creerLvl1(jungle);
		//MondeJungle.creerLvl2(jungle);
		//MondeJungle.creerLvl3(jungle);
		return jungle;
	}

	
	private static void creerLvl1(Monde courant) {
		Niveau nouv = new Niveau(false);
		C[][] n = new C[20][30];
		n[0] = new C[]{C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx};
		n[1] = new C[]{C.bx,C.ph,C.__,C.__,C.__,C.__,C.iz,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[2] = new C[]{C.bx,C.bx,C.bx,C.bx,C.bt,C.bt,C.bt,C.bx,C.bx,C.bx,C.bx,C.bt,C.bg,C.bg,C.bg,C.bt,C.bt,C.bt,C.bt,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[3] = new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.il,C.il,C.il,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.bt,C.bt,C.bt,C.bt,C.bt,C.il,C.__,C.__,C.pp,C.bx};
		n[4] = new C[]{C.bx,C.__,C.__,C.be,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.__,C.__,C.il,C.il,C.__,C.__,C.__,C.be,C.bt,C.__,C.__,C.__,C.bt,C.be,C.bt,C.bt,C.bt,C.bx};
		n[5] = new C[]{C.bx,C.il,C.il,C.be,C.bx,C.bx,C.bx,C.__,C.__,C.__,C.__,C.__,C.be,C.bt,C.bt,C.be,C.bt,C.bt,C.bt,C.bt,C.bt,C.__,C.__,C.il,C.bt,C.be,C.__,C.__,C.__,C.bx};
		n[6] = new C[]{C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.bx,C.__,C.__,C.__,C.bt,C.be,C.__,C.__,C.__,C.bx};
		n[7] = new C[]{C.bx,C.bx,C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.bx,C.__,C.__,C.__,C.bx,C.be,C.__,C.__,C.__,C.bx};
		n[8] = new C[]{C.bx,C.bx,C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.bx,C.il,C.il,C.il,C.bx,C.be,C.__,C.__,C.__,C.bx};
		n[9] = new C[]{C.bx,C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.il,C.__,C.__,C.__,C.be,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.bx,C.bx,C.bt,C.bx,C.bx,C.be,C.__,C.__,C.__,C.bx};
		n[10]= new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.bt,C.bt,C.bt,C.bt,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.bx};
		n[11]= new C[]{C.bx,C.il,C.il,C.__,C.__,C.bf,C.bf,C.__,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.bx};
		n[12]= new C[]{C.bx,C.bt,C.bt,C.bt,C.bt,C.__,C.__,C.bt,C.bt,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.bx};
		n[13]= new C[]{C.bx,C.bx,C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.il,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.bx};
		n[14]= new C[]{C.bx,C.bx,C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.be,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.__,C.__,C.__,C.bx};
		n[15]= new C[]{C.bx,C.bx,C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.bx,C.bx,C.bt,C.bt,C.bt,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[16]= new C[]{C.bx,C.bx,C.__,C.__,C.__,C.il,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[17]= new C[]{C.bx,C.__,C.be,C.bg,C.bg,C.bg,C.bt,C.bt,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.be,C.bt,C.bt,C.be,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[18]= new C[]{C.bx,C.il,C.be,C.bx,C.bx,C.bx,C.bx,C.bx,C.bt,C.bt,C.bt,C.bt,C.bt,C.be,C.__,C.be,C.__,C.__,C.__,C.__,C.be,C.bx,C.bx,C.be,C.il,C.il,C.il,C.il,C.pm,C.bx};
		n[19]= new C[]{C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx};
		nouv.remplirNiveau(n);
		/* on ajoute le n au monde */
		nouv.setMusiqueAmbiance("/ressources/Musiques/what_is_love.wav");
		nouv.setTheme("/ressources/Images/background_jungle.png");
		/*
		nouv.setMusiqueAmbiance("/ressources/Musiques/let_it_go.wav");
		nouv.setTheme("/ressources/Images/background_fire.png");*/
		courant.addNiveauTemp(nouv);
	}
	
	private static void creerLvl2(Monde courant) {
		Niveau nouv = new Niveau(false);
		C[][] n = new C[20][30];
		/* INDICATEUR : ***** C.__ 1 C.bt C.be 4 5 6 7 8 9.C.__.1.C.bt.C.be.4.5.6.7.8.9.C.__.1.C.bt.C.be.4.5.6.7.8.9*/
		n[0] = new C[]{C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx};
		n[1] = new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[2] = new C[]{C.bx,C.__,C.__,C.__,C.il,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[3] = new C[]{C.bx,C.__,C.__,C.__,C.bt,C.bt,C.be,C.be,C.be,C.be,C.be,C.__,C.__,C.__,C.__,C.be,C.be,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[4] = new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.be,C.__,C.__,C.be,C.be,C.__,C.be,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.pp,C.bx};
		n[5] = new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.be,C.be,C.__,C.__,C.__,C.be,C.be,C.__,C.__,C.__,C.__,C.__,C.be,C.bt,C.bt,C.bt,C.bx};
		n[6] = new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.be,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.bx};
		n[7] = new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.be,C.be,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.bx};
		n[8] = new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.il,C.be,C.be,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.bx};
		n[9] = new C[]{C.bx,C.bx,C.bx,C.il,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.be,C.be,C.__,C.__,C.__,C.__,C.__,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.__,C.__,C.__,C.bx};
		n[10]= new C[]{C.bx,C.bt,C.bt,C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.be,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[11]= new C[]{C.bx,C.bt,C.__,C.bt,C.__,C.__,C.__,C.__,C.__,C.__,C.il,C.be,C.be,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[12]= new C[]{C.bx,C.bt,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.be,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[13]= new C[]{C.bx,C.bt,C.ph,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.be,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[14]= new C[]{C.bx,C.bt,C.bt,C.bt,C.bt,C.__,C.__,C.__,C.be,C.bt,C.bt,C.bt,C.be,C.bt,C.bt,C.bt,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.il,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[15]= new C[]{C.bx,C.il,C.__,C.__,C.__,C.__,C.__,C.be,C.bt,C.bx,C.bx,C.bx,C.be,C.bx,C.bx,C.bx,C.bt,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.be,C.bt,C.be,C.__,C.bx};
		n[16]= new C[]{C.bx,C.be,C.__,C.__,C.__,C.__,C.be,C.bt,C.bx,C.bx,C.__,C.il,C.be,C.il,C.__,C.bx,C.bx,C.bt,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bt,C.be,C.__,C.bx};
		n[17]= new C[]{C.bx,C.be,C.__,C.__,C.__,C.be,C.bt,C.bt,C.bx,C.bx,C.__,C.__,C.be,C.__,C.__,C.bx,C.bx,C.bt,C.bt,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.bt,C.be,C.__,C.bx};
		n[18]= new C[]{C.bx,C.be,C.__,C.__,C.be,C.bt,C.bt,C.bx,C.bx,C.il,C.__,C.__,C.be,C.__,C.__,C.__,C.bx,C.bx,C.bt,C.bt,C.be,C.__,C.__,C.__,C.__,C.__,C.bt,C.be,C.pm,C.bx};
		n[19]= new C[]{C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx};
		nouv.remplirNiveau(n);
		/* on ajoute le n au monde */
		nouv.setTheme("/ressources/Images/background_jungle.png");
		courant.addNiveau(nouv);
	}
	
	private static void creerLvl3(Monde courant) {
		Niveau nouv = new Niveau(false);
		C[][] n = new C[20][30];
		n[0] = new C[]{C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx};
		n[1] = new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[2] = new C[]{C.bx,C.__,C.__,C.__,C.be,C.be,C.__,C.__,C.il,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[3] = new C[]{C.bx,C.bt,C.be,C.bt,C.be,C.be,C.be,C.__,C.be,C.be,C.be,C.be,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[4] = new C[]{C.bx,C.__,C.be,C.bt,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.be,C.il,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.bt,C.bt,C.bt,C.bt,C.bt,C.bx};
		n[5] = new C[]{C.bx,C.ph,C.be,C.bt,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.be,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[6] = new C[]{C.bx,C.bt,C.bt,C.bt,C.il,C.be,C.be,C.be,C.be,C.be,C.il,C.be,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[7] = new C[]{C.bx,C.bt,C.__,C.__,C.__,C.be,C.be,C.__,C.__,C.__,C.__,C.__,C.be,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[8] = new C[]{C.bx,C.__,C.__,C.__,C.be,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[9] = new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.be,C.be,C.__,C.be,C.__,C.be,C.be,C.be,C.be,C.be,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[10]= new C[]{C.bx,C.__,C.__,C.il,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.il,C.be,C.be,C.be,C.be,C.be,C.__,C.__,C.__,C.be,C.be,C.__,C.__,C.__,C.__,C.bx};
		n[11]= new C[]{C.bx,C.bx,C.be,C.bx,C.__,C.__,C.be,C.be,C.be,C.__,C.__,C.bt,C.bt,C.bt,C.bt,C.__,C.be,C.be,C.__,C.__,C.__,C.__,C.__,C.be,C.be,C.be,C.__,C.__,C.__,C.bx};
		n[12]= new C[]{C.bx,C.bx,C.be,C.bx,C.__,C.__,C.be,C.be,C.be,C.bt,C.bt,C.bt,C.__,C.__,C.bt,C.__,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.be,C.be,C.be,C.__,C.__,C.bx};
		n[13]= new C[]{C.bx,C.__,C.be,C.bx,C.il,C.__,C.__,C.be,C.be,C.bt,C.il,C.il,C.il,C.il,C.bt,C.__,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.be,C.be,C.__,C.__,C.bx};
		n[14]= new C[]{C.bx,C.il,C.be,C.bx,C.bt,C.bt,C.__,C.__,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.__,C.be,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.bx};
		n[15]= new C[]{C.bx,C.__,C.be,C.__,C.__,C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.bx};
		n[16]= new C[]{C.bx,C.__,C.be,C.__,C.__,C.bx,C.il,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.be,C.be,C.be,C.be,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.bx};
		n[17]= new C[]{C.bx,C.__,C.be,C.il,C.__,C.bx,C.bx,C.__,C.__,C.__,C.__,C.be,C.bt,C.bt,C.be,C.__,C.__,C.__,C.be,C.__,C.__,C.be,C.be,C.__,C.__,C.__,C.be,C.__,C.__,C.bx};
		n[18]= new C[]{C.bx,C.il,C.be,C.__,C.il,C.__,C.bx,C.__,C.__,C.__,C.be,C.bt,C.__,C.__,C.be,C.__,C.pm,C.pp,C.be,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.be,C.__,C.__,C.bx};
		n[19]= new C[]{C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx};
		nouv.remplirNiveau(n);
		/* on ajoute le n au monde */
		nouv.setTheme("/ressources/Images/background_jungle.png");
		courant.addNiveau(nouv);
	}
	
}
