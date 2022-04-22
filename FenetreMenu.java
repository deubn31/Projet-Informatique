import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.*;
import java.lang.ref.Reference;

public class FenetreMenu extends JFrame implements ActionListener{

    private Font policeTitre = new FontUIResource("Serif", Font.BOLD, (int) Math.round(Toolkit.getDefaultToolkit().getScreenSize().width*0.030));
    private Font policeJoueur = new FontUIResource("Verdana", Font.BOLD, (int) Math.round(Toolkit.getDefaultToolkit().getScreenSize().width*0.02));
    private Font policeTexte = new FontUIResource("Verdana", Font.ITALIC, (int) Math.round(Toolkit.getDefaultToolkit().getScreenSize().width*0.015));


    private Color jaune = new Color(255,206,0);
    private Color rouge = new Color(196,52,45);
    private Color gris = new Color(169,169,169);
    private Color vert = new Color(50,205,50);

    public ImageIcon[] avionsJ1 = new ImageIcon[5];
    public ImageIcon[] avionsJ2 = new ImageIcon[5];

    public ImageIcon[] avionJ1choisi = new ImageIcon[4];
    public ImageIcon[] avionJ2choisi = new ImageIcon[4];

    public ImageIcon[] avion1J1 = new ImageIcon[4];
    public ImageIcon[] avion2J1 = new ImageIcon[4];
    public ImageIcon[] avion3J1 = new ImageIcon[4];
    public ImageIcon[] avion4J1 = new ImageIcon[4];
    public ImageIcon[] avion5J1 = new ImageIcon[4];

    public ImageIcon[] avion1J2 = new ImageIcon[4];
    public ImageIcon[] avion2J2 = new ImageIcon[4];
    public ImageIcon[] avion3J2 = new ImageIcon[4];
    public ImageIcon[] avion4J2 = new ImageIcon[4];
    public ImageIcon[] avion5J2 = new ImageIcon[4];

    int depart1 = 0;
    JPanel conteneurJ1;
    JLabel skinsJ1;

    int depart2 = 0;
    JPanel conteneurJ2;
    JLabel skinsJ2;

    public ImageIcon flecheSelectionDroite;
    public ImageIcon flecheSelectionGauche;

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

    String pseudoJ1 = "Joueur 1";
    String pseudoJ2 = "Joueur 2";

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

        username1 = new JTextField("Rentrez votre pseudo");
        username1.setHorizontalAlignment(JTextField.CENTER);
        username1.setSize(conteneurJ1.getWidth()/2,conteneurJ1.getHeight()/6);
        username1.setLocation(conteneurJ1.getWidth()/2 - username1.getWidth()/2, conteneurJ1.getHeight()/12);
        username1.setFont(policeTexte);
        username1.setBackground(rouge);
        conteneurJ1.add(username1);

        flecheSelectionDroite = new ImageIcon("Images/flecheSelectionDroite.png");
        flecheD1 = new JButton(flecheSelectionDroite);
        flecheD1.setOpaque(false);
        flecheD1.setContentAreaFilled(false);
        flecheD1.setBorderPainted(true);
        flecheD1.addActionListener(this);
        conteneurJ1.add(flecheD1);
        flecheD1.setSize(conteneurJ1.getHeight()/8,conteneurJ1.getHeight()/8);
        flecheD1.setLocation(conteneurJ1.getWidth()*3/4-flecheD1.getWidth()/2,conteneurJ1.getHeight()/2);

        flecheSelectionGauche = new ImageIcon("Images/flecheSelectionGauche.png");
        flecheG1 = new JButton(flecheSelectionGauche);
        flecheG1.setOpaque(false);
        flecheG1.setContentAreaFilled(false);
        flecheG1.setBorderPainted(true);
        flecheG1.addActionListener(this);
        conteneurJ1.add(flecheG1);
        flecheG1.setSize(conteneurJ1.getHeight()/8,conteneurJ1.getHeight()/8);
        flecheG1.setLocation(conteneurJ1.getWidth()/4-flecheG1.getWidth()/2,conteneurJ1.getHeight()/2);

        //skins avions J1
        avion1J1[0] = new ImageIcon("Images/SkinAvionRougeDroitemodif.png");
		avion1J1[1] = new ImageIcon("Images/SkinAvionRougeGauchemodif.png");
		avion1J1[2] = new ImageIcon("Images/skinAvionRougeDroiteBoost.png");
		avion1J1[3] = new ImageIcon("Images/skinAvionRougeGaucheBoost.png");

        avion2J1[0] = new ImageIcon("Images/AvionMarronDroite.png");
        avion2J1[1] = new ImageIcon("Images/AvionMarronGauche.png");
        avion2J1[2] = new ImageIcon("Images/AvionMarronDroiteBoost.png");
        avion2J1[3] = new ImageIcon("Images/AvionMarronGaucheBoost.png");

