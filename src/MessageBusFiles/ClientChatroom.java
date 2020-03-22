package MessageBusFiles;

import CS4B.Messages.*;
import MessageBusFiles.InternalWrappers.SendMessage;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

/**
 * This class handles one instance of the chatroom that the client is participating in.
 * This object will maintain the connection to the chatroom on the server side
 * It will display the messages coming in and send messages out
 * It will store a log of the chat
 */
public class ClientChatroom {
    private String chatroomName;
    private ArrayList<ChatMessage>chatlog;
    private BlockingQueue<Packet> outgoing;


    public ClientChatroom(String chatroomName, BlockingQueue<Packet> outgoing) {
        this.chatroomName = chatroomName;
        this.outgoing = outgoing;
        chatlog = new ArrayList<>();
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

    public void receiveMessage(ChatMessage message){
        //Add the message to the log
        chatlog.add(message);
        displayMessage(message);
    }

    public void sendMessage(SendMessage message){
        System.out.println("Adding message to outgoing blocking queue.");
        outgoing.add(new Packet(message.getUsername(), message.getChatroomName(), message.getMessage(), "ChatMessage"));
    }

}
