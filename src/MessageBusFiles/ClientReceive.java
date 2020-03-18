package MessageBusFiles;


import java.io.*;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import CS4B.Messages.*;


/**
 * This class listens and displays to console
 */
public class ClientReceive implements Runnable {

    //TODO: Change to ChatMessage
    Socket connection;
    //private BlockingQueue<Packet> incoming;
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
                incoming.add((ChatMessage)fromServer.readObject());
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            System.out.println("ClientReceive closing.");
        }
    }
}
