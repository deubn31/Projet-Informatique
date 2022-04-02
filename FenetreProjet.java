import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.awt.event.*;

public class FenetreProjet extends JFrame implements KeyListener, ActionListener {

	public BufferedImage image;
	public Avion AvionJ1;
	public Avion AvionJ2;

	public missile missileJoueur1;
	public missile missileJoueur2;
	public int pasMissile = 40;

	public HashSet<Integer> evenementClavier = new HashSet<Integer>();

	ImageIcon troisPointsDeVie;
	ImageIcon deuxPointsDeVie;
	ImageIcon unPointDeVie;
	ImageIcon zeroPointDeVie;
	JLabel viesJ1;
	JLabel viesJ2;
	JLabel explosion;
	JLabel gameOver;
	boolean J1isTouche;
	boolean J2isTouche;
	boolean fini;

	int[] touchesJ1;
	int[] touchesJ2;

	long tempsDebutBoostJ1;
	long tempsDebutBoostJ2;

	public JPanel Principal; 

	public ImageIcon skinAvionVioletDroite;
	public ImageIcon skinAvionVioletGauche;
	public ImageIcon skinAvionVioletDroiteBoost;
	public ImageIcon skinAvionVioletGaucheBoost;


	public ImageIcon skinAvionRougeDroite;
	public ImageIcon skinAvionRougeGauche;
	public ImageIcon skinAvionRougeDroiteBoost;
	public ImageIcon skinAvionRougeGaucheBoost;

	public ImageIcon skinMissileDroiteJaune;
	public ImageIcon skinMissileGaucheJaune;
	public ImageIcon skinMissileDroiteRouge;
	public ImageIcon skinMissileGaucheRouge;

	public ImageIcon skinExplosion;
	public ImageIcon skinGameOver;

	public FenetreProjet(){

		// Pour ameliorer la compatibilite des affichages
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

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
		this.setSize(1515, 890);

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
		viesJ1.setBounds(100, 10, troisPointsDeVie.getIconWidth(), troisPointsDeVie.getIconHeight());


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
		viesJ2.setBounds(this.getWidth() - troisPointsDeVie.getIconWidth() - 110, 10, troisPointsDeVie.getIconWidth(), troisPointsDeVie.getIconHeight());

		//Missile du Joueur 2//

		missileJoueur2 = new missile(skinMissileDroiteRouge, 100, 100);
		missileJoueur2.setVisible(false);
		missileJoueur2.orientation = 1;


		// Explosion //

		skinExplosion = new ImageIcon("Images/gifExplosion2.gif");
		explosion = new JLabel(skinExplosion);
		explosion.setBounds(500, 500, skinExplosion.getIconWidth(), skinExplosion.getIconHeight());
		explosion.setLayout(null);
		explosion.setVisible(true);

		// Game Over //
		skinGameOver = new ImageIcon("Images/game-over_modif.png");
		gameOver = new JLabel(skinGameOver);
		gameOver.setBounds(0, 0, 1515, 890);
		gameOver.setLayout(null);
		gameOver.setVisible(false);

		Principal.add(viesJ1);
		Principal.add(viesJ2);
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

		Timer horloge = new Timer(16, this);
		horloge.setInitialDelay(2000);
		horloge.start();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		evenementClavier.remove(e.getKeyCode());
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

		//Boost//

		if (evenementClavier.contains(KeyEvent.VK_CONTROL) && (AvionJ1.boost == 2)) {
			AvionJ1.boost();
		}
		
		//--------Touches du Joueur 1-------//

		//Gestion des missiles//
		
		if (evenementClavier.contains(KeyEvent.VK_C)) {
			if (missileJoueur1.estPresent(this.getWidth() , this.getHeight())){
				missileJoueur1.updatePos((int)AvionJ1.position[0] + 60, (int)AvionJ1.position[1] + 50);
				if (AvionJ1.directionDroite == true){
					missileJoueur1.orientation = 0 ; 
					missileJoueur1.setIcon(skinMissileDroiteJaune);
				}else{
					missileJoueur1.orientation = 1; 
					missileJoueur1.setIcon(skinMissileGaucheJaune);
				}
				missileJoueur1.setVisible(true) ;
			}
		}
	

		if (missileJoueur1.orientation == 1){
			missileJoueur1.updatePos (missileJoueur1.position[0] - pasMissile, missileJoueur1.position[1]); 
		}else{
			missileJoueur1.updatePos (missileJoueur1.position[0] + pasMissile, missileJoueur1.position[1]);
		}
		
		if (evenementClavier.contains(KeyEvent.VK_N)) {
			if (missileJoueur2.estPresent(this.getWidth() , this.getHeight())){
				missileJoueur2.updatePos((int)AvionJ2.position[0] + 60, (int)AvionJ2.position[1] + 50);
				if (AvionJ2.directionDroite == true){
					missileJoueur2.orientation = 0; 
					missileJoueur2.setIcon(skinMissileDroiteRouge);
					missileJoueur2.setInit(missileJoueur2.v0x , missileJoueur2.v0y);
				}else{
					missileJoueur2.orientation = 1; 
					missileJoueur2.setIcon(skinMissileGaucheRouge);
					missileJoueur2.setInit(-missileJoueur2.v0x , missileJoueur2.v0y);
				}
				missileJoueur2.setVisible(true);
			}
		}

		if (missileJoueur2.orientation == 1){
			missileJoueur2.updatePos (missileJoueur2.deplacements()[0] , missileJoueur2.deplacements()[1]); 
		}else{
			missileJoueur2.updatePos (missileJoueur2.deplacements()[0] , missileJoueur2.deplacements()[1]) ;
		}


		//Boost//

		if (evenementClavier.contains(KeyEvent.VK_SHIFT) && (AvionJ2.boost == 2)) {
			AvionJ2.boost();
		}


		//J2

		//gestion des collisions
		AvionJ2.collision(missileJoueur1) ;  
		//gestion des points de vie 
		AvionJ2.updatePointsDeVie(2 ,viesJ2 , deuxPointsDeVie , unPointDeVie , zeroPointDeVie, this.getWidth()) ; 
		
		//J1
		//gestion des collisions
		AvionJ1.collision(missileJoueur2) ;
		//gestion des points de vie
		AvionJ1.updatePointsDeVie(1 ,viesJ1 , deuxPointsDeVie , unPointDeVie , zeroPointDeVie, this.getWidth()) ;  
		


		//Déplacement des avions//
		if (fini == false){
			AvionJ1.updatePos((int)AvionJ1.deplacements(evenementClavier, this.getWidth(), this.getHeight())[0], 
			(int)AvionJ1.deplacements(evenementClavier, this.getWidth(), this.getHeight())[1]);

			AvionJ2.updatePos((int)AvionJ2.deplacements(evenementClavier, this.getWidth(), this.getHeight())[0], 
			(int)AvionJ2.deplacements(evenementClavier, this.getWidth(), this.getHeight())[1]);
		}

		
		//------ Game Over -----//
		
		if ((AvionJ1.vie <= 0 && AvionJ2.vie >= 0) && (fini==false)) {
			fini=true;
			gameOver.setVisible(true);
			System.out.println("J2 a gagné");
		}
		if ((AvionJ2.vie <= 0 && AvionJ1.vie >= 0) &&(fini == false)) {
			fini=true;
			gameOver.setVisible(true);
			System.out.println("J1 a gagné");
		}
	}
}
