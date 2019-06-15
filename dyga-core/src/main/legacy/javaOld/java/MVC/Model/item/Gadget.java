package java.MVC.Model.item;

import java.MVC.Model.jeu.Coordonnees;

public abstract class Gadget extends Item {
	
	/* ***** ***** ATTRIBUTS ***** ***** */
	protected int[][] rayonAction;
	protected int delai;
	protected boolean usageUnique;
	protected boolean actif;
	/* ***** ***** ********* ***** ***** */
	
	public Gadget(Coordonnees position, String img) {
		super(position,img);
	}
	
	public int[][] getRayonAction(){
		return this.rayonAction;
	}

	public double getDelai(){
		return this.delai;
	}
	
	public boolean isUsageUnique(){
		return this.usageUnique;
	}
	
	public boolean isActif(){
		return this.actif;
	}
        
        public void setActif(Boolean a) {
            this.actif = a;
        }

        public abstract void utiliser();
}
