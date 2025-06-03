import java.io.*;
import java.net.Socket;
import java.util.ArrayList;



public class ClientHandler implements Runnable{

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    public static int idCounter = 0;
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private int clientID;
    private int x;
    private int y;


    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());
            this.clientID = idCounter;
            idCounter++;
            clientHandlers.add(this);
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
                    x = message[0];
                    y = message[1];
                    broadcastPositions();

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

    public void broadcastPositions() {
        int[][] message = new int[clientHandlers.size()][3];
        for(int i = 0; i < clientHandlers.size(); i++){
            message[i][0] = clientHandlers.get(i).clientID;
            message[i][1] = clientHandlers.get(i).x;
            message[i][2] = clientHandlers.get(i).y;
        }
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                if (clientHandler.clientID != clientID) {
                    clientHandler.outputStream.writeObject(message);
                }
            } catch (IOException e) {
                closeEverything(socket, inputStream, outputStream);
            }
        }
    }

    public void removeClientHandler() {

        clientHandlers.remove(this);

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