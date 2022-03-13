import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import java.awt.*;
import java.awt.event.*;

public class FenetreBoutons extends JFrame implements ActionListener{

    private Font policeTitre = new FontUIResource("Serif", Font.BOLD, 50);
    private Font policeJoueur = new FontUIResource("Verdana", Font.BOLD, 30);

    private Color jaune = new Color(255,206,0);
    private Color rouge = new Color(196,52,45);

    public FenetreBoutons (String nom, int width, int height) {
        
        //Fenetre
        super(nom);
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
        JLabel Titre = new JLabel();
        Titre.setText("Titre du jeu");
        Titre.setBounds(850,10,600,100);
        Titre.setFont(policeTitre);


        //joueur1
        RoundedPanel conteneurJ1 = new RoundedPanel();
        conteneurJ1.setLayout(null);
        conteneurJ1.setBounds(100,400,800,500);
        conteneurJ1.setBackground(rouge); 
        conteneurJ1.setArcs(new Dimension(50, 50));
        
        JLabel TitreJ1 = new JLabel();
        TitreJ1.setText("Joueur 1");
        TitreJ1.setBounds(450,320,400,100);
        TitreJ1.setFont(policeJoueur);
        TitreJ1.setForeground(rouge);
        
        //joueur2
        RoundedPanel conteneurJ2 = new RoundedPanel();
        conteneurJ2.setLayout(null);
        conteneurJ2.setBounds(1000,400,800,500);
        conteneurJ2.setBackground(jaune);
        conteneurJ2.setArcs(new Dimension(50, 50));

        JLabel TitreJ2 = new JLabel();
        TitreJ2.setText("Joueur 2");
        TitreJ2.setBounds(1380,320,400,100); 
        TitreJ2.setFont(policeJoueur);
        TitreJ2.setForeground(jaune); 

        conteneurHaut.add(Titre);
        conteneurHaut.add(conteneurJ1);
        conteneurHaut.add(TitreJ1); 
        conteneurHaut.add(conteneurJ2);
        conteneurHaut.add(TitreJ2); 
        this.add(conteneurHaut);
    }
    public void actionPerformed (ActionEvent e){
      
    }
}
