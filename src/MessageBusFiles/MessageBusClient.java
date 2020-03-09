package MessageBusFiles;

import java.util.ArrayList;

/**
 * This object handles connecting with a server
 * When a user boots up the client. The client will handle the connection to a server
 * and maintain the
 */
public class MessageBusClient{
    ArrayList<Thread> chatrooms;

    public MessageBusClient(){
        chatrooms = new ArrayList<>();
    }

    public void addChatroom(){
        ClientChatroom clientChatroom = new ClientChatroom();
        Thread thread = new Thread(clientChatroom);
        thread.start();
        chatrooms.add(thread);
    }

    //TODO: Implement
    public void closeChatroom(int chatroomID){

    }
}
