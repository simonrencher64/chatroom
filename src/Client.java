import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    public static Client client;
    public static Frame frame;

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    private ArrayList<OtherPlayer> others = new ArrayList<>();

    public int id;

    public Client(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendMessage(String message) {
        try {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String message;

                while (socket.isConnected()) {
                    try {
                        message = bufferedReader.readLine();
                        System.out.println(message);
                        int msgID = Integer.parseInt(message.substring(0,message.indexOf(",")));
                        int msgX = Integer.parseInt(message.substring(message.indexOf(",")+1,message.indexOf(" ")));
                        int msgY = Integer.parseInt(message.substring(message.indexOf(" ")+1));
                        //frame.addMessage(msgFromGroupChat);
                        if(msgID >= others.size()){
                            others.add(new OtherPlayer(msgID, msgX, msgY));
                        } else {
                            others.get(msgID).updateLocation(msgX,msgY);
                        }
                    } catch (IOException e) {
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
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



    public static void enterMessage(String message){
        client.sendMessage(message);
        //frame.addMessage(message);
    }
}
