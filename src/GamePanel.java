import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener {

    final int PANEL_WIDTH = 500;
    final int PANEL_HEIGHT = 500;
    int x = 0;
    int y = 0;
    Timer timer;
    ArrayList<int[]> positions;


    GamePanel(){
        positions = new ArrayList<>();

        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(Color.white);
        timer = new Timer(1,this);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.RED);
        g2D.drawRect(0,0,500,500);
        for (int[] position : positions) {
            if(position[0] == Client.it){
                g2D.fillRect(position[1], position[2], 10, 10);
            }
            g2D.drawRect(position[1], position[2], 10, 10);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
