package MessageBusFiles;


import java.io.*;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import MessageTypes.*;


/**
 * This class listens and displays to console
 */
public class ClientReceive implements Runnable {

    //TODO: Change to ChatMessage
    Socket connection;
    private BlockingQueue<ChatMessage> incoming;

    public ClientReceive(Socket connection, BlockingQueue<ChatMessage> incoming){
        this.connection = connection;
        this.incoming = incoming;
        Thread self = new Thread(this);
        self.start();
    }

    @Override
    public void run() {

        try {
            //fromServer = new ObjectInputStream(socket.getInputStream());
            ObjectInputStream fromServer = new ObjectInputStream(connection.getInputStream());
            while(true){
                ChatMessage toDisplay = (ChatMessage)fromServer.readObject();
                System.out.println(toDisplay.getStringMessage());

            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
