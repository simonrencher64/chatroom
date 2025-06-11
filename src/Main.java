import javax.swing.*;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Main {

    static MenuFrame menuFrame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> menuFrame = new MenuFrame());
    }

    public static void createServer(String port) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String[] strings = new String[1];
                strings[0] = port;
                try {
                    Server.main(strings);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public static void joinServer() throws IOException {
        menuFrame.setVisible(false);
        String[] strings = new String[0];
        Client.main(strings);
    }
}
