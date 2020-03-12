package MessageBusFiles;



import java.io.IOException;
import java.net.Socket;
import MessageTypes.*;
import java.util.ArrayList;

/**
 * This class handles one instance of the chatroom that the client is participating in.
 * This object will maintain the connection to the chatroom on the server side
 * It will display the messages coming in and send messages out
 * It will store a log of the chat
 */
public class ClientChatroom implements Runnable{
    private ArrayList<ChatMessage>chatlog;//TODO: Will we implement serializable to keep this chat log in between sessions? or nah



    public ClientChatroom() {
    }

    public void sendMessage(ChatMessage chatMessage){
        //Combines the chat message with the username and sends it to the server
    }

    /**
     * displayMessage(String Message)
     * @param chatMessage: Display the MessageBusFiles.ChatMessage object
     */
    public void displayMessage(ChatMessage chatMessage){

    }

    public void receiveMessage(ChatMessage chatMessage){
        //Add the message to the log
        chatlog.add(chatMessage);
        displayMessage(chatMessage);
    }



    @Override
    public void run() {

    }
}
