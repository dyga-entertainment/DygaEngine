package Data.levels;

import java.MVC.Model.jeu.Monde;
import java.MVC.Model.jeu.Niveau;
import java.MVC.Model.bloc.C;

/** Monde de Demonstration
 * Monde utilise pour la presentation du projet
 * @author Axel Grau
 * @version 2.0
 *
 */
public class MondeDemonstration {
	
	private static Monde demo;
	
	public static Monde LoadDemoWorld() {
		demo = new Monde("Jungle", "/ressources/Images/background_fire.png");
		/** Creation des niveaux */
		MondeDemonstration.creerLvl1(demo);
		MondeDemonstration.creerLvl2(demo);
		MondeDemonstration.creerLvl3(demo);
		MondeDemonstration.creerLvl4(demo);
		return demo;
	}

	/** creerLvl1
	 * Permet de creer le niveau 1 du monde de demonstration :
	 *  - recuperer des lingots
	 *  - creuser
	 *  - monter des echelles
	 *  - rencontrer des MadMonks
	 * @param courant le monde dans lequel il faut ajouter ce niveau
	 */
	private static void creerLvl1(Monde courant) {
		Niveau nouv = new Niveau(false);
		C[][] n = new C[20][30];
		/*INDICATEUR {0...,1...,2...,3...,4...,5...,6...,7...,8...,9...,10..,11..,12..,13..,14..,15..,16..,17..,18..,19..,20..,21..,22..,23..,24..,25..,26..,27..,28..,29..}*/
		n[0]= new C[]{C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx};
		n[1]= new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[2]= new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[3]= new C[]{C.bx,C.__,C.ph,C.__,C.iz,C.il,C.__,C.il,C.__,C.__,C.il,C.__,C.__,C.il,C.__,C.__,C.__,C.il,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[4]= new C[]{C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.__,C.__,C.bx,C.bx};
		n[5]= new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[6]= new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[7]= new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[8]= new C[]{C.bx,C.be,C.bt,C.bt,C.bt,C.bt,C.bt,C.be,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bx};
		n[9]= new C[]{C.bx,C.be,C.bt,C.__,C.__,C.__,C.bt,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[10]=new C[]{C.bx,C.be,C.bt,C.il,C.il,C.__,C.bt,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[11]=new C[]{C.bx,C.be,C.bt,C.bt,C.bt,C.bt,C.bt,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.bt,C.bt,C.bt,C.bt,C.bt,C.be,C.__,C.__,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx};
		n[12]=new C[]{C.bx,C.be,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.bt,C.__,C.__,C.__,C.bt,C.be,C.il,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[13]=new C[]{C.bx,C.be,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.bf,C.bf,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[14]=new C[]{C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.__,C.__,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.be,C.__,C.bx,C.bx};
		n[15]=new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.bx};
		n[16]=new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.bf,C.bf,C.bf,C.bf,C.be,C.__,C.__,C.__,C.__,C.__,C.be,C.bt,C.bt,C.bt,C.bt,C.__,C.__,C.be,C.__,C.__,C.bx};
		n[17]=new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.bx,C.__,C.__,C.bx,C.be,C.__,C.__,C.__,C.bf,C.bf,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.bx};
		n[18]=new C[]{C.bx,C.__,C.__,C.__,C.__,C.il,C.il,C.__,C.be,C.bx,C.__,C.pm,C.bx,C.be,C.__,C.__,C.__,C.bf,C.__,C.be,C.__,C.__,C.__,C.__,C.__,C.pp,C.be,C.__,C.__,C.bx};
		n[19]=new C[]{C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx};
		nouv.remplirNiveau(n);
		//nouv.setMusiqueAmbiance("/ressources/Musiques/what_is_love.wav");
		/*
		nouv.setMusiqueAmbiance(ResourcesPaths.SONGS_ROOT_PATH + "song.wav");
		nouv.setTheme(ResourcesPaths.SPRITE_BACKGROUND_PATH + "background_jungle.png");
		nouv.setImageUrl(ResourcesPaths.SPRITE_BACKGROUND_PATH + "background_custom.png");
		/* on ajoute le niveau au monde */
		courant.addNiveauTemp(nouv);
	}
	
	/** creerLvl2
	 * Permet de creer le niveau 2 du monde de demonstration :
	 * - petite bombe
	 * @param courant
	 */
	private static void creerLvl2(Monde courant) {
		Niveau nouv = new Niveau(false);
		C[][] n = new C[20][30];
		/*INDICATEUR {0...,1...,2...,3...,4...,5...,6...,7...,8...,9...,10..,11..,12..,13..,14..,15..,16..,17..,18..,19..,20..,21..,22..,23..,24..,25..,26..,27..,28..,29..}*/
		n[0]= new C[]{C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx};
		n[1]= new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[2]= new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[3]= new C[]{C.bx,C.__,C.ph,C.__,C.__,C.ib,C.ib,C.ib,C.ib,C.ib,C.ib,C.ib,C.ib,C.ib,C.ib,C.__,C.__,C.il,C.__,C.__,C.il,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[4]= new C[]{C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.__,C.__,C.__,C.__,C.__,C.bx,C.bx};
		n[5]= new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[6]= new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[7]= new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[8]= new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bt,C.bt,C.bt,C.bt,C.__,C.bx};
		n[9]= new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.be,C.be,C.__,C.__,C.__,C.__,C.bx};
		n[10]=new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.be,C.be,C.__,C.__,C.__,C.__,C.bx};
		n[11]=new C[]{C.bx,C.__,C.__,C.bt,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.bt,C.bg,C.bg,C.bg,C.bt,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[12]=new C[]{C.bx,C.__,C.__,C.bt,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bt,C.__,C.__,C.be,C.bt,C.__,C.__,C.__,C.bt,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[13]=new C[]{C.bx,C.__,C.__,C.bt,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bt,C.__,C.__,C.be,C.__,C.__,C.il,C.__,C.bt,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[14]=new C[]{C.bx,C.__,C.__,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx};
		n[15]=new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[16]=new C[]{C.bx,C.bt,C.bt,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.bt,C.bt,C.bt,C.bt,C.be,C.__,C.__,C.__,C.__,C.bx};
		n[17]=new C[]{C.bx,C.__,C.__,C.__,C.__,C.be,C.be,C.be,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.bt,C.__,C.__,C.bt,C.be,C.__,C.__,C.__,C.__,C.bx};
		n[18]=new C[]{C.bx,C.__,C.__,C.__,C.be,C.bt,C.__,C.__,C.__,C.__,C.be,C.bt,C.be,C.__,C.pp,C.__,C.__,C.__,C.__,C.be,C.bt,C.__,C.pm,C.bt,C.be,C.__,C.__,C.__,C.__,C.bx};
		n[19]=new C[]{C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx};
		nouv.remplirNiveau(n);
		/*
		nouv.setMusiqueAmbiance("/ressources/Musiques/radioactive.wav");
		nouv.setTheme("/ressources/Images/background_fire.png");
		nouv.setImageUrl(ResourcesPaths.SPRITE_UI_ICON_PATH + "avatar_axel.png");
		/* on ajoute le niveau au monde */
		courant.addNiveauTemp(nouv);
	}
	
	
	private static void creerLvl3(Monde courant) {
		Niveau nouv = new Niveau(false);
		C[][] n = new C[20][30];
		n[0] = new C[]{C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx};
		n[1] = new C[]{C.bx,C.ph,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[2] = new C[]{C.bx,C.bx,C.bx,C.bx,C.bt,C.bt,C.bt,C.bx,C.bx,C.bx,C.bx,C.bt,C.bg,C.bg,C.bg,C.bt,C.bt,C.bt,C.bt,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[3] = new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.il,C.il,C.il,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.bt,C.bt,C.bt,C.bt,C.bt,C.il,C.__,C.__,C.pp,C.bx};
		n[4] = new C[]{C.bx,C.__,C.__,C.be,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.__,C.__,C.il,C.il,C.__,C.__,C.__,C.be,C.bt,C.__,C.__,C.__,C.bt,C.be,C.bt,C.bt,C.bt,C.bx};
		n[5] = new C[]{C.bx,C.__,C.__,C.be,C.bx,C.bx,C.bx,C.__,C.__,C.__,C.__,C.__,C.be,C.bt,C.bt,C.be,C.bt,C.bt,C.bt,C.bt,C.bt,C.__,C.__,C.il,C.bt,C.be,C.__,C.__,C.__,C.bx};
		n[6] = new C[]{C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.bx,C.__,C.__,C.__,C.bt,C.be,C.__,C.__,C.__,C.bx};
		n[7] = new C[]{C.bx,C.bx,C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.bx,C.__,C.__,C.__,C.bx,C.be,C.__,C.__,C.__,C.bx};
		n[8] = new C[]{C.bx,C.bx,C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.bx,C.il,C.il,C.il,C.bx,C.be,C.__,C.__,C.__,C.bx};
		n[9] = new C[]{C.bx,C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.il,C.__,C.__,C.__,C.be,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.bx,C.bx,C.bt,C.bx,C.bx,C.be,C.__,C.__,C.__,C.bx};
		n[10]= new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.bt,C.bt,C.bt,C.bt,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.bx};
		n[11]= new C[]{C.bx,C.__,C.__,C.__,C.__,C.bf,C.bf,C.__,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.bx};
		n[12]= new C[]{C.bx,C.bt,C.bt,C.bt,C.bt,C.__,C.__,C.bt,C.bt,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.bx};
		n[13]= new C[]{C.bx,C.bx,C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.il,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.bx};
		n[14]= new C[]{C.bx,C.bx,C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.be,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.__,C.__,C.__,C.bx};
		n[15]= new C[]{C.bx,C.bx,C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.bx,C.bx,C.bt,C.bt,C.bt,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[16]= new C[]{C.bx,C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[17]= new C[]{C.bx,C.__,C.be,C.bg,C.bg,C.bg,C.bt,C.bt,C.be,C.__,C.__,C.__,C.__,C.__,C.__,C.be,C.__,C.__,C.__,C.__,C.be,C.bt,C.bt,C.be,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[18]= new C[]{C.bx,C.__,C.be,C.bx,C.bx,C.bx,C.bx,C.bx,C.bt,C.bt,C.bt,C.bt,C.bt,C.be,C.__,C.be,C.__,C.__,C.__,C.__,C.be,C.bx,C.bx,C.be,C.__,C.__,C.__,C.__,C.pm,C.bx};
		n[19]= new C[]{C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx};
		nouv.remplirNiveau(n);
		nouv.setMusiqueAmbiance("/ressources/Musiques/spooky_scary_skeletons.wav");
		nouv.setTheme("/ressources/Images/background_hell.png");

		/*
		nouv.setImageUrl(ResourcesPaths.SPRITE_UI_ICON_PATH + "avatar_greg.png");
		/* on ajoute le niveau au monde */
		courant.addNiveauTemp(nouv);
	}
	
	private static void creerLvl4(Monde courant) {
		Niveau nouv = new Niveau(false);
		C[][] n = new C[20][30];
		/*INDICATEUR {0...,1...,2...,3...,4...,5...,6...,7...,8...,9...,10..,11..,12..,13..,14..,15..,16..,17..,18..,19..,20..,21..,22..,23..,24..,25..,26..,27..,28..,29..}*/
		n[0]= new C[]{C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx};
		n[1]= new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[2]= new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[3]= new C[]{C.bx,C.__,C.ph,C.__,C.__,C.il,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[4]= new C[]{C.bx,C.bx,C.bx,C.bx,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bt,C.bx};
		n[5]= new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[6]= new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[7]= new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[8]= new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[9]= new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[10]=new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[11]=new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.il,C.__,C.__,C.__,C.pm,C.__,C.il,C.__,C.__,C.__,C.il,C.__,C.__,C.__,C.pp,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[12]=new C[]{C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx};
		n[13]=new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[14]=new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[15]=new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[16]=new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[17]=new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[18]=new C[]{C.bx,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.__,C.bx};
		n[19]=new C[]{C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx,C.bx};
		nouv.remplirNiveau(n);
		nouv.setMusiqueAmbiance("/ressources/Musiques/let_it_go.wav");
		nouv.setTheme("/ressources/Images/background_snow.png");

		/*
		nouv.setImageUrl(ResourcesPaths.SPRITE_UI_ICON_PATH + "avatar_damien.png");
		/* on ajoute le niveau au monde */
		courant.addNiveauTemp(nouv);
	}
	
}
