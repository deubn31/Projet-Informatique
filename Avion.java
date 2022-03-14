import javax.swing.ImageIcon;

public class Avion {
    int vie = 3;
    boolean missileCharge = true;
    ImageIcon skin;
    boolean immortel = false;

    public Avion (ImageIcon image) {
        skin = image;
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
}
