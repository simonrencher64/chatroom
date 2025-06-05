import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {

    JButton button;
    JTextField hostField;
    JTextField portField;


    MenuPanel(){
        button = new JButton("Join");
        button.addActionListener(this);

        hostField = new JTextField();
        hostField.setPreferredSize(new Dimension(250,40));
        hostField.setText("localhost");

        portField = new JTextField();
        portField.setPreferredSize(new Dimension(250,40));
        portField.setText("1234");

        this.add(button);
        this.add(hostField);
        this.add(portField);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String host = hostField.getText();
        int port = Integer.parseInt(portField.getText());
        if(e.getSource()==button && !host.isEmpty()){
            if(Client.attemptConnection(host, port)){
                Client.frame.connect();
            } else {
                hostField.setText("");
                portField.setText("");
            }
        }
    }
}
