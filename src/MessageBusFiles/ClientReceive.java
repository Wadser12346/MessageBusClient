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
    String receivedMessage;
    Socket socket;

    public ClientReceive(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        while(true){
            try {
                DataInputStream fromServer = new DataInputStream(socket.getInputStream());
                receivedMessage = fromServer.readUTF();
                System.out.println(receivedMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
