package MessageBusFiles;

import MessageBusFiles.InternalWrappers.ConnectionAttempt;
import MessageTypes.ChatMessage;
import MessageTypes.Packet;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * CLient will manage
 *  one thread for sending messages
 *  one thread for receiving messages
 *  a list of chatrooms
 */
public class Client {
    private ClientSend send;
    private ClientReceive receive;
    private ClientPublish publish;
    private ArrayList<ClientChatroom> chatrooms;
    private Socket serverConnection;
    private boolean run;// This is so the disconnect function can set this to true and stop the while loop in run

    //TODO: Change to packet when available
//    private BlockingQueue<Packet> outgoing;
//    private BlockingQueue<Packet> incoming;
    private BlockingQueue<ChatMessage> outgoing;
    private BlockingQueue<ChatMessage> incoming;

    public Client(){
        outgoing = new ArrayBlockingQueue<>(100);
        incoming = new ArrayBlockingQueue<>(100);
        chatrooms = new ArrayList<>();
        run = false;
    }

    public void main() {
        run = true;
        try{
            setServerConnection("localhost", 8000);
            send = new ClientSend(serverConnection, outgoing);
            receive = new ClientReceive(serverConnection, incoming);
            publish = new ClientPublish(chatrooms, incoming, this);
            chatrooms.add(new ClientChatroom("Chat 1", outgoing));
            while(run){

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Client closing.");
        }
    }

    public void setServerConnection(String hostname, int port) throws IOException {
        serverConnection = new Socket(hostname, port);
    }

    public void setServerConnection(ConnectionAttempt attempt) throws IOException {
        setServerConnection(attempt.getServerName(), Integer.parseInt(attempt.getPortNumber()));
    }
}
