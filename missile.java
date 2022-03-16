
import javax.swing.ImageIcon ;
import javax.swing.JLabel ;

public class missile extends JLabel{
    double masse = 50  ; 
    int PosX ; 
    int PosY ; 
    ImageIcon missile = new ImageIcon("Images/missiles.jpg") ; 
    public missile (int x, int y) {
        PosX=x;
        PosY=y;
        this.setBounds(PosX, PosY, missile.getIconWidth(), missile.getIconHeight());
    }
    public void updatePos(int x, int y){
        this.PosX=x;
        this.PosY=y;
        this.setLocation(PosX, PosY);
    }

}   

