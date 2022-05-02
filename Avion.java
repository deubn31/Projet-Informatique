import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Avion extends JLabel{

    public int vie = 3; //Un avion a par défaut 3 vies
    private int pas; //Force de déplacement par défaut de l'avion selon une direction

    public int[] keySet; //Liste des touches auxquelles répond l'avion

    private int tempsBoost; //Compteur du temps écoulé depuis le début du boost
    private Timer timerBoost;

    private Timer invincible; 
    private int tempsInv; //Compteur du temps écoulé depuis le début de l'invincibilité

    private int boost = 2; // 2 si disponible ; 1 si en cours d'utilisation ; 0 si en rechargement
    private int pasSansBoost = 10000; //Par défaut, le pas est de 10000
    private int pasAvecBoost = (int)(1.7*pasSansBoost);
    private final int dureeBoost = 4;  //durée du boost en seconde 
    private final int cooldownBoost = 6; //cooldown du boost en seconde 

    private int masse = 10; //masse en tonnes (peut varier en fonction de l'avion)

    // Vecteurs de l'avion
    
    private double[] vitesse = {0,0};
    private double[] acceleration = {0,0};
    public double[] position = {0, 0};
    private int[] forceDeplacement = {0,0};

    private boolean directionDroite; //directionDroite vaut true si l'avion se dirige vers la droite et false si l'avion se dirige vers la gauche

    private boolean immortel = false;

    private ImageIcon skinAvionDroite, skinAvionGauche, skinAvionDroiteBoost, skinAvionGaucheBoost, skin; //skin --> skin utilisé actuelement
    
    public int largueurAvion;
    public int longeurAvion;

    public ImageIcon troisPointsDeVie, deuxPointsDeVie, unPointDeVie, zeroPointDeVie;

    public long tempsPrecedent; //Temps du système lors de l'exécution précédente de la méthode "deplacements"
	private long deltaT; //Différence de temps du système entre l'exécution de la méthode "deplacements" précédente et l'actuelle

    final double cstePesenteur = 200;
	final double csteFrottementX = 8;
	final double csteFrottementY = 5;

    public Avion (ImageIcon[] skinsAvion, int[] touches, int x, int y) {

        super(skinsAvion[0]);

        troisPointsDeVie = new ImageIcon("Images/3viesmodif.png");
		deuxPointsDeVie = new ImageIcon("Images/2viesModif.png");
		unPointDeVie = new ImageIcon("Images/1vieModif.png");
		zeroPointDeVie = new ImageIcon("Images/0vieModif.png");
        
        skinAvionDroite = skinsAvion[0];
        skinAvionGauche = skinsAvion[1];
        skinAvionDroiteBoost = skinsAvion[2];
        skinAvionGaucheBoost = skinsAvion[3];
        skin = skinAvionDroite;  //Skin de droite est pris par défault => modifier l'orientation de l'avion avec la méthode "setDirection" si celui de Gauche est souhaité

        keySet = touches;

        pas = pasSansBoost; 

        position[0] = x;
        position[1] = y;

        this.setBounds((int)position[0], (int)position[1], skin.getIconWidth(), skin.getIconHeight());

        longeurAvion = skin.getIconHeight();
        largueurAvion = skin.getIconWidth();
    }

    public void Touche(){ // Retire une vie et met l'avion en invincible s'il n'est pas immortel
        if (this.immortel == false){
            vie = vie-1;
            this.setinvincible();
        }
    }

    public void Tire(missile missileJoueur, int largeurFenetre , int longueurFenetre, int tempsVitesse, ImageIcon skinMissileDroite , ImageIcon skinMissileGauche){
        if (missileJoueur.estPresent(largeurFenetre , longueurFenetre)){ // s'il est présent
				missileJoueur.updatePos((int)this.position[0] + 60, (int)this.position[1] + 50); // Sa position est sur l'avion
				// Update du skin en fonction de la direction de l'avion  
				if (this.directionDroite == true){
					missileJoueur.orientation = 0 ; 
					missileJoueur.setIcon(skinMissileDroite);
					missileJoueur.setInit(missileJoueur.v0x + 45* tempsVitesse, missileJoueur.v0y- 40 * tempsVitesse);
				}else{
					missileJoueur.orientation = 1; 
					missileJoueur.setIcon(skinMissileGauche);
					missileJoueur.setInit(-missileJoueur.v0x  - 40 * tempsVitesse, missileJoueur.v0y- 40 * tempsVitesse);
				}
				missileJoueur.setVisible(true) ;
			}
    }

    public void updatePos(int x, int y){ //Permet de changer la position de l'avion
        this.position[0]=x;
        this.position[1]=y;
        this.setLocation((int)position[0], (int)position[1]);
    }

    public void setDirection(boolean direction){ //Permet de mettre à jour le skin de l'avion en fonction de ses caractéristiques (boost et orientation)
        directionDroite = direction;
        if (directionDroite == true){
            if (boost == 1) {
                this.setIcon(skinAvionDroiteBoost);
                skin = skinAvionDroiteBoost;
                this.setSize(skin.getIconWidth(), skin.getIconHeight());
            } else {
                this.setIcon(skinAvionDroite);
                skin = skinAvionDroite;
                this.setSize( skin.getIconWidth(), skin.getIconHeight());
            }
        } else {
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
    

    public void setinvincible(){ //Permet de rendre l'avion invincible pendant un certain temps après qu'il ait été touché, sauf s'il est mort
        immortel = true ;
        tempsInv = 0 ; 
        invincible = new Timer (125 , new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tempsInv++ ;
                if ((tempsInv % 2 == 0) && (vie != 0)){ //Permet de faire clignoter l'avion sauf s'il est mort
                    setVisible(true);
                } else if (vie != 0){
                    setVisible(false);
                }
                if (tempsInv == 10 ){ // L'invincibilité s'arrête au bout de 5 secondes (10*500ms)
                    immortel = false ; 
                    invincible.stop () ; 
                }
            }
        }) ;
        invincible.start() ;  
    }

    public void boost(JLabel labelBoost){ //Permet de procurer un boost de vitesse à l'avion, de changer son skin, et de lancer un compteur du temps restant de boost
        tempsBoost = 0;
        this.boost = 1 ; 
        labelBoost.setIcon(null); //On retire l'image de boost affiché
        labelBoost.setText(String.valueOf(dureeBoost-tempsBoost)); //On initialise le compteur à l'écran
        this.pas = pasAvecBoost;
        this.setDirection(directionDroite); //On change le skin
        timerBoost = new Timer(1000, new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e) {
                tempsBoost++;
                if ((tempsBoost >= dureeBoost) && (tempsBoost < dureeBoost + cooldownBoost)){ //Appariton de l'image de boost utilisé lorsque le compteur est terminé
                    boost = 0;
                    labelBoost.setText(null);
                    labelBoost.setIcon(FenetreJeu.imageBoostUtilisee);
                    pas = pasSansBoost;
                    setDirection(directionDroite);
                } else if (tempsBoost == dureeBoost + cooldownBoost){ //Changement de l'image du boost lorsqu'il est utilisé puis rechargé
                    boost = 2;
                    labelBoost.setIcon(FenetreJeu.imageBoost);
                    timerBoost.stop();
                } else { //Mise à jour du compteur
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
			if (evenementClavier.contains(keySet[1])) {//Si les 2 touches opposée sont appuyées en même temps (D et Q ici) les forces s'annulent
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

        //Boost
        if (evenementClavier.contains(keySet[5]) && (this.boost == 2)) { 
			    this.boost(labelBoost);
			}

        //---------- Gestion de la physique de l'avion -----------//
		
		deltaT = System.currentTimeMillis() - tempsPrecedent;
		tempsPrecedent = System.currentTimeMillis();

		//PFD du J1//
		this.acceleration[0] = (forceDeplacement[0] - csteFrottementX*this.vitesse[0]) / this.masse;
		this.acceleration[1] = (forceDeplacement[1] + this.masse*cstePesenteur - csteFrottementY*this.vitesse[1]) / this.masse;

		this.vitesse[0] = this.vitesse[0] + this.acceleration[0] * deltaT*0.001; //*0.001 car deltaT est en milliseconde
		this.vitesse[1] = this.vitesse[1] + this.acceleration[1] * deltaT*0.001;

		this.position[0] = this.position[0] + this.vitesse[0] * deltaT*0.001;
		this.position[1] = this.position[1] + this.vitesse[1] * deltaT*0.001;

         //Lorsque l'avion renconte le bord de l'écran, sa vitesse (selon x ou y en fonction du bord rencontré) devient nulle
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

    public boolean peutEtreTouche(missile missileJoueur){ //Vérifie si l'avion et le missile interfèrent et donc s'il y a collision
        return (missileJoueur.position [0] >this.position[0] &&  missileJoueur.position[0] < this.position[0] + this.skin.getIconWidth()
		&& missileJoueur.position[1]> this.position[1] &&  missileJoueur.position[1]< this.position[1] +this.skin.getIconHeight() &&
		missileJoueur.isVisible() == true) ; 
    }

    public void collision (missile missileJoueur){ //Efface le missile et exécute la méthode "Touche" lorsqu'il y a collision
        if (peutEtreTouche(missileJoueur)){
			this.Touche(); 
			missileJoueur.setVisible(false);  
        }
    }

    public void updatePointsDeVie(JLabel vies){ //Gère l'affichage des points de vie sur l'écran en fonction de ceux de l'avion
        if (this.vie == 2) {
            vies.setIcon(deuxPointsDeVie);
        }else if (this.vie == 1) {
            vies.setIcon(unPointDeVie);
        }else if (this.vie == 0) {
            vies.setIcon(zeroPointDeVie);
        }
    }
    
}
