import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class FenetreBoutons extends JFrame implements ActionListener{

    private Font policeTitre = new FontUIResource("Serif", Font.BOLD, 60);
    private Font policeJoueur = new FontUIResource("Verdana", Font.BOLD, 30);
    private Font policeJouer = new FontUIResource("Verdana", Font.BOLD, 30);

    private Color jaune = new Color(255,206,0);
    private Color rouge = new Color(196,52,45);
    private Color gris = new Color(169,169,169);
    private Color vert = new Color(50,205,50);

    JButton jouer;
    JButton comm;

    FenetreProjet fenetre1;

    public FenetreBoutons (String nom, int width, int height) throws IOException {
        
        //Fenetre
        super(nom);
        fenetre1 = new FenetreProjet();
        setSize(width, height);
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
        conteneurTitre.setBounds(700,50,500,110);
        conteneurTitre.setBackground(Color.magenta);
        conteneurTitre.setArcs(new Dimension(50, 50));

        JLabel Titre = new JLabel();
        Titre.setText("Titre du jeu");
        conteneurTitre.add(Titre);
        Titre.setBounds(90,0,400,100);
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
        choixJ1.setBounds(350,400,300,100);
        
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
        choixJ2.setBounds(350,400,300,100);

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
    }
    public void actionPerformed (ActionEvent e){
        if (e.getSource() == jouer){
            fenetre1.setVisible(true);
        }
    }
}
