import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Frame extends JFrame implements ActionListener {

    JButton button;
    JTextField textField;
    JLabel label;
    Boolean loggedIn = false;

    Frame(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        button = new JButton("Submit");
        button.addActionListener(this);

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(250,40));

        label = new JLabel("Enter your username");


        this.add(button);
        this.add(textField);
        this.add(label);
        this.setResizable(true);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button){
            String text = textField.getText();
            if(loggedIn == false){
                textField.setText("");
                loggedIn = true;
                label.setText("Enter your message");
                try {
                    Client.connect(text);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                Client.enterMessage(text);
            }


        }
    }
}
