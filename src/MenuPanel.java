import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {
    JButton createButton;
    JButton joinButton;

    public MenuPanel(){
        createButton = new JButton("Create Server");
        joinButton = new JButton("Join Server");
        this.add(createButton);
        this.add(joinButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==createButton){

        }
    }
}
