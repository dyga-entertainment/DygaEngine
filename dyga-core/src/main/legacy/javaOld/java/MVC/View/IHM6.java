package java.MVC.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.MVC.Model.jeu.Joueur;
import Data.levels.ProfilData;

public class IHM6 extends JPanel {
	
    private JPButton[] boutons;
    private int profilChoisi;

    public JPanel afficherProfil(Joueur j){
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(4,1,0,0));
        JLabel nomPro = new JLabel(j.getNom());
        JLabel apercu = new JLabel(new ImageIcon(getClass().getResource("/ressources/Images/"+j.getImageProfil())));
        JLabel avancement = new JLabel("Niveau debloque  :  ".concat(String.valueOf(j.getUnlockedWorld()).concat("-".concat(String.valueOf(j.getUnlockedLevel())))));
        JLabel nbViePro = new JLabel("Vies restantes  :  ".concat(String.valueOf(j.getNbVieRestantes())));

        jp.add(nomPro);
        jp.add(apercu);
        jp.add(avancement);
        jp.add(nbViePro);

        return jp;
    }

    public IHM6() {
        BufferedReader fluxEntree=null;
        String ligneLue = "";
        try {
            // Creation du flux vers le fichier texte
            URL url = getClass().getResource("/ressources/gamedata/Profils.txt");
            fluxEntree = new BufferedReader(new FileReader(url.getPath()));

            // Lecture d'une ligne
            ligneLue = fluxEntree.readLine();
        }
        catch(IOException exc){
            exc.printStackTrace();
        }
        finally{
            try{
                if(fluxEntree!=null){
                    // Fermeture du flux vers le fichier
                    fluxEntree.close();
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        System.out.println(ligneLue);
        ProfilData profils = new ProfilData(ligneLue.split(" "));
        this.profilChoisi = 1;
        this.setLayout(new GridLayout(2,1,0,0));
        this.setBackground(Color.BLACK);
        this.repaint();
        this.profilChoisi = 0;
        intermed(profils);
    }

    public void majIHM(ProfilData profils) {
        for ( int i = 0; i < 3; i++) {
            if ( i < profils.getNbJ()) {
                this.boutons[i].removeAll();
                this.boutons[i] = new JPButton("Profil "+(i+1),"bouton_profil"+(i+1)+"1.png","bouton_profil"+(i+1)+"2.png");
                this.boutons[i].setEnabled(true);
                this.boutons[i].setText("");
            } else {
                this.boutons[i].setEnabled(false);
                this.boutons[i].setText("Vide");
            }
            this.removeAll();
            intermed(profils);
        }
    }

    public int getProfilChoisi() {
        return this.profilChoisi;
    }

    public void intermed(ProfilData profils) {
        JPanel pg = new JPanel();
        pg.setLayout(new GridLayout(3,3,60,40));
        pg.setPreferredSize(new Dimension(300,300));

        JPanel pd = new JPanel();

        this.boutons = new JPButton[3];

        for ( int j = 0; j < 3; j++) {
            if ( j >= profils.getNbJ()) {
                    this.boutons[j] = new JPButton("Profil "+(j+1),"bouton_vide.png","bouton_vide.png");
                    this.boutons[j].setEnabled(false);
            } else {
                    this.boutons[j] = new JPButton("Profil "+(j+1),"bouton_profil"+(j+1)+"1.png","bouton_profil"+(j+1)+"2.png");
                JPButton b = this.boutons[j];
                b.setHorizontalTextPosition(SwingConstants.CENTER);
                b.setVerticalTextPosition(SwingConstants.CENTER);
                b.setForeground(Color.WHITE);
                b.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int nbp = profils.getNbJ();
                        for ( int j = 0; j < 3; j++) {
                            if ( b.getTextO() == boutons[j].getTextO()) {
                                boutons[j].setEnabled(false);
                            } else {
                                if ( j < nbp ){
                                    boutons[j].setEnabled(true);
                                } else {
                                    boutons[j].setEnabled(false);;
                                }
                            }
                        }
                        pd.removeAll();
                        switch ( b.getTextO()) {
                        case "Profil 1" :
                            if ( nbp >= 1) {
                                pd.add(afficherProfil(profils.getJ(0)));
                                profilChoisi = 0;
                            }
                            break;
                        case "Profil 2" :
                            if ( nbp >= 2) {
                                pd.add(afficherProfil(profils.getJ(1)));
                                profilChoisi = 1;;
                            }
                            break;
                        default : //case "Profil 3" :
                            if ( nbp == 3) {
                                pd.add(afficherProfil(profils.getJ(2)));
                                profilChoisi = 2;
                            }
                            break;
                        }
                        pd.repaint();
                        add(pg);
                        add(pd);
                        repaint();
                        revalidate();
                    }
                });
                if ( j < profils.getNbJ()) {
                    b.setEnabled(true);
                    b.setText("");
                } else {
                    b.setEnabled(false);
                    b.repaint();
                }
            }
        }

        pg.add(new JPanel());
        pg.add(new JPanel());
        pg.add(new JPanel());
        pg.add(this.boutons[0]);
        pg.add(this.boutons[1]);
        pg.add(this.boutons[2]);	
        pg.add(new JPanel());
        pg.add(new JPanel());
        pg.add(new JPanel());

        if (profils.getNbJ() != 0){
            pd.add(afficherProfil(profils.getJ(0)));
        } else {
            pd.add(new JPanel());
        }

        this.add(pg);
        this.add(pd);
        this.repaint();
        this.validate();
    }
}