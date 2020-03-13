package MessageBusFiles;

import MessageTypes.ChatMessage;
import MessageTypes.Packet;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * CLient will manage
 *  one thread for sending messages
 *  one thread for receiving messages
 *  a list of chatrooms
 */
public class Client implements Runnable {
    private ClientSend send;
    private ClientReceive receive;
    private ArrayList<ClientChatroom> chatrooms;
    private Socket serverConnection;


    //TODO: Change to packet when available
    private BlockingQueue<Packet> outgoing;
    private BlockingQueue<Packet> incoming;

    @Override
    public void run() {
        try{
            setServerConnection("localhost", 8000);
            send = new ClientSend(serverConnection, outgoing);
            receive = new ClientReceive(serverConnection, incoming);
            chatrooms.add(new ClientChatroom("Chat 1", outgoing));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Client closing.");
        }
    }

    public void setServerConnection(String hostname, int port) throws IOException {
        serverConnection = new Socket(hostname, port);
    }
}
