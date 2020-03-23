package MessageBusFiles;


import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import CS4B.Messages.*;


/**
 * This class listens to incoming packets from the server and passes it on to the BlockingQueue
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

    public void test(){
        System.out.println("Spoofing chatroom incoming");
        ArrayList<String>chats = new ArrayList<>();
        chats.add("CS4A");
        chats.add("CS4B");
        incoming.add(new Packet("Client", "", new ChatroomList(chats), "ChatroomList"));
    }

    @Override
    public void run() {

        try {
            ObjectInputStream fromServer = new ObjectInputStream(connection.getInputStream());
            while(true){
                incoming.add((Packet) fromServer.readObject());// This adds the packet, does not decode or anything
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            System.out.println("ClientReceive closing.");
        }
    }
}
