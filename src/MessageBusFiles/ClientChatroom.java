package MessageBusFiles;



import java.io.IOException;
import java.net.Socket;
import MessageTypes.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

/**
 * This class handles one instance of the chatroom that the client is participating in.
 * This object will maintain the connection to the chatroom on the server side
 * It will display the messages coming in and send messages out
 * It will store a log of the chat
 */
public class ClientChatroom implements Runnable{
    private String chatroomName;
    private ArrayList<ChatMessage>chatlog;
    private BlockingQueue<Packet> outgoing;


    public ClientChatroom(String chatroomName, BlockingQueue<Packet> outgoing) {
        this.chatroomName = chatroomName;
        this.outgoing = outgoing;
        Thread self = new Thread(this);
        self.start();
    }

    public void sendMessage(ChatMessage chatMessage){
        //Combines the chat message with the username and sends it to the server
    }

    /**
     * displayMessage(String Message)
     * @param chatMessage: Display the MessageBusFiles.ChatMessage object
     */
    public void displayMessage(ChatMessage chatMessage){
        System.out.println(chatMessage.getStringMessage());
    }

    public String getChatroomName(){
        return chatroomName;
    }

    @Override
    public String toString() {
        return "Chatroom: " + chatroomName;
    }

    public void receiveMessage(ChatMessage chatMessage){
        //Add the message to the log
        chatlog.add(chatMessage);
        displayMessage(chatMessage);
    }



    @Override
    public void run() {
        int i = 0;
        Scanner input = new Scanner(System.in);
        while (i < 10){
            String message = input.nextLine();
            outgoing.add(new Packet("", chatroomName,new ChatMessage(new StringMessage(message)), "ChatMessage"));
            ++i;
        }
    }
}
