package Code;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import java.util.HashSet;
import java.awt.Font;
import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Toolkit;


public class FenetreJeu extends JFrame implements KeyListener, ActionListener {

	private Avion AvionJ1;
	private Avion AvionJ2;
	
	private missile missileJoueur1;
	private missile missileJoueur2;
	public final int pasMissile = 40;

	private HashSet<Integer> evenementClavier = new HashSet<Integer>(); //Cette collection contient toutes les touches du clavier qui sont en train d'être appuyées

	//Polices utilisées
	private Font policeJoueur = new FontUIResource("Verdana", Font.BOLD, (int) Math.round(Toolkit.getDefaultToolkit().getScreenSize().width*0.02));

	//Couleurs utilisées
	private Color rouge = new Color(196,52,45);
	private Color vert = new Color(50,205,50);
	
	private String pseudoJ1, pseudoJ2;

	public ImageIcon troisPointsDeVie, deuxPointsDeVie, unPointDeVie, zeroPointDeVie;
	
	private JLabel viesJ1, viesJ2;

	private JLabel gameOver;

	private JButton rejouer, quitter;

	private JLabel quiGagne; //Ce JLabel affiche en fin de partie le gagant

	private boolean jouable = false; //Si jouable = false, les joueurs ne peuvent pas contrôler les avions

	private int[] touchesJ1, touchesJ2;

	private JPanel Principal; 

	private ImageIcon skinMissileDroiteJ1, skinMissileGaucheJ1, skinMissileDroiteJ2, skinMissileGaucheJ2;

	private ImageIcon[] skinsJ1, skinsJ2;

	private ImageIcon skinGameOver;

	private Timer decompte;
	private ImageIcon[] imagesDecompte = new ImageIcon[4];
	private JLabel labelDecompte;

	public static ImageIcon imageBoost;
	public static ImageIcon imageBoostUtilisee;
	private JLabel labelBoostJ1, labelBoostJ2;

	private JFrame FenetreStart; //Le menu de démarage est gardé en mémoire

	private Timer horloge;

	private int tempsVitesse1 = 0 ; 
	private int tempsVitesse2 = 0 ; 

