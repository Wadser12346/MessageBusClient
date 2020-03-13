package MessageBusFiles;

import MessageTypes.*;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class ClientPublish implements Runnable {

    private ArrayList<ClientChatroom> chatrooms;
    private BlockingQueue<Packet> incoming;
    private Client client;

    public ClientPublish(ArrayList<ClientChatroom> chatrooms, BlockingQueue<Packet> incoming, Client client){
        this.chatrooms = chatrooms;
        this.incoming = incoming;
        this.client = client;
        Thread self = new Thread(this);
        self.start();
    }


    @Override
    public void run() {
        // Grabs available messages from incoming and processes it and sends to relevant object
        while(true){
            try {
                Packet packet = incoming.take();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
