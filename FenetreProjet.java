import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class FenetreProjet extends JFrame {

	BufferedImage image;

	public FenetreProjet() throws IOException {

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
		this.setResizable(false);
		// this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(0, 0);

		/*
		Toolkit T = Toolkit.getDefaultToolkit();
		img = T.getImage("Images/image-fond.png");
		reader.setInput
		int x = img.getWidth(this);
		*/

        JPanel pane = new JPanel();
		image = ImageIO.read(new File("Images\\image-fond.png"));
		int x = image.getWidth();
		int y = image.getHeight();

		this.setSize(x, y);
		this.setVisible(true);
	}

	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, this);
	}

}
