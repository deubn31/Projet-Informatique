import javax.imageio.ImageTypeSpecifier;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.awt.Font;
import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Toolkit;


public class FenetreJeu extends JFrame implements KeyListener, ActionListener {

	int tempVitesse1 = 0 ; 
	int tempVitesse2 = 0 ; 

	public BufferedImage image;
	public Avion AvionJ1;
	public Avion AvionJ2;

	public missile missileJoueur1;
	public missile missileJoueur2;
	public int pasMissile = 40;

	public HashSet<Integer> evenementClavier = new HashSet<Integer>();

	private Font policeJoueur = new FontUIResource("Verdana", Font.BOLD, (int) Math.round(Toolkit.getDefaultToolkit().getScreenSize().width*0.02));

	private Color rouge = new Color(196,52,45);
	private Color vert = new Color(50,205,50);

	ImageIcon troisPointsDeVie, deuxPointsDeVie, unPointDeVie, zeroPointDeVie;
	
	JLabel viesJ1, viesJ2;

	JLabel explosion;

	JLabel gameOver;

	JButton rejouer;
	JButton quitter;

	boolean J1isTouche;
	boolean J2isTouche;

	boolean jouable = false;

	int[] touchesJ1, touchesJ2;

	long tempsDebutBoostJ1, tempsDebutBoostJ2;

	public JPanel Principal; 

	public ImageIcon skinAvionVioletDroite, skinAvionVioletGauche, skinAvionVioletDroiteBoost, skinAvionVioletGaucheBoost;

	public ImageIcon skinAvionRougeDroite, skinAvionRougeGauche, skinAvionRougeDroiteBoost, skinAvionRougeGaucheBoost;

	public ImageIcon skinMissileDroiteJaune, skinMissileGaucheJaune, skinMissileDroiteRouge, skinMissileGaucheRouge;

	public ImageIcon skinExplosion;

	public ImageIcon skinGameOver;

	public Timer decompte;
	public ImageIcon[] imagesDecompte = new ImageIcon[4];
	public JLabel labelDecompte;

	public static ImageIcon imageBoost;
	public static ImageIcon imageBoostUtilisee;
	public JLabel labelBoostJ1, labelBoostJ2;

	public JFrame FenetreStart;


	public Timer horloge;

