import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javafx.event.ActionEvent;
import java.awt.*;
import java.awt.event.*;

public class FenetreCommandes extends JFrame implements ActionListener{

    private Color gris = new Color(169,169,169);
    private Font policeRetour = new FontUIResource("Verdana", Font.BOLD, 30);

    JButton retour;

    public FenetreCommandes(){

        this.setTitle("Menu retour");
        this.setSize(1950, 1080);


        JPanel monConteneur = new JPanel();
        monConteneur.setLayout(null);
        monConteneur.setBounds(10,10,160,40);

        JLabel avancer = new JLabel("Z : se déplacer vers le haut");
        avancer.setBounds(10, 10, 100, 100);
        monConteneur.add(avancer);

        //bouton retour au menu
        retour = new JButton ("Retour");
        retour.setLayout(null);
        retour.setBounds(1550,850,300,100);
        retour.setBackground(gris);
        retour.setFont(policeRetour);
        retour.addActionListener(this);
        monConteneur.add(retour);

        this.add(monConteneur);
        this.setVisible(false);
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
        if(e.getSource()==retour){
            this.setVisible(false);
        }
    }
}
