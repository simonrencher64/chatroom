import javax.swing.*;
import java.awt.event.*;

public class Frame extends JFrame implements KeyListener{


    GamePanel panel;
    MenuPanel menuPanel;



    Frame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuPanel = new MenuPanel();
        this.add(menuPanel);
        this.pack();
        this.setVisible(true);

    }

    public void connect(){
        System.out.println("Connect");

        panel = new GamePanel();
        this.add(panel);
        this.remove(menuPanel);
        this.pack();

        sendLocation(panel.x,panel.y);
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()) {
            case 65:
                panel.x-=10;
                break;
            case 87:
                panel.y-=10;
                break;
            case 83:
                panel.y+=10;
                break;
            case 68:
                panel.x+=10;
                break;
        }
        sendLocation(panel.x,panel.y);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }



    public void sendLocation(int x,int y){
        Client.enterMessage(x,y);
    }


}