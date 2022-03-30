import java.util.HashSet;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Avion extends JLabel{
    int vie = 3;
    int pas = 100;

    int[] keySet;

    boolean boost = false;
    int pasSansBoost = 100;
    int pasAvecBoost = 200;
    int dureeBoost = 4000;  //durée en milliseconde 
    int cooldownBoost = 10000; //cooldown en milliseconde 

    int masse = 10; //masse en tonne

    // Vecteurs de l'avion
    
    double[] vitesse = {0,0};
    double[] acceleration = {0,0};
    double[] position = {0, 0};
    int[] ForceDeplacement = {0,0};

    boolean directionDroite; //la directionDroite vaut true si l'avion se dirige vers la droite et false si l'avion se dirige vers la gauche

    boolean missileCharge = true;
    boolean immortel = false;

    ImageIcon skinAvionDroite;
	ImageIcon skinAvionGauche;
    ImageIcon skin; //skin utilisé actuelement

    ImageIcon missile; 
    
    int largueurAvion;
    int longeurAvion;

    long tempsPrecedent = System.currentTimeMillis() + 2000; //On ajoute 2000 car le timer commence avec un délais de 2000
	long deltaT;

    double cstePesenteur = 2;
	double csteFrottementX = 0.2;
	double csteFrottementY = 0.1;

    public Avion (ImageIcon skinDroite, ImageIcon skinGauche, int[] touches, int x, int y) {
        super(skinDroite);
        
        skinAvionDroite = skinDroite;
        skinAvionGauche = skinGauche;
        skin = skinAvionDroite;  //Le skin droite est mis par défault

        keySet = touches;

        pas = pasSansBoost;

        position[0] = x;
        position[1] = y;

        this.setBounds((int)position[0], (int)position[1], skin.getIconWidth(), skin.getIconHeight());
        missile = new ImageIcon("Images/missiles.jpg");

        longeurAvion = missile.getIconHeight();
        largueurAvion = missile.getIconWidth();
    }

    public Avion(ImageIcon image){
        super(image);
        skin = image;
        this.setBounds((int)position[0], (int)position[1], skin.getIconWidth(), skin.getIconHeight());
        missile = new ImageIcon("Images/missiles.jpg");
    }

    public void Touchee(){
        if (this.immortel == false){
            vie = vie-1;
            this.immortel = true;
        }
    }
    
    public void Recharge(){
        if (missileCharge == false){
            missileCharge = true;
        }
    }

    public void Tire(){
        missileCharge = false;
        //  missile a = new missile (posX , posY) ; 
        // a.updatePos(a.PosX + 2 * pas , a.PosY);
    }

    public void updatePos(int x, int y){
        this.position[0]=x;
        this.position[1]=y;
        this.setLocation((int)position[0], (int)position[1]);
    }

    public void setDirection(String direction){
        if (direction == "droite"){
            directionDroite = true;
            this.setIcon(skinAvionDroite);
            skin = skinAvionDroite;
        } else if (direction == "gauche"){
            directionDroite = false;
            this.setIcon(skinAvionGauche);
            skin = skinAvionGauche;
        }
    }

    public void invincible(){
        if (this.immortel == true){
            long compteur = System.currentTimeMillis();
            int a = this.vie; 
            if (System.currentTimeMillis()- compteur < 100){
                this.vie = a; 
            }
        }
        immortel = false; 
    }

    public void startBoost(){
        this.boost = true;
        this.pas = pasAvecBoost;
    }

    public void stopBoost(){
        this.boost = false;
        this.pas = pasSansBoost;
    }

    public double[] deplacements(HashSet<Integer> evenementClavier, int largeurFenetre, int hauteurFenetre){

        //Réinitialisation des forces //
		ForceDeplacement[0] = 0;
		ForceDeplacement[1] = 0;

		//Déplacements//

		if (evenementClavier.contains(keySet[3])) {
			if (evenementClavier.contains(keySet[1])) {
				ForceDeplacement[0] = 0;
			} else {
				this.setDirection("droite");
				ForceDeplacement[0] = this.pas;
			}
		}
		if (evenementClavier.contains(keySet[1])) {
			if (evenementClavier.contains(keySet[3])) {
				ForceDeplacement[0] = 0;
			} else {
				this.setDirection("gauche");
				ForceDeplacement[0] = -this.pas; 
			}
		} 
		if (evenementClavier.contains(keySet[2])) {
			if (evenementClavier.contains(keySet[0])) {
				ForceDeplacement[1] = 0;
			} else {
				ForceDeplacement[1] = this.pas;
			}
		} 
		if (evenementClavier.contains(keySet[0])) {
			if (evenementClavier.contains(keySet[2])) {
				ForceDeplacement[1] = 0;
			}else{
				ForceDeplacement[1] = -this.pas;
			}
		}

        //---------- Gestion de la physique de l'avion -----------//
		
		deltaT = System.currentTimeMillis() - tempsPrecedent;
		tempsPrecedent = System.currentTimeMillis();

		//PFD du J1//
		this.acceleration[0] = this.masse * (ForceDeplacement[0] - csteFrottementX*this.vitesse[0]);
		this.acceleration[1] = this.masse * (ForceDeplacement[1] + this.masse*cstePesenteur - csteFrottementY*this.vitesse[1]);

		this.vitesse[0] = this.vitesse[0] + this.acceleration[0] * deltaT*0.001;
		this.vitesse[1] = this.vitesse[1] + this.acceleration[1] * deltaT*0.001;

		this.position[0] = this.position[0] + this.vitesse[0] * deltaT*0.001;
		this.position[1] = this.position[1] + this.vitesse[1] * deltaT*0.001;

		if (this.position[0] > largeurFenetre-160){
			this.vitesse[0] = 0.0;
			this.position[0] = largeurFenetre-160;
		}

		if (this.position[0] < 0){
			this.vitesse[0] = 0.0;
			this.position[0] = 0.0;
		}

		if (this.position[1] > hauteurFenetre-90){
			this.vitesse[1] = 0.0;
			this.position[1] = hauteurFenetre-90;
		}

		if (this.position[1] < 0){
			this.vitesse[1] = 0.0;
			this.position[1] = 0.0;
		}

        return this.position;
    }
}
