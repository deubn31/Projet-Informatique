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

    Timer invincible ; 
    int tempsInv ;

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
    int[] forceDeplacement = {0,0};

    boolean directionDroite; //directionDroite vaut true si l'avion se dirige vers la droite et false si l'avion se dirige vers la gauche

    boolean missileCharge = true;
    boolean immortel = false;

    ImageIcon skinAvionDroite, skinAvionGauche, skinAvionDroiteBoost, skinAvionGaucheBoost, skin; //skin --> utilisé actuelement
    
    int largueurAvion;
    int longeurAvion;

    ImageIcon troisPointsDeVie, deuxPointsDeVie, unPointDeVie, zeroPointDeVie;

    long tempsPrecedent;
	long deltaT;
    long tempsInvincible ; 

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

        longeurAvion = skin.getIconHeight();
        largueurAvion = skin.getIconWidth();
    }

    public void Touche(){
        if (this.immortel == false){
            vie = vie-1;
            this.setinvincible();
        }
    }
    
    public void Recharge(){
        if (missileCharge == false){
            missileCharge = true;
        }
    }

    public void Tire(missile missileJoueur, int largeurFenetre , int longueurFenetre, int tempsVitesse, ImageIcon skinMissileDroite , ImageIcon skinMissileGauche){
        if (missileJoueur.estPresent(largeurFenetre , longueurFenetre)){ // s'il est présent
				missileJoueur.updatePos((int)this.position[0] + 60, (int)this.position[1] + 50); // Sa position est sur l'avion
				// Update du skin en fonction de la direction de l'avion  
				if (this.directionDroite == true){
					missileJoueur.orientation = 0 ; 
					missileJoueur.setIcon(skinMissileDroite);
					missileJoueur.setInit(missileJoueur.v0x + 35* tempsVitesse, missileJoueur.v0y- 35 * tempsVitesse);
				}else{
					missileJoueur.orientation = 1; 
					missileJoueur.setIcon(skinMissileGauche);
					missileJoueur.setInit(-missileJoueur.v0x  - 35 * tempsVitesse, missileJoueur.v0y- 35 * tempsVitesse);
				}
				missileJoueur.setVisible(true) ;
			}
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
    

    public void setinvincible(){
        immortel = true ;
        tempsInv = 0 ; 
        invincible = new Timer (125 , new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tempsInv++ ;
                if ((tempsInv % 2 == 0) && (vie != 0)){
                    setVisible(true);
                } else if (vie != 0){
                    setVisible(false);
                }
                if (tempsInv == 10 ){
                    immortel = false ; 
                    invincible.stop () ; 
                }
            }
        }) ;
        invincible.start() ;  
    }

    public void boost(JLabel labelBoost){
        tempsBoost = 0;
        this.boost = 1 ; 
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
                    labelBoost.setIcon(FenetreJeu.imageBoostUtilisee);
                    pas = pasSansBoost;
                    setDirection(directionDroite);
                } else if (tempsBoost == dureeBoost + cooldownBoost){
                    boost = 2;
                    labelBoost.setIcon(FenetreJeu.imageBoost);
                    timerBoost.stop();
                } else {
                    labelBoost.setText(String.valueOf(dureeBoost-tempsBoost));
                }
            }
        });
        timerBoost.start();
    }

    public double[] deplacements(HashSet<Integer> evenementClavier, int largeurFenetre, int hauteurFenetre, JLabel labelBoost){

        //Réinitialisation des forces //
		forceDeplacement[0] = 0;
		forceDeplacement[1] = 0;

		//Déplacements//

		if (evenementClavier.contains(keySet[3])) {
			if (evenementClavier.contains(keySet[1])) {
				forceDeplacement[0] = 0;
			} else {
				this.setDirection(true); // true = va vers la droite
				forceDeplacement[0] = this.pas;
			}
		}
		if (evenementClavier.contains(keySet[1])) {
			if (evenementClavier.contains(keySet[3])) {
				forceDeplacement[0] = 0;
			} else {
				this.setDirection(false); // false = va vers la gauche
				forceDeplacement[0] = -this.pas; 
			}
		} 
		if (evenementClavier.contains(keySet[2])) {
			if (evenementClavier.contains(keySet[0])) {
				forceDeplacement[1] = 0;
			} else {
				forceDeplacement[1] = this.pas;
			}
		} 
		if (evenementClavier.contains(keySet[0])) {
			if (evenementClavier.contains(keySet[2])) {
				forceDeplacement[1] = 0;
			}else{
				forceDeplacement[1] = -this.pas;
			}
		}
        if (evenementClavier.contains(keySet[5]) && (this.boost == 2)) {
			    this.boost(labelBoost);
			}

        //---------- Gestion de la physique de l'avion -----------//
		
		deltaT = System.currentTimeMillis() - tempsPrecedent;
		tempsPrecedent = System.currentTimeMillis();

		//PFD du J1//
		this.acceleration[0] = (forceDeplacement[0] - csteFrottementX*this.vitesse[0]) / this.masse;
		this.acceleration[1] = (forceDeplacement[1] + this.masse*cstePesenteur - csteFrottementY*this.vitesse[1]) / this.masse;

        //System.out.println("accélation selon x = " + acceleration[0] + " accélération selon y = " + acceleration[1]);

		this.vitesse[0] = this.vitesse[0] + this.acceleration[0] * deltaT*0.001; //*0.001 car deltaT est en milliseconde
		this.vitesse[1] = this.vitesse[1] + this.acceleration[1] * deltaT*0.001;

		this.position[0] = this.position[0] + this.vitesse[0] * deltaT*0.001;
		this.position[1] = this.position[1] + this.vitesse[1] * deltaT*0.001;

		if (this.position[0] > largeurFenetre-160){
			this.vitesse[0] = 0.0 ;
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

    public boolean peutEtreTouche(missile missileJoueur){
        return (missileJoueur.position [0] >this.position[0] &&  missileJoueur.position[0] < this.position[0] + this.skin.getIconWidth()
		&& missileJoueur.position[1]> this.position[1] &&  missileJoueur.position[1]< this.position[1] +this.skin.getIconHeight() &&
		missileJoueur.isVisible() == true) ; 
    }

    public void collision (missile missileJoueur){
        if (peutEtreTouche(missileJoueur)){
			this.Touche(); 
			missileJoueur.setVisible(false);  
        }
    }

    public void updatePointsDeVie(JLabel vies){
        if (this.vie == 2) {
            vies.setIcon(deuxPointsDeVie);
        }else if (this.vie == 1) {
            vies.setIcon(unPointDeVie);
        }else if (this.vie == 0) {
            vies.setIcon(zeroPointDeVie);
        }
    }
       
}
