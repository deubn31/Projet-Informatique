import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;
import java.awt.event.*;

public class FenetreProjet extends JFrame implements KeyListener, ActionListener {

	public BufferedImage image;
	public Avion AvionJ1;
	public Avion AvionJ2;

	public missile missileJoueur1;
	public missile missileJoueur2;

	public int pasMissile = 20;

	public HashSet<Integer> evenementClavier = new HashSet<Integer>();

	ImageIcon troisPointsDeVie;
	ImageIcon deuxPointsDeVie;
	ImageIcon unPointDeVie;
	ImageIcon zeroPointDeVie;
	JLabel viesJ1;
	JLabel viesJ2;
	JLabel explosion;
	boolean J1isTouche;
	boolean J2isTouche;
	boolean fini;

	int pasJ1 = 20;
	int pasJ2 = 20;

	int[] ForceDeplacementJ1 = {0,0};
	int[] ForceDeplacementJ2 = {0,0};
	double cstePesenteur = 0.9;
	double cstefrottement = 0.05;

	long tempsPrecedent;
	long deltaT;

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
		this.setResizable(true);
		// this.setResizable(false);

		// Implémentation KeyListener
		this.addKeyListener(this);

		// Panel Principal
		Principal = new JPanel();
		Principal.setLayout(null);

		// JPanel de l'Image de fond
		JPanelBackground Conteneur = new JPanelBackground();

		// Réglage de la taille de la fenêtre en fonction de l'image de fond
		this.setSize(1515, 890);

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
		AvionJ1 = new Avion(skinAvionVioletDroite);
		AvionJ1.updatePos(100, 500);
		AvionJ1.setDirection("droite");

		//Missile du Joueur 1//

		missileJoueur1 = new missile(skinMissileDroiteJaune ,100, 100);
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
		AvionJ2 = new Avion (skinAvionRougeGauche);
		AvionJ2.updatePos(this.getWidth()-257, 500);
		AvionJ2.setDirection("gauche");

		// Points de vie Joueur2
		viesJ2 = new JLabel(troisPointsDeVie);
		viesJ2.setBounds(this.getWidth() - troisPointsDeVie.getIconWidth() - 110, 10, troisPointsDeVie.getIconWidth(), troisPointsDeVie.getIconHeight());

		//Missile du Joueur 2//

		missileJoueur2 = new missile(skinMissileDroiteRouge ,100, 100);
		missileJoueur2.setVisible(false);
		missileJoueur2.orientation = 1;


		// Explosion //

		skinExplosion = new ImageIcon("Images/gifExplosion2.gif");
		explosion = new JLabel(skinExplosion);
		explosion.setBounds(500,500,skinExplosion.getIconWidth(), skinExplosion.getIconHeight());
		explosion.setLayout(null);
		explosion.setVisible(true);

		Principal.add(viesJ1);
		Principal.add(viesJ2);
		Principal.add(PPJ1);
		Principal.add(PPJ2);
		//Principal.add(explosion);
		Principal.add(AvionJ1);
		Principal.add(missileJoueur1);
		Principal.add(missileJoueur2) ; 
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
		if (evenementClavier.contains(KeyEvent.VK_CONTROL)){
			pasJ1 = 40;
		} else {
			pasJ1 = 20;
		}

		//--------Touches du Joueur 1-------//

