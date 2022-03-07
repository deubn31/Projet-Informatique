import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.event.*;
import java.util.LinkedList;
import javax.swing.UIManager;

public class FenetreProjet extends JFrame{
	
	public FenetreProjet(){
		
		// Pour ameliorer la compatibilite des affichages
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch(Exception e) {
            e.printStackTrace(); 
        }
        
		this.setTitle("IHM Projet - Fenetre de jeu ");
		this.setLayout(null);
		this.setSize(800,800);
		// Pour placer la fenêtre au centre de l'écran
		this.setLocationRelativeTo(null);
		// Pour empêcher le redimensionnement de la fenêtre
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		Icon iconFond = new ImageIcon("image-fond.png");
		JLabel imageFond = new JLabel();
		imageFond.setIcon(iconFond);
		//imageFond.setIcon (new ImageIcon("./Images/image-fond.png"));
		//imageFond.setText("Test ldld");
		//imageFond.setBounds(0,0,800,800);
		this.add(imageFond);
		this.setVisible(true);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	
		// Les Widgets à déclarer en dehors du constructeur
	private JTextField textChoix;
	private JTextField affResult;
	private JButton monBoutonAfficher;
    private JButton monBoutonEffacer;
    
	private LinkedList<Courbe> maListe;
	
	private FenetrePlotCourbe maFenetrePlot; //on ajoute l'attribut laFenetrePLot _>objectif 3
		
	public FenetrePlotCourbe(LinkedList<Courbe> maListeCourbe){
        
        // Pour ameliorer la compatibilite des affichages
        try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }catch(Exception e){
            e.printStackTrace(); 
        }
	
		maListe = maListeCourbe;
		this.setTitle("IHM Courbe - Graphisme ");
		this.setLayout(null);
		this.setSize(600,600);
		// Pour placer la fenêtre au centre de l'écran
		//~ this.setLocationRelativeTo(null);
		this.setLocation(700,200);
		// Pour empêcher le redimensionnement de la fenêtre
		this.setResizable(false);
		// Pour cacher la fênetre à sa création
		this.setVisible(false);
				
	}
	
	
	
	
	
	
	
	
	// Les Widgets à déclarer en dehors du constructeur
	private LinkedList<Courbe> maListe;
		
	public FenetrePlotCourbe(LinkedList<Courbe> maListeCourbe){
		maListe = maListeCourbe;
		this.setTitle("IHM Courbe - Graphisme ");
		this.setLayout(null);
		this.setSize(600,600);
		this.setLocation(700,200);
		// Pour empêcher le redimensionnement de la fenêtre
		this.setResizable(false);
		// Pour cacher la fenêtre à sa création
		this.setVisible(false);
		
	}
	
	/**
	 * Pour faire des dessins simples
	 
	public void paint(Graphics g){
		g.setColor(Color.orange);
		g.fillRect(0,0,this.getWidth(),this.getHeight());
		for (int i = 0;i<maListe.size();i++){
			maListe.get(i).dessine(g);
		}
	}
	
	*/
}
