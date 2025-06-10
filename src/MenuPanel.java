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
        if(e.getSource()==createButton){
            try {
                createServer();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void createServer() throws IOException {
        String[] strings = new String[0];
        Server.main(strings);

    }

    public void joinServer(){

    }
}
