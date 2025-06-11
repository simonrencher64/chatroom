import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MenuPanel extends JPanel implements ActionListener {
    JButton createButton;
    JButton joinButton;
    JTextField portField;

    public MenuPanel(){
        createButton = new JButton("Create Server");
        createButton.addActionListener(this);
        portField = new JTextField();
        portField.setText("1234");
        joinButton = new JButton("Join Server");
        joinButton.addActionListener(this);
        this.add(createButton);
        this.add(portField);
        this.add(joinButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
            try {
                String port = portField.getText();
                Main.createServer(port);
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
