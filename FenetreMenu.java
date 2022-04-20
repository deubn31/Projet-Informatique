import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.*;

public class FenetreMenu extends JFrame implements ActionListener{

    private Font policeTitre = new FontUIResource("Serif", Font.BOLD, (int) Math.round(Toolkit.getDefaultToolkit().getScreenSize().width*0.030));
    private Font policeJoueur = new FontUIResource("Verdana", Font.BOLD, (int) Math.round(Toolkit.getDefaultToolkit().getScreenSize().width*0.02));
    private Font policeTexte = new FontUIResource("Verdana", Font.ITALIC, (int) Math.round(Toolkit.getDefaultToolkit().getScreenSize().width*0.015));


    private Color jaune = new Color(255,206,0);
    private Color rouge = new Color(196,52,45);
    private Color gris = new Color(169,169,169);
    private Color vert = new Color(50,205,50);

    public ImageIcon flecheSelectionDroite;
    JLabel flecheDroiteJ1;
    JLabel flecheGaucheJ1;
    public ImageIcon flecheSelectionGauche;
    JLabel flecheDroiteJ2;
    JLabel flecheGaucheJ2;

    JButton jouer;
    JButton comm;
    JButton flecheD1;
    JButton flecheG1;
    JButton flecheD2;
    JButton flecheG2;

    JTextField username1;
    JTextField username2;

    FenetreJeu fenetre1;
    FenetreCommandes fenetreCom;

    int choix1;
    int choix2;

