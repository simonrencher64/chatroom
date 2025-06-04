import javax.swing.*;
import java.awt.*;

public class OtherPlayer {
    public int id;
    public int x;
    public int y;
    public Frame frame;
    public JLabel label;

    public OtherPlayer(int id,int x,int y,Frame frame){
        this.id = id;
        this.x = x;
        this.y = y;
        this.frame = frame;
        this.label = new JLabel();
        label.setBackground(Color.red);
        label.setBounds(x,y,100,100);
        label.setOpaque(true);
        //frame.createLabel(label);
    }

    public void updateLocation(int x, int y){
        this.x = x;
        this.y = y;
        //frame.updateLabel(label,x,y);
    }
}
