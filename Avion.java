import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

//import org.omg.PortableServer.ThreadPolicyOperations;

public class Avion extends JLabel{
    int vie = 3;
    int pas = 10000;

    int[] keySet;

    int tempsBoost;
    Timer timerBoost;

    int boost = 2; // 2 si disponible ; 1 si en cours d'utilisation ; 0 si en rechargement
    int pasSansBoost = pas;
    int pasAvecBoost = 2*pasSansBoost;
    int dureeBoost = 4;  //durée en seconde 
    int cooldownBoost = 6; //cooldown en seconde 

    int masse = 10; //masse en tonnes (peut varier en fonction de l'avion)

    // Vecteurs de l'avion
    
    double[] vitesse = {0,0};
    double[] acceleration = {0,0};
    double[] position = {0, 0};
    int[] ForceDeplacement = {0,0};

    boolean directionDroite; //directionDroite vaut true si l'avion se dirige vers la droite et false si l'avion se dirige vers la gauche

    boolean missileCharge = true;
    boolean immortel = false;

    ImageIcon skinAvionDroite;
	ImageIcon skinAvionGauche;
    ImageIcon skinAvionDroiteBoost;
    ImageIcon skinAvionGaucheBoost;
    ImageIcon skin; //skin utilisé actuelement

    ImageIcon missile; 
    
    int largueurAvion;
    int longeurAvion;

    ImageIcon troisPointsDeVie;
	ImageIcon deuxPointsDeVie;
	ImageIcon unPointDeVie;
	ImageIcon zeroPointDeVie;

    

    long tempsPrecedent;
	long deltaT;

    double cstePesenteur = 200;
	double csteFrottementX = 8;
	double csteFrottementY = 5;

    public Avion (ImageIcon skinDroite, ImageIcon skinGauche, ImageIcon skinDroiteBoost, ImageIcon skinGaucheBoost, int[] touches, int x, int y) {
        super(skinDroite);

        troisPointsDeVie = new ImageIcon("Images/3viesmodif.png");
		deuxPointsDeVie = new ImageIcon("Images/2viesModif.png");
		unPointDeVie = new ImageIcon("Images/1vieModif.png");
		zeroPointDeVie = new ImageIcon("Images/0vieModif.png");

        
        skinAvionDroite = skinDroite;
        skinAvionGauche = skinGauche;
        skinAvionDroiteBoost = skinDroiteBoost;
        skinAvionGaucheBoost = skinGaucheBoost;
        skin = skinAvionDroite;  //Skin de droite par défault => modifier l'orientation de l'avion avec : setDirection si celui de Gauche est souhaité

        keySet = touches;

        pas = pasSansBoost;

        position[0] = x;
        position[1] = y;

        this.setBounds((int)position[0], (int)position[1], skin.getIconWidth(), skin.getIconHeight());
        missile = new ImageIcon("Images/missiles.jpg");

        longeurAvion = skin.getIconHeight();
        largueurAvion = skin.getIconWidth();
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

    public void setDirection(boolean direction){ //direction vaut true si l'avion de déplace à droite en false s'il se déplace à gauche
        directionDroite = direction;
        if (direction == true){
            if (boost == 1) {
                this.setIcon(skinAvionDroiteBoost);
                skin = skinAvionDroiteBoost;
                this.setSize(skin.getIconWidth(), skin.getIconHeight());
            } else {
                this.setIcon(skinAvionDroite);
                skin = skinAvionDroite;
                this.setSize( skin.getIconWidth(), skin.getIconHeight());
            }
        } else if (direction == false){
            if (boost == 1) {
                this.setIcon(skinAvionGaucheBoost);
                skin = skinAvionGaucheBoost;
                this.setSize( skin.getIconWidth(), skin.getIconHeight());
            } else {
                this.setIcon(skinAvionGauche);
                skin = skinAvionGauche;
                this.setSize( skin.getIconWidth(), skin.getIconHeight());
            }
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

    public void boost(JLabel labelBoost){
        tempsBoost = 0;
        this.boost = 1;
        labelBoost.setIcon(null);
        labelBoost.setText(String.valueOf(dureeBoost-tempsBoost));
        this.pas = pasAvecBoost;
        this.setDirection(directionDroite);
        timerBoost = new Timer(1000, new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e) {
                tempsBoost++;
                if ((tempsBoost >= dureeBoost) && (tempsBoost < dureeBoost + cooldownBoost)){
                    boost = 0;
                    labelBoost.setText(null);
                    labelBoost.setIcon(FenetreProjet.imageBoostUtilisee);
                    pas = pasSansBoost;
                    setDirection(directionDroite);
                } else if (tempsBoost == dureeBoost + cooldownBoost){
                    boost = 2;
                    labelBoost.setIcon(FenetreProjet.imageBoost);
                    timerBoost.stop();
                } else {
                    labelBoost.setText(String.valueOf(dureeBoost-tempsBoost));
                }
            }
        });
        timerBoost.start();
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
				this.setDirection(true); // true = va vers la droite
				ForceDeplacement[0] = this.pas;
			}
		}
		if (evenementClavier.contains(keySet[1])) {
			if (evenementClavier.contains(keySet[3])) {
				ForceDeplacement[0] = 0;
			} else {
				this.setDirection(false); // false = va vers la gauche
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
		this.acceleration[0] = (ForceDeplacement[0] - csteFrottementX*this.vitesse[0]) / this.masse;
		this.acceleration[1] = (ForceDeplacement[1] + this.masse*cstePesenteur - csteFrottementY*this.vitesse[1]) / this.masse;

        //System.out.println("accélation selon x = " + acceleration[0] + " accélération selon y = " + acceleration[1]);

		this.vitesse[0] = this.vitesse[0] + this.acceleration[0] * deltaT*0.001; //*0.001 car deltaT est en milliseconde
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

    public void collision (missile missileJoueur){
        if (missileJoueur.position [0] >this.position[0] &&  missileJoueur.position[0] < this.position[0] + this.skin.getIconWidth()
		&& missileJoueur.position[1]> this.position[1] &&  missileJoueur.position[1]< this.position[1] +this.skin.getIconHeight() &&
		missileJoueur.isVisible() == true ){
			this.vie -- ;  

			//AvionJ2.invincible();

			missileJoueur.setVisible(false);  
        }
    }

    public void updatePointsDeVie(int joueur , JLabel vies, int largeurFenetre ){
        if (this.vie == 2) {
            vies.setIcon(deuxPointsDeVie);
        }else if (this.vie == 1) {
            vies.setIcon(unPointDeVie);
        }else if (this.vie == 0) {
            vies.setIcon(zeroPointDeVie);
        }
    }



   
}