    public FenetreMenu (String nom, int width, int height){
        
        //Fenetre
        super(nom);
        setSize(width, height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocation(0,0);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        //Création fenêtre commandes
        fenetreCom = new FenetreCommandes("Menu commandes",this.getWidth(),this.getHeight());
        
        //Conteneur principal
        JPanel conteneurHaut = new JPanel();
        conteneurHaut.setLayout(null);
        conteneurHaut.setBounds(0,0,getWidth(),getHeight());
        conteneurHaut.setBackground(new Color(119,181,254));

        //label titre       
        RoundedPanel conteneurTitre = new RoundedPanel();
        conteneurTitre.setLayout(null);
        conteneurTitre.setSize((int)(getWidth()/3.2),getHeight()/9);
        conteneurTitre.setLocation(getWidth()/2 - conteneurTitre.getWidth()/2,getHeight()/15);
        conteneurTitre.setBackground(new Color(220,219,212));
        conteneurTitre.setArcs(new Dimension(50, 50));

        JLabel Titre = new JLabel("Bataille aérienne",SwingConstants.CENTER);
        conteneurTitre.add(Titre,BorderLayout.CENTER);
        Titre.setSize(conteneurTitre.getWidth(),conteneurTitre.getHeight());
        Titre.setFont(policeTitre);

        //joueur1
        RoundedPanel conteneurJ1 = new RoundedPanel();
        conteneurJ1.setLayout(null);
        conteneurJ1.setSize((int)(getWidth()/2.3), getHeight()/2);
        conteneurJ1.setLocation(getWidth()/4 - conteneurJ1.getWidth()/2,(int)(getHeight()/3.5));
        conteneurJ1.setBackground(rouge); 
        conteneurJ1.setArcs(new Dimension(50, 50));
        
        JLabel TitreJ1 = new JLabel();
        TitreJ1.setText("Joueur 1");
        TitreJ1.setSize((int)(getWidth()/10),getHeight()/6);
        TitreJ1.setLocation(getWidth()/4 - TitreJ1.getWidth()/2,(int)(getHeight()/6.5));
        TitreJ1.setFont(policeJoueur);
        TitreJ1.setForeground(rouge);

        JLabel choixJ1 = new JLabel("Choisissez votre avion");
        conteneurJ1.add(choixJ1);
        choixJ1.setSize((int)(conteneurJ1.getWidth()/2.5),conteneurJ1.getHeight()/8);
        choixJ1.setLocation(conteneurJ1.getWidth()/2-choixJ1.getWidth()/2,conteneurJ1.getHeight()*5/6);
        choixJ1.setFont(policeTexte);

        JTextField username1 = new JTextField("Rentrez votre pseudo");
        username1.setHorizontalAlignment(JTextField.CENTER);
        conteneurJ1.add(username1);
        username1.setSize(conteneurJ1.getWidth()/2,conteneurJ1.getHeight()/6);
        username1.setLocation(conteneurJ1.getWidth()/2 - username1.getWidth()/2, conteneurJ1.getHeight()/12);
        username1.setFont(policeTexte);
        username1.setBackground(rouge);

        JButton flecheD1 = new JButton();
        flecheD1.setLayout(null);
        conteneurJ1.add(flecheD1);
        flecheD1.setSize(conteneurJ1.getHeight()/8,conteneurJ1.getHeight()/8);
        flecheD1.setLocation(conteneurJ1.getWidth()*3/4-flecheD1.getWidth()/2,conteneurJ1.getHeight()/2);
        flecheD1.setOpaque(false);
        flecheD1.setContentAreaFilled(false);
        flecheD1.setBorderPainted(false);
        //Ajout de l'image fleche par-dessus le bouton
        flecheSelectionDroite = new ImageIcon("Images/flecheSelectionDroite.png");
        flecheDroiteJ1 = new JLabel(flecheSelectionDroite);
        conteneurJ1.add(flecheDroiteJ1);
        flecheDroiteJ1.setBounds(conteneurJ1.getWidth()*3/4-flecheD1.getWidth()/2,conteneurJ1.getHeight()/2, flecheSelectionDroite.getIconWidth(), flecheSelectionDroite.getIconHeight());
        flecheDroiteJ1.setLayout(null);

        JButton flecheG1 = new JButton();
        flecheG1.setLayout(null);
        conteneurJ1.add(flecheG1);
        flecheG1.setSize(conteneurJ1.getHeight()/8,conteneurJ1.getHeight()/8);
        flecheG1.setLocation(conteneurJ1.getWidth()/4-flecheG1.getWidth()/2,conteneurJ1.getHeight()/2);
        flecheG1.setOpaque(false);
        flecheG1.setContentAreaFilled(false);
        flecheG1.setBorderPainted(false);
        //Ajout de l'image fleche par-dessus le bouton
        flecheSelectionGauche = new ImageIcon("Images/flecheSelectionGauche.png");
        flecheGaucheJ1 = new JLabel(flecheSelectionGauche);
        conteneurJ1.add(flecheGaucheJ1);
        flecheGaucheJ1.setBounds(conteneurJ1.getWidth()/4-flecheG1.getWidth()/2,conteneurJ1.getHeight()/2, flecheSelectionGauche.getIconWidth(), flecheSelectionGauche.getIconHeight());
        flecheGaucheJ1.setLayout(null);
        
        //joueur2
        RoundedPanel conteneurJ2 = new RoundedPanel();
        conteneurJ2.setLayout(null);
        conteneurJ2.setSize((int)(getWidth()/2.3), getHeight()/2);
        conteneurJ2.setLocation(getWidth()*3/4 - conteneurJ2.getWidth()/2,(int)(getHeight()/3.5));
        conteneurJ2.setBackground(jaune);
        conteneurJ2.setArcs(new Dimension(50, 50));

        JLabel TitreJ2 = new JLabel();
        TitreJ2.setText("Joueur 2");
        TitreJ2.setSize((int)(getWidth()/10),getHeight()/6);
        TitreJ2.setLocation(getWidth()*3/4 - TitreJ2.getWidth()/2,(int)(getHeight()/6.5));
        TitreJ2.setFont(policeJoueur);
        TitreJ2.setForeground(jaune); 
        
        JLabel choixJ2 = new JLabel("Choisissez votre avion");
        conteneurJ2.add(choixJ2);
        choixJ2.setSize((int)(conteneurJ2.getWidth()/2.5),conteneurJ2.getHeight()/8);
        choixJ2.setLocation(conteneurJ2.getWidth()/2-choixJ2.getWidth()/2,conteneurJ2.getHeight()*5/6);
        choixJ2.setFont(policeTexte);

        JTextField username2 = new JTextField("Rentrez votre pseudo");
        username2.setHorizontalAlignment(JTextField.CENTER);
        conteneurJ2.add(username2);
        username2.setSize(conteneurJ2.getWidth()/2,conteneurJ2.getHeight()/6);
        username2.setLocation(conteneurJ2.getWidth()/2 - username2.getWidth()/2, conteneurJ2.getHeight()/12);
        username2.setFont(policeTexte);
        username2.setBackground(jaune);

        JButton flecheD2 = new JButton();
        flecheD2.setLayout(null);
        conteneurJ2.add(flecheD2);
        flecheD2.setSize(conteneurJ2.getHeight()/8,conteneurJ2.getHeight()/8);
        flecheD2.setLocation(conteneurJ2.getWidth()*3/4-flecheD2.getWidth()/2,conteneurJ2.getHeight()/2);
        flecheD2.setOpaque(false);
        flecheD2.setContentAreaFilled(false);
        flecheD2.setBorderPainted(false);
        //Ajout de l'image fleche par-dessus le bouton
        flecheDroiteJ2 = new JLabel(flecheSelectionDroite);
        conteneurJ2.add(flecheDroiteJ2);
        flecheDroiteJ2.setBounds(conteneurJ2.getWidth()*3/4-flecheD2.getWidth()/2,conteneurJ2.getHeight()/2, flecheSelectionDroite.getIconWidth(), flecheSelectionDroite.getIconHeight());
        flecheDroiteJ2.setLayout(null);

        JButton flecheG2 = new JButton();
        flecheG2.setLayout(null);
        conteneurJ2.add(flecheG2);
        flecheG2.setSize(conteneurJ2.getHeight()/8,conteneurJ2.getHeight()/8);
        flecheG2.setLocation(conteneurJ2.getWidth()/4-flecheG2.getWidth()/2,conteneurJ2.getHeight()/2);
        flecheG2.setOpaque(false);
        flecheG2.setContentAreaFilled(false);
        flecheG2.setBorderPainted(false);
        //Ajout de l'image fleche par-dessus le bouton
        flecheSelectionGauche = new ImageIcon("Images/flecheSelectionGauche.png");
        flecheGaucheJ2 = new JLabel(flecheSelectionGauche);
        conteneurJ2.add(flecheGaucheJ2);
        flecheGaucheJ2.setBounds(conteneurJ2.getWidth()/4-flecheG2.getWidth()/2,conteneurJ2.getHeight()/2, flecheSelectionGauche.getIconWidth(), flecheSelectionGauche.getIconHeight());
        flecheGaucheJ2.setLayout(null);

        //bouton jouer
        jouer = new JButton ("JOUER");
        jouer.setLayout(null);
        jouer.setSize(getWidth()/6,getHeight()/10);
        jouer.setLocation(getWidth()/2-jouer.getWidth()/2,(int)(getHeight()*0.82));
        jouer.setBackground(vert);
        jouer.setFont(policeJoueur);
        jouer.addActionListener(this);

        //bouton commande
        comm = new JButton ("Commandes");
        comm.setLayout(null);
        comm.setSize(getWidth()/6,getHeight()/10);
        comm.setLocation(getWidth()*4/5,(int)(getHeight()*0.82));
        comm.setBackground(gris);
        comm.setFont(policeJoueur);
        comm.addActionListener(this);

        conteneurHaut.add(conteneurTitre);
        conteneurHaut.add(conteneurJ1);
        conteneurHaut.add(TitreJ1); 
        conteneurHaut.add(conteneurJ2);
        conteneurHaut.add(TitreJ2);  
        conteneurHaut.add(jouer);
        conteneurHaut.add(comm);
        this.add(conteneurHaut);

        repaint();
    }
    public void actionPerformed (ActionEvent e){
        if (e.getSource() == jouer){
            fenetre1 = new FenetreJeu(this, fenetreCom.keysetJ1, fenetreCom.keysetJ2);
            fenetre1.setVisible(true);
            this.setVisible(false);

            //selection de l'avion et du pseudo
            

            choix1 = Integer.parseInt(username1.getText());
            choix2 = Integer.parseInt(username2.getText());

        }if (e.getSource() == comm){
            fenetreCom.setVisible(true);
        }if(e.getSource() == flecheD1){
            
        }if(e.getSource()==flecheG1){

        }if(e.getSource()==flecheD2){
            
        }if(e.getSource()==flecheG2){
            
        }
    }
}
