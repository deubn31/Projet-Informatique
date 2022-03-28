import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.util.*;

public class Avion extends JLabel{
    int vie = 3;

    int posX = 0;
    int posY = 0;
    int pas = 40 ;

    boolean directionDroite; //la directionDroite vaut true si l'avion se dirige vers la droite et false si l'avion se dirige vers la gauche

    boolean missileCharge = true;
    boolean immortel = false;

    ImageIcon skin;
    ImageIcon missile; 

    public Avion (ImageIcon image, int x, int y) {
        super(image);
        skin = image;
        posX=x;
        posY=y;
        this.setBounds(posX, posY, skin.getIconWidth(), skin.getIconHeight());
        missile = new ImageIcon("Images/missiles.jpg");
    }

    public Avion(ImageIcon image){
        super(image);
        skin = image;
        this.setBounds(posX, posY, skin.getIconWidth(), skin.getIconHeight());
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
        this.posX=x;
        this.posY=y;
        this.setLocation(posX, posY);
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
