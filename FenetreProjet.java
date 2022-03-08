import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;

public class FenetreProjet extends JFrame {

	Image img;

	public FenetreProjet() {

		// Pour ameliorer la compatibilite des affichages
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.setTitle("IHM Projet - Fenetre de jeu ");
		// Pour placer la fenêtre au centre de l'écran
		this.setLocationRelativeTo(null);
		// Pour empêcher le redimensionnement de la fenêtre
		// this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1515, 890);
		this.setLocation(0, 0);

		Toolkit T = Toolkit.getDefaultToolkit();
		img = T.getImage("Images/image-fond.png");

		this.setVisible(true);
	}

	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}

}
