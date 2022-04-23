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
    JTextField Q;
    JTextField S;
    JTextField D;
    JTextField C;
    JTextField V;

    JTextField O;
    JTextField K;
    JTextField L;
    JTextField M;
    JTextField N;
    JTextField B;

    int[] keysetJ1 = {90,81,83,68,67,86};
    int[] keysetJ2 = {79,75,76,77,44,78};

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


        Z = new JTextField("Z");
        Z.setHorizontalAlignment(JTextField.CENTER);
        CJ1.add(Z);
        Z.setSize(CJ1.getWidth()/18,CJ1.getHeight()/8);
        Z.setLocation((int)(CJ1.getWidth()/2-haut1.getWidth()/2.5), CJ1.getHeight()/18);
        Z.setFont(police);
        Z.setBackground(rouge);
        Z.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
                if (Z.getText().length() >= 1 ) // limit textfield to 1 characters
                    e.consume(); 
            }  
        });

        JLabel bas1 = new JLabel(": se déplacer vers le bas");
        bas1.setSize(CJ1.getWidth(),CJ1.getHeight()/6);
        bas1.setLocation((int)(CJ1.getWidth()/2-bas1.getWidth()/3), (int)(CJ1.getHeight()/6));
        bas1.setFont(police);
        CJ1.add(bas1);

        S = new JTextField("S");
        S.setHorizontalAlignment(JTextField.CENTER);
        CJ1.add(S);
        S.setSize(CJ1.getWidth()/18,CJ1.getHeight()/8);
        S.setLocation((int)(CJ1.getWidth()/2-bas1.getWidth()/2.5), (int)(CJ1.getHeight()/5.2));
        S.setFont(police);
        S.setBackground(rouge);
        S.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
                if (S.getText().length() >= 1 ) // limit textfield to 1 characters
                    e.consume(); 
            }  
        });

        JLabel droite1 = new JLabel(": se déplacer vers la droite");
        droite1.setSize(CJ1.getWidth(),CJ1.getHeight()/6);
        droite1.setLocation((int)(CJ1.getWidth()/2-droite1.getWidth()/3), (int)(CJ1.getHeight()/3.3));
        droite1.setFont(police);
        CJ1.add(droite1);

        D = new JTextField("D");
        D.setHorizontalAlignment(JTextField.CENTER);
        CJ1.add(D);
        D.setSize(CJ1.getWidth()/18,CJ1.getHeight()/8);
        D.setLocation((int)(CJ1.getWidth()/2-droite1.getWidth()/2.5), (int)(CJ1.getHeight()/3.05));
        D.setFont(police);
        D.setBackground(rouge);
        D.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
                if (D.getText().length() >= 1 ) // limit textfield to 1 characters
                    e.consume(); 
            }  
        });

        JLabel gauche1 = new JLabel(": se déplacer vers la gauche");
        gauche1.setSize(CJ1.getWidth(),CJ1.getHeight()/6);
        gauche1.setLocation((int)(CJ1.getWidth()/2-gauche1.getWidth()/3), (int)(CJ1.getHeight()/2.3));
        gauche1.setFont(police);
        CJ1.add(gauche1);

        Q = new JTextField("Q");
        Q.setHorizontalAlignment(JTextField.CENTER);
        CJ1.add(Q);
        Q.setSize(CJ1.getWidth()/18,CJ1.getHeight()/8);
        Q.setLocation((int)(CJ1.getWidth()/2-gauche1.getWidth()/2.5), (int)(CJ1.getHeight()/2.15));
        Q.setFont(police);
        Q.setBackground(rouge);
        Q.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
                if (Q.getText().length() >= 1 ) // limit textfield to 1 characters
                    e.consume(); 
            }  
        });

        JLabel tir1 = new JLabel(": tirer un missile");
        tir1.setSize(CJ1.getWidth(),CJ1.getHeight()/6);
        tir1.setLocation((int)(CJ1.getWidth()/2-tir1.getWidth()/3), (int)(CJ1.getHeight()/1.55));
        tir1.setFont(police);
        CJ1.add(tir1);

        C = new JTextField("C");
        C.setHorizontalAlignment(JTextField.CENTER);
        CJ1.add(C);
        C.setSize(CJ1.getWidth()/18,CJ1.getHeight()/8);
        C.setLocation((int)(CJ1.getWidth()/2-tir1.getWidth()/2.5), (int)(CJ1.getHeight()/1.49));
        C.setFont(police);
        C.setBackground(rouge);
        C.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
                if (C.getText().length() >= 1 ) // limit textfield to 1 characters
                    e.consume(); 
            }  
        });

        JLabel boost1 = new JLabel(": utiliser le boost");
        boost1.setSize(CJ1.getWidth(),CJ1.getHeight()/6);
        boost1.setLocation((int)(CJ1.getWidth()/2-boost1.getWidth()/3), (int)(CJ1.getHeight()/1.3));
        boost1.setFont(police);
        CJ1.add(boost1);

        V = new JTextField("V");
        V.setHorizontalAlignment(JTextField.CENTER);
        CJ1.add(V);
        V.setSize(CJ1.getWidth()/18,CJ1.getHeight()/8);
        V.setLocation((int)(CJ1.getWidth()/2-boost1.getWidth()/2.5), (int)(CJ1.getHeight()/1.25));
        V.setFont(police);
        V.setBackground(rouge);
        V.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
                if (V.getText().length() >= 1 ) // limit textfield to 1 characters
                    e.consume(); 
            }  
        });

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

        O = new JTextField("O");
        O.setHorizontalAlignment(JTextField.CENTER);
        CJ2.add(O);
        O.setSize(CJ2.getWidth()/18,CJ2.getHeight()/8);
        O.setLocation((int)(CJ2.getWidth()/2-haut2.getWidth()/2.5), CJ2.getHeight()/18);
        O.setFont(police);
        O.setBackground(jaune);
        O.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
                if (O.getText().length() >= 1 ) // limit textfield to 1 characters
                    e.consume(); 
            }  
        });

        JLabel bas2 = new JLabel(": se déplacer vers le bas");
        bas2.setSize(CJ2.getWidth(),CJ2.getHeight()/6);
        bas2.setLocation((int)(CJ2.getWidth()/2-bas2.getWidth()/3), (int)(CJ2.getHeight()/6));
        bas2.setFont(police);
        CJ2.add(bas2);

        L = new JTextField("L");
        L.setHorizontalAlignment(JTextField.CENTER);
        CJ2.add(L);
        L.setSize(CJ2.getWidth()/18,CJ2.getHeight()/8);
        L.setLocation((int)(CJ2.getWidth()/2-bas2.getWidth()/2.5), (int)(CJ2.getHeight()/5.2));
        L.setFont(police);
        L.setBackground(jaune);
        L.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
                if (L.getText().length() >= 1 ) // limit textfield to 1 characters
                    e.consume(); 
            }  
        });

        JLabel droite2 = new JLabel(": se déplacer vers la droite");
        droite2.setSize(CJ2.getWidth(),CJ2.getHeight()/6);
        droite2.setLocation((int)(CJ2.getWidth()/2-droite2.getWidth()/3), (int)(CJ2.getHeight()/3.3));
        droite2.setFont(police);
        CJ2.add(droite2);

        M = new JTextField("M");
        M.setHorizontalAlignment(JTextField.CENTER);
        CJ2.add(M);
        M.setSize(CJ2.getWidth()/18,CJ2.getHeight()/8);
        M.setLocation((int)(CJ2.getWidth()/2-droite2.getWidth()/2.5), (int)(CJ2.getHeight()/3.05));
        M.setFont(police);
        M.setBackground(jaune);
        M.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
                if (M.getText().length() >= 1 ) // limit textfield to 1 characters
                    e.consume(); 
            }  
        });

        JLabel gauche2 = new JLabel(": se déplacer vers la gauche");
        gauche2.setSize(CJ2.getWidth(),CJ2.getHeight()/6);
        gauche2.setLocation((int)(CJ2.getWidth()/2-gauche2.getWidth()/3), (int)(CJ2.getHeight()/2.3));
        gauche2.setFont(police);
        CJ2.add(gauche2);

        K = new JTextField("K");
        K.setHorizontalAlignment(JTextField.CENTER);
        CJ2.add(K);
        K.setSize(CJ2.getWidth()/18,CJ2.getHeight()/8);
        K.setLocation((int)(CJ2.getWidth()/2-gauche2.getWidth()/2.5), (int)(CJ2.getHeight()/2.15));
        K.setFont(police);
        K.setBackground(jaune);
        K.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
                if (K.getText().length() >= 1 ) // limit textfield to 1 characters
                    e.consume(); 
            }  
        });

        JLabel tir2 = new JLabel(": tirer un missile");
        tir2.setSize(CJ2.getWidth(),CJ2.getHeight()/6);
        tir2.setLocation((int)(CJ2.getWidth()/2-tir2.getWidth()/3), (int)(CJ2.getHeight()/1.55));
        tir2.setFont(police);
        CJ2.add(tir2);

        N = new JTextField(",");
        N.setHorizontalAlignment(JTextField.CENTER);
        CJ2.add(N);
        N.setSize(CJ2.getWidth()/18,CJ2.getHeight()/8);
        N.setLocation((int)(CJ2.getWidth()/2-tir2.getWidth()/2.5), (int)(CJ2.getHeight()/1.49));
        N.setFont(police);
        N.setBackground(jaune);
        N.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
                if (N.getText().length() >= 1 ) // limit textfield to 1 characters
                    e.consume(); 
            }  
        });

        JLabel boost2 = new JLabel(": utiliser le boost");
        boost2.setSize(CJ2.getWidth(),CJ2.getHeight()/6);
        boost2.setLocation((int)(CJ2.getWidth()/2-boost2.getWidth()/3), (int)(CJ2.getHeight()/1.3));
        boost2.setFont(police);
        CJ2.add(boost2);

        B = new JTextField("N");
        B.setHorizontalAlignment(JTextField.CENTER);
        CJ2.add(B);
        B.setSize(CJ2.getWidth()/18,CJ2.getHeight()/8);
        B.setLocation((int)(CJ2.getWidth()/2-boost2.getWidth()/2.5), (int)(CJ2.getHeight()/1.25));
        B.setFont(police);
        B.setBackground(jaune);
        B.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
                if (B.getText().length() >= 1 ) // limit textfield to 1 characters
                    e.consume(); 
            }  
        });

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
            //J1
            keysetJ1[0] = KeyStroke.getKeyStroke(Character.toUpperCase(Z.getText().charAt(0)), 0).getKeyCode();
            keysetJ1[1] = KeyStroke.getKeyStroke(Character.toUpperCase(Q.getText().charAt(0)), 0).getKeyCode();
            keysetJ1[2] = KeyStroke.getKeyStroke(Character.toUpperCase(S.getText().charAt(0)), 0).getKeyCode();
            keysetJ1[3] = KeyStroke.getKeyStroke(Character.toUpperCase(D.getText().charAt(0)), 0).getKeyCode();
            keysetJ1[4] = KeyStroke.getKeyStroke(Character.toUpperCase(C.getText().charAt(0)), 0).getKeyCode();
            keysetJ1[5] = KeyStroke.getKeyStroke(Character.toUpperCase(V.getText().charAt(0)), 0).getKeyCode();

            //J2
            keysetJ2[0] = KeyStroke.getKeyStroke(Character.toUpperCase(O.getText().charAt(0)), 0).getKeyCode();
            keysetJ2[1] = KeyStroke.getKeyStroke(Character.toUpperCase(K.getText().charAt(0)), 0).getKeyCode();
            keysetJ2[2] = KeyStroke.getKeyStroke(Character.toUpperCase(L.getText().charAt(0)), 0).getKeyCode();
            keysetJ2[3] = KeyStroke.getKeyStroke(Character.toUpperCase(M.getText().charAt(0)), 0).getKeyCode();
            keysetJ2[4] = KeyStroke.getKeyStroke(Character.toUpperCase(N.getText().charAt(0)), 0).getKeyCode();
            keysetJ2[5] = KeyStroke.getKeyStroke(Character.toUpperCase(B.getText().charAt(0)), 0).getKeyCode();
        }
    }
}
