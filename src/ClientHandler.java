import java.io.*;
import java.net.Socket;
import java.util.ArrayList;



public class ClientHandler implements Runnable{

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private int clientID;


    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());
            this.clientID = clientHandlers.size();
            clientHandlers.add(this);
            outputStream.write(this.clientID);
        } catch (IOException e) {
            closeEverything(socket, inputStream, outputStream);
        }
    }

    @Override
    public void run() {
        int[] message;

        while (socket.isConnected()) {
            try {
                message = (int[]) inputStream.readObject();
                if(message != null){
                    broadcastMessage(message);
                } else {
                    removeClientHandler();
                    break;
                }

            } catch (IOException e) {
                closeEverything(socket, inputStream, outputStream);
                break;
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void broadcastMessage(String messageToSend) {
        System.out.println(messageToSend);
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                if (clientHandler.clientID != clientID) {
                    clientHandler.outputStream.write(clientID + "," + messageToSend);
                }
            } catch (IOException e) {
                closeEverything(socket, inputStream, outputStream);
            }
        }
    }

    public void removeClientHandler() {

        clientHandlers.remove(this);
        broadcastMessage("end "+clientID);

    }

    public void closeEverything(Socket socket, ObjectInputStream bufferedReader, ObjectOutputStream bufferedWriter) {
        removeClientHandler();
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}