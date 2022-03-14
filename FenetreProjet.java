import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;
import java.awt.event.*;

public class FenetreProjet extends JFrame implements KeyListener, ActionListener {

	public BufferedImage image;
	public Avion AvionJ1;
	public int pas = 10;
	public HashSet<Integer> KeyEvent = new HashSet<Integer>();

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

		//Skin avions
		ImageIcon skinAvionViolet = new ImageIcon("Images/SkinAvionVioletDroitemodif.png");
		ImageIcon skinAvionRouge = new ImageIcon("Images/SkinAvionRouge.png");

		//----------- JOUEUR 1 --------------//

		//Points de vie Joueur1
        JLabel vie1J1 = new JLabel(troisPointsDeVie);
		vie1J1.setBounds(100,10,troisPointsDeVie.getIconWidth(),troisPointsDeVie.getIconHeight());

		//Photo de profil Joueur1
		JLabel PPJ1 = new JLabel(PPJoueur1);
		PPJ1.setBounds(10,10,PPJoueur1.getIconWidth(), PPJoueur1.getIconHeight());

		//Avion du J1
		AvionJ1 = new Avion(skinAvionViolet); 

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
		Principal.add(AvionJ1);

		Principal.add(Conteneur);
		this.setContentPane(Principal);

		this.setVisible(false);

		Timer mt = new Timer(500,this);
		mt.start();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		KeyEvent.remove(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
			case 37 : 
				if (AvionJ1.posX>=10){
					AvionJ1.updatePos(AvionJ1.posX-pas, AvionJ1.posY);
				}
				break;
			case 39 : 
				if (AvionJ1.posX <= this.getWidth()-170){
					AvionJ1.updatePos(AvionJ1.posX+pas, AvionJ1.posY);
				}
				break;
			case 38 : 
				if (AvionJ1.posY>=10){
					AvionJ1.updatePos(AvionJ1.posX, AvionJ1.posY-(pas/2));
				}
				break;
			case 40 : 
				if (AvionJ1.posY <= this.getHeight()-100){
					AvionJ1.updatePos(AvionJ1.posX, AvionJ1.posY+(pas/2));
				}
				break;
			case 32 :
				if (pas == 10){
					pas = 50;
				} else {
					pas = 10;
				}
				break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(pressed.contains(KeyEvent.VK_D)) {
			setSize(getWidth()+5, getHeight());
		} else if(pressed.contains(KeyEvent.VK_A)) {
			setSize(getWidth()-5, getHeight());
		} else if(pressed.contains(KeyEvent.VK_S)) {
			setSize(getWidth(), getHeight()+5);
		} else if(pressed.contains(KeyEvent.VK_W)) {
			setSize(getWidth(), getHeight()-5);
		}
		
	}
}
