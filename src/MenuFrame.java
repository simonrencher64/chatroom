import javax.swing.*;

public class MenuFrame extends JFrame {

    MenuPanel menuPanel;

    public MenuFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuPanel = new MenuPanel();
        this.add(menuPanel);
        this.pack();
        this.setVisible(true);
    }
}
