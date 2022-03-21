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

	public int pasJ1 = 10;
	public int pasJ2 = 10;
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

	public FenetreProjet() throws IOException {

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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
		AvionJ1.setDirection("gauche");

		// Points de vie Joueur2
		viesJ2 = new JLabel(troisPointsDeVie);
		viesJ2.setBounds(this.getWidth() - troisPointsDeVie.getIconWidth() - 110, 10, troisPointsDeVie.getIconWidth(), troisPointsDeVie.getIconHeight());


		// Explosion //

		skinExplosion = new ImageIcon("Images/gifExplosion.gif");
		explosion = new JLabel(skinExplosion);
		explosion.setBounds(500,500,skinExplosion.getIconWidth(), skinExplosion.getIconHeight());
		explosion.setLayout(null);
		explosion.setVisible(true);

		Principal.add(viesJ1);
		Principal.add(viesJ2);
		Principal.add(PPJ1);
		Principal.add(PPJ2);
		Principal.add(explosion);
		Principal.add(AvionJ1);
		Principal.add(missileJoueur1);
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
		if (evenementClavier.contains(KeyEvent.VK_CONTROL)){
			pasJ1 = 20;
		} else {
			pasJ1 = 10;
		}

		//Touches zqsd//

		if (evenementClavier.contains(KeyEvent.VK_F)) {
			missileJoueur1.updatePos(AvionJ1.posX + 60, AvionJ1.posY + 50);
			if (AvionJ1.directionDroite == true){
				missileJoueur1.orientation = 0 ; 
				missileJoueur1.setIcon(skinMissileDroiteJaune);
			}else{
				missileJoueur1.orientation = 1; 
				missileJoueur1.setIcon(skinMissileGaucheJaune);
			}
			missileJoueur1.setVisible(true);

			//AvionJ2.Touchee();

			/*if (AvionJ2.vie == 2) {
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
				fini = true;
			}*/
	
		}

		if (missileJoueur1.orientation == 1){
			missileJoueur1.updatePos (missileJoueur1.PosX - pasMissile , missileJoueur1.PosY ); 
		}else{
			missileJoueur1.updatePos (missileJoueur1.PosX + pasMissile , missileJoueur1.PosY );
		}


		if (evenementClavier.contains(KeyEvent.VK_D)) {
			if (AvionJ1.posX <= this.getWidth() - 170){
				AvionJ1.setIcon(skinAvionVioletDroite);
				AvionJ1.setDirection("droite");
				AvionJ1.updatePos(AvionJ1.posX + pasJ1, AvionJ1.posY);
			} else if (evenementClavier.contains(KeyEvent.VK_Q)) {
				AvionJ1.updatePos(AvionJ1.posX, AvionJ1.posY);
			}
		}
		if (evenementClavier.contains(KeyEvent.VK_Q)) {
			if (AvionJ1.posX >= pasJ1){
				AvionJ1.setIcon(skinAvionVioletGauche);
				AvionJ1.setDirection("gauche");
				AvionJ1.updatePos(AvionJ1.posX - pasJ1, AvionJ1.posY);
			} 
		} 
		if (evenementClavier.contains(KeyEvent.VK_S)) {
			if (AvionJ1.posY <= this.getHeight() - 100) {
				AvionJ1.updatePos(AvionJ1.posX, AvionJ1.posY + (pasJ1 / 2));
			} else if (evenementClavier.contains(KeyEvent.VK_Z)) {
				AvionJ1.updatePos(AvionJ1.posX, AvionJ1.posY);
			}
		} 
		if (evenementClavier.contains(KeyEvent.VK_Z)) {
			if (AvionJ1.posY > 0) {
				AvionJ1.updatePos(AvionJ1.posX, AvionJ1.posY - (pasJ1 / 2));
			}
		}

		//a.updatePos(a.PosX + pasMissile , a.PosY) ;

		// Gestion des touches du Joueur 2 //

		//Boost//
		if (evenementClavier.contains(KeyEvent.VK_SHIFT)){
			pasJ2 = 20;
		} else {
			pasJ2 = 10;
		}

		//Touches zqsd//

		if (evenementClavier.contains(KeyEvent.VK_J)) {
			//a = new missile ( AvionJ2.posX ,AvionJ2.posY) ; 
			//Principal.add(a);
			AvionJ1.Touchee();

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
				fini = true;
			}
		}

		if (evenementClavier.contains(KeyEvent.VK_M)) {
			if (AvionJ2.posX <= this.getWidth() - 170){
				AvionJ2.setIcon(skinAvionRougeDroite);
				AvionJ2.setDirection("droite");
				AvionJ2.updatePos(AvionJ2.posX + pasJ2, AvionJ2.posY);
			} else if (evenementClavier.contains(KeyEvent.VK_K)) {
				AvionJ2.updatePos(AvionJ2.posX, AvionJ2.posY);
			}
		}
		if (evenementClavier.contains(KeyEvent.VK_K)) {
			if (AvionJ2.posX > 0){
				AvionJ2.setIcon(skinAvionRougeGauche);
				AvionJ2.setDirection("gauche");
				AvionJ2.updatePos(AvionJ2.posX - pasJ2, AvionJ2.posY);
			} 
		} 
		if (evenementClavier.contains(KeyEvent.VK_L)) {
			if (AvionJ2.posY <= this.getHeight() - 100) {
				AvionJ2.updatePos(AvionJ2.posX, AvionJ2.posY + (pasJ2 / 2));
			} else if (evenementClavier.contains(KeyEvent.VK_O)) {
				AvionJ2.updatePos(AvionJ2.posX, AvionJ2.posY);
			}
		} 
		if (evenementClavier.contains(KeyEvent.VK_O)) {
			if (AvionJ2.posY > 0) {
				AvionJ2.updatePos(AvionJ2.posX, AvionJ2.posY - (pasJ2 / 2));
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
		if (missileJoueur1.PosX >AvionJ2.posX &&  missileJoueur1.PosX< AvionJ2.posX + AvionJ2.skin.getIconWidth()
		&& missileJoueur1.PosY> AvionJ2.posY &&  missileJoueur1.PosY< AvionJ2.posY +AvionJ2.skin.getIconHeight() &&
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

	}
}
