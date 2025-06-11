import javax.swing.*;
import java.awt.event.*;

public class Frame extends JFrame implements KeyListener{


    GamePanel panel;
    FramePanel framePanel;



    Frame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        framePanel = new FramePanel();
        this.add(framePanel);
        this.pack();
        this.setVisible(true);

    }

    public void connect(){
        panel = new GamePanel();
        this.add(panel);
        this.remove(framePanel);
        this.requestFocus();
        this.pack();

        sendLocation(panel.x,panel.y);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()) {
            case 65:
                if(panel.x > 0){
                    panel.x-=10;
                }
                break;
            case 87:
                if(panel.y > 0){
                    panel.y-=10;
                }
                break;
            case 83:
                if(panel.y < 490){
                    panel.y+=10;
                }
                break;
            case 68:
                if(panel.x < 490){
                    panel.x+=10;
                }
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