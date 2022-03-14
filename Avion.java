import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Avion extends JLabel{
    int vie = 3;
    boolean missileCharge = true;
    ImageIcon skin;
    boolean immortel = false;
    int posX = 100;
    int posY = 500;

    public Avion (ImageIcon image, int x, int y) {
        super();
        skin = image;
        posX=x;
        posY=y;
        this.setBounds(posX, posY, skin.getIconWidth(), skin.getIconHeight());
        
    }

    public void Touchee(){
        if (this.immortel == false){
            vie = vie -1;
            this.immortel = true;
        }
    }
    
    public void Recharge(){
        if (missileCharge == false){
            missileCharge = true;
        }
    }

    public void Tire(){
        if (missileCharge == true){
            missileCharge = false;
        }
    }

    public void updatePos(int x, int y){
        this.posX=x;
        this.posY=y;
    }
}
