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

    public int id;

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
                String message;

                while (socket.isConnected()) {
                    try {
                        message = inputStream.readLine();
                        System.out.println(message);
                        if(message.length() == 1){
                            id = Integer.parseInt(message);
                        } else {
                            int msgID = Integer.parseInt(message.substring(0,message.indexOf(",")));
                            int msgX = Integer.parseInt(message.substring(message.indexOf(",")+1,message.indexOf(" ")));
                            int msgY = Integer.parseInt(message.substring(message.indexOf(" ")+1));
                            //frame.addMessage(msgFromGroupChat);
                            if(msgID >= others.size()){
                                others.add(new OtherPlayer(msgID, msgX, msgY));
                            } else {
                                others.get(msgID).updateLocation(msgX,msgY);
                            }
                        }

                    } catch (IOException e) {
                        closeEverything(socket, inputStream, outputStream);
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

    public static void main(String[] args) throws IOException {

        SwingUtilities.invokeLater(() -> frame = new Frame());
        Socket socket = new Socket("localhost", 1234);
        client = new Client(socket);
        client.listenForMessage();
    }



    public static void enterMessage(int x, int y){
        client.sendMessage(x,y);
        //frame.addMessage(message);
    }
}