	public FenetreJeu(JFrame FenetreBoutons){

		// Pour ameliorer la compatibilite des affichages
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		FenetreStart = FenetreBoutons;

		this.setTitle("IHM Projet - Fenetre de jeu");
		// Pour placer la fenêtre au centre de l'écran

		// Pour empêcher le redimensionnement de la fenêtre
		this.setResizable(false);

		// Implémentation KeyListener
		this.addKeyListener(this);

		// Panel Principal
		Principal = new JPanel();
		Principal.setLayout(null);

		// JPanel de l'Image de fond
		JPanelBackground Conteneur = new JPanelBackground();

		// Réglage de la taille de la fenêtre en fonction de l'image de fond
		Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
		if (tailleMoniteur.getWidth()> Conteneur.longeurImage){
			tailleMoniteur.width = Conteneur.longeurImage;
		}
		tailleMoniteur.height = Conteneur.largeurImage + 36;

		this.setSize((int)tailleMoniteur.getWidth(), (int)tailleMoniteur.getHeight());

		//Réglages du décompte//
		imagesDecompte[0] = new ImageIcon("Images/trois.png");
		imagesDecompte[1] = new ImageIcon("Images/deux.png");
		imagesDecompte[2] = new ImageIcon("Images/un.png");
		imagesDecompte[3] = new ImageIcon("Images/go.png");
		labelDecompte = new JLabel();
		labelDecompte.setVisible(true);

		//Définition des touches de déplacement
		int [] touchesJ1 = {90,81,83,68};
		int [] touchesJ2 = {79,75,76,77};

		// Images de points de Vie
		troisPointsDeVie = new ImageIcon("Images/3viesmodif.png");
		deuxPointsDeVie = new ImageIcon("Images/2viesModif.png");
		unPointDeVie = new ImageIcon("Images/1vieModif.png");
		zeroPointDeVie = new ImageIcon("Images/0vieModif.png");

		// PP avions
		ImageIcon PPJoueur1 = new ImageIcon("Images/AvionVertmodif.png");
		ImageIcon PPJoueur2 = new ImageIcon("Images/AvionVioletmodif.png");

		// Skin avions
		skinAvionVioletDroite = new ImageIcon("Images/SkinAvionVioletDroitemodif.png");
		skinAvionVioletGauche = new ImageIcon("Images/SkinAvionVioletGauchemodif.png");
		skinAvionVioletDroiteBoost = new ImageIcon("Images/skinAvionVioletDroiteBoost.png");
		skinAvionVioletGaucheBoost = new ImageIcon("Images/skinAvionVioletGaucheBoost.png");

		skinAvionRougeDroite = new ImageIcon("Images/SkinAvionRougeDroitemodif.png");
		skinAvionRougeGauche = new ImageIcon("Images/SkinAvionRougeGauchemodif.png");
		skinAvionRougeDroiteBoost = new ImageIcon("Images/skinAvionRougeDroiteBoost.png");
		skinAvionRougeGaucheBoost = new ImageIcon("Images/skinAvionRougeGaucheBoost.png");

		// Skin missile //
		skinMissileDroiteJaune = new ImageIcon("Images/missile2DroiteJaunemodif.png");
		skinMissileGaucheJaune = new ImageIcon("Images/missile2GaucheJaunemodif.png");
		skinMissileDroiteRouge = new ImageIcon("Images/missile2DroiteRougemodif.png");
		skinMissileGaucheRouge = new ImageIcon("Images/missile2GaucheRougemodif.png");

		// Skin Boost //
		imageBoost = new ImageIcon("Images/boostmodif.png");
		imageBoostUtilisee = new ImageIcon("Images/boostutilisé.png");

		// ----------- JOUEUR 1 --------------//

		// Photo de profil Joueur1
		JLabel PPJ1 = new JLabel(PPJoueur1);
		PPJ1.setBounds(10, 10, PPJoueur1.getIconWidth(), PPJoueur1.getIconHeight());

		// Avion du J1
		AvionJ1 = new Avion(skinAvionVioletDroite, skinAvionVioletGauche, skinAvionVioletDroiteBoost, skinAvionVioletGaucheBoost, touchesJ1, 100, 500);
		AvionJ1.updatePos((int)AvionJ1.position[0], (int)AvionJ1.position[1]);
		AvionJ1.setDirection(true); // true = va vers la droite

		//Missile du Joueur 1//
		missileJoueur1 = new missile(skinMissileDroiteJaune, 100, 100);
		missileJoueur1.setVisible(false);
		missileJoueur1.orientation = 0;

		// Points de vie Joueur1
		viesJ1 = new JLabel(troisPointsDeVie);
		viesJ1.setBounds(100, 25, troisPointsDeVie.getIconWidth(), troisPointsDeVie.getIconHeight());

		//Décompte du Boost J1
		labelBoostJ1 = new JLabel(imageBoost);
		labelBoostJ1.setBounds(5, PPJ1.getHeight() + 20, imageBoost.getIconWidth(), imageBoost.getIconHeight());
		labelBoostJ1.setFont(new Font("Verdana", Font.BOLD, 30));


		// ----------- JOUEUR 2 --------------//

		// Photo de profil Joueur2
		JLabel PPJ2 = new JLabel(PPJoueur2);
		PPJ2.setBounds(this.getWidth() - PPJoueur2.getIconWidth() - 25, 10, PPJoueur2.getIconWidth(), PPJoueur2.getIconHeight());

		// Avion du J2
		AvionJ2 = new Avion (skinAvionRougeDroite, skinAvionRougeGauche, skinAvionRougeDroiteBoost, skinAvionRougeGaucheBoost, touchesJ2, this.getWidth()-257, 500);
		AvionJ2.updatePos((int)AvionJ2.position[0], (int)AvionJ2.position[1]);
		AvionJ2.setDirection(false); // false = va vers la gauche

		// Points de vie Joueur2
		viesJ2 = new JLabel(troisPointsDeVie);
		viesJ2.setBounds(this.getWidth() - troisPointsDeVie.getIconWidth() - 110, 25, troisPointsDeVie.getIconWidth(), troisPointsDeVie.getIconHeight());

		//Missile du Joueur 2//
		missileJoueur2 = new missile(skinMissileDroiteRouge, 100, 100);
		missileJoueur2.setVisible(false);
		missileJoueur2.orientation = 1;

		//Décompte du Boost J1
		labelBoostJ2 = new JLabel(imageBoost);
		labelBoostJ2.setBounds(PPJ2.getX() - 5, PPJ2.getHeight() + 20, imageBoost.getIconWidth(), imageBoost.getIconHeight());
		labelBoostJ2.setFont(new Font("Verdana", Font.BOLD, 30));


		// Explosion //

		skinExplosion = new ImageIcon("Images/gifExplosion2.gif");
		explosion = new JLabel(skinExplosion);
		explosion.setBounds(500, 500, skinExplosion.getIconWidth(), skinExplosion.getIconHeight());
		explosion.setLayout(null);
		explosion.setVisible(true);

		// Game Over //
		skinGameOver = new ImageIcon("Images/game_over.png");
		gameOver = new JLabel(skinGameOver);
		gameOver.setBounds(this.getWidth()/2 - skinGameOver.getIconWidth()/2, this.getHeight()/2 - skinGameOver.getIconHeight()/2, skinGameOver.getIconWidth(), skinGameOver.getIconHeight());
		gameOver.setLayout(null);
		gameOver.setVisible(false);

		//bouton rejouer
		rejouer = new JButton ("REJOUER");
		rejouer.setLayout(null);
		rejouer.setSize(this.getWidth()/6,this.getHeight()/10);
		rejouer.setLocation(this.getWidth()/2-rejouer.getWidth()/2+150,(int)(this.getHeight()*0.75));
		rejouer.addActionListener(this);
		rejouer.setVisible(false);
		rejouer.setFont(policeJoueur);
        rejouer.setBackground(vert);
		Principal.add(rejouer);

		//bouton quitter
		quitter = new JButton ("QUITTER");
		quitter.setLayout(null);
		quitter.setSize(this.getWidth()/6,this.getHeight()/10);
		quitter.setLocation(this.getWidth()/2-rejouer.getWidth()/2-150,(int)(this.getHeight()*0.75));
		quitter.addActionListener(this);
		quitter.setVisible(false);
		quitter.setFont(policeJoueur);
        quitter.setBackground(rouge);
		Principal.add(quitter);

		Principal.add(labelDecompte);
		Principal.add(viesJ1);
		Principal.add(viesJ2);
		Principal.add(labelBoostJ1);
		Principal.add(labelBoostJ2);
		Principal.add(PPJ1);
		Principal.add(PPJ2);
		//Principal.add(explosion);
		Principal.add(gameOver);
		Principal.add(AvionJ1);
		Principal.add(missileJoueur1);
		Principal.add(missileJoueur2); 
		Principal.add(AvionJ2);

		Principal.add(Conteneur);
		this.setContentPane(Principal);
		this.setVisible(false);

		decompte = new Timer(1000, new ActionListener(){

            int i = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
				if (i == imagesDecompte.length){
					labelDecompte.setVisible(false);
					AvionJ1.tempsPrecedent = System.currentTimeMillis();
					AvionJ2.tempsPrecedent = System.currentTimeMillis();
					jouable = true;
					decompte.stop();
				} else {
					labelDecompte.setBounds(getWidth()/2 - imagesDecompte[i].getIconWidth()/2, getHeight()/2 - imagesDecompte[i].getIconHeight()/2, imagesDecompte[i].getIconWidth(), imagesDecompte[i].getIconHeight());
					labelDecompte.setIcon(imagesDecompte[i]);
                	i++;
				}
            }
        });
		decompte.setInitialDelay(1000);
		decompte.start();