        avion3J1[0] = new ImageIcon("Images/AvionBeige2Droite.png");
        avion3J1[1] = new ImageIcon("Images/AvionBeige2Gauche.png");
        avion3J1[2] = new ImageIcon("Images/AvionBeige2DroiteBoost.png");
        avion3J1[3] = new ImageIcon("Images/AvionBeige2GaucheBoost.png");

        avion4J1[0] = new ImageIcon("Images/helicoptereKakiDroite.png");
        avion4J1[1] = new ImageIcon("Images/helicoptereKakiGauche.png");
        avion4J1[2] = new ImageIcon("Images/helicoptereKakiDroite.png");
        avion4J1[3] = new ImageIcon("Images/helicoptereKakiGauche.png");

        avion5J1[0] = new ImageIcon("Images/helicoptereVertDroite.png");
        avion5J1[1] = new ImageIcon("Images/helicoptereVertGauche.png");
        avion5J1[2] = new ImageIcon("Images/helicoptereVertDroite.png");
        avion5J1[3] = new ImageIcon("Images/helicoptereVertGauche.png");

        //skins selection avions J1
        avionsJ1[0] = new ImageIcon("Images/SkinAvionRougeGauchemodif.png");
        avionsJ1[1] = new ImageIcon("Images/AvionMarronGauche.png");
        avionsJ1[2] = new ImageIcon("Images/AvionBeige2Gauche.png");
        avionsJ1[3] = new ImageIcon("Images/helicoptereKakiGauche.png");
        avionsJ1[4] = new ImageIcon("Images/helicoptereVertGauche.png");

        skinsJ1 = new JLabel(avionsJ1[0]);
        skinsJ1.setLayout(null);
        conteneurJ1.add(skinsJ1);
        skinsJ1.setSize((int)(conteneurJ1.getWidth()/2.5),conteneurJ1.getHeight()/2);
        skinsJ1.setLocation(conteneurJ1.getWidth()/2-skinsJ1.getWidth()/2,(int)(conteneurJ1.getHeight()/1.8-skinsJ1.getHeight()/2));
        
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

        username2 = new JTextField("Rentrez votre pseudo");
        username2.setHorizontalAlignment(JTextField.CENTER);
        conteneurJ2.add(username2);
        username2.setSize(conteneurJ2.getWidth()/2,conteneurJ2.getHeight()/6);
        username2.setLocation(conteneurJ2.getWidth()/2 - username2.getWidth()/2, conteneurJ2.getHeight()/12);
        username2.setFont(policeTexte);
        username2.setBackground(jaune);

        flecheD2 = new JButton(flecheSelectionDroite);
        flecheD2.setOpaque(false);
        flecheD2.setContentAreaFilled(false);
        flecheD2.setBorderPainted(true);
        flecheD2.addActionListener(this);
        conteneurJ2.add(flecheD2);
        flecheD2.setSize(conteneurJ2.getHeight()/8,conteneurJ2.getHeight()/8);
        flecheD2.setLocation(conteneurJ2.getWidth()*3/4-flecheD2.getWidth()/2,conteneurJ2.getHeight()/2);

        flecheG2 = new JButton(flecheSelectionGauche);
        flecheG2.setOpaque(false);
        flecheG2.setContentAreaFilled(false);
        flecheG2.setBorderPainted(true);
        flecheG2.addActionListener(this);
        conteneurJ2.add(flecheG2);
        flecheG2.setSize(conteneurJ2.getHeight()/8,conteneurJ2.getHeight()/8);
        flecheG2.setLocation(conteneurJ2.getWidth()/4-flecheG2.getWidth()/2,conteneurJ2.getHeight()/2);

        //skins avions J2

        avion1J2[0] = new ImageIcon("Images/AvionGrisDroite.png");
        avion1J2[1] = new ImageIcon("Images/AvionGrisGauche.png");
        avion1J2[2] = new ImageIcon("Images/AvionGrisDroiteBoost.png");
        avion1J2[3] = new ImageIcon("Images/AvionGrisGaucheBoost.png");

        avion2J2[0] = new ImageIcon("Images/SkinAvionVioletDroitemodif.png");
		avion2J2[1] = new ImageIcon("Images/SkinAvionVioletGauchemodif.png");
		avion2J2[2] = new ImageIcon("Images/skinAvionVioletDroiteBoost.png");
		avion2J2[3] = new ImageIcon("Images/skinAvionVioletGaucheBoost.png");

