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
    private BlockingQueue<Packet> incoming;

    public ClientReceive(Socket connection, BlockingQueue<Packet> incoming){
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
                incoming.add((Packet)fromServer.readObject());
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
