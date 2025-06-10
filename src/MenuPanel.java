import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MenuPanel extends JPanel implements ActionListener {
    JButton createButton;
    JButton joinButton;

    public MenuPanel(){
        createButton = new JButton("Create Server");
        createButton.addActionListener(this);
        joinButton = new JButton("Join Server");
        joinButton.addActionListener(this);
        this.add(createButton);
        this.add(joinButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
            try {
                Main.createServer();
                createButton.setEnabled(false);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if(e.getSource() == joinButton){
            try {
                Main.joinServer();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
    }
}
