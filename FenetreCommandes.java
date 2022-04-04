import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.*;

public class FenetreCommandes extends JFrame implements ActionListener{

    private Color gris = new Color(169,169,169);
    private Color jaune = new Color(255,206,0);
    private Color rouge = new Color(196,52,45);

    private Font policeJ = new FontUIResource("Verdana", Font.ITALIC, (int) Math.round(Toolkit.getDefaultToolkit().getScreenSize().width*0.03));
    private Font police = new FontUIResource("Verdana", Font.BOLD, (int) Math.round(Toolkit.getDefaultToolkit().getScreenSize().width*0.02));

    JButton retour;

    public FenetreCommandes(String nom, int width, int height){

        super(nom);
        setSize(width, height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocation(0,0);

        JPanel monConteneur = new JPanel();
        monConteneur.setLayout(null);
        monConteneur.setBounds(0,0,getWidth(),getHeight());
        monConteneur.setBackground(new Color(119,181,254));

        //Commandes J1
        JPanel CJ1 = new JPanel();
        CJ1.setLayout(null);
        CJ1.setSize((int)(monConteneur.getWidth()/2),(int)(monConteneur.getHeight()/2));
        CJ1.setLocation((int)(monConteneur.getWidth()/2-CJ1.getWidth()/2),(int)(monConteneur.getHeight()/3));
        CJ1.setBackground(rouge);
        monConteneur.add(CJ1);


        /*JLabel TJ1 = new JLabel();
        TJ1.setText("Commandes joueur 1");
        //TJ1.setBounds(200,100,900,100);
        TJ1.setFont(policeJ);
        TJ1.setForeground(rouge);
        monConteneur.add(TJ1);

        JLabel haut1 = new JLabel("Z : se déplacer vers le haut");
        //haut1.setBounds(100, 10, 800, 100);
        haut1.setFont(police);
        CJ1.add(haut1);

        JLabel bas1 = new JLabel("S : se déplacer vers le bas");
        //bas1.setBounds(100, 110, 800, 100);
        bas1.setFont(police);
        CJ1.add(bas1);

        JLabel droite1 = new JLabel("D : se déplacer vers la droite");
        //droite1.setBounds(100, 210, 800, 100);
        droite1.setFont(police);
        CJ1.add(droite1);

        JLabel gauche1 = new JLabel("Q : se déplacer vers la gauche");
        //gauche1.setBounds(100, 310, 800, 100);
        gauche1.setFont(police);
        CJ1.add(gauche1);

        JLabel tir1 = new JLabel("C : tirer un missile");
        //tir1.setBounds(100, 410, 800, 100);
        tir1.setFont(police);
        CJ1.add(tir1);

        JLabel boost1 = new JLabel("Ctrl : utiliser le boost");
        //boost1.setBounds(100, 410, 800, 100);
        boost1.setFont(police);
        CJ1.add(boost1);

        //Commandes J2
        JPanel CJ2 = new JPanel();
        CJ2.setLayout(null);
        monConteneur.add(CJ2);
        CJ2.setBounds(970,200,900,530);
        CJ2.setBackground(jaune);

        JLabel haut2 = new JLabel("O : se déplacer vers le haut");
        haut2.setBounds(100, 10, 800, 100);
        haut2.setFont(police);
        CJ2.add(haut2);

        JLabel bas2 = new JLabel("L : se déplacer vers le bas");
        bas2.setBounds(100, 110, 800, 100);
        bas2.setFont(police);
        CJ2.add(bas2);

        JLabel droite2 = new JLabel("M : se déplacer vers la droite");
        droite2.setBounds(100, 210, 800, 100);
        droite2.setFont(police);
        CJ2.add(droite2);

        JLabel gauche2 = new JLabel("K : se déplacer vers la gauche");
        gauche2.setBounds(100, 310, 800, 100);
        gauche2.setFont(police);
        CJ2.add(gauche2);

        JLabel tir2 = new JLabel("N : tirer un missile");
        tir2.setBounds(100, 410, 800, 100);
        tir2.setFont(police);
        CJ2.add(tir2);

        JLabel boost2 = new JLabel("Shift : utiliser le boost");
        //boost2.setBounds(100, 410, 800, 100);
        boost2.setFont(police);
        CJ1.add(boost2);

        JLabel TJ2 = new JLabel();
        TJ2.setText("Commandes joueur 2");
        TJ2.setBounds(1150,100,900,100);
        TJ2.setFont(policeJ);
        TJ2.setForeground(jaune);
        monConteneur.add(TJ2);*/

        //bouton retour au menu
        retour = new JButton ("Retour");
        retour.setLayout(null);
        retour.setSize(monConteneur.getWidth()/6,monConteneur.getHeight()/10);
        retour.setLocation(monConteneur.getWidth()*4/5,(int)(monConteneur.getHeight()*0.82));
        retour.setBackground(gris);
        retour.setFont(police);
        retour.addActionListener(this);
        monConteneur.add(retour);

        this.add(monConteneur);

        repaint();

        this.setVisible(false);
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
        if(e.getSource()==retour){
            this.setVisible(false);
        }
    }
}
