import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame {

    JFrame frame;
    JLabel label;
    Action upAction;
    Action downAction;
    Action leftAction;
    Action rightAction;

    Frame(){

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);

        label = new JLabel();
        label.setBackground(Color.red);
        label.setBounds(100,100,100,100);
        label.setOpaque(true);

        upAction = new UpAction();
        downAction = new DownAction();
        leftAction = new LeftAction();
        rightAction = new RightAction();

        label.getInputMap().put(KeyStroke.getKeyStroke("UP"), "upAction");
        label.getActionMap().put("upAction", upAction);
        label.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "downAction");
        label.getActionMap().put("downAction", downAction);
        label.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "leftAction");
        label.getActionMap().put("leftAction", leftAction);
        label.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
        label.getActionMap().put("rightAction", rightAction);


        frame.add(label);
        frame.setVisible(true);
    }

    public class UpAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            label.setLocation(label.getX(), label.getY()-10);
        }
    }
    public class DownAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            label.setLocation(label.getX(), label.getY()+10);
        }
    }
    public class LeftAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            label.setLocation(label.getX()-10, label.getY());
        }
    }
    public class RightAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            label.setLocation(label.getX()+10, label.getY());
        }
    }
}




//import javax.swing.*;
//import javax.swing.border.Border;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//
//public class Frame extends JFrame implements ActionListener {
//
//    JButton button;
//    JTextField textField;
//    JLabel label;
//    JPanel enterPanel;
//    JScrollPane scrollPane;
//    JTextArea text;
//    JPanel textPanel;
//    Boolean loggedIn = false;
//
//    Frame(){
//
//
//
//        enterPanel = new JPanel();
//        enterPanel.setBackground(Color.red);
//        enterPanel.setBounds(0,0,500,50);
//
//
//        button = new JButton("Submit");
//        button.addActionListener(this);
//
//        textField = new JTextField();
//        textField.setPreferredSize(new Dimension(250,40));
//
//        label = new JLabel("Enter your username");
//
//        enterPanel.add(label);
//        enterPanel.add(textField);
//        enterPanel.add(button);
//
//        textPanel = new JPanel();
//        textPanel.setBackground(Color.gray);
//        textPanel.setBounds(0,50,500,500);
//
//        scrollPane = new JScrollPane();
//        text = new JTextArea();
//        text.setText("");
//        text.setEditable(false);
//
//        scrollPane.add(text);
//
//        textPanel.add(text);
//
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setLayout(null);
//        this.setSize(500,550);
//
//
//        this.add(enterPanel);
//        this.add(textPanel);
//
//        this.setResizable(false);
//        this.setVisible(true);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if(e.getSource()==button){
//            String text = textField.getText();
//            textField.setText("");
//            if(loggedIn == false){
//                login();
//                try {
//                    Client.connect(text);
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                }
//            } else {
//                Client.enterMessage(text);
//            }
//
//
//        }
//    }
//
//    public void login(){
//        loggedIn = true;
//        label.setText("Enter your message");
//    }
//
//    public void addMessage(String message){
//        text.setText(message + "\n" + text.getText());
//    }
//}