	public FenetreJeu(JFrame FenetreBoutons, int[] keySetJ1, int[] keySetJ2, String usernameJ1, String usernameJ2, ImageIcon[] skinsAvionJ1, ImageIcon[] skinsAvionJ2){

		// Pour ameliorer la compatibilite des affichages
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		FenetreStart = FenetreBoutons; // On garde en paramètre le Menu de démarrage pour pouvoir y retouner lorsque le jeu est terminé

		pseudoJ1 = usernameJ1;
		pseudoJ2 = usernameJ2;

		skinsJ1 = skinsAvionJ1;
		skinsJ2 = skinsAvionJ2;

		this.setTitle("IHM Projet - Fenetre de jeu");

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
		imagesDecompte[0] = new ImageIcon("Code/Images/trois.png");
		imagesDecompte[1] = new ImageIcon("Code/Images/deux.png");
		imagesDecompte[2] = new ImageIcon("Code/Images/un.png");
		imagesDecompte[3] = new ImageIcon("Code/Images/go.png");
		labelDecompte = new JLabel();
		labelDecompte.setVisible(true);

		//Définition des touches de déplacement
		touchesJ1 = keySetJ1;
		touchesJ2 = keySetJ2;

		// Images de points de Vie
		troisPointsDeVie = new ImageIcon("Code/Images/3viesmodif.png");
		deuxPointsDeVie = new ImageIcon("Code/Images/2viesModif.png");
		unPointDeVie = new ImageIcon("Code/Images/1vieModif.png");
		zeroPointDeVie = new ImageIcon("Code/Images/0vieModif.png");

		// PP avions
		ImageIcon PPJoueur1 = new ImageIcon("Code/Images/AvionVertmodif.png");
		ImageIcon PPJoueur2 = new ImageIcon("Code/Images/AvionVioletmodif.png");

		// Skin missile //
		skinMissileDroiteJ1 = new ImageIcon("Code/Images/missile2DroiteJaunemodif.png");
		skinMissileGaucheJ1 = new ImageIcon("Code/Images/missile2GaucheJaunemodif.png");
		skinMissileDroiteJ2 = new ImageIcon("Code/Images/missile2DroiteRougemodif.png");
		skinMissileGaucheJ2 = new ImageIcon("Code/Images/missile2GaucheRougemodif.png");

		// Skin Boost //
		imageBoost = new ImageIcon("Code/Images/boostmodif.png");
		imageBoostUtilisee = new ImageIcon("Code/Images/boostutilisé.png");

		// ----------- JOUEUR 1 --------------//

		// Photo de profil Joueur1
		JLabel PPJ1 = new JLabel(PPJoueur1);
		PPJ1.setBounds(10, 10, PPJoueur1.getIconWidth(), PPJoueur1.getIconHeight());

		// Avion du J1
		AvionJ1 = new Avion(skinsJ1, touchesJ1, 100, 500);
		AvionJ1.updatePos((int)AvionJ1.position[0], (int)AvionJ1.position[1]);
		AvionJ1.setDirection(true); // true = va vers la droite

		//Missile du Joueur 1//
		missileJoueur1 = new missile(skinMissileDroiteJ1, 100, 100);
		missileJoueur1.setVisible(false);
		missileJoueur1.orientation = 0;

		// Points de vie Joueur1
		viesJ1 = new JLabel(troisPointsDeVie);
		viesJ1.setBounds(100, 25, troisPointsDeVie.getIconWidth(), troisPointsDeVie.getIconHeight());

		//Boost J1
		labelBoostJ1 = new JLabel(imageBoost);
		labelBoostJ1.setBounds(5, PPJ1.getHeight() + 20, imageBoost.getIconWidth(), imageBoost.getIconHeight());
		labelBoostJ1.setFont(new Font("Verdana", Font.BOLD, 30));

		//Pseudo J1
		JLabel labelPseudoJ1 = new JLabel();
		labelPseudoJ1.setText(pseudoJ1);
		labelPseudoJ1.setFont(new Font("Verdana", Font.BOLD, 30));
		labelPseudoJ1.setBounds(labelBoostJ1.getX() + labelBoostJ1.getWidth() + 20, labelBoostJ1.getY() - 5, 250, 40);


		// ----------- JOUEUR 2 --------------//

		// Photo de profil Joueur2
		JLabel PPJ2 = new JLabel(PPJoueur2);
		PPJ2.setBounds(this.getWidth() - PPJoueur2.getIconWidth() - 25, 10, PPJoueur2.getIconWidth(), PPJoueur2.getIconHeight());

		// Avion du J2
		AvionJ2 = new Avion (skinsJ2, touchesJ2, this.getWidth()-257, 500);
		AvionJ2.updatePos((int)AvionJ2.position[0], (int)AvionJ2.position[1]);
		AvionJ2.setDirection(false); // false = va vers la gauche

		// Points de vie Joueur2
		viesJ2 = new JLabel(troisPointsDeVie);
		viesJ2.setBounds(this.getWidth() - troisPointsDeVie.getIconWidth() - 110, 25, troisPointsDeVie.getIconWidth(), troisPointsDeVie.getIconHeight());

		//Missile du Joueur 2//
		missileJoueur2 = new missile(skinMissileDroiteJ2, 100, 100);
		missileJoueur2.setVisible(false);
		missileJoueur2.orientation = 1;

		//Décompte du Boost J1
		labelBoostJ2 = new JLabel(imageBoost);
		labelBoostJ2.setBounds(PPJ2.getX() - 5, PPJ2.getHeight() + 20, imageBoost.getIconWidth(), imageBoost.getIconHeight());
		labelBoostJ2.setFont(new Font("Verdana", Font.BOLD, 30));

		//Pseudo J2
		JLabel labelPseudoJ2 = new JLabel();
		labelPseudoJ2.setText(pseudoJ2);
		labelPseudoJ2.setFont(new Font("Verdana", Font.BOLD, 30));
		labelPseudoJ2.setBounds(labelBoostJ2.getX() -270, labelBoostJ2.getY() - 5, 250, 40);
		labelPseudoJ2.setHorizontalAlignment(SwingConstants.RIGHT);

		// Game Over //
		skinGameOver = new ImageIcon("Code/Images/game_over.png");
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

		//JLABEL quiGagne
		quiGagne = new JLabel();
		quiGagne.setSize(1500,this.getHeight()/10);
		quiGagne.setLocation(this.getWidth()/2-quiGagne.getWidth()/2 ,(int)(this.getHeight()*0.15));
		quiGagne.setFont(new Font("Verdana", Font.BOLD, 45));
		quiGagne.setHorizontalAlignment(SwingConstants.CENTER);
		quiGagne.setVisible(false);
		Principal.add(quiGagne);

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

		//Ajout de tous les éléments dans le conteneur Principal
		Principal.add(labelDecompte);
		Principal.add(viesJ1);
		Principal.add(viesJ2);
		Principal.add(labelBoostJ1);
		Principal.add(labelBoostJ2);
		Principal.add(PPJ1);
		Principal.add(PPJ2);
		Principal.add(labelPseudoJ1);
		Principal.add(labelPseudoJ2);
		Principal.add(gameOver);
		Principal.add(AvionJ1);
		Principal.add(missileJoueur1);
		Principal.add(missileJoueur2); 
		Principal.add(AvionJ2);
		Principal.add(Conteneur);
		this.setContentPane(Principal);
		this.setVisible(false);

		//Création du décompte en début de jeu
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

		//Création du Timer du jeu
		horloge = new Timer(16, this); // 16ms pour du 60fps
		horloge.setInitialDelay(5000); //On attend que le décompte soit fini
		horloge.start();
	}

