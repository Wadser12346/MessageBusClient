package MessageBusFiles;

import java.io.*;
import java.net.Socket;
import java.sql.SQLOutput;

/**
 * THis class listens and displays to console
 */
public class ClientReceive implements Runnable {

    //TODO: Change to ChatMessage
    String receivedMessage;
    Socket socket;

    public ClientReceive(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectInputStream fromServer = null;
        try {
            fromServer = new ObjectInputStream(socket.getInputStream());
            while(true){
                receivedMessage = fromServer.readUTF();
                System.out.println(receivedMessage);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