		horloge = new Timer(16, this);
		horloge.setInitialDelay(5000);
		horloge.start();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		evenementClavier.remove(e.getKeyCode());


		System.out.println("you typed  " + e.getKeyChar()); 


		if (e.getKeyChar() == 'c') {
			if (missileJoueur1.estPresent(this.getWidth() , this.getHeight())){
				missileJoueur1.updatePos((int)AvionJ1.position[0] + 60, (int)AvionJ1.position[1] + 50);
				if (AvionJ1.directionDroite == true){
					missileJoueur1.orientation = 0 ; 
					missileJoueur1.setIcon(skinMissileDroiteJaune);
					missileJoueur1.setInit(missileJoueur1.v0x + 35* tempVitesse1, missileJoueur1.v0y- 35 * tempVitesse1);
				}else{
					missileJoueur1.orientation = 1; 
					missileJoueur1.setIcon(skinMissileGaucheJaune);
					missileJoueur1.setInit(-missileJoueur1.v0x  - 35 * tempVitesse1, missileJoueur1.v0y- 35 * tempVitesse1);
				}
				missileJoueur1.setVisible(true) ;
			}
		}
		if (e.getKeyChar() == 'n') {
			if (missileJoueur2.estPresent(this.getWidth() , this.getHeight())){
				missileJoueur2.updatePos((int)AvionJ2.position[0] + 60, (int)AvionJ2.position[1] + 50);
				if (AvionJ2.directionDroite == true){
					missileJoueur2.orientation = 0; 
					missileJoueur2.setIcon(skinMissileDroiteRouge);
					missileJoueur2.setInit(missileJoueur2.v0x + 35* tempVitesse2, missileJoueur2.v0y - 35* tempVitesse2);
				}else{
					missileJoueur2.orientation = 1; 
					missileJoueur2.setIcon(skinMissileGaucheRouge);
					missileJoueur2.setInit(-missileJoueur2.v0x - 35* tempVitesse1, missileJoueur2.v0y - 35* tempVitesse1);
				}
				missileJoueur2.setVisible(true);
			}
		}
		 