        avion3J2[0] = new ImageIcon("Images/AvionBleuDroite.png");
        avion3J2[1] = new ImageIcon("Images/AvionBleuGauche.png");
        avion3J2[2] = new ImageIcon("Images/AvionBleuDroiteBoost.png");
        avion3J2[3] = new ImageIcon("Images/AvionBleuGaucheBoost.png");

        avion4J2[0] = new ImageIcon("Images/helicoptereBleu2Droite.png");
        avion4J2[1] = new ImageIcon("Images/helicoptereBleu2Gauche.png");
        avion4J2[2] = new ImageIcon("Images/helicoptereBleu2Droite.png");
        avion4J2[3] = new ImageIcon("Images/helicoptereBleu2Gauche.png");

        avion5J2[0] = new ImageIcon("Images/helicoptereBleuDroite.png");
        avion5J2[1] = new ImageIcon("Images/helicoptereBleuGauche.png");
        avion5J2[2] = new ImageIcon("Images/helicoptereBleuDroite.png");
        avion5J2[3] = new ImageIcon("Images/helicoptereBleuGauche.png");

        //skins selection avions J2
        avionsJ2[0] = new ImageIcon("Images/AvionGrisGauche.png");
        avionsJ2[1] = new ImageIcon("Images/skinAvionVioletGauchemodif.png");
        avionsJ2[2] = new ImageIcon("Images/AvionBleuGauche.png");
        avionsJ2[3] = new ImageIcon("Images/helicoptereBleu2Gauche.png");
        avionsJ2[4] = new ImageIcon("Images/helicoptereBleuGauche.png");

        skinsJ2 = new JLabel(avionsJ2[0]);
        skinsJ2.setLayout(null);
        conteneurJ2.add(skinsJ2);
        skinsJ2.setSize((int)(conteneurJ2.getWidth()/2.5),conteneurJ2.getHeight()/2);
        skinsJ2.setLocation(conteneurJ2.getWidth()/2-skinsJ2.getWidth()/2,(int)(conteneurJ2.getHeight()/1.8-skinsJ2.getHeight()/2));

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

            //envoi des avions selectionnés
            if(depart1==0){
                avionJ1choisi = avion1J1;
            }else if(depart1==1){
                avionJ1choisi = avion2J1;
            }else if(depart1==2){
                avionJ1choisi = avion3J1;
            }else if(depart1==3){
                avionJ1choisi = avion4J1;
            }else if(depart1==4){
                avionJ1choisi = avion5J1;
            }
            if(depart2==0){
                avionJ2choisi = avion1J2;
            }else if(depart2==1){
                avionJ2choisi = avion2J2;
            }else if(depart2==2){
                avionJ2choisi = avion3J2;
            }else if(depart2==3){
                avionJ2choisi = avion4J2;
            }else if(depart2==4){
                avionJ2choisi = avion5J2;
            }

            pseudoJ1 = username1.getText();
            pseudoJ2 = username2.getText();
            
            //envoi des touches et des pseudos
            if ((pseudoJ1.equals("Rentrez votre pseudo")) || (pseudoJ2.equals("Rentrez votre pseudo"))){
                JOptionPane.showMessageDialog(this, "Vous devez choisir un nom pour vos avions");
            } else {
                fenetre1 = new FenetreJeu(this, fenetreCom.keysetJ1, fenetreCom.keysetJ2, pseudoJ1, pseudoJ2, avionJ1choisi, avionJ2choisi);
                fenetre1.setVisible(true);
                this.setVisible(false);
            }
        }
        if (e.getSource() == comm){
            fenetreCom.setVisible(true);
        }
        if(e.getSource() == flecheD1){
            depart1++;
            if(depart1!=avionsJ1.length){
                skinsJ1.setIcon(avionsJ1[depart1]);
            }else{
                depart1=0;
                skinsJ1.setIcon(avionsJ1[depart1]);
            }
        }
        if(e.getSource() == flecheG1){
            depart1--;
            if(depart1==-1){
                depart1=avionsJ1.length-1;
                skinsJ1.setIcon(avionsJ1[depart1]);
            }else{
                skinsJ1.setIcon(avionsJ1[depart1]);
            }
        }
        if(e.getSource() == flecheD2){
            depart2++;
            if(depart2!=avionsJ2.length){
                skinsJ2.setIcon(avionsJ2[depart2]);
            }else{
                depart2=0;
                skinsJ2.setIcon(avionsJ2[depart2]);
            }
        }
        if(e.getSource() == flecheG2){
            depart2--;
            if(depart2==-1){
                depart2=avionsJ2.length-1;
                skinsJ2.setIcon(avionsJ2[depart2]);
            }else{
                skinsJ2.setIcon(avionsJ2[depart2]);
            }  
        }
    }
}
