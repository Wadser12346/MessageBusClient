package MessageBusFiles;

import CS4B.Messages.*;
import MessageBusFiles.InternalWrappers.ConnectionAttempt;
import MessageBusFiles.InternalWrappers.InternalPacket;
import MessageBusFiles.InternalWrappers.SendMessage;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * CLient will manage
 *  one thread for sending messages
 *  one thread for receiving messages
 *  a list of chatrooms
 */
public class Client extends Observable implements Observer, Runnable {
    private ClientSend send;
    private ClientReceive receive;
    private ClientPublish publish;
    private ArrayList<ClientChatroom> chatrooms;
    private Socket serverConnection;
    private boolean run;// This is so the disconnect function can set this to true and stop the while loop in run

    //TODO: Change to packet when available
    private BlockingQueue<Packet> outgoing;
    private BlockingQueue<Packet> incoming;

    public Client(){
        outgoing = new ArrayBlockingQueue<>(100);
        incoming = new ArrayBlockingQueue<>(100);
        chatrooms = new ArrayList<>();
        run = false;
    }

    public void main() {
        System.out.println("Starting Client Main");
        try{
            send = new ClientSend(serverConnection, outgoing);
            receive = new ClientReceive(serverConnection, incoming);
            publish = new ClientPublish(chatrooms, incoming);
            publish.addObserver(this);
            chatrooms.add(new ClientChatroom("Chat 1", outgoing));
            Thread self = new Thread(this);
            self.start();
        } finally {
            System.out.println("Client closing.");
        }
    }

    @Override
    public void run() {
        run = true;
        //Requesting ChatroomList
        outgoing.add(new Packet("Client", "N/A", new RequestChatroom(), "RequestChatroomList"));
        while(run){

        }
    }

    public void setServerConnection(String hostname, int port) throws IOException {
        serverConnection = new Socket(hostname, port);
    }

    public void setServerConnection(ConnectionAttempt attempt) throws IOException {
        setServerConnection(attempt.getServerName(), Integer.parseInt(attempt.getPortNumber()));
    }

    public void startChatroom(String chatroomName){
        //TODO: Check for already open classrooms
        chatrooms.add(new ClientChatroom(chatroomName, outgoing));
    }

    public void disconnect(){
        outgoing.add(new Packet("Client", "N/A", new DisconnectMessageClient(), "DisconnectMessageClient"));
        serverConnection = null;
    }

    public void sendMessage(SendMessage message){
        String chatroomName = message.getChatroomName();
        System.out.println("Sorting through chatrooms to find correct one.");
        for(ClientChatroom c : chatrooms){
            if (c.getChatroomName().equals(chatroomName)){
                System.out.println("Chatroom Found");
                c.sendMessage(message);
                break;
            }
        }
    }

    public void newChatroom(NewChatroom chatroom) {
        System.out.println("Sending Chatroom Request to Server.");
        outgoing.add(new Packet("client", "N/A", chatroom, "NewChatroomRequest"));
    }

    public void test(){
        System.out.println("Conducting Tests");
        receive.test();
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Passing Packet onto ClientUI");
        setChanged();
        notifyObservers(arg);//Pass it on to ClientUI
    }


}
