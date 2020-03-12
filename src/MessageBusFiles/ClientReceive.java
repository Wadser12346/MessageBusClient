package MessageBusFiles;


import java.io.*;
import java.net.Socket;
import MessageTypes.*;


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

        try {
            //fromServer = new ObjectInputStream(socket.getInputStream());
            ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
            while(true){
                ChatMessage toDisplay = (ChatMessage)fromServer.readObject();
                System.out.println(toDisplay.getStringMessage());
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
