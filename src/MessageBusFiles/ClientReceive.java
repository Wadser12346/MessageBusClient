package MessageBusFiles;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.SQLOutput;

/**
 * THis class listens and displays to console
 */
public class ClientReceive implements Runnable {

    //TODO: Change to ChatMessage
    ChatMessage receivedMessage;
    Socket socket;

    public ClientReceive(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
            while(true){
                    receivedMessage = (ChatMessage) fromServer.readObject();
                    System.out.println(receivedMessage.getStringMessage().toString());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
