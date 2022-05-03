package Code;

import javax.swing.ImageIcon ;
import javax.swing.JLabel ;

public class missile extends JLabel{
    private double masse = 30  ; 

    private ImageIcon skin; 
    public int orientation ;// 0 pour gauche vers droite , 1 pour l'inverse

    //Vecteur du missile
    public double v0x = 500 ; 
    public double v0y = 700 ; 
    private double[] vitesse = {v0x,-v0y};
    private double[] acceleration = {0,0};
    public int [] position  = new int [2] ; 

    //Cstes Physiques
    private final double cstePesenteur = 300 ;
    private final double csteFrottementX = 12 ;
	private final double csteFrottementY = 5 ;

    private long tempsPrecedent = System.currentTimeMillis()  ; //Temps du système lors de l'exécution précédente de la méthode "deplacements"
    private long deltaT ; //Différence de temps du système entre l'exécution de la méthode "deplacements" précédente et l'actuelle
    
    public missile (ImageIcon image,int x, int y) {
        super(image);
        skin = image;
        position[0]=x;
        position[1]=y;
        this.setBounds(position[0] , position[1], skin.getIconWidth(), skin.getIconHeight());
    }

    public void updatePos(int x, int y){ //Permet de mettre à jour la position du missile
        position[0]=x;
        position[1]=y;
        this.setLocation(position[0], position[1]);
    }

    public boolean estPresent( int largeurFenetre, int hauteurFenetre ){ //Permet de vérifier si le missile est toujours dans la fenêtre
            return (this.isVisible() == false  || this.position[0] > largeurFenetre
			|| this.position[0] < 0  || this.position[1] > hauteurFenetre || this.position[1] < 0) ; 
    }

    public int[] deplacements(){ //Permet de simuler la physique des missiles et d'ainsi renvoyez la position du missile
        deltaT = System.currentTimeMillis() - tempsPrecedent;
		tempsPrecedent = System.currentTimeMillis();
        this.acceleration[0] =  ( - csteFrottementX*this.vitesse[0]) /this.masse ; 
		this.acceleration[1] =  ( this.masse*cstePesenteur - csteFrottementY*this.vitesse[1]) / this.masse;

		this.vitesse[0] = this.vitesse[0] + this.acceleration[0] * deltaT*0.001; //*0.001 car deltaT est en milliseconde
		this.vitesse[1] = this.vitesse[1] + this.acceleration[1] * deltaT*0.001;

		this.position[0] = this.position[0] + (int)(this.vitesse[0] * deltaT*0.001);
		this.position[1] = this.position[1] + (int)(this.vitesse[1] * deltaT*0.001);

        return this.position ; 
    }

    public void setInit (double vx , double vy) { //Permet d'initialiser la vitesse initiale du missile  calculé en fonction du temps d'appui sur la touche tire
        if (vy<0){
            vitesse[1] = 0 ; 
        }else {
        vitesse[1] = -vy;
        }
        vitesse[0] = vx;
        acceleration[0] = 0 ; 
        acceleration[1] = 0 ; 
    }

}   

