package MessageBusFiles;


import MessageTypes.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;


public class ClientSend implements Runnable{
    private Socket connection;
    //private BlockingQueue<Packet> outgoing;
    private BlockingQueue<ChatMessage> outgoing;

    public ClientSend(Socket connection, BlockingQueue<ChatMessage> outgoing){
        this.connection = connection;//Reference to socket connection
        this.outgoing = outgoing;// Reference to outgoing blocking queue
        Thread self = new Thread(this);
        self.start();
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream toServer = new ObjectOutputStream(connection.getOutputStream());
            while(true){
                toServer.writeObject(outgoing.take());//Pull from blocking queue
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("ClientSend closing.");
        }
    }
}
