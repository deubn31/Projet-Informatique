import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.*;

public class FenetreBoutons extends JFrame implements ActionListener{

    private Font policeTitre = new FontUIResource("Serif", Font.BOLD, 60);
    private Font policeJoueur = new FontUIResource("Verdana", Font.BOLD, 30);
    private Font policeJouer = new FontUIResource("Verdana", Font.BOLD, 30);
    private Font police20 = new FontUIResource("Verdana", Font.BOLD, 20);


    private Color jaune = new Color(255,206,0);
    private Color rouge = new Color(196,52,45);
    private Color gris = new Color(169,169,169);
    private Color vert = new Color(50,205,50);

    JButton jouer;
    JButton comm;
    JButton flecheD1;
    JButton flecheG1;
    JButton flecheD2;
    JButton flecheG2;

    private JTextField username1;
    private JTextField username2;

    FenetreProjet fenetre1;
    FenetreCommandes fenetreCom;

    public FenetreBoutons (String nom, int width, int height){
        
        //Fenetre
        super(nom);
        fenetreCom = new FenetreCommandes();
        setSize(width, height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocation(0,0);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        //Conteneur principal
        JPanel conteneurHaut = new JPanel();
        conteneurHaut.setLayout(null);
        conteneurHaut.setBounds(0,0,1920,1080);
        conteneurHaut.setBackground(new Color(119,181,254));

        //label titre       
        RoundedPanel conteneurTitre = new RoundedPanel();
        conteneurTitre.setLayout(null);
        conteneurTitre.setBounds(650,70,600,110);
        conteneurTitre.setBackground(new Color(220,219,212));
        conteneurTitre.setArcs(new Dimension(50, 50));

        JLabel Titre = new JLabel();
        Titre.setText("Bataille aérienne");
        conteneurTitre.add(Titre);
        Titre.setBounds(90,0,500,100);
        Titre.setFont(policeTitre);

        //joueur1
        RoundedPanel conteneurJ1 = new RoundedPanel();
        conteneurJ1.setLayout(null);
        conteneurJ1.setBounds(100,300,800,500);
        conteneurJ1.setBackground(rouge); 
        conteneurJ1.setArcs(new Dimension(50, 50));
        
        JLabel TitreJ1 = new JLabel();
        TitreJ1.setText("Joueur 1");
        TitreJ1.setBounds((int) (100 + conteneurJ1.getWidth()/2.5),220,400,100);
        TitreJ1.setFont(policeJoueur);
        TitreJ1.setForeground(rouge);

        JLabel choixJ1 = new JLabel("Choisissez votre avion");
        conteneurJ1.add(choixJ1);
        choixJ1.setBounds(280,400,300,100);
        choixJ1.setFont(police20);

        JTextField username1 = new JTextField("Rentrez votre pseudo");
        conteneurJ1.add(username1);
        username1.setBounds(200,10,380,100);
        username1.setFont(policeJoueur);
        username1.setBackground(rouge);

        JButton flecheD1 = new JButton();
        flecheD1.setLayout(null);
        conteneurJ1.add(flecheD1);
        flecheD1.setBounds(600,250,50,50);

        JButton flecheG1 = new JButton();
        flecheG1.setLayout(null);
        conteneurJ1.add(flecheG1);
        flecheG1.setBounds(120,250,50,50);
        
        //joueur2
        RoundedPanel conteneurJ2 = new RoundedPanel();
        conteneurJ2.setLayout(null);
        conteneurJ2.setBounds(1000,300,800,500);
        conteneurJ2.setBackground(jaune);
        conteneurJ2.setArcs(new Dimension(50, 50));

        JLabel TitreJ2 = new JLabel();
        TitreJ2.setText("Joueur 2");
        TitreJ2.setBounds((int) (1000 + conteneurJ2.getWidth()/2.5),220,400,100); 
        TitreJ2.setFont(policeJoueur);
        TitreJ2.setForeground(jaune); 
        
        JLabel choixJ2 = new JLabel("Choisissez votre avion");
        conteneurJ2.add(choixJ2);
        choixJ2.setBounds(280,400,300,100);
        choixJ2.setFont(police20);

        JTextField username2 = new JTextField("Rentrez votre pseudo");
        conteneurJ2.add(username2);
        username2.setBounds(200,10,380,100);
        username2.setFont(policeJoueur);
        username2.setBackground(jaune);

        JButton flecheD2 = new JButton();
        flecheD2.setLayout(null);
        conteneurJ2.add(flecheD2);
        flecheD2.setBounds(600,250,50,50);

        JButton flecheG2 = new JButton();
        flecheG2.setLayout(null);
        conteneurJ2.add(flecheG2);
        flecheG2.setBounds(120,250,50,50);

        //bouton jouer
        jouer = new JButton ("JOUER");
        jouer.setLayout(null);
        jouer.setBounds(800,850,300,100);
        jouer.setBackground(vert);
        jouer.setFont(policeJouer);
        jouer.addActionListener(this);

        //bouton commande
        comm = new JButton ("Commandes");
        comm.setLayout(null);
        comm.setBounds(1550,850,300,100);
        comm.setBackground(gris);
        comm.setFont(policeJouer);
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
            fenetre1 = new FenetreProjet();
            fenetre1.setVisible(true);
            this.setVisible(false);
        }if (e.getSource() == comm){
            fenetreCom.setVisible(true);
        }if(e.getSource() == flecheD1){

        }if(e.getSource()==flecheG1){

        }if(e.getSource()==flecheD2){
            
        }if(e.getSource()==flecheG2){
            
        }
    }
}
