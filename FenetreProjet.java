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

	int[] ForceDeplacementJ1 = {0,0};
	int[] ForceDeplacementJ2 = {0,0};
	double cstePesenteur = 2;
	double csteFrottementX = 0.2;
	double csteFrottementY = 0.1;

	long tempsPrecedent;
	long deltaT;

	int[] touchesJ1;
	int[] touchesJ2 = new int[4];

	long tempsDebutBoostJ1;
	long tempsDebutBoostJ2;

	public JPanel Principal; 

	public ImageIcon skinAvionVioletDroite;
	public ImageIcon skinAvionVioletGauche;
	public ImageIcon skinAvionRougeDroite;
	public ImageIcon skinAvionRougeGauche;

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
		skinAvionRougeDroite = new ImageIcon("Images/SkinAvionRougeDroitemodif.png");
		skinAvionRougeGauche = new ImageIcon("Images/SkinAvionRougeGauchemodif.png");

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
		AvionJ1 = new Avion(skinAvionVioletDroite, skinAvionVioletGauche, touchesJ1, 100, 500);
		AvionJ1.updatePos((int)AvionJ1.position[0], (int)AvionJ1.position[1]);
		AvionJ1.setDirection("droite");

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
		AvionJ2 = new Avion (skinAvionRougeGauche, skinAvionRougeGauche, touchesJ2, this.getWidth()-257, 500);
		AvionJ2.updatePos((int)AvionJ2.position[0], (int)AvionJ2.position[1]);
		AvionJ2.setDirection("gauche");

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
		skinGameOver = new ImageIcon("Images/game-over_modif.jpg");
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
		tempsPrecedent = System.currentTimeMillis() + 2000;
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

		if (evenementClavier.contains(KeyEvent.VK_CONTROL) && (AvionJ1.boost == false) && (System.currentTimeMillis() - tempsDebutBoostJ1 > AvionJ1.cooldownBoost)) {
			tempsDebutBoostJ1 = System.currentTimeMillis();
			AvionJ1.startBoost();
			System.out.println("Début du boost du J1");
		}
		if (((System.currentTimeMillis() - tempsDebutBoostJ1) > AvionJ1.dureeBoost) && (AvionJ1.boost == true)){
			AvionJ1.stopBoost();
			System.out.println("Fin du boost du J1");
		}
		
		//--------Touches du Joueur 1-------//

		//Gestion des missiles//
		
		if (evenementClavier.contains(KeyEvent.VK_C)) {
			if (missileJoueur1.isVisible() == false  || missileJoueur1.PosX > this.getWidth() 
			|| missileJoueur1.PosX < 0  || missileJoueur1.PosY > this.getHeight() || missileJoueur1.PosY < 0){
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
			missileJoueur1.updatePos (missileJoueur1.PosX - pasMissile, missileJoueur1.PosY); 
		}else{
			missileJoueur1.updatePos (missileJoueur1.PosX + pasMissile, missileJoueur1.PosY);
		}

		if (evenementClavier.contains(KeyEvent.VK_N)) {
			if (missileJoueur2.isVisible() == false  || missileJoueur2.PosX > this.getWidth() 
			|| missileJoueur2.PosX < 0  || missileJoueur2.PosY > this.getHeight() || missileJoueur2.PosY < 0){
				missileJoueur2.updatePos((int)AvionJ2.position[0] + 60, (int)AvionJ2.position[1] + 50);
				if (AvionJ2.directionDroite == true){
					missileJoueur2.orientation = 0; 
					missileJoueur2.setIcon(skinMissileDroiteRouge);
				}else{
					missileJoueur2.orientation = 1; 
					missileJoueur2.setIcon(skinMissileGaucheRouge);
				}
				missileJoueur2.setVisible(true);
			}
		}

		if (missileJoueur2.orientation == 1){
			missileJoueur2.updatePos (missileJoueur2.PosX - pasMissile , missileJoueur2.PosY); 
		}else{
			missileJoueur2.updatePos (missileJoueur2.PosX + pasMissile, missileJoueur2.PosY);
		}

		// Gestion des touches du Joueur 2 //

		//Boost//

		if (evenementClavier.contains(KeyEvent.VK_SHIFT) && (AvionJ2.boost == false) && (System.currentTimeMillis() - tempsDebutBoostJ2 > AvionJ2.cooldownBoost)) {
			tempsDebutBoostJ2 = System.currentTimeMillis();
			AvionJ2.startBoost();
			System.out.println("Début du boost du J2");
		}
		if (((System.currentTimeMillis() - tempsDebutBoostJ2) > AvionJ2.dureeBoost) && (AvionJ2.boost == true)) {
			AvionJ2.stopBoost();
			System.out.println("Fin du boost du J2");
		}
		
		/*chrono = new Timer (1000, new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e){
				val++;
			}
		});

		if (J1isTouche == true){
			chrono.start();
			while (val <= 3000){
				AvionJ1.immortel = true; 
			}
			chrono.restart();
		}

		if (J2isTouche == true){
			chrono.start();
			while (val <= 3000){
				AvionJ2.immortel = true; 
			}
			chrono.restart();
		}*/

		//gestion des collisions

		if (missileJoueur1.PosX >AvionJ2.position[0] &&  missileJoueur1.PosX< AvionJ2.position[0] + AvionJ2.skin.getIconWidth()
		&& missileJoueur1.PosY> AvionJ2.position[1] &&  missileJoueur1.PosY< AvionJ2.position[1] +AvionJ2.skin.getIconHeight() &&
		missileJoueur1.isVisible() == true ){
			AvionJ2.vie -- ;  

			//AvionJ2.invincible();

			missileJoueur1.setVisible(false) ;  
			//System.out.println(AvionJ2.vie) ; 
			//gestion des points de vie 
			if (AvionJ2.vie == 2) {
				viesJ2.setIcon(deuxPointsDeVie);
				viesJ2.setBounds(this.getWidth() - deuxPointsDeVie.getIconWidth() - 110, 10, deuxPointsDeVie.getIconWidth(), deuxPointsDeVie.getIconHeight());
			}
	
			if (AvionJ2.vie == 1) {
				viesJ2.setIcon(unPointDeVie);
				viesJ2.setBounds(this.getWidth() - unPointDeVie.getIconWidth() - 110, 10, unPointDeVie.getIconWidth(), unPointDeVie.getIconHeight());
			}
	
			if (AvionJ2.vie == 0) {
				viesJ2.setIcon(zeroPointDeVie);
				viesJ2.setBounds(this.getWidth() - zeroPointDeVie.getIconWidth() - 110, 10, zeroPointDeVie.getIconWidth(), zeroPointDeVie.getIconHeight());
			}
		}
		//System.out.println("partie supérieur de x"+AvionJ2.posX  + " position missile "+ missileJoueur1.PosX +
		// "partie sup avion "+AvionJ2.posX +  AvionJ2.skin.getIconWidth() +" vie : "+ AvionJ2.vie  ) ;  // test

		if (missileJoueur2.PosX >AvionJ1.position[0] &&  missileJoueur2.PosX< AvionJ1.position[0] + AvionJ1.skin.getIconWidth()
		&& missileJoueur2.PosY> AvionJ1.position[1] &&  missileJoueur2.PosY< AvionJ1.position[1] +AvionJ1.skin.getIconHeight() &&
		missileJoueur2.isVisible() == true ){
			AvionJ1.vie -- ;  

			//AvionJ2.invincible();

			missileJoueur2.setVisible(false);  
			//System.out.println(AvionJ1.vie); 
			//gestion des points de vie 
			if (AvionJ1.vie == 2) {
				viesJ1.setIcon(deuxPointsDeVie);
				viesJ1.setBounds(100, 10, deuxPointsDeVie.getIconWidth(), deuxPointsDeVie.getIconHeight());
			}
	
			if (AvionJ1.vie == 1) {
				viesJ1.setIcon(unPointDeVie);
				viesJ1.setBounds(100, 10, unPointDeVie.getIconWidth(), unPointDeVie.getIconHeight());
			}
	
			if (AvionJ1.vie == 0) {
				viesJ1.setIcon(zeroPointDeVie);
				viesJ1.setBounds(100, 10, zeroPointDeVie.getIconWidth(), zeroPointDeVie.getIconHeight());
			}
		}
		//System.out.println("AvionJ2.posX = "+ AvionJ2.posX  + " position missile "+ missileJoueur2.PosX +
		// "AvionJ2.posY = "+AvionJ2.posY  +" vie : "+ AvionJ1.vie  ) ;


		//Déplacement des avions//

		AvionJ1.updatePos((int)AvionJ1.deplacements(evenementClavier, this.getWidth(), this.getHeight())[0], 
		(int)AvionJ1.deplacements(evenementClavier, this.getWidth(), this.getHeight())[1]);

		AvionJ2.updatePos((int)AvionJ2.deplacements(evenementClavier, this.getWidth(), this.getHeight())[0], 
		(int)AvionJ2.deplacements(evenementClavier, this.getWidth(), this.getHeight())[1]);

		
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
