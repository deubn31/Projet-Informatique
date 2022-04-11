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
    
    JTextField Z;
    JTextField S;
    JTextField D;
    JTextField Q;
    JTextField C;
    JTextField V;
    JTextField O;
    JTextField L;
    JTextField M;
    JTextField K;
    JTextField N;
    JTextField B;

    int[] keyset;

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
        CJ1.setSize((int)(monConteneur.getWidth()/2.2),(int)(monConteneur.getHeight()/2));
        CJ1.setLocation((int)(monConteneur.getWidth()/4-CJ1.getWidth()/2),(int)(monConteneur.getHeight()/4));
        CJ1.setBackground(rouge);
        monConteneur.add(CJ1);

        JLabel TJ1 = new JLabel();
        TJ1.setText("Commandes joueur 1");
        TJ1.setSize(getWidth()/3,getHeight()/15);
        TJ1.setLocation((int)(monConteneur.getWidth()/4-TJ1.getWidth()/2),(int)(monConteneur.getHeight()/7));
        TJ1.setFont(policeJ);
        TJ1.setForeground(rouge);
        monConteneur.add(TJ1);

        JLabel haut1 = new JLabel(": se déplacer vers le haut");
        haut1.setSize(CJ1.getWidth(),CJ1.getHeight()/6);
        haut1.setLocation((int)(CJ1.getWidth()/2-haut1.getWidth()/3), CJ1.getHeight()/30);
        haut1.setFont(police);
        CJ1.add(haut1);

        JTextField Z = new JTextField("Z");
        Z.setHorizontalAlignment(JTextField.CENTER);
        CJ1.add(Z);
        Z.setSize(CJ1.getWidth()/18,CJ1.getHeight()/8);
        Z.setLocation((int)(CJ1.getWidth()/2-haut1.getWidth()/2.5), CJ1.getHeight()/18);
        Z.setFont(police);
        Z.setBackground(rouge);

        JLabel bas1 = new JLabel(": se déplacer vers le bas");
        bas1.setSize(CJ1.getWidth(),CJ1.getHeight()/6);
        bas1.setLocation((int)(CJ1.getWidth()/2-bas1.getWidth()/3), (int)(CJ1.getHeight()/6));
        bas1.setFont(police);
        CJ1.add(bas1);

        JTextField S = new JTextField("S");
        S.setHorizontalAlignment(JTextField.CENTER);
        CJ1.add(S);
        S.setSize(CJ1.getWidth()/18,CJ1.getHeight()/8);
        S.setLocation((int)(CJ1.getWidth()/2-bas1.getWidth()/2.5), (int)(CJ1.getHeight()/5.2));
        S.setFont(police);
        S.setBackground(rouge);

        JLabel droite1 = new JLabel(": se déplacer vers la droite");
        droite1.setSize(CJ1.getWidth(),CJ1.getHeight()/6);
        droite1.setLocation((int)(CJ1.getWidth()/2-droite1.getWidth()/3), (int)(CJ1.getHeight()/3.3));
        droite1.setFont(police);
        CJ1.add(droite1);

        JTextField D = new JTextField("D");
        D.setHorizontalAlignment(JTextField.CENTER);
        CJ1.add(D);
        D.setSize(CJ1.getWidth()/18,CJ1.getHeight()/8);
        D.setLocation((int)(CJ1.getWidth()/2-droite1.getWidth()/2.5), (int)(CJ1.getHeight()/3.05));
        D.setFont(police);
        D.setBackground(rouge);

        JLabel gauche1 = new JLabel(": se déplacer vers la gauche");
        gauche1.setSize(CJ1.getWidth(),CJ1.getHeight()/6);
        gauche1.setLocation((int)(CJ1.getWidth()/2-gauche1.getWidth()/3), (int)(CJ1.getHeight()/2.3));
        gauche1.setFont(police);
        CJ1.add(gauche1);

        JTextField G = new JTextField("G");
        G.setHorizontalAlignment(JTextField.CENTER);
        CJ1.add(G);
        G.setSize(CJ1.getWidth()/18,CJ1.getHeight()/8);
        G.setLocation((int)(CJ1.getWidth()/2-gauche1.getWidth()/2.5), (int)(CJ1.getHeight()/2.15));
        G.setFont(police);
        G.setBackground(rouge);

        JLabel tir1 = new JLabel(": tirer un missile");
        tir1.setSize(CJ1.getWidth(),CJ1.getHeight()/6);
        tir1.setLocation((int)(CJ1.getWidth()/2-tir1.getWidth()/3), (int)(CJ1.getHeight()/1.55));
        tir1.setFont(police);
        CJ1.add(tir1);

        JTextField C = new JTextField("C");
        C.setHorizontalAlignment(JTextField.CENTER);
        CJ1.add(C);
        C.setSize(CJ1.getWidth()/18,CJ1.getHeight()/8);
        C.setLocation((int)(CJ1.getWidth()/2-tir1.getWidth()/2.5), (int)(CJ1.getHeight()/1.49));
        C.setFont(police);
        C.setBackground(rouge);

        JLabel boost1 = new JLabel(": utiliser le boost");
        boost1.setSize(CJ1.getWidth(),CJ1.getHeight()/6);
        boost1.setLocation((int)(CJ1.getWidth()/2-boost1.getWidth()/3), (int)(CJ1.getHeight()/1.3));
        boost1.setFont(police);
        CJ1.add(boost1);

        JTextField V = new JTextField("V");
        V.setHorizontalAlignment(JTextField.CENTER);
        CJ1.add(V);
        V.setSize(CJ1.getWidth()/18,CJ1.getHeight()/8);
        V.setLocation((int)(CJ1.getWidth()/2-boost1.getWidth()/2.5), (int)(CJ1.getHeight()/1.25));
        V.setFont(police);
        V.setBackground(rouge);

        //Commandes J2
        JPanel CJ2 = new JPanel();
        CJ2.setLayout(null);
        monConteneur.add(CJ2);
        CJ2.setSize((int)(monConteneur.getWidth()/2.2),(int)(monConteneur.getHeight()/2));
        CJ2.setLocation((int)(monConteneur.getWidth()*3/4-CJ2.getWidth()/2),(int)(monConteneur.getHeight()/4));
        CJ2.setBackground(jaune);

        JLabel TJ2 = new JLabel();
        TJ2.setText("Commandes joueur 2");
        TJ2.setSize(getWidth()/3,getHeight()/15);
        TJ2.setLocation((int)(monConteneur.getWidth()*3/4-TJ2.getWidth()/2),(int)(monConteneur.getHeight()/7));
        TJ2.setFont(policeJ);
        TJ2.setForeground(jaune);
        monConteneur.add(TJ2);

        JLabel haut2 = new JLabel(": se déplacer vers le haut");
        haut2.setSize(CJ2.getWidth(),CJ2.getHeight()/6);
        haut2.setLocation((int)(CJ2.getWidth()/2-haut2.getWidth()/3), CJ2.getHeight()/30);
        haut2.setFont(police);
        CJ2.add(haut2);

        JTextField O = new JTextField("O");
        O.setHorizontalAlignment(JTextField.CENTER);
        CJ2.add(O);
        O.setSize(CJ2.getWidth()/18,CJ2.getHeight()/8);
        O.setLocation((int)(CJ2.getWidth()/2-haut2.getWidth()/2.5), CJ2.getHeight()/18);
        O.setFont(police);
        O.setBackground(jaune);

        JLabel bas2 = new JLabel(": se déplacer vers le bas");
        bas2.setSize(CJ2.getWidth(),CJ2.getHeight()/6);
        bas2.setLocation((int)(CJ2.getWidth()/2-bas2.getWidth()/3), (int)(CJ2.getHeight()/6));
        bas2.setFont(police);
        CJ2.add(bas2);

        JTextField L = new JTextField("L");
        L.setHorizontalAlignment(JTextField.CENTER);
        CJ2.add(L);
        L.setSize(CJ2.getWidth()/18,CJ2.getHeight()/8);
        L.setLocation((int)(CJ2.getWidth()/2-bas2.getWidth()/2.5), (int)(CJ2.getHeight()/5.2));
        L.setFont(police);
        L.setBackground(jaune);

        JLabel droite2 = new JLabel(": se déplacer vers la droite");
        droite2.setSize(CJ2.getWidth(),CJ2.getHeight()/6);
        droite2.setLocation((int)(CJ2.getWidth()/2-droite2.getWidth()/3), (int)(CJ2.getHeight()/3.3));
        droite2.setFont(police);
        CJ2.add(droite2);

        JTextField M = new JTextField("M");
        M.setHorizontalAlignment(JTextField.CENTER);
        CJ2.add(M);
        M.setSize(CJ2.getWidth()/18,CJ2.getHeight()/8);
        M.setLocation((int)(CJ2.getWidth()/2-droite2.getWidth()/2.5), (int)(CJ2.getHeight()/3.05));
        M.setFont(police);
        M.setBackground(jaune);

        JLabel gauche2 = new JLabel(": se déplacer vers la gauche");
        gauche2.setSize(CJ2.getWidth(),CJ2.getHeight()/6);
        gauche2.setLocation((int)(CJ2.getWidth()/2-gauche2.getWidth()/3), (int)(CJ2.getHeight()/2.3));
        gauche2.setFont(police);
        CJ2.add(gauche2);

        JTextField K = new JTextField("K");
        K.setHorizontalAlignment(JTextField.CENTER);
        CJ2.add(K);
        K.setSize(CJ2.getWidth()/18,CJ2.getHeight()/8);
        K.setLocation((int)(CJ2.getWidth()/2-gauche2.getWidth()/2.5), (int)(CJ2.getHeight()/2.15));
        K.setFont(police);
        K.setBackground(jaune);

        JLabel tir2 = new JLabel(": tirer un missile");
        tir2.setSize(CJ2.getWidth(),CJ2.getHeight()/6);
        tir2.setLocation((int)(CJ2.getWidth()/2-tir2.getWidth()/3), (int)(CJ2.getHeight()/1.55));
        tir2.setFont(police);
        CJ2.add(tir2);

        JTextField N = new JTextField("N");
        N.setHorizontalAlignment(JTextField.CENTER);
        CJ2.add(N);
        N.setSize(CJ2.getWidth()/18,CJ2.getHeight()/8);
        N.setLocation((int)(CJ2.getWidth()/2-tir2.getWidth()/2.5), (int)(CJ2.getHeight()/1.49));
        N.setFont(police);
        N.setBackground(jaune);

        JLabel boost2 = new JLabel(": utiliser le boost");
        boost2.setSize(CJ2.getWidth(),CJ2.getHeight()/6);
        boost2.setLocation((int)(CJ2.getWidth()/2-boost2.getWidth()/3), (int)(CJ2.getHeight()/1.3));
        boost2.setFont(police);
        CJ2.add(boost2);

        JTextField B = new JTextField("B");
        B.setHorizontalAlignment(JTextField.CENTER);
        CJ2.add(B);
        B.setSize(CJ2.getWidth()/18,CJ2.getHeight()/8);
        B.setLocation((int)(CJ2.getWidth()/2-boost2.getWidth()/2.5), (int)(CJ2.getHeight()/1.25));
        B.setFont(police);
        B.setBackground(jaune);

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

            //récupérer les touches
            keyset[0] = Integer.parseInt(Z.getText());
            keyset[1] = Integer.parseInt(S.getText());
            keyset[2] = Integer.parseInt(D.getText());
            keyset[3] = Integer.parseInt(Q.getText());
            keyset[4] = Integer.parseInt(C.getText());
            keyset[5] = Integer.parseInt(V.getText());
            keyset[6] = Integer.parseInt(O.getText());
            keyset[7] = Integer.parseInt(L.getText());
            keyset[8] = Integer.parseInt(M.getText());
            keyset[9] = Integer.parseInt(K.getText());
            keyset[10] = Integer.parseInt(N.getText());
            keyset[11] = Integer.parseInt(B.getText());
        }
    }
}
