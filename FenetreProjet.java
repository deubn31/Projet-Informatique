import javax.swing.*;
import java.awt.*;

public class FenetreProjet extends JFrame{
	  
	public ImageIcon icon = new ImageIcon(".\\Images\\image-fond.png");
    
    	public FenetreProjet(){
		
		// Pour ameliorer la compatibilite des affichages
		try {
		    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch(Exception e) {
		    e.printStackTrace(); 
		}

		this.setTitle("IHM Projet - Fenetre de jeu ");
		// Pour placer la fenêtre au centre de l'écran
		this.setLocationRelativeTo(null);
		// Pour empêcher le redimensionnement de la fenêtre
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1515,890);
		this.setLocation(0,0);
		
		JTextArea text = new JTextArea() 
		{
		  Image img = icon.getImage();
		  // initialiseur d'instance
		  {setOpaque(false);}
		  public void paintComponent(Graphics graphics) 
		  {
			graphics.drawImage(img, 0, 0, this);
			super.paintComponent(graphics);
		  }
		};
		JScrollPane pane = new JScrollPane(text);
		Container content = this.getContentPane();
		content.add(pane, BorderLayout.CENTER);
		
		this.setVisible(true);
	  }
}