		System.out.println("tempVitesse  = " + tempVitesse1) ;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		evenementClavier.add(e.getKeyCode());
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// Gestion des touches du Joueur 1 //

		
		//--------Touches du Joueur 1-------//

		//Gestion des missiles//

		//J1

		if (evenementClavier.contains(KeyEvent.VK_C)) {
			tempVitesse1 ++ ;
		}else {
			tempVitesse1 = 0 ; 
		}

		missileJoueur1.updatePos (missileJoueur1.deplacements()[0] , missileJoueur1.deplacements()[1]); 


		//J2


		if (evenementClavier.contains(KeyEvent.VK_N)) {
			tempVitesse2 ++ ; 
		}else{
			tempVitesse2 = 0 ; 
		}

		missileJoueur2.updatePos (missileJoueur2.deplacements()[0] , missileJoueur2.deplacements()[1]); 


		//gestion des collisions avec les missiles//

		
		AvionJ1.collision(missileJoueur2) ;
		AvionJ2.collision(missileJoueur1) ; 
		


		if (jouable == true){

			//Déplacements//

			AvionJ1.updatePos((int)AvionJ1.deplacements(evenementClavier, this.getWidth(), this.getHeight())[0], 
			(int)AvionJ1.deplacements(evenementClavier, this.getWidth(), this.getHeight())[1]);

			AvionJ2.updatePos((int)AvionJ2.deplacements(evenementClavier, this.getWidth(), this.getHeight())[0], 
			(int)AvionJ2.deplacements(evenementClavier, this.getWidth(), this.getHeight())[1]);

			//Boost//

			if (evenementClavier.contains(KeyEvent.VK_SHIFT) && (AvionJ2.boost == 2)) {
				AvionJ2.boost(labelBoostJ2);
			}

			if (evenementClavier.contains(KeyEvent.VK_CONTROL) && (AvionJ1.boost == 2)) {
				AvionJ1.boost(labelBoostJ1);
			}

			//gestion des points de vie //

			AvionJ1.updatePointsDeVie(viesJ1) ;
			AvionJ2.updatePointsDeVie(viesJ2) ;
		}

		
		
		//------ Game Over -----//
		
		if ((AvionJ1.vie <= 0 && AvionJ2.vie >= 0) && (jouable==true)) {
			jouable=false;
			gameOver.setVisible(true);
			System.out.println("J2 a gagné");
			rejouer.setVisible(true);
			quitter.setVisible(true);
		}
		if ((AvionJ2.vie <= 0 && AvionJ1.vie >= 0) &&(jouable == true)) {
			jouable=false;
			gameOver.setVisible(true);
			System.out.println("J1 a gagné");
			rejouer.setVisible(true);
			quitter.setVisible(true);
		}

		//Rejouer ou Quiiter//

		if(e.getSource() == rejouer){
			JFrame FenetreProjet2 = new FenetreJeu(FenetreStart);
			this.dispose();
			FenetreProjet2.setVisible(true);
        }

		if(e.getSource() == quitter){
			this.dispose();
			FenetreStart.setVisible(true);
        }
	}
}
