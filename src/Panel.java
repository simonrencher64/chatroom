import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Panel extends JPanel implements ActionListener {

    final int PANEL_WIDTH = 500;
    final int PANEL_HEIGHT = 500;
    int x = 0;
    int y = 0;
    Timer timer;
    ArrayList<int[]> positions;


    Panel(){
        positions = new ArrayList<>();

        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(Color.white);
        timer = new Timer(1,this);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        for (int[] position : positions) {
            g2D.drawRect(position[1], position[2], 100, 100);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
