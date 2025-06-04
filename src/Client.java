import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    public static Client client;
    public static Frame frame;

    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    private ArrayList<OtherPlayer> others = new ArrayList<>();





    public Client(Socket socket) {
        try {
            this.socket = socket;
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            closeEverything(socket, inputStream, outputStream);
        }
    }

    public void sendMessage(int x, int y) {
        try {
            int[] message = new int[2];
            message[0] = x;
            message[1] = y;
            outputStream.writeObject(message);
        } catch (IOException e) {
            closeEverything(socket, inputStream, outputStream);
        }
    }

    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int[][] message;

                while (socket.isConnected()) {
                    try {
                        message = (int[][]) inputStream.readObject();

                        for (int[] ints : message) {
                            updatePosition(ints[0], ints[1], ints[2]);
                        }

                    } catch (IOException e) {
                        closeEverything(socket, inputStream, outputStream);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, ObjectInputStream bufferedReader, ObjectOutputStream bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if(socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updatePosition(int id, int x, int y){
        for (OtherPlayer other : others) {
            if (other.id == id) {
                other.updateLocation(x,y);
                return;
            }
        }
        others.add(new OtherPlayer(id,x,y,frame));
    }

    public static void main(String[] args) throws IOException {

        SwingUtilities.invokeLater(() -> frame = new Frame());
        Socket socket = new Socket("localhost", 1234);
        client = new Client(socket);
        client.listenForMessage();
    }



    public static void enterMessage(int x, int y){
        client.sendMessage(x,y);
    }
}
