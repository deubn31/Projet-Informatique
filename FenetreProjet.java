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

	public missile a; 

	public JPanel Principal; 

	public ImageIcon skinAvionVioletDroite;
	public ImageIcon skinAvionVioletGauche;
	public ImageIcon skinAvionRougeDroite;
	public ImageIcon skinAvionRougeGauche;

	public ImageIcon skinMissile;

	public FenetreProjet() throws IOException {

		// Pour ameliorer la compatibilite des affichages
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.setTitle("IHM Projet - Fenetre de jeu ");
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
		ImageIcon troisPointsDeVie = new ImageIcon("Images/3viesmodif.png");
		ImageIcon deuxPointsDeVie = new ImageIcon("Images/2viesModif.png");
		ImageIcon unPointDeVie = new ImageIcon("Images/1vieModif.png");
		ImageIcon zeroPointDeVie = new ImageIcon("Images/0vieModif.png");

		// PP avions
		ImageIcon PPJoueur1 = new ImageIcon("Images/AvionVertmodif.png");
		ImageIcon PPJoueur2 = new ImageIcon("Images/AvionVioletmodif.png");

		// Skin avions
		skinAvionVioletDroite = new ImageIcon("Images/SkinAvionVioletDroitemodif.png");
		skinAvionVioletGauche = new ImageIcon("Images/SkinAvionVioletGauchemodif.png");
		skinAvionRougeDroite = new ImageIcon("Images/SkinAvionRougeDroitemodif.png");
		skinAvionRougeGauche = new ImageIcon("Images/SkinAvionRougeGauchemodif.png");

		// Skin missile //
		skinMissile = new ImageIcon("Images/missileDroitemodif.png");

		// ----------- JOUEUR 1 --------------//

		// Points de vie Joueur1
		JLabel vie1J1 = new JLabel(troisPointsDeVie);
		vie1J1.setBounds(100, 10, troisPointsDeVie.getIconWidth(), troisPointsDeVie.getIconHeight());

		// Photo de profil Joueur1
		JLabel PPJ1 = new JLabel(PPJoueur1);
		PPJ1.setBounds(10, 10, PPJoueur1.getIconWidth(), PPJoueur1.getIconHeight());

		// Avion du J1
		AvionJ1 = new Avion(skinAvionVioletDroite);
		AvionJ1.updatePos(100, 500);

		//Missile du Joueur 1//

		missileJoueur1 = new missile(skinMissile ,100, 100);
		missileJoueur1.setVisible(false);

		// ----------- JOUEUR 2 --------------//

		// Points de vie Joueur2
		JLabel vie1J2 = new JLabel(troisPointsDeVie);
		vie1J2.setBounds(this.getWidth() - troisPointsDeVie.getIconWidth() - 110, 10, troisPointsDeVie.getIconWidth(), troisPointsDeVie.getIconHeight());

		// Photo de profil Joueur2
		JLabel PPJ2 = new JLabel(PPJoueur2);
		PPJ2.setBounds(this.getWidth() - PPJoueur2.getIconWidth() - 25, 10, PPJoueur2.getIconWidth(), PPJoueur2.getIconHeight());

		// Avion du J2
		AvionJ2 = new Avion (skinAvionRougeGauche) ;
		AvionJ2.updatePos(this.getWidth()-257, 500);
		System.out.println(this.getWidth());

		Principal.add(vie1J1);
		Principal.add(vie1J2);
		Principal.add(PPJ1);
		Principal.add(PPJ2);
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
			missileJoueur1.setVisible(true);
			//AvionJ1.Tire();
		}

		if (evenementClavier.contains(KeyEvent.VK_D)) {
			if (AvionJ1.posX <= this.getWidth() - 170){
				AvionJ1.setIcon(skinAvionVioletDroite);
				AvionJ1.updatePos(AvionJ1.posX + pasJ1, AvionJ1.posY);
			} else if (evenementClavier.contains(KeyEvent.VK_Q)) {
				AvionJ1.updatePos(AvionJ1.posX, AvionJ1.posY);
			}
		}
		if (evenementClavier.contains(KeyEvent.VK_Q)) {
			if (AvionJ1.posX >= pasJ1){
				AvionJ1.setIcon(skinAvionVioletGauche);
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
			//AvionJ2.Tire();
		}

		if (evenementClavier.contains(KeyEvent.VK_M)) {
			if (AvionJ2.posX <= this.getWidth() - 170){
				AvionJ2.setIcon(skinAvionRougeDroite);
				AvionJ2.updatePos(AvionJ2.posX + pasJ2, AvionJ2.posY);
			} else if (evenementClavier.contains(KeyEvent.VK_K)) {
				AvionJ2.updatePos(AvionJ2.posX, AvionJ2.posY);
			}
		}
		if (evenementClavier.contains(KeyEvent.VK_K)) {
			if (AvionJ2.posX > 0){
				AvionJ2.setIcon(skinAvionRougeGauche);
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

	}
}
