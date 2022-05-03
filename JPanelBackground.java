import java.awt.Graphics;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.awt.Toolkit;

public class JPanelBackground extends JPanel{
    
    private BufferedImage image;
    public int largeurImage;
    public int longeurImage;

    public JPanelBackground() {
        super() ;
        this.setLayout(null) ;
        //Importation de l'image de fond
        Path CHEMIN_IMAGES = Paths.get( "Images");
        String chemin = CHEMIN_IMAGES.resolve("image-fond.png").toString();
        try {
            image = ImageIO.read(new File(chemin));
        } catch (IOException e) {
            e.printStackTrace();
        }
        largeurImage = image.getHeight();
        longeurImage = image.getWidth();
        this.setBounds(0,0,longeurImage,largeurImage);
    }

    public void paint(Graphics g) {
        Toolkit.getDefaultToolkit().sync(); //Amélioration de l'affichage en linux
		g.drawImage(image, 0, 0, this) ;
	}
}
