import javax.swing.ImageIcon ;
import javax.swing.JLabel ;

public class missile extends JLabel{
    double masse = 30  ; 
    int [] position  = new int [2] ; 
    ImageIcon skin; 
    int orientation ;// 0 pour gauche vers droite , 1 pour l'inverse
    double v0x = 500 ; 
    double v0y = 700 ; 
    double[] vitesse = {v0x,-v0y};
    double[] acceleration = {0,0};
    double cstePesenteur = 300 ;
    double csteFrottementX = 12 ;
	double csteFrottementY = 5 ;



    long tempsPrecedent = System.currentTimeMillis()  ; 
    long deltaT ; 
    
    public missile (ImageIcon image,int x, int y) {
        super(image);
        skin = image;
        position[0]=x;
        position[1]=y;
        this.setBounds(position[0] , position[1], skin.getIconWidth(), skin.getIconHeight());
    }
    public void updatePos(int x, int y){
        position[0]=x;
        position[1]=y;
        this.setLocation(position[0], position[1]);
    }
    public boolean estPresent( int largeurFenetre, int hauteurFenetre ){
            return (this.isVisible() == false  || this.position[0] > largeurFenetre
			|| this.position[0] < 0  || this.position[1] > hauteurFenetre || this.position[1] < 0) ; 
    }

    public int[] deplacements(){
        deltaT = System.currentTimeMillis() - tempsPrecedent;
		tempsPrecedent = System.currentTimeMillis();
        this.acceleration[0] =  ( - csteFrottementX*this.vitesse[0]) /this.masse ; 
		this.acceleration[1] =  ( this.masse*cstePesenteur - csteFrottementY*this.vitesse[1]) / this.masse;

		this.vitesse[0] = this.vitesse[0] + this.acceleration[0] * deltaT*0.001; //*0.001 car deltaT est en milliseconde
		this.vitesse[1] = this.vitesse[1] + this.acceleration[1] * deltaT*0.001;
        //System.out.println(this.vitesse[0]+ " " + this.vitesse[1]) ; 

		this.position[0] = this.position[0] + (int)(this.vitesse[0] * deltaT*0.001);
		this.position[1] = this.position[1] + (int)(this.vitesse[1] * deltaT*0.001);

        return this.position ; 
    }

    public void setInit (double vx , double vy) {
        vitesse[0] = vx;
        if (vy<0){
            vitesse[1] = 0 ; 
        }else {
        vitesse[1] = -vy;
        }
        acceleration[0] = 0 ; acceleration[1] = 0 ; 
    }

}   

