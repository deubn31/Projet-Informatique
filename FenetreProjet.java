import java.awt.Graphics;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.event.*;

public class FenetreProjet extends JFrame implements KeyListener {

	BufferedImage image;
	JLabel AvionJ1;

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
		this.setResizable(false);
		// this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Implémentation KeyListener
		this.addKeyListener(this);

		//Panel Principal
		JPanel Principal = new JPanel();
		Principal.setLayout(null);

		//Panel de l'Image de fond
		JPanelBackground Conteneur = new JPanelBackground();

		//Réglage de la taille de la fenêtre en fonction de l'image de fond
		this.setSize(1515, 890);

		//Images de points de Vie
		ImageIcon troisPointsDeVie = new ImageIcon("Images/3viesmodif.png");
		ImageIcon deuxPointsDeVie;
		ImageIcon unPointDeVie;
		ImageIcon zeroPointDeVie;

		//PP avions
		ImageIcon PPJoueur1 = new ImageIcon("Images/AvionVertmodif.png");
		ImageIcon PPJoueur2 = new ImageIcon("Images/AvionVioletmodif.png");

		//----------- JOUEUR 1 --------------//

		//Points de vie Joueur1
        JLabel vie1J1 = new JLabel(troisPointsDeVie);
		vie1J1.setBounds(100,10,troisPointsDeVie.getIconWidth(),troisPointsDeVie.getIconHeight());

		//Photo de profil Joueur1
		JLabel PPJ1 = new JLabel(PPJoueur1);
		PPJ1.setBounds(10,10,PPJoueur1.getIconWidth(), PPJoueur1.getIconHeight());

		//----------- JOUEUR 2 --------------//

		//Points de vie Joueur2
        JLabel vie1J2 = new JLabel(troisPointsDeVie);
		vie1J2.setBounds(this.getWidth()-troisPointsDeVie.getIconWidth()-110,10,troisPointsDeVie.getIconWidth(),troisPointsDeVie.getIconHeight());

		//Photo de profil Joueur2
		JLabel PPJ2 = new JLabel(PPJoueur2);
		PPJ2.setBounds(this.getWidth()-PPJoueur2.getIconWidth()-25,10,PPJoueur2.getIconWidth(), PPJoueur2.getIconHeight());


		Principal.add(vie1J1);
		Principal.add(vie1J2);
		Principal.add(PPJ1);
		Principal.add(PPJ2);

		Principal.add(Conteneur);
		this.setContentPane(Principal);

		this.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		System.out.println(key);
	}
}