		//Missile//
		if (evenementClavier.contains(KeyEvent.VK_F)) {
			if (missileJoueur1.isVisible() == false){
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
			missileJoueur1.updatePos (missileJoueur1.PosX - pasMissile , missileJoueur1.PosY ); 
		}else{
			missileJoueur1.updatePos (missileJoueur1.PosX + pasMissile , missileJoueur1.PosY );
		}

		if (evenementClavier.contains(KeyEvent.VK_J)) {
			missileJoueur2.updatePos((int)AvionJ2.position[0] + 60, (int)AvionJ2.position[1] + 50);
			if (AvionJ2.directionDroite == true){
				missileJoueur2.orientation = 0 ; 
				missileJoueur2.setIcon(skinMissileDroiteRouge);
			}else{
				missileJoueur2.orientation = 1; 
				missileJoueur2.setIcon(skinMissileGaucheRouge);
			}
			missileJoueur2.setVisible(true) ;
		}

		if (missileJoueur2.orientation == 1){
			missileJoueur2.updatePos (missileJoueur2.PosX - pasMissile , missileJoueur2.PosY ); 
		}else{
			missileJoueur2.updatePos (missileJoueur2.PosX + pasMissile , missileJoueur2.PosY );
		}


		//Réinitialisation des forces //
		ForceDeplacementJ1[0] = 0;
		ForceDeplacementJ1[1] = 0;

		//Déplacements//

		if (evenementClavier.contains(KeyEvent.VK_D)) {
			if (evenementClavier.contains(KeyEvent.VK_Q)) {
				ForceDeplacementJ1[0] = 0;
			} else if (AvionJ1.position[0] <= this.getWidth() - AvionJ1.longeurAvion){
				AvionJ1.setIcon(skinAvionVioletDroite);
				AvionJ1.setDirection("droite");
				ForceDeplacementJ1[0] = pasJ1;
			}
		}
		if (evenementClavier.contains(KeyEvent.VK_Q)) {
			if (AvionJ1.position[0] >= pasJ1){
				AvionJ1.setIcon(skinAvionVioletGauche);
				AvionJ1.setDirection("gauche");
				ForceDeplacementJ1[0] = -pasJ1;
			} 
		} 
		if (evenementClavier.contains(KeyEvent.VK_S)) {
			if (evenementClavier.contains(KeyEvent.VK_Z)) {
				ForceDeplacementJ1[1] = 0;
			} else if (AvionJ1.position[1] <= this.getHeight() - AvionJ1.largueurAvion) {
				ForceDeplacementJ1[1] = pasJ1;
			}
		} 
		if (evenementClavier.contains(KeyEvent.VK_Z)) {
			if (AvionJ1.position[1] > 0) {
				ForceDeplacementJ1[1] = -pasJ1;
			}
		}

		// Gestion des touches du Joueur 2 //

		//Boost//
		if (evenementClavier.contains(KeyEvent.VK_SHIFT)){
			pasJ2 = 40;
		} else {
			pasJ2 = 20;
		}

		//Touches Joueur 2//

		if (evenementClavier.contains(KeyEvent.VK_M)) {
			if (evenementClavier.contains(KeyEvent.VK_K)) {
				ForceDeplacementJ2[0] = 0;
			} else if (AvionJ2.position[0] <= this.getWidth() - 170){
				AvionJ2.setIcon(skinAvionRougeDroite);
				AvionJ2.setDirection("droite");
				ForceDeplacementJ2[0] = pasJ2;
			}
		}
		if (evenementClavier.contains(KeyEvent.VK_K)) {
			if (AvionJ2.position[0] > 0){
				AvionJ2.setIcon(skinAvionRougeGauche);
				AvionJ2.setDirection("gauche");
				ForceDeplacementJ2[0] = -pasJ2;
			} 
		} 
		if (evenementClavier.contains(KeyEvent.VK_L)) {
			if (evenementClavier.contains(KeyEvent.VK_O)) {
				ForceDeplacementJ2[1] = 0;
			} else if (AvionJ2.position[1] <= this.getHeight() - 100) {
				ForceDeplacementJ2[1] = pasJ2/2;
			}
		} 
		if (evenementClavier.contains(KeyEvent.VK_O)) {
			if (AvionJ2.position[1] > 0) {
				ForceDeplacementJ2[1] = -pasJ2/2;
			}
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
			System.out.println(AvionJ2.vie) ; 
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

			missileJoueur2.setVisible(false) ;  
			System.out.println(AvionJ1.vie) ; 
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

		//---------- Gestion des déplacements joueur1 -----------//
		
		deltaT = System.currentTimeMillis() - tempsPrecedent;
		//System.out.println("Delta T = " + deltaT);
		tempsPrecedent = System.currentTimeMillis();

		//System.out.println("x = " + AvionJ1.position[0] + ", y = " + AvionJ1.position[1]);

		//System.out.println("Force De déplacement = x : " + ForceDeplacementJ1[0] + " y : " + ForceDeplacementJ1[1]);
		///System.out.println("Force du poids = " + ForcePoids[1]);

		//PFD//
		AvionJ1.acceleration[0] = AvionJ1.masse * (ForceDeplacementJ1[0] - cstefrottement*AvionJ1.vitesse[0]);
		AvionJ1.acceleration[1] = AvionJ1.masse * (ForceDeplacementJ1[1] + AvionJ1.masse*cstePesenteur - cstefrottement*AvionJ1.vitesse[1]);

		AvionJ1.vitesse[0] = AvionJ1.vitesse[0] + AvionJ1.acceleration[0] * deltaT*0.001;
		AvionJ1.vitesse[1] = AvionJ1.vitesse[1] + AvionJ1.acceleration[1] * deltaT*0.001;

		AvionJ1.position[0] = AvionJ1.position[0] + AvionJ1.vitesse[0] * deltaT*0.001;
		AvionJ1.position[1] = AvionJ1.position[1] + AvionJ1.vitesse[1] * deltaT*0.001;

		if (AvionJ1.position[0] > this.getWidth()-160){
			AvionJ1.vitesse[0] = 0.0;
			AvionJ1.position[0] = this.getWidth()-160;
		}

		if (AvionJ1.position[0] < 0){
			AvionJ1.vitesse[0] = 0.0;
			AvionJ1.position[0] = 0.0;
		}

		if (AvionJ1.position[1] > this.getHeight()-90){
			AvionJ1.vitesse[1] = 0.0;
			AvionJ1.position[1] = this.getHeight()-90;
		}

		if (AvionJ1.position[1] < 0){
			AvionJ1.vitesse[1] = 0.0;
			AvionJ1.position[1] = 0.0;
		}

		AvionJ1.updatePos((int)AvionJ1.position[0], (int)AvionJ1.position[1]);
		
		if ((AvionJ1.position[0] >= this.getWidth()-100)){
			
		}
		

	}
}
