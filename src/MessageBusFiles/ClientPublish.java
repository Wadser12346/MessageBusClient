package MessageBusFiles;

import CS4B.Messages.*;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class ClientPublish implements Runnable {

    private ArrayList<ClientChatroom> chatrooms;
    private BlockingQueue<ChatMessage> incoming;
    //private BlockingQueue<Packet> incoming;
    private Client client;

    public ClientPublish(ArrayList<ClientChatroom> chatrooms, BlockingQueue<ChatMessage> incoming, Client client){
        this.chatrooms = chatrooms;
        this.incoming = incoming;
        this.client = client;
        Thread self = new Thread(this);
        self.start();
    }


    @Override
    public void run() {
        // Grabs available messages from incoming and processes it and sends to relevant object
        try {
//                Packet packet = incoming.take();// Get the packet from the incoming queue
//                if (packet.getMessageType() == "ChatMessage"){
//                    ChatMessage message = (ChatMessage)packet.getMessage();// Extract the message from packet
//                    String intendedRoom = packet.getChatroomName();// Get the chatroom the message is for
//                    for (ClientChatroom c : chatrooms){
//                        if (c.getChatroomName() == intendedRoom){// If it finds the correct chatrooms(s)
//                            c.receiveMessage(message);
//                            break;// It found the correct chatroom so no need to go any further
//                        }
//                    }
//                }
            while (true) {
                ChatMessage message = incoming.take();// Extract the message from packet

                for (ClientChatroom c : chatrooms) {
                    c.receiveMessage(message);
                }
            }

        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("ClientPublish closing.");
        }
    }
}
