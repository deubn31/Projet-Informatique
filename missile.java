
import javax.swing.ImageIcon ;
import javax.swing.JLabel ;

public class missile extends JLabel{
    double masse = 50  ; 
    int PosX ; 
    int PosY ; 
    ImageIcon skin; 
    int orientation ;// 0 pour gauche vers droite , 1 pour l'inverse
    public missile (ImageIcon image,int x, int y) {
        super(image);
        skin = image;
        PosX=x;
        PosY=y;
        this.setBounds(PosX, PosY, skin.getIconWidth(), skin.getIconHeight());
    }
    public void updatePos(int x, int y){
        this.PosX=x;
        this.PosY=y;
        this.setLocation(PosX, PosY);
    }


}   

