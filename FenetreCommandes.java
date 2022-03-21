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

        //bouton retour au menu
        retour = new JButton ("Retour");
        retour.setLayout(null);
        retour.setBounds(1550,850,300,100);
        retour.setBackground(gris);
        retour.setFont(policeRetour);
        retour.addActionListener(this);






        this.setVisible(false);
    }

    public void actionPerformed (ActionEvent e) {
        if(e.getSource()==retour){
            this.setVisible(false);
        }
    }
}
