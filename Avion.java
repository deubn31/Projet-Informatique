import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.util.*;

public class Avion extends JLabel{
    int vie = 3;
    boolean missileCharge = true;
    ImageIcon skin;
    ImageIcon missile; 
    boolean immortel = false;
    int posX = 0;
    int posY = 0;
    int pas = 20; 

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
