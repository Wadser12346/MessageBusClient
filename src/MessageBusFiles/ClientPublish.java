package MessageBusFiles;

import CS4B.Messages.*;
import MessageBusFiles.InternalWrappers.InternalPacket;

import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.BlockingQueue;

public class ClientPublish extends Observable implements Runnable {

    private ArrayList<ClientChatroom> chatrooms;
    private BlockingQueue<Packet> incoming;

    public ClientPublish(ArrayList<ClientChatroom> chatrooms, BlockingQueue<Packet> incoming){
        this.chatrooms = chatrooms;
        this.incoming = incoming;
        Thread self = new Thread(this);
        self.start();
    }


    @Override
    public void run() {
        // Grabs available messages from incoming and processes it and sends to relevant object
        try {
            while (true) {
                Packet packet = incoming.take();// Get the packet from the incoming queue
                if (packet.getMessageType().equals("ChatMessage")){
                    System.out.println("Message Received");
                    ChatMessage message = (ChatMessage)packet.getMessage();// Extract the message from packet
                    String intendedRoom = packet.getChatroomName();// Get the chatroom the message is for
                    for (ClientChatroom c : chatrooms){
                        if (c.getChatroomName() == intendedRoom){// If it finds the correct chatrooms(s)
                            c.receiveMessage(message);
                            break;// It found the correct chatroom so no need to go any further
                        }
                    }
                }
                else if(packet.getMessageType().equals("ChatroomList")){
                    //Send this list to Client so it can update the UI
                    System.out.println("Sending ChatroomList to Client");
                    setChanged();
                    notifyObservers(new InternalPacket(packet.getMessageType(), packet.getMessage()));
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
