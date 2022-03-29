import java.awt.Graphics;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class JPanelBackground extends JPanel{
    
    BufferedImage image;
    int largeurImage;
    int longeurImage;

    public JPanelBackground() {
        super() ;
        this.setLayout(null) ;
        try {
            image = ImageIO.read(new File("Images\\image-fond.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        largeurImage = image.getWidth();
        longeurImage = image.getHeight();
        this.setBounds(0,0,largeurImage,longeurImage);
    }

    public void paint(Graphics g) {
		g.drawImage(image, 0, 0, this) ;
	}
}