	//Mise à jour de la collection evenementClavier + gestion temps d'appui missiles
	@Override
	public void keyReleased(KeyEvent e) {
		evenementClavier.remove(e.getKeyCode()); //Si une touche est relachée, on la retire

		// ici KeyReleased est utilisé car on souhaite envoyer le missile au moment où le joueur relâche le bouton de tir

		if ((e.getKeyCode() == touchesJ1[4]) && (jouable != false)){ 
			AvionJ1.Tire(missileJoueur1 , this.getWidth() , this.getHeight(), tempsVitesse1 , skinMissileDroiteJ1 , skinMissileGaucheJ1) ; 
		}
		if ((e.getKeyCode() == touchesJ2[4]) && (jouable != false)){  
			AvionJ2.Tire(missileJoueur2 , this.getWidth() , this.getHeight(), tempsVitesse2 , skinMissileDroiteJ2 , skinMissileGaucheJ2) ; 
		}
		 
	}

	@Override
	public void keyTyped(KeyEvent e) { //Non utilisé
	}

	@Override
	public void keyPressed(KeyEvent e) {
		evenementClavier.add(e.getKeyCode()); //Si une touche est préssée, on l'ajoute
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (jouable == true){

			//-----Gestion de la vitesse initiale des missiles-------//

			// les variables "tempsVitesse1" et "tempsVitesse2" vont permettre de moduler la vitesse initale en fonction du temps d'appui sur la touche
		
			if (evenementClavier.contains(touchesJ1[4])) {
				tempsVitesse1 ++ ;
			}else {
				tempsVitesse1 = 0 ; 
			}

			if (evenementClavier.contains(touchesJ2[4])) {
				tempsVitesse2 ++ ; 
			}else{
				tempsVitesse2 = 0 ; 
			}


			//----Déplacement des missiles----//


			missileJoueur1.updatePos (missileJoueur1.deplacements()[0] , missileJoueur1.deplacements()[1]); 
			missileJoueur2.updatePos (missileJoueur2.deplacements()[0] , missileJoueur2.deplacements()[1]); 


			//gestion des collisions avec les missiles//

			AvionJ1.collision(missileJoueur2) ;
			AvionJ2.collision(missileJoueur1) ; 

			//Déplacements//

			AvionJ1.updatePos((int)AvionJ1.deplacements(evenementClavier, this.getWidth(), this.getHeight(), labelBoostJ1)[0], 
			(int)AvionJ1.deplacements(evenementClavier, this.getWidth(), this.getHeight(), labelBoostJ1)[1]);

			AvionJ2.updatePos((int)AvionJ2.deplacements(evenementClavier, this.getWidth(), this.getHeight(), labelBoostJ2)[0], 
			(int)AvionJ2.deplacements(evenementClavier, this.getWidth(), this.getHeight(), labelBoostJ2)[1]);

			//gestion des points de vie //

			AvionJ1.updatePointsDeVie(viesJ1) ;
			AvionJ2.updatePointsDeVie(viesJ2) ;
		}

		
		
		//------ Game Over -----//
		
		if ((AvionJ1.vie <= 0 && AvionJ2.vie >= 0) && (jouable==true)) {
			jouable=false;
			gameOver.setVisible(true);
			quiGagne.setText(pseudoJ2 + " A GAGNÉ(E)") ; 
			quiGagne.setVisible(true) ;
			rejouer.setVisible(true);
			quitter.setVisible(true);
		}
		if ((AvionJ2.vie <= 0 && AvionJ1.vie >= 0) &&(jouable == true)) {
			jouable=false;
			gameOver.setVisible(true);
			quiGagne.setText(pseudoJ1 + " A GAGNÉ(E)") ; 
			quiGagne.setVisible(true) ;
			rejouer.setVisible(true);
			quitter.setVisible(true);
		}

		//Rejouer ou Quiiter//

		if(e.getSource() == rejouer){
			JFrame FenetreProjet2 = new FenetreJeu(FenetreStart, AvionJ1.keySet, AvionJ2.keySet, pseudoJ1, pseudoJ2, skinsJ1, skinsJ2);
			this.dispose();
			FenetreProjet2.setVisible(true);
        }

		if(e.getSource() == quitter){
			this.dispose();
			FenetreStart.setVisible(true);
        }
	}
	/*Amélioration de l'affichage pour Linux
	public void paint(Graphics g) {
        Toolkit.getDefaultToolkit().sync();
	}*/
}
