import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.util.*;

public class Avion extends JLabel{
    int vie = 3;
    int pas = 20;
    int masse = 10; //masse en tonne

    // Vecteurs de l'avion
    
    double[] vitesse = {0,0};
    double[] acceleration = {0,0};
    double[] position = {0, 0};

    boolean directionDroite; //la directionDroite vaut true si l'avion se dirige vers la droite et false si l'avion se dirige vers la gauche

    boolean missileCharge = true;
    boolean immortel = false;

    ImageIcon skin;
    ImageIcon missile; 
    
    int largueurAvion;
    int longeurAvion;

    public Avion (ImageIcon image, int x, int y) {
        super(image);
        skin = image;
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
        } else if (direction == "gauche"){
            directionDroite = false;
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
}
